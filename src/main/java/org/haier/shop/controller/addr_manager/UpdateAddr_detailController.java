package org.haier.shop.controller.addr_manager;

import javax.annotation.Resource;

import org.haier.shop.service.Addr_managerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/addr_manager")
public class UpdateAddr_detailController {
	@Resource
	private Addr_managerService addrService;
	@RequestMapping("/updateAddr_detail.do")
	@ResponseBody
	
	public NoteResult updateAddr_manager(Integer addr_id,String addr_receiver_name,String addr_phone,String addr_addr,String addr_province,String addr_city,String addr_county,String addr_default,String cus_unique){
		return addrService.updateAddr_detail(addr_id, addr_receiver_name, addr_phone, addr_addr, addr_province, addr_city, addr_county, addr_default,cus_unique);
	}
}
