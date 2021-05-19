package org.haier.shop.controller.sup_goods_kind;

import javax.annotation.Resource;

import org.haier.shop.service.Sup_goods_kindService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/exchange/goods")
public class FindParUniqueController {
	@Resource
	private Sup_goods_kindService kindService;
	
	@RequestMapping("/findParNames.do")
	@ResponseBody
	public NoteResult execute(){
		return kindService.findParNames();
	}
	@RequestMapping("/findNames.do")
	@ResponseBody
	public NoteResult findNanmes(String sup_goods_kind_parunique){
		return kindService.findNames(sup_goods_kind_parunique);
	}
}
