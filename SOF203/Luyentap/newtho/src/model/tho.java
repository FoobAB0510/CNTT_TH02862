/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;

/**
 *
 * @author Administrator
 */
public class tho {
    private int id;
    private String ma_danh_muc;
    private String ten_danh_muc;
    private String trang_thai;
    private Timestamp ngay_tao;
    private Timestamp ngay_sua;

    public tho() {
    }

    public tho(int id, String ma_danh_muc, String ten_danh_muc, String trang_thai, Timestamp ngay_tao, Timestamp ngay_sua) {
        this.id = id;
        this.ma_danh_muc = ma_danh_muc;
        this.ten_danh_muc = ten_danh_muc;
        this.trang_thai = trang_thai;
        this.ngay_tao = ngay_tao;
        this.ngay_sua = ngay_sua;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMa_danh_muc(String ma_danh_muc) {
        this.ma_danh_muc = ma_danh_muc;
    }

    public void setTen_danh_muc(String ten_danh_muc) {
        this.ten_danh_muc = ten_danh_muc;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public void setNgay_tao(Timestamp ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public void setNgay_sua(Timestamp ngay_sua) {
        this.ngay_sua = ngay_sua;
    }

    public int getId() {
        return id;
    }

    public String getMa_danh_muc() {
        return ma_danh_muc;
    }

    public String getTen_danh_muc() {
        return ten_danh_muc;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public Timestamp getNgay_tao() {
        return ngay_tao;
    }

    public Timestamp getNgay_sua() {
        return ngay_sua;
    }
}
