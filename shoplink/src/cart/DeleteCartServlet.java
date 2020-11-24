package cart;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteCartServlet
 */
@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  int id = Integer.parseInt(request.getParameter("id"));
	  HttpSession session = request.getSession();
	  CartDTO cart = (CartDTO) session.getAttribute("cart");

	  Iterator<ProductDTO> itr = cart.getProduct().iterator();
	  while(itr.hasNext()) {
	    ProductDTO dto = itr.next();
	    if(dto.getId() == id) {
	      itr.remove();
	    }
	  }

	  CartLogic logic = new CartLogic();
	  TotalAmountDTO totalAmount = logic.execute(cart);
	  session.setAttribute("totalAmount", totalAmount);
	  session.setAttribute("cart", cart);
	  request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);

	}

}
