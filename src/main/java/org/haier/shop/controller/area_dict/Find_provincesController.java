package org.haier.shop.controller.area_dict;

import javax.annotation.Resource;

import org.haier.shop.service.Area_dictService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/area_dict")
public class Find_provincesController {
	@Resource
	private Area_dictService areaService;
	
	@RequestMapping("/find_provinces.do")
	@ResponseBody
	public NoteResult execute(){
		return areaService.find_provinces();
	}
}
