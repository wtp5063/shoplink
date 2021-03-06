package management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DeleteCustomerDao;

/**
 * Servlet implementation class DeleteCustomer
 */
@WebServlet("/DeleteCustomer")
public class DeleteCustomer extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("id");
        boolean result = false;

        try
        {
            result = DeleteCustomerDao.deleteCustomerById(id);
        }
        catch (SQLException e)
        {
            request.setAttribute("errors", "データベースにアクセスできませんでした");
            e.printStackTrace();
        }
        if (result)
        {
            request.setAttribute("errors", "削除に成功しました");
        }
        else
        {
            request.setAttribute("errors", "削除に失敗しました");
        }
        request.getRequestDispatcher("CustomerListServlet").forward(request, response);
    }
}
