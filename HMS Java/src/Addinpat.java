import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
public class Addinpat implements ActionListener{
	JTextField InpatientID1,AdmitDate1,DischargeDate1,Status1;
	JButton jb;
	
    Addinpat()
	{
		JFrame jf=new JFrame("Add INpatient Details");
		JPanel jp=new JPanel();
		
		JLabel l1= new JLabel("REGISTER");
	     l1.setBounds(50,50,350,30);
	     l1.setFont(new Font("Arial",Font.BOLD,25));
	     l1.setForeground(Color.BLACK);
	     jp.add(l1);
		
		jp.setBackground(Color.decode("#319998"));
		JLabel InpatientID=new JLabel("InpatientID");
		InpatientID.setBounds(100, 90, 150, 50);
		InpatientID.setForeground(Color.white);
		JLabel AdmitDate =new JLabel("AdmitDate");
		AdmitDate.setBounds(100, 140, 150, 50);
		AdmitDate.setForeground(Color.white);
		JLabel DischargeDate=new JLabel("DischargeDate");
		DischargeDate.setBounds(80, 190, 150, 50);
		DischargeDate.setForeground(Color.white);
		JLabel Status=new JLabel("Status");
		Status.setBounds(100, 240, 150, 50);
		Status.setForeground(Color.white);
		
		InpatientID1 =new JTextField();
		InpatientID1.setBounds(170, 100, 150, 30);
		AdmitDate1 =new JTextField();
		AdmitDate1.setBounds(170, 150, 150, 30);
		 DischargeDate1 =new JTextField();
		 DischargeDate1.setBounds(170,200,150,30);
		 Status1 =new JTextField();
		 Status1.setBounds(170,250,150,30);
		 
		 jb=new JButton("Submit Data");
		 jb.setBounds(100,300,150,40);
		 jb.setBackground(Color.black);
	     jb.setForeground(Color.white);
	     
		jp.add(InpatientID);
		jp.add(InpatientID1);
		jp.add(AdmitDate);
		jp.add(AdmitDate1);
		jp.add(DischargeDate);
		jp.add(DischargeDate1);
		jp.add(Status);
		jp.add(Status1);
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
			String InpatientId=InpatientID1.getText();
			String Admitdate=AdmitDate1.getText();
			String DischargeDate=DischargeDate1.getText();
			String status=Status1.getText();
		
		try{
			
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
            System.out.println("Connected Successfully");
            System.out.print("Insert into Inpatient values('"+InpatientId+"','"+Admitdate+"','"+DischargeDate+"','"+status+"')");
            PreparedStatement preparedStatement=connection.prepareStatement("Insert into Inpatient(InpatientID,AdmitDate,DischargeDate,Status) values('"+InpatientId+"','"+Admitdate+"','"+DischargeDate+"','"+status+"')");;
           
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