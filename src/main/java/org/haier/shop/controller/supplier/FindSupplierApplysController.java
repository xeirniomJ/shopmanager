package org.haier.shop.controller.supplier;

import javax.annotation.Resource;

import org.haier.shop.service.SupplierService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/supplier")
public class FindSupplierApplysController {
	@Resource
	private SupplierService supService;
	
	@RequestMapping("/findSupplierApplys.do")
	@ResponseBody
	public NoteResult execute(String manager_unique,String manager_token){
		return supService.findSupplierApplys(manager_unique, manager_token);
	}
}
