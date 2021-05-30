package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QueryManagerDetailController {
	@Resource
	private ManagerService manService;
	
	@RequestMapping("/html/manager/queryManagerDetail.do")
	@ResponseBody
	public NoteResult execute(String manager_unique,String manager_token ,String submanager_unique){
		return manService.queryManagerMessage(manager_unique, manager_token, submanager_unique);
	}
}
