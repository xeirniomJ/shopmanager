package org.haier.shop.controller.adjust;

import javax.annotation.Resource;

import org.haier.shop.service.AdjustService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adjust")
public class Adjust_handlestatue_findController {
	@Resource
	private AdjustService adjustService;
	
	@RequestMapping("/adjust_handlestatue_find.do")
	@ResponseBody
	public NoteResult execute(){
		return adjustService.adjust_handlestatue_find();
	}
}
