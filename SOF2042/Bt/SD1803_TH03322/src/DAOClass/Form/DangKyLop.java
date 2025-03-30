/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DAOClass.Form;

import DAOClass.DangKyKhoaHocDAO;
import DAOClass.KhoaHocDAO;
import DAOClass.LopHocDAO;
import DAOClass.ModelsClass.KhoaHoc;
import DAOClass.ModelsClass.LopHoc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class DangKyLop extends javax.swing.JFrame {

    /**
     * Creates new form DangKyLop
     */
    private String idHocVien;
    private JTable tblKhoaHoc, tblLopHoc;
    private DefaultTableModel modelKhoaHoc, modelLopHoc;
    private JButton btnDangKy;

    public DangKyLop(String idHocVien) {
        this.idHocVien = idHocVien;
        setTitle("Đăng Ký Khóa Học và Lớp Học");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tiêu đề
        JLabel lblTitle = new JLabel("Đăng Ký Khóa Học", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Bảng Khóa Học
        modelKhoaHoc = new DefaultTableModel(new String[]{"ID", "Tên", "Học phí"}, 0);
        tblKhoaHoc = new JTable(modelKhoaHoc);
        loadKhoaHoc();

        // Bảng Lớp Học
        modelLopHoc = new DefaultTableModel(new String[]{"ID", "Tên Lớp"}, 0);
        tblLopHoc = new JTable(modelLopHoc);

        // Xử lý sự kiện chọn khóa học
        tblKhoaHoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblKhoaHoc.getSelectedRow();
                if (selectedRow >= 0) {
                    String idKhoaHoc = modelKhoaHoc.getValueAt(selectedRow, 0).toString();
                    loadLopHoc(idKhoaHoc);
                }
            }
        });

        // Xử lý sự kiện chọn lớp học
        tblLopHoc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tblKhoaHoc.getSelectedRow() >= 0 && tblLopHoc.getSelectedRow() >= 0) {
                    btnDangKy.setEnabled(true);
                }
            }
        });

        // Nút Đăng Ký
        btnDangKy = new JButton("Đăng Ký");
        btnDangKy.setEnabled(false);
        btnDangKy.setFont(new Font("Arial", Font.BOLD, 14));
        btnDangKy.setBackground(new Color(34, 177, 76));
        btnDangKy.setForeground(Color.WHITE);
        btnDangKy.setFocusPainted(false);
        btnDangKy.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnDangKy.addActionListener(e -> {
            int selectedKhoaHoc = tblKhoaHoc.getSelectedRow();
            int selectedLopHoc = tblLopHoc.getSelectedRow();
            if (selectedKhoaHoc >= 0 && selectedLopHoc >= 0) {
                String idKhoaHoc = modelKhoaHoc.getValueAt(selectedKhoaHoc, 0).toString();
                String idLopHoc = modelLopHoc.getValueAt(selectedLopHoc, 0).toString();
                dangKyKhoaHoc(idHocVien, idKhoaHoc, idLopHoc);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn cả khóa học và lớp học.");
            }
        });

        // Layout chính
        JPanel panelCenter = new JPanel(new GridLayout(1, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCenter.add(new JScrollPane(tblKhoaHoc));
        panelCenter.add(new JScrollPane(tblLopHoc));

        JPanel panelBottom = new JPanel();
        panelBottom.add(btnDangKy);

        setLayout(new BorderLayout());
        add(lblTitle, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
    }

    private void loadKhoaHoc() {
        modelKhoaHoc.setRowCount(0);
        List<KhoaHoc> list = KhoaHocDAO.hienThiTiep();
        for (KhoaHoc kh : list) {
            modelKhoaHoc.addRow(new Object[]{kh.getIdKhoaHoc(), kh.getTenKhoaHoc(), kh.getHocPhi()});
        }
    }

    private void loadLopHoc(String idKhoaHoc) {
        modelLopHoc.setRowCount(0);
        List<LopHoc> list = LopHocDAO.hienThiDK(idKhoaHoc);
        for (LopHoc lh : list) {
            modelLopHoc.addRow(new Object[]{lh.getId(), lh.getTen()});
        }
    }

    private void dangKyKhoaHoc(String idHocVien, String idKhoaHoc, String idLopHoc) {
        boolean success = DangKyKhoaHocDAO.dangKy(idHocVien, idKhoaHoc, idLopHoc);
        if (success) {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Đăng ký thất bại!");
        }
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DangKyLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DangKyLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DangKyLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DangKyLop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
