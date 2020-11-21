package management;

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
 * Servlet implementation class EditCustomer
 */
@WebServlet("/EditCustomer")
public class EditCustomer extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  String id = request.getParameter("id");
	  String name = request.getParameter("name");
	  String address = request.getParameter("address");
	  String tel = request.getParameter("tel");

	  Connection con = null;
	  PreparedStatement stmt = null;
	  try {
	    con = BaseDatabase.getConnection();
	    stmt = con.prepareStatement("UPDATE customer SET name = ?, address = ?, tel = ? WHERE id = ?");
	    stmt.setString(1, name);
	    stmt.setString(2, address);
	    stmt.setString(3, tel);
	    stmt.setString(4, id);
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
	 RequestDispatcher disp = request.getRequestDispatcher("customerList.jsp");
	 disp.forward(request, response);

	}
}
