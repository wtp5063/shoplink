package cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDTO implements Serializable {

 private List<ProductDTO> product;

 public CartDTO() {
   super();
   this.product = new ArrayList<>();
 }

public List<ProductDTO> getProduct() {
  return product;
}
public void setProduct(List<ProductDTO> list) {
  this.product = list;
}

}