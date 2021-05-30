package org.haier.shop.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.CustomerDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
@Transactional
public class CustomerServiceImpl  implements CustomerService{
	@Resource
	private CustomerDao cusDao;
	/**
	 * 查询所有与店铺相关的客户信息
	 */
	public NoteResult findCuss(String shop_unique,String customermessage,String manager_unique) {
		NoteResult ns=new NoteResult();
		if(shop_unique.equals("")){
			shop_unique=null;
		}
		if(customermessage==null||"".equals(customermessage.trim())){
			customermessage="%%%";
		}else if(customermessage!=null){
			customermessage="%"+customermessage+"%";
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cus_alias", customermessage);
		map.put("cus_name", customermessage);
		map.put("cus_phone", customermessage);
		map.put("cus_id", customermessage);
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		List<Map<String,Object>> list=cusDao.findCuss(map);
		ns.setData(list);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		return ns;
	}
	/**
	 * 根据客户编号和店铺编号，查询客户详细信息
	 */
	public NoteResult findCus(String shop_unique, Integer cus_id) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cus_id", cus_id);
		List<Map<String,Object>> list=cusDao.findC(map);
		if(list.size()>0){
			ns.setStatus(0);
			ns.setData(list.get(0));
			ns.setMsg("查询成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("没有满足条件的客户");
		return ns;
	}
	/**
	 * 根据会员编号，更新会员信息
	 */
	public NoteResult updatecus(Integer cus_id, String cus_name, String cus_sex, String cus_alias,
			String cus_phone, Timestamp cus_regedit_date, Timestamp cus_birthday,String cus_email) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cus_unique", new Date().getTime());
		map.put("cus_id", cus_id);
		map.put("cus_name", cus_name);
		map.put("cus_sex", cus_sex);
		map.put("cus_alias", cus_alias);
		map.put("cus_phone", cus_phone);
		map.put("cus_regedit_date", cus_regedit_date);
		map.put("cus_birthday", cus_birthday);
		map.put("cus_email", cus_email);
		int k=cusDao.updatecus(map);
		if(k!=1){
			ns.setStatus(1);
			ns.setMsg("更新失败");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("更新成功");
		return ns;
	}
	/**
	 * 插入新会员信息
	 */
	public NoteResult newcus(String cus_account, String cus_pwd, String cus_name, String cus_sex, String cus_alias,
			String cus_phone,  Timestamp cus_birthday, String cus_email) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cus_unique", new Date().getTime());
		map.put("cus_account", cus_account);
		map.put("cus_name", cus_name);
		map.put("cus_pwd", 111111);
		map.put("cus_sex", cus_sex);
		map.put("cus_alias", cus_alias);
		map.put("cus_phone", cus_phone);
		map.put("cus_regedit_date", new Timestamp(new Date().getTime()));
		map.put("cus_birthday", cus_birthday);
		map.put("cus_email", cus_email);
		int k=cusDao.newcus(map);
		System.out.println(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("您插入了"+k+"条信息");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("添加会员失败");
		return ns;
	}
	
	/**
	 * 客户送货地址管理界面，模糊查询满足条件客户信息
	 */
	public NoteResult findCusts(String customermessage) {
		NoteResult ns=new NoteResult();
		if("".equals(customermessage)||"客户姓名，电话，别名".equals(customermessage)||customermessage==null){
			customermessage="%%%";
		}else{
			customermessage="%"+customermessage+"%";
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cus_name", customermessage);
		map.put("cus_alias", customermessage);
		map.put("cus_phone", customermessage);
		List<Map<String,Object>> list=cusDao.findCusts(map);
		if(list.size()==1){
			ns.setStatus(0);
			ns.setMsg("没有满足条件的会员信息");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 
	 */
}
