
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class CRUDOperations
{
	String username,password;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	boolean validationstatus;
	// method to check validation of the worker
	public boolean validate(String username,String password)
	{
		try
		{
			con=new ConnectionManager().makeConnection();
			String query="select * from users where username=? and password=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();

			if(rs.next()==false)
			{
				validationstatus=false;
			}
			//user exists
			else
			{
				validationstatus=true;	
			}
			pstmt.close();
			con.close();
		}catch(SQLException sqle)
		{ }
		return validationstatus;
	}

	public void add(String username,String password)
	{	
		try
		{	    
			con=new ConnectionManager().makeConnection();
			String query0="select * from users where username=? and password=?";
			pstmt=con.prepareStatement(query0);
			pstmt.setString(1,"admin");
			pstmt.setString(2,"pass");
			rs=pstmt.executeQuery();

			if(rs.next()==false)
			{
				JFrame fr = new JFrame();                                                   // Dialog box code
				JDialog dg1 = new JDialog(new Frame());
				JLabel msg1;
				msg1=new JLabel("RESET PASSWORD FIRST");
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();				// To Get the screen size(width,height)
				// calculate the new location of the window
				int w = fr.getSize().width;
				int h = fr.getSize().height;
				final int x = (dim.width - w) / 2;
				final int y = (dim.height - h) / 2;
				msg1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
				msg1.setForeground(Color.red);
				msg1.setBounds(40,25,100,30);
				dg1.setLocation(x-140, y-40);
				dg1.setSize(280,80);
				dg1.setVisible(true);
				dg1.setResizable(false);
				dg1.add(msg1);
			}

			else
			{   con.close();
			con=new ConnectionManager().makeConnection();
			String query1="delete from users WHERE id = 0";
			pstmt=con.prepareStatement(query1);
			pstmt.executeUpdate();


			String query="insert into users values(0,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.executeUpdate();

			}

			con.close();
			return;
		}
		catch(SQLException sqle){System.out.println(sqle);}


	}

	public void add()                  // RESET LOGON CREDENTIALS
	{	
		try
		{	
			con=new ConnectionManager().makeConnection();
			String query1="delete from users WHERE id = 0";
			pstmt=con.prepareStatement(query1);
			pstmt.executeUpdate();

			String query="insert into users values(0,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,"admin");
			pstmt.setString(2,"pass");
			pstmt.executeUpdate();

			JOptionPane.showMessageDialog (null, "RESET SUCCESSFULL", "RESET PASSWORD", JOptionPane.INFORMATION_MESSAGE);

			pstmt.close();	
			con.close();
			return;
		}
		catch(SQLException sqle){System.out.println(sqle);}
	}

	public void add_parameter(int n,String name)                  // set parameters
	{	
		try
		{	
			con=new ConnectionManager().makeConnection();
			String query2="select * from subject_name WHERE id = ?";
			pstmt=con.prepareStatement(query2);
			pstmt.setInt(1, n);
			rs = pstmt.executeQuery();
			rs.next();

			if(rs.getString("sub_name").equals("SET SUBJECT"))
			{ 
				String query1="delete from subject_name WHERE id = ?";
				pstmt=con.prepareStatement(query1);
				pstmt.setInt(1, n);
				pstmt.executeUpdate();

				String query="insert into subject_name values(?,?)";
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1,n);
				pstmt.setString(2,name);
				pstmt.executeUpdate();
			}	
			con.close();
			return;

		}
		catch(SQLException sqle){System.out.println(sqle); }
	}

	public void Reset_Subjects(int n,String name,String table_name)                  // RESET SUBJECT
	{	
		try
		{	
			con=new ConnectionManager().makeConnection();
			Statement statement = con.createStatement();
			String query1="delete from subject_name WHERE id = ?";
			pstmt=con.prepareStatement(query1);
			pstmt.setInt(1,n);
			pstmt.executeUpdate();

			String query="insert into subject_name values(?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1,n);
			pstmt.setString(2,"SET SUBJECT");
			pstmt.executeUpdate();
			statement.executeUpdate("drop table " +table_name);
			statement.executeUpdate("create table "+table_name+" (Date string PRIMARY KEY, Attendance string NOT NULL)");

			String query2="delete from daily_check WHERE subject_Id = ?";
			pstmt=con.prepareStatement(query2);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			pstmt.executeUpdate();

			String query3="insert into daily_check values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query3);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			pstmt.setString(2,"- - -");
			pstmt.setString(3,"- - -");
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setDouble(6, 0);
			pstmt.executeUpdate();



			con.close();
			return;

		}
		catch(SQLException sqle){System.out.println(sqle); }
	}

	public void present(String table_name)                  // attendance marking present
	{	
		try
		{	//System.out.println("hello");
			con=new ConnectionManager().makeConnection();
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("EEEE, dd MMMM YYYY");
			String dt = ft.format(dNow);

			String query="insert into "+table_name+" values(?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,dt);
			pstmt.setString(2,"PRESENT");
			pstmt.executeUpdate();

			String query0="select * from daily_check WHERE subject_Id = ?";        // calculation of present , percent , absent
			pstmt=con.prepareStatement(query0);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			rs = pstmt.executeQuery();
			rs.next();
			double present=rs.getInt("Present") + 1;
			double absent=rs.getInt("Absent");
			double percent= (present / (present + absent)) *100;
			System.out.println("present"+present+"//"+absent+"//"+percent);
			//pstmt.close();

			String query1="delete from daily_check WHERE subject_Id = ?";
			pstmt=con.prepareStatement(query1);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			pstmt.executeUpdate();

			String query2="insert into daily_check values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query2);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			pstmt.setString(2,dt);
			pstmt.setString(3,"PRESENT");
			pstmt.setInt(4, (int)present);
			pstmt.setInt(5, (int)absent);
			pstmt.setDouble(6, percent);
			pstmt.executeUpdate();


			pstmt.close();	
			con.close();	
			return;

		}
		catch(SQLException sqle){System.out.println(sqle); }
	}

	public void absent(String table_name)                  // attendance marking absent
	{	
		try
		{	
			con=new ConnectionManager().makeConnection();
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("EEEE, dd MMMM YYYY");
			String dt = ft.format(dNow);

			String query="insert into "+table_name+" values(?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,dt);
			pstmt.setString(2,"ABSENT");
			pstmt.executeUpdate();

			String query0="select * from daily_check WHERE subject_Id = ?";        // calculation of present , percent , absent
			pstmt=con.prepareStatement(query0);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			rs = pstmt.executeQuery();
			rs.next();
			double present=rs.getInt("Present");
			double absent=rs.getInt("Absent") + 1;
			double percent= (present / (present + absent)) *100;
			System.out.println("absent"+present+"//"+absent+"//"+percent);
			//pstmt.close();

			String query1="delete from daily_check WHERE subject_Id = ?";
			pstmt=con.prepareStatement(query1);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			pstmt.executeUpdate();

			String query2="insert into daily_check values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query2);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			pstmt.setString(2,dt);
			pstmt.setString(3,"ABSENT");
			pstmt.setInt(4, (int)present);
			pstmt.setInt(5, (int)absent);
			pstmt.setDouble(6, percent);
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
			return;

		}
		catch(SQLException sqle){System.out.println(sqle); }
	}

}
