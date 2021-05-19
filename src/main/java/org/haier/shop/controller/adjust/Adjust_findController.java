package org.haier.shop.controller.adjust;

import javax.annotation.Resource;

import org.haier.shop.service.AdjustService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adjust")
public class Adjust_findController {
	@Resource
	private AdjustService adjustService;
	
	@RequestMapping("/adjust_find.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String adjust_statue,String adjust_handlestatue,String adjust_type,Integer page_num,String manager_unique){
		return adjustService.adjust_find(shop_unique, adjust_statue, adjust_handlestatue,adjust_type,page_num,manager_unique);
	}
}
