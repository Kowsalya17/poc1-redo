package poc1;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class output {
		public static void main(String[] args) throws SQLException, IOException {
		    String jdbcUrl = "jdbc:mysql://localhost:3306/poc1";
		    String username = "root";
		    String password = "Kowse@17998";

		    Connection conn = null;
		    Statement stmt = null;
		    ResultSet rs = null;
		    
		      conn = DriverManager.getConnection(jdbcUrl, username, password);
		      stmt = conn.createStatement();
		      
		      FileOutputStream fos = new FileOutputStream("C:\\Users\\kowsalya.balachander\\Desktop\\out.txt");
		      DataOutputStream dos = new DataOutputStream(fos);
		       
		      rs = stmt.executeQuery("select * from poc1 order by teamid ASC, playerscore ASC");
		      while(rs.next()) {
		    	  String id = rs.getString("teamid");
		    	  String name = rs.getString("teamname");
		    	  String pname = rs.getString("playername");
		    	  String pscore = rs.getString("playerscore");
		    	  String out = "\n" + id + " " + name +" " + pname + " " +pscore;
		    	  dos.writeBytes(out);
		      }	      
		      System.out.println("Output file has been writen");
}
}