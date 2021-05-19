package org.haier.shop.controller.purchase_list;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class FindGoodsSuppliersController {
	@Resource
	private Purchase_listService listService;
	
	@RequestMapping("/findGoodsSuppliers.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String goods_barcode){
		 return listService.findGoodsSuppliers(goods_barcode, shop_unique);
	}
}
