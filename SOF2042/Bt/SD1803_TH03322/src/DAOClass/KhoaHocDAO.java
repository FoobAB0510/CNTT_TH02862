/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

import DAOClass.ModelsClass.KhoaHoc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 *
 * @author ADMIN
 */
public class KhoaHocDAO {

   public boolean themKhoaHoc(KhoaHoc kh) {
        String query = "INSERT INTO KhoaHoc (MaKhoaHoc, TenKhoaHoc, MoTa, imgURL, ThoiLuong, HocPhi, TrangThai, MaChuyenDe) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(kh.getIdKhoaHoc().toString());
        params.add(kh.getTenKhoaHoc());
        params.add(kh.getMoTa());
        params.add(kh.getImgURL());
        params.add(kh.getThoiLuong());
        params.add(kh.getHocPhi());
        params.add(kh.getTrangThai());
        params.add(kh.getIdChuyenDe() != null ? kh.getIdChuyenDe().toString() : null);
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public boolean suaKhoaHoc(KhoaHoc kh) {
        String query = "UPDATE KhoaHoc SET TenKhoaHoc = ?, MoTa = ?, imgURL = ?, ThoiLuong = ?, HocPhi = ?, TrangThai = ?, MaChuyenDe = ? WHERE MaKhoaHoc = ?";
        List<Object> params = new ArrayList<>();
        params.add(kh.getTenKhoaHoc());
        params.add(kh.getMoTa());
        params.add(kh.getImgURL());
        params.add(kh.getThoiLuong());
        params.add(kh.getHocPhi());
        params.add(kh.getTrangThai());
        params.add(kh.getIdChuyenDe() != null ? kh.getIdChuyenDe().toString() : null);
        params.add(kh.getIdKhoaHoc().toString());
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public boolean xoaKhoaHoc(UUID id) {
        String query = "DELETE FROM KhoaHoc WHERE MaKhoaHoc = ?";
        List<Object> list = new ArrayList<>();
        list.add(id.toString());
        return (boolean) CRUD_DAO.executeQuery(query, list);
    }

    public List<KhoaHoc> hienThi() {
        List<KhoaHoc> list = new ArrayList<>();
        String query = "SELECT * FROM KhoaHoc";
        List<Object> listKH = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(query, listKH);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setIdKhoaHoc(UUID.fromString(rs.getString(1)));
                kh.setTenKhoaHoc(rs.getString(2));
                kh.setMoTa(rs.getString(3));
                kh.setImgURL(rs.getString(4));
                kh.setThoiLuong(rs.getInt(5));
                kh.setHocPhi(rs.getDouble(6));
                kh.setTrangThai(rs.getInt(7));
                
                String idChuyenDeStr = rs.getString(8);
                kh.setIdChuyenDe(idChuyenDeStr != null ? UUID.fromString(idChuyenDeStr) : null);
                
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        public static List<KhoaHoc> hienThiTiep() {
        List<KhoaHoc> list = new ArrayList<>();
        String query = "SELECT * FROM KhoaHoc";
        List<Object> listKH = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(query, listKH);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setIdKhoaHoc(UUID.fromString(rs.getString(1)));
                kh.setTenKhoaHoc(rs.getString(2));
                kh.setMoTa(rs.getString(3));
                kh.setImgURL(rs.getString(4));
                kh.setThoiLuong(rs.getInt(5));
                kh.setHocPhi(rs.getDouble(6));
                kh.setTrangThai(rs.getInt(7));
                
                String idChuyenDeStr = rs.getString(8);
                kh.setIdChuyenDe(idChuyenDeStr != null ? UUID.fromString(idChuyenDeStr) : null);
                
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
