package org.haier.shop.controller.shops;

import javax.annotation.Resource;

import org.haier.shop.service.ShopService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/shop")
public class PassExaminestatusController {
	@Resource
	private ShopService shopService;
	
	@RequestMapping("/passExaminestatus.do")
	@ResponseBody
	public NoteResult execuete(String manager_unique,String manager_token,String shop_unique,Integer examinestatus,String examinestatus_reason){
		return shopService.updateExamineStatus(manager_unique, manager_token, shop_unique, examinestatus, examinestatus_reason);
	}
}
