package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class Good_findController {
	@Resource
	private GoodsService goodsService;
	@RequestMapping("/findGood.do")
	@ResponseBody
	public NoteResult execute(String goods_barcode,String shop_unique){
		return goodsService.findGood(goods_barcode,shop_unique);
	}
}
