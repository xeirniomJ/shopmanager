package org.haier.shop.controller.return_list;

import javax.annotation.Resource;

import org.haier.shop.service.Return_listService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页未处理订单查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/html/home/return_list")
public class Return_undoController {
	@Resource
	private Return_listService retService;
	@RequestMapping("/undo_ret.do")
	@ResponseBody
	public NoteResult execute(String manager_unique,String shop_unique){
		return retService.undo_ret(manager_unique,shop_unique);
	}
}
