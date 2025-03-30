package CRUD;

import Model.DkyChuyenDe;
import Model.DkyKhoaHoc;
import java.sql.*;

import java.util.ArrayList;

public class CRUD_DangkyChuyenDe {

    public static ArrayList getChuyenDe() {
        ArrayList<DkyChuyenDe> dkyChuyenDe = new ArrayList<DkyChuyenDe>();
        try {
            Statement db = CRUD_DAO.getConnect().createStatement();

            ResultSet rs = db.executeQuery("SELECT * FROM DangKyKhoaHoc");

            while (rs.next()) {
                DkyChuyenDe dky = new DkyChuyenDe();
                dky.setDKID(rs.getInt("DKID"));
                dky.setCategoryID(rs.getInt("CategoryID"));
                dky.setSinhVienID(rs.getInt("SinhVienID"));
                dky.setHocPhi(rs.getString("HocPhi"));
                dky.setNgayDK(rs.getDate("NgayDK"));
                dkyChuyenDe.add(dky);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dkyChuyenDe;
    }

    public static ArrayList getKhoaHoc() {
        ArrayList<DkyKhoaHoc> dkyKhoaHoc = new ArrayList<DkyKhoaHoc>();
        try {
            Statement db = CRUD_DAO.getConnect().createStatement();
            ResultSet rsKH = db.executeQuery("SELECT * FROM DangKyKhoaHoc");

            while (rsKH.next()) {
                DkyKhoaHoc dkyKH = new DkyKhoaHoc();
                dkyKH.setClassID(rsKH.getInt("ClassID"));
                dkyKH.setCID(rsKH.getInt("CID"));
                dkyKH.setDKID(rsKH.getInt("DKID"));
                dkyKH.setSinhVienID(rsKH.getInt("SinhVienID"));
                dkyKH.setStatus(rsKH.getInt("Status"));
                dkyKH.setPrice(rsKH.getFloat("Price"));
                dkyKH.setRegDate(rsKH.getDate("RegDate"));
                dkyKhoaHoc.add(dkyKH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dkyKhoaHoc;
    }

    /**
     * Kiểu dữ liệu đối tượng
     *
     * [DkyChuyenDe] int CategoryID, int DKID, String HocPhi, Date NgayDK, int
     * SinhVienID [DkyKhoaHoc] int ClassID, int CID, int DKID, int SinhVienID,
     * int Status, Float Price, Date RegDate
     *
     * [Chuyende] String IDChuyenDe, String TenChuyenDe, Float HocPhi, String
     * MoTa, String IMG [KhoaHoc] String IDKhoaHoc, String TenKhoaHoc, String
     * IDChuyenDe, String ThoiLuong, Integer HocPhi, String IMG
     *
     *
     * CREATE TABLE DangKyKhoaHoc ( DKID INT PRIMARY KEY IDENTITY(1,1), ClassID
     * INT NULL FOREIGN KEY REFERENCES Class(ClassID), CID INT NOT NULL FOREIGN
     * KEY REFERENCES KhoaHoc(CID), SinhVienID INT NOT NULL FOREIGN KEY
     * REFERENCES SinhVien(SinhVienID), Price FLOAT NOT NULL, inChuyende BIT NOT
     * NULL, RegDate DATE NOT NULL, Status BIT NOT NULL );
     *
     *
     * CREATE TABLE DangKyChuyenDe ( DKID INT PRIMARY KEY IDENTITY(1,1),
     * CategoryID INT NOT NULL FOREIGN KEY REFERENCES ChuyenDe(CategoryID),
     * SinhVienID INT NOT NULL FOREIGN KEY REFERENCES SinhVien(SinhVienID),
     * HocPhi FLOAT NOT NULL, NgayDK DATE NOT NULL, Status BIT NOT NULL,
     * Description NVARCHAR(200) NOT NULL );
     */
    // Create chuyên đề
    public static int createChuyenDe(int categoryID, int sinhVienID, float hocPhi, String description) {
        String sql = "INSERT INTO DangKyChuyenDe (CategoryID, SinhVienID, HocPhi, NgayDK, Status, Description) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, categoryID);
            ps.setInt(2, sinhVienID);
            ps.setFloat(3, hocPhi);
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            ps.setBoolean(5, true); // Mặc định status = true
            ps.setString(6, description);

            return ps.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

// Create khóa học
    public static int createKhoaHoc(int classID, int cid, int sinhVienID, float price) {
        String sql = "INSERT INTO DangKyKhoaHoc (ClassID, CID, SinhVienID, Price, inChuyende, RegDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, classID);
            ps.setInt(2, cid);
            ps.setInt(3, sinhVienID);
            ps.setFloat(4, price);
            ps.setBoolean(5, true); // inChuyende
            ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
            ps.setBoolean(7, true); // Status

            return ps.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

// Edit chuyên đề
    public static int editChuyenDe(int dkID, float hocPhi, boolean status, String description) {
        String sql = "UPDATE DangKyChuyenDe SET HocPhi = ?, Status = ?, Description = ? WHERE DKID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setFloat(1, hocPhi);
            ps.setBoolean(2, status);
            ps.setString(3, description);
            ps.setInt(4, dkID);

            return ps.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

// Edit khóa học
    public static int editKhoaHoc(int dkID, float price, boolean status) {
        String sql = "UPDATE DangKyKhoaHoc SET Price = ?, Status = ? WHERE DKID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setFloat(1, price);
            ps.setBoolean(2, status);
            ps.setInt(3, dkID);

            return ps.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

// Delete chuyên đề
    public static int deleteChuyenDe(int dkID) {
        String sql = "DELETE FROM DangKyChuyenDe WHERE DKID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, dkID);
            return ps.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

// Delete khóa học
    public static int deleteKhoaHoc(int dkID) {
        String sql = "DELETE FROM DangKyKhoaHoc WHERE DKID = ?";

        try (Connection con = CRUD_DAO.getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, dkID);
            return ps.executeUpdate() > 0 ? 1 : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
