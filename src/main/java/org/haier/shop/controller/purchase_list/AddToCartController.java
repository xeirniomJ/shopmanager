package org.haier.shop.controller.purchase_list;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/purchase")
public class AddToCartController {
	@Resource
	private Purchase_listService listService;
	
	@RequestMapping("/addToCart.do")
	@ResponseBody
	public NoteResult addToCart(String shop_unique, String goods_barcode, Integer purchase_list_detail_count,
			String supplier_unique, Double purchase_list_detail_total){
		return listService.addToCart(shop_unique, goods_barcode, purchase_list_detail_count, supplier_unique, purchase_list_detail_total);
	}
}
