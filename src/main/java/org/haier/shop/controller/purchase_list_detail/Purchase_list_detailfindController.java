package org.haier.shop.controller.purchase_list_detail;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_list_detailService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/purchase_list_detail")
public class Purchase_list_detailfindController {
	@Resource
	private Purchase_list_detailService purchase_list_detailService;
	
	@RequestMapping("/purchase_list_detail_find.do")
	@ResponseBody
	public NoteResult purchase_list_detailfind(String purchase_list_unique){
		return purchase_list_detailService.purchase_list_detail_find(purchase_list_unique);
	}
}
