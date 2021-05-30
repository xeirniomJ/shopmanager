package org.haier.shop.controller.goods;

import javax.annotation.Resource;

import org.haier.shop.service.GoodsService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class FindGoodsSupsController {
	@Resource
	private GoodsService goodsService;
	
	@RequestMapping("/findGoodsSups.do")
	@ResponseBody
	public NoteResult execute(String goods_barcode,String area_dict_num){
		return goodsService.findGoodsSuppliers(goods_barcode, area_dict_num);
	}
}
