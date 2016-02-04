import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Welcome
{
	JFrame fr;
	JLabel l0,l1,l2,l3;
	JButton b1,b2,b3,b4,b5,b6;
	String username,password;
	public Welcome()
	{
	fr=new JFrame("WELCOME");
	l0=new JLabel("WELCOME  TO");
	l1=new JLabel("SELF  ATTENDANCE  REGISTER");
	Font f = new Font(Font.MONOSPACED, Font.BOLD, 30);
	l0.setFont(f);
	l1.setFont(f);
	l3=new JLabel("Default Username = admin and password = pass. Please change it at first use. ");
	b1=new JButton("CONTINUE");
	b2=new JButton("EXIT");
	Container contentpane=fr.getContentPane();
	contentpane.setLayout(null);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();				// To Get the screen size(width,height)
    int w = fr.getSize().width;
    int h = fr.getSize().height;
    int x = (dim.width - w) / 2;
    int y = (dim.height - h) / 2;
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.setLocation(x-300, y-250);
	fr.setVisible(true);
	fr.setSize(600,500);
	fr.setResizable(false);

	l0.setBounds(200,50,500,100);
	l1.setBounds(50,130,500,100);
	
	b1.setBounds(100,300,130,40);
	b1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,17));
	b1.setBackground(Color.green);
	b1.setForeground(Color.white);
	b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	
	b2.setBounds(350,300,130,40);
	b2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,17));
	b2.setBackground(Color.red);
	b2.setForeground(Color.white);
	b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	l3.setBounds(5,445,600,30);
	l3.setForeground(Color.red);
	contentpane.add(l0);
	contentpane.add(l1);
	contentpane.add(b1);
	contentpane.add(b2);
	
	contentpane.add(l3);
		b1.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				fr.setVisible(false);
				new LoginForm();
			}
		}
		);  
			b2.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				System.exit(0);
				fr.setVisible(false);
				
			}
		}
		);  
	}
public static void main(String args[])
{
	new Welcome();
}
}	