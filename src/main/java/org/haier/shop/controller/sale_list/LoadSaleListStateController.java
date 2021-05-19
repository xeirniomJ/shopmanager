package org.haier.shop.controller.sale_list;

import javax.annotation.Resource;

import org.haier.shop.service.Sale_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/html/home/saleList")
public class LoadSaleListStateController {
	@Resource
	private Sale_listService listService;
	
	@RequestMapping("/loadSaleListState.do")
	@ResponseBody
	public NoteResult loadState(){
		return listService.loadSaleListState();
	}
	
	@RequestMapping("/loadSaleListHandleState.do")
	@ResponseBody
	public NoteResult loadHandleStatue(){
		return listService.loadSaleListHandleState();
	}
}
