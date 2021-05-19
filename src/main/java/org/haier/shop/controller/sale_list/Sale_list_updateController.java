package org.haier.shop.controller.sale_list;

import javax.annotation.Resource;

import org.haier.shop.service.Sale_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/home/sale_list")
public class Sale_list_updateController {
	@Resource
	private Sale_listService saleService;
	
	@RequestMapping("/sale_list_save.do")
	@ResponseBody
	public NoteResult execute(Integer sale_list_id, Double sale_list_delfee, Double sale_list_total,
			String sale_list_remarks, String sale_list_state, String sale_list_handlestate){
		return saleService.sale_list_update(sale_list_id, sale_list_delfee, sale_list_total, sale_list_remarks, sale_list_state, sale_list_handlestate);
	}
}
