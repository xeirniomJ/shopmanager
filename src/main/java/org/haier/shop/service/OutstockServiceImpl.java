package org.haier.shop.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.OutstockDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("outstockService")
@Transactional
public class OutstockServiceImpl implements OutstockService{
	@Resource
	private OutstockDao outDao;
	/**
	 * 根据输入条件，查询满足条件的出库单
	 */
	public NoteResult findOutstocks(String shop_unique, Timestamp startDate, Timestamp endDate) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object> ();
		map.put("shop_unique", shop_unique);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		List<Map<String,Object>> list=outDao.findOutstocks(map);
		if(list.size()==0){
			ns.setMsg("没有满足条件的出库单");
			ns.setStatus(1);
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 根据点击的出库单号，查询对应出库详情
	 */
	public NoteResult findOutstock(String outstock_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object> ();
		 map.put("outstock_unique", outstock_unique);
		 List<Map<String,Object>> list=outDao.findOutstock(map);
		 if(list.size()==0){
			 ns.setStatus(1);
			 ns.setMsg("没有满足条件的出库单");
			 return ns;
		 }
		 ns.setStatus(0);
		 ns.setMsg("查询成功");
		 ns.setData(list);
		 ns.setStatus(0);
		return ns;
	}
	
}
