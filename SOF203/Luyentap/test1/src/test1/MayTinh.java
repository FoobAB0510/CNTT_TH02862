/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

/**
 *
 * @author Administrator
 */
public class MayTinh {
    private int Ma;
    private String ten, trangThai, hang;
    private double giaBan;

    public MayTinh() {
    }

    public MayTinh(int Ma, String ten, String trangThai, String hang, double giaBan) {
        this.Ma = Ma;
        this.ten = ten;
        this.trangThai = trangThai;
        this.hang = hang;
        this.giaBan = giaBan;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getHang() {
        return hang;
    }

    public MayTinh(int Ma, String ten, String trangThai, double giaBan) {
        this.Ma = Ma;
        this.ten = ten;
        this.trangThai = trangThai;
        this.giaBan = giaBan;
    }

    public int getMa() {
        return Ma;
    }

    public String getTen() {
        return ten;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setMa(int Ma) {
        this.Ma = Ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
}
