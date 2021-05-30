package org.haier.shop.controller.manager;

import javax.annotation.Resource;

import org.haier.shop.service.ManagerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UpdateManagerDetailController {
	@Resource
	private ManagerService manService;

	@RequestMapping("/html/manager/updateManagerDetail.do")
	@ResponseBody
	public NoteResult execute(String manager_unique, String manager_name, String manager_token,
			String manager_account, String manager_phone, String submanager_unique, String manager_level,
			String power_createManager, String power_modifyFunction, String power_examShop, String power_examSup,
			String power_forbidCus) {
		return manService.updateManagerDetail(manager_unique, manager_name, manager_token, manager_account,
				manager_phone, submanager_unique, manager_level, power_createManager, power_modifyFunction, power_examShop,
				power_examSup, power_forbidCus);
	}
}
