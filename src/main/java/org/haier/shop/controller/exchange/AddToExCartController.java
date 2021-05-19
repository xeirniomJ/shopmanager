package org.haier.shop.controller.exchange;

import javax.annotation.Resource;

import org.haier.shop.service.ExchangeService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/exchange")
public class AddToExCartController {
	@Resource
	private ExchangeService exchangeService;
	
	@RequestMapping("/addToExCart.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String goods_barcode,Double exchange_list_detail_count,String supplier_unique){
		return exchangeService.addToExCart(shop_unique, goods_barcode, exchange_list_detail_count, supplier_unique);
	}
}
