package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager")
public class FindShopManagersController {
	@Resource
	private ManagerService managerService;
	
	@RequestMapping("/findShopManagers.do")
	@ResponseBody
	public NoteResult execute(String manager_unique){
		return managerService.findShopManagers(manager_unique);
	}
}
