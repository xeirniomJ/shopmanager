package org.haier.shop.controller.customer;

import javax.annotation.Resource;

import org.haier.shop.service.CustomerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class Cus_findController {
	@Resource
	private CustomerService cusService;
	
	@RequestMapping("/findCus.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,Integer cus_id){
		return cusService.findCus(shop_unique, cus_id);
	}
}
