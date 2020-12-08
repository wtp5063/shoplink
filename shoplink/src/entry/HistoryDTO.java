package entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import database.dao.entity.DetailsEntity;

public class HistoryDTO implements Serializable {

  private List<DetailsEntity> list;
  private int id;
  private String datetime;
  private int totalAmount;

  public HistoryDTO() {
    super();
    this.list = new ArrayList<>();
  }

  public List<DetailsEntity> getList() {
    return list;
  }
  public void setList(List<DetailsEntity> list) {
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
