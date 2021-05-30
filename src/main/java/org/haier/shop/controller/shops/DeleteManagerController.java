package org.haier.shop.controller.shops;

import javax.annotation.Resource;

import org.haier.shop.service.ShopService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shops")
public class DeleteManagerController {
	@Resource
	private ShopService shopService;
	
	@RequestMapping("/deleteManager.do")
	@ResponseBody
	public NoteResult execute(String manager_account){
		return shopService.deleteManager(manager_account);
	}
}
