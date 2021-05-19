package org.haier.shop.controller.shops;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Shops_uniquefindController {
	@Resource
	private ManagerService managerService;
	
	@RequestMapping("/shops_uniquefind.do")
	@ResponseBody
	public NoteResult execute(String manager_unique,String shop_unique){
		return managerService.shops_uniquefind(manager_unique,shop_unique);
	}
}
