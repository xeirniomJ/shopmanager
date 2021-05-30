package text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.KindsDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestKindDao {
	@Test
	@SuppressWarnings("resource")
	public void test1(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		KindsDao dao=ac.getBean("kindsDao",KindsDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list=dao.addKindsLevel(map);
		System.out.println(list.size());
	}
}
