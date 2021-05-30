package org.haier.shop.controller.exchange;

import javax.annotation.Resource;

import org.haier.shop.service.ExchangeService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/exchange")
public class FindExCartCountController {
	@Resource
	private ExchangeService exchangeService;
	
	@RequestMapping("/findExCartGoods.do")
	@ResponseBody
	public NoteResult execute(String shop_unique){
		return exchangeService.FindExchangeGoods(shop_unique);
	}
}
