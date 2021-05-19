package org.haier.shop.controller.shops;

import javax.annotation.Resource;

import org.haier.shop.service.ShopService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindShopApplysController {
	@Resource
	private ShopService shopService;
	
	@RequestMapping("/html/shop/findShopApplys.do")
	@ResponseBody
	public NoteResult execute(String manager_unique,String manager_token){
		return shopService.findShopApplys(manager_unique, manager_token);
	}
}
