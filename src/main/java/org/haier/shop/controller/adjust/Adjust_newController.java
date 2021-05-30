package org.haier.shop.controller.adjust;

import java.sql.Date;

import javax.annotation.Resource;

import org.haier.shop.service.AdjustService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/adjust")
public class Adjust_newController {
	@Resource
	private AdjustService adjustService;
	
	@RequestMapping("/adjust_new_save.do")
	@ResponseBody
	public NoteResult execute(String shop_unique, String goods_barcode, Double adjust_count,
			Double adjust_price, String adjust_type, Date adjust_validity,String goods_name,String adjust_remark){
		return adjustService.adjust_new_save(shop_unique, goods_barcode, adjust_count, adjust_price, adjust_type, adjust_validity, goods_name,adjust_remark);
	}
}
