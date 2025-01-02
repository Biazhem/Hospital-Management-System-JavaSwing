import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;



public class testing implements ActionListener {
    JButton Add, View, Search;
    JLabel data;

    public testing() {
        JFrame jf = new JFrame("Doctor Information");
        jf.setLayout(new FlowLayout());
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.Y_AXIS));
        ButtonPanel.setAlignmentX(50);
        ButtonPanel.setAlignmentY(50);

        JPanel ViewPanel = new JPanel();
        ViewPanel.setLayout(new BoxLayout(ViewPanel, BoxLayout.Y_AXIS));
        Add = new JButton(" Add newdoc ");
        View = new JButton("View data");
        Search = new JButton("Search Doc ");
        data = new JLabel(" You will see the data here");
        ButtonPanel.add(Add);
        ButtonPanel.add(View);
        ButtonPanel.add(Search);
        ViewPanel.add(data);
        jf.add(ButtonPanel);
        jf.add(ViewPanel);
        View.addActionListener(this);
        Add.addActionListener(this);
        Search.addActionListener(this);
        jf.setSize(300, 500);
        jf.setVisible(true);
        jf.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == View) {
            try {
                String res = "<table border=1><tr><td>Doctor ID</td><td>Name</td><td>Specialist</td></tr>";
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Doctor");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String DoctorID = resultSet.getString("DoctorID");
                    String Name = resultSet.getString("Name");
                    String Specialist = resultSet.getString("Specilist");
                    res += "<tr><td>" + DoctorID + "</td><td>" + Name + "</td><td>" + Specialist + "</td></tr>";
                }
                data.setText("<html>" + res + "</html>");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (AE.getSource() == Search) {
            String name = JOptionPane.showInputDialog(null, "Enter the name of the doctor to search");
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Doctor WHERE Name LIKE ?");
                preparedStatement.setString(1, "%" + name + "%");
                ResultSet resultSet = preparedStatement.executeQuery();
                String rs = "";
                while (resultSet.next()) {
                    String DoctorID = resultSet.getString("DoctorID");
                    String Specialist = resultSet.getString("Specilist");
                    String Name = resultSet.getString("Name");
                    rs += DoctorID + " Specilist in " + Specialist + " Name: " + Name + "\n";
                }
                if (!rs.isEmpty()) {
                    JOptionPane.showMessageDialog(null, rs);
                } else {
                    JOptionPane.showMessageDialog(null, "No doctor found with the name: " + name);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (AE.getSource() == Add) {
            new test(); // Opens the "test" class for adding new doctors
        }
    }

    public static void main(String[] args) {
        new testing();
    }
}
