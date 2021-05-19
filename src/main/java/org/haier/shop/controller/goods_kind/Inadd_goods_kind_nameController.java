package org.haier.shop.controller.goods_kind;

import javax.annotation.Resource;

import org.haier.shop.service.Goods_kindService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods_kind")
public class Inadd_goods_kind_nameController {
		@Resource
		private Goods_kindService kindService;
		
		@RequestMapping("/inadd_goods_kind_name.do")
		@ResponseBody
		public NoteResult execute(String goods_kind_parunique,String shop_unique){
			return kindService.inadd_goods_kind_name(goods_kind_parunique,shop_unique);
		}
}
