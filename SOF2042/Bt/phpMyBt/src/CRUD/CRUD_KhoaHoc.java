/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.*;
import java.util.ArrayList;

import Model.KhoaHoc;

public class CRUD_KhoaHoc {

    public static KhoaHoc getByID(String id) {
        ArrayList<KhoaHoc> ls = get();
        for(KhoaHoc cd : ls) {
            if(cd.getIDChuyenDe().equals(id)) {
                return cd;
            }
        }
        return null;
    }

    public static ArrayList<KhoaHoc> get() {
        ArrayList<KhoaHoc> khoahoc = new ArrayList<KhoaHoc>();
        try {
            Statement db = CRUD_DAO.getConnect().createStatement();
            String sql = "SELECT * FROM KhoaHoc";

            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc(
                    String.valueOf(rs.getInt("CID")),
                    rs.getString("Name"),
                    String.valueOf(rs.getInt("CategoryID")),
                    String.valueOf(rs.getInt("Duration")),
                    rs.getInt("Cost"),
                    rs.getString("Image")
                );
                khoahoc.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khoahoc;
    }

    public static int create(String name, String desc, int categoryId, float cost, int duration, String img, int lessonsCount, boolean status) {
        String sql = "INSERT INTO KhoaHoc (CategoryID, Name, Description, Cost, Duration, Image, LessonsCount, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = CRUD_DAO.getConnect(); 
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, categoryId);
            ps.setString(2, name);
            ps.setString(3, desc);
            ps.setFloat(4, cost);
            ps.setInt(5, duration);
            ps.setString(6, img);
            ps.setInt(7, lessonsCount);
            ps.setBoolean(8, status);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int update(int CID, String name, String desc, int categoryId, float cost, int duration, String img, int lessonsCount, boolean status) {
        String sql = "UPDATE KhoaHoc SET CategoryID = ?, Name = ?, Description = ?, Cost = ?, Duration = ?, Image = ?, LessonsCount = ?, Status = ? WHERE CID = ?";

        try (Connection con = CRUD_DAO.getConnect(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            ps.setString(2, name);
            ps.setString(3, desc);
            ps.setFloat(4, cost);
            ps.setInt(5, duration);
            ps.setString(6, img);
            ps.setInt(7, lessonsCount);
            ps.setBoolean(8, status);
            ps.setInt(9, CID);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int delete(int CID) {
        String sql = "DELETE FROM KhoaHoc WHERE CID = ?";

        try (Connection con = CRUD_DAO.getConnect(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, CID);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}