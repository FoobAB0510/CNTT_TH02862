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
public class LopHoc {
    UUID id; 
    String ten;
    String tgBatDau;
    String tgKetThuc;
    UUID idKhoaHoc;
    UUID idGiangVien;
    int trangthai;

    public LopHoc() {
    }

    public LopHoc(UUID id, String ten, String tgBatDau, String tgKetThuc, UUID idKhoaHoc, UUID idGiangVien, int trangthai) {
        this.id = id;
        this.ten = ten;
        this.tgBatDau = tgBatDau;
        this.tgKetThuc = tgKetThuc;
        this.idKhoaHoc = idKhoaHoc;
        this.idGiangVien = idGiangVien;
        this.trangthai = trangthai;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTgBatDau() {
        return tgBatDau;
    }

    public void setTgBatDau(String tgBatDau) {
        this.tgBatDau = tgBatDau;
    }

    public String getTgKetThuc() {
        return tgKetThuc;
    }

    public void setTgKetThuc(String tgKetThuc) {
        this.tgKetThuc = tgKetThuc;
    }

    public UUID getIdKhoaHoc() {
        return idKhoaHoc;
    }

    public void setIdKhoaHoc(UUID idKhoaHoc) {
        this.idKhoaHoc = idKhoaHoc;
    }

    public UUID getIdGiangVien() {
        return idGiangVien;
    }

    public void setIdGiangVien(UUID idGiangVien) {
        this.idGiangVien = idGiangVien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
    
    
   
}
