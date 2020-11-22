package entry;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
  private int id;
  private String name;
  private String email;
  private String address;
  private String tel;
  private int admin;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getAdmin() {
    return admin;
  }
  public void setAdmin(int admin) {
    this.admin = admin;
  }

}
