package shopmanagert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.Return_list_detailDao;
import org.haier.shop.entity.Return_list_detail;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Return_list_detailDaoTest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Return_list_detailDao dao=ac.getBean("return_list_detailDao",Return_list_detailDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ret_list_unique", "20120120132");
		List<Return_list_detail> list=dao.findReturn_list_details(map);
		System.out.println(list.size());
		System.out.println(list);
	}
}
