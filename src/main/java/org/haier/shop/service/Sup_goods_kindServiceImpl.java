package org.haier.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.Sup_goods_kindDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;

@Service("sup_goods_kindService")
public class Sup_goods_kindServiceImpl implements Sup_goods_kindService{
	@Resource
	private 	Sup_goods_kindDao kindDao;
	/**
	 * 查询供应商商品大类
	 */
	public NoteResult findParNames() {
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=kindDao.findParNames();
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 根据选择的商品大类，查询满足条件的商品小类
	 */
	public NoteResult findNames(String sup_goods_kind_parunique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sup_goods_kind_parunique", sup_goods_kind_parunique);
		List<Map<String,Object>> list=kindDao.findNames(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	
}
