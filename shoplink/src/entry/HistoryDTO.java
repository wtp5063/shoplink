package entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoryDTO implements Serializable {

  private List<DetailsDTO> list;
  private int id;
  private String datetime;
  private int totalAmount;

  public HistoryDTO() {
    super();
    this.list = new ArrayList<>();
  }

  public List<DetailsDTO> getList() {
    return list;
  }
  public void setList(List<DetailsDTO> list) {
    this.list = list;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getDatetime() {
    return datetime;
  }
  public void setDatetime(String datetime) {
    this.datetime = datetime;
  }
  public int getTotalAmount() {
    return totalAmount;
  }
  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }
}
