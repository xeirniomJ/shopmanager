package shopmanagert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.Sale_list_detailDao;
import org.haier.shop.entity.Sale_list_detail;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Sale_list_detailDaoTest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Sale_list_detailDao dao=ac.getBean("sale_list_detailDao",Sale_list_detailDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sale_list_unique", "0901201618274910001");
		List<Sale_list_detail> list=dao.findSale_list_details(map);
		System.out.println(list.size());
	}
}
