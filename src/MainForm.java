
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class MainForm
{
	JFrame fr;
	JLabel l1;
	JButton b1,b1p2,b1p,b1g1,b1g2,refresh;
	JButton b2,b2p2,b2p,b2g1,b2g2;
	JButton b3,b3p2,b3p,b3g1,b3g2;
	JButton b4,b4p2,b4p,b4g1,b4g2;
	JButton b5,b5p2,b5p,b5g1,b5g2;
	JButton b6,b6p2,b6p,b6g1,b6g2;
	JMenuBar menubar;
	JMenu setting,resetApplication;
	JMenuItem setParameter,about,setPassword,exit,password,database;
	String sub[] = new String[7];
	String date[] = new String[7];
	String attend[] = new String[7];
	int present[] = new int[7];
	int absent[] = new int[7];
	int percent[] = new int[7];
	String advice[] = new String[7];
	double pretemp, abtemp, percal;      //advice temp variables
	int count=0;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public void Retrive_subject_names() throws SQLException{
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


	public void Daily_check_method() throws SQLException{
		con=new ConnectionManager().makeConnection();
		String query="select * from daily_check WHERE subject_Id = ?";
		pstmt=con.prepareStatement(query);
		for(int i = 0; i<6; i++){
			count=0;
			pstmt.setInt(1, i+1);
			rs = pstmt.executeQuery();
			rs.next();
			date[i+1]=rs.getString("Date");
			attend[i+1]=rs.getString("Attendance");
			pretemp = present[i+1]=rs.getInt("Present");
			abtemp = absent[i+1]=rs.getInt("Absent");
			percent[i+1]=rs.getInt("Percentage");
			percal = ( pretemp / (abtemp + pretemp) ) * 100;

			if(percal > 75.2)
			{
				while(percal > 75.0){
					abtemp++;
					percal = ( pretemp / (abtemp + pretemp) ) * 100;
					count++;

				}
				if(percal >=75.0)
					advice[i+1] = "ADVICE : You can bunk "+count+" class";
				else if(--count ==0)
					advice[i+1] = "ADVICE : Go in next class";
				else
					advice[i+1] = "ADVICE : You can bunk "+(count)+" class";
			}

			else if(percal < 75.0)
			{
				while(percal < 75.0){
					pretemp++;
					percal = ( pretemp / (abtemp + pretemp) ) * 100;
					count++;
				}
				advice[i+1] = "ADVICE : You have to attend "+count+" class";
			}

			else if(sub[i+1]=="SET SUBJECT" || (Double.isNaN(percal) && abtemp==0.0 && pretemp==0.0) )
				advice[i+1] = "ADVICE : Set Subject first";
			else
			{
				advice[i+1] = "ADVICE : Go in next class";
			}

		}
		pstmt.close();
		return;
	}


	MainForm() throws SQLException
	{
		fr=new JFrame("Self Attendance Register");
		l1=new JLabel("SELF  ATTENDANCE  REGISTER");
		Font f = new Font(Font.MONOSPACED, Font.BOLD, 50);

		/********************* Menu Bar Starts *********************************/

		menubar =new JMenuBar();
		fr.setJMenuBar(menubar);

		setting = new JMenu("Setting");
		menubar.add(setting);

		resetApplication = new JMenu("Reset Application");
		menubar.add(resetApplication);

		password = new JMenuItem("Reset pasword");
		resetApplication.add(password);

		database = new JMenuItem("Reset Database");
		resetApplication.add(database);

		setPassword = new JMenuItem("Set Password");
		setting.add(setPassword);

		setParameter = new JMenuItem("Set Subjects");
		setting.add(setParameter);

		about = new JMenuItem("About");
		setting.add(about);

		exit = new JMenuItem("Exit");
		setting.add(exit);

		password.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CRUDOperations c=new CRUDOperations();
				c.add();
			}
		}		
				);

		database.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					int n = JOptionPane.showConfirmDialog(null,"Do you really want to reset subject database?",
							"Confirmation Dialog",JOptionPane.YES_NO_OPTION);

					if(n==JOptionPane.YES_OPTION){
						new	ResetDatabase();
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}		
				);

		setPassword.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new SetPassword();
			}
		}		
				);

		setParameter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new SetParameter();
			}
		}		
				);

		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
				);

		about.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new About();
			}
		}		
				);

		/********************* Menu Bar End*************************************/

		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("EEEE, dd MMMM YYYY");
		@SuppressWarnings("unused")
		String dt = ft.format(dNow);


		l1.setFont(f);
		Retrive_subject_names();
		Daily_check_method();

		refresh=new JButton("REFRESH PAGE DATA");
		b1=new JButton(sub[1].toUpperCase());
		b1p=new JButton(percent[1]+"%");
		b1p2=new JButton(advice[1]);
		b1g1=new JButton("Date : " + date[1]);
		b1g2=new JButton(attend[1]);

		b2=new JButton(sub[2].toUpperCase());
		b2p=new JButton(percent[2]+"%");
		b2p2=new JButton(advice[2]);
		b2g1=new JButton("Date : " + date[2]);
		b2g2=new JButton(attend[2]);

		b3=new JButton(sub[3].toUpperCase());
		b3p=new JButton(percent[3]+"%");
		b3p2=new JButton(advice[3]);
		b3g1=new JButton("Date : " + date[3]);
		b3g2=new JButton(attend[3]);

		b4=new JButton(sub[4].toUpperCase());
		b4p=new JButton(percent[4]+"%");
		b4p2=new JButton(advice[4]);
		b4g1=new JButton("Date : " + date[4]);
		b4g2=new JButton(attend[4]);

		b5=new JButton(sub[5].toUpperCase());
		b5p=new JButton(percent[5]+"%");
		b5p2=new JButton(advice[5]);
		b5g1=new JButton("Date : " + date[5]);
		b5g2=new JButton(attend[5]);

		b6=new JButton(sub[6].toUpperCase());
		b6p=new JButton(percent[6]+"%");
		b6p2=new JButton(advice[6]);
		b6g1=new JButton("Date : " + date[6]);
		b6g2=new JButton(attend[6]);

		Container contentpane=fr.getContentPane();
		contentpane.setLayout(null);

		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());        // Determines users screen dimensions
		int ySize = ((int) tk.getScreenSize().getHeight());       // Determines users screen dimensions
		fr.setSize(xSize, ySize);
		fr.setVisible(true);


		l1.setBounds(300,20,1200,100);
		refresh.setBounds(40, 30, 170, 30);
		refresh.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		refresh.setForeground(Color.white);
		refresh.setBackground(Color.darkGray);
		///////////////////////////////////////////////////

		b1.setBounds(210,200,400,70);
		b1.setFont(new Font("Arial", Font.BOLD, 20));
		b1.setForeground(Color.white);
		b1.setBackground(Color.blue);
		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		b1p.setBounds(90,200,120,100);
		b1p.setFont(new Font("Arial", Font.BOLD, 33));
		b1p.setForeground(Color.white);
		b1p.setBackground(Color.black);

		b1p2.setBounds(90,300,520,20);
		b1p2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		b1p2.setForeground(Color.black);
		b1p2.setBackground(Color.yellow);

		b1g1.setBounds(210,270,270,30);
		b1g1.setFont(new Font("Arial", Font.BOLD, 15));
		b1g1.setForeground(Color.white);
		b1g1.setBackground(Color.black);

		b1g2.setBounds(480,270,130,30);
		b1g2.setFont(new Font("Arial", Font.BOLD, 15));
		if(attend[1].equals("PRESENT"))
		{
			b1g2.setForeground(Color.black);
			b1g2.setBackground(Color.green);
		}
		else
		{
			b1g2.setForeground(Color.white);
			b1g2.setBackground(Color.red);
		}
		/////////////////////////////////////////////////////

		b2.setBounds(850,200,400,70);
		b2.setFont(new Font("Arial", Font.BOLD, 20));
		b2.setBackground(Color.blue);
		b2.setForeground(Color.white);
		b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		b2p.setBounds(730,200,120,100);
		b2p.setFont(new Font("Arial", Font.BOLD, 33));
		b2p.setForeground(Color.white);
		b2p.setBackground(Color.black);

		b2p2.setBounds(730,300,520,20);
		b2p2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		b2p2.setForeground(Color.black);
		b2p2.setBackground(Color.yellow);

		b2g1.setBounds(850,270,270,30);
		b2g1.setFont(new Font("Arial", Font.BOLD, 15));
		b2g1.setForeground(Color.white);
		b2g1.setBackground(Color.black);

		b2g2.setBounds(1120,270,130,30);
		b2g2.setFont(new Font("Arial", Font.BOLD, 15));
		if(attend[2].equals("PRESENT"))
		{
			b2g2.setForeground(Color.black);
			b2g2.setBackground(Color.green);
		}
		else
		{
			b2g2.setForeground(Color.white);
			b2g2.setBackground(Color.red);
		}

		//////////////////////////////////////////////////////

		b3.setBounds(210,360,400,70);
		b3.setFont(new Font("Arial", Font.BOLD, 20));
		b3.setBackground(Color.blue);
		b3.setForeground(Color.white);
		b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		b3p.setBounds(90,360,120,100);
		b3p.setFont(new Font("Arial", Font.BOLD, 33));
		b3p.setForeground(Color.white);
		b3p.setBackground(Color.black);

		b3p2.setBounds(90,460,520,20);
		b3p2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		b3p2.setForeground(Color.black);
		b3p2.setBackground(Color.yellow);

		b3g1.setBounds(210,430,270,30);
		b3g1.setFont(new Font("Arial", Font.BOLD, 15));
		b3g1.setForeground(Color.white);
		b3g1.setBackground(Color.black);

		b3g2.setBounds(480,430,130,30);
		b3g2.setFont(new Font("Arial", Font.BOLD, 15));
		if(attend[3].equals("PRESENT"))
		{
			b3g2.setForeground(Color.black);
			b3g2.setBackground(Color.green);
		}
		else
		{
			b3g2.setForeground(Color.white);
			b3g2.setBackground(Color.red);
		}
		//////////////////////////////////////////////////////

		b4.setBounds(850,360,400,70);
		b4.setFont(new Font("Arial", Font.BOLD, 20));
		b4.setBackground(Color.blue);
		b4.setForeground(Color.white);
		b4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		b4p.setBounds(730,360,120,100);
		b4p.setFont(new Font("Arial", Font.BOLD, 33));
		b4p.setForeground(Color.white);
		b4p.setBackground(Color.black);

		b4p2.setBounds(730,460,520,20);
		b4p2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		b4p2.setForeground(Color.black);
		b4p2.setBackground(Color.yellow);

		b4g1.setBounds(850,430,270,30);
		b4g1.setFont(new Font("Arial", Font.BOLD, 15));
		b4g1.setForeground(Color.white);
		b4g1.setBackground(Color.black);

		b4g2.setBounds(1120,430,130,30);
		b4g2.setFont(new Font("Arial", Font.BOLD, 15));
		if(attend[4].equals("PRESENT"))
		{
			b4g2.setForeground(Color.black);
			b4g2.setBackground(Color.green);
		}
		else
		{
			b4g2.setForeground(Color.white);
			b4g2.setBackground(Color.red);
		}
		//////////////////////////////////////////////////////

		b5.setBounds(210,520,400,70);
		b5.setFont(new Font("Arial", Font.BOLD, 20));
		b5.setBackground(Color.blue);
		b5.setForeground(Color.white);
		b5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		b5p.setBounds(90,520,120,100);
		b5p.setFont(new Font("Arial", Font.BOLD, 33));
		b5p.setForeground(Color.white);
		b5p.setBackground(Color.black);

		b5p2.setBounds(90,620,520,20);
		b5p2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		b5p2.setForeground(Color.black);
		b5p2.setBackground(Color.yellow);

		b5g1.setBounds(210,590,270,30);
		b5g1.setFont(new Font("Arial", Font.BOLD, 15));
		b5g1.setForeground(Color.white);
		b5g1.setBackground(Color.black);

		b5g2.setBounds(480,590,130,30);
		b5g2.setFont(new Font("Arial", Font.BOLD, 15));
		if(attend[5].equals("PRESENT"))
		{
			b5g2.setForeground(Color.black);
			b5g2.setBackground(Color.green);
		}
		else
		{
			b5g2.setForeground(Color.white);
			b5g2.setBackground(Color.red);
		}
		////////////////////////////////////////////////////////

		b6.setBounds(850,520,400,70);
		b6.setFont(new Font("Arial", Font.BOLD, 20));
		b6.setBackground(Color.blue);
		b6.setForeground(Color.white);
		b6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		b6p.setBounds(730,520,120,100);
		b6p.setFont(new Font("Arial", Font.BOLD, 33));
		b6p.setForeground(Color.white);
		b6p.setBackground(Color.black);

		b6p2.setBounds(730,620,520,20);
		b6p2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		b6p2.setForeground(Color.black);
		b6p2.setBackground(Color.yellow);

		b6g1.setBounds(850,590,270,30);
		b6g1.setFont(new Font("Arial", Font.BOLD, 15));
		b6g1.setForeground(Color.white);
		b6g1.setBackground(Color.black);

		b6g2.setBounds(1120,590,130,30);
		b6g2.setFont(new Font("Arial", Font.BOLD, 15));
		if(attend[6].equals("PRESENT"))
		{
			b6g2.setForeground(Color.black);
			b6g2.setBackground(Color.green);
		}
		else
		{
			b6g2.setForeground(Color.white);
			b6g2.setBackground(Color.red);
		}
		////////////////////////////////////////////////////////

		contentpane.add(l1);
		contentpane.add(refresh);

		contentpane.add(b1);
		contentpane.add(b1p);
		contentpane.add(b1p2);
		contentpane.add(b1g1);
		contentpane.add(b1g2);

		contentpane.add(b2);
		contentpane.add(b2p);
		contentpane.add(b2p2);
		contentpane.add(b2g1);
		contentpane.add(b2g2);

		contentpane.add(b3);
		contentpane.add(b3p);
		contentpane.add(b3p2);
		contentpane.add(b3g1);
		contentpane.add(b3g2);

		contentpane.add(b4);
		contentpane.add(b4p);
		contentpane.add(b4p2);
		contentpane.add(b4g1);
		contentpane.add(b4g2);

		contentpane.add(b5);
		contentpane.add(b5p);
		contentpane.add(b5p2);
		contentpane.add(b5g1);
		contentpane.add(b5g2);

		contentpane.add(b6);
		contentpane.add(b6p);
		contentpane.add(b6p2);
		contentpane.add(b6g1);
		contentpane.add(b6g2);

		refresh.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				fr.setVisible(false);
				fr.dispose();
				try {
					new MainForm();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});  

		b1.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 

				try {
					new Attendance("subject1",sub[1],date[1]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});  
		b2.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 

				try {
					new Attendance("subject2",sub[2],date[2]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				);  
		b3.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 

				try {
					new Attendance("subject3",sub[3],date[3]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				);  
		b4.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 

				try {
					new Attendance("subject4",sub[4],date[4]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				);
		b5.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 

				try {
					new Attendance("subject5",sub[5],date[5]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				);  
		b6.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 

				try {
					new Attendance("subject6",sub[6],date[6]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				);
	}
	public static void main(String args[]) throws SQLException
	{
		new MainForm();
	}
}