package org.haier.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.GoodsDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("stockService")
@Transactional
public class StockServiceImpl implements StockService{
	@Resource
	private GoodsDao goodsDao;
	/**
	 * 查询某一店面的库存
	 */
	public NoteResult findGoodss(String shop_unique, String goodsmessage,String manager_unique) {
		NoteResult ns=new NoteResult();
		if("请输入商品信息".equals(goodsmessage)){
			goodsmessage="%%%";
		}else{
			goodsmessage="%"+goodsmessage+"%";
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goodsMessage", goodsmessage);
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		List<Map<String,Object>> list=goodsDao.findGoodss(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
}
