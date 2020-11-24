package cart;

public class CartLogic {

  public static void execute(CartDTO cart, ProductDTO product) {
    double tax = 0.08;
    cart.getProduct().add(product);
    cart.setSubTotal(product.getPrice() * product.getQuantity() + cart.getSubTotal());
    cart.setTaxAmount((int)(cart.getSubTotal() * tax));
    cart.setTotal(cart.getSubTotal() + cart.getTaxAmount() + cart.getShipping());
  }
}
