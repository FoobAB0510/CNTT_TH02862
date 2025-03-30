package Form;

import CRUD.CRUD_Account;
import CRUD.CRUD_ChuyenDe;
import CRUD.CRUD_DangkyChuyenDe;
import CRUD.CRUD_HocVien;
import CRUD.CRUD_KhoaHoc;
import Model.Account;
import Model.ChuyenDe;
import Model.HocVien;
import Model.KhoaHoc;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DkyNgay extends JDialog {


//     Đăng ký chuyên đề/khóa học:

// - DKID int
// - CategoryID int
// - SinhVienID int
// - HocPhi float
// - NgayDK Date <Today>


// CRUD
// - Create -> Create Dký chuyên đề
// - Review -> View chuyên đề
//   - Jtable = display
// - Update -> Update chuyên đề sinh viên
// - Delete -> Gỡ chuyên đề sinh viên đăng ký


// Jframe
// - Tạo Form để điền thông tin
//   -> Phương pháp fill click:
//     - gồm 3 bảng tổng hợp trong 1 JFrame
// 	+ Sinh viên
//  	+ Khóa học truy vấn ra chuyên đề
	
//     - Xử lý sự kiện onClick => Fill table
//     -> Nhấn đký = ＞︿＜

    private JTable tblHocVien, tblChuyenDe, tblKhoaHoc;
    private JTextField txtHocVien, txtKhoaHoc, txtNgayDK;
    private JButton btnDangKy;
    private final Main parent;
    private DefaultTableModel modelHV, modelCD, modelKH;

    public DkyNgay(Main parent) {
        super(parent, "Đăng ký khóa học/chuyên đề", true);
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setSize(800, 600);

        // Panel chứa các bảng bên phải
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        // Tạo các bảng
        modelHV = new DefaultTableModel(new String[]{"Mã HV", "Họ tên", "Email", "Giới tính"}, 0);
        modelCD = new DefaultTableModel(new String[]{"Mã CD", "Tên CD", "Học phí"}, 0);
        modelKH = new DefaultTableModel(new String[]{"Mã KH", "Tên KH", "Học phí"}, 0);

        tblHocVien = new JTable(modelHV);
        tblChuyenDe = new JTable(modelCD);
        tblKhoaHoc = new JTable(modelKH);

        // Panel form bên trái
        JPanel leftPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        txtHocVien = new JTextField();
        txtKhoaHoc = new JTextField();
        txtNgayDK = new JTextField();
        btnDangKy = new JButton("Đăng ký");

        leftPanel.add(new JLabel("Thông tin HV:"));
        leftPanel.add(txtHocVien);
        leftPanel.add(new JLabel("Thông tin khóa học/chuyên đề:"));
        leftPanel.add(txtKhoaHoc);
        leftPanel.add(new JLabel("Ngày đăng ký:"));
        leftPanel.add(txtNgayDK);
        leftPanel.add(new JLabel(""));
        leftPanel.add(btnDangKy);

        // Thêm các component vào frame
        rightPanel.add(new JScrollPane(tblChuyenDe));
        rightPanel.add(new JScrollPane(tblKhoaHoc));
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(tblHocVien), rightPanel);
        splitPane.setDividerLocation(300);

        add(splitPane, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.SOUTH);

        // Load dữ liệu
        loadHocVienData();
        loadChuyenDeData();

        // Thêm sự kiện
        setupEvents();

        setLocationRelativeTo(parent);
    }

    private void setupEvents() {
        tblHocVien.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblHocVien.getSelectedRow();
                if (row >= 0) {
                    String maHV = tblHocVien.getValueAt(row, 0).toString();
                    String hoTen = tblHocVien.getValueAt(row, 1).toString();
                    txtHocVien.setText(maHV + " - " + hoTen);
                }
            }
        });

        tblChuyenDe.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblChuyenDe.getSelectedRow();
                if (row >= 0) {
                    String maCD = tblChuyenDe.getValueAt(row, 0).toString();
                    loadKhoaHocData(maCD);
                    String tenCD = tblChuyenDe.getValueAt(row, 1).toString();
                    txtKhoaHoc.setText("CD: " + maCD + " - " + tenCD);
                }
            }
        });

        tblKhoaHoc.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tblKhoaHoc.getSelectedRow();
                if (row >= 0) {
                    String maKH = tblKhoaHoc.getValueAt(row, 0).toString();
                    String tenKH = tblKhoaHoc.getValueAt(row, 1).toString();
                    txtKhoaHoc.setText("KH: " + maKH + " - " + tenKH);
                }
            }
        });

        btnDangKy.addActionListener(e -> handleDangKy());
    }

    private void loadHocVienData() {
        modelHV.setRowCount(0);
        for (HocVien hv : CRUD_HocVien.get()) {
            Account acc = CRUD_Account.getter(String.valueOf(hv.getUserID()), "user");
            modelHV.addRow(new Object[]{
                hv.getUserID(),
                hv.getFullName(),
                acc.getEmail(),
                hv.getGender() == 0 ? "Nữ" : "Nam"
            });
        }
    }

    private void loadChuyenDeData() {
        modelCD.setRowCount(0);
        for (ChuyenDe cd : CRUD_ChuyenDe.get()) {
            modelCD.addRow(new Object[]{
                cd.getIDChuyenDe(),
                cd.getTenChuyenDe(),
                cd.getHocPhi()
            });
        }
    }

    private void loadKhoaHocData(String maChuyenDe) {
        modelKH.setRowCount(0);
        for (KhoaHoc kh : CRUD_KhoaHoc.get()) {
            if (kh.getIDChuyenDe().equals(maChuyenDe)) {
                modelKH.addRow(new Object[]{
                    kh.getIDKhoaHoc(),
                    kh.getTenKhoaHoc(),
                    kh.getHocPhi()
                });
            }
        }
    }

    private void handleDangKy() {
        try {
            if (txtHocVien.getText().isEmpty() || txtKhoaHoc.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ thông tin!");
                return;
            }

            String[] hvInfo = txtHocVien.getText().split(" - ");
            int maHV = Integer.parseInt(hvInfo[0]);

            if (txtKhoaHoc.getText().startsWith("CD:")) {
                String[] cdInfo = txtKhoaHoc.getText().split(": ")[1].split(" - ");
                int maCD = Integer.parseInt(cdInfo[0]);
                float hocPhi = Float.parseFloat(tblChuyenDe.getValueAt(
                    tblChuyenDe.getSelectedRow(), 2).toString());
                
                int result = CRUD_DangkyChuyenDe.createChuyenDe(maCD, maHV, hocPhi, "");
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Đăng ký chuyên đề thành công!");
                    dispose();
                }
            } else {
                String[] khInfo = txtKhoaHoc.getText().split(": ")[1].split(" - ");
                int maKH = Integer.parseInt(khInfo[0]);
                float hocPhi = Float.parseFloat(tblKhoaHoc.getValueAt(
                    tblKhoaHoc.getSelectedRow(), 2).toString());
                
                int result = CRUD_DangkyChuyenDe.createKhoaHoc(0, maKH, maHV, hocPhi);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Đăng ký khóa học thành công!");
                    dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
}