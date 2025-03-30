package Model;

import java.util.Date;

public class TonghophaiOptionpls {
    private int DKID, CategoryID, SinhVienID, CID;
    private Float HocPhi;
    private Date NgayDK;
    private String Status;

    public TonghophaiOptionpls(int CID, int CategoryID, int DKID, Float HocPhi, Date NgayDK, int SinhVienID, String Status) {
        this.CID = CID;
        this.CategoryID = CategoryID;
        this.DKID = DKID;
        this.HocPhi = HocPhi;
        this.NgayDK = NgayDK;
        this.SinhVienID = SinhVienID;
        this.Status = Status;
    }

    public TonghophaiOptionpls() {
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

    public int getCID() {
        return CID;
    }

    public Float getHocPhi() {
        return HocPhi;
    }

    public Date getNgayDK() {
        return NgayDK;
    }

    public String getStatus() {
        return Status;
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

    public void setCID(int CID) {
        this.CID = CID;
    }

    public void setHocPhi(Float HocPhi) {
        this.HocPhi = HocPhi;
    }

    public void setNgayDK(Date NgayDK) {
        this.NgayDK = NgayDK;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
