package cart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BaseDatabase;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		HttpSession session = request.getSession();
		CartDTO cart = (CartDTO)session.getAttribute("cart");
    if(cart == null) {
      cart = new CartDTO();
    }
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		  con = BaseDatabase.getConnection();
		  stmt = con.prepareStatement("SELECT * FROM products WHERE id = ?");
		  stmt.setString(1,id);
		  rs = stmt.executeQuery();
		  while(rs.next()) {
		    ProductDTO dto = new ProductDTO();
		    dto.setId(rs.getInt("id"));
		    dto.setTitle(rs.getString("title"));
		    dto.setArtist(rs.getString("artist"));
		    dto.setPrice(rs.getInt("price"));
		    dto.setImages(rs.getString("images").replace(".jpg", ""));
		    dto.setQuantity(quantity);
		    CartLogic.execute(cart, dto);
		    request.setAttribute("msg", dto.getTitle() + "をカートに追加しました");
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
		session.setAttribute("cart", cart);
		request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);


	}

}
