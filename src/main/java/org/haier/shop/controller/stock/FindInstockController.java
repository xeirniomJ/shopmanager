package org.haier.shop.controller.stock;

import javax.annotation.Resource;

import org.haier.shop.service.InstockService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 查询入库单详情
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/instock")
public class FindInstockController {
	@Resource
	private InstockService instockService;
	
	@RequestMapping("/findInstock.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,Integer instock_id){
		return instockService.findInstock(shop_unique, instock_id);
	}
}
