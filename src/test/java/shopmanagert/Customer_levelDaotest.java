package shopmanagert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.Customer_levelDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Customer_levelDaotest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Customer_levelDao dao=ac.getBean("customer_levelDao",Customer_levelDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list=dao.findCustomer_levels(map);
		System.out.println(list.size());
		System.out.println(list);
		
	}
	@Test
	public void test2(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Customer_levelDao dao=ac.getBean("customer_levelDao",Customer_levelDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list=dao.finds(map);
		System.out.println(list.size());
	}
}
