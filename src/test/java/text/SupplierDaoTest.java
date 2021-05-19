package text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.SupplierDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SupplierDaoTest {
	@Test
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		SupplierDao supDao=ac.getBean("supplierDao",SupplierDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("supplier_unique", "1476781215791");
		List<Map<String,Object>> supList=supDao.loadSupplierDetail(map);
		System.out.println(supList.size());
		if(supList.size()>1){
			System.out.println(supList.get(0));
		}
	}
}
