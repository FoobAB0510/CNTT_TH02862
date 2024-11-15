/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ktra1;

import java.security.Timestamp;

/**
 *
 * @author Administrator
 */
public class SanPham {
    private int id;
    private String ma_SP;
    private String ten_SP;
    private String trang_thai;
    private String danhMuc;

    public SanPham() {
    }

    public SanPham(int id, String ma_SP, String ten_SP, String trang_thai, String danhMuc) {
        this.id = id;
        this.ma_SP = ma_SP;
        this.ten_SP = ten_SP;
        this.trang_thai = trang_thai;
        this.danhMuc = danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public int getId() {
        return id;
    }

    public String getMa_SP() {
        return ma_SP;
    }

    public String getTen_SP() {
        return ten_SP;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMa_SP(String ma_SP) {
        this.ma_SP = ma_SP;
    }

    public void setTen_SP(String ten_SP) {
        this.ten_SP = ten_SP;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
    
}
