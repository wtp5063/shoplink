package management;

import java.io.File;
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
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    String id = request.getParameter("id");
    String images = request.getParameter("images");
    Connection con = null;
    PreparedStatement stmt = null;
    try {
      con = BaseDatabase.getConnection();
      con.setAutoCommit(false);
      stmt = con.prepareStatement("DELETE FROM products WHERE id = ?");
      stmt.setString(1, id);
      stmt.executeUpdate();
      File file = new File(getServletContext().getRealPath("/images") + "/" + images);
      file.delete();
      con.commit();
    } catch(SQLException e) {
      e.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if(stmt != null) {stmt.close();}
        if(stmt != null) {stmt.close();}
      } catch(SQLException e) {
        e.printStackTrace();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
    request.getRequestDispatcher("manager.jsp").forward(request, response);

	}
}
