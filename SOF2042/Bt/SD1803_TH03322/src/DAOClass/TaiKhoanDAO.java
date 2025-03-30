/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;
import DAOClass.ModelsClass.TaiKhoan;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author ADMIN
 */
public class TaiKhoanDAO {
    public boolean themTaiKhoan(TaiKhoan tk) {
        String query = "INSERT INTO TaiKhoanNguoiDung (IDDangNhap, MatKhau, Email) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(tk.getId().toString());
        params.add(tk.getMk());
        params.add(tk.getEmail());
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public boolean suaTaiKhoan(TaiKhoan tk) {
        String query = "UPDATE TaiKhoanNguoiDung SET MatKhau = ?, Email = ? WHERE IDDangNhap = ?";
        List<Object> params = new ArrayList<>();
        params.add(tk.getMk());
        params.add(tk.getEmail());
        params.add(tk.getId().toString());
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public boolean xoaTaiKhoan(UUID id) {
        String query = "DELETE FROM TaiKhoanNguoiDung WHERE IDDangNhap = ?";
        List<Object> params = new ArrayList<>();
        params.add(id.toString());
        return (boolean) CRUD_DAO.executeQuery(query, params);
    }

    public List<TaiKhoan> hienThi() {
        List<TaiKhoan> list = new ArrayList<>();
        String query = "SELECT * FROM TaiKhoanNguoiDung";
        List<Object> params = new ArrayList<>();
        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(query, params);
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setId(UUID.fromString(rs.getString(1)));
                tk.setMk(rs.getString(2));
                tk.setEmail(rs.getString(3));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
