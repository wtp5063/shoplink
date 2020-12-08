package entry;

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
 * Servlet implementation class HomeServlet
 */
@WebServlet("/")
public class HomeServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<ProductEntity> list = null;
        try
        {
            list = ProductDao.selectAllProduct();
        }
        catch (SQLException e)
        {
            request.setAttribute("errors", "データベースアクセスに問題が発生しました");
            e.printStackTrace();
        }
        if (list == null)
        {
            request.setAttribute("errors", "データが存在しません");
        }
        request.setAttribute("rs", list);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
