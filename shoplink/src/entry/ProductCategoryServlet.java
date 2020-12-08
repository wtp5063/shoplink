package entry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.ProductCategoryDao;
import database.dao.entity.ProductEntity;

/**
 * Servlet implementation class ProductCategoryServlet
 */
@WebServlet("/ProductCategoryServlet")
public class ProductCategoryServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String artist = request.getParameter("category");
		List<ProductEntity> list = null;
		try
        {
            list = ProductCategoryDao.selectProductByArtist(artist);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            request.setAttribute("msg", "データベースに接続できませんでした");
        }
		if (list == null)
        {
           request.setAttribute("msg", "データが存在しません");
        }
		request.setAttribute("product", list);
		request.getRequestDispatcher("productCategory.jsp").forward(request, response);
	}

}
