package Form;

import CRUD.CRUD_ChuyenDe;
import Model.ChuyenDe;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChuyenDeForm extends JDialog {

    private JTextField txtMa, txtTen, txtHocPhi, txtMoTa, txtHinh;
    private final ChuyenDe data;
    private final Main parent; // Dùng đối tượng Main để gọi updateTable()

    public ChuyenDeForm(ChuyenDe cd, Main parent) {
        super(parent, cd == null ? "Thêm chuyên đề" : "Sửa chuyên đề", true);
        this.data = cd;
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(6, 2, 10, 10));

        txtMa = new JTextField();
        txtTen = new JTextField();
        txtHocPhi = new JTextField();
        txtMoTa = new JTextField();
        txtHinh = new JTextField();
        JButton btnBrowse = new JButton("Chọn ảnh");
        JButton btnSave = new JButton("Lưu");

        // Nếu là sửa, điền dữ liệu hiện có
        if (data != null) {
            txtMa.setText(data.getIDChuyenDe());
            txtMa.setEnabled(false);
            txtTen.setText(data.getTenChuyenDe());
            txtHocPhi.setText(String.valueOf(data.getHocPhi()));
            txtMoTa.setText(data.getMoTa());
            txtHinh.setText(data.getIMG());
        } else {
            // Sinh mã mới (ví dụ: "CD" + số lượng chuyên đề hiện có + 1)
            txtMa.setText("CD" + (CRUD_ChuyenDe.get().size() + 1));
            txtMa.setEnabled(false);
        }

        btnBrowse.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            // Có thể giới hạn lựa chọn ảnh nếu cần
            fc.setFileFilter(new FileNameExtensionFilter("Ảnh", "jpg", "png", "gif"));
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtHinh.setText(fc.getSelectedFile().getAbsolutePath());
            }
        });

        btnSave.addActionListener(e -> saveData());

        add(new JLabel("Mã chuyên đề:"));
        add(txtMa);
        add(new JLabel("Tên chuyên đề:"));
        add(txtTen);
        add(new JLabel("Học phí:"));
        add(txtHocPhi);
        add(new JLabel("Mô tả:"));
        add(txtMoTa);
        add(new JLabel("Hình ảnh:"));
        add(txtHinh);
        add(btnBrowse);
        add(btnSave);

        pack();
        setLocationRelativeTo(parent);
    }

    private void saveData() {
        try {
            float hocPhiValue = Float.parseFloat(txtHocPhi.getText());
            if (data == null) {
                int result = CRUD_ChuyenDe.create(txtTen.getText(), txtMoTa.getText(), txtHinh.getText(), Math.round(hocPhiValue));
                if (result > 0) {
                    parent.updateTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!");
                }
            } else {
                int idNumber = Integer.parseInt(txtMa.getText().replaceAll("[^0-9]", ""));
                int result = CRUD_ChuyenDe.update(idNumber, txtTen.getText(), txtMoTa.getText(), txtHinh.getText(), hocPhiValue);
                if (result > 0) {
                    parent.updateTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Học phí không hợp lệ");
        }
    }
}
