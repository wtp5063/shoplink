package cart;

import java.io.Serializable;

public class TotalAmountDTO implements Serializable {

  private int subTotal;
  private int taxAmount;
  private int shipping;
  private int totalAmount;

  public int getSubTotal() {
    return subTotal;
  }
  public void setSubTotal(int subTotal) {
    this.subTotal = subTotal;
  }
  public int getTaxAmount() {
    return taxAmount;
  }
  public void setTaxAmount(int taxAmount) {
    this.taxAmount = taxAmount;
  }
  public int getShipping() {
    return shipping;
  }
  public void setShipping(int shipping) {
    this.shipping = shipping;
  }
  public int getTotalAmount() {
    return totalAmount;
  }
  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }
}
