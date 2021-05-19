package org.haier.shop.controller.supplier;

import javax.annotation.Resource;

import org.haier.shop.service.SupplierService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/supplier")
public class FindSuppliersController {
	@Resource
	private SupplierService supService;
	
	@RequestMapping("/findSuppliers.do")
	@ResponseBody
	public NoteResult execute(String manager_unique, String manager_token, String parea_dict_num,
			String carea_dict_num, String area_dict_num, String examinestatus, String message){
		return supService.findSuppliers(manager_unique, manager_token, parea_dict_num, carea_dict_num, area_dict_num, examinestatus, message);
	}
}
