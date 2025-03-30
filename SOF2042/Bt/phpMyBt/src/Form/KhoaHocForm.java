package Form;

import javax.swing.*;

import CRUD.CRUD_ChuyenDe;
import CRUD.CRUD_KhoaHoc;
import Model.ChuyenDe;
import Model.KhoaHoc;

import java.awt.*;
import java.util.ArrayList;

public class KhoaHocForm extends JDialog {
    private JTextField txtMa, txtTen, txtHocPhi, txtThoiLuong, txtHinh;
    private JComboBox<String> cboChuyenDe;
    private final KhoaHoc data;
    private final Main parent;

    public KhoaHocForm(KhoaHoc kh, Main parent) {
        super(parent, kh == null ? "Thêm khóa học" : "Sửa khóa học", true);
        this.data = kh;
        this.parent = parent;
        setupUI();
    }

    private void setupUI() {
        setLayout(new GridLayout(7, 2, 10, 10));

        txtMa = new JTextField();
        txtTen = new JTextField();
        txtHocPhi = new JTextField();
        txtThoiLuong = new JTextField();
        txtHinh = new JTextField();
        
        // Populate ChuyenDe combobox
        ArrayList<ChuyenDe> chuyenDeList = CRUD_ChuyenDe.get();
        String[] chuyenDeNames = new String[chuyenDeList.size()];
        for (int i = 0; i < chuyenDeList.size(); i++) {
            chuyenDeNames[i] = chuyenDeList.get(i).getTenChuyenDe();
        }
        cboChuyenDe = new JComboBox<>(chuyenDeNames);
        
        JButton btnBrowse = new JButton("Chọn ảnh");
        JButton btnSave = new JButton("Lưu");

        if (data != null) {
            txtMa.setText(data.getIDKhoaHoc());
            txtMa.setEnabled(false);
            txtTen.setText(data.getTenKhoaHoc());
            txtHocPhi.setText(String.valueOf(data.getHocPhi()));
            txtThoiLuong.setText(data.getThoiLuong());
            txtHinh.setText(data.getIMG());
            
            // Set selected ChuyenDe
            for (int i = 0; i < chuyenDeList.size(); i++) {
                if (chuyenDeList.get(i).getIDChuyenDe().equals(data.getIDChuyenDe())) {
                    cboChuyenDe.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            txtMa.setText("KH" + (CRUD_KhoaHoc.get().size() + 1));
            txtMa.setEnabled(false);
        }

        btnBrowse.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Ảnh", "jpg", "png", "gif"));
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                txtHinh.setText(fc.getSelectedFile().getAbsolutePath());
            }
        });

        btnSave.addActionListener(e -> saveData());

        add(new JLabel("Mã khóa học:"));
        add(txtMa);
        add(new JLabel("Tên khóa học:"));
        add(txtTen);
        add(new JLabel("Chuyên đề:"));
        add(cboChuyenDe);
        add(new JLabel("Học phí:"));
        add(txtHocPhi);
        add(new JLabel("Thời lượng:"));
        add(txtThoiLuong);
        add(new JLabel("Hình ảnh:"));
        add(txtHinh);
        add(btnBrowse);
        add(btnSave);

        pack();
        setLocationRelativeTo(parent);
    }

    private void saveData() {
        try {
            String selectedChuyenDe = CRUD_ChuyenDe.get().get(cboChuyenDe.getSelectedIndex()).getIDChuyenDe();
            float hocPhi = Float.parseFloat(txtHocPhi.getText());
            int thoiLuong = Integer.parseInt(txtThoiLuong.getText());

            if (data == null) {
                int result = CRUD_KhoaHoc.create(
                    txtTen.getText(), 
                    "", // Description
                    Integer.parseInt(selectedChuyenDe),
                    hocPhi,
                    thoiLuong,
                    txtHinh.getText(),
                    0, // LessonsCount
                    true // Status
                );
                if (result > 0) {
                    parent.updateTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!");
                }
            } else {
                int idNumber = Integer.parseInt(txtMa.getText().replaceAll("[^0-9]", ""));
                int result = CRUD_KhoaHoc.update(
                    idNumber,
                    txtTen.getText(),
                    "", // Description
                    Integer.parseInt(selectedChuyenDe),
                    hocPhi,
                    thoiLuong,
                    txtHinh.getText(),
                    0, // LessonsCount
                    true // Status
                );
                if (result > 0) {
                    parent.updateTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Học phí hoặc thời lượng không hợp lệ");
        }
    }
}