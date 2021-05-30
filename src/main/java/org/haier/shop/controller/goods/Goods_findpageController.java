package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/goods")
@Controller
public class Goods_findpageController {
	@Resource
	private GoodsService goodsService;
	
	@RequestMapping("/find_goods_page.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String goods_message,Integer page_num){
		return goodsService.findGood_page(shop_unique, goods_message, page_num);
	}
}
