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
 * Servlet implementation class EditProductServlet
 */
@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  String id = request.getParameter("id");
	  String title = request.getParameter("title");
	  String artist = request.getParameter("artist");
	  String price = request.getParameter("price");
	  Connection con = null;
	  PreparedStatement stmt = null;
	  try {
	    con = BaseDatabase.getConnection();
	    stmt = con.prepareStatement("UPDATE products SET title=?, artist=?, price=? WHERE id = ?");
	    stmt.setString(1, title);
	    stmt.setString(2, artist);
	    stmt.setString(3, price);
	    stmt.setString(4, id);
	    stmt.executeUpdate();
	  } catch(SQLException e) {
	    e.printStackTrace();
	  } catch(Exception e) {
	    e.printStackTrace();
	  } finally {
	    try {
	      if(stmt != null) {stmt.close();}
	      if(con != null) {stmt.close();}
	    } catch(SQLException e) {
	      e.printStackTrace();
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	  request.getRequestDispatcher("manager.jsp").forward(request, response);

	}
}
