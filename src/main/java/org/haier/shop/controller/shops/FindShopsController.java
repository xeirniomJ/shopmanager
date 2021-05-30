package org.haier.shop.controller.shops;

import javax.annotation.Resource;

import org.haier.shop.service.ShopService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/shop")
public class FindShopsController {
	@Resource
	private ShopService shopService;
	
	@RequestMapping("/findShops.do")
	@ResponseBody
	public NoteResult execute(String manager_token,String manager_unique,String message,Integer examinestatus,String area_dict_num,Integer pageNum,String parea_dict_num
			,String carea_dict_num){
		return shopService.findShopDetails(manager_unique, manager_token, examinestatus, message, pageNum, parea_dict_num, carea_dict_num, area_dict_num);
	}
}
