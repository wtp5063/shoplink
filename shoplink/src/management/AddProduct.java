package management;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import database.dao.AddProductDao;

/**
 * Servlet implementation class addProduct
 */
@WebServlet("/AddProduct")
@MultipartConfig(location = "/tmp/")
public class AddProduct extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        Part part = request.getPart("images");
        String fileName = part.getSubmittedFileName();
        part.write(getServletContext().getRealPath("/images") + "/" + fileName);
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String price = request.getParameter("price");

        boolean result = false;
        try
        {
            result = AddProductDao.insertProduct(title, artist, price, fileName);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            request.setAttribute("msg", "データベースにアクセスできませんでした");
            request.getRequestDispatcher("newProduct.jsp").forward(request, response);
            return;
        }
        if (result)
        {
            request.setAttribute("msg", "商品の追加が完了しました");
        }
        else
        {
            request.setAttribute("msg", "商品の追加に失敗しました");
            request.getRequestDispatcher("newProduct.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("ManagerServlet").forward(request, response);
    }
}
