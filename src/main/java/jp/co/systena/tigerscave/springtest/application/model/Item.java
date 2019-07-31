package jp.co.systena.tigerscave.springtest.application.model;

public class Item {

  private int itemId;
  private String name;
  private int price;

  Item(int itemId, String name, int price){
    this.itemId=itemId;
    this.name=name;
    this.price=price;
  }

  public int getItemId() {
    return this.itemId;
  }
  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return this.price;
  }
  public void setPrice(int price) {
    this.price = price;
  }

}
