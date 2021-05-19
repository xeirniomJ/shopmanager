package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/manager")
public class FindManagerMessageController {
	@Resource
	private ManagerService managerService;
	
	@RequestMapping("findManagerMessage.do")
	@ResponseBody
	public NoteResult  execute(String manager_unique,String manager_token){
		return managerService.findManagerMessage(manager_unique, manager_token);
	}
}
