package db;

public class chuot {

    private int ma;
    private String ten;
    private boolean thuNghiem;

    public chuot(int ma, String ten, boolean thuNghiem) {
        this.ma = ma;
        this.ten = ten;
        this.thuNghiem = thuNghiem;
    }

    public chuot() {
    }

    public int getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public boolean isThuNghiem() {
        return thuNghiem;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setThuNghiem(boolean thuNghiem) {
        this.thuNghiem = thuNghiem;
    }
}
