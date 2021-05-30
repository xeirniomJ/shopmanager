package shopmanagert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.Purchase_list_detailDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Purchase_list_detailDaoTest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Purchase_list_detailDao dao=ac.getBean("purchase_list_detailDao",Purchase_list_detailDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("purchase_list_id","1");
		map.put("purchase_list_unique", "20122");
		List<Map<String,Object>> list=dao.findPurchase_list_details(map);
		System.out.println(list.size());
		for(Map<String,Object> mp:list){
			System.out.println(mp.get("purchase_list_unique"));
		}
	}
	
}
