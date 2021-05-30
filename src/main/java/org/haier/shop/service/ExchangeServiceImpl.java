package org.haier.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.Exchange_listDao;
import org.haier.shop.dao.GoodsDao;
import org.haier.shop.dao.Sup_exOrderDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;

@Service("exchangeService")
public class ExchangeServiceImpl implements ExchangeService{
	@Resource
	private Exchange_listDao exDao;
	@Resource  
	private GoodsDao goodsDao;
	@Resource
	private Sup_exOrderDao sup_exDao;
	/**
	 * 向购物车添加商品，若购物车中商品已存在，则修改数量；如不存在，则添加新明细；
	 */
	public NoteResult addToExCart(String shop_unique, String goods_barcode,Double exchange_list_detail_count,
			String supplier_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		map.put("exchange_list_status", "1".trim());
		List<Map<String,Object>> eList=exDao.findExchangeDetails(map);
		map.put("goods_barcode", goods_barcode.trim());
		List<Map<String,Object>> gList=goodsDao.findGood(map);
		int goods_count=Integer.parseInt(gList.get(0).get("goods_count").toString());
		map.put("supplier_unique", supplier_unique.trim());
		if(eList.size()>0){
			map.put("exchange_list_unique", eList.get(0).get("exchange_list_unique"));
			boolean flag=true;
			for(int i=0;i<eList.size();i++){
				Object barcode=eList.get(i).get("goods_barcode");
				if(barcode!=null&&goods_barcode.trim().equals(barcode.toString().trim())){
					Double count=Double.parseDouble(eList.get(i).get("exchange_list_detail_count").toString());
					if(count+exchange_list_detail_count>goods_count){
						map.put("exchange_list_detail_count", goods_count);
						map.put("cartCount", goods_count);
					}else{
						map.put("exchange_list_detail_count", count+exchange_list_detail_count);
						map.put("cartCount", count+exchange_list_detail_count);
					}
					exDao.updateExchangeDetails(map);
					map.put("goods_count", goods_count);
					ns.setStatus(0);
					ns.setMsg("更新成功！");
					ns.setData(map);
					return ns;
				}
			}
			if(flag){
				List<Map<String,Object>> goodsList=goodsDao.findGood(map);
				Double count=0.00;
				if(goodsList.size()>0){
					count=Double.parseDouble(goodsList.get(0).get("goods_count").toString());
				}else{
					ns.setStatus(0);
					ns.setMsg("所查询的商品不存在");
					return ns;
				}
				if(count<exchange_list_detail_count){
					exchange_list_detail_count=count;
				}
				map.put("exchange_list_detail_count", exchange_list_detail_count);
				exDao.newExchangeDetail(map);
			}
		}else{
			List<Map<String,Object>> goodsList=goodsDao.findGood(map);
			Double count=0.00;
			if(goodsList.size()>0){
				count=Double.parseDouble(goodsList.get(0).get("goods_count").toString());
			}else{
				ns.setStatus(0);
				ns.setMsg("所查询的商品不存在");
				return ns;
			}
			if(count<exchange_list_detail_count){
				exchange_list_detail_count=count;
			}
			map.put("exchange_list_detail_count", exchange_list_detail_count);
			String exchange_list_unique=new Date().getTime()+"";
			map.put("exchange_list_unique", exchange_list_unique);
			exDao.newExchange(map);
			map.put("exchange_list_detail_count", exchange_list_detail_count);
			exDao.newExchangeDetail(map);
		}
		Map<String,Object> mp=new HashMap<String,Object>();
		mp.put("goods_count", goods_count);//无用代码？
		mp.put("cartCount", exchange_list_detail_count);//无用代码？
		ns.setStatus(0);
		ns.setMsg("商品添加成功");
		ns.setData(mp);
		return ns;
	}
	/**
	 * 查询店铺退货清单中的商品
	 */
	public NoteResult FindExchangeGoods(String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		map.put("exchange_list_status",1);
		List<Map<String,Object>> goodsList=exDao.findExchangeDetails(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(goodsList);
		return ns;
	}
	/**
	 * 更新退货订单商品数量
	 */
	public NoteResult changeExCartGoods(String shop_unique,String goods_barcode,Double goods_count){
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		map.put("goods_barcode", goods_barcode.trim());
		map.put("exchange_list_status", "1".trim());
		map.put("exchange_list_detail_count", goods_count);
		int k=exDao.changeExCartCount(map);
		if(k==0){
			ns.setStatus(1);
			ns.setMsg("更新商品数量失败!");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("更新商品数量成功");
		return ns;
	}
	/**
	 * 移除换货订单里的商品
	 */
	public NoteResult rmExCartGoods(String shop_unique, String goods_barcode) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		map.put("goods_barcode", goods_barcode.trim());
		map.put("exchange_list_status", "1".trim());
		int k=exDao.rmExCartGoods(map);
		if(k==0){
			ns.setStatus(1);
			ns.setMsg("移除失败");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("移除成功");
		return ns;
	}
	public NoteResult submitExCart(String shop_unique,String unique){
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		//首先从数据库查询商品数量 
		map.put("shop_unique", shop_unique.trim());
		map.put("exchange_list_status", 1);
		List<Map<String,Object >> dList=exDao.findExchangeDetails(map);
		if(dList.size()>0){
			map.put("exchange_list_unique", dList.get(0).get("exchange_list_unique"));
		}
		//将订单更新为正式换货单
		map.put("exchange_list_status", 2);
		map.put("exchange_list_datetime", new java.sql.Date(new Date().getTime()));
		int k=exDao.updateExchange(map);
		if(k==0){
			ns.setStatus(1);
			ns.setMsg("更新失败，没有相关换货订单");
			return ns;
		}
		//创建新的供应商退货单,并同时更新其详情
		String[] uniques=unique.split(";");
		for(String uni:uniques){
			map.put("sup_exOrder_unique",new Date().getTime());
			map.put("sup_exOrder_datetime", new java.sql.Date(new Date().getTime()));
			map.put("supplier_unique", uni);
			map.put("shop_unique", shop_unique);
			map.put("sup_exOrder_status", 1);
			sup_exDao.newExOrder(map);
			exDao.updateExchangeDetails(map);
		}
		ns.setStatus(0);
		ns.setMsg("创建成功");
		return ns;
	}
}
