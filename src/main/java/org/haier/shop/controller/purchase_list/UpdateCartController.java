package org.haier.shop.controller.purchase_list;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/purchase")
public class UpdateCartController {
	@Resource
	private Purchase_listService listService;
	
	@RequestMapping("/updateCart.do")
	@ResponseBody
	public NoteResult execute(String supplier_unique,String goods_barcode,Integer purchase_list_detail_count,String purchase_list_unique){
		return listService.updateCart( goods_barcode, purchase_list_detail_count, supplier_unique,purchase_list_unique);
	}
}
