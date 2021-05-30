package org.haier.shop.controller.aop;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect//将当前组件指定为切面组件
public class ExceptionBean {
	//异常通知方法定义，方法有一个Exception参数,
	//该参数就是目标方法抛出的异常对象
	@AfterThrowing(throwing="e",
pointcut="within(org.haier.shop.service..*)")
	public void doExecute(Exception e){
		//将异常信息写入文件
		//System.out.println("将异常信息写入文件"+e);
		System.out.println("开始记录异常日志");
		try{
			System.out.println("aaab");
			File f=new File("F:\\error.log");
			System.out.println(f.exists());
			System.out.println("创建文件成功");
			FileWriter fw = 	new FileWriter("F:\\error.log",true);
			System.out.println("文件");
			PrintWriter pw = new PrintWriter(fw);
			//打印异常头部
			System.out.println("创建异常日志");
			Date date = new Date();
			SimpleDateFormat sdf = 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(date);
			System.out.println("转换异常事件");
			pw.println("******************************");
			pw.println("*  异常类型："+e);
			pw.println("*  发生时间："+dateStr);
			pw.println("*************详情*************");
			//将异常栈信息写入error.log
			e.printStackTrace(pw);
			System.out.println("打印异常日志结束");
			pw.close();
			fw.close();
		}catch(Exception e1){
			System.out.println("记录日志发生错误");
		}
	}
}
