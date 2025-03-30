/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class HocVien {

    private int SinhVienID;
    private String FullName;
    private int UserID;
    private int Status;
    private String Address;
    private int Gender;
    private String Phone;

    public HocVien(String Address, String FullName, int Gender, int SinhVienID, int Status, int UserID, String Email, String Phone) {
        this.Address = Address;
        this.FullName = FullName;
        this.Gender = Gender;
        this.SinhVienID = SinhVienID;
        this.Status = Status;
        this.UserID = UserID;
        this.Phone = Phone;
    }

    public int getSinhVienID() {
        return SinhVienID;
    }

    public String getFullName() {
        return FullName;
    }

    public int getUserID() {
        return UserID;
    }

    public int getStatus() {
        return Status;
    }

    public String getAddress() {
        return Address;
    }

    public int getGender() {
        return Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    public void setSinhVienID(int SinhVienID) {
        this.SinhVienID = SinhVienID;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public HocVien() {
    }
    
}
