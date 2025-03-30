package Model;

import java.util.Date;

public class DkyKhoaHoc {
    private int DKID, ClassID, CID, SinhVienID, inChuyende, Status;
    private Float Price;
    private Date RegDate;

    public DkyKhoaHoc(int ClassID, int CID, int DKID, int SinhVienID, int Status, Float Price, Date RegDate) {
        this.ClassID = ClassID;
        this.CID = CID;
        this.DKID = DKID;
        this.SinhVienID = SinhVienID;
        this.Status = Status;
        this.Price = Price;
        this.RegDate = RegDate;
    }

    public DkyKhoaHoc() {   
    }

    public int getDKID() {
        return DKID;
    }

    public int getClassID() {
        return ClassID;
    }

    public int getCID() {
        return CID;
    }

    public int getSinhVienID() {
        return SinhVienID;
    }

    public int getInChuyende() {
        return inChuyende;
    }

    public int getStatus() {
        return Status;
    }

    public Float getPrice() {
        return Price;
    }

    public Date getRegDate() {
        return RegDate;
    }

    public void setDKID(int DKID) {
        this.DKID = DKID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public void setSinhVienID(int SinhVienID) {
        this.SinhVienID = SinhVienID;
    }

    public void setInChuyende(int inChuyende) {
        this.inChuyende = inChuyende;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setPrice(Float Price) {
        this.Price = Price;
    }

    public void setRegDate(Date RegDate) {
        this.RegDate = RegDate;
    }


}
