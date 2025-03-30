/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import Model.HocVien;
import java.sql.*;
import java.util.ArrayList;

public class CRUD_HocVien {

    public static HocVien getter(String id, String options) {
        ArrayList<HocVien> ls = get();
        for (HocVien cd : ls) {
            if(options.equals("user")) {
                if (String.valueOf(cd.getSinhVienID()).equals(id)) {
                    return cd;
                }
            }
        }
        return null;
    }

    public static ArrayList<HocVien> get() {
        ArrayList<HocVien> account = new ArrayList<HocVien>();
        try {
            Statement db = CRUD_DAO.getConnect().createStatement();
            String sql = "SELECT * FROM SinhVien";

            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                HocVien acc = new HocVien();
                acc.setSinhVienID(rs.getInt("SinhVienID"));
                acc.setFullName(rs.getString("FullName"));
                acc.setUserID(rs.getInt("UserID"));
                acc.setStatus(rs.getInt("Status"));
                acc.setAddress(rs.getString("Address"));
                acc.setGender(rs.getInt("Gender"));
                acc.setPhone(rs.getString("Phone"));
                account.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public static int create(String fullName, int UserID, int status, int gender, String address) {
        String sqlSinhVien = "INSERT INTO SinhVien (FullName, UserID, Status, Address, Gender) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement psSinhVien = con.prepareStatement(sqlSinhVien, Statement.RETURN_GENERATED_KEYS)) {

            psSinhVien.setString(1, fullName);
            psSinhVien.setInt(2, UserID);
            psSinhVien.setBoolean(3, status == 1);
            psSinhVien.setString(4, address);
            psSinhVien.setBoolean(5, gender == 1);

            int affectedRows = psSinhVien.executeUpdate();

            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int update(String fullName, int UserID, int status, int gender, String address) {
        String sql = "UPDATE SinhVien SET FullName = ?, Status = ?, Address = ?, Gender = ? WHERE UserID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fullName);
            ps.setBoolean(2, status == 1);
            ps.setString(3, address);
            ps.setBoolean(4, gender == 1);
            ps.setInt(5, UserID);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int delete(int id) {
        String sqlDeleteSinhVien = "DELETE FROM SinhVien WHERE UserID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement psDelete = con.prepareStatement(sqlDeleteSinhVien)) {

            psDelete.setInt(1, id);

            int affectedRows = psDelete.executeUpdate();
            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
