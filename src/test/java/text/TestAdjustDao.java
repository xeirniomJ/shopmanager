package text;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.AdjustDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAdjustDao {
	@Test
	public void test(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		AdjustDao dao=ac.getBean("adjustDao",AdjustDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list1=dao.adjust_find(map);
		map.put("adjust_statue", 1);
		map.put("goods_message", "%%%");
		Calendar c=Calendar.getInstance();
		Date adjust_validity=new Date(c.getTime().getTime());
		for(int i=0;i<list1.size();i++){
				System.out.println(list1.get(i).get("adjust_validity"));
		}
		map.put("shop_unique", "8302016134121");
		System.out.println(adjust_validity);
		map.put("adjust_validity", adjust_validity);
		List<Map<String,Object>> list=dao.oadjust_find(map);
		System.out.println(list.size());
	}
}
