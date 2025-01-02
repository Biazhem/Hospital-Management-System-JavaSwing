import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
public class Docdata implements ActionListener{
	JTextField DoctorID1,Name1,Specilist1,InpatientID1,DepartName1;
	JButton jb;
    Docdata()
	{
		JFrame jf=new JFrame("Add Doctor Details");
		JPanel jp=new JPanel();
		
		JLabel l1= new JLabel("REGISTER");
	     l1.setBounds(50,50,350,30);
	     l1.setFont(new Font("Arial",Font.BOLD,25));
	     l1.setForeground(Color.BLACK);
	     jp.add(l1);
		
		jp.setBackground(Color.decode("#319998"));
		JLabel DoctorID=new JLabel("DoctorID");
		DoctorID.setBounds(100, 90, 150, 50);
		DoctorID.setForeground(Color.white);
		JLabel Name=new JLabel("Name");
		Name.setBounds(100, 140, 150, 50);
		Name.setForeground(Color.white);
		JLabel Specilist=new JLabel("Specilist");
		Specilist.setBounds(100, 190, 150, 50);
		Specilist.setForeground(Color.white);
		JLabel InpatientID=new JLabel("InpatientID");
		InpatientID.setBounds(100, 240, 150, 50);
		InpatientID.setForeground(Color.white);
		JLabel DepartName=new JLabel("DepartName");
		DepartName.setBounds(90, 280, 150, 50);
		DepartName.setForeground(Color.white);
		
		DoctorID1 =new JTextField();
		DoctorID1.setBounds(170, 100, 150, 30);
		 Name1 =new JTextField();
		 Name1.setBounds(170, 150, 150, 30);
		 Specilist1 =new JTextField();
		 Specilist1.setBounds(170,200,150,30);
		 InpatientID1 =new JTextField();
		 InpatientID1.setBounds(170,250,150,30);
		 DepartName1 =new JTextField();
		DepartName1.setBounds(170,300,150,30);
		 
		 jb=new JButton("Submit Data");
		 jb.setBounds(100,380,150,40);
		 jb.setBackground(Color.black);
	     jb.setForeground(Color.white);
	     
		jp.add(DoctorID);
		jp.add(DoctorID1);
		jp.add(Name);
		jp.add(Name1);
		jp.add(InpatientID);
		jp.add(InpatientID1);
		jp.add(Specilist);
		jp.add(Specilist1);
		jp.add(DepartName1);
		jp.add(DepartName);
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
			String DoctorId=DoctorID1.getText();
			String name=Name1.getText();
			String Specilis=Specilist1.getText();
			String InpatientId=InpatientID1.getText();
		    String Departname=DepartName1.getText();
		try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb");
            System.out.println("Connected Successfully");
            System.out.print("Insert into Doctor values('"+DoctorId+"','"+name+"','"+Specilis+"','"+Departname+"','"+InpatientId+"')");
            PreparedStatement preparedStatement=connection.prepareStatement("Insert into Doctor(DoctorID,Name,Specilist,DepartName,InpatientID) values('"+DoctorId+"','"+name+"','"+Specilis+"','"+Departname+"','"+InpatientId+"')");;
           
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