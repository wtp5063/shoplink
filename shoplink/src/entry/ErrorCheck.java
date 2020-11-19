package entry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.BaseDatabase;

public class ErrorCheck {
  private List<String> errors;
  private ErrorCheck() {
    this.errors = new ArrayList<String>();
  }

  public void requiredCheck(String value, String name) {
    if(value == null || value.trim().isEmpty()) {
      this.errors.add(name + "を入力して下さい");
    }
  }

  public void regExpCheck(String value, String pattern, String name) {
    if(!value.matches(pattern)) {
      this.errors.add(name + "を正しい形式で入力して下さい");
    }
  }

  public void passwordCheck(String value1, String value2) {
    if(!value1.matches("^(?=.*[a-z])(?=.*[0-9])[\\w\\.-/_]{8, 15}$")) {
      this.errors.add("パスワードは半角英数字の組み合わせと記号(.-/_)がご利用頂けます");
    } else if(!(value1 == value2)) {
      this.errors.add("パスワードが一致しません");
    }
  }

  public void duplicateCheck(String value) {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      con = BaseDatabase.getConnection();
      stmt = con.prepareStatement("SELECT * FROM customer WHERE email = ?");
      stmt.setString(1, value);
      rs = stmt.executeQuery();
      if(rs.next()) {this.errors.add("そのメールアドレスは既に利用されています");}
    } catch(SQLException e) {
      e.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if(rs != null) {rs.close();}
        if(stmt != null) {stmt.close();}
        if(con != null) {con.close();}
      } catch(SQLException e) {
        e.printStackTrace();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }

  public boolean hasErrors() {
    return !this.errors.isEmpty();
  }

  public String errorList() {
    StringBuffer buff = new StringBuffer();
    buff.append("<ul style='color:red'>");
    for(String error : this.errors) {
      buff.append("<li>" + error + "<li>");
    }
    buff.append("</ul>");
    return buff.toString();
  }

}
