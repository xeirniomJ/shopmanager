package org.haier.shop.controller.adjust;

import javax.annotation.Resource;

import org.haier.shop.service.AdjustService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adjust")
public class Adjust_cancelController {
	@Resource
	private AdjustService adjustService;
	
	@RequestMapping("/adjust_cancel.do")
	@ResponseBody
	public NoteResult execute(Integer adjust_id){
		return adjustService.adjust_cancel(adjust_id);
	}
}
