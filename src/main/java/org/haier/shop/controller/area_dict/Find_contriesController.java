package org.haier.shop.controller.area_dict;

import javax.annotation.Resource;

import org.haier.shop.service.Area_dictService;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/area_dict")
public class Find_contriesController {
	@Resource
	private Area_dictService areaService;
	
	@RequestMapping("/find_contries.do")
	@ResponseBody
	public NoteResult execute(String area_dict_num){
		return areaService.find_cities(area_dict_num);
	}
}
