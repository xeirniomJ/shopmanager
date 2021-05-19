package shopmanagert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.InstockDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InstockDaoTest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		InstockDao dao=ac.getBean("instockDao",InstockDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique","0830201613441210001");
		List<Map<String,Object>> list=dao.findInstocks(map);
		System.out.println(list.size());
	}
	@Test
	public void test2(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		InstockDao dao=ac.getBean("instockDao",InstockDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique","0830201613441210001");
		map.put("instock_id", '1');
		List<Map<String,Object>> list=dao.findInstock(map);
		System.out.println(list.size());
		for(Map<String,Object> mp:list){
			System.out.println(mp);
		}
	}
}
