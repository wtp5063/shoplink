package management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.EditProductDao;

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

	  boolean result = false;
	  try
    {
        result = EditProductDao.updateProductById(id, title, artist, price);
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        request.setAttribute("msg", "データベースにアクセスできませんでした");
    }
	  if (result)
    {
        request.setAttribute("msg", "更新に成功しました");
    }
    else
    {
        request.setAttribute("msg", "更新に失敗しました");
    }
	  request.setAttribute("id", id);
	  request.getRequestDispatcher("ProductDetailsServlet").forward(request, response);

	}
}
