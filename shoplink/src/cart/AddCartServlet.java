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
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  ProductDTO product = new ProductDTO();
    HttpSession session = request.getSession();
	  CartDTO cart = (CartDTO) session.getAttribute("cart");

	  if(cart == null) {
	    cart = new CartDTO();
	    product.setId(Integer.parseInt(request.getParameter("id")));
	    product.setTitle(request.getParameter("title"));
	    product.setArtist(request.getParameter("artist"));
	    product.setPrice(Integer.parseInt(request.getParameter("price")));
	    product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
	    product.setImages(request.getParameter("images").replace(".jpg", ""));

	  } else {
	  Iterator<ProductDTO> itr_cart = cart.getProduct().iterator();
	  boolean foundProduct = false;
	  while(itr_cart.hasNext()) {
	    ProductDTO dto = itr_cart.next();
	    if(dto.getId() == Integer.parseInt(request.getParameter("id"))) {
	      product.setId(Integer.parseInt(request.getParameter("id")));
	      product.setTitle(request.getParameter("title"));
	      product.setArtist(request.getParameter("artist"));
	      product.setPrice(Integer.parseInt(request.getParameter("price")));
	      product.setQuantity(Integer.parseInt(request.getParameter("quantity")) + dto.getQuantity());
	      product.setImages(request.getParameter("images").replace(".jpg", ""));
	      itr_cart.remove();
	      foundProduct = true;
	      break;
	    }
	  }
	  if(!foundProduct) {
      product.setId(Integer.parseInt(request.getParameter("id")));
      product.setTitle(request.getParameter("title"));
      product.setArtist(request.getParameter("artist"));
      product.setPrice(Integer.parseInt(request.getParameter("price")));
      product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
      product.setImages(request.getParameter("images").replace(".jpg", ""));
	  }
   }

	  cart.getProduct().add(product);
	  CartLogic logic = new CartLogic();
	  TotalAmountDTO totalAmount = logic.execute(cart);

    session.setAttribute("cart", cart);
    session.setAttribute("totalAmount", totalAmount);
    request.setAttribute("msg", product.getTitle() + "をカートに追加しました");
    request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);


	}
}
