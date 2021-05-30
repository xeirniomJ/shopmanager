package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/manager")
public class UpdateManagerMessageController {
	@Resource
	private ManagerService managerService;
	
	@RequestMapping("/updateManagerMessage.do")
	@ResponseBody
	public NoteResult execute(String manager_unique,String manager_name,String manager_phone){
		return managerService.updateManagerMessage(manager_unique, manager_name, manager_phone);
	}
}
