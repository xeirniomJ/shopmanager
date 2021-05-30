package org.haier.shop.controller.adjust;

import javax.annotation.Resource;

import org.haier.shop.service.AdjustService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adjust")
public class Adjust_ofindController {
	@Resource
	private AdjustService adjustService;
	
	@RequestMapping("/oadjust_find.do")
	@ResponseBody
	public NoteResult execute(String goods_message,String adjust_type,Integer page_num,String shop_unique,String manager_unique){
		return adjustService.oadjust_find(goods_message, adjust_type,page_num,shop_unique,manager_unique);
	}
}
