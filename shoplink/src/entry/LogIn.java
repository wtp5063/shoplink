package entry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.dao.CustomerDao;
import database.dao.entity.CustomerEntity;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ErrorCheck eCheck = new ErrorCheck();
        eCheck.logInCheck(email, password);
        if (eCheck.hasErrors())
        {
            request.setAttribute("errors", eCheck.errorList());
            RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
            disp.forward(request, response);
            return;
        }

        CustomerEntity dto = null;

        try
        {
            dto = CustomerDao.selectByLogInData(password, email);
        }
        catch (SQLException e)
        {
            request.setAttribute("errors", Arrays.asList(new String [] {"msg..."}));
            RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
            disp.forward(request, response);
            e.printStackTrace();
        }

        if (dto == null)
        {
            request.setAttribute("errors", Arrays.asList(new String [] {"msg2..."}));
            RequestDispatcher disp = request.getRequestDispatcher("login.jsp");
            disp.forward(request, response);
        }

        HttpSession session = request.getSession();
        session.setAttribute("account", dto);
        if (dto.getAdmin() == 1)
        {
            RequestDispatcher disp = request.getRequestDispatcher("manager.jsp");
            disp.forward(request, response);
        }
        else
        {
            if (session.getAttribute("logIn") == null)
            {
                RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
                disp.forward(request, response);
            }
            else
            {
                RequestDispatcher disp = request.getRequestDispatcher("orderConfirm.jsp");
                disp.forward(request, response);
            }
        }

    }

}