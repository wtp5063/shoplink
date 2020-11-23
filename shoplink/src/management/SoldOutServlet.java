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
 * Servlet implementation class SoldOutServlet
 */
@WebServlet("/SoldOutServlet")
public class SoldOutServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("id");
    int price = Integer.parseInt(request.getParameter("price"));
    Connection con = null;
    PreparedStatement stmt = null;
    int isSold = 0;
    if(price == 0) {
      isSold = 3000;
    }
    try {
      con = BaseDatabase.getConnection();
      stmt = con.prepareStatement("UPDATE products SET price = ? WHERE id = ?");
      stmt.setInt(1, isSold);
      stmt.setString(2, id);
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
    request.getRequestDispatcher("productDetails.jsp").forward(request, response);

	}
}
