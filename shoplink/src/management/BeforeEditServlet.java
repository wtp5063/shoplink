package management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.entity.CustomerEntity;
import database.dao.entity.CustomerSelectDao;

/**
 * Servlet implementation class BeforeEditServlet
 */
@WebServlet("/BeforeEditServlet")
public class BeforeEditServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        CustomerEntity list = null;
        String id = request.getParameter("id");

        try
        {
            list = CustomerSelectDao.selectCustomer(id);
        }
        catch (SQLException e)
        {
            request.setAttribute("errors", "データベースにアクセスできませんでした");
            e.printStackTrace();
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
    }
}
