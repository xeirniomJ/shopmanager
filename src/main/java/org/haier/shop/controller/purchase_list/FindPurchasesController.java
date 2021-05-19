package org.haier.shop.controller.purchase_list;

import javax.annotation.Resource;

import org.haier.shop.service.Purchase_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 查询周期内订货总金额
 * 一天，一周，一月，一年，当年
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/purchase_list")
public class FindPurchasesController {
	@Resource
	private Purchase_listService purService;
	@RequestMapping("/findPurchases.do")
	@ResponseBody
	public NoteResult execute(String shop_unique){
		return purService.findPurchases(shop_unique);
	}
}
