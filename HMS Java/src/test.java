import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;

public class test implements ActionListener {
    /*JTextField DoctorID1, Name1, Specilist1, Departn1, InpatientID1;
    JButton jb;

    public test() {
        JFrame jf = new JFrame("Doctor Details");
        JPanel jp = new JPanel(new FlowLayout());
        
        JLabel DoctorID = new JLabel("Doctor ID");
        JLabel Name = new JLabel("Name");
        JLabel Specilist = new JLabel("Specialist");
        JLabel DepartmentName = new JLabel("Department Name");
        JLabel InpatientID = new JLabel("Inpatient ID");
        
        DoctorID1 = new JTextField("", 30);
        Name1 = new JTextField("", 30);
        Specilist1 = new JTextField("", 30);
        Departn1 = new JTextField("", 30);
        InpatientID1 = new JTextField("", 30);
        
        jb = new JButton("Submit Data");
        
        jp.add(DoctorID);
        jp.add(DoctorID1);
        jp.add(Name);
        jp.add(Name1);
        jp.add(DepartmentName);
        jp.add(Departn1);
        jp.add(Specilist);
        jp.add(Specilist1);
        jp.add(InpatientID);
        jp.add(InpatientID1);
        jp.add(jb);
        
        jf.add(jp);
        jf.setSize(400, 400);
        jf.setVisible(true);
        
        jb.addActionListener(this);
    }*/
	JFrame f;
	JLabel l1,l2,l3,l4;
	JButton b1,b2,b3,b4;
	test(){
		f=new JFrame("HOME PAGE");
		f.setBackground(Color.WHITE);
		f.setLayout(null);
		
		l1=new JLabel();
		l1.setBounds(0,0,800,570);
		l1.setLayout(null);
		
		ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("D:\\img\\admin\\img1.jpg"));
		Image i1=img.getImage().getScaledInstance(800,570,Image.SCALE_SMOOTH);
		ImageIcon img1=new ImageIcon(i1);
		l1.setIcon(img1);
		
		l2=new JLabel("HOSPITAL MANAGEMENT SYSTEM");
		l2.setBounds(86,340,500,30);
		l2.setFont(new Font("Arial",Font.BOLD,30));
		l2.setForeground(Color.BLACK);
		
		f.add(l1);
		l1.add(l2);
		f.setSize(800,900);
		f.setLocation(300,1000);
		f.setVisible(true);
		f.setResizable(false);}
}
    @Override
    public void actionPerformed(ActionEvent AE) {
        if (AE.getSource() == jb) {
            String DoctorID = DoctorID1.getText();
            String name = Name1.getText();
            String Specialist = Specilist1.getText();
            String DepartmentName = Departn1.getText();
            String InpatientId = InpatientID1.getText();

            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); // Loading Driver
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://D:/HMS.accdb"); // Establishing Connection
                System.out.println("Connected Successfully");

                String query = "INSERT INTO Doctor (DoctorID, Name, Specilist, DepartName, InpatientID) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                
                preparedStatement.setString(1, DoctorID);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, Specialist);
                preparedStatement.setString(4, DepartmentName);
                preparedStatement.setString(5, InpatientId);
                
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Data inserted successfully");
                }
            } catch (Exception e) {
                System.out.println("Error in connection: " + e.getMessage());
            }
        }
    }
}
