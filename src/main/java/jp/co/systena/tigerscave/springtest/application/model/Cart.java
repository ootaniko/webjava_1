package jp.co.systena.tigerscave.springtest.application.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

  private List<Order> orderList = new ArrayList<Order>();

  public List<Order> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }

}
