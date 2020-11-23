package cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDTO implements Serializable {

 private List<ProductDTO> product;
 private int subTotal;
 private int taxAmount;
 private int shipping;
 private int total;

 public CartDTO() {
   super();
   this.product = new ArrayList<>();
   this.subTotal = 0;
   this.taxAmount = 0;
   this.shipping = 2000;
   this.total = 0;
 }

public List<ProductDTO> getProduct() {
  return product;
}
public void setProduct(List<ProductDTO> list) {
  this.product = list;
}

public int getSubTotal() {
  return subTotal;
}

public void setSubTotal(int subTotal) {
  this.subTotal = subTotal;
}

public int getTaxAmount() {
  return taxAmount;
}

public void setTaxAmount(int d) {
  this.taxAmount = d;
}

public int getShipping() {
  return shipping;
}

public int getTotal() {
  return total;
}
public void setTotal(int total) {
  this.total = total;
}

}
