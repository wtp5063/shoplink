package entry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.BaseDatabase;

public class ErrorCheck {
  private List<String> errors;
  public ErrorCheck() {
    this.errors = new ArrayList<String>();
  }

  public void requiredCheck(String value, String name) {
    if(value == null || value.trim().isEmpty()) {
      this.errors.add(name + "を入力して下さい");
    }
  }

  public void regExpCheck(String value, String regExp, String name) {
    Pattern p = Pattern.compile(regExp);
    Matcher m = p.matcher(value);
    if(!m.matches()) {
      this.errors.add(name + "を正しい形式で入力して下さい");
    }
  }

  public void passwordCheck(String value1, String value2) {
    Pattern p = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[(a-zA-Z0-9(\\.\\-/_)*]{8,15}$");
    Matcher m = p.matcher(value1);
    if(!m.matches()){
      this.errors.add("パスワードは半角英数字の組み合わせと記号(.-/_)がご利用頂けます");
    } else if(!value1.equals(value2)) {
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

  public void logInCheck(String email, String password) {
    Connection con = null;
    PreparedStatement stmtEmail = null;
    PreparedStatement stmtPass = null;
    ResultSet rsEmail = null;
    ResultSet rsPass = null;
    try {
      con = BaseDatabase.getConnection();
      stmtEmail = con.prepareStatement("SELECT * FROM customer WHERE email = ?");
      stmtPass = con.prepareStatement("SELECT * FROM customer WHERE password = ?");
      stmtEmail.setString(1, email);
      stmtPass.setString(1, password);
      rsEmail = stmtEmail.executeQuery();
      rsPass = stmtPass.executeQuery();
      if(!rsEmail.next()) {this.errors.add("そのメールアドレスは登録されていません");}
      if(!rsPass.next()) {this.errors.add("パスワードが間違っています");}
    } catch(SQLException e) {
      e.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if(rsPass != null) {rsPass.close();}
        if(rsEmail != null) {rsEmail.close();}
        if(stmtPass != null) {stmtPass.close();}
        if(stmtEmail != null) {stmtEmail.close();}
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
    buff.append("<ul class='errors'>");
    for(String error : this.errors) {
        buff.append("<li>" + error + "</li>");
    }
    buff.append("</ul>");
    return buff.toString();
  }

}
