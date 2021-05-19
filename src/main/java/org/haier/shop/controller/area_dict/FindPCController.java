package org.haier.shop.controller.area_dict;

import javax.annotation.Resource;

import org.haier.shop.service.Area_dictService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindPCController {
	@Resource
	private Area_dictService areaService;
	
	@RequestMapping("/area_dict/findPC.do")
	@ResponseBody
	public NoteResult execute(String area_dict_num){
		return  areaService.findPC(area_dict_num);
	}
}
