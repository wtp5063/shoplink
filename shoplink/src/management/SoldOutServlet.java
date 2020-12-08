package management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.SoldOutDao;

/**
 * Servlet implementation class SoldOutServlet
 */
@WebServlet("/SoldOutServlet")
public class SoldOutServlet extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        boolean result = false;
        String id = request.getParameter("id");
        int price = Integer.parseInt(request.getParameter("price"));
        int isSold = 0;
        if (price == 0)
        {
            isSold = 3000;
        }
        try
        {
            result = SoldOutDao.updateProductById(isSold, id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            request.setAttribute("msg", "データベースにアクセスできませんでした");
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;
        }
        if (result)
        {
            request.setAttribute("msg", "更新に成功しました");
        }
        else
        {
            request.setAttribute("msg", "更新に失敗しました");
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;

        }
        request.getRequestDispatcher("productDetails.jsp").forward(request, response);
    }
}
