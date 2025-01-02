import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
public class Addapp implements ActionListener{
	JTextField AppointmentID1,PatientID1,AppDr1,Status1;
	JButton add;
	
    Addapp()
	{
		JFrame jf=new JFrame("Add Appointment Details");
		JPanel jp=new JPanel();
		
		JLabel l1= new JLabel("REGISTER");
	     l1.setBounds(50,50,350,30);
	     l1.setFont(new Font("Arial",Font.BOLD,25));
	     l1.setForeground(Color.BLACK);
	     jp.add(l1);
		
		jp.setBackground(Color.decode("#319998"));
		JLabel AppointmentID=new JLabel("AppointmentID");
		AppointmentID.setBounds(80, 90, 150, 50);
		AppointmentID.setForeground(Color.white);
		JLabel PatientID =new JLabel("PatientID");
		PatientID.setBounds(100, 140, 150, 50);
		PatientID.setForeground(Color.white);
		JLabel AppDr=new JLabel("AppointmentDr");
		AppDr.setBounds(80, 190, 150, 50);
		AppDr.setForeground(Color.white);
		JLabel Status=new JLabel("Status");
		Status.setBounds(100, 240, 150, 50);
		Status.setForeground(Color.white);
		
		AppointmentID1 =new JTextField();
		AppointmentID1.setBounds(170, 100, 150, 30);
		 PatientID1 =new JTextField();
		 PatientID1.setBounds(170, 150, 150, 30);
		 AppDr1 =new JTextField();
		 AppDr1.setBounds(170,200,150,30);
		 Status1 =new JTextField();
		 Status1.setBounds(170,250,150,30);
		 
		 add=new JButton("Submit Data");
		 add.setBounds(100,300,150,40);
		 add.setBackground(Color.black);
	     add.setForeground(Color.white);
	     
		jp.add(AppointmentID);
		jp.add(AppointmentID1);
		jp.add(PatientID);
		jp.add(PatientID1);
		jp.add(AppDr);
		jp.add(AppDr1);
		jp.add(Status);
		jp.add(Status1);
		jp.setLayout(null);
		jp.add(add);
		jf.add(jp);
		jf.setLocation(300,100);
		jf.setSize(500,550);
		jf.setVisible(true);
		
	add.addActionListener(this);	
	}
	public void actionPerformed(ActionEvent AE)
	{
		if (AE.getSource()==add)
		{
			String AppointmentId=AppointmentID1.getText();
			String PatientId=PatientID1.getText();
			String Appdr=AppDr1.getText();
			String status=Status1.getText();
		
		try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
            System.out.println("Connected Successfully");
            System.out.print("Insert into Appointment values("+AppointmentId+",'"+PatientId+"',"+Appdr+","+status+")");
            PreparedStatement preparedStatement=connection.prepareStatement("Insert into Appointment(AppointmentID,PatientID,AppDr,Status) values('"+AppointmentId+"','"+PatientId+"','"+Appdr+"','"+status+"')");;
           
            int c= preparedStatement.executeUpdate();
            if(c>0)
            System.out.print("Data inserted successfully");
                 
	     }
            catch(Exception e){
            System.out.println("Error in connection");

        }
		}
	}

}