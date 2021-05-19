package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@Resource
	private ManagerService managerService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult execute(String manager_account,String manager_pwd){
		return managerService.login(manager_account, manager_pwd);
	}
}
