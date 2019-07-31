package jp.co.systena.tigerscave.springtest.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.springtest.application.model.ListService;

@Controller // Viewあり。Viewを返却するアノテーション
public class ListController {

  @RequestMapping(value = "/productlist", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView index(ModelAndView mav) {
    // Viewに渡すデータを設定
    mav.addObject("items", ListService.getItemList());

    // Viewのテンプレート名を設定
    mav.setViewName("productlist");
    return mav;
  }
}
