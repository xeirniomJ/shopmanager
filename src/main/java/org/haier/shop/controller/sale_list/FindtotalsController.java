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
public class FindtotalsController {
	@Resource
	private Sale_listService saleService;
	
	@RequestMapping("/findtotals.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,Timestamp startDate,Timestamp endDate,String manager_unique){
		return saleService.findSales_details(shop_unique,startDate,endDate,manager_unique);
	}
}
