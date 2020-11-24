package cart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BaseDatabase;
import entry.CustomerDTO;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  HttpSession session = request.getSession();
	  CustomerDTO account = (CustomerDTO) session.getAttribute("account");
	  CartDTO cart = (CartDTO)session.getAttribute("cart");
	  TotalAmountDTO totalAmount = (TotalAmountDTO)session.getAttribute("totalAmount");

	  int user_id = account.getId();
	  int total = totalAmount.getTotalAmount();

	  LocalDateTime dateTime = LocalDateTime.now();
    Timestamp timeStamp = Timestamp.valueOf(dateTime);
    int id = 0;

	  List<ProductDTO> product = cart.getProduct();

	  Connection con = null;
	  PreparedStatement stmt_order = null;
	  PreparedStatement stmt_details = null;
	  ResultSet rs_id = null;

	  try {
	    con = BaseDatabase.getConnection();
	    con.setAutoCommit(false);

	    stmt_order = con.prepareStatement("INSERT INTO order (customer_id, datetime, total_amount) VALUES (?,?,?)");
	    stmt_order.setInt(1, user_id);
	    stmt_order.setTimestamp(2, timeStamp);
	    stmt_order.setInt(3, total);
	    stmt_order.executeUpdate();

	    rs_id = stmt_order.executeQuery("SELECT LAST_INSERT_ID() AS LAST");
	    id = rs_id.getInt("LAST");


	    for(ProductDTO item : product) {
	      stmt_details = con.prepareStatement("INSERT INTO details (order_id, product_id, price, quantity) VALUES (?,?,?,?)");
	      stmt_details.setInt(1, id);
	      stmt_details.setString(2, item.getImages());
	      stmt_details.setInt(3, item.getPrice());
	      stmt_details.setInt(4, item.getQuantity());
	      stmt_details.executeUpdate();
	    }
	    con.commit();
	  } catch(SQLException e) {
        try {
          con.rollback();
        } catch (SQLException e1) {
          // TODO 自動生成された catch ブロック
          e1.printStackTrace();
        }
	    e.printStackTrace();
	  } catch(Exception e) {
	    try {
        con.rollback();
      } catch (SQLException e1) {
        // TODO 自動生成された catch ブロック
        e1.printStackTrace();
      }
	    e.printStackTrace();
	  } finally {
	    try {
	      if(stmt_details != null) {stmt_details.close();}
	      if(rs_id != null) {stmt_details.close();}
	      if(stmt_order != null) {stmt_order.close();}
	      if(con != null) {con.close();}
	    } catch(SQLException e) {
	      e.printStackTrace();
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	  session.removeAttribute("cart");
	  session.removeAttribute("totalAmount");
	  request.setAttribute("order_id", id);
	  request.getRequestDispatcher("orderResult.jsp").forward(request, response);

	}
}
