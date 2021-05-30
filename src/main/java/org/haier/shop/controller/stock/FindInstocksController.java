package org.haier.shop.controller.stock;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.haier.shop.service.InstockService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/instock")
public class FindInstocksController {
	@Resource
	private InstockService instockService;
	
	@RequestMapping("/findInstocks.do")
	@ResponseBody
	public NoteResult execute(Timestamp startDate,Timestamp endDate,String shop_unique,String manager_unique){
		return instockService.findInstocks(startDate, endDate, shop_unique,manager_unique);
	}
}
