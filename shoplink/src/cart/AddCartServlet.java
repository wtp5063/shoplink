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
    HttpSession session = request.getSession();
	  CartDTO cart = (CartDTO) session.getAttribute("cart");

	  if(cart == null) {
	    cart = new CartDTO();
	    ProductDTO product = new ProductDTO();
	    product.setId(Integer.parseInt(request.getParameter("id")));
	    product.setTitle(request.getParameter("title"));
	    product.setArtist(request.getParameter("artist"));
	    product.setPrice(Integer.parseInt(request.getParameter("price")));
	    product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
	    product.setImages(request.getParameter("images").replace(".jpg", ""));
	    CartLogic.execute(cart, product);
	    session.setAttribute("cart", cart);
	    request.setAttribute("msg", product.getTitle() + "をカートに追加しました");
	  }

	  Iterator<ProductDTO> itr_cart = cart.getProduct().iterator();
	  boolean foundProduct = false;
	  while(itr_cart.hasNext()) {
	    ProductDTO dto = itr_cart.next();
	    if(dto.getImages() == request.getParameter("images").replace(".jpg", "") ) {
	      ProductDTO product = new ProductDTO();
	      product.setId(Integer.parseInt(request.getParameter("id")));
	      product.setTitle(request.getParameter("title"));
	      product.setArtist(request.getParameter("artist"));
	      product.setPrice(Integer.parseInt(request.getParameter("price")));
	      product.setQuantity(Integer.parseInt(request.getParameter("quantity")) + dto.getQuantity());
	      product.setImages(request.getParameter("images").replace(".jpg", ""));
	      CartLogic.execute(cart, product);
	      session.setAttribute("cart", cart);
	      request.setAttribute("msg", product.getTitle() + "をカートに追加しました");
	      itr_cart.remove();
	    } else {
	      foundProduct = true;
	    }
	  }
	  if(foundProduct) {
	    ProductDTO product = new ProductDTO();
      product.setId(Integer.parseInt(request.getParameter("id")));
      product.setTitle(request.getParameter("title"));
      product.setArtist(request.getParameter("artist"));
      product.setPrice(Integer.parseInt(request.getParameter("price")));
      product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
      product.setImages(request.getParameter("images").replace(".jpg", ""));
      CartLogic.execute(cart, product);
      session.setAttribute("cart", cart);
      request.setAttribute("msg", product.getTitle() + "をカートに追加しました");
	  }

    request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);


	}
}
