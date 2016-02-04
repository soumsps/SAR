import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Attendance {

	private JTable table;
	private JFrame fr;
	JButton btnPresent,btnAbsent;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	
	public static TableModel resultSetToTableModel(ResultSet rs) {
		try {
		    ResultSetMetaData metaData = rs.getMetaData();
		    int numberOfColumns = metaData.getColumnCount();
		    Vector<String> columnNames = new Vector<String>();

		    // Get the column names
		    for (int column = 0; column < numberOfColumns; column++) {
			columnNames.addElement(metaData.getColumnLabel(column + 1));
		    }

		    // Get all rows.
		    Vector<Vector<Object>> rows = new Vector<Vector<Object>>();

		    while (rs.next()) {
			Vector<Object> newRow = new Vector<Object>();

			for (int i = 1; i <= numberOfColumns; i++) {
			    newRow.addElement(rs.getObject(i));
			}

			rows.addElement(newRow);
		    }

		    return new DefaultTableModel(rows, columnNames);
		} catch (Exception e) {
		    e.printStackTrace();

		    return null;
		}
	    }
	
	public Attendance(final String table_name, String subject_name, String date) throws SQLException {
		fr = new JFrame("SUBJECT ATTENDANCE");
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();				// To Get the screen size(width,height)
																					// calculate the new location of the window
	    int w = fr.getSize().width;
	    int h = fr.getSize().height;
	    int x = (dim.width - w) / 2;
	    int y = (dim.height - h) / 2;
	    fr.setLocation(x-250, y-250);
		fr.setVisible(true);
		fr.setSize(500,500);
		fr.setResizable(false);
		Container contentpane=fr.getContentPane();
		contentpane.setLayout(null);
		
		JLabel l1 = new JLabel(subject_name.toUpperCase());
		l1.setBounds(5, 5, 260, 30);
		l1.setForeground(Color.blue);
		l1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		contentpane.add(l1);
		
		
		btnPresent = new JButton("PRESENT");
		btnPresent.setBounds(270, 5, 100, 30);
		btnPresent.setFont(new Font("Arial", Font.BOLD, 14));
		btnPresent.setForeground(Color.white);
		btnPresent.setBackground(Color.green);
		contentpane.add(btnPresent);
		
		btnAbsent = new JButton("ABSENT");
		btnAbsent.setBounds(380, 5, 100, 30);
		btnAbsent.setFont(new Font("Arial", Font.BOLD, 14));
		btnAbsent.setForeground(Color.white);
		btnAbsent.setBackground(Color.red);
		contentpane.add(btnAbsent);
		
		Date dNow = new Date( );
	     SimpleDateFormat ft = new SimpleDateFormat ("EEEE, dd MMMM YYYY");
	     String dt = ft.format(dNow);
	     
	     if(date.equals(dt))
	       {
	    	 btnPresent.setEnabled(false);
			 btnAbsent.setEnabled(false);
	       }
	     else
	     {
	    	 btnPresent.setEnabled(true);
	    	 btnAbsent.setEnabled(true);
	     }
		
		btnPresent.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				CRUDOperations c=new CRUDOperations(); 
				btnPresent.setEnabled(false);
				btnAbsent.setEnabled(false);
				c.present(table_name);
				
			}
		}
		);  
		
		btnAbsent.addActionListener(new ActionListener()
		{                 
			public void actionPerformed(ActionEvent ae)
			{ 
				CRUDOperations c=new CRUDOperations();  
				btnPresent.setEnabled(false);
				btnAbsent.setEnabled(false);
				c.absent(table_name);
				
			}
		}
		); 
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 50 , 480, 390);
		contentpane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		con=new ConnectionManager().makeConnection();
		String query="select * from " + table_name;
			pstmt=con.prepareStatement(query);
			rs = pstmt.executeQuery();
			table.setModel(resultSetToTableModel(rs));
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
			table.setBackground(Color.white);
	        table.setForeground(Color.black);
	        table.setRowHeight(24);
	        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
	        
	        con=new ConnectionManager().makeConnection();
	        String query0="select * from daily_check WHERE subject_Id = ?";        // calculation of present , percent , absent
			pstmt=con.prepareStatement(query0);
			pstmt.setInt(1, (int)table_name.charAt(7)-48);
			rs = pstmt.executeQuery();
			rs.next();
			int present=rs.getInt("Present");
			int absent=rs.getInt("Absent");
			int percent= rs.getInt("Percentage");
			pstmt.close();
	        
	        JLabel l2 = new JLabel("Present/Absent : " +present+"/"+absent);
			l2.setBounds(7, 440, 480, 30);
			l2.setForeground(Color.black);
			l2.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
			contentpane.add(l2);
			
			JLabel l3 = new JLabel("Present Percentage : " +percent+"%");
			l3.setBounds(240, 440, 480, 30);
			l3.setForeground(Color.black);
			l3.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
			contentpane.add(l3);   
	        
	}
	
	public static void main(String args[]) throws SQLException
	{
		new MainForm();
	}

}
