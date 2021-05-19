package org.haier.shop.controller.sale_list;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.haier.shop.service.Sale_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sale_list")
public class CheckNewOrderController {
	@Resource
	private Sale_listService saleService;
	
	@RequestMapping("/checkNewOrder.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String manager_unique,Timestamp startTime){
		return saleService.checkNewOrder(shop_unique, manager_unique, startTime);
	}
}
