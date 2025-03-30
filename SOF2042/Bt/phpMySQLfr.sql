CREATE DATABASE SUGDB
USE SUGDB


CREATE TABLE [User] (
    UserID int PRIMARY KEY,
    email nvarchar(100) NOT NULL,
    password nvarchar(100) NOT NULL,
    role int NOT NULL,
    CreateAt date NOT NULL,
    Status bit NOT NULL
);

CREATE TABLE SinhVien (
    SinhVienID int PRIMARY KEY,
    FullName nvarchar(200) NOT NULL,
    UserID int NOT NULL,
    Status bit NOT NULL,
    Address nvarchar(100) NOT NULL,
    Gender bit NOT NULL,
    CONSTRAINT FK_SinhVien_User FOREIGN KEY (UserID) REFERENCES [User](UserID)
);

CREATE TABLE GiangVien (
    GiangVienID int PRIMARY KEY,
    FullName nvarchar(200) NOT NULL,
    UserID int NOT NULL,
    Status bit NOT NULL,
    Address nvarchar(100) NOT NULL,
    Gender bit NOT NULL,
    CONSTRAINT FK_GiangVien_User FOREIGN KEY (UserID) REFERENCES [User](UserID)
);

CREATE TABLE ChuyenDe (
    CategoryID int PRIMARY KEY,
    Name nvarchar(100) NOT NULL,
    Description nvarchar(200) NOT NULL,
    HocPhi float NOT NULL
);

CREATE TABLE KhoaHoc (
    CID int PRIMARY KEY,
    CategoryID int NOT NULL,
    Name nvarchar(100) NOT NULL,
    Description nvarchar(200) NOT NULL,
    Cost float NOT NULL,
    Duration int NOT NULL,
    Image nvarchar(500) NOT NULL,
    LessonsCount int NOT NULL,
    Status bit NOT NULL,
    CONSTRAINT FK_KhoaHoc_ChuyenDe FOREIGN KEY (CategoryID) REFERENCES ChuyenDe(CategoryID)
);

CREATE TABLE Class (
    ClassID int PRIMARY KEY,
    CID int NOT NULL,
    Name nvarchar(100) NOT NULL,
    StartDate datetime NOT NULL,
    EndDate datetime NOT NULL,
    Student int NOT NULL,
    Status bit NOT NULL,
    CONSTRAINT FK_Class_KhoaHoc FOREIGN KEY (CID) REFERENCES KhoaHoc(CID)
);

CREATE TABLE KhoaHocGiangVien (
    TID int PRIMARY KEY,
    CID int NOT NULL,
    GiangVienID int NOT NULL,
    Status bit NOT NULL,
    CONSTRAINT FK_KhoaHocGiangVien_KhoaHoc FOREIGN KEY (CID) REFERENCES KhoaHoc(CID),
    CONSTRAINT FK_KhoaHocGiangVien_GiangVien FOREIGN KEY (GiangVienID) REFERENCES GiangVien(GiangVienID)
);

CREATE TABLE DangKyKhoaHoc (
    DKID int PRIMARY KEY,
    ClassID int NULL,
    CID int NOT NULL,
    SinhVienID int NOT NULL,
    Price float NOT NULL,
    inChuyende bit NOT NULL,
    RegDate date NOT NULL,
    Status bit NOT NULL,
    CONSTRAINT FK_DangKyKhoaHoc_Class FOREIGN KEY (ClassID) REFERENCES Class(ClassID),
    CONSTRAINT FK_DangKyKhoaHoc_KhoaHoc FOREIGN KEY (CID) REFERENCES KhoaHoc(CID),
    CONSTRAINT FK_DangKyKhoaHoc_SinhVien FOREIGN KEY (SinhVienID) REFERENCES SinhVien(SinhVienID)
);

CREATE TABLE DangKyChuyenDe (
    DKID int PRIMARY KEY,
    CategoryID int NOT NULL,
    SinhVienID int NOT NULL,
    HocPhi float NOT NULL,
    NgayDK date NOT NULL,
    Status bit NOT NULL,
    Description nvarchar(200) NOT NULL,
    CONSTRAINT FK_DangKyChuyenDe_ChuyenDe FOREIGN KEY (CategoryID) REFERENCES ChuyenDe(CategoryID),
    CONSTRAINT FK_DangKyChuyenDe_SinhVien FOREIGN KEY (SinhVienID) REFERENCES SinhVien(SinhVienID)
);

INSERT INTO [User] (UserID, email, password, role, CreateAt, Status) VALUES
(1, 'sinhvien1@example.com', 'pass1', 1, '2025-01-01', 1),
(2, 'giangvien1@example.com', 'pass2', 2, '2025-01-02', 1),
(3, 'sinhvien2@example.com', 'pass3', 1, '2025-01-03', 1);

INSERT INTO SinhVien (SinhVienID, FullName, UserID, Status, Address, Gender) VALUES
(1, 'Nguyen Van A', 1, 1, '123 Le Loi, HN', 1),
(2, 'Tran Thi B', 3, 1, '456 Tran Hung Dao, HN', 0);

INSERT INTO GiangVien (GiangVienID, FullName, UserID, Status, Address, Gender) VALUES
(1, 'Pham Van D', 2, 1, '10 Nguyen Trai, HN', 1);

INSERT INTO ChuyenDe (CategoryID, Name, Description, HocPhi) VALUES
(1, 'Chuyen de Lap trinh', 'Cac kien thuc lap trinh co ban', 1500);

INSERT INTO KhoaHoc (CID, CategoryID, Name, Description, Cost, Duration, Image, LessonsCount, Status) VALUES
(1, 1, 'Khoa hoc Lap trinh C#', 'Lap trinh C# co ban', 3000, 30, 'image1.jpg', 20, 1);

INSERT INTO Class (ClassID, CID, Name, StartDate, EndDate, Student, Status) VALUES
(1, 1, 'Lop C# - Sang', '2025-02-01 08:00:00', '2025-02-01 12:00:00', 25, 1);

INSERT INTO KhoaHocGiangVien (TID, CID, GiangVienID, Status) VALUES
(1, 1, 1, 1);

INSERT INTO DangKyKhoaHoc (DKID, ClassID, CID, SinhVienID, Price, inChuyende, RegDate, Status) VALUES
(1, 1, 1, 1, 3000, 0, '2025-01-15', 1);

INSERT INTO DangKyChuyenDe (DKID, CategoryID, SinhVienID, HocPhi, NgayDK, Status, Description) VALUES
(1, 1, 1, 1500, '2025-01-18', 1, 'Dang ky chuyen de lap trinh');
