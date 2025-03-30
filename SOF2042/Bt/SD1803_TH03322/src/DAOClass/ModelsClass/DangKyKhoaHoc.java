/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass.ModelsClass;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class DangKyKhoaHoc {

    UUID maDangKyKH;
    Date ngayDangKy;
    int trangThai;
    double hocPhi;
    float diem;
    int isChuyenDe;
    UUID maHocVien;
    UUID maLopHoc;
    UUID maKhoaHoc;

    public DangKyKhoaHoc() {
    }

    public DangKyKhoaHoc(UUID maDangKyKH, Date ngayDangKy, int trangThai, double hocPhi, float diem, int isChuyenDe, UUID maHocVien, UUID maLopHoc, UUID maKhoaHoc) {
        this.maDangKyKH = maDangKyKH;
        this.ngayDangKy = ngayDangKy;
        this.trangThai = trangThai;
        this.hocPhi = hocPhi;
        this.diem = diem;
        this.isChuyenDe = isChuyenDe;
        this.maHocVien = maHocVien;
        this.maLopHoc = maLopHoc;
        this.maKhoaHoc = maKhoaHoc;
    }

    public UUID getMaDangKyKH() {
        return maDangKyKH;
    }

    public void setMaDangKyKH(UUID maDangKyKH) {
        this.maDangKyKH = maDangKyKH;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public int getIsChuyenDe() {
        return isChuyenDe;
    }

    public void setIsChuyenDe(int isChuyenDe) {
        this.isChuyenDe = isChuyenDe;
    }

    public UUID getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(UUID maHocVien) {
        this.maHocVien = maHocVien;
    }

    public UUID getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(UUID maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    public UUID getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(UUID maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }
    
    
}
