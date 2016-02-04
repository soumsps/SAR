
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class LoginForm
{

	JFrame fr;
	JLabel l1,l2;
	JTextField t1;
	JPasswordField t2;
	JButton b1;
	boolean loginstatus;
	String username,password;

	public LoginForm()                // login form constructor
	{

		fr=new JFrame("Login Form");
		l1=new JLabel("User Name");
		l2=new JLabel("Password");
		t1=new JTextField();
		t2=new JPasswordField();
		b1=new JButton("SUBMIT");

		Container contentpane=fr.getContentPane();
		contentpane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();				// To Get the screen size(width,height)
		// calculate the new location of the window
		int w = fr.getSize().width;
		int h = fr.getSize().height;
		final int x = (dim.width - w) / 2;
		final int y = (dim.height - h) / 2;
		fr.setLocation(x-165, y-125);
		fr.setVisible(true);
		fr.setSize(330,250);
		fr.setResizable(false);

		l1.setBounds(20,50,80,30);
		t1.setBounds(130,50,150,25);
		l2.setBounds(20,100,70,30);
		t2.setBounds(130,100,150,25);
		b1.setBounds(115,150,100,25);
		b1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,17));
		b1.setBackground(Color.blue);
		b1.setForeground(Color.white);
		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		contentpane.add(l1);
		contentpane.add(t1);
		contentpane.add(l2);
		contentpane.add(t2);
		contentpane.add(b1);


		t2.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}
			public void removeUpdate(DocumentEvent e) {
				changed();
			}
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			@SuppressWarnings("deprecation")
			public void changed() {
				if (t2.getText().equals("")){ 
					b1.setEnabled(false);
				}
				else     	
					b1.setEnabled(true);
			}
		});

		b1.addActionListener(new ActionListener()              
		{                 
			public void actionPerformed(ActionEvent ae)
			{
				//get the user login details
				String username=t1.getText();
				@SuppressWarnings("deprecation")
				String password=t2.getText();
				CRUDOperations c=new CRUDOperations();
				loginstatus=c.validate(username,password);
				//user does not exist
				if(loginstatus==false)
				{
					JOptionPane.showMessageDialog (null, "LOGIN UNSUCCESSFULL", "Login Failed", JOptionPane.ERROR_MESSAGE);
				}
				//user exist
				else
				{
					fr.dispose();

					try {
						new MainForm();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}		
			}
		});
	} 
}	

