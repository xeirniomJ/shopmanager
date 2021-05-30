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
@Aspect//����ǰ���ָ��Ϊ�������
public class ExceptionBean {
	//�쳣֪ͨ�������壬������һ��Exception����,
	//�ò�������Ŀ�귽���׳����쳣����
	@AfterThrowing(throwing="e",
pointcut="within(org.haier.shop.service..*)")
	public void doExecute(Exception e){
		//���쳣��Ϣд���ļ�
		//System.out.println("���쳣��Ϣд���ļ�"+e);
		System.out.println("��ʼ��¼�쳣��־");
		try{
			System.out.println("aaab");
			File f=new File("F:\\error.log");
			System.out.println(f.exists());
			System.out.println("�����ļ��ɹ�");
			FileWriter fw = 	new FileWriter("F:\\error.log",true);
			System.out.println("�ļ�");
			PrintWriter pw = new PrintWriter(fw);
			//��ӡ�쳣ͷ��
			System.out.println("�����쳣��־");
			Date date = new Date();
			SimpleDateFormat sdf = 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(date);
			System.out.println("ת���쳣�¼�");
			pw.println("******************************");
			pw.println("*  �쳣���ͣ�"+e);
			pw.println("*  ����ʱ�䣺"+dateStr);
			pw.println("*************����*************");
			//���쳣ջ��Ϣд��error.log
			e.printStackTrace(pw);
			System.out.println("��ӡ�쳣��־����");
			pw.close();
			fw.close();
		}catch(Exception e1){
			System.out.println("��¼��־��������");
		}
	}
}
