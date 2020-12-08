package management;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DeleteProductDao;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    String id = request.getParameter("id");
    String images = request.getParameter("images");
    boolean result = false;

    try
    {
        result = DeleteProductDao.deleteProductById(id);
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        request.setAttribute("msg", "データベースにアクセスできませんでした");
    }
    if (result)
    {
        File file = new File(getServletContext().getRealPath("/images") + "/" + images);
        file.delete();
        request.setAttribute("msg", "削除に成功しました");
    }
    else
    {
        request.setAttribute("msg", "削除に失敗しました");
    }
    request.getRequestDispatcher("manager.jsp").forward(request, response);
	}
}
