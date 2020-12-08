package management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.SelectProductByIdDao;
import database.dao.entity.ProductEntity;

/**
 * Servlet implementation class BeforeEditProductServlet
 */
@WebServlet("/BeforeEditProductServlet")
public class BeforeEditProductServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductEntity entity = null;
		try
        {
            entity = SelectProductByIdDao.selectProductById(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            request.setAttribute("msg", "データベースにアクセスできません");
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;
        }
		request.setAttribute("list", entity);
		request.getRequestDispatcher("editProduct.jsp").forward(request, response);
	}

}
