package text;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.GoodsDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestGoods {
	@Test
	public void test1(){
		Map<String,Object> map=new HashMap<String,Object>();
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		GoodsDao dao=ac.getBean("goodsDao",GoodsDao.class);
		map.put("shop_unique", "8302016134121");
		Long t1=new Date().getTime();
		List<Map<String,Object>> list=dao.likeUnion(map);
		Long t2=new Date().getTime();
		System.out.println("耗时："+(t2-t1)/1000);
		System.out.println(list.size());
	}
}
