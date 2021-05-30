package org.haier.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.ManagerDao;
import org.haier.shop.dao.ShopDao;
import org.haier.shop.util.Load;
import org.haier.shop.util.NoteResult;
import org.haier.shop.util.NoteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("managerService")
@Transactional
public class ManagerServiceImpl implements ManagerService{
	@Resource
	private ManagerDao managerDao;
	@Resource
	private ShopDao shopDao;
	/**
	 * 管理员登录
	 */
	public NoteResult login(String manager_account,String manager_pwd){
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		String manager_pwd5=NoteUtil.md5(manager_pwd);
		map.put("manager_account", manager_account);
		map.put("manager_pwd", manager_pwd5.trim());
		List<Map<String,Object >> list=managerDao.findManagers(map);
		if(list.isEmpty()){
			ns.setStatus(1);
			ns.setMsg("账号或密码错误");
			return ns;
		}
		String manager_token=NoteUtil.createId();
		map.put("manager_token", manager_token);
		int k=managerDao.updateToken(map);
		if(k<1){
			ns.setStatus(1);
			ns.setMsg("账号或密码错误");
			return ns;
		}
		ns.setData1(manager_token);
		ns.setStatus(0);
		ns.setMsg("登录成功");
		ns.setData(list.get(0));
		return ns;
	}
	/**
	 * 管理员注册
	 */
	public NoteResult register(String manager_account, String manager_pwd, String manager_phone,
			String manager_name) {
		NoteResult ns=new NoteResult();
		if(manager_account==null||manager_pwd==null){
			ns.setMsg("账号或密码不能为空");
			ns.setStatus(1);
			return ns;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_account", manager_account);
		int j=managerDao.findManagers(map).size();
		if(j>0){
			ns.setStatus(1);
			ns.setMsg("该账户已被注册,请尝试其他账户名");
			return ns;
		}
		map.put("manager_pwd",NoteUtil.md5(manager_pwd).trim());
		map.put("manager_name", manager_name);
		map.put("manager_phone", manager_phone);
		map.put("manager_unique", new Date().getTime());
		int k=managerDao.newManager(map);
		if(k==1){
			ns.setMsg("注册成功");
			ns.setStatus(0);
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("注册失败");
		return ns;
	}
	/**
	 * 根据前端员工号，查询该员工管理的店铺
	 */
	public NoteResult shops_uniquefind(String manager_unique,String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("shop_unique", shop_unique);
		List<Map<String,Object>> list=managerDao.shops_uniquefind(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 更新管理员账号密码
	 */
	public NoteResult updatePwd(String old_pwd, String manager_account, String manager_pwd) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_account);
		map.put("manager_pwd", NoteUtil.md5(old_pwd).trim());
		int k=managerDao.findManagers(map).size();
		if(k<1){
			ns.setStatus(1);
			ns.setMsg("密码错误");
			return ns;
		}
		map.put("manager_pwd", NoteUtil.md5(manager_pwd).trim());
		managerDao.updateManagerMessage(map);
		ns.setStatus(0);
		ns.setMsg("更新成功");
		return ns;
	}
	/**
	 * 查询管理员所有店铺的店铺的所有前端管理员信息
	 */
	public NoteResult findShopManagers(String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		List<Map<String,Object>> list=managerDao.findShopManagers(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有相应的店铺信息");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 更新店铺管理员信息
	 */
	public NoteResult updateManagerMessage(String manager_unique, String manager_name, String manager_phone) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("manager_unique", manager_unique);
		if(!"".equals(manager_name)){
			map.put("manager_name", manager_name);
		}//endIf
		if(!"".equals(manager_phone)){
			map.put("manager_phone",manager_phone);
		}//endIf
		managerDao.updateManagerMessage(map);
		ns.setStatus(0);
		ns.setMsg("更新成功");
		return ns;
	}
	/**
	 * 根据管理员编号，查询管理员信息
	 */
	public NoteResult findManagerMessage(String manager_unique,String manager_token) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		if(manager_token==null||manager_unique==null){
			ns.setStatus(1);
			ns.setMsg("您的登录状态已失效，请重新登录");
			return ns;
		}
		List<Map<String,Object>> list=managerDao.findManagers(map);
		System.out.println("查询店铺信息为："+map);
		System.out.println(list.size());
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("您的登录状态已失效，请重新登录");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 
	 * 超级管理员查询所有管理员名单
	 */
	public NoteResult findManagers(String manager_unique, String manager_token) {
		NoteResult ns=new NoteResult ();
		Map<String,Object> map=new HashMap<String,Object>();
		if(manager_unique==null||manager_token==null){
			ns.setStatus(1);
			ns.setMsg("登录状态已失效，请重新登录。");
			return ns;
		}
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		List<Map<String,Object>> manlist=managerDao.findManagers(map);
		int k=manlist.size();
		if(k==0){
			ns.setStatus(1);
			ns.setMsg("管理员信息错误");
			return ns;
		}
		if(Integer.parseInt(manlist.get(0).get("manager_level").toString())!=2){
			ns.setStatus(1);
			ns.setMsg("您的权限不够，联系超级管理员");
			return ns;
		}
		map.clear();
		map.put("manager_levels", Load.shopManagerLevel);
		List<Map<String,Object>> list=managerDao.findManagers(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有符合条件的管理员信息");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	
	/**
	 * 超级管理员更新管理员权限信息
	 */
	public NoteResult updateManagerDetail(String manager_unique, String manager_name, String manager_token,
			String manager_account, String manager_phone, String submanager_unique, String manager_level,
			String power_createManager, String power_modifyFunction, String power_examShop, String power_examSup,
			String power_forbidCus) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("token", manager_token);
		List<Map<String,Object>> manlist=managerDao.findManagers(map);
		int k=manlist.size();
		if(k<1||manager_unique==null){
			ns.setStatus(1);
			ns.setMsg("管理员登录超时");
			return ns;
		}
		if(Integer.parseInt((manlist.get(0).get("manager_level").toString()))!=2){
			ns.setStatus(1);
			ns.setMsg("您没有相应的权限");
			return ns;
		}
		map.put("manager_unique",submanager_unique);
		map.put("manager_name", manager_name);
		map.put("manager_account", manager_account);
		map.put("manager_phone", manager_phone);
		map.put("manager_level", Integer.parseInt(manager_level));
		map.put("power_examShop", Integer.parseInt(power_examShop));
		map.put("power_createManager", Integer.parseInt(power_createManager));
		map.put("power_modifyFunction", Integer.parseInt(power_modifyFunction));
		map.put("power_examSup", Integer.parseInt(power_examSup));
		map.put("power_forbidCus", Integer.parseInt(power_forbidCus));
		if("2"==manager_level){
			map.put("manager_levelName", "超级管理员");
		}else if("3"==manager_level){
			map.put("manager_levelName", "普通管理员");
		}
		k=managerDao.updateManagerMessage(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("更新成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("更新失败，请检查会员编号");
		return ns;
	}
	
	public NoteResult queryManagerMessage(String manager_unique, String manager_token, String submanager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(manager_token==null||manager_unique==null){
			ns.setStatus(1);
			ns.setMsg("登录失效，请重新登录");
			return ns;
		}
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		List<Map<String,Object>> manlist=managerDao.findManagers(map);
		int k=manlist.size();
		if(k<1){
			ns.setStatus(1);
			ns.setMsg("登录失效，请重新登录");
			return ns;
		}
		if(Integer.parseInt(manlist.get(0).get("manager_level").toString())!=2){
			ns.setStatus(1);
			ns.setMsg("您没有相应的权限修改");
			return ns;
		}
		map.put("manager_unique", submanager_unique.trim());
		
		List<Map<String,Object>> list=managerDao.queryManagerDetail(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有相应的管理员信息");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list.get(0));
		return ns;
	}
}
