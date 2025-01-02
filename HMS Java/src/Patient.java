import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
public class Patient extends JFrame implements ActionListener{
	JFrame pf;
	JLabel data,pl1,pl2;
	JButton Add,View,Search;
    Patient(){
		JFrame df = new JFrame("Doctor Information");
		df.setBackground(Color.WHITE);
		df.setLayout(null);
       // df.setLayout(new FlowLayout());
       /* JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.Y_AXIS));
        ButtonPanel.setAlignmentX(50);
        ButtonPanel.setAlignmentY(50);*/
		pl1=new JLabel();
		pl1.setBounds(0,0,800,570);
		pl1.setLayout(null);
		
		pl2 = new JLabel("PATIENT DETAILS");
	     pl2.setBounds(50,50,350,30);
	   	 pl2.setFont(new Font("Arial",Font.BOLD,25));
	     pl2.setForeground(Color.BLACK);
	     pl1.add(pl2);
		
        ImageIcon img = new ImageIcon("img/admin/doctor.jpg");
        Image i1 = img.getImage().getScaledInstance(600, 650, Image.SCALE_SMOOTH);
        ImageIcon img3 = new ImageIcon(i1);
        pl1.setIcon(img3);
    	pf.add(pl1);
    	
        //JPanel ViewPanel = new JPanel();
      //  ViewPanel.setLayout(new BoxLayout(ViewPanel, BoxLayout.Y_AXIS));
        
        Add = new JButton(" Add Doctor Data ");
        Add.setBounds(50,200,150,40);
    	Add.setBackground(Color.black);
    	Add.setForeground(Color.white);
    	pl1.add(Add);
    	
        View = new JButton("View data");
        View.setBounds(50,100,150,40);
        View.setBackground(Color.black);
        View.setForeground(Color.white);
        pl1.add(View);
        
        Search = new JButton("Search ");
    	Search.setBounds(50,150,150,40);
    	Search.setBackground(Color.black);
    	Search.setForeground(Color.white);
    	pl1.add(Search);
    	
       data = new JLabel(" You will see the data here");
       data.setBounds(250,25,350,400);
   	  data.setFont(new Font("Arial",Font.BOLD,20));
     	data.setForeground(Color.BLACK);
     	pl1.add(data);
      /*  ButtonPanel.add(dl);
        ButtonPanel.add(Add);
        ButtonPanel.add(View);
        ButtonPanel.add(Search);*/
        //ViewPanel.add(data);
        //df.add(ButtonPanel);
        //df.add(ViewPanel);
    	
        View.addActionListener(this);
        Add.addActionListener(this);
        Search.addActionListener(this);
        pf.setSize(600,650);
        pf.setLocation(300,100);
        pf.setVisible(true);
        pf.setResizable(false);
    }
	@Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == View) {
            try {
                String res = "<table border=1><tr><th>Patient ID</th><th>Name</th><th>Gender</th></tr>";
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Patient");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String PatientID = resultSet.getString("PatientID");
                    String Name = resultSet.getString("Name");
                    String Gender = resultSet.getString("Gender");
                    res += "<tr><td>" + PatientID + "</td><td>" + Name + "</td><td>" + Gender + "</td></tr>";
                }
                data.setText("<html>" + res + "</table>"+"</html>");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (AE.getSource() == Search) {
            String name = JOptionPane.showInputDialog(null, "Enter the name of the doctor to search");
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Patient WHERE Name LIKE ?");
                preparedStatement.setString(1, "%" + name + "%");
                ResultSet resultSet = preparedStatement.executeQuery();
                String rs = "";
                while (resultSet.next()) {
                    String PatientID = resultSet.getString("PatientID");
                    String Gender = resultSet.getString("Gender");
                    String Name = resultSet.getString("Name");
                    rs += PatientID + " Gender " + Gender + " Name: " + Name + "\n";
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
          //  new test(); // Opens the "test" class for adding new doctors
        }
    }