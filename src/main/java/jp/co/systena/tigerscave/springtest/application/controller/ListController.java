package jp.co.systena.tigerscave.springtest.application.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.springtest.application.model.Cart;
import jp.co.systena.tigerscave.springtest.application.model.ListForm;
import jp.co.systena.tigerscave.springtest.application.model.ListService;
import jp.co.systena.tigerscave.springtest.application.model.Order;

@Controller // Viewあり。Viewを返却するアノテーション
public class ListController {

  @Autowired
  HttpSession session; // セッション管理


  @RequestMapping(value = "/productlist", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView index(ModelAndView mav, @Valid ListForm listForm) {

    // セッションからカート情報を取得する
    Cart carts = new Cart();
    carts.setOrderList((List<Order>)session.getAttribute("orderList"));

    // セッションにカート情報が存在しないときは新しく作る
    if (carts.getOrderList() == null) {
      carts = new Cart();
      session.setAttribute("orderList", carts.getOrderList());
    }

    mav.addObject("orderList", carts.getOrderList());

    // Viewに渡すデータを設定
    mav.addObject("items", ListService.getItemList());

    // 注文の合計金額を計算
    int total=0;
    for(int i=0; i<carts.getOrderList().size(); i++) {
      total += ListService.getItemList().get(carts.getOrderList().get(i).getItemId()).getPrice() * carts.getOrderList().get(i).getNum();
    }
    mav.addObject("total", total);

    // Viewのテンプレート名を設定
    mav.setViewName("productlist");
    return mav;
  }

  @RequestMapping(value = "/productlist", method = RequestMethod.POST) // URLとのマッピング
  private ModelAndView additem(ModelAndView mav, @Valid ListForm listForm,
      BindingResult bindingResult, HttpServletRequest request) {

    // セッションからカート情報を取得する
    Cart carts = new Cart();
    carts.setOrderList((List<Order>)session.getAttribute("orderList"));

    // セッションにカート情報が存在しないときは新しく作る
    if (carts.getOrderList() == null) {
      carts = new Cart();
      session.setAttribute("orderList", carts.getOrderList());
    }

    if (bindingResult.getAllErrors().size() > 0) {
      // エラーがある場合はそのまま戻す
      mav.addObject("listForm", listForm); // 新規クラスを設定

      mav.addObject("orderList", carts);

      // Viewのテンプレート名を設定
      mav.setViewName("productlist");
      return mav;

    }
    // Order order = new Order();
    // order.setItemId(listForm.getItemId());
    // order.setNum(listForm.getNum());

    if (carts.getOrderList()==null || carts.getOrderList().isEmpty()) {
      carts.getOrderList().add(new Order(listForm.getItemId(), listForm.getNum()));
    } else {
      boolean isOrdered = false;
      for (int i=0; i < carts.getOrderList().size(); i++) {
        // itemIdが一致するOrderに数を足す
        if (carts.getOrderList().get(i).getItemId() == listForm.getItemId()) {
          carts.getOrderList().get(i)
              .setNum(carts.getOrderList().get(i).getNum() + listForm.getNum());
          isOrdered=true;
          break;
        }
      }
      // itemIdが一致するOrderがない場合は新しく追加する
      if (isOrdered==false) {
        carts.getOrderList().add(new Order(listForm.getItemId(), listForm.getNum()));
      }
    }

    // データをセッションへ保存
    session.setAttribute("orderList", carts.getOrderList());

    return new ModelAndView("redirect:/productlist"); // リダイレクト
  }

}
