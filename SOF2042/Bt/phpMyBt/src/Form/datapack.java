package Form;
import java.awt.*;
import javax.swing.*;

public class datapack extends JFrame {
    private JPanel navBar;
    private JPanel mainContent;
    private JPanel footer;
    
    public datapack() {
        initComponents();
    }
    
    private void initComponents() {
        // Set frame properties
        setTitle("Course Registration");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main layout
        setLayout(new BorderLayout());
        
        // Initialize navbar
        navBar = new JPanel();
        navBar.setBackground(new Color(51, 51, 51));
        navBar.setPreferredSize(new Dimension(800, 50));
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        // Add menu items to navbar
        JButton homeBtn = new JButton("Home");
        JButton coursesBtn = new JButton("Courses");
        JButton profileBtn = new JButton("Profile");
        navBar.add(homeBtn);
        navBar.add(coursesBtn);
        navBar.add(profileBtn);
        
        // Initialize main content
        mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        
        // Initialize footer
        footer = new JPanel();
        footer.setBackground(new Color(51, 51, 51));
        footer.setPreferredSize(new Dimension(800, 30));
        footer.add(new JLabel("© 2024 Course Registration System"));
        
        // Add components to frame
        add(navBar, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new datapack().setVisible(true);
        });
    }
}


