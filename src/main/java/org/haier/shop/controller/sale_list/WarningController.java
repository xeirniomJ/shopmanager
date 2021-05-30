package org.haier.shop.controller.sale_list;

import javax.annotation.Resource;

import org.haier.shop.service.Sale_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WarningController {
	@Resource
	private Sale_listService saleService;
	
	@RequestMapping("/warning.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,String manager_unique){
		return saleService.warning(shop_unique,manager_unique);
	}
}
