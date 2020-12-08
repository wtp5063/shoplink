package entry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.OrderHistoryDao;

/**
 * Servlet implementation class OrderHistoryServlet
 */
@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        int user_id = Integer.parseInt(request.getParameter("id"));

        List<HistoryDTO> list = null;
        try
        {
            list = OrderHistoryDao.selectOrderHistory(user_id);
        }
        catch (SQLException e)
        {
            request.setAttribute("errors", "データベースにアクセスできませんでした");
            e.printStackTrace();
        }
        if (list == null)
        {
            request.setAttribute("errors", "購入履歴がありません");
        }
        request.setAttribute("history", list);
        request.getRequestDispatcher("customerHistory.jsp").forward(request, response);

    }
}
