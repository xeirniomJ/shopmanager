package text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.Goods_kindDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestGoodsKindDao {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Goods_kindDao dao=ac.getBean("goods_kindDao",Goods_kindDao.class);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		for(int i=0;i<10;i++){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("goods_kind_unique", "1");
			map.put("goods_kind_parunique", "0");
			map.put("goods_kind_name", "测试用");
			list.add(map);
		}
		int k=dao.addShopKinds(list);
		System.out.println(k);
		
	}
}
