package org.haier.shop.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.dao.CustomerDao;
import org.haier.shop.dao.GoodsDao;
import org.haier.shop.dao.Purchase_listDao;
import org.haier.shop.dao.Return_listDao;
import org.haier.shop.dao.Sale_listDao;
import org.haier.shop.dao.Sale_list_detailDao;
import org.haier.shop.entity.Sale;
import org.haier.shop.util.ExcelUtil;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sale_listService")
@Transactional
public class Sale_listServiceImpl implements Sale_listService{
	@Resource 
	private Sale_listDao sale_listDao;
	@Resource
	private CustomerDao cusDao;
	@Resource
	private Sale_list_detailDao sale_detailDao;
	@Resource
	private Return_listDao retDao;
	@Resource
	private GoodsDao goodDao;
	@Resource
	private Purchase_listDao purDao;
	/**
	 * 若用户信息不为空，
	 * 先根据用户信息查询出用户相关的唯一编号，
	 * 再联合其他信息查询出所以满足条件的订单
	 */
	public NoteResult sale_lists_find(String shop_unique,Integer sale_list_id, Timestamp startDate,Timestamp endDate, String sale_list_state, String sale_list_handlestate,String manager_unique) {
		//返回结果类
		NoteResult ns=new NoteResult();
		//存储查询条件
		Map<String,Object> map=new HashMap<String,Object>();
		if("0".equals(sale_list_state)||sale_list_state==null){
			sale_list_state=null;
		}
		if("0".equals(sale_list_handlestate)){
			sale_list_handlestate=null;
		}
		if(sale_list_id!=null){
			String sale_list_id1="%"+sale_list_id+"%";
			map.put("sale_list_id", sale_list_id1);
		}
		map.put("sale_list_state",sale_list_state);
		map.put("sale_list_handlestate",sale_list_handlestate);
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		List<Map<String,Object>> salelists=new ArrayList<Map<String,Object>>();
		if(shop_unique!=null&&!shop_unique.equals("undefined")&&!"null".equals(shop_unique)){
			map.put("shop_unique", shop_unique);
			salelists=sale_listDao.findSales(map);
		}else{
			map.put("manager_unique", manager_unique);
			salelists=sale_listDao.findAllSales(map);
		}
		ns.setData(salelists);
		ns.setMsg("查询成功");
		ns.setStatus(0);
		return ns;
	}
	/**
	 * 销售订单详情
	 */
	public NoteResult sale_list_find(Integer sale_list_id) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sale_list_id", sale_list_id);
		List<Map<String,Object>>list=sale_listDao.findSale_listss(map);
		String sale_list_address=(String)list.get(0).get("sale_list_address");
		if(sale_list_address!=null){
			String[] adds=sale_list_address.split(";");
			String cus_name=adds[0];
			sale_list_address=adds[1];
			String cus_phone=adds[2];
			list.get(0).put("sale_list_address", sale_list_address);
			list.get(0).put("cus_name", cus_name);
			list.get(0).put("cus_phone", cus_phone);
		}
		ns.setData(list);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		return ns;
	}
	/**
	 * 订单更新
	 */
	public NoteResult sale_list_update(Integer sale_list_id, Double sale_list_delfee, Double sale_list_total,
			String sale_list_remarks, String sale_list_state, String sale_list_handlestate) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sale_list_id", sale_list_id);
		map.put("sale_list_delfee", sale_list_delfee);
		map.put("sale_list_total", sale_list_total);
		if("0".equals(sale_list_handlestate)||"".equals(sale_list_handlestate)){
			sale_list_handlestate=null;
		}
		if("0".equals(sale_list_state)||"".equals(sale_list_state)){
			sale_list_state=null;
		}
		if("".equals(sale_list_remarks)){
			sale_list_remarks=null;
		}
		map.put("sale_list_state", sale_list_state);
		map.put("sale_list_handlestate",sale_list_handlestate);
		map.put("sale_list_remarks",sale_list_remarks);
		int result=sale_listDao.updateSale_list(map);
		if(result==1){
			ns.setStatus(0);
			ns.setMsg("保存成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("保存异常");
		return ns;
	}
	/**
	 * 首页未处理订单提醒
	 */
	public NoteResult index_alert(String shop_unique,String manager_unique){
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		//未处理订单提醒(已付款)
		map.put("sale_list_state",3);
		map.put("sale_list_handlestate",2);
		if(!"".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique.trim());
		}else{
			map.put("manager_unique", manager_unique.trim());
		}
		List<Integer> list=new ArrayList<Integer>();
		int k=sale_listDao.findSale_lists(map).size();
		map.clear();
		map.put("sale_list_state", 1);
		map.put("sale_list_handlestate",2);
		if(!"".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		int m=sale_listDao.findSale_lists(map).size();
		list.add(k+m);
		//未处理退货订单提醒
		map.clear();
		if(!"".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique.trim());
		}else{
			map.put("manager_unique", manager_unique.trim());
		}
		map.put("ret_list_handlestate", "1");
		list.add(retDao.findReturn_lists(map).size());
		ns.setStatus(0);
		ns.setData(list);
		ns.setMsg("查询成功");
		return ns;
	}
	/**
	 * 查询当日营业状况
	 */
	public NoteResult conditions_alert(String shop_unique,Timestamp startDate,Timestamp endDate,String manager_unique) {

		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if("".equals(shop_unique)){
			map.put("manager_unique",manager_unique);
		}else{
			map.put("shop_unique", shop_unique);
		}
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		List<Map<String,Object>> list=sale_listDao.findSale_lists(map);
		Double total=0.0;
		for(Map<String,Object> sale:list){
			total+=Double.parseDouble(sale.get("sale_list_total").toString());
		}
		ns.setStatus(0);
		ns.setData(total);
		return ns;
	}
	
	/**
	 * 查询某段时间内产品销售情况
	 * 即销售订单中每种产品的销售业绩
	 */
	public NoteResult findSales_details(String shop_unique,Timestamp startDate,Timestamp endDate,String manger_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manger_unique);
		}
		List<Map<String,Object>> list=sale_listDao.findSales_details(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 查询商品周期内订单总金额
	 * 周期分别为，当天，一周，一月，一季度，一年，当年
	 */
	public NoteResult findturnover(String shop_unique,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		Calendar c=Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND, 0);
		Date startDate=c.getTime();
		c.set(Calendar.DATE, c.get(Calendar.DATE)+1);
		Date endDate=c.getTime();
		
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		//当日销售记录
		if(sale_listDao.findturnover(map)==null){
		}else{
			list.addAll(sale_listDao.findturnover(map));
		}
		if(purDao.findPurchases(map)==null){
		}else{
			list.addAll(purDao.findPurchases(map));
		}
		double l=list.size();
		//一周销售记录
		c.set(Calendar.DATE,c.get(Calendar.DATE)-6);
		endDate=startDate;
		startDate=c.getTime();
		map.put("startDate", startDate);
		map.put("endDate",Calendar.getInstance().getTime());
		if(sale_listDao.findturnover(map)==null){
		}else{
			list.addAll(sale_listDao.findturnover(map));
		}
		if(purDao.findPurchases(map)==null){
		}else{
			list.addAll(purDao.findPurchases(map));
		}
		//一月销售记录
		c.set(Calendar.MONTH,c.get(Calendar.MONTH)-1);
		startDate=c.getTime();
		map.put("startDate", startDate);
		if(sale_listDao.findturnover(map)==null){
		}else{
			list.addAll(sale_listDao.findturnover(map));
		}
		if(purDao.findPurchases(map)==null){
		}else{
			list.addAll(purDao.findPurchases(map));
		}
		//一年的营业额
		c.set(Calendar.MONTH,c.get(Calendar.MONTH)-12);
		map.put("startDate", c.getTime());
		if(sale_listDao.findturnover(map)==null){
		}else{
			list.addAll(sale_listDao.findturnover(map));
		}
		if(purDao.findPurchases(map)==null){
		}else{
			list.addAll(purDao.findPurchases(map));
		}
		//当年的营业额
		c.set(Calendar.YEAR,Calendar.getInstance().get(Calendar.YEAR));
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DATE,0);
		map.put("startDate", startDate);
		map.put("endDate", Calendar.getInstance().getTime());
		if(sale_listDao.findturnover(map)==null){
		}else{
			list.addAll(sale_listDao.findturnover(map));
		}
		if(purDao.findPurchases(map)==null){
			
		}else{
			list.addAll(purDao.findPurchases(map));
		}
		map.clear();
		map.put("size", l);
		list.add(map);
		ns.setData(list);
		ns.setMsg("查询成功");
		ns.setStatus(0);
		return ns;
	}
	
	/**
	 * 查询订单详情并生成excel表
	 */
	public NoteResult excel(String shop_unique,Integer sale_list_id, Timestamp startDate,
			Timestamp endDate, String sale_list_state, String sale_list_handlestate,HttpServletRequest request,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if("0".equals(sale_list_state)){
			sale_list_state=null;
		}
		if("0".equals(sale_list_handlestate)){
			sale_list_handlestate=null;
		}
		map.put("sale_list_id", sale_list_id);
		map.put("sale_list_state",sale_list_state);
		map.put("sale_list_handlestate",sale_list_handlestate);
		map.put("startDate",startDate);
		map.put("endDate",endDate);
		List<Sale> list=new ArrayList<Sale>();
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)){
			map.put("shop_unique", shop_unique);
			list=sale_listDao.findSaless(map);
		}else{
			map.put("manager_unique", manager_unique);
			list=sale_listDao.findAllSaless(map);
		}
		String path=request.getSession().getServletContext().getRealPath(File.separator)+"xlsx"+File.separator+shop_unique+File.separator; 
		File p=new File(path);
		if(!p.exists()){
			p.mkdirs();
		}
		File f=new File(path+"sale.xls");
		if(!f.exists()){
			try {
				f.createNewFile();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(f.exists()){
			f.delete();
		}
		try {
			OutputStream out=new FileOutputStream(f);
			ExcelUtil<Sale> e=new ExcelUtil<Sale>();
			e.buildExcel(list, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ns.setData(list);
		ns.setStatus(0);
		ns.setMsg("保存成功");
		ns.setAddress(f);
		return ns;
	}
	/**
	 * 首页查询未处理订单
	 */
	public NoteResult undo_sale(String manager_unique,String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(manager_unique!=null){
			map.put("manager_unique", manager_unique);
		}else{
			map.put("shop_unique", shop_unique);
		}
		map.put("sale_list_state", '1');
		map.put("sale_list_handlestate", '2');
		List<Map<String,Object>> list=sale_listDao.findSales(map);
		map.put("sale_list_state", '3');
		list.addAll(sale_listDao.findSales(map));
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有未处理订单");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 进货及滞销提醒
	 * @param shop_unique
	 */
	public NoteResult warning(String shop_unique,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"undefined".equals(shop_unique)&&!"null".equals(shop_unique)&&shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		//查询所有产品的库存量
		map.put("goodsMessage", "%%%");
		List<Map<String,Object>> goodslist=goodDao.findGoodss(map);
		//获取当前系统时间
		Date endDate=new Date();
		//将起始时间调整为一个月前
		Date startDate=new Date(endDate.getTime()-1000l*24*3600*30);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		//建立集合存储每样商品的销售总量
		List<Map<String,Object>> totallist=sale_listDao.findSales_details(map);
		List<Map<String,Object>> retlist=new ArrayList<Map<String,Object>>();
		for(int i=0;i<goodslist.size();i++){
			for(int j=0;j<totallist.size();j++){
				if(goodslist.get(i).get("goods_name").equals(totallist.get(j).get("goods_name"))){
					
					if(goodslist.get(i).get("goods_count")!=null&&totallist.get(j).get("sum")!=null){
						if((Integer)goodslist.get(i).get("goods_count")/Integer.parseInt(totallist.get(j).get("sum").toString())*30>30||(Integer)goodslist.get(i).get("goods_count")/Integer.parseInt(totallist.get(j).get("sum").toString())*30<7){
							Map<String,Object> m1ap=new HashMap<String,Object>();
							m1ap.put("goods_name", goodslist.get(i).get("goods_name"));
							m1ap.put("sum", goodslist.get(i).get("goods_count"));
							m1ap.put("average",Double.parseDouble((totallist.get(j).get("sum").toString()))/30);
							m1ap.put("hold",(Integer)goodslist.get(i).get("goods_count")/Double.parseDouble(totallist.get(j).get("sum").toString())*30);
							retlist.add(m1ap);
						}
					}
				}
			}
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(retlist);
		return ns;
	}
	/**
	 * 查询五分钟之内的新订单
	 * @param manager_unique:商铺管理员
	 * @param shop_unique:店铺编号
	 */
	public NoteResult checkNewOrder(String shop_unique, String manager_unique, Timestamp startTime) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(shop_unique!=null&&!"".equals(shop_unique)){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		map.put("startTime", startTime);
		map.put("sale_list_statue", '1');//货到付款
		map.put("sale_list_handlestate", 2);//订单未发货
		List<Map<String,Object>> list=sale_listDao.findSale_lists(map);
		map.put("sale_list_statue", 3);
		List<Map<String,Object>> list1=sale_listDao.findSale_lists(map);
		for(Map<String,Object> mp:list1){
			int k=0;
			for(Map<String,Object> ma:list){
				if(mp.get("sale_list_id")==ma.get("sale_list_id")){
					break;
				}else{
					k++;
				}
			}
			if(k==list.size()){
				list.add(mp);
			}
		}
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有新订单需要处理");
			return ns;
		}
		ns.setData(list);
		ns.setStatus(0);
		ns.setMsg("您有"+list.size()+"条新订单未处理");
		return ns;
	}
	/**
	 * 查询所有订单支付状态
	 */
	public NoteResult loadSaleListState() {
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=sale_listDao.loadSaleListState();
		ns.setStatus(0);
		ns.setData(list);
		ns.setMsg("查询成功");
		return ns;
	}
	/**
	 * 查询所有订单处理状态
	 */
	public NoteResult loadSaleListHandleState() {
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=sale_listDao.loadSaleListHandleState();
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
}
