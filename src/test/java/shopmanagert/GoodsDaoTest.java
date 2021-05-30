package shopmanagert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.GoodsDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GoodsDaoTest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		GoodsDao dao=ac.getBean("goodsDao",GoodsDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goods_name", "%金笔%");
		List<Map<String,Object>> list=dao.test(map);
		map.clear();
		System.out.println(list.size());
		System.out.println(dao.test(map).size());
	}
	@Test
	public void test2(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		GoodsDao dao=ac.getBean("goodsDao",GoodsDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goods_name", "%清风%");
		map.put("goods_alias","%钢笔%");
		map.put("goods_brand", "%钢笔%");
		map.put("shop_unique", "0830201613441210001");
		map.put("goods_barcode", "%钢笔%");
		List<Map<String,Object>> list=dao.findGoodss(map);
		System.out.println(list.size());
		for(Map<String,Object> mp:list){
			System.out.println(mp.get("goods_barcode"));
		}
	}
}
