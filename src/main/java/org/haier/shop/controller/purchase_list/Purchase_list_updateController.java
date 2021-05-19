package org.haier.shop.controller.purchase_list;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/purchase_list")
public class Purchase_list_updateController {
	@Resource
	private Purchase_listService purchase_listService;
	
	@RequestMapping("/purchase_list_update.do")
	@ResponseBody
	public NoteResult execute(String purchase_list_unique,String purchase_list_statue){
		return purchase_listService.purchase_list_update(purchase_list_unique, purchase_list_statue);
	}
}
