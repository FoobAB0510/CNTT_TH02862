/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CRUD;

import Model.ChuyenDe;
import java.sql.*;
import java.util.ArrayList;

public class CRUD_ChuyenDe {
    
    public static ChuyenDe getByList(String id) {
        ArrayList<ChuyenDe> ls = get();
        for(ChuyenDe cd : ls) {
            if(cd.getIDChuyenDe().equals(id)) {
                return cd;
            }
        }
        return null;
    }

    public static ArrayList<ChuyenDe> get() {
        ArrayList<ChuyenDe> chuyende = new ArrayList<ChuyenDe>();
        try {
            Statement db = CRUD_DAO.getConnect().createStatement();
            String sql = "SELECT * FROM ChuyenDe";

            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe();
                cd.setIDChuyenDe(String.valueOf(rs.getInt("CategoryID")));
                cd.setTenChuyenDe(rs.getString("Name"));
                cd.setMoTa(rs.getString("Description"));
                cd.setHocPhi(rs.getFloat("HocPhi"));
                cd.setIMG(rs.getString("Image"));
                chuyende.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chuyende;
    }

    public static int create(String name, String desc, String img, int hocPhi) {
        String chuyendeNow = "INSERT INTO ChuyenDe (Name, Description, Image, HocPhi) VALUES (?, ?, ?, ?)";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement psChuyenDe = con.prepareStatement(chuyendeNow, Statement.RETURN_GENERATED_KEYS)) {

            psChuyenDe.setString(1, name);
            psChuyenDe.setString(2, desc);
            psChuyenDe.setString(3, img);
            psChuyenDe.setInt(4, hocPhi);

            int affectedRows = psChuyenDe.executeUpdate();

            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int update(int CategoryID, String Name, String Description, String Image, float HocPhi) {
        String sql = "UPDATE ChuyenDe SET Name = ?, Description = ?, Image = ?, HocPhi = ? WHERE CategoryID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, Name);
            ps.setString(2, Description);
            ps.setString(3, Image);
            ps.setFloat(4, HocPhi);
            ps.setInt(5, CategoryID);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int delete(int CategoryID) {
        String sql = "DELETE FROM ChuyenDe WHERE CategoryID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, CategoryID);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
