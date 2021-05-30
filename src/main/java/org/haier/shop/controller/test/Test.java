package org.haier.shop.controller.test;

import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
	@RequestMapping("/upload1.do")
	@ResponseBody
	public NoteResult execute(String ceshi){
		NoteResult ns=new NoteResult();
		System.out.println(ceshi);
		ns.setStatus(0);
		ns.setMsg("222");
		return ns;
	}
}