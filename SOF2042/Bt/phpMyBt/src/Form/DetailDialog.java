package Form;

import Model.ChuyenDe;
import Model.KhoaHoc;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailDialog extends JDialog {

    public DetailDialog(JFrame parent, ChuyenDe cd) {
        super(parent, "Chi tiết chuyên đề", true);
        setupUI(cd);
    }

    public DetailDialog(JFrame parent, KhoaHoc kh) {
        super(parent, "Chi tiết khóa học", true);
        setupUI(kh);
    }

    private void setupUI(ChuyenDe cd) {
        setLayout(new BorderLayout(10, 10));

        try {
            Image image = ImageIO.read(new File(cd.getIMG()));
            JLabel lblImage = new JLabel(new ImageIcon(image.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
            add(lblImage, BorderLayout.NORTH);
        } catch (Exception e) {
            add(new JLabel("Không tìm thấy ảnh"), BorderLayout.NORTH);
        }

        JPanel info = new JPanel(new GridLayout(4, 1));
        info.add(new JLabel("Mã CD: " + cd.getIDChuyenDe()));
        info.add(new JLabel("Tên CD: " + cd.getTenChuyenDe()));
        info.add(new JLabel("Học phí: " + cd.getHocPhi()));
        info.add(new JLabel("Mô tả: " + cd.getMoTa()));

        add(info, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getParent());
    }

    private void setupUI(KhoaHoc kh) {
        setLayout(new BorderLayout(10, 10));

        try {
            Image image = ImageIO.read(new File(kh.getIMG()));
            JLabel lblImage = new JLabel(new ImageIcon(image.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
            add(lblImage, BorderLayout.NORTH);
        } catch (Exception e) {
            add(new JLabel("Không tìm thấy ảnh"), BorderLayout.NORTH);
        }

        JPanel info = new JPanel(new GridLayout(5, 1));
        info.add(new JLabel("Mã KH: " + kh.getIDKhoaHoc()));
        info.add(new JLabel("Tên KH: " + kh.getTenKhoaHoc()));
        info.add(new JLabel("Mã CD: " + kh.getIDChuyenDe()));
        info.add(new JLabel("Học phí: " + kh.getHocPhi()));
        info.add(new JLabel("Thời lượng: " + kh.getThoiLuong()));

        add(info, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getParent());
    }
}
