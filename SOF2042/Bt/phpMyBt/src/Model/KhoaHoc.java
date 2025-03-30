/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class KhoaHoc {
    String IDKhoaHoc;
    String TenKhoaHoc;
    String IDChuyenDe;
    String ThoiLuong;
    Integer HocPhi;
    String IMG;

    public KhoaHoc(String IDKhoaHoc, String TenKhoaHoc, String IDChuyenDe, String ThoiLuong, Integer HocPhi, String IMG) {
        this.IDKhoaHoc = IDKhoaHoc;
        this.TenKhoaHoc = TenKhoaHoc;
        this.IDChuyenDe = IDChuyenDe;
        this.ThoiLuong = ThoiLuong;
        this.HocPhi = HocPhi;
        this.IMG = IMG;
    }

    public String getIDKhoaHoc() {
        return IDKhoaHoc;
    }

    public void setIDKhoaHoc(String IDKhoaHoc) {
        this.IDKhoaHoc = IDKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String TenKhoaHoc) {
        this.TenKhoaHoc = TenKhoaHoc;
    }

    public String getIDChuyenDe() {
        return IDChuyenDe;
    }

    public void setIDChuyenDe(String IDChuyenDe) {
        this.IDChuyenDe = IDChuyenDe;
    }

    public String getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(String ThoiLuong) {
        this.ThoiLuong = ThoiLuong;
    }

    public Integer getHocPhi() {
        return HocPhi;
    }

    public void setHocPhi(Integer HocPhi) {
        this.HocPhi = HocPhi;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public KhoaHoc() {
    }
}
