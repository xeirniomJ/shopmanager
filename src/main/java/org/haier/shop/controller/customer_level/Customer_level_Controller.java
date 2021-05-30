package org.haier.shop.controller.customer_level;

import javax.annotation.Resource;

import org.haier.shop.service.Customer_levelService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer_level")
public class Customer_level_Controller {
	@Resource
	private Customer_levelService customer_levelService;
	
	@RequestMapping("/customer_level_list.do")
	@ResponseBody
	public NoteResult customer_level_list(){
		return customer_levelService.customer_level_list();
	}
}
