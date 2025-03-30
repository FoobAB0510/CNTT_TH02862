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
public class GiangVien {
    UUID id;
    String tenGiangVien;
    String chuyenMon;
    String email;
    String soDienThoai;
    int trangThai;

    public GiangVien() {
    }

    public GiangVien(UUID id, String tenGiangVien, String chuyenMon, String email, String soDienThoai, int trangThai) {
        this.id = id;
        this.tenGiangVien = tenGiangVien;
        this.chuyenMon = chuyenMon;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.trangThai = trangThai;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public String getChuyenMon() {
        return chuyenMon;
    }

    public void setChuyenMon(String chuyenMon) {
        this.chuyenMon = chuyenMon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
}
