package org.haier.shop.controller.goods_kind;

import javax.annotation.Resource;

import org.haier.shop.service.Goods_kindService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods_kind")
public class QueryGoods_kindController {
	@Resource
	private Goods_kindService kindService;
	
	@RequestMapping("/queryGoods_kind.do")
	@ResponseBody
	public NoteResult execute(String goods_kind_parunique,String goods_kind_unique,String shop_unique,String manager_unique){
		return kindService.queryGoods_kind(goods_kind_parunique,goods_kind_unique,shop_unique,manager_unique);
	}
}
