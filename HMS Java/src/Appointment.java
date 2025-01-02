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
public class Appointment extends JFrame implements ActionListener {
	JFrame af;
	JLabel data,nl1,nl2;
	JButton Add,View,Search,Back;

	Appointment(){
		JFrame af = new JFrame("Appointments");
		af.setBackground(Color.WHITE);
	    af.setLayout(null);
        
	    nl1=new JLabel();
		nl1.setBounds(0,0,800,570);
		nl1.setLayout(null);
		
		 nl2 = new JLabel("Appointment DETAILS");
	     nl2.setBounds(50,50,350,30);
	   	 nl2.setFont(new Font("Arial",Font.BOLD,25));
	     nl2.setForeground(Color.WHITE);
	     nl1.add(nl2);
		
        ImageIcon img = new ImageIcon("img/admin/img10.jpg");
        Image i1 = img.getImage().getScaledInstance(600, 650, Image.SCALE_SMOOTH);
        ImageIcon img3 = new ImageIcon(i1);
        nl1.setIcon(img3);
    	af.add(nl1);
        
        Add = new JButton(" Add AppData ");
        Add.setBounds(50,200,150,40);
    	Add.setBackground(Color.white);
    	Add.setForeground(Color.black);
    	nl1.add(Add);
    	
        View = new JButton("View All data");
        View.setBounds(50,100,150,40);
        View.setBackground(Color.white);
        View.setForeground(Color.black);
        nl1.add(View);
        
        Search = new JButton("Search ");
    	Search.setBounds(50,150,150,40);
    	Search.setBackground(Color.white);
    	Search.setForeground(Color.black);
    	nl1.add(Search);
    	
    	Back=new JButton("Go to Home");
    	Back.setBounds(50,450,110,30);
    	Back.setBackground(Color.red);
    	Back.setForeground(Color.white);
    	nl1.add(Back);
    	
       data = new JLabel(" ");
       data.setBounds(230,25,350,400);
   	   data.setFont(new Font("Arial",Font.BOLD,13));
     	data.setForeground(Color.white);
     	nl1.add(data);
    
     	Back.addActionListener(this);
        View.addActionListener(this);
        Add.addActionListener(this);
        Search.addActionListener(this);
        af.setSize(600,550);
        af.setLocation(300,100);
        af.setVisible(true);
        af.setResizable(false);
    }
	
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == View) {
            try {
                String res = "<table border=1><tr><th>Appointment ID</th><th>AppDr</th><th>Status</th></tr>";
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Appointment");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String AppointmentID = resultSet.getString("AppointmentID");
                    String AppDr = resultSet.getString("AppDr");
                    String Status = resultSet.getString("Status");
                    res += "<tr><td>" + AppointmentID  + "</td><td>" + AppDr + "</td><td>" + Status + "</td></tr>";
                }
                data.setText("<html>" + res + "</table>"+"</html>");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (AE.getSource() == Search) {
            String name = JOptionPane.showInputDialog(null, "Enter the name of the Appointment Doctor to search");
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a valid doctor name.");
                return;
            }
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Appointment WHERE AppDr LIKE ?");
                preparedStatement.setString(1, name+ "%"); 
                ResultSet resultSet = preparedStatement.executeQuery();

                String rs = "<table border=1><tr><th>Appointment ID</th><th>Appointment Doctor</th><th>Status</th></tr>";
                boolean found = false; 

                while (resultSet.next()) {
                    found = true;
                    String AppointmentID = resultSet.getString("AppointmentID");
                    String AppDr = resultSet.getString("AppDr");
                    String Status = resultSet.getString("Status");

                    rs += "<tr><td>" + AppointmentID + "</td><td>" + AppDr + "</td><td>" + Status + "</td></tr>";
                }
                rs += "</table>";

                if (found) {
                    data.setText("<html>" + rs + "</html>");
                } else {
                    JOptionPane.showMessageDialog(null, "No appointments found for doctor: " + name);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
	} else if (AE.getSource() == Add) {
           new Addapp(); 
        }else if(AE.getSource()==Back) {
        	nl1.setVisible(false);
       	    new Testgui();
       }
    }
}