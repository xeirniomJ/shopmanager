package org.haier.shop.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.haier.shop.dao.AdjustDao;
import org.haier.shop.dao.ShopDao;
import org.haier.shop.util.Load;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
@Service
public class AdjustServiceImpl implements AdjustService{
	@Resource
	private AdjustDao adjustDao;
	@Resource
	private ShopDao shopDao;
	/**
	 * 根据选择的状态，查询店铺的调货单
	 * @param shop_unique,店铺唯一标识符
	 * @param adjust_statue,响应状态
	 * @param adjust_statue,发货状态
	 */
	public NoteResult adjust_find(String shop_unique, String adjust_statue, String adjust_handlestatue,String adjust_type,Integer page_num,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if("0".equals(adjust_statue)){
			adjust_statue=null;
		}
		if("0".equals(adjust_handlestatue)){
			adjust_handlestatue=null;
		}
		if("0".equals(adjust_type)){
			adjust_type=null;
		}
		if(page_num==null||page_num<0){
			page_num=0;
		}
		map.put("adjust_type", adjust_type);
		map.put("adjust_statue", adjust_statue);
		map.put("adjust_handlestatue", adjust_handlestatue);
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		
		int k=adjustDao.adjust_find(map).size();
		map.put("page_size", Load.apage_size);
		map.put("page_start", page_num*Load.apage_size);
		List<Map<String,Object>> list=adjustDao.adjust_find(map);
		map.clear();
		if(k%Load.apage_size==0){
			map.put("page_num", k/Load.apage_size);
		}else{
			map.put("page_num", k/Load.apage_size+1);
		}
		list.add(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	
	/**
	 * 根据调货单好，取消未被响应的调货单
	 * @param adjust_id,调货单号
	 */
	public NoteResult adjust_cancel(Integer adjust_id) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("adjust_id", adjust_id);
		//添加取消信息
		map.put("adjust_statue", "3");
		int k=adjustDao.adjust_cancel(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("取消成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("取消失败，请确认订单状态");
		return ns;
	}
	/**
	 * 自动添加订货单状态
	 */
	public NoteResult adjust_statue_find() {
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=adjustDao.adjust_statue_find();
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 自动添加订货单处理状态（发货状态)
	 */
	public NoteResult adjust_handlestatue_find(){
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=adjustDao.adjust_handlestatue_find();
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;

	}
	/**
	 * 将新申请添加到数据库
	 */
	public NoteResult adjust_new_save(String shop_unique, String goods_barcode, Double adjust_count,
			Double adjust_price, String adjust_type, Date adjust_validity,String goods_name,String adjust_remark) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique);
		map.put("goods_barcode", goods_barcode);
		map.put("adjust_count", adjust_count);
		map.put("adjust_price", adjust_price);
		map.put("adjust_type", adjust_type);
		map.put("adjust_validity", adjust_validity);
		map.put("adjust_datetime", new Date(new java.util.Date().getTime()));
		map.put("adjust_total", adjust_price*adjust_count);
		map.put("adjust_unique", new java.util.Date().getTime());
		map.put("adjust_statue", "1");
		map.put("adjust_handlestatue", "1");
		map.put("goods_name", goods_name);
		map.put("adjust_total", adjust_count*adjust_price);
		map.put("adjust_remark", adjust_remark);
		int k=adjustDao.adjust_new_save(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("添加成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("添加失败");
		return ns;
	}
	/**
	 * 自动添加调货单申请类型
	 */
	public NoteResult adjust_type_find() {
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=adjustDao.adjust_type_find();
		if(list.size()>0){
			ns.setStatus(0);
			ns.setMsg("查询成功");
			ns.setData(list);
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("没有满足条件的申请类型");
		
		return ns;
	}
	/**
	 * 查询满足条件的其他商家订货单
	 * @param goods_message,商品信息
	 * @param adjust_type,调货类型
	 */
	public NoteResult oadjust_find(String goods_message, String adjust_type,Integer page_num,String shop_unique,String manager_unique) {
		NoteResult ns=new NoteResult();
		if(goods_message==null||goods_message.equals("")){
			goods_message="%%";
		}else{
			goods_message="%"+goods_message.trim()+"%";
		}
		if("0".equals(adjust_type)){
			adjust_type=null;
		}
		if(page_num==null){
			page_num=0;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goods_message", goods_message);
		if(adjust_type!=null){
			map.put("adjust_type", adjust_type);
		}
		map.put("adjust_statue", "1");
		map.put("adjust_handlestatue", "1");
		map.put("adjust_validity", new Date(new java.util.Date().getTime()));
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		List<Map<String,Object>> list=adjustDao.oadjust_find(map);
		double k=list.size();
		map.put("page_size", Load.apage_size);
		map.put("page_start", page_num*Load.apage_size);
		list=adjustDao.oadjust_find(map);
		List<Map<String,Object>> list1=shopDao.findShop(map);
		List<Map<String,Object>> list2=new LinkedList<Map<String,Object>>();
		for(int i=0;i<list.size();i++){
			Map<String,Object> mp=new HashMap<String,Object>();
			mp=list.get(i);
			mp.put("lt", list1.get(0).get("shop_latitude"));
			mp.put("lg", list1.get(0).get("shop_longitude"));
			list2.add(mp);
		}
		if(list.isEmpty()){
			ns.setStatus(1);
			ns.setMsg("没有满足条件的调货单");
			return ns;
		}
		if(k%Load.apage_size==0){
			Map<String,Object> ma=new HashMap<String,Object>();
			ma.put("page_total", k/Load.apage_size);
			list2.add(ma);
		}else{
			Map<String,Object> ma=new HashMap<String,Object>();
			ma.put("page_total", k/Load.apage_size+1);
			list2.add(ma);
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list2);
		return ns;
	}
	/**
	 * 更新调货单响应状态
	 * @param shop_unique,响应店铺的编号
	 * @param adjust_id响应的调货单编号
	 */
	public NoteResult adjust_update(String shop_unique, String adjust_id) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rshop_unique", shop_unique);
		map.put("adjust_id", adjust_id);
		map.put("adjust_statue", '2');
		map.put("radjust_datetime", new Date(new java.util.Date().getTime()));
		int k=adjustDao.adjust_update(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("更新成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("更新失败，请稍后重试");
		return ns;
	}
	/**
	 * 查询满足条件的响应调货单
	 * @param shop_unique响应的店铺唯一标识符
	 * @param goods_message,商品信息
	 * @param adjust_type,调货单类型
	 */
	public NoteResult adjust_rfind(String shop_unique, String goods_message, String adjust_type) {
		NoteResult ns=new NoteResult();
		if(goods_message==null||"".equals(goods_message)){
			goods_message=null;
		}else{
			goods_message="%"+goods_message+"%";
		}
		if("0".equals(adjust_type)){
			adjust_type=null;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		map.put("adjust_type", adjust_type);
		map.put("goods_message", goods_message);
		List<Map<String,Object>> list=adjustDao.adjust_rfind(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有满足条件的订单");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
}
