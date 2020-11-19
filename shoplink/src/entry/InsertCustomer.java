package entry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BaseDatabase;

/**
 * Servlet implementation class InsertCustomer
 */
@WebServlet("/InsertCustomer")
public class InsertCustomer extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  request.setCharacterEncoding("UTF-8");
	  //POSTされたデータを変数に格納。
	  String name = request.getParameter("name");
	  String email = request.getParameter("email");
	  String password = request.getParameter("password");
	  String validation = request.getParameter("validation");
	  String address = request.getParameter("address");
	  String tel = request.getParameter("tel");
	  //ErrorCheckクラスをインスタンス化し、POSTされたデータをチェック。
	  ErrorCheck eCheck = new ErrorCheck();
	  eCheck.requiredCheck(name, "名前");
	  eCheck.duplicateCheck(email);
	  eCheck.passwordCheck(password, validation);
	  eCheck.regExpCheck(tel, "^0\\d{9,}$", "電話番号");
	  //エラーが見つかった場合にエラー情報をリクエストに格納し、フォワード。
	  if(eCheck.hasErrors()) {
	    request.setAttribute("errors", eCheck.errorList());
	    RequestDispatcher disp = request.getRequestDispatcher("admissionCustomer.jsp");
	    disp.forward(request, response);
	    return;
	  }

	  Connection con = null;
	  PreparedStatement stmt = null;
	  try {
	    con = BaseDatabase.getConnection();
	    stmt = con.prepareStatement("INSERT INTO customer (name,email,password,address,tel) VALUES (?,?,?,?,?)");
	    stmt.setString(1, name);
	    stmt.setString(2, email);
	    stmt.setString(3, password);
	    stmt.setString(4, address);
	    stmt.setString(5, tel);
	    stmt.executeUpdate();
	  } catch(SQLException e) {
	    e.printStackTrace();
	  } catch(Exception e) {
	    e.printStackTrace();
	  } finally {
	    try {
	      if(stmt != null) {stmt.close();}
	      if(con != null) {con.close();}
	    } catch(SQLException e) {
	      e.printStackTrace();
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	  }

	  RequestDispatcher disp = request.getRequestDispatcher("admissionCustomer.jsp");
	  disp.forward(request, response);
	}

}
