package org.haier.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.Goods_kindDao;
import org.haier.shop.dao.KindsDao;
import org.haier.shop.dao.ManagerDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("goods_kind")
@Transactional
public class Goods_kindServiceImpl implements Goods_kindService{
	@Resource
	private Goods_kindDao goods_kindDao;
	@Resource
	private ManagerDao managerDao;
	@Resource
	private KindsDao kindDao;
	/**
	 * 自动添加商品大类
	 */
	public NoteResult add_goods_kind_group(String shop_unique,String manager_unique){
		NoteResult ns=new NoteResult();
		//存放查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goods_kind_parunique", "0");
		if(!"undefined".equals(shop_unique)&&shop_unique!=null&&!"null".equals(shop_unique)){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		List<Map<String,Object>> goods_kindslist=goods_kindDao.findGoods_kinds(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(goods_kindslist);
		return ns;
	}

	//根据大类，确定需要输入的小类
	public NoteResult inadd_goods_kind_name(String goods_kind_parunique,String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("goods_kind_parunique", goods_kind_parunique);
		//查询出所有商品类型（包含所有小类）
		map.put("shop_unique", shop_unique);
		List<Map<String,Object>> goods_kinds=goods_kindDao.finds(map);
		//存储所有小类
		ns.setStatus(0);
		ns.setMsg("小类查询成功");
		ns.setData(goods_kinds);
		return ns;
	}
	/**
	 * 添加新的商品分类
	 */
	public NoteResult newGoods_kind(String goods_kind_group, String goods_kind_name,String shop_unique,String manager_unique) {
		NoteResult ns=new NoteResult();
		//先查询大类是否存在
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goods_kind_name", goods_kind_group);
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
			List<Map<String, Object>> list=goods_kindDao.findGoods_kinds(map);
			if(list.size()>=1){
				goods_kind_group=(String) list.get(0).get("goods_kind_unique");
			}else{
				goods_kind_group=new Date().getTime()+"";
				map.put("goods_kind_unique", goods_kind_group);
				map.put("goods_kind_parunique", "0");
				map.put("shop_unique", shop_unique);
				//创建新的大类
				goods_kindDao.newGoods_kind(map);
			}
			map.clear();
			map.put("goods_kind_parunique",goods_kind_group);
			map.put("goods_kind_name", goods_kind_name);
			map.put("goods_kind_unique", null);
			map.put("shop_unique", shop_unique);
			
			if(goods_kindDao.finds(map).size()>=1){
				ns.setStatus(1);
				ns.setMsg("该类已存在");
				return ns;
			}
			map.put("goods_kind_unique", new Date().getTime()+"");
			goods_kindDao.newGoods_kind(map); 
		}else{
			map.put("manager_unique", manager_unique);
			List<Map<String,Object>> slist=managerDao.shops_uniquefind(map);
			for(Map<String,Object> mpp:slist){
				shop_unique=(String) mpp.get("shop_unique");
				map.put("shop_unique", shop_unique);
				List<Map<String, Object>> list=goods_kindDao.findGoods_kinds(map);
				if(list.size()==1){
					goods_kind_group=(String) list.get(0).get("goods_kind_unique");
				}else{
					goods_kind_group=new Date().getTime()+"";
					map.put("goods_kind_unique", goods_kind_group);
					map.put("goods_kind_parunique", "0");
					map.put("shop_unique", shop_unique);
					//创建新的大类
					goods_kindDao.newGoods_kind(map);
				}
				map.clear();
				map.put("goods_kind_parunique",goods_kind_group);
				map.put("goods_kind_name", goods_kind_name);
				map.put("goods_kind_unique", null);
				map.put("shop_unique", shop_unique);
				if(goods_kindDao.finds(map).size()==1){
					ns.setStatus(1);
					ns.setMsg("该类已存在");
					return ns;
				}
				
				map.put("goods_kind_unique", new Date().getTime()+"");
				goods_kindDao.newGoods_kind(map); 
			}
		}
		ns.setStatus(0);
		ns.setMsg("新建成功");
		return ns;
	}
	/***
	 * 查询满足条件的商品种类
	 */
	public NoteResult queryGoods_kind(String goods_kind_parunique, String goods_kind_unique,String shop_unique,String manager_unique) {
		Map<String,Object> map=new HashMap<String,Object>();
		NoteResult ns=new NoteResult();
		if("0".equals(goods_kind_parunique)){
			goods_kind_parunique=null;
		}
		if("0".equals(goods_kind_unique)){
			goods_kind_unique=null;
		}
		map.put("goods_kind_parunique", goods_kind_parunique);
		map.put("goods_kind_unique", goods_kind_unique);
		if(shop_unique!=null&&!"null".equals(shop_unique)&&!"undefined".equals(shop_unique)){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		List<Map<String,Object>> list=goods_kindDao.finds(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	
	/**
	 * 进入更新商品类界面，查询商品小类详情
	 * 
	 */
	public NoteResult findGoods_kind(String goods_kind_id) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goods_kind_id", goods_kind_id);
		List<Map<String,Object>> list=goods_kindDao.findk(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 更新商品类型
	 */
	public NoteResult modifyGoods_kind(Integer goods_kind_id, String goods_kind_group, String goods_kind_name,String shop_unique) {
		NoteResult ns=new NoteResult();
		//先检测该大类是否存在，若存在，获取其unique
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goods_kind_name", goods_kind_group);
		map.put("shop_unique", shop_unique);
		List<Map<String, Object>> list=goods_kindDao.findGoods_kinds(map);
		if(list.size()==1){
			goods_kind_group=(String) list.get(0).get("goods_kind_unique");
		}else{
			goods_kind_group=new Date().getTime()+"";
			map.put("goods_kind_unique", goods_kind_group);
			map.put("goods_kind_parunique", "0");
			map.put("shop_unique", shop_unique);
			//创建新的大类
			goods_kindDao.newGoods_kind(map);
		}
		map.clear();
		list.clear();
		map.put("goods_kind_parunique", goods_kind_group);
		map.put("goods_kind_name", goods_kind_name);
		map.put("shop_unique", shop_unique);
		list=goods_kindDao.finds(map);
		if(list.size()==1){
			ns.setStatus(1);
			ns.setMsg("该类已存在");
			return ns;
		}
		map.put("goods_kind_id", goods_kind_id);
		map.put("goods_kind_parunique", goods_kind_group);
		map.put("goods_kind_name", goods_kind_name);
		map.put("shop_unique", shop_unique);

		int k=goods_kindDao.modifyGoods_kind(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("更新成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("更新失败");
		return ns;
	}
	/**
	 * 查询所有商品小类
	 */
	public NoteResult findAllNames(String shop_unique, String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(shop_unique!=null&&!shop_unique.equals("")){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		
		List<Map<String,Object>> list=goods_kindDao.findAllNames(map);
		ns.setData(list);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		return ns;
	}
	
	/**
	 * 添加一级商品分析信息
	 */
	public NoteResult addStairKind() {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goodsKindPid", 0);
		List<Map<String,Object>> list=kindDao.addKindsLevel(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有满足条件的商品分类");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}//一级商品分类信息添加结束
	
	/**
	 * 一级商品分类信息修改后，自动修改二级商品分类信息
	 * 二级商品分类信息修改后，自动修改三级商品分类信息
	 */
	public NoteResult addSecLevel(Integer goodsKindPid){
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goodsKindPid", goodsKindPid);
		List<Map<String,Object>> list=kindDao.addKindsLevel(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有满足条件的商品");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}//商品分类信息修改结束
	/**
	 * 添加所有商品二级分类
	 */
	public NoteResult addSLevels() {
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=kindDao.addSLevels();
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有满足条件的商品信息");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功！");
		ns.setData(list);
		return ns;
	}
	/**
	 * 查询所有三级商品分类
	 * @return
	 */
	public NoteResult addTLevels(){
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=kindDao.addTLevels();
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有满足条件的商品信息");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功！");
		ns.setData(list);
		return ns;
	}
	/**
	 * 根据输入的商品分类信息名称，查询其下一级分类信息
	 */
	public NoteResult chSLevels(String goodsKindName) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goodsKindName", goodsKindName);
		List<Map<String,Object>> list=kindDao.chSLevels(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有相应的结果");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 查询管理员商品分类(全版，管理员版）
	 */
	public NoteResult queryKinds(String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique);
		//加载店铺自己的商品分类
		List<Map<String,Object>> shopKinds=goods_kindDao.findGoods_kinds(map);
		//加载管理员商品分类
		map.put("shop_unique", "0");
		List<Map<String,Object>> kindList=goods_kindDao.queryKinds(map);
		if(kindList.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有满足条件的分类");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(kindList);
		ns.setData1(shopKinds);
		return ns;
	}
	/**
	 * 保存商品分类
	 */
	public NoteResult submitShopKinds(String shop_unique, String message) {
		NoteResult ns=new NoteResult();
		Map<String,Object> mp=new HashMap<String,Object>();
		mp.put("shop_unique", shop_unique);
		goods_kindDao.deleteShopKinds(mp);
		List<Map<String,Object>> messages=new ArrayList<Map<String,Object>>();
		String[] arr=message.split(";");
		for(String ar:arr){
			String[] a=ar.split(":");
			Map<String,Object> map=new HashMap<String,Object>();
			for(int i=0;i<a.length;i++){
				map.put("goods_kind_unique", a[1]);
				map.put("goods_kind_parunique", a[2]);
				map.put("goods_kind_name", a[3]);
				map.put("shop_unique", a[0]);
			}
			messages.add(map);
		}
		int k=goods_kindDao.addShopKinds(messages);
		if(k==0){
			ns.setStatus(1);
			ns.setMsg("没有改动");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("保存成功");
		return ns;
	}
}