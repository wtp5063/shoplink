package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BaseDatabase;

/**
 * Servlet implementation class DeleteCustomer
 */
@WebServlet("/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  String id = request.getParameter("id");
	  Connection con = null;
	  PreparedStatement stmt = null;
	  try {
	    con = BaseDatabase.getConnection();
	    stmt = con.prepareStatement("DELETE FROM customer WHERE id = ?");
	    stmt.setString(1, id);
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
	  request.getRequestDispatcher("customerList.jsp").forward(request, response);
	}

}