package management;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.ProductDao;
import database.dao.entity.ProductEntity;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    List<ProductEntity> list = null;
	    try
        {
            list = ProductDao.selectAllProduct();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            request.setAttribute("msg", "データベースにアクセスできません");
        }
	    if (list == null)
        {
            request.setAttribute("msg", "データが存在しません");
        }
	    request.setAttribute("list", list);
	    request.getRequestDispatcher("manager.jsp").forward(request, response);
	}
}
