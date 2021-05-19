package org.haier.shop.controller.goods_kind;

import javax.annotation.Resource;

import org.haier.shop.service.Goods_kindService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods_kind")
public class AddGoodsGroupsController {
	@Resource
	private Goods_kindService kindService;
	
	@RequestMapping("/addGoodsGroups.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String manager_unique){
		return kindService.add_goods_kind_group(shop_unique, manager_unique);
	}
}
