
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class SetPassword
{
	JFrame fr;
	JLabel l1,l2,l7;
	JTextField t1,t2;
	JButton b1;
	//String username,password;
	SetPassword()
	{
		fr=new JFrame("SET PASSWORD");
		l7=new JLabel("Set Credentials");
		l1=new JLabel("User Name");
		l2=new JLabel("Password");


		t1=new JTextField();
		t2=new JTextField();

		Container contentpane=fr.getContentPane();
		contentpane.setLayout(null);
		b1=new JButton("SET");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();				// To Get the screen size(width,height)
		// calculate the new location of the window
		int w = fr.getSize().width;
		int h = fr.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		fr.setLayout(null);
		fr.setLocation(x-250, y-125);
		fr.setVisible(true);
		fr.setSize(500,250);
		fr.setResizable(false);

		l7.setBounds(180,10,250,30);
		l7.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
		l1.setBounds(40,50,220,30);
		t1.setBounds(200,50,220,25);
		l2.setBounds(40,90,220,30);
		t2.setBounds(200,90,220,25);
		b1.setBounds(200,150,100,25);
		b1.setBackground(Color.blue);
		b1.setForeground(Color.white);
		b1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		contentpane.add(l1);
		contentpane.add(t1);
		contentpane.add(l2);
		contentpane.add(t2);
		contentpane.add(l7);
		contentpane.add(b1);

		b1.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent ae)
			{ 

				String username=t1.getText();
				String password=t2.getText();

				CRUDOperations c=new CRUDOperations();
				c.add(username,password);  
				fr.setVisible(false);
			}
		}
				);

	}
	public static void main(String args[])
	{
		new SetPassword();
	}
}	