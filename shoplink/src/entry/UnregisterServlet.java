package entry;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.dao.DeleteCustomerDao;

/**
 * Servlet implementation class UnregisterServlet
 */
@WebServlet("/UnregisterServlet")
  public class UnregisterServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    HttpSession session = request.getSession();
    String id = request.getParameter("id");
    boolean result = false;
    try
    {
        result = DeleteCustomerDao.deleteCustomerById(id);
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        request.setAttribute("msg", "データベースに接続できませんでした");
    }
    if (result)
    {
        session.invalidate();
        request.setAttribute("msg", "退会が完了しました");
    }
    else
    {
        request.setAttribute("msg", "退会に失敗しました");
    }

    request.getRequestDispatcher("/").forward(request, response);

  }
}

