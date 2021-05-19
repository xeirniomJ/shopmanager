package org.haier.shop.controller.goods_kind;

import javax.annotation.Resource;

import org.haier.shop.service.Goods_kindService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/goods")
public class QueryKindsController {
	@Resource 
	private Goods_kindService kindService;
	
	@RequestMapping("/queryKinds.do")
	@ResponseBody
	public NoteResult execute(String shop_unique){
		return kindService.queryKinds(shop_unique);
	}
}
