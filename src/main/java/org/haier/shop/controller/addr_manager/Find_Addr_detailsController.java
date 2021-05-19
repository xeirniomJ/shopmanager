package org.haier.shop.controller.addr_manager;

import javax.annotation.Resource;

import org.haier.shop.service.Addr_managerService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/addr_manager")
public class Find_Addr_detailsController {
	@Resource
	private Addr_managerService addrService;
	/**
	 * 根据会员编号，查询会员信息
	 * @param cus_id
	 * @return
	 */
	@RequestMapping("/findAddr_details.do")
	@ResponseBody
	public NoteResult execute(Integer cus_id){
		return addrService.findAddr_details(cus_id);
	}
	
	@RequestMapping("/findAddr_detail.do")
	@ResponseBody
	public NoteResult findAddr_detail(Integer addr_id){
		return addrService.findAddr_detail(addr_id);
	}
}
