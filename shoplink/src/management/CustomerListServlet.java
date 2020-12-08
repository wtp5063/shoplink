package management;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.CustomerListDao;
import database.dao.entity.CustomerEntity;

/**
 * Servlet implementation class CustomerListServlet
 */
@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet
{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<CustomerEntity> list = null;
        try
        {
            list = CustomerListDao.selectCustomerList();
        }
        catch (SQLException e)
        {
            request.setAttribute("errors", "データベースにアクセスできませんでした");
            e.printStackTrace();
        }
        if (list == null)
        {
            request.setAttribute("errors", "情報がありません");
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("customerList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
