package org.haier.shop.controller.purchase_list;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/purchase")
public class NewPurchase_list {
	@Resource
	private Purchase_listService purchaseService;
	
	@RequestMapping("/newPurchase.do")
	@ResponseBody
	public NoteResult execute(String purchase_list_unique,String details){
		return purchaseService.newPurchase_list( purchase_list_unique,details);
	}
}
