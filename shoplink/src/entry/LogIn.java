package entry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BaseDatabase;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  String email = request.getParameter("email");
	  String password = request.getParameter("password");
	  String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";

	  ErrorCheck eCheck = new ErrorCheck();
    eCheck.logInCheck(email, password);
    if(eCheck.hasErrors()) {
      request.setAttribute("errors", eCheck.errorList());
      RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
      disp.forward(request, response);
      return;
    }

	  Connection con = null;
	  PreparedStatement stmt = null;
	  ResultSet rs = null;
	  try {
	    con = BaseDatabase.getConnection();
	    stmt = con.prepareStatement(sql);
	    stmt.setString(1, email);
	    stmt.setString(2, password);
	    rs = stmt.executeQuery();
	    if(rs.next()) {
	      int admin = rs.getInt("admin");
	      if(admin == 1) {
	        RequestDispatcher disp = request.getRequestDispatcher("manager.jsp");
	        disp.forward(request, response);
	      } else {
	        CustomerDTO dto = new CustomerDTO();
	        dto.setName(rs.getString("name"));
	        dto.setEmail(rs.getString("email"));
	        dto.setAddress(rs.getString("address"));
	        dto.setTel(rs.getString("tel"));
	        HttpSession session = request.getSession();
	        session.setAttribute("account", dto);
	        RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
	        disp.forward(request, response);
	      }
	    }
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

}