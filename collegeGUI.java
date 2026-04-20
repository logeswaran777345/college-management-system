import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CollegeGUI {

    static final String DB_URL = "jdbc:mysql://localhost:3306/college";
    static final String USER = "root";
    static final String PASS = "dbms";

    JFrame frame;

    public CollegeGUI() {
        frame = new JFrame("College Management System");
        frame.setSize(900, 500);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Students", studentPanel());

        frame.add(tabs);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JPanel studentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Name", "Class"}, 0);
        JTable table = new JTable(model);

        JButton load = new JButton("Load");

        load.addActionListener(e -> {
            model.setRowCount(0);
            try (Connection c = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement s = c.createStatement()) {

                ResultSet rs = s.executeQuery("SELECT * FROM student");
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3)
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        panel.add(new JScrollPane(table));
        panel.add(load, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        new CollegeGUI();
    }
}