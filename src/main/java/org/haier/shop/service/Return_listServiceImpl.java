package org.haier.shop.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.dao.CustomerDao;
import org.haier.shop.dao.Return_listDao;
import org.haier.shop.dao.Return_list_detailDao;
import org.haier.shop.entity.Customer;
import org.haier.shop.entity.Return;
import org.haier.shop.util.ExcelUtil;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("return_listService")
@Transactional
public class Return_listServiceImpl implements Return_listService{
	@Resource
	private Return_listDao returnDao;
	@Resource
	private CustomerDao cusDao;
	@Resource
	private Return_list_detailDao detailDao;
	/**
	 * 根据输入条件，查询满足条件退货订单
	 */
	public NoteResult return_lists_find(Integer ret_list_id, Timestamp startDate, Timestamp endDate,
			 String ret_list_state, String ret_list_handlestate,String shop_unique,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Customer> cuslist=new ArrayList<Customer>();
		if("".equals(ret_list_state)||"0".equals(ret_list_state)){
			ret_list_state=null;
		}
		
		if("".equals(ret_list_handlestate)||"0".equals(ret_list_handlestate)){
			ret_list_handlestate=null;
		}
		map.put("ret_list_id",ret_list_id);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("ret_list_state", ret_list_state);
		map.put("ret_list_handlestate", ret_list_handlestate);
		if(!"undefined".equals(shop_unique)&&shop_unique!=null&&!"null".equals(shop_unique)){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		List<Map<String,Object>> retlist=returnDao.findUnDoRets(map);
		ns.setStatus(0);
		ns.setData1(cuslist);
		ns.setMsg("查询成功");
		ns.setData(retlist);
		return ns;
		}
	/**
	 * 查询订单详情
	 */
	public NoteResult return_list_find(String ret_list_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ret_list_unique", ret_list_unique);
		List<Map<String,Object>> detaillist=returnDao.finddetails(map);
		ns.setData(detaillist);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		return ns;
	}
	/**
	 * 保存订单
	 */
	public NoteResult return_list_save(String ret_list_unique, String ret_list_state, String ret_list_handlestate) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("ret_list_unique", ret_list_unique);
		map.put("ret_list_state", ret_list_state);
		map.put("ret_list_handlestate",ret_list_handlestate);
		int k=returnDao.updateReturn_list_detail(map);
		if(k==1){
			ns.setMsg("修改订单成功");
			ns.setStatus(0);
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("你所保存的订单已删除");
		return ns;
	}
	/**
	 * 生产订单excel表
	 */
	public NoteResult excel(Integer ret_list_id, Timestamp startDate, Timestamp endDate, String ret_list_state,
			String ret_list_handlestate, String shop_unique,HttpServletRequest request,String manager_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if("".equals(ret_list_state)||"0".equals(ret_list_state)){
			ret_list_state=null;
		}
		if("".equals(ret_list_handlestate)||"0".equals(ret_list_handlestate)){
			ret_list_handlestate=null;
		}
		map.put("ret_list_id",ret_list_id);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("ret_list_state", ret_list_state);
		map.put("ret_list_handlestate", ret_list_handlestate);
		map.put("shop_unique", shop_unique);
		List<Return> list=returnDao.findReturns(map);
		if(list.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有相关订单");
			return ns;
		}
		String path=request.getSession().getServletContext().getRealPath(File.separator)+"xlsx"+File.separator+shop_unique+File.separator; 
		System.out.println(path);
		File p=new File(path);
		if(!p.exists()){
			p.mkdirs();
		}
		File f=new File(path+"return.xls");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			OutputStream out=new FileOutputStream(f);
			ExcelUtil<Return> e=new ExcelUtil<Return>();
			e.buildExcel(list, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ns.setStatus(0);
		ns.setMsg("保存成功");
		ns.setData(list);
		ns.setAddress(f);
		return ns;
	}
	/**
	 * 单击未处理退货订单，显示未处理订单界面
	 */
	public NoteResult undo_ret(String manager_unique,String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(shop_unique!=null){
			map.put("shop_unique", shop_unique);
		}else{
			map.put("manager_unique", manager_unique);
		}
		map.put("ret_list_handlestate", 1);
		List<Map<String,Object>> retlist=returnDao.findUnDoRets(map);
		if(retlist.size()==0){
			ns.setStatus(1);
			ns.setMsg("没有未处理退货订单");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(retlist);
		return ns;
	}
}
