package org.haier.shop.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.InstockDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("instockService")
@Transactional
public class InstockServiceImpl implements InstockService{
	@Resource
	private InstockDao instockDao;
	/**
	 * 查询某一商店，某段时间内的入库记录
	 */
	public NoteResult findInstocks(Timestamp startDate, Timestamp endDate,String shop_unique,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique",manager_unique);
		}
		List<Map<String,Object>> list=instockDao.findInstocks(map);
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
	/**
	 * 根据点击的入库单号，查询该入库单的详情
	 */
	
	public NoteResult findInstock(String shop_unique, Integer instock_id) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique);
		map.put("instock_id", instock_id);
		List<Map<String,Object>> list=instockDao.findInstock(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有关于该订单的内容");
		}
		ns.setStatus(0);
		ns.setData(list);
		ns.setMsg("查询成");
		return ns;
	}
}

