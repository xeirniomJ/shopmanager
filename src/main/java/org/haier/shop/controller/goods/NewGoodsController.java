package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class NewGoodsController {
	@Resource
	private GoodsService goodsService;
	@RequestMapping("/newGoods.do")
	@ResponseBody
	public NoteResult execute(Integer goods_id, String goods_name, String goods_brand, String goods_barcode,
			String goods_alias, Double goods_sale_price, Double goods_in_price, Integer goods_life,
			Integer goods_points, String goods_address, Integer goods_contain, String goods_standard,
			String goods_kind_parunique, String goods_kind_unique, String shop_unique,String manager_unique){
		return goodsService.newGoods(goods_name, goods_brand, goods_barcode, 
				goods_alias, goods_sale_price, goods_in_price, goods_life, goods_points, goods_address, goods_contain, goods_standard, goods_kind_parunique, goods_kind_unique, shop_unique,manager_unique);
	}
}
