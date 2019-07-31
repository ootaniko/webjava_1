package jp.co.systena.tigerscave.springtest.application.model;

import java.util.ArrayList;
import java.util.List;

public class ListService {


  public static List<Item> getItemList() {

    List<Item> item = new ArrayList<Item>();

    item.add(new Item(0, "いちご", 100));
    item.add(new Item(1, "ばなな", 200));
    item.add(new Item(2, "ぶどう", 300));

    return item;
  }

}
