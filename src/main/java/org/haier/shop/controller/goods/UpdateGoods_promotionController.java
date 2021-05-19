package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class UpdateGoods_promotionController {
	@Resource
	private GoodsService goodsService;
	@RequestMapping("/updateGoods_promotion.do")
	@ResponseBody
	public NoteResult execute(String goods_barcode,String goods_promotion,Double goods_discount,Double goods_sale_price,String shop_unique){
		return goodsService.updateGoods_promotion(goods_barcode, goods_promotion, goods_discount, goods_sale_price,shop_unique);
	}
}
