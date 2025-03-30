/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;
import DAOClass.ModelsClass.ChuyenDe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author ADMIN
 */
public class ChuyenDeDao {
    
    public boolean themCD(ChuyenDe cd) {
        String query = "INSERT INTO Chuyende (MaChuyenDe, TenChuyenDe, MoTa, HocPhi, imgURL, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(cd.getId().toString());
        params.add(cd.getName());
        params.add(cd.getMota());
        params.add(cd.getHocphi());
        params.add(cd.getImgURL());
        params.add(cd.getTrangthai());
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }


    public boolean suaCD(ChuyenDe cd) {
        String query = "UPDATE Chuyende SET TenChuyenDe = ?, MoTa = ?, HocPhi = ?, imgURL = ?, TrangThai = ? WHERE MaChuyenDe = ?";
        List<Object> params = new ArrayList<>();
        params.add(cd.getName());
        params.add(cd.getMota());
        params.add(cd.getHocphi());
        params.add(cd.getImgURL());
        params.add(cd.getTrangthai());
        params.add(cd.getId().toString());
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public boolean xoaCD(UUID id) {
        String query = "DELETE FROM Chuyende WHERE MaChuyenDe = ?";
        List<Object> list = new ArrayList<>();
        list.add(id.toString());
        return (boolean) CRUD_DAO.executeQuery(query, list);
    }


    public List<ChuyenDe> hienThi() {
        List<ChuyenDe> list = new ArrayList<>();
        String query = "SELECT * FROM Chuyende";
        List<Object> listCD = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(query, listCD);
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe();
                cd.setId(UUID.fromString(rs.getString(1)));
                cd.setName(rs.getString(2));
                cd.setMota(rs.getString(3));
                cd.setHocphi(rs.getInt(4));
                cd.setImgURL(rs.getString(5));
                cd.setTrangthai(rs.getInt(6));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
