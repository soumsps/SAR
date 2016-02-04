import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class About {
	
	JFrame fr;
	JLabel l1,l22,l23,l24,l3;
	
	public About(){
		fr = new JFrame("About");
		Font f = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC ,25);
		Font f1 = new Font(Font.SANS_SERIF, Font.BOLD + Font.ITALIC, 15);
		Font f3 = new Font(Font.SANS_SERIF, Font.BOLD , 18);
		l1 = new JLabel("SELF  ATTENDANCE  REGISTER");
		l1.setFont(f);
		
		
		l22=new JLabel("Soumendu Prasad Sinha");
		l22.setFont(f1);
		l23=new JLabel("Github username : soumsps");
		l23.setFont(f1);
		l24=new JLabel("MJPRU Bareilly");
		l24.setFont(f1);
		l3= new JLabel("(Project Work)");
		l3.setFont(f3);
		
		Container contentpane = fr.getContentPane();
		contentpane.setLayout(null);
		fr.setSize(510, 300);
		fr.setVisible(true);
		fr.setResizable(false);
		center(fr); 

		
		l1.setBounds(50,10,1000,100);    contentpane.add(l1);
		l3.setBounds(180,40,1000,100);    contentpane.add(l3);
		l22.setBounds(300,170,1000,30);  contentpane.add(l22);
		l23.setBounds(300,195,1000,30);  contentpane.add(l23);
		l24.setBounds(300,220,1000,30);  contentpane.add(l24);
	}                 														//End of constructor About()
	
	public static void center(JFrame frame) {
		        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();				// To Get the screen size(width,height)
		        // calculate the new location of the window
		        int w = frame.getSize().width;
		        int h = frame.getSize().height;
		        int x = (dim.width - w) / 2;
		        int y = (dim.height - h) / 2;
		        frame.setLocation(x, y);
		    }

	
	public static void main(String[] args){
		
		new About();
	}

}
