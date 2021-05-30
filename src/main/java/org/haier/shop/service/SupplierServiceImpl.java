package org.haier.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.ManagerDao;
import org.haier.shop.dao.ShopDao;
import org.haier.shop.dao.SupplierDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService{
	@Resource
	private SupplierDao supDao;
	@Resource
	private ManagerDao manDao;	
	@Resource
	private ShopDao shopDao;
	
	public NoteResult findApplys(String manager_unique,String manager_token) {
		NoteResult ns=new NoteResult();
		if(manager_token==null||manager_unique==null){
			ns.setStatus(1);
			ns.setMsg("登录超时,请稍后重试");
			return ns;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		if(manDao.findManagers(map).size()!=1){
			ns.setStatus(1);
			ns.setMsg("登录超时，请重新登录");
			return ns;
		};
		map.clear();
		map.put("examinestatus", 2);
		List<Map<String,Object>> supList=supDao.findSups(map);
		List<Map<String,Object>> shopList=shopDao.findShop(map);
		ns.setStatus(0);
		ns.setMsg("共有"+shopList.size()+"家店铺申请；共有"+supList.size()+"供货端申请");
		return ns;
	}

	public NoteResult findSupplierApplys(String manager_unique, String manager_token) {
		NoteResult ns=new NoteResult();
		if(manager_unique==null||manager_token==null){
			ns.setStatus(2);
			ns.setMsg("登录超时，请重新登录！");
			return ns;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		if(manDao.findManagers(map).size()!=1){
			ns.setStatus(2);
			ns.setMsg("登录超时，请重新登录!");
			return ns;
		}
		map.clear();
		map.put("examinestatus", 2);
		List<Map<String,Object>> supList=supDao.findSups(map);
		if(supList.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有新的供应商申请");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功！");
		ns.setData(supList);
		return ns;
	}
	/**
	 * 加载供应商信息
	 */
	public NoteResult loadSupplierMessage(String manager_unique, String manager_token, String supplier_unique) {
		NoteResult ns=new NoteResult();
		if(manager_unique==null||manager_token==null){
			ns.setStatus(2);
			ns.setMsg("您的登录已超时，请重新登录!");
			return ns;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		if(manDao.findManagers(map).size()!=1){
			ns.setStatus(2);
			ns.setMsg("登录超时，请重新登录!");
			return ns;
		}
		if(Integer.parseInt(manDao.findManagers(map).get(0).get("power_examSup").toString())!=1){
			ns.setStatus(1);
			ns.setMsg("您的权限不够!不能进行相关操作！");
			return ns;
		}
		map.clear();
		map.put("supplier_unique",supplier_unique);
		List<Map<String,Object>> supList=supDao.loadSupplierDetail(map);
		if(supList.size()!=1){
			ns.setStatus(1);
			ns.setMsg("没有供应商相关信息！");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(supList.get(0));
		return ns;
	}
	/**
	 * 通过供应商审核
	 */
	public NoteResult passExamine(String manager_unique, String manager_token, String supplier_unique) {
		NoteResult ns=new NoteResult();
		if(manager_unique==null||manager_token==null){
			ns.setStatus(2);
			ns.setMsg("您的登录状态已失效，请重新登录！");
			return ns;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_token", manager_token);
		map.put("manager_unique", manager_unique);
		int k=manDao.findManagers(map).size();
		if(k!=1){
			ns.setStatus(2);
			ns.setMsg("您的登录状态已失效，请重新登录！");
			return ns;
		}
		if(1!=Integer.parseInt(manDao.findManagers(map).get(0).get("power_examSup").toString())){
			ns.setStatus(0);
			ns.setMsg("您的权限不够，不能进行相关操作！");
			return ns;
		}
		map.clear();
		map.put("supplier_unique", supplier_unique);
		map.put("examinestatus", 4);
		k=supDao.updateSupplier(map);
		if(k!=1){
			ns.setMsg("更新失败！");
			ns.setStatus(1);
			return ns;
		}
		ns.setMsg("更新成功");
		ns.setStatus(0);
		return ns;
	}
	/**
	 * 将供应商设置为审核不通过状态
	 */
	public NoteResult submitReason(String manager_unique,String manager_token,String supplier_unique,String examinestatus_reason){
		NoteResult ns=new NoteResult();
		if(null==manager_unique||null==manager_token){
			ns.setStatus(2);
			ns.setMsg("您的登录状态已失效，请重新登录!");
			return ns;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		if(manDao.findManagers(map).size()!=1){
			ns.setStatus(2);
			ns.setMsg("登录超时，请重新登录!");
			return ns;
		}
		if(Integer.parseInt(manDao.findManagers(map).get(0).get("power_examSup").toString())!=1){
			ns.setStatus(1);
			ns.setMsg("您的权限不够!不能进行相关操作！");
			return ns;
		}
		map.clear();
		map.put("supplier_unique", supplier_unique);
		map.put("examinestatus_reason",examinestatus_reason);
		map.put("examinestatus", 3);
		int k=supDao.updateSupplier(map);
		if(k!=1){
			ns.setStatus(1);
			ns.setMsg("更新失败！");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("更新成功！");
		return ns;
	}
	/**
	 * 查询满足条件的供应商信息
	 */
	public NoteResult findSuppliers(String manager_unique, String manager_token, String parea_dict_num,
			String carea_dict_num, String area_dict_num, String examinestatus, String message) {
		NoteResult ns=new NoteResult();
		if(null==manager_unique||null==manager_token){
			ns.setStatus(2);
			ns.setMsg("您的登录状态已失效，请重新登录!");
			return ns;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		if(manDao.findManagers(map).size()!=1){
			ns.setStatus(2);
			ns.setMsg("登录超时，请重新登录!");
			return ns;
		}
		if(Integer.parseInt(manDao.findManagers(map).get(0).get("power_examSup").toString())!=1){
			ns.setStatus(1);
			ns.setMsg("您的权限不够!不能进行相关操作！");
			return ns;
		}
		map.clear();
		if(!"0".equals(parea_dict_num)){
			map.put("parea_dict_num", parea_dict_num);
		}
		if(!"0".equals(carea_dict_num)){
			map.put("carea_dict_num", carea_dict_num);
		}
		if(!"0".equals(area_dict_num)){
			map.put("area_dict_num", area_dict_num);
		}
		if(!"0".equals(examinestatus)){
			map.put("examinestatus", examinestatus);
		}
		List<Map<String,Object>> supList=supDao.findSuppliers(map);
		if(supList.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有满足条件的供应商！");
			return ns;
		}
		ns.setMsg("查询成功！");
		ns.setStatus(0);
		ns.setData(supList);
		return ns;
	}
}
