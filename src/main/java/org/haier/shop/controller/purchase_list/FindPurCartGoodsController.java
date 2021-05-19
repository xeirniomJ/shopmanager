package org.haier.shop.controller.purchase_list;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/purchase")
public class FindPurCartGoodsController {
	@Resource
	private Purchase_listService listService;
	
	@RequestMapping("/findPurCartGoods.do")
	@ResponseBody
	public NoteResult execute(String shop_unique){
		return listService.findPurCartGoods(shop_unique);
	}
}
