import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Testgui extends JFrame implements ActionListener{
JFrame f;
JLabel l1,l2;
JButton b1,b2,b3,b4;
Testgui(){
	f=new JFrame("HOME PAGE");
	f.setBackground(Color.WHITE);
	f.setLayout(null);
	
	l1=new JLabel();
	l1.setBounds(0,0,800,570);
	l1.setLayout(null);
	
	ImageIcon img = new ImageIcon("img/admin/img4.jpg");
    Image i1 = img.getImage().getScaledInstance(800, 570, Image.SCALE_SMOOTH);
    ImageIcon img3 = new ImageIcon(i1);
    l1.setIcon(img3);
	f.add(l1);
	
	
	l2=new JLabel("HOSPITAL MANAGEMENT SYSTEM");
	l2.setBounds(56,340,500,30);
	l2.setFont(new Font("Arial",Font.BOLD,25));
	l2.setForeground(Color.black);
	
	b1=new JButton("DOCTOR");
	b1.setBounds(50,390,150,40);
	b1.setBackground(Color.black);
	b1.setForeground(Color.white);
	l1.add(b1);
	
	b2=new JButton("INPATIENT");
	b2.setBounds(220,390,150,40);
	b2.setBackground(Color.black);
	b2.setForeground(Color.white);
	l1.add(b2);
	
	b3=new JButton("APPOINTMENT");
	b3.setBounds(50,450,150,40);
	b3.setBackground(Color.black);
	b3.setForeground(Color.white);
	l1.add(b3);
	
	b4=new JButton("BILL RECORD");
	b4.setBounds(220,450,150,40);
	b4.setBackground(Color.black);
	b4.setForeground(Color.white);
	l1.add(b4);
	b1.addActionListener(this);
	b2.addActionListener(this);
	b3.addActionListener(this);
	b4.addActionListener(this);
	
	l1.add(l2);
	f.setSize(800,570);
	f.setLocation(300,100);
	f.setVisible(true);
	f.setResizable(false);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			f.setVisible(false);
			new Doc();
			
		}
		if(e.getSource()==b2) {
			f.setVisible(false);
		    new Inpatient();
		}
		if(e.getSource()==b3) {
			f.setVisible(false);
			new Appointment();
		}
		if(e.getSource()==b4) {
			f.setVisible(false);
			new Bills();
		}
		
	}
	public static void main(String[] args) {
		new Testgui();
	}
}
