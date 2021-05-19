package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.SupplierService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindApplyController {
	@Resource
	private SupplierService supService;
	
	@RequestMapping("/html/manager/findApply.do")
	@ResponseBody
	public NoteResult execute(String manager_unique,String manager_token){
		return supService.findApplys(manager_unique,manager_token);
	}
}
