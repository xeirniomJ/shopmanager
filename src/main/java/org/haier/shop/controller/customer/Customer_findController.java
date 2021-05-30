package org.haier.shop.controller.customer;

import javax.annotation.Resource;

import org.haier.shop.service.CustomerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class Customer_findController {
	@Resource
	private CustomerService cusService;
	
	@RequestMapping("/findCuss.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String customermessage,String manager_unique){
		return cusService.findCuss(shop_unique,customermessage,manager_unique);
	}
}
