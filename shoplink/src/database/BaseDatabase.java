package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatabase {
  public static Connection getConnection() {
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/customer";
    String user = "root";
    String pass = "root";
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(url, user, pass);
    } catch(SQLException e) {
      e.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
    return con;
  }
}
