package cart;

import java.io.Serializable;

public class ProductDTO implements Serializable {

  private int id;
  private String title;
  private String artist;
  private int price;
  private String images;
  private int quantity;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getArtist() {
    return artist;
  }
  public void setArtist(String artist) {
    this.artist = artist;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public String getImages() {
    return images;
  }
  public void setImages(String images) {
    this.images = images;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


}