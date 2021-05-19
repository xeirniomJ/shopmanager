package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class DeleteGoodsController {
	@Resource
	private GoodsService goodsService;
	
	@RequestMapping("/deleteGoods.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String goods_barcode){
		return goodsService.deleteGoods(goods_barcode, shop_unique);
	}
}
