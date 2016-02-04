import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Main {
	
	JFrame fr;
	JMenuBar menubar;
	JMenu setting;
	JMenuItem setParameter;
	JMenuItem about;
	JMenuItem exit;
	
	public Main(){
		
		fr = new JFrame();
		Container contentpane = fr.getContentPane();
		contentpane.setLayout(null);
			
/********************* Menu Bar Starts *********************************/
		
		menubar =new JMenuBar();
		fr.setJMenuBar(menubar);
		
		setting = new JMenu("Setting");
		menubar.add(setting);
		
		setParameter = new JMenuItem("Set Parameters");
		setting.add(setParameter);
		
		about = new JMenuItem("About");
		setting.add(about);
		
		exit = new JMenuItem("Exit");
		setting.add(exit);
		
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
		
		
		
/**********************Fullscreen Layout Code**************************/		
		
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());        // Determines users screen dimensions
		int ySize = ((int) tk.getScreenSize().getHeight());       // Determines users screen dimensions
		fr.setSize(xSize, ySize);
		fr.setVisible(true);
		fr.setTitle("Self Attendance Register");
		
/**********************Fullscreen Layout Code Ends**************************/		
	
	}
	
  	
	public class event implements ActionListener{
		public void actionPerformed(ActionEvent e)  { System.exit(0); }
	}
	
	public static void main(String[] args){
	   new Main();
   }
}