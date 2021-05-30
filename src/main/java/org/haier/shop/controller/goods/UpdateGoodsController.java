package org.haier.shop.controller.goods;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 更新商品明细
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/goods")
public class UpdateGoodsController {
	@Resource
	private GoodsService goodsService;
	@RequestMapping("/updateGoods.do")
	@ResponseBody
	public NoteResult execute(HttpServletRequest request,String goods_id,String goods_name,String goods_brand,String goods_barcode,String goods_alias,String goods_sale_price,String goods_in_price
			,String goods_life,String goods_points,String goods_address,String goods_contain,String goods_standard,String goods_kind_parunique,String goods_kind_unique,String shop_unique,Integer goods_count,String supplier_unique){
		return goodsService.updateGoods(request,goods_id, goods_name, goods_brand, goods_barcode, goods_alias, goods_sale_price, goods_in_price, goods_life, goods_points, goods_address,
				goods_contain, goods_standard, goods_kind_parunique, goods_kind_unique,shop_unique,goods_count,supplier_unique);
	}
}
