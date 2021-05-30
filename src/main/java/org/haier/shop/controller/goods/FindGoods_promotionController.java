package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindGoods_promotionController {
	@Resource
	private GoodsService goodsService;
	@RequestMapping("/goods/findGoods_promotion.do")
	@ResponseBody
	public NoteResult execute(String goodsmessage,String goods_kind_parunique,String goods_kind_unique,String goods_promotion,String shop_unique,String manager_unique){
		return goodsService.findGoods_promotion(goodsmessage, goods_kind_parunique, goods_kind_unique, goods_promotion,shop_unique,manager_unique);
	}
}
