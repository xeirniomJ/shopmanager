package org.haier.shop.controller.exchange;

import javax.annotation.Resource;

import org.haier.shop.service.ExchangeService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/exchange")
public class SubmitExCartController {
	@Resource
	private ExchangeService exService;
	@ResponseBody
	@RequestMapping("/submitExCart.do")
	public NoteResult execute(String shop_unique,String unique){
		return exService.submitExCart(shop_unique, unique);
	}
}
