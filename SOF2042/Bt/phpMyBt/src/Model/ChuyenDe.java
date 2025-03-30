/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class ChuyenDe {
    String IDChuyenDe;
    String TenChuyenDe;
    Float HocPhi;
    String MoTa;
    String IMG;

    public ChuyenDe(String IDChuyenDe, String TenChuyenDe, Float HocPhi, String MoTa, String IMG) {
        this.IDChuyenDe = IDChuyenDe;
        this.TenChuyenDe = TenChuyenDe;
        this.HocPhi = HocPhi;
        this.MoTa = MoTa;
        this.IMG = IMG;
    }

    public String getIDChuyenDe() {
        return IDChuyenDe;
    }

    public void setIDChuyenDe(String IDChuyenDe) {
        this.IDChuyenDe = IDChuyenDe;
    }

    public String getTenChuyenDe() {
        return TenChuyenDe;
    }

    public void setTenChuyenDe(String TenChuyenDe) {
        this.TenChuyenDe = TenChuyenDe;
    }

    public Float getHocPhi() {
        return HocPhi;
    }

    public void setHocPhi(Float HocPhi) {
        this.HocPhi = HocPhi;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public ChuyenDe() {
    }
    
}
