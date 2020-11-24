package entry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	  Connection con = null;
	  PreparedStatement stmt_detail = null;
	  PreparedStatement stmt_order = null;
	  PreparedStatement stmt_product = null;
	  ResultSet rs_detail = null;
	  ResultSet rs_order = null;
	  ResultSet rs_product = null

	  try {
	    con = BaseDatabase.getConnection();
	    stmt_detail = con.prepareStatement("SELECT * FROM details WHERE user_id = ?");
	    stmt_detail.setString(1, user_id);
	    rs_detail = stmt_detail.executeQuery();
	    while(rs_detail.next()) {
	      stmt_product = con.prepareStatement("SELECT * FROM products WHERE images = ?");
	      stmt_product.setString(1, rs_detail.getString("product") + ".jpg");
	      rs_product = stmt_product.executeQuery();

	    }
	  }

	}

}
