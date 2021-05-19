package org.haier.shop.controller.test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.haier.shop.util.NoteResult;
import org.haier.shop.util.PicSaveUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class PicController {
	@RequestMapping("/upload.do")
	@ResponseBody
	public NoteResult execute( HttpServletRequest request,MultipartFile file,String province,HttpServletResponse response,String ceshi) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf8");
		NoteResult ns=new NoteResult();
//		String ceshi=request.getParameter("ceshi");
		System.out.println(ceshi);
		System.out.println(URLDecoder.decode(ceshi,"utf-8"));
		String path = request.getSession().getServletContext().getRealPath("upload");
        // String fileName = new Date().getTime()+".jpg";
        System.out.println(path);
        File fi=new File(path);
        System.out.println(file.getSize());
        if(!fi.exists()){
        	fi.mkdirs();
        }
        String picName=new Date().getTime()+"";
        PicSaveUtil.handleFileUpId(file, request, path, picName);
        ns.setStatus(0);
        ns.setMsg("成功");
        ns.setData(ceshi);
		return ns;
	}
}