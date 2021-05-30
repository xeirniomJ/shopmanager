package text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.Exchange_listDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testsql {
	@Test
	public void test(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Exchange_listDao dao=ac.getBean("exchange_listDao",Exchange_listDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", "0830201613441210001");
		map.put("exchange_list_status", 1);
		List<Map<String,Object>> list=dao.tests(map);
		System.out.println(list.size());
	}
}
