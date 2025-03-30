/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass.ModelsClass;

import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class KhoaHoc {
    UUID idKhoaHoc;
    String tenKhoaHoc;
    String moTa;
    String imgURL;
    int thoiLuong;
    double hocPhi;
    int trangThai;
    UUID idChuyenDe;

    public KhoaHoc() {
    }

    public KhoaHoc(UUID idKhoaHoc, String tenKhoaHoc, String moTa, String imgURL, int thoiLuong, double hocPhi, int trangThai, UUID idChuyenDe) {
        this.idKhoaHoc = idKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.moTa = moTa;
        this.imgURL = imgURL;
        this.thoiLuong = thoiLuong;
        this.hocPhi = hocPhi;
        this.trangThai = trangThai;
        this.idChuyenDe = idChuyenDe;
    }

    public UUID getIdKhoaHoc() {
        return idKhoaHoc;
    }

    public void setIdKhoaHoc(UUID idKhoaHoc) {
        this.idKhoaHoc = idKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public UUID getIdChuyenDe() {
        return idChuyenDe;
    }

    public void setIdChuyenDe(UUID idChuyenDe) {
        this.idChuyenDe = idChuyenDe;
    }
    
    
}
