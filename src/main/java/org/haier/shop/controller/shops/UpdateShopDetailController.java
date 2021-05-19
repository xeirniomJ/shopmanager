package org.haier.shop.controller.shops;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.service.ShopService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shops")
public class UpdateShopDetailController {
	@Resource
	private ShopService shopService;
	
	@RequestMapping("/updateShopDetail.do")
	@ResponseBody
	public NoteResult execute(String shop_unique, String shop_name, String shop_phone,
			String shop_address_detail, String manager_pwd, HttpServletRequest request,String shop_remark,String power_supplier){
		return shopService.updateShopDetail(shop_unique, shop_name, shop_phone, shop_address_detail, manager_pwd, request,shop_remark,power_supplier);
	}
}
