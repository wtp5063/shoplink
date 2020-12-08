package management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.EditCustomerDao;
import database.dao.entity.CustomerEntity;

/**
 * Servlet implementation class EditCustomer
 */
@WebServlet("/EditCustomer")
public class EditCustomer extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        CustomerEntity entity = new CustomerEntity();
        entity.setId(Integer.parseInt(request.getParameter("id")));
        entity.setName(request.getParameter("name"));
        entity.setAddress(request.getParameter("address"));
        entity.setTel(request.getParameter("tel"));

        boolean result = false;
        try
        {
            result = EditCustomerDao.updateCustomerById(entity);
        }
        catch (SQLException e)
        {
            request.setAttribute("errors", "データベースに接続できませんでした");
            e.printStackTrace();
        }
        if (result)
        {
            request.setAttribute("errors", "更新に成功しました");
        }
        else
        {
            request.setAttribute("errors", "更新に失敗しました");
        }
        RequestDispatcher disp = request.getRequestDispatcher("CustomerListServlet");
        disp.forward(request, response);

    }
}
