package org.haier.shop.controller.sale_list;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.haier.shop.service.Sale_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sale_lists_findController {
	@Resource
	private Sale_listService saleService;
	@RequestMapping("/sale_list/sale_lists_find.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,Integer sale_list_id,  Timestamp startDate,
			Timestamp endDate, String sale_list_state, String sale_list_handlestate,String manager_unique){
		return saleService.sale_lists_find(shop_unique,sale_list_id, startDate, endDate, sale_list_state, sale_list_handlestate,manager_unique);
	}
	@RequestMapping("/html/home/sale_list/sale_list_find.do")
	@ResponseBody
	public NoteResult sale_list_find(Integer sale_list_id){
		return saleService.sale_list_find(sale_list_id);
	}
}
