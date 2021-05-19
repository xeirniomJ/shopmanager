package org.haier.shop.controller.purchase_list;

import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/purchase_list")
public class Purchase_excelController { 
	@Resource
	private Purchase_listService purService;
	@RequestMapping("/excel.do")
	@ResponseBody
	public NoteResult execute(String purchase_list_id, Date startDate, Date endDate, String shop_unique,HttpServletRequest request,String manager_unique){
		NoteResult ns=purService.findPurs(purchase_list_id, startDate, endDate, shop_unique,request,manager_unique);
		return ns;
	}
}
