package org.haier.shop.controller.customer;

import javax.annotation.Resource;

import org.haier.shop.service.CustomerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 会员送货地址管理界面，根据输入客户信息，查询满足条件客户
 * @author Administrator
 */
@Controller
@RequestMapping("/customer")
public class FindCustsController {
	@Resource
	private CustomerService cusService;
	@RequestMapping("/findCusts.do")
	@ResponseBody
	public NoteResult execute(String customermessage){
		return cusService.findCusts(customermessage);
	}
}
