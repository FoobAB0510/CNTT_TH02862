package Form;

import CRUD.CRUD_Account;
import CRUD.CRUD_ChuyenDe;
import CRUD.CRUD_DangkyChuyenDe;
import CRUD.CRUD_HocVien;
import CRUD.CRUD_KhoaHoc;
import Model.Account;
import Model.ChuyenDe;
import Model.DkyChuyenDe;
import Model.DkyKhoaHoc;
import Model.HocVien;
import Model.KhoaHoc;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {

    private JComboBox<String> viewSelector;
    private JTable mainTable;
    private JButton addButton, editButton, deleteButton;

    // Is main frame fr
    public Main() {
        setTitle("Quản lý đào tạo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        events();
        loadTableData();
    }

    // Init fr
    private void initComponents() {
        // Tạo top panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn giao diện LEFT
        viewSelector = new JComboBox<>(new String[]{"Chuyên đề", "Khóa học", "Học viên", "Quản lý tài khoản", "Danh sách đăng ký"});
        // Tạo button
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");

        // Thêm vào top panel
        topPanel.add(viewSelector);
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        add(topPanel, BorderLayout.NORTH);

        mainTable = new JTable();
        mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(mainTable);
        add(scrollPane, BorderLayout.CENTER);

        // setLocationRelativeTo(null);
    }

    // Php my event 🎈🎈🎈
    private void events() {
        viewSelector.addActionListener(e -> loadTableData());
        addButton.addActionListener(e -> handleAdd());
        editButton.addActionListener(e -> handleEdit());
        deleteButton.addActionListener(e -> handleDelete());

        mainTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showDetailDialog();
                }
            }
        });
    }

    // Display bảng cho lần đầu fr
    private void loadTableData() {
        String view = (String) viewSelector.getSelectedItem();
        DefaultTableModel model = new DefaultTableModel() {
            // Ko thể sửa bảng. ....
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        switch (view) {
            case "Chuyên đề":
                model.setColumnIdentifiers(new String[]{"Mã CD", "Tên CD", "Học phí", "Mô tả", "Hình ảnh"});
                for (ChuyenDe cd : CRUD_ChuyenDe.get()) {
                    model.addRow(new Object[]{cd.getIDChuyenDe(), cd.getTenChuyenDe(), cd.getHocPhi(), cd.getMoTa(), cd.getIMG()});
                }
                break;
            case "Khóa học":
                model.setColumnIdentifiers(new String[]{"Mã KH", "Tên KH", "Mã CD", "Học phí", "Thời lượng", "Hình ảnh"});
                for (KhoaHoc kh : CRUD_KhoaHoc.get()) {
                    model.addRow(new Object[]{kh.getIDKhoaHoc(), kh.getTenKhoaHoc(), kh.getIDChuyenDe(), kh.getHocPhi(), kh.getThoiLuong(), kh.getIMG()});
                }
                break;
            case "Học viên":
                model.setColumnIdentifiers(new String[]{"Mã HV", "Họ tên", "Email", "Điện thoại", "Giới tính"});
                for (HocVien hv : CRUD_HocVien.get()) {
                    Account acc = CRUD_Account.getter(String.valueOf(hv.getUserID()), "user");
                    model.addRow(new Object[]{hv.getUserID(), hv.getFullName(), acc.getEmail(), hv.getPhone(), hv.getGender() == 0 ? "Nữ" : "Nam"});
                }
                break;
            case "Quản lý tài khoản":
                model.setColumnIdentifiers(new String[]{"Mã TK", "Tên đăng nhập", "Email", "Vai trò"});
                for (Account acc : CRUD_Account.get()) {
                    // Vì Account không có getID hay getUsername nên dùng getUserID() và getEmail()
                    model.addRow(new Object[]{acc.getUserID(), acc.getEmail(), acc.getEmail(), acc.getRole()});
                }
                break;
            case "Danh sách đăng ký":
                model.setColumnIdentifiers(new String[]{"Mã ĐK", "Loại ĐK", "Tên SV", "Tên CD/KH", "Học phí", "Ngày ĐK", "Trạng thái"});

                // Load đăng ký chuyên đề
                for (Object obj : CRUD_DangkyChuyenDe.getChuyenDe()) {
                    DkyChuyenDe dky = (DkyChuyenDe) obj;
                    HocVien hv = CRUD_HocVien.getter(String.valueOf(dky.getSinhVienID()), "id");
                    ChuyenDe cd = CRUD_ChuyenDe.getByList(String.valueOf(dky.getCategoryID()));

                    model.addRow(new Object[]{
                        "CD" + dky.getDKID(),
                        "Chuyên đề",
                        hv.getFullName(),
                        cd.getTenChuyenDe(),
                        dky.getHocPhi(),
                        dky.getNgayDK(),
                        "Đã đăng ký"
                    });
                }

                // Load đăng ký khóa học
                for (Object obj : CRUD_DangkyChuyenDe.getKhoaHoc()) {
                    DkyKhoaHoc dky = (DkyKhoaHoc) obj;
                    HocVien hv = CRUD_HocVien.getter(String.valueOf(dky.getSinhVienID()), "id");
                    KhoaHoc kh = CRUD_KhoaHoc.getByID(String.valueOf(dky.getCID()));

                    model.addRow(new Object[]{
                        "KH" + dky.getDKID(),
                        "Khóa học",
                        hv.getFullName(),
                        kh.getTenKhoaHoc(),
                        dky.getPrice(),
                        dky.getRegDate(),
                        dky.getStatus() == 1 ? "Hoạt động" : "Không hoạt động"
                    });
                }
                break;
        }

        mainTable.setModel(model);
    }

    // Khi add
    private void handleAdd() {
        String view = (String) viewSelector.getSelectedItem();
        switch (view) {
            case "Chuyên đề":
                new ChuyenDeForm(null, this).setVisible(true);
                break;
            case "Khóa học":
                new KhoaHocForm(null, this).setVisible(true);
                break;
            case "Học viên":
                new HocVienForm(null, null, this).setVisible(true);
                break;
            case "Quản lý tài khoản":
                new AccountForm(null, null).setVisible(true);
                break;
            case "Danh sách đăng ký":
                new DkyNgay(this).setVisible(true);
                break;
        }
        loadTableData();
    }

    // Khi edit
    private void handleEdit() {
        int row = mainTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mục cần sửa");
            return;
        }
        String view = (String) viewSelector.getSelectedItem();
        String id = mainTable.getValueAt(row, 0).toString();
        switch (view) {
            case "Chuyên đề":
                ChuyenDe cd = CRUD_ChuyenDe.getByList(id);
                new ChuyenDeForm(cd, this).setVisible(true);
                break;
            case "Khóa học":
                KhoaHoc kh = CRUD_KhoaHoc.getByID(id);
                new KhoaHocForm(kh, this).setVisible(true);
                break;
            case "Học viên":
                Account acc = CRUD_Account.getter(String.valueOf(Integer.parseInt(id)), "id");
                HocVien hv = CRUD_HocVien.getter(id, "user");
                new HocVienForm(acc, hv, this).setVisible(true);
                break;
            case "Quản lý tài khoản":
                Account account = CRUD_Account.getter(String.valueOf(Integer.parseInt(id)), "id");
                new AccountForm(account, this).setVisible(true);
                break;
            case "Danh sách đăng ký":
                String dkyId = id.substring(2); // Bỏ "CD" hoặc "KH" ở đầu
                boolean isChuyenDe = id.startsWith("CD");
                if (isChuyenDe) {
                    // Edit đăng ký chuyên đề
                    DkyChuyenDe dkyCD = null;
                    for (Object obj : CRUD_DangkyChuyenDe.getChuyenDe()) {
                        DkyChuyenDe dky = (DkyChuyenDe) obj;
                        if (dky.getDKID() == Integer.parseInt(dkyId)) {
                            dkyCD = dky;
                            break;
                        }
                    }
                    if (dkyCD != null) {
                        new DkyNgay(this).setVisible(true);
                    }
                } else {
                    // Edit đăng ký khóa học  
                    DkyKhoaHoc dkyKH = null;
                    for (Object obj : CRUD_DangkyChuyenDe.getKhoaHoc()) {
                        DkyKhoaHoc dky = (DkyKhoaHoc) obj;
                        if (dky.getDKID() == Integer.parseInt(dkyId)) {
                            dkyKH = dky;
                            break;
                        }
                    }
                    if (dkyKH != null) {
                        new DkyNgay(this).setVisible(true);
                    }
                }
                break;
        }
    }

    // Khi delete
    private void handleDelete() {
        int row = mainTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mục cần xóa");
            return;
        }
        String view = (String) viewSelector.getSelectedItem();
        Object value = mainTable.getValueAt(row, 0);
        if (value == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ");
            return;
        }
        String id = value.toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?");
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            boolean success = false;
            switch (view) {
                case "Chuyên đề":
                    int cdId = Integer.parseInt(id.replaceAll("[^0-9]", ""));
                    success = CRUD_ChuyenDe.delete(cdId) > 0;
                    break;
                case "Khóa học":
                    int khId = Integer.parseInt(id.replaceAll("[^0-9]", ""));
                    success = CRUD_KhoaHoc.delete(khId) > 0;
                    break;
                case "Học viên":
                    success = CRUD_HocVien.delete(Integer.parseInt(id)) > 0;
                    break;
                case "Quản lý tài khoản":
                    success = CRUD_Account.delete(Integer.parseInt(id)) > 0;
                    break;
                case "Danh sách đăng ký":
                    String dkyId = id.substring(2);
                    if (id.startsWith("CD")) {
                        success = CRUD_DangkyChuyenDe.deleteChuyenDe(Integer.parseInt(dkyId)) > 0;
                    } else {
                        success = CRUD_DangkyChuyenDe.deleteKhoaHoc(Integer.parseInt(dkyId)) > 0;
                    }
                    break;
            }
            if (success) {
                loadTableData();
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã không hợp lệ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    // Khi show detail, thông tin chi tiết
    private void showDetailDialog() {
        int row = mainTable.getSelectedRow();
        if (row == -1) {
            return;
        }
        String view = (String) viewSelector.getSelectedItem();
        Object value = mainTable.getValueAt(row, 0);
        if (value == null) {
            JOptionPane.showMessageDialog(this, "Errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            return;
        }
        String id = value.toString();
        switch (view) {
            case "Chuyên đề":
                ChuyenDe cd = CRUD_ChuyenDe.getByList(id);
                if (cd != null) {
                    new DetailDialog(this, cd).setVisible(true);
                }
                break;
            case "Khóa học":
                KhoaHoc kh = CRUD_KhoaHoc.getByID(id);
                if (kh != null) {
                    new DetailDialog(this, kh).setVisible(true);
                }
                break;
            case "Học viên":
                HocVien hv = CRUD_HocVien.getter(String.valueOf(Integer.parseInt(id)), "id");
                if (hv != null) {
                    JOptionPane.showMessageDialog(this,
                            String.format("Học viên: %s\nĐịa chỉ: %s\nSĐT: %s",
                                    hv.getFullName(), hv.getAddress(), hv.getPhone()));
                }
                break;
            case "Quản lý tài khoản":
                Account acc = CRUD_Account.getter(String.valueOf(Integer.parseInt(id)), "user");
                if (acc != null) {
                    JOptionPane.showMessageDialog(this,
                            String.format("Email: %s\nVai trò: %s\nTrạng thái: %s",
                                    acc.getEmail(),
                                    acc.getRole() == 1 ? "Giảng viên" : "Sinh viên",
                                    acc.getStatus() == 1 ? "Hoạt động" : "Không hoạt động"));
                }
                break;
        }
    }

    public void updateTable() {
        loadTableData();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
