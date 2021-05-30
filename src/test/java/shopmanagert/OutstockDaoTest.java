package shopmanagert;

import java.util.HashMap;
import java.util.Map;

import org.haier.shop.dao.OutstockDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OutstockDaoTest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		OutstockDao dao=ac.getBean("outstockDao",OutstockDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", "0830201613441210001");
		System.out.println(dao.findOutstocks(map).size());
	}
	@Test
	public void test2(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		OutstockDao dao=ac.getBean("outstockDao",OutstockDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", "0830201613441210001");
		map.put("outstock_id", "1");
		System.out.println(dao.findOutstock(map).size());
		for(Map<String,Object> mp:dao.findOutstock(map)){
			System.out.println(mp.get("sale_list_id"));
			System.out.println(mp.get("sale_list_unique"));
		}
	}
}
