package org.haier.shop.controller.customer;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.haier.shop.service.CustomerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class UpdateCusController {
	@Resource
	public CustomerService cusService;
	@RequestMapping("/updateCus.do")
	@ResponseBody
	public NoteResult execute(Integer cus_id,String cus_pwd,String cus_name,String cus_sex,String cus_alias,String cus_phone,Timestamp cus_regedit_date,Timestamp cus_birthday,String cus_email){
		return cusService.updatecus(cus_id,cus_name, cus_sex, cus_alias, cus_phone, cus_regedit_date, cus_birthday,cus_email);
	}
}
