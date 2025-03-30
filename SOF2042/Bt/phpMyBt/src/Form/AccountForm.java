package Form;

import CRUD.CRUD_Account;
import Model.Account;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AccountForm extends JDialog {

    private JTextField txtMa, txtMail, txtDateCreate, txtStatus, txtPassword, txtName, txtAddress;
    private JComboBox<String> txtGender, txtRole;
    private final Account data;
    private final Main parent;

    public static String dateToString(Date sqlDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(sqlDate);
    }
    
    public AccountForm(Account cd, Main parent) {
        super(parent, cd == null ? "Thêm tài khoản" : "Sửa tài khoản", true);
        this.data = cd;
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(10, 2, 5, 5));  // Điều chỉnh GridLayout để có khoảng cách phù hợp
    
        // Khởi tạo các components
        txtMa = new JTextField();
        txtName = new JTextField();
        txtMail = new JTextField();
        txtPassword = new JTextField();
        txtGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        txtAddress = new JTextField();
        txtRole = new JComboBox<>(new String[]{"Sinh viên", "Giảng viên"});
        txtDateCreate = new JTextField();
        txtStatus = new JTextField();
        JButton btnSave = new JButton("Lưu");
    
        // Thiết lập giá trị mặc định
        if (data != null) {
            txtMa.setText(String.valueOf(data.getUserID()));
            txtMa.setEnabled(false);
            txtName.setText(data.getFullName());
            txtMail.setText(data.getEmail());
            txtRole.setSelectedItem(data.getRole() == 1 ? "Giảng viên" : "Sinh viên");
            txtDateCreate.setText(dateToString(data.getCreateAt()));
            txtStatus.setText(data.getStatus() == 1 ? "Hoạt động" : "Không hoạt động");
        } else {
            txtMa.setText(String.valueOf(CRUD_Account.get().size() + 1));
            txtMa.setEnabled(false);
            txtDateCreate.setText(dateToString(new Date()));
            txtStatus.setText("Hoạt động");
        }
    
        txtDateCreate.setEnabled(false);
        txtStatus.setEnabled(false);
    
        // Thêm components theo thứ tự đúng
        add(new JLabel("Mã tài khoản:"));
        add(txtMa);
        add(new JLabel("Họ và tên:"));
        add(txtName);
        add(new JLabel("Email:"));
        add(txtMail);
        add(new JLabel("Mật khẩu:"));
        add(txtPassword);
        add(new JLabel("Giới tính:"));
        add(txtGender);
        add(new JLabel("Địa chỉ:"));
        add(txtAddress);
        add(new JLabel("Vai trò:"));
        add(txtRole);
        add(new JLabel("Ngày tạo:"));
        add(txtDateCreate);
        add(new JLabel("Trạng thái:"));
        add(txtStatus);
        add(new JLabel(""));
        add(btnSave);
    
        btnSave.addActionListener(e -> saveData());
    
        pack();
        setLocationRelativeTo(parent);
    }

    private void saveData() {
        try {
            String email = txtMail.getText().trim();
            String password = txtPassword.getText();
            String fullName = txtName.getText().trim();
            String address = txtAddress.getText().trim();
            
            if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin bắt buộc");
                return;
            }

            if (data == null) {
                int result = CRUD_Account.create(
                    email,
                    password, 
                    txtRole.getSelectedItem().equals("Sinh viên") ? 0 : 1,
                    1,
                    fullName,
                    txtGender.getSelectedItem().equals("Nam") ? 1 : 0,
                    address
                );
                if (result > 0) {
                    parent.updateTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm mới thất bại!");
                }
            } else {
                int result = CRUD_Account.update(
                    Integer.parseInt(txtMa.getText().replaceAll("[^0-9]", "")),
                    email,
                    password,
                    txtRole.getSelectedItem().equals("Sinh viên") ? 0 : 1,
                    1,
                    fullName,
                    address,
                    txtGender.getSelectedItem().equals("Nam") ? 1 : 0
                );
                if (result > 0) {
                    parent.updateTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
}