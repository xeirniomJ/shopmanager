package org.haier.shop.controller.return_list;

import javax.annotation.Resource;

import org.haier.shop.service.Return_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/home/return_list")
public class Return_list_saveController {
	@Resource
	private Return_listService retService;
	@RequestMapping("/return_list_save.do")
	@ResponseBody
	public NoteResult execute(String ret_list_unique,String ret_list_state,String ret_list_handlestate){
		return retService.return_list_save(ret_list_unique, ret_list_state, ret_list_handlestate);
	}
}
