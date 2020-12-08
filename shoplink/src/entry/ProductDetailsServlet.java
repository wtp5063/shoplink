package entry;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.ProductDetailsDao;
import database.dao.entity.ProductEntity;

/**
 * Servlet implementation class ProductDetailsServlet
 */
@WebServlet("/ProductDetailsServlet")
public class ProductDetailsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductEntity list = null;
		try
        {
            list = ProductDetailsDao.selectProductById(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            request.setAttribute("msg", "データベースに接続できませんでした");
        }
		if (list == null)
        {
            request.setAttribute("msg", "データがありません");
        }
		request.setAttribute("list", list);
		request.getRequestDispatcher("productDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
