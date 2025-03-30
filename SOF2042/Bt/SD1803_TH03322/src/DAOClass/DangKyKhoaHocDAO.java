/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class DangKyKhoaHocDAO {
        public static boolean dangKy(String idHocVien, String idKhoaHoc, String idLopHoc) {
        String sql = "INSERT INTO DangKyKhoaHoc (MaDangKyKH, MaHocVien, MaLopHoc, MaKhoaHoc, NgayDangKy, HocPhi, Diem, TrangThai) VALUES (?, ?, ?, ?, GETDATE(), 0, 0, 1)";
        List<Object> stmt = new ArrayList<>();
        stmt.add(UUID.randomUUID().toString());
        stmt.add(idHocVien);
        stmt.add(idLopHoc);
        stmt.add(idKhoaHoc);
        return (boolean) CRUD_DAO.executeQuery(sql, stmt);
    }
        
}
