/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DAOClass.Form;

import DAOClass.ChuyenDeDao;
import DAOClass.KhoaHocDAO;
import DAOClass.ModelsClass.ChuyenDe;
import DAOClass.ModelsClass.KhoaHoc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class KhoaHocPanel extends javax.swing.JPanel {

    /**
     * Creates new form KhoaHocPanel
     */
    private JTable table;
    private DefaultTableModel tableModel;
    private KhoaHocDAO khoaHocDao;
    private ChuyenDeDao chuyenDeDao;

    public KhoaHocPanel() {
        setLayout(new BorderLayout());
        khoaHocDao = new KhoaHocDAO();
        chuyenDeDao = new ChuyenDeDao();

        String[] columnNames = {"Mã Khóa Học", "Tên Khóa Học", "Mô Tả", "Thời Lượng", "Ảnh", "Học Phí", "Mã Chuyên Đề", "Trạng Thái"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");

        btnAdd.addActionListener(e -> addKhoaHoc());
        btnEdit.addActionListener(e -> editKhoaHoc());
        btnDelete.addActionListener(e -> deleteKhoaHoc());

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<KhoaHoc> list = khoaHocDao.hienThi();
        for (KhoaHoc kh : list) {
            tableModel.addRow(new Object[]{
                kh.getIdKhoaHoc(),
                kh.getTenKhoaHoc(),
                kh.getMoTa(),
                kh.getThoiLuong(),
                kh.getImgURL() != null ? kh.getImgURL() : "Không có ảnh",
                kh.getHocPhi(),
                kh.getIdChuyenDe(),
                kh.getTrangThai()
            });
        }
    }

    private void addKhoaHoc() {
        List<ChuyenDe> chuyenDeList = chuyenDeDao.hienThi();
        if (chuyenDeList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có chuyên đề nào, hãy thêm chuyên đề trước!");
            return;
        }

        String[] chuyenDeNames = chuyenDeList.stream().map(ChuyenDe::getName).toArray(String[]::new);
        UUID[] chuyenDeIds = chuyenDeList.stream().map(ChuyenDe::getId).toArray(UUID[]::new);

        String selectedChuyenDe = (String) JOptionPane.showInputDialog(
                this,
                "Chọn chuyên đề:",
                "Danh sách Chuyên Đề",
                JOptionPane.QUESTION_MESSAGE,
                null,
                chuyenDeNames,
                chuyenDeNames[0]
        );

        if (selectedChuyenDe == null) {
            return;
        }

        UUID idChuyenDe = null;
        for (int i = 0; i < chuyenDeList.size(); i++) {
            if (chuyenDeNames[i].equals(selectedChuyenDe)) {
                idChuyenDe = chuyenDeIds[i];
                break;
            }
        }

        String ten = JOptionPane.showInputDialog(this, "Nhập tên khóa học:");
        String mota = JOptionPane.showInputDialog(this, "Nhập mô tả:");
        int thoiLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập thời lượng (giờ):"));
        double hocPhi = Double.parseDouble(JOptionPane.showInputDialog(this, "Nhập học phí:"));
        int trangthai = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập trạng thái:"));

        KhoaHoc kh = new KhoaHoc(UUID.randomUUID(), ten, mota, "default.jpg", thoiLuong, hocPhi, trangthai, idChuyenDe);
        if (khoaHocDao.themKhoaHoc(kh)) {
            loadData();
        }
    }

    private void editKhoaHoc() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khóa học để sửa.");
            return;
        }

        List<ChuyenDe> chuyenDeList = chuyenDeDao.hienThi();
        if (chuyenDeList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có chuyên đề nào, hãy thêm chuyên đề trước!");
            return;
        }

        UUID id = UUID.fromString(tableModel.getValueAt(selectedRow, 0).toString());
        String currentTen = tableModel.getValueAt(selectedRow, 1).toString();
        String currentMota = tableModel.getValueAt(selectedRow, 2).toString();
        int currentThoiLuong = Integer.parseInt(tableModel.getValueAt(selectedRow, 3).toString());
        String currentImgURL = tableModel.getValueAt(selectedRow, 4).toString();
        double currentHocPhi = Double.parseDouble(tableModel.getValueAt(selectedRow, 5).toString());
        int currentTrangThai = Integer.parseInt(tableModel.getValueAt(selectedRow, 7).toString());
        UUID currentIdChuyenDe = UUID.fromString(tableModel.getValueAt(selectedRow, 6).toString());

        String[] chuyenDeNames = chuyenDeList.stream().map(ChuyenDe::getName).toArray(String[]::new);
        UUID[] chuyenDeIds = chuyenDeList.stream().map(ChuyenDe::getId).toArray(UUID[]::new);

        String selectedChuyenDe = (String) JOptionPane.showInputDialog(
                this,
                "Chọn chuyên đề mới:",
                "Danh sách Chuyên Đề",
                JOptionPane.QUESTION_MESSAGE,
                null,
                chuyenDeNames,
                chuyenDeNames[0]
        );

        if (selectedChuyenDe == null) {
            return;
        }

        UUID newIdChuyenDe = null;
        for (int i = 0; i < chuyenDeList.size(); i++) {
            if (chuyenDeNames[i].equals(selectedChuyenDe)) {
                newIdChuyenDe = chuyenDeIds[i];
                break;
            }
        }

        String newTen = JOptionPane.showInputDialog(this, "Nhập tên khóa học:", currentTen);
        String newMota = JOptionPane.showInputDialog(this, "Nhập mô tả:", currentMota);
        String newThoiLuongStr = JOptionPane.showInputDialog(this, "Nhập thời lượng (giờ):", currentThoiLuong);
        String newHocPhiStr = JOptionPane.showInputDialog(this, "Nhập học phí:", currentHocPhi);
        String newImgURL = JOptionPane.showInputDialog(this, "Nhập đường dẫn ảnh:", currentImgURL);
        String newTrangThaiStr = JOptionPane.showInputDialog(this, "Nhập trạng thái (0 hoặc 1):", currentTrangThai);

        if (newTen == null || newMota == null || newThoiLuongStr == null || newHocPhiStr == null || newTrangThaiStr == null) {
            return;
        }

        try {
            int newThoiLuong = Integer.parseInt(newThoiLuongStr);
            double newHocPhi = Double.parseDouble(newHocPhiStr);
            int newTrangThai = Integer.parseInt(newTrangThaiStr);

            if (newTrangThai != 0 && newTrangThai != 1) {
                throw new NumberFormatException("Trạng thái chỉ có thể là 0 hoặc 1.");
            }

            KhoaHoc kh = new KhoaHoc(id, newTen, newMota, newImgURL, newThoiLuong, newHocPhi, newTrangThai, newIdChuyenDe);
            if (khoaHocDao.suaKhoaHoc(kh)) {
                loadData();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteKhoaHoc() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khóa học để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khóa học này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            UUID id = UUID.fromString(tableModel.getValueAt(selectedRow, 0).toString());
            if (khoaHocDao.xoaKhoaHoc(id)) {
                loadData();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
