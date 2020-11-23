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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = request.getParameter("search");
		Connection con  = null;
		PreparedStatement stmt = null;
		try {
		  con = BaseDatabase.getConnection();
		  stmt = con.prepareStatement("SELECT * FROM products WHERE title LIKE %?% OR artist LIKE %?%");
		  stmt.setString(1, text);
		  stmt.setString(2, text);
		  stmt.executeQuery();
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

	}
}
