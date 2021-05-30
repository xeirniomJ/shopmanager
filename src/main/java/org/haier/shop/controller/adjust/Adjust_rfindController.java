package org.haier.shop.controller.adjust;

import javax.annotation.Resource;

import org.haier.shop.service.AdjustService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adjust")
public class Adjust_rfindController {
	@Resource 
	private AdjustService adjustService;
	
	@RequestMapping("/adjust_rfind.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String adjust_type,String goods_message){
		return adjustService.adjust_rfind(shop_unique, goods_message, adjust_type);
	}
}
