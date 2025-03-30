/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

import DAOClass.ModelsClass.LopHoc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class LopHocDAO {

       public static boolean themLop(LopHoc lh) {
        String sql = "INSERT INTO LopHoc (id, name, startDate, endDate, idKhoahoc, idGiangVien, trangthai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        List<Object> stmt = new ArrayList<>();
        stmt.add(lh.getId().toString());
        stmt.add(lh.getTen());
        stmt.add(lh.getTgBatDau());
        stmt.add(lh.getTgKetThuc());
        stmt.add(lh.getIdKhoaHoc().toString());
        stmt.add(lh.getIdGiangVien().toString());
        stmt.add(lh.getTrangthai());
        return (boolean) CRUD_DAO.executeQuery(sql, stmt);
    }

    public static List<LopHoc> hienThi() {
        List<LopHoc> list = new ArrayList<>();
        String query = "SELECT * FROM LopHoc";
        List<Object> params = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(query, params);
            while (rs.next()) {
                LopHoc lh = new LopHoc();
                lh.setId(UUID.fromString(rs.getString(1)));
                lh.setTen(rs.getString(2));
                lh.setTgBatDau(rs.getString(3));
                lh.setTgKetThuc(rs.getString(4));
                lh.setIdKhoaHoc(UUID.fromString(rs.getString(5)));
                lh.setIdGiangVien(UUID.fromString(rs.getString(6)));
                lh.setTrangthai(rs.getInt(7));
                list.add(lh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean suaLop(LopHoc lh) {
        String sql = "UPDATE LopHoc SET name = ?, startDate = ?, endDate = ?, idKhoahoc = ?, idGiangVien = ?, trangthai = ? WHERE id = ?";
        List<Object> stmt = new ArrayList<>();
        stmt.add(lh.getTen());  
        stmt.add(lh.getTgBatDau());
        stmt.add(lh.getTgKetThuc());
        stmt.add(lh.getIdKhoaHoc().toString());
        stmt.add(lh.getIdGiangVien().toString());
        stmt.add(lh.getTrangthai());
        stmt.add(lh.getId().toString());  
        return (boolean) CRUD_DAO.executeQuery(sql, stmt);
    }

    public static boolean xoa(UUID id) {
        String query = "DELETE FROM LopHoc WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(id.toString());
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public static List<LopHoc> hienThiDK(String idKhoaHoc) {
        List<LopHoc> list = new ArrayList<>();
        String query = "SELECT * FROM LopHoc WHERE idKhoahoc = ?";
        List<Object> params = new ArrayList<>();
        params.add(idKhoaHoc);

        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(query, params);
            while (rs.next()) {
                LopHoc lh = new LopHoc();
                lh.setId(UUID.fromString(rs.getString(1)));
                lh.setTen(rs.getString(2));
                lh.setTgBatDau(rs.getString(3));
                lh.setTgKetThuc(rs.getString(4));
                lh.setIdKhoaHoc(UUID.fromString(rs.getString(5)));
                lh.setIdGiangVien(UUID.fromString(rs.getString(6)));
                lh.setTrangthai(rs.getInt(7));
                list.add(lh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
