package Model;

import java.util.Date;

public class DkyChuyenDe {
    private int DKID, CategoryID, SinhVienID;
    private String HocPhi;
    private Date NgayDK;

    public DkyChuyenDe(int CategoryID, int DKID, String HocPhi, Date NgayDK, int SinhVienID) {
        this.CategoryID = CategoryID;
        this.DKID = DKID;
        this.HocPhi = HocPhi;
        this.NgayDK = NgayDK;
        this.SinhVienID = SinhVienID;
    }

    public DkyChuyenDe() {
    }

    public int getDKID() {
        return DKID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public int getSinhVienID() {
        return SinhVienID;
    }

    public String getHocPhi() {
        return HocPhi;
    }

    public Date getNgayDK() {
        return NgayDK;
    }

    public void setDKID(int DKID) {
        this.DKID = DKID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public void setSinhVienID(int SinhVienID) {
        this.SinhVienID = SinhVienID;
    }

    public void setHocPhi(String HocPhi) {
        this.HocPhi = HocPhi;
    }

    public void setNgayDK(Date NgayDK) {
        this.NgayDK = NgayDK;
    }
}
