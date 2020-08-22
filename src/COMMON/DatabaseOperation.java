
package COMMON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperation {
  
    
     public ResultSet rs;
     public PreparedStatement st;
      public Connection con;
    
    public DatabaseOperation()
    { 
      try
       {
          
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/securequiz";
        con=DriverManager.getConnection(url,"root","shiv");
       
       }
      catch(ClassNotFoundException ex)
      {
          ex.printStackTrace();
      }
      catch(SQLException exe)
      {
           exe.printStackTrace();
      }  
     
   }
 
}
