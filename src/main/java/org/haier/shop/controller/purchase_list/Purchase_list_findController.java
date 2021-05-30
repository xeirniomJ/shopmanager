package org.haier.shop.controller.purchase_list;

import java.sql.Date;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/purchase_list")
public class Purchase_list_findController{
	@Resource
	private Purchase_listService purchase_listService;
	
	@RequestMapping("/purchase_list_find.do")
	@ResponseBody
	public NoteResult purchase_list_find(String purchase_list_id,Date startDate,Date endDate,String shop_unique,String manager_unique){
		return purchase_listService.purchase_list_find(purchase_list_id, startDate, endDate,shop_unique,manager_unique);
	}
}
