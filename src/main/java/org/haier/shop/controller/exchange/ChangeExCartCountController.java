package org.haier.shop.controller.exchange;

import javax.annotation.Resource;

import org.haier.shop.service.ExchangeService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/exchange")
public class ChangeExCartCountController {
	@Resource
	private ExchangeService exchangeService;
	
	@ResponseBody
	@RequestMapping("/changeExCartCount.do")
	public NoteResult execute(String shop_unique,String goods_barcode,Double goods_count){
		return exchangeService.changeExCartGoods(shop_unique, goods_barcode, goods_count);
	}
}
