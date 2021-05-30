package org.haier.shop.controller.adjust;

import javax.annotation.Resource;

import org.haier.shop.service.AdjustService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/adjust")
public class Adjust_updateController {
	@Resource
	private AdjustService adjustService;
	
	/**
	 * 响应其他店铺申请
	 * @param shop_unique
	 * @param adjust_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/adjust_update.do")
	public NoteResult execute(String shop_unique,String adjust_id){
		return adjustService.adjust_update(shop_unique, adjust_id);
	}
}

