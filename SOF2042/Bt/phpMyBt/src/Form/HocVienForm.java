package Form;

import CRUD.CRUD_Account;
import Model.Account;
import Model.HocVien;
import java.awt.*;
import javax.swing.*;

public class HocVienForm extends JDialog {
    private JTextField txtEmail, txtPassword, txtFullName, txtAddress;
    private JComboBox<String> cboGender, cboRole, cboStatus;
    private final Account accountData;
    private final HocVien hocVienData;
    private final Main parent;

    public HocVienForm(Account acc, HocVien hv, Main parent) {
        super(parent, acc == null ? "Thêm học viên" : "Sửa học viên", true);
        this.accountData = acc;
        this.hocVienData = hv;
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(9, 2, 10, 10));

        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtFullName = new JTextField();
        txtAddress = new JTextField();
        
        cboGender = new JComboBox<>(new String[]{"Nữ", "Nam"});
        cboRole = new JComboBox<>(new String[]{"Sinh viên", "Giảng viên"});
        cboStatus = new JComboBox<>(new String[]{"Không hoạt động", "Hoạt động"});
        
        JButton btnSave = new JButton("Lưu");

        if (accountData != null && hocVienData != null) {
            txtEmail.setText(accountData.getEmail());
            txtPassword.setText(accountData.getPassword());
            txtFullName.setText(hocVienData.getFullName());
            txtAddress.setText(hocVienData.getAddress());
            cboGender.setSelectedIndex(hocVienData.getGender());
            cboRole.setSelectedIndex(accountData.getRole());
            cboStatus.setSelectedIndex(accountData.getStatus());
        }

        btnSave.addActionListener(e -> saveData());

        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Mật khẩu:"));
        add(txtPassword);
        add(new JLabel("Họ tên:"));
        add(txtFullName);
        add(new JLabel("Địa chỉ:"));
        add(txtAddress);
        add(new JLabel("Giới tính:"));
        add(cboGender);
        add(new JLabel("Vai trò:"));
        add(cboRole);
        add(new JLabel("Trạng thái:"));
        add(cboStatus);
        add(new JLabel(""));
        add(btnSave);

        pack();
        setLocationRelativeTo(parent);
    }

    private void saveData() {
        try {
            String email = txtEmail.getText().trim();
            String password = txtPassword.getText();
            String fullName = txtFullName.getText().trim();
            int gender = cboGender.getSelectedIndex();
            int role = cboRole.getSelectedIndex();
            int status = cboStatus.getSelectedIndex();
            String address = txtAddress.getText().trim();

            if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin bắt buộc");
                return;
            }

            if (accountData == null) {
                // String mail, String pass, int role, int status, String fullName, int gender, String address
                int result = CRUD_Account.create(email, password, role, status, fullName, gender, address);
                if (result > 0) {
                    parent.updateTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm mới thất bại!");
                }
            } else {
                int result = CRUD_Account.update(accountData.getUserID(), email, password, role, status, fullName, address, gender);
                if (result > 0) {
                    parent.updateTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
}