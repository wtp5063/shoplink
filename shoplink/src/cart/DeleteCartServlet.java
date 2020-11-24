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
	  int price = Integer.parseInt(request.getParameter("price"));
	  int quantity = Integer.parseInt(request.getParameter("quantity"));
	  HttpSession session = request.getSession();
	  CartDTO cart = (CartDTO) session.getAttribute("cart");

	  Iterator<ProductDTO> itr = cart.getProduct().iterator();
	  while(itr.hasNext()) {
	    ProductDTO dto = itr.next();
	    if(dto.getId() == id) {
	      itr.remove();
	    }
	  }
	  CartLogic.delete(cart, price * quantity);
	  session.setAttribute("cart", cart);
	  request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);

	}

}
