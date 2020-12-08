package entry;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.InsertCustomerDao;
import database.dao.entity.CustomerEntity;

/**
 * Servlet implementation class InsertCustomer
 */
@WebServlet("/InsertCustomer")
public class InsertCustomer extends HttpServlet {

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
	  eCheck.duplicateCheck(email);
	  eCheck.passwordCheck(password, validation);
	  eCheck.regExpCheck(tel, "^0\\d{9,}$", "電話番号");

	  if(eCheck.hasErrors()) {
	    request.setAttribute("errors", eCheck.errorList());
	    RequestDispatcher disp = request.getRequestDispatcher("admissionCustomer.jsp");
	    disp.forward(request, response);
	    return;
	  }

	  CustomerEntity entity = new CustomerEntity();
	  entity.setName(name);
	  entity.setEmail(email);
	  entity.setPassword(password);
	  entity.setAddress(address);
	  entity.setTel(tel);

	  boolean insert = false;

	  try
    {
        insert = InsertCustomerDao.insertProfile(entity);
    }
    catch (SQLException e)
    {
        request.setAttribute("errors", "登録に失敗しました");
        request.getRequestDispatcher("admissionCustomer.jsp").forward(request, response);
        e.printStackTrace();
        return;
    }
	  if (insert)
    {
	      request.setAttribute("email", email);
	      request.setAttribute("password", password);
	      request.getRequestDispatcher("/LogIn").forward(request, response);
	      return;
    }
    else
    {
        request.setAttribute("errors", "登録に失敗しました");
        RequestDispatcher disp = request.getRequestDispatcher("admissionCustomer.jsp");
        disp.forward(request, response);
        return;
    }

	}

}
