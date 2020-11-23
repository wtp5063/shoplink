package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.BaseDatabase;

/**
 * Servlet implementation class addProduct
 */
@WebServlet("/AddProduct")
@MultipartConfig(location = "/tmp/")
public class AddProduct extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  Part part = request.getPart("images");
	  String fileName = part.getSubmittedFileName();
	  part.write(getServletContext().getRealPath("/images")+ "/" + fileName);
	  String title = request.getParameter("title");
	  String artist = request.getParameter("artist");
	  String price = request.getParameter("price");

	  Connection con = null;
	  PreparedStatement stmt = null;
	  try {
	    con = BaseDatabase.getConnection();
	    stmt = con.prepareStatement("INSERT INTO products (title,artist,price,images) VALUE (?,?,?,?)");
	    stmt.setString(1, title);
	    stmt.setString(2, artist);
	    stmt.setString(3, price);
	    stmt.setString(4, fileName);
	    stmt.executeUpdate();
	    request.setAttribute("msg", "アップロード完了");
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

	  request.getRequestDispatcher("newProduct.jsp").forward(request, response);

	}

	/*private String getFileName(Part part) {
	  String fileName = null;
	  for(String item : part.getHeader("Content-Disposition").split(";")) {
	    if(item.trim().startsWith("filename")) {
	      fileName = item.substring(item.indexOf("\"")).replaceAll("\"", "");
	    }
	  }
	  return fileName;
	  }*/


}
