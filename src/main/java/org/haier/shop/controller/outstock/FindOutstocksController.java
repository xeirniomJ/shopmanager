package org.haier.shop.controller.outstock;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.haier.shop.service.OutstockService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/outstock")
public class FindOutstocksController {
	@Resource
	private OutstockService outService;
	@RequestMapping("/findOutstocks.do")
	@ResponseBody
	public NoteResult execute(String shop_unique,Timestamp startDate,Timestamp endDate){
		return outService.findOutstocks(shop_unique, startDate, endDate);
	}
}
