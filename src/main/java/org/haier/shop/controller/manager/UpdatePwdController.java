package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager")
public class UpdatePwdController {
	@Resource
	private ManagerService managerService;
	
	@RequestMapping("/updatePwd.do")
	@ResponseBody
	public NoteResult execute(String oldPwd,String manager_pwd,String manager_unique){
		return managerService.updatePwd(oldPwd, manager_unique, manager_pwd);
	}
}
