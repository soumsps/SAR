
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ResetDatabase
{
	JFrame fr;
	JLabel l1;
	JButton b1,b2,b3,b4,b5,b6;
	String sub[] = new String[7];
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
    public void Retrive_subject_names() throws SQLException{
    	//CRUDOperations con = new CRUDOperations();
    	 con=new ConnectionManager().makeConnection();
		String query="select * from subject_name WHERE id = ?";
			pstmt=con.prepareStatement(query);
			for(int i = 0; i<6; i++){
				pstmt.setInt(1, i+1);
				rs = pstmt.executeQuery();
				rs.next();
				sub[i+1]=rs.getString("sub_name");
			}
			pstmt.close();
			return;
			
	}
	
	
	
	ResetDatabase() throws SQLException
	{
	fr=new JFrame("Reset Database");
	l1=new JLabel("RESET  DATABASE  OF  SUBJECTS");
	Font f = new Font("Arial", Font.BOLD, 20);
	
	Retrive_subject_names();
	l1.setFont(f);
	b1=new JButton(sub[1].toUpperCase());
	b2=new JButton(sub[2].toUpperCase());
	b3=new JButton(sub[3].toUpperCase());
	b4=new JButton(sub[4].toUpperCase());
	b5=new JButton(sub[5].toUpperCase());
	b6=new JButton(sub[6].toUpperCase());
	Container contentpane=fr.getContentPane();
	contentpane.setLayout(null);
	
	fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();				// To Get the screen size(width,height)
    // calculate the new location of the window
    int w = fr.getSize().width;
    int h = fr.getSize().height;
    int x = (dim.width - w) / 2;
    int y = (dim.height - h) / 2;
    fr.setLocation(x-300, y-125);
	fr.setSize(595, 240);
	fr.setVisible(true);
	
	
	
	
	l1.setBounds(130,10,400,20);
    
	b1.setBounds(10,60,270,35);
    b1.setFont(new Font("Arial", Font.BOLD, 14));
    b1.setForeground(Color.white);
    b1.setBackground(Color.blue);
    b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
    b2.setBounds(300,60,270,35);
	b2.setFont(new Font("Arial", Font.BOLD, 14));
	b2.setBackground(Color.blue);
	b2.setForeground(Color.white);
	b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
	b3.setBounds(10,105,270,35);
	b3.setFont(new Font("Arial", Font.BOLD, 14));
	b3.setBackground(Color.blue);
	b3.setForeground(Color.white);
	b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
	b4.setBounds(300,105,270,35);
	b4.setFont(new Font("Arial", Font.BOLD, 14));
	b4.setBackground(Color.blue);
	b4.setForeground(Color.white);
	b4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
	b5.setBounds(10,150,270,35);
	b5.setFont(new Font("Arial", Font.BOLD, 14));
	b5.setBackground(Color.blue);
	b5.setForeground(Color.white);
	b5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
	b6.setBounds(300,150,270,35);
	b6.setFont(new Font("Arial", Font.BOLD, 14));
	b6.setBackground(Color.blue);
	b6.setForeground(Color.white);
	b6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
	contentpane.add(l1);
	contentpane.add(b1);
	contentpane.add(b2);
	contentpane.add(b3);
	contentpane.add(b4);
	contentpane.add(b5);
	contentpane.add(b6);
		b1.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				CRUDOperations c=new CRUDOperations();  
				  c.Reset_Subjects(1,sub[1],"subject1");
				  
				  fr.setVisible(false);
					try {
						new ResetDatabase();
					} catch (SQLException e) {
						
						e.printStackTrace();
					} 
				
			}
		}
		);  
			b2.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				CRUDOperations c=new CRUDOperations();  
				  c.Reset_Subjects(2,sub[2],"subject2");
				  
				  fr.setVisible(false);
					try {
						new ResetDatabase();
					} catch (SQLException e) {
						
						e.printStackTrace();
					} 
			}
		}
		);  
		b3.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				CRUDOperations c=new CRUDOperations();  
				  c.Reset_Subjects(3,sub[3],"subject3");
				  
				  fr.setVisible(false);
					try {
						new ResetDatabase();
					} catch (SQLException e) {
						
						e.printStackTrace();
					} 
			}
		}
		);  
		b4.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				CRUDOperations c=new CRUDOperations();  
				  c.Reset_Subjects(4,sub[4],"subject4");
				  
				  fr.setVisible(false);
					try {
						new ResetDatabase();
					} catch (SQLException e) {
						
						e.printStackTrace();
					} 
			}
		}
		);
		b5.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				CRUDOperations c=new CRUDOperations();  
				  c.Reset_Subjects(5,sub[5],"subject5");
				  
				  fr.setVisible(false);
					try {
						new ResetDatabase();
					} catch (SQLException e) {
						
						e.printStackTrace();
					} 
			}
		}
		);  
		b6.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
			  CRUDOperations c=new CRUDOperations();  
			  c.Reset_Subjects(6,sub[6],"subject6");
			  
			  fr.setVisible(false);
				try {
					new ResetDatabase();
				} catch (SQLException e) {
					
					e.printStackTrace();
				} 
				
				}
		}
		);
	}
	
	
	
	
public static void main(String args[]) throws SQLException
{
	new ResetDatabase();
}
}