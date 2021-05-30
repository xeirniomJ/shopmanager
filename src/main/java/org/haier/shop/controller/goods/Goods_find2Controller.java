package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 调货页面，根据商品编号和输入的商品信息，查询商品
 * @author Administrator
 *
 */
@RequestMapping("/goods")
@Controller
public class Goods_find2Controller {
	@Resource
	private GoodsService goodsService;
	@ResponseBody
	@RequestMapping("/find_goods.do")
	public  NoteResult execute(String shop_unique,String goods_message  ){
		return goodsService.findGoods2(shop_unique, goods_message);
	}
}
