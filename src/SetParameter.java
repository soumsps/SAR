
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class SetParameter
{
	JFrame fr;
	JLabel l1,l2,l3,l4,l5,l6,l7;
	JTextField t1,t2,t3,t4,t5,t6;
	JButton b1;
	SetParameter()
	{
		fr=new JFrame("SET PARAMETERS");
		l7=new JLabel("ENTER  NAME  OF  SUBJECTS");
		l1=new JLabel("SUBJECT 1");
		l2=new JLabel("SUBJECT 2");
		l3=new JLabel("SUBJECT 3");
		l4=new JLabel("SUBJECT 4");
		l5=new JLabel("SUBJECT 5");
		l6=new JLabel("SUBJECT 6");

		t1=new JTextField();
		t2=new JTextField();
		t3=new JTextField();
		t4=new JTextField();
		t5=new JTextField();
		t6=new JTextField();
		Container contentpane=fr.getContentPane();
		contentpane.setLayout(null);
		b1=new JButton("SUBMIT");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();				// To Get the screen size(width,height)
		// calculate the new location of the window
		int w = fr.getSize().width;
		int h = fr.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fr.setLayout(null);
		fr.setLocation(x-250, y-200);
		fr.setVisible(true);
		fr.setSize(500,400);
		fr.setResizable(false);

		l7.setBounds(150,10,250,30);
		l7.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

		l1.setBounds(40,90,220,30);
		t1.setBounds(200,90,220,25);

		l2.setBounds(40,120,220,30);
		t2.setBounds(200,120,220,25);

		l3.setBounds(40,150,220,30);
		t3.setBounds(200,150,220,25);

		l4.setBounds(40,180,220,30);
		t4.setBounds(200,180,220,25);

		l5.setBounds(40,210,220,30);
		t5.setBounds(200,210,220,25);

		l6.setBounds(40,240,220,30);
		t6.setBounds(200,240,220,25);

		//b1.setBounds(180,300,120,30);
		b1.setBounds(200,300,100,25);
		b1.setBackground(Color.blue);
		b1.setForeground(Color.white);
		b1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
		b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		contentpane.add(l1);
		contentpane.add(t1);

		contentpane.add(l2);
		contentpane.add(t2);

		contentpane.add(l3);
		contentpane.add(t3);

		contentpane.add(l4);
		contentpane.add(t4);

		contentpane.add(l5);
		contentpane.add(t5);

		contentpane.add(l6);
		contentpane.add(t6);

		contentpane.add(l7);
		contentpane.add(b1);

		b1.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent ae)
			{         
				CRUDOperations c=new CRUDOperations();  
				if(! t1.getText().equals(""))    c.add_parameter(1,t1.getText());
				if(! t2.getText().equals(""))    c.add_parameter(2,t2.getText());
				if(! t3.getText().equals(""))    c.add_parameter(3,t3.getText());
				if(! t4.getText().equals(""))    c.add_parameter(4,t4.getText());
				if(! t5.getText().equals(""))    c.add_parameter(5,t5.getText());
				if(! t6.getText().equals(""))    c.add_parameter(6,t6.getText());

			}
		}
				);

	}
	public static void main(String args[])
	{
		new SetParameter();
	}
}	