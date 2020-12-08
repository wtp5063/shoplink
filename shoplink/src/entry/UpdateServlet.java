package entry;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.dao.UpdateAccountDao;
import database.dao.entity.CustomerEntity;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    //POSTされたデータを変数に格納。
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String validation = request.getParameter("validation");
    String address = request.getParameter("address");
    String tel = request.getParameter("tel");
    //ErrorCheckクラスをインスタンス化し、POSTされたデータをチェック。
    ErrorCheck eCheck = new ErrorCheck();
    eCheck.requiredCheck(name, "名前");
    eCheck.passwordCheck(password, validation);
    eCheck.regExpCheck(tel, "^0\\d{9,}$", "電話番号");
    //エラーが見つかった場合にエラー情報をリクエストに格納し、フォワード。
    if(eCheck.hasErrors()) {
      request.setAttribute("errors", eCheck.errorList());
      RequestDispatcher disp = request.getRequestDispatcher("admissionCustomer.jsp");
      disp.forward(request, response);
      return;
    }

    HttpSession session = request.getSession();
    CustomerEntity sessionDto = (CustomerEntity)session.getAttribute("account");
    int id = sessionDto.getId();

    CustomerEntity result = null;
    try
    {
        result = UpdateAccountDao.updateAccountByName(name, email, password, address, tel, id);
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        request.setAttribute("msg", "データベースにアクセスできませんでした");
    }

    session.setAttribute("account", result);
    RequestDispatcher disp = request.getRequestDispatcher("/");
    disp.forward(request, response);
 }
}
