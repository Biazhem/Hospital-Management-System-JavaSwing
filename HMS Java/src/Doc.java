import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Doc extends JFrame implements ActionListener {

    JLabel data, dl1, dl2;
    JButton Add, View, Search, Back;

    private static final String DB_URL = "jdbc:ucanaccess://D:/HMS.accdb";

    Doc() {
        setTitle("Doctor Information");
        setLayout(null);
        setSize(600, 550);
        setLocation(300, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dl1 = new JLabel();
        dl1.setBounds(0, 0, 800, 570);
        dl1.setLayout(null);

        dl2 = new JLabel("DOCTOR DETAILS");
        dl2.setBounds(50, 50, 350, 30);
        dl2.setFont(new Font("Arial", Font.BOLD, 25));
        dl2.setForeground(Color.WHITE);
        dl1.add(dl2);

        ImageIcon img = new ImageIcon("img/admin/img10.jpg");
        Image i1 = img.getImage().getScaledInstance(600, 650, Image.SCALE_SMOOTH);
        dl1.setIcon(new ImageIcon(i1));

        View = createButton("View All data", 50, 100);
        Search = createButton("Search", 50, 150);
        Add = createButton("Add Doctor Data", 50, 200);

        Back = new JButton("Go to Home");
        Back.setBounds(50, 450, 110, 30);
        Back.setBackground(Color.RED);
        Back.setForeground(Color.WHITE);
        Back.addActionListener(this);
        dl1.add(Back);

        data = new JLabel(" ");
        data.setBounds(230, 25, 350, 400);
        data.setFont(new Font("Arial", Font.BOLD, 13));
        data.setForeground(Color.WHITE);
        dl1.add(data);

        add(dl1);
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 40);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        dl1.add(btn);
        return btn;
    }

    private Connection getConnection() throws Exception {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        return DriverManager.getConnection(DB_URL);
    }

    public void actionPerformed(ActionEvent AE) {

        if (AE.getSource() == View) {
            viewDoctors();

        } else if (AE.getSource() == Search) {
            searchDoctor();

        } else if (AE.getSource() == Add) {
            new Docdata();

        } else if (AE.getSource() == Back) {
            dl1.setVisible(false);
            new Testgui();
        }
    }

    private void viewDoctors() {
        StringBuilder res = new StringBuilder(
            "<table border=1><tr><th>Doctor ID</th><th>Name</th><th>Specialist</th><th>DepartName</th></tr>"
        );

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Doctor");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                res.append("<tr>")
                   .append("<td>").append(rs.getString("DoctorID")).append("</td>")
                   .append("<td>").append(rs.getString("Name")).append("</td>")
                   .append("<td>").append(rs.getString("Specilist")).append("</td>")
                   .append("<td>").append(rs.getString("DepartName")).append("</td>")
                   .append("</tr>");
            }

            data.setText("<html>" + res + "</table></html>");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void searchDoctor() {
        String name = JOptionPane.showInputDialog(null, "Enter the name of the Dr to search");

        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid doctor name.");
            return;
        }

        StringBuilder rsText = new StringBuilder(
            "<table border=1><tr><th>Doctor ID</th><th>Name</th><th>Specialist</th><th>Department</th></tr>"
        );

        boolean found = false;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Doctor WHERE Name LIKE ?")) {

            ps.setString(1, name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                found = true;
                rsText.append("<tr>")
                      .append("<td>").append(rs.getString("DoctorID")).append("</td>")
                      .append("<td>").append(rs.getString("Name")).append("</td>")
                      .append("<td>").append(rs.getString("Specilist")).append("</td>")
                      .append("<td>").append(rs.getString("DepartName")).append("</td>")
                      .append("</tr>");
            }

            rsText.append("</table>");

            if (found) {
                data.setText("<html>" + rsText + "</html>");
            } else {
                JOptionPane.showMessageDialog(null, "No doctor found with the name: " + name);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
