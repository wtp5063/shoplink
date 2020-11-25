package entry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BaseDatabase;

/**
 * Servlet implementation class OrderHistoryServlet
 */
@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  String user_id = request.getParameter("id");
	  List<DetailsDTO> list = new ArrayList<>();
	  HistoryDTO history = new HistoryDTO();
	  List<HistoryDTO> history_list = new ArrayList<>();

	  Connection con = null;
	  PreparedStatement stmt_detail = null;
	  PreparedStatement stmt_order = null;
	  PreparedStatement stmt_product = null;
	  ResultSet rs_detail = null;
	  ResultSet rs_order = null;
	  ResultSet rs_product = null;

	  try {
	    con = BaseDatabase.getConnection();
	    stmt_order = con.prepareStatement("SELECT * FROM new_order WHERE customer_id = ?");
	    stmt_order.setString(1, user_id);
	    rs_order = stmt_order.executeQuery();
	    while(rs_order.next()) {
	    history.setId(rs_order.getInt("id"));
	    Timestamp ts = rs_order.getTimestamp("datetime");
	    LocalDateTime ldt = ts.toLocalDateTime();
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd hh:mm:ss");
	    history.setDatetime(dtf.format(ldt));
	    history.setTotalAmount(rs_order.getInt("total_amounr"));

	    stmt_detail = con.prepareStatement("SELECT * FROM details WHERE order_id = ?");
	    stmt_detail.setInt(1, rs_order.getInt("id"));
	    rs_detail = stmt_detail.executeQuery();
	    while(rs_detail.next()) {
	      stmt_product = con.prepareStatement("SELECT * FROM products WHERE images = ?");
	      stmt_product.setString(1, rs_detail.getString("product") + ".jpg");
	      rs_product = stmt_product.executeQuery();
	      while(rs_product.next()) {
	        DetailsDTO detailsDto = new DetailsDTO();
	        detailsDto.setTitle(rs_product.getString("title"));
	        detailsDto.setArtist(rs_product.getString("artist"));
	        detailsDto.setPrice(rs_product.getInt("price"));
	        detailsDto.setQuantity(rs_product.getInt("quantity"));
	        list.add(detailsDto);
	      }
	    }
	    history.setList(list);
	    history_list.add(history);
	  }
	} catch(SQLException e) {
	  e.printStackTrace();
	} catch(Exception e) {
	  e.printStackTrace();
	} finally {
	  try {
	  if(rs_product != null) {rs_product.close();}
	  if(rs_detail != null) {rs_detail.close();}
	  if(rs_order != null) {rs_order.close();}
	  if(stmt_product != null) {stmt_product.close();}
	  if(stmt_detail != null) {stmt_detail.close();}
	  if(stmt_order != null) {stmt_order.close();}
	  if(con != null) {con.close();}
	  } catch(SQLException e) {
	    e.printStackTrace();
	  } catch(Exception e) {
	    e.printStackTrace();
	  }
	}
	  request.setAttribute("history", history_list);
	  request.getRequestDispatcher("customerHistory.jsp").forward(request, response);

	}
}
