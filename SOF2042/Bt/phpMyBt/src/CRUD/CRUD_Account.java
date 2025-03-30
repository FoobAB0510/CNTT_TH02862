/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import Model.Account;
import Model.ChuyenDe;

import java.sql.*;
import java.util.ArrayList;

public class CRUD_Account {

    /**
     * Một chút quy tắc: 
     * - Role: 1 = Giảng viên, 2 = Sinh viên 
     * - Status: 0 = Không hoạt động, 1 = Hoạt động 
     * - Return 0 = Không thành công, 1 = Thành công 
     * - Gender: 0 = Nữ, 1 = Nam
     *
     * Note!: Vì GiangVien và SinhVien đều là bảng phụ của User, nên khi cập
     * nhật cần phải đi kèm GiangVien và SinhVien
     */
    public static Account getter(String id, String options) {
        ArrayList<Account> ls = get();
        for (Account cd : ls) {
            if(options.equals("id")) {
                if (String.valueOf(cd.getUserID()).equals(id)) {
                    return cd;
                }
            } else if(options.equals("user")) {
                if (String.valueOf(cd.getUserID()).equals(id)) {
                    return cd;
                }
            }
        }
        return null;
    }

    public static ArrayList<Account> get() {
        ArrayList<Account> account = new ArrayList<Account>();
        try {
            Statement db = CRUD_DAO.getConnect().createStatement();
            String sql = "SELECT * FROM [User]";

            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                Account acc = new Account();
                acc.setUserID(rs.getInt("UserID"));
                acc.setRole(rs.getInt("Role"));
                acc.setStatus(rs.getInt("Status"));
                acc.setEmail(rs.getString("Email"));
                acc.setPassword(rs.getString("Password"));
                acc.setCreateAt(rs.getDate("CreateAt"));
                account.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public static int create(String mail, String pass, int role, int status, String fullName, int gender, String address) {
        String sqlUser = "INSERT INTO [User] (email, password, role, CreateAt, Status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement psUser = con.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {

            psUser.setString(1, mail);
            psUser.setString(2, pass);
            psUser.setInt(3, role);
            psUser.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            psUser.setBoolean(5, status == 1);

            int affectedRows = psUser.executeUpdate();
            int doneWithSinhVien = 0;
            try {
                int creater = CRUD_HocVien.create(fullName, psUser.getGeneratedKeys().getInt("UserID"), status, gender, address);
                doneWithSinhVien = creater;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (affectedRows == 0 && doneWithSinhVien == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int update(int id, String mail, String pass, int role, int status, String fullName, String address, int gender) {
        String u = "UPDATE [User] SET email = ?, password = ?, role = ?, Status = ? WHERE UserID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement psUser = con.prepareStatement(u)) {

            psUser.setString(1, mail);
            psUser.setString(2, pass);
            psUser.setInt(3, role);
            psUser.setBoolean(4, status == 1);
            psUser.setInt(5, id);

            int rowsUser = psUser.executeUpdate();
            int updater = 0;
            try {
                CRUD_HocVien.update(fullName, id, status, gender, address);
                updater = 1;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (rowsUser > 0 && updater > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int delete(int id) {
        String sqlDeleteUser = "DELETE FROM [User] WHERE UserID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement psSelect = con.prepareStatement(sqlDeleteUser)) {
            psSelect.setInt(1, id);
            int deleted = 0;
            try {
                CRUD_HocVien.delete(id);
                deleted = 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            int rs = psSelect.executeUpdate();
            if (rs > 0 && deleted > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
