package shopmanagert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.CustomerDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerDaoTest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		CustomerDao dao=ac.getBean("customerDao",CustomerDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cus_name", "zs");
		map.put("cus_alias","zs");
		map.put("cus_phone", "%151%");
		List<Map<String,Object>> list=dao.findCusts(map);
		System.out.println(list.size());
		for(Map<String,Object> mpp:list){
			System.out.println(mpp.toString());
		}
	}
}
