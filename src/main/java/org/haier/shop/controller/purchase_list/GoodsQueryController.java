package org.haier.shop.controller.purchase_list;

import javax.annotation.Resource;

import org.haier.shop.service.ShopService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/goods")
public class GoodsQueryController {
	@Resource
	private ShopService shopService;
	
	@RequestMapping("/goodsQuery.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String sup_goods_kind_unique,String sup_goods_kind_parunique,String goods_message,Integer pageNum){
		return shopService.findPurchaseGoods(shop_unique, sup_goods_kind_unique, sup_goods_kind_parunique, goods_message,pageNum);
	}
}
