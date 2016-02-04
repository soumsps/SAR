
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionManager
{
	Connection con;
	
	
	public Connection makeConnection()
	{
			try{
				 File file = new File ("C:\\SAR Database\\sardb.db");

				  if(file.exists()) //here's how to check
				     {
					    con=DriverManager.getConnection("jdbc:sqlite:C:\\SAR Database\\sardb.db");
				     }
				     else{
				    	 
				    	// create a database connection
				         new File("C:\\SAR Database").mkdirs();	
				         con = DriverManager.getConnection("jdbc:sqlite:C:\\SAR Database\\sardb.db");
				         Statement statement = con.createStatement();
				        
				         statement.executeUpdate("create table users (id integer NOT NULL, username string NOT NULL, password string NOT NULL)");
				         statement.executeUpdate("insert into users values(0, 'admin', 'pass')");
				        
				         statement.executeUpdate("create table subject_name (id integer, sub_name string NOT NULL)");
				         statement.executeUpdate("insert into subject_name values(1, 'SET SUBJECT')");
				         statement.executeUpdate("insert into subject_name values(2, 'SET SUBJECT')");
				         statement.executeUpdate("insert into subject_name values(3, 'SET SUBJECT')");
				         statement.executeUpdate("insert into subject_name values(4, 'SET SUBJECT')");
				         statement.executeUpdate("insert into subject_name values(5, 'SET SUBJECT')");
				         statement.executeUpdate("insert into subject_name values(6, 'SET SUBJECT')");
				         
				         statement.executeUpdate("create table subject1 (Date string PRIMARY KEY, Attendance string NOT NULL)");
				         statement.executeUpdate("create table subject2 (Date string PRIMARY KEY, Attendance string NOT NULL)");
				         statement.executeUpdate("create table subject3 (Date string PRIMARY KEY, Attendance string NOT NULL)");
				         statement.executeUpdate("create table subject4 (Date string PRIMARY KEY, Attendance string NOT NULL)");
				         statement.executeUpdate("create table subject5 (Date string PRIMARY KEY, Attendance string NOT NULL)");
				         statement.executeUpdate("create table subject6 (Date string PRIMARY KEY, Attendance string NOT NULL)");
				         
				         statement.executeUpdate("create table daily_check (subject_Id integer PRIMARY KEY, Date string NOT NULL, Attendance string NOT NULL, Present integer NOT NULL, Absent integer NOT NULL, Percentage integer NOT NULL)");
				         statement.executeUpdate("insert into daily_check values(1, '- - -','- - -',0,0,0)");  
				         statement.executeUpdate("insert into daily_check values(2, '- - -','- - -',0,0,0)");
				         statement.executeUpdate("insert into daily_check values(3, '- - -','- - -',0,0,0)");
				         statement.executeUpdate("insert into daily_check values(4, '- - -','- - -',0,0,0)");
				         statement.executeUpdate("insert into daily_check values(5, '- - -','- - -',0,0,0)");
				         statement.executeUpdate("insert into daily_check values(6, '- - -','- - -',0,0,0)");
				         JOptionPane.showMessageDialog (null, "Initializing Database For First time use. press OK to continue.",
		                           "Database Initialization", JOptionPane.INFORMATION_MESSAGE);
				     }  
				           
			   
			    
			 } // end of try block
		
			catch(SQLException sqle)
			 { 
				System.out.println("error in connection");
				System.out.println(sqle);
			 }
		return con;
	}
}