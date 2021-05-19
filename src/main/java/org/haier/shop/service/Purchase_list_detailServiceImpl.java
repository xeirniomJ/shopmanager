package org.haier.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.Purchase_list_detailDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("purchase_list_detailService")
@Transactional
public class Purchase_list_detailServiceImpl implements Purchase_list_detailService{
	@Resource
	private Purchase_list_detailDao purchase_list_detailDao;
	public NoteResult purchase_list_detail_find(String purchase_list_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("purchase_list_unique", purchase_list_unique);
		List<Map<String,Object>> list=purchase_list_detailDao.findPurchase_list_details(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
}
