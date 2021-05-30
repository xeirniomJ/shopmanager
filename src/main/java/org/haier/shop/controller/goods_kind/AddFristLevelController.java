package org.haier.shop.controller.goods_kind;

import javax.annotation.Resource;

import org.haier.shop.service.Goods_kindService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddFristLevelController {
	@Resource
	private Goods_kindService kindService;
	
	@RequestMapping("/goods_kind/addFristLevel.do")
	@ResponseBody
	public NoteResult execute(){
		return kindService.addStairKind();
	}
}
