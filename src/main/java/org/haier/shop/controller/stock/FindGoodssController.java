package org.haier.shop.controller.stock;

import javax.annotation.Resource;

import org.haier.shop.service.StockService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stock")
public class FindGoodssController {
	@Resource
	private StockService stockService;
	@RequestMapping("/findGoodss.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String goodsmessage,String manager_unique){
		return stockService.findGoodss(shop_unique, goodsmessage,manager_unique);
	}
}
