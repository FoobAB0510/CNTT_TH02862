/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DAOClass.Form;

import DAOClass.CRUD_DAO;
import DAOClass.ModelsClass.ChuyenDe;
import DAOClass.ModelsClass.KhoaHoc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author ADMIN
 */
public class TestData extends javax.swing.JFrame {

    /**
     * Creates new form TestData
     */
    private List<ChuyenDe> chuyendeList;
    private JPanel contentPanel;
    private JLabel pageLabel;
    private JButton nextButton, backButton;
    private int currentPage = 1;
    private int itemsPerPage = 4;

    public TestData() {
        setTitle("Danh sách Chuyên đề");
        this.setSize(900, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        chuyendeList = new ArrayList<>();
        String select = "Select * from ChuyenDe";
        List<Object> selectparam = new ArrayList<>();

        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(select, selectparam);
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe();
                cd.setId(UUID.fromString(rs.getString(1)));
                cd.setName(rs.getString(2));
                cd.setMota(rs.getString(3));
                cd.setHocphi(rs.getInt(4));
                cd.setImgURL(rs.getString(5));
                cd.setTrangthai(rs.getInt(6));
                chuyendeList.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(rootPane, chuyendeList.size());

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 10, 10));
        contentPanel.setBackground(Color.darkGray);
        this.add(contentPanel, BorderLayout.CENTER);

        JPanel paginationPanel = new JPanel();
        paginationPanel.setBackground(Color.LIGHT_GRAY);
        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        pageLabel = new JLabel("Page: 1");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 1) {
                    currentPage--;
                    loadPage();
                }
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int totalPages = (int) Math.ceil((double) chuyendeList.size() / itemsPerPage);
                if (currentPage < totalPages) {
                    currentPage++;
                    loadPage();
                }
            }
        });
        paginationPanel.add(backButton);
        paginationPanel.add(pageLabel);
        paginationPanel.add(nextButton);
        add(paginationPanel, BorderLayout.SOUTH);
        loadPage();
        this.setVisible(true);
        initComponents();
    }

    private JPanel createChuyendePanel(ChuyenDe cd) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(200, 250));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        ImageIcon icon = new ImageIcon(new ImageIcon(cd.getImgURL()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        JLabel imgLabel = new JLabel(icon);
        imgLabel.setPreferredSize(new Dimension(150, 150));
        panel.add(imgLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new GridLayout(3, 1, 5, 5));
        infoPanel.add(new JLabel("   Tên: " + cd.getName()));
        infoPanel.add(new JLabel("   Mô tả: " + cd.getMota()));
        infoPanel.add(new JLabel("   Học phí: " + cd.getHocphi() + " VND"));
        panel.add(infoPanel, BorderLayout.CENTER);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CDKhoaHoc(cd.getId());
            }
        });

        return panel;
    }

    private void CDKhoaHoc(UUID chuyendeId) {
        List<KhoaHoc> khoaHocList = new ArrayList<>();
        String query = "SELECT * FROM KhoaHoc WHERE MaChuyenDe = ?";
        List<Object> params = new ArrayList<>();
        params.add(chuyendeId.toString());

        try {
            ResultSet rs = (ResultSet) CRUD_DAO.executeQuery(query, params);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setIdKhoaHoc(UUID.fromString(rs.getString(1)));
                kh.setTenKhoaHoc(rs.getString(2));
                kh.setMoTa(rs.getString(3));
                kh.setImgURL(rs.getString(4));
                kh.setThoiLuong(rs.getInt(5));
                kh.setHocPhi(rs.getInt(6));
                kh.setTrangThai(rs.getInt(7));
                khoaHocList.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (khoaHocList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khóa học nào thuộc chuyên đề này!");
            return;
        }

        JDialog dialog = new JDialog(this, "Danh sách khóa học thuộc chuyên đề", true);
        dialog.setSize(700, 500);
        dialog.setLayout(new BorderLayout());

        JPanel khoaHocPanel = new JPanel();
        khoaHocPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Hiển thị 2 cột
        khoaHocPanel.setBackground(Color.WHITE);

        for (KhoaHoc kh : khoaHocList) {
            JPanel khPanel = new JPanel(new BorderLayout());
            khPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            khPanel.setBackground(Color.LIGHT_GRAY);
            khPanel.setPreferredSize(new Dimension(300, 150));

            ImageIcon icon = new ImageIcon(new ImageIcon(kh.getImgURL()).getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH));
            JLabel imgLabel = new JLabel(icon);
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new GridLayout(3, 1));
            infoPanel.setBackground(Color.WHITE);
            infoPanel.add(new JLabel("Tên: " + kh.getTenKhoaHoc()));
            infoPanel.add(new JLabel("Mô tả: " + kh.getMoTa()));
            infoPanel.add(new JLabel("Học phí: " + kh.getHocPhi() + " VND"));

            khPanel.add(imgLabel, BorderLayout.NORTH);
            khPanel.add(infoPanel, BorderLayout.CENTER);

            khPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    khPanel.setBackground(Color.LIGHT_GRAY);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    khPanel.setBackground(Color.gray);
                }
            });

            khoaHocPanel.add(khPanel);
        }

        JScrollPane scrollPane = new JScrollPane(khoaHocPanel);
        dialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Đóng");
        closeButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void loadPage() {
        contentPanel.removeAll();
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, chuyendeList.size());
        for (int i = start; i < end; i++) {
            ChuyenDe cd = chuyendeList.get(i);
            contentPanel.add(createChuyendePanel(cd));
        }

        int emptyPanels = itemsPerPage - (end - start);
        for (int i = 0; i < emptyPanels; i++) {
            JPanel p = new JPanel();
            p.add(new Label(i + 1 + " empty"));
            contentPanel.add(p);
        }
        pageLabel.setText("Page: " + currentPage);
        contentPanel.revalidate();
        contentPanel.repaint();
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
            .addGap(0, 1069, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(TestData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
