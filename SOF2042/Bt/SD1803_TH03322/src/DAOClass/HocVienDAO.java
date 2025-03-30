/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;
import DAOClass.ModelsClass.HocVien;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ADMIN
 */
public class HocVienDAO {
    public boolean themHocVien(HocVien hv) {
        String query = "INSERT INTO HocVien (MaHocVien, HoTen, NgaySinh, Email, SoDienThoai, GioiTinh, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        List<Object> params = Arrays.asList(
            hv.getId().toString(),
            hv.getHoTen(),
            hv.getNgaySinh(),
            hv.getEmail(),
            hv.getSoDienThoai(),
            hv.getGioiTinh(),
            hv.getTrangThai()
        );
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public boolean suaHocVien(HocVien hv) {
        String query = "UPDATE HocVien SET HoTen = ?, NgaySinh = ?, Email = ?, SoDienThoai = ?, GioiTinh = ?, TrangThai = ? WHERE MaHocVien = ?";
        List<Object> params = Arrays.asList(
            hv.getHoTen(),
            hv.getNgaySinh(),
            hv.getEmail(),
            hv.getSoDienThoai(),
            hv.getGioiTinh(),
            hv.getTrangThai(),
            hv.getId().toString()
        );
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public boolean xoaHocVien(UUID id) {
        String query = "DELETE FROM HocVien WHERE MaHocVien = ?";
        List<Object> params = Collections.singletonList(id.toString());
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public List<HocVien> hienThi() {
        List<HocVien> list = new ArrayList<>();
        String query = "SELECT * FROM HocVien";
        List<Object> params = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(query, params);
            while (rs.next()) {
                HocVien hv = new HocVien(
                    UUID.fromString(rs.getString("MaHocVien")),
                    rs.getString("HoTen"),
                    rs.getString("NgaySinh"),
                    rs.getString("Email"),
                    rs.getString("SoDienThoai"),
                    rs.getString("GioiTinh"),
                    rs.getInt("TrangThai")
                );
                list.add(hv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
