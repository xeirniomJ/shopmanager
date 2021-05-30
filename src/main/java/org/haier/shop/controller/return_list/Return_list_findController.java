package org.haier.shop.controller.return_list;

import javax.annotation.Resource;

import org.haier.shop.service.Return_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/home/return_list")
public class Return_list_findController {
	@Resource
	private Return_listService retService;
	
	@RequestMapping("/return_list_find.do")
	@ResponseBody
	public NoteResult execute(String ret_list_unique){
		return retService.return_list_find(ret_list_unique);
	}
}
