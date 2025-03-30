import java.awt.*;
import javax.swing.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý khóa học");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        String[] columnNames = {"Mã KH", "Tên khóa học", "Thời lượng", "Học phí"};
        Object[][] data = {
            {"KH01", "Java cơ bản", "30 giờ", "1.500.000đ"},
            {"KH02", "SQL Server", "24 giờ", "1.200.000đ"},
            {"KH03", "Lập trình Web", "36 giờ", "2.000.000đ"}
        };

        
        JTable table = new JTable(data, columnNames);
        
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        
        frame.add(scrollPane, BorderLayout.CENTER);
        
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}