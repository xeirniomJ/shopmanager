package text;

import java.util.HashMap;
import java.util.Map;

import org.haier.shop.dao.Purchase_listDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPurchase_listDao {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Purchase_listDao dao=ac.getBean("purchase_listDao",Purchase_listDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", "0830201613441210001");
		map.put("goods_barcode", "6923840700234");
		int list=dao.rmCartGoods(map);
		System.out.println(list);
	}
}
