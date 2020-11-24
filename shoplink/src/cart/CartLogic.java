package cart;

import java.util.List;

public class CartLogic {

    private double tax;
    private int taxAmount;
    private int subTotal;
    private int total;
    private int shipping;

    public CartLogic() {
      this.tax = 0.08;
      this.taxAmount = 0;
      this.subTotal = 0;
      this.shipping = 2000;
      this.total = 0;
    }


  public TotalAmountDTO execute(CartDTO cart) {
   List <ProductDTO> products = cart.getProduct();
   for(ProductDTO dto : products) {
     this.subTotal = dto.getPrice() * dto.getQuantity() + this.subTotal;
   }
   this.taxAmount = (int)(this.subTotal * this.tax);
   this.total = this.subTotal + this.taxAmount + this.shipping;

   TotalAmountDTO totalAmount = new TotalAmountDTO();
   totalAmount.setSubTotal(this.subTotal);
   totalAmount.setTaxAmount(this.taxAmount);
   totalAmount.setShipping(this.shipping);
   totalAmount.setTotalAmount(this.total);

   return totalAmount;

  }
}