package org.haier.shop.controller.sale_list;

import javax.annotation.Resource;

import org.haier.shop.service.Sale_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页未处理订单查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/html/home/sale_list")
public class Undo_saleController {
	@Resource
	private Sale_listService saleService;
	@RequestMapping("/undo_sale.do")
	@ResponseBody
	public NoteResult execeute(String manager_unique,String shop_unique){
		return saleService.undo_sale(manager_unique,shop_unique);
	}
}
