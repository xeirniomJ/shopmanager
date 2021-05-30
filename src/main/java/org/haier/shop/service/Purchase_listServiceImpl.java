package org.haier.shop.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.dao.GoodsDao;
import org.haier.shop.dao.Purchase_listDao;
import org.haier.shop.dao.Purchase_list_detailDao;
import org.haier.shop.dao.Sup_goodsDao;
import org.haier.shop.dao.Sup_orderDao;
import org.haier.shop.entity.Purchase;
import org.haier.shop.util.ExcelUtil;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("purchase_listService")
@Transactional
public class Purchase_listServiceImpl implements Purchase_listService{
	@Resource
	private Purchase_listDao purchase_listDao;
	@Resource
	private Purchase_list_detailDao detailDao;
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private Sup_goodsDao sup_goodsDao;
	@Resource
	private Sup_orderDao orderDao;
	/**
	 * 查询店铺内满足时间段的进货订单
	 */
	public NoteResult purchase_list_find(String purchase_list_id,Date startDate,Date endDate,String shop_unique,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"".equals(purchase_list_id)&&purchase_list_id!=null){
			StringBuilder id=new StringBuilder("%");
			char[] chars=purchase_list_id.toCharArray();
			for(int i=0;i<chars.length;i++){
				id.append(chars[i]+"%");
			}
			purchase_list_id=id.toString();
		}else{
			purchase_list_id="%%%";
		}
		map.put("purchase_list_id",purchase_list_id);
		map.put("startDate",startDate);
		map.put("endDate", endDate);
		if(!"undefined".equals(shop_unique)&&shop_unique!=null&&!"null".equals(shop_unique)){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		List<Map<String,Object>> list=purchase_listDao.findPurchase_lists(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
}
	
	/**
	 * 更新订单
	 */
	public NoteResult purchase_list_update(String purchase_list_unique, String purchase_list_statue) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(purchase_list_statue.equals("0")){
			purchase_list_statue=null;
		}
		map.put("purchase_list_unique", purchase_list_unique);
		map.put("purchase_list_statue", purchase_list_statue);
		int i=purchase_listDao.updatepurchase_list(map);
		if(i==1){
			ns.setStatus(0);
			ns.setMsg("更新成功");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("更新失败");
		return ns;
	}
	/**
	 * 查询周期内店铺进货情况
	 */
	public NoteResult findPurchases(String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		Calendar c=Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		Date startDate=c.getTime();
		c.set(Calendar.DATE, c.get(Calendar.DATE)+1);
		Date endDate=c.getTime();
		map.put("shop_unique", shop_unique);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		//当日销售记录
		if(purchase_listDao.findPurchases(map)==null){
		}else{
			list.addAll(purchase_listDao.findPurchases(map));
		}
		//一周销售记录
		c.set(Calendar.DATE,c.get(Calendar.DATE)-6);
		endDate=startDate;
		startDate=c.getTime();
		map.put("startDate", startDate);
		map.put("endDate",Calendar.getInstance().getTime());
		if(purchase_listDao.findPurchases(map)==null){
		}else{
			list.addAll(purchase_listDao.findPurchases(map));
		}
		//一月销售记录
		c.set(Calendar.MONTH,c.get(Calendar.MONTH)-1);
		startDate=c.getTime();
		map.put("startDate", startDate);
		if(purchase_listDao.findPurchases(map)==null){
		}else{
			list.addAll(purchase_listDao.findPurchases(map));
		}
		//一年的营业额
		c.set(Calendar.MONTH,c.get(Calendar.MONTH)-11);
		map.put("startDate", c.getTime());
		if(purchase_listDao.findPurchases(map)==null){
		}else{
			list.addAll(purchase_listDao.findPurchases(map));
		}
		//当年的营业额
		c.set(Calendar.YEAR,Calendar.getInstance().get(Calendar.YEAR));
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE,0);
		
		map.put("startDate", startDate);
		map.put("endDate", Calendar.getInstance().getTime());
		if(purchase_listDao.findPurchases(map)==null){
		}else{
			list.addAll(purchase_listDao.findPurchases(map));
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 查询生产excel表
	 * @param purchase_list_id:订单编号信息
	 * @param startDate
	 * @param endDate
	 * @param shop_unique
	 * @param request
	 * @return
	 */
	public NoteResult findPurs(String purchase_list_id, Date startDate, Date endDate, String shop_unique,HttpServletRequest request,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(!purchase_list_id.equals("%")){
			StringBuilder id=new StringBuilder("%");
			char[] chars=purchase_list_id.toCharArray();
			for(int i=0;i<chars.length;i++){
				id.append(chars[i]+"%");
			}
		}
		map.put("purchase_list_id",purchase_list_id);
		map.put("startDate",startDate);
		map.put("endDate", endDate);
		List<Purchase> list=new ArrayList<Purchase>();
		if(shop_unique!=null&&!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		list=purchase_listDao.findPurs(map);
		
		String path=request.getSession().getServletContext().getRealPath(File.separator)+"xlsx"+File.separator+shop_unique+File.separator; 
		File path1=new File(path);
		if(!path1.exists()){
			path1.mkdirs();
		}
		File f=new File(path+"purchase.xls");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			OutputStream out=new FileOutputStream(f);
			ExcelUtil<Purchase> e=new ExcelUtil<Purchase>();
			e.buildExcel(list, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ns.setMsg("创建成功");
		ns.setStatus(0);
		return ns;
	}
	/**
	 * 生成新的订单
	 */
	public NoteResult newPurchase_list(String purchase_list_unique,String detail) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("purchase_list_unique",purchase_list_unique.trim());
		map.put("purchase_list_statue", 1);
		map.put("purchase_list_date", new java.sql.Date(new Date().getTime()));
		String[] details=detail.split(";");
		Double purchase_list_total=0.0;
		//将购物车里的商品，依据商品供应商编号，依次生产供货商订单
		List<String> uniqueList=new ArrayList<String>();
		//将购物车里的商品供货商依次查出
		for(int k=0;k<details.length;k++){
			String supplier_unique=details[k].split(":")[2];
			boolean flag=true;
			for(String unique:uniqueList){
				if(unique.trim().equals(supplier_unique.trim())){
					flag=false;
					break;
				}
			}
			if(flag){
				uniqueList.add(supplier_unique);
			}
		}
		//根据供应商，依次查询每个供应商提供产品的货物单价，更新订单详情，计算各商家货物总和
		List<Double> priceList=new ArrayList<Double>();
		for(int k=0;k<uniqueList.size();k++){
			Double sum=0.00;
			for(int i=0;i<details.length;i++){
				String[] d=details[i].split(":");
				if(d[2].trim().equals(uniqueList.get(k))){
					map.put("goods_barcode", d[0].trim());
					map.put("supplier_unique", d[2].trim());
					List<Map<String,Object>> list=sup_goodsDao.findSupGoodsPrice(map);
					if(list.size()>0){
						map.put("purchase_list_detail_price", list.get(0).get("goods_price"));
						purchase_list_total+=Double.parseDouble(list.get(0).get("goods_price").toString())*Integer.parseInt(d[1].trim());
						sum+=Double.parseDouble(list.get(0).get("goods_price").toString())*Integer.parseInt(d[1].trim());
					}
					detailDao.updateCartDetail(map);
				}
			}
			priceList.add(sum);
		}
		map.put("purchase_list_total",purchase_list_total);
		//将订单由购物车状态转换生订单状态，并更新订单时间，金额
		purchase_listDao.newPurchaselist(map);
		//生成供应商订单
		for(int k=0;k<uniqueList.size();k++){
			Map<String,Object> mp=new HashMap<String,Object>();
			String sup_order_unique=new Date().getTime()+"";
			mp.put("supplier_unique", uniqueList.get(k));//此处有bug
			mp.put("sup_order_total", priceList.get(k));
			mp.put("sup_order_date",new java.sql.Date(new Date().getTime()));
			mp.put("sup_order_unique", sup_order_unique);
			orderDao.newSupOrder(mp);
			for(int i=0;i<details.length;i++){
				String[] d=details[i].split(":");
				if(d[2].trim().equals(uniqueList.get(k))){
					map.clear();
					map.put("sup_order_unique", sup_order_unique);
					map.put("goods_barcode", d[0].trim());
					map.put("purchase_list_unique", purchase_list_unique);
					System.out.println("更新的商品信息为："+mp);
					detailDao.updateCartDetail(map);//此处有Bug
				}
			}
		}
		ns.setStatus(0);
		ns.setMsg("订单成功创建");
		return ns;
	}
	/**
	 * 生产新的订单详情
	 * @param shop_unique
	 * @param goods_barcode
	 * @param purchase_list_detail_count
	 * @param purchase_list_detail_total
	 */
	
	public NoteResult addToCart(String shop_unique, String goods_barcode, Integer purchase_list_detail_count,
			String supplier_unique, Double purchase_list_detail_total) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("purchase_list_statue",2);
		map.put("shop_unique", shop_unique);
		List<Map<String,Object>> list=purchase_listDao.findPurchaseDetails(map);//查询是否有购物车清单及购物车清单内容
		map.put("goods_barcode", goods_barcode);
		map.put("supplier_unique", supplier_unique);
		if(list.size()>=1){
			map.put("purchase_list_unique",list.get(0).get("purchase_list_unique"));
			boolean flag=true;
			for(int i=0;i<list.size();i++){
				if(list.get(i).get("goods_barcode")!=null&&list.get(i).get("goods_barcode").toString().equals(goods_barcode)){
					map.put("purchase_list_detail_count",Integer.parseInt(list.get(i).get("purchase_list_detail_count").toString())+purchase_list_detail_count);
					detailDao.updateCartDetail(map);
					flag=false;
					break;
				}
			}
			if(flag){
				map.put("purchase_list_detail_count", purchase_list_detail_count);
				detailDao.newCartDetail(map);
			}
			ns.setStatus(0);
			ns.setMsg("添加成功");
			ns.setData(list.get(0).get("purchase_list_unique"));
			return ns;
		}
		String purchaseUnique=new Date().getTime()+"";
		map.put("purchase_list_unique", purchaseUnique);
		purchase_listDao.newPurchase_list(map);
		map.put("purchase_list_detail_count", purchase_list_detail_count);
		detailDao.newCartDetail(map);
		ns.setStatus(0);
		ns.setMsg("添加成功");
		ns.setData(purchaseUnique);
		return ns;
	}
	/**
	 * 查询商品供应商及商品供给价格
	 */
	public NoteResult findGoodsSuppliers(String goods_barcode, String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		map.put("goods_barcode", goods_barcode.trim());
		
		ns.setMsg("查询成功");
		return ns;
	}
	/**
	 * 查询购物车内容
	 */
	public NoteResult findPurCartGoods(String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		map.put("purchase_list_statue", 2);
		List<Map<String,Object>> ls=purchase_listDao.findPurchaseDetails(map);
		if(ls.size()>0){
			ns.setStatus(0);
			ns.setMsg("查询成功");
			ns.setData(ls);
			return ns;
		}
		map.put("purchase_list_unique", new Date().getTime());
		purchase_listDao.newPurchase_list(map);
		ns.setStatus(1);
		ns.setMsg("创建新的购物车！");
		return ns;
	}
	/**
	 * 移除购物车的内容
	 */
	public NoteResult rmCartGoods(String shop_unique, String goods_barcode) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		map.put("purchase_list_statue",2);
		map.put("goods_barcode", goods_barcode.trim());
		int k=purchase_listDao.rmCartGoods(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("删除成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("删除失败，购物车可能没有该产品");
		return ns;
	}
	/**
	 * 将购物车里的商品转换为新的订单
	 */
	public NoteResult updateCart(String goods_barcode, Integer purchase_list_detail_count,
			String supplier_unique,String purchase_list_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("purchase_list_statue",2);
		map.put("goods_barcode", goods_barcode.trim());
		if(supplier_unique!=null){
			map.put("supplier_unique", supplier_unique.trim());
		}
		map.put("purchase_list_detail_count", purchase_list_detail_count);
		map.put("purchase_list_unique", purchase_list_unique);
		detailDao.updateCartDetail(map);
		ns.setStatus(0);
		ns.setMsg("更新成功");
		return ns;
	}
}
