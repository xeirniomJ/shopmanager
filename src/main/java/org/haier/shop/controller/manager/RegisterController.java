package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
	@Resource
	private ManagerService manService;
	
	@RequestMapping("/register.do")
	@ResponseBody
	public NoteResult execute(String manager_account, String manager_pwd,  String manager_phone,
			String manager_name){
		return manService.register(manager_account, manager_pwd,manager_phone, manager_name);
	}
}
