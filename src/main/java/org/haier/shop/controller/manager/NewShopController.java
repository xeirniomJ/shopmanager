package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ShopService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shop")
public class NewShopController {
	@Resource
	private ShopService shopService;
	
	@RequestMapping("/newShop.do")
	@ResponseBody
	public NoteResult execute(String manager_unique, String account, String pwd, String shop_name,
			String shop_address_detail, String shop_phone){
		return shopService.newShop(manager_unique, account, pwd, shop_name, shop_address_detail, shop_phone);
	}
}
