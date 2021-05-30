package org.haier.shop.controller.test;

import javax.servlet.http.HttpServletRequest;

import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormController {
	@RequestMapping("/form.do")
	@ResponseBody
	public NoteResult execute(HttpServletRequest request){
		NoteResult ns=new NoteResult();
		String ceshi=request.getParameter("ceshi");
		System.out.println(ceshi);
		ns.setStatus(0);
		ns.setMsg(ceshi);
		return ns;
	}
}
