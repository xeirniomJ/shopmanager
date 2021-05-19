package org.haier.shop.controller.sale_list;

import javax.annotation.Resource;

import org.haier.shop.service.Sale_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/home/sale_list")
public class Index_alertController {
	@Resource
	private Sale_listService saleService;
	@RequestMapping("/index_alert.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String manager_unique){
		return saleService.index_alert(shop_unique,manager_unique);
	}
}
