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
public class Addbill implements ActionListener{
	JTextField BillID1,Payment1,PatientID1,BillingDate1;
	JButton jb;
	
    Addbill()
	{
		JFrame jf=new JFrame("Add APPointment Details");
		JPanel jp=new JPanel();
		
		JLabel l1= new JLabel("REGISTER");
	     l1.setBounds(50,50,350,30);
	     l1.setFont(new Font("Arial",Font.BOLD,25));
	     l1.setForeground(Color.BLACK);
	     jp.add(l1);
		
		jp.setBackground(Color.decode("#319998"));
		JLabel BillID=new JLabel("BillID");
		BillID.setBounds(100, 90, 150, 50);
		BillID.setForeground(Color.white);
		JLabel Payment =new JLabel("Payment");
		Payment.setBounds(100, 140, 150, 50);
		Payment.setForeground(Color.white);
		JLabel PatientID=new JLabel("PatientID");
		PatientID.setBounds(100, 190, 150, 50);
		PatientID.setForeground(Color.white);
		JLabel BillingDate=new JLabel("BillingDate");
		BillingDate.setBounds(100, 240, 150, 50);
		BillingDate.setForeground(Color.white);
		
		BillID1 =new JTextField();
		BillID1.setBounds(170, 100, 150, 30);
		Payment1 =new JTextField();
		Payment1.setBounds(170, 150, 150, 30);
		PatientID1 =new JTextField();
		PatientID1.setBounds(170,200,150,30);
		BillingDate1 =new JTextField();
		BillingDate1.setBounds(170,250,150,30);
		 
		 jb=new JButton("Submit Data");
		 jb.setBounds(100,300,150,40);
		 jb.setBackground(Color.black);
	     jb.setForeground(Color.white);
	     
		jp.add(BillID);
		jp.add(BillID1);
		jp.add(Payment);
		jp.add(Payment1);
		jp.add(PatientID);
		jp.add(PatientID1);
		jp.add(BillingDate);
		jp.add(BillingDate1);
		jp.setLayout(null);
		jp.add(jb);
		jf.add(jp);
		jf.setLocation(300,100);
		jf.setSize(500,550);
		jf.setVisible(true);
		
	jb.addActionListener(this);	
	}
	public void actionPerformed(ActionEvent AE)
	{
		if (AE.getSource()==jb)
		{
			String BillId=BillID1.getText();
			String payment=Payment1.getText();
			String PatientId=PatientID1.getText();
			String billingDate=BillingDate1.getText();
		
		try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
            System.out.println("Connected Successfully");
            System.out.print("Insert into Bills values("+BillId+",'"+payment+"',"+PatientId+","+billingDate+")");
            PreparedStatement preparedStatement=connection.prepareStatement("Insert into Bills(BillID,Payment,PatientID,BillingDate) values('"+BillId+"','"+payment+"','"+PatientId+"','"+billingDate+"')");;
           
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