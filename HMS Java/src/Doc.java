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

public class Doc extends JFrame implements ActionListener{ 
	JFrame df;
	JLabel data,dl1,dl2;
	JButton Add,View,Search,Back;
	Doc(){
		JFrame df = new JFrame("Doctor Information");
		df.setBackground(Color.WHITE);
		df.setLayout(null);
       
       
		dl1=new JLabel();
		dl1.setBounds(0,0,800,570);
		dl1.setLayout(null);
		
		 dl2 = new JLabel("DOCTOR DETAILS");
	     dl2.setBounds(50,50,350,30);
	   	 dl2.setFont(new Font("Arial",Font.BOLD,25));
	     dl2.setForeground(Color.white);
	     dl1.add(dl2);
		
        ImageIcon img = new ImageIcon("img/admin/img10.jpg");
        Image i1 = img.getImage().getScaledInstance(600, 650, Image.SCALE_SMOOTH);
        ImageIcon img3 = new ImageIcon(i1);
        dl1.setIcon(img3);
    	df.add(dl1);
    	
        
        Add = new JButton(" Add Doctor Data ");
        Add.setBounds(50,200,150,40);
    	Add.setBackground(Color.black);
    	Add.setForeground(Color.white);
    	dl1.add(Add);
    	
        View = new JButton("View All data");
        View.setBounds(50,100,150,40);
        View.setBackground(Color.black);
        View.setForeground(Color.white);
        dl1.add(View);
        
        Search = new JButton("Search ");
    	Search.setBounds(50,150,150,40);
    	Search.setBackground(Color.black);
    	Search.setForeground(Color.white);
    	dl1.add(Search);
    	
    	Back=new JButton("Go to Home");
    	Back.setBounds(50,450,110,30);
    	Back.setBackground(Color.red);
    	Back.setForeground(Color.white);
    	dl1.add(Back);
    	
       data = new JLabel(" ");
       data.setBounds(230,25,350,400);
   	   data.setFont(new Font("Arial",Font.BOLD,13));
     	data.setForeground(Color.white);
     	dl1.add(data);
     
    	df.add(dl1);
        View.addActionListener(this);
        Add.addActionListener(this);
        Search.addActionListener(this);
        Back.addActionListener(this);
        df.setSize(600,550);
        df.setLocation(300,100);
        df.setVisible(true);
        df.setResizable(false);
    }

    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == View) {
            try {
                String res = "<table border=1><tr><th>Doctor ID</th><th>Name</th><th>Specialist</th><th>DepartName</th></tr>";
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Doctor");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String DoctorID = resultSet.getString("DoctorID");
                    String Name = resultSet.getString("Name");
                    String Specialist = resultSet.getString("Specilist");
                    String Departname=resultSet.getString("DepartName");
                    res += "<tr><td>" + DoctorID + "</td><td>" + Name + "</td><td>" + Specialist + "</td><td>" + Departname + "</td></tr>";
                }
                data.setText("<html>" + res + "</table>"+"</html>");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (AE.getSource() == Search) {
            String name = JOptionPane.showInputDialog(null, "Enter the name of the Dr to search");
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid doctor name.");
                return;
            }
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Doctor WHERE Name LIKE ?");
                preparedStatement.setString(1,  name + "%"); 
                ResultSet resultSet = preparedStatement.executeQuery();

                String rs = new String("<table border=1><tr><th>Doctor ID</th><th>Name</th><th>Specialist</th><th>Department</th></tr>");
                boolean found = false;

                while (resultSet.next()) {
                    found = true;
                    String DoctorID = resultSet.getString("DoctorID");
                    String Specialist = resultSet.getString("Specilist");
                    String Name = resultSet.getString("Name");
                    String Departname = resultSet.getString("DepartName");
                    rs+= "<tr><td>" + DoctorID + "</td><td>" + Name + "</td><td>" + Specialist + "</td><td>" + Departname + "</td></tr>";
                }
                rs += "</table>";

                if (found) {
                    data.setText("<html>" + rs + "</html>");
                } else {
                    JOptionPane.showMessageDialog(null, "No doctor found with the name: " + name);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (AE.getSource() == Add) {
            new Docdata(); 
        }else if (AE.getSource() == Back) {
          
           dl1.setVisible(false);
           new Testgui();
            
        }   
	}

}