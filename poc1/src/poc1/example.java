package poc1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class example {
  
  private static final String CREATE_TABLE_SQL="CREATE TABLE poc1 ("
                    + "teamid INT NOT NULL,"
                    + "teamname VARCHAR(45) NOT NULL,"
                    + "playername VARCHAR(45) NOT NULL,"
                    + "playerscore VARCHAR(20))";

  public static void main(String[] args) throws SQLException, FileNotFoundException {
    String jdbcUrl = "jdbc:mysql://localhost:3306/poc1";
    String username = "root";
    String password = "Kowse@17998";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement preparedstatement = null;

    try {

      conn = DriverManager.getConnection(jdbcUrl, username, password);
      stmt = conn.createStatement();
      

      stmt.executeUpdate(CREATE_TABLE_SQL);

      System.out.println("Table created");

    } catch (SQLException e) {
      e.printStackTrace();
    } 
 
    try{
        String read=null;
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\kowsalya.balachander\\Desktop\\values.txt")); 
       
        while ((read = in.readLine()) != null) {
            String[] splited = read.split("\\s+");
            String teamid = splited[0];
            String teamname = splited[1];
            String playername = splited[2];
            String playerscore = null;
			add(conn, preparedstatement, teamid, teamname, playername, playerscore);
        }
    }
    catch (IOException e) {System.out.println("There was a problem: " + e);}}

  
    public static void add(Connection connection, PreparedStatement preparedstatement, String teamid, String teamname, String playername, String playerscore ) throws SQLException{
    preparedstatement=connection.prepareStatement("insert into poc1(teamid, teamname, playername, playerscore) values(?,?,?,?)");
    preparedstatement.setString(1, teamid);
    preparedstatement.setString(2, teamname);
    preparedstatement.setString(3, playername);
    
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter score of : " +playername);
    playerscore = scanner.nextLine();
    preparedstatement.setString(4, playerscore);
    
    preparedstatement.executeUpdate();
    System.out.println("Values Inserted");     
    
    
    
    
    }      
  }   