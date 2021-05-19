package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class Goods_findController {
	@Resource
	private GoodsService goodsService;
	@RequestMapping("/findGoods.do")
	@ResponseBody
	public NoteResult goods_find(String goodsmessage,String goods_kind_parunique,String goods_kind_unique,String shop_unique,String manager_unique){
		return goodsService.findGoods(goodsmessage, goods_kind_parunique, goods_kind_unique,shop_unique,manager_unique);
	}
}
