package entry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BaseDatabase;

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
    eCheck.duplicateCheck(email);
    eCheck.passwordCheck(password, validation);
    eCheck.regExpCheck(tel, "^0\\d{9,}$", "電話番号");
    //エラーが見つかった場合にエラー情報をリクエストに格納し、フォワード。
    if(eCheck.hasErrors()) {
      request.setAttribute("errors", eCheck.errorList());
      RequestDispatcher disp = request.getRequestDispatcher("admissionCustomer.jsp");
      disp.forward(request, response);
      return;
    }

    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      con = BaseDatabase.getConnection();
      stmt = con.prepareStatement("UPDATE customer SET name=?,email=?,password=?,address=?,tel=?)");
      stmt.setString(1, name);
      stmt.setString(2, email);
      stmt.setString(3, password);
      stmt.setString(4, address);
      stmt.setString(5, tel);
      stmt.executeUpdate();
      rs = stmt.executeQuery();
      if(rs.next()) {
        CustomerDTO dto = new CustomerDTO();
        dto.setName(rs.getString("name"));
        dto.setEmail(rs.getString("email"));
        dto.setAddress(rs.getString("address"));
        dto.setTel(rs.getString("tel"));
        HttpSession session = request.getSession();
        session.setAttribute("account", dto);
        RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
        disp.forward(request, response);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if(stmt != null) {stmt.close();}
        if(con != null) {con.close();}
      } catch(SQLException e) {
        e.printStackTrace();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }

  }
}
