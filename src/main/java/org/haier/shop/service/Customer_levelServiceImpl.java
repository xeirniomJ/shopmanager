package org.haier.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.Customer_levelDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("customer_levleService")
@Transactional
public class Customer_levelServiceImpl implements Customer_levelService{
	@Resource
	private Customer_levelDao customer_levelDao;
	
	public NoteResult customer_level_list() {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> list=customer_levelDao.findCustomer_levels(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	
}
