package cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entry.CustomerDTO;

/**
 * Servlet implementation class ToBuyServlet
 */
@WebServlet("/ToBuyServlet")
public class ToBuyServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    HttpSession session = request.getSession();
    CustomerDTO account = (CustomerDTO) session.getAttribute("account");
    if(account == null) {
      session.setAttribute("logIn", "OK");
      request.getRequestDispatcher("login.jsp").forward(request, response);
    } else {
      request.getRequestDispatcher("orderConfirm.jsp").forward(request, response);
    }
	}

}
