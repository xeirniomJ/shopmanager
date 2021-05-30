package org.haier.shop.controller.return_list;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.service.Return_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/return_list")
public class Return_excelController {
	@Resource
	private Return_listService retService;
	
	@RequestMapping("/excel.do")
	@ResponseBody
	public NoteResult execute(Integer ret_list_id, Timestamp startDate, Timestamp endDate, String ret_list_state,
			String ret_list_handlestate, String shop_unique,HttpServletRequest request,String manager_unique){
		return retService.excel(ret_list_id, startDate, endDate, ret_list_state, ret_list_handlestate, shop_unique,request,manager_unique);
	}
}
