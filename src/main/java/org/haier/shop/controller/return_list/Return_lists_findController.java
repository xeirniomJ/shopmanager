package org.haier.shop.controller.return_list;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.haier.shop.service.Return_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/return_list")
public class Return_lists_findController {
	@Resource
	private Return_listService retService;
	@RequestMapping("/return_lists_find.do")
	@ResponseBody
	public NoteResult return_list_find(Integer ret_list_id, Timestamp startDate, Timestamp endDate,
			String ret_list_state, String ret_list_handlestate,String shop_unique,String manager_unique){
		return retService.return_lists_find(ret_list_id, startDate, endDate, ret_list_state, ret_list_handlestate, shop_unique,manager_unique);
	}
}
