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
public class ChuyenDe {

    UUID id;
    String name;
    String mota;
    int hocphi;
    String imgURL;
    int trangthai;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getHocphi() {
        return hocphi;
    }

    public void setHocphi(int hocphi) {
        this.hocphi = hocphi;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public ChuyenDe(UUID id, String name, String mota, int hocphi, String imgURL, int trangthai) {
        this.id = id;
        this.name = name;
        this.mota = mota;
        this.hocphi = hocphi;
        this.imgURL = imgURL;
        this.trangthai = trangthai;
    }
    
    

    public ChuyenDe() {
    }

}
