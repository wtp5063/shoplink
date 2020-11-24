package cart;

public class CartLogic {
    private static double tax = 0.08;

  public static void execute(CartDTO cart, ProductDTO product) {
    cart.getProduct().add(product);
    cart.setSubTotal(product.getPrice() * product.getQuantity() + cart.getSubTotal());
    cart.setTaxAmount((int)(cart.getSubTotal() * tax));
    cart.setTotal(cart.getSubTotal() + cart.getTaxAmount() + cart.getShipping());
  }

  public static void delete(CartDTO cart, int price) {
    cart.setSubTotal(cart.getSubTotal() - price);
    cart.setTaxAmount((int)(cart.getSubTotal() * tax));
    cart.setTotal(cart.getSubTotal() + cart.getTaxAmount() + cart.getShipping());
  }

  public static void update(CartDTO cart, ProductDTO product, int price) {
    cart.getProduct().add(product);
    cart.setSubTotal(cart.getSubTotal() - price + (product.getPrice() * product.getQuantity()));
    cart.setTaxAmount((int)(cart.getSubTotal() * tax));
    cart.setTotal(cart.getSubTotal() + cart.getTaxAmount() + cart.getShipping());
  }


}