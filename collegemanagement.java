import java.sql.*;
import java.util.Scanner;

public class CollegeManagement {

    static final String DB_URL = "jdbc:mysql://localhost:3306/college";
    static final String USER = "root";
    static final String PASS = "dbms";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            while (true) {
                System.out.println("\n===== COLLEGE MANAGEMENT SYSTEM =====");
                System.out.println("1. View Students");
                System.out.println("2. View Teachers");
                System.out.println("3. View Marks");
                System.out.println("4. Add Student");
                System.out.println("5. Update Student Marks");
                System.out.println("6. Delete Student");
                System.out.println("7. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1: viewStudents(conn); break;
                    case 2: viewTeachers(conn); break;
                    case 3: viewMarks(conn); break;
                    case 4: addStudent(conn, sc); break;
                    case 5: updateMarks(conn, sc); break;
                    case 6: deleteStudent(conn, sc); break;
                    case 7: System.out.println("Exiting..."); return;
                    default: System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewStudents(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM student");
        System.out.println("\n--- Students ---");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
        }
    }

    static void viewTeachers(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM teacher");
        System.out.println("\n--- Teachers ---");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
        }
    }

    static void viewMarks(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM marks");
        System.out.println("\n--- Marks ---");
        while (rs.next()) {
            System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getInt(3));
        }
    }

    static void addStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("ID: ");
        String id = sc.next();
        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("Class: ");
        String cls = sc.next();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO student VALUES (?, ?, ?)");
        ps.setString(1, id);
        ps.setString(2, name);
        ps.setString(3, cls);
        ps.executeUpdate();

        System.out.println("Student Added!");
    }

    static void updateMarks(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Student ID: ");
        String id = sc.next();
        System.out.print("Subject: ");
        String sub = sc.next();
        System.out.print("Marks: ");
        int marks = sc.nextInt();

        PreparedStatement ps = conn.prepareStatement(
                "UPDATE marks SET marks=? WHERE student_id=? AND subject=?");
        ps.setInt(1, marks);
        ps.setString(2, id);
        ps.setString(3, sub);

        System.out.println(ps.executeUpdate() + " row updated");
    }

    static void deleteStudent(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Student ID: ");
        String id = sc.next();

        PreparedStatement ps = conn.prepareStatement("DELETE FROM student WHERE student_id=?");
        ps.setString(1, id);

        System.out.println(ps.executeUpdate() + " row deleted");
    }
}