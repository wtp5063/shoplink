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
 * Servlet implementation class EditQuantityServlet
 */
@WebServlet("/EditQuantityServlet")
public class EditQuantityServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  ProductDTO product = new ProductDTO();
	  int id = Integer.parseInt(request.getParameter("id"));
	  int quantity = Integer.parseInt(request.getParameter("quantity"));
	  HttpSession session = request.getSession();
	  CartDTO cart = (CartDTO) session.getAttribute("cart");

	  Iterator<ProductDTO> itr = cart.getProduct().iterator();
	  while(itr.hasNext()) {
	    ProductDTO dto = itr.next();
	    if(dto.getId() == id) {
	      product.setId(dto.getId());
	      product.setTitle(dto.getTitle());
	      product.setArtist(dto.getArtist());
	      product.setImages(dto.getImages());
	      product.setPrice(dto.getPrice());
	      product.setQuantity(quantity);
	      itr.remove();
	    }
	  }

	  cart.getProduct().add(product);
	  CartLogic logic = new CartLogic();
	  TotalAmountDTO totalAmount = logic.execute(cart);
	  session.setAttribute("cart", cart);
	  session.setAttribute("totalAmount", totalAmount);
	  request.setAttribute("msg", product.getTitle() + "の数量を" + quantity + "個に変更しました");

	  request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);

	}
}
