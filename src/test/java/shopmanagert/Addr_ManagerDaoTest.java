package shopmanagert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.haier.shop.dao.Addr_managerDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Addr_ManagerDaoTest {
	@Test
	/**
	 * 测试查询某一会员详细送货地址信息
	 */
	public void test1(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Addr_managerDao dao=ac.getBean("addr_managerDao",Addr_managerDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cus_id", "1");
		List<Map<String,Object>> list=dao.findAddr_details(map);
		System.out.println(list.size());
		System.out.println(list);
	}
	/**
	 * 测试查询单个送货地址详情
	 */
	@Test
	public void test2(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Addr_managerDao dao=ac.getBean("addr_managerDao",Addr_managerDao.class);
		System.out.println(dao);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("addr_id", 1);
		System.out.println(map);
		List<Map<String,Object>> list=dao.findAddr_detail(map);
		System.out.println(list.size());
		System.out.println(list);
	}
	/**
	 * 测试更新会员送回地址信息
	 */
	@Test
	public void test3(){
		@SuppressWarnings("resource")
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		Addr_managerDao dao=ac.getBean("addr_managerDao",Addr_managerDao.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("addr_id", 1);
		map.put("addr_phone","15151514");
		int k=dao.updateAddr_detail(map);
		System.out.println(k);
	}
}

