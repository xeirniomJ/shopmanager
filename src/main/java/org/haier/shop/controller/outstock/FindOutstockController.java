package org.haier.shop.controller.outstock;

import javax.annotation.Resource;

import org.haier.shop.service.OutstockService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/outstock")
public class FindOutstockController {
	@Resource
	private OutstockService outService;
	@RequestMapping("/findOutstock.do")
	@ResponseBody
	public NoteResult execute(String outstock_unique){
		System.out.println(outstock_unique);
		return outService.findOutstock(outstock_unique);
	}
}
