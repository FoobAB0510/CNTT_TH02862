/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DAOClass.Form;

import DAOClass.ChuyenDeDao;
import DAOClass.ModelsClass.ChuyenDe;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class ChuyenDePanel extends javax.swing.JPanel {

    /**
     * Creates new form ChuyenDePanel
     */
    private JTable table;
    private DefaultTableModel tableModel;
    private ChuyenDeDao chuyenDeDao;

    public ChuyenDePanel() {
        setLayout(new BorderLayout());
        chuyenDeDao = new ChuyenDeDao();
        
        String[] columnNames = {"Mã Chuyên Đề", "Tên Chuyên Đề", "Mô Tả", "Học Phí", "Ảnh", "Trạng Thái"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        
        btnAdd.addActionListener(e -> addChuyenDe());
        btnEdit.addActionListener(e -> editChuyenDe());
        btnDelete.addActionListener(e -> deleteChuyenDe());
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);
        
        loadData();
        setVisible(true);
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<ChuyenDe> list = chuyenDeDao.hienThi();
        for (ChuyenDe cd : list) {
            tableModel.addRow(new Object[]{cd.getId(), cd.getName(), cd.getMota(), cd.getHocphi(), cd.getImgURL(), cd.getTrangthai()});
        }
    }
    
    private void addChuyenDe() {
        String ten = JOptionPane.showInputDialog(this, "Nhập tên chuyên đề:");
        String mota = JOptionPane.showInputDialog(this, "Nhập mô tả:");
        String hocphiStr = JOptionPane.showInputDialog(this, "Nhập học phí:");
        String imgURL = JOptionPane.showInputDialog(this, "Nhập URL ảnh:");
        String trangthaiStr = JOptionPane.showInputDialog(this, "Nhập trạng thái:");
        
        if (ten != null && mota != null && hocphiStr != null && imgURL != null && trangthaiStr != null) {
            try {
                int hocphi = Integer.parseInt(hocphiStr);
                int trangthai = Integer.parseInt(trangthaiStr);
                ChuyenDe cd = new ChuyenDe(UUID.randomUUID(), ten, mota, hocphi, imgURL, trangthai);
                if (chuyenDeDao.themCD(cd)) {
                    loadData();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Học phí và trạng thái phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editChuyenDe() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chuyên đề để sửa.");
            return;
        }
        
        UUID id = UUID.fromString(tableModel.getValueAt(selectedRow, 0).toString());
        String ten = JOptionPane.showInputDialog(this, "Nhập tên chuyên đề:", tableModel.getValueAt(selectedRow, 1));
        String mota = JOptionPane.showInputDialog(this, "Nhập mô tả:", tableModel.getValueAt(selectedRow, 2));
        String hocphiStr = JOptionPane.showInputDialog(this, "Nhập học phí:", tableModel.getValueAt(selectedRow, 3));
        String imgURL = JOptionPane.showInputDialog(this, "Nhập URL ảnh:", tableModel.getValueAt(selectedRow, 4));
        String trangthaiStr = JOptionPane.showInputDialog(this, "Nhập trạng thái:", tableModel.getValueAt(selectedRow, 5));
        
        if (ten != null && mota != null && hocphiStr != null && imgURL != null && trangthaiStr != null) {
            try {
                int hocphi = Integer.parseInt(hocphiStr);
                int trangthai = Integer.parseInt(trangthaiStr);
                ChuyenDe cd = new ChuyenDe(id, ten, mota, hocphi, imgURL, trangthai);
                if (chuyenDeDao.suaCD(cd)) {
                    loadData();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Học phí và trạng thái phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteChuyenDe() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chuyên đề để xóa.");
            return;
        }
        
        UUID id = UUID.fromString(tableModel.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa chuyên đề này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (chuyenDeDao.xoaCD(id)) {
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
