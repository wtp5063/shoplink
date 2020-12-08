package entry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.SearchProductDao;
import database.dao.entity.ProductEntity;

/**
 * Servlet implementation class SearchProductServlet
 */
@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		List<ProductEntity> list = null;
		try
        {
            list = SearchProductDao.selectProductsLikeX(search);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            request.setAttribute("msg", "データベースにアクセスできませんでした");
        }
		if (list == null)
        {
            request.setAttribute("msg", "データが見つかりません");
        }
		request.setAttribute("product", list);
		request.getRequestDispatcher("searchProduct.jsp").forward(request, response);
	}
}
