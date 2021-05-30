package org.haier.shop.controller.sale_list;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.service.Sale_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sale_list")
public class Sale_excelController {
	@Resource
	private Sale_listService saleService;
	
	@RequestMapping("/excel.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,Integer sale_list_id, String customermessage, Timestamp startDate,
			Timestamp endDate, String sale_list_state, String sale_list_handlestate,HttpServletRequest request,String manager_unique){
		return saleService.excel(shop_unique, sale_list_id,  startDate, endDate, sale_list_state, sale_list_handlestate,request,manager_unique);
	}
}
