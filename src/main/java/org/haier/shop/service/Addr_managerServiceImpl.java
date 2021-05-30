package org.haier.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.Addr_managerDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("addr_ManagerService")
@Transactional
public class Addr_managerServiceImpl implements Addr_managerService{
	@Resource 
	private Addr_managerDao addrDao;
	/**
	 * 根据客户编号，查询会员送货地址详情
	 */
	public NoteResult findAddr_details(Integer cus_id) {
		
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("cus_id", cus_id);
		List<Map<String,Object>> list=addrDao.findAddr_details(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 送货地址编辑界面
	 * 根据送货地址编号，查询送货单地址详情
	 */
	public NoteResult findAddr_detail(Integer addr_id){
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("addr_id", addr_id);
		List<Map<String,Object>> list=addrDao.findAddr_detail(map);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list.get(0));
		return ns;
	}
	/**
	 * 更新送货地址信息
	 * @param addr_city 所在城市
	 * @param addr_id 送货地址编号
	 * @param addr_receiver_name 收货人姓名
	 * @param addr_phone 收货人联系方式
	 * @param addr_addr 收货人详细地址
	 * @param addr_province 收货人省份
	 * @param addr_county 收货人所在区县
	 * @param addr_defaultmorn状态
	 */
	public NoteResult updateAddr_detail(Integer addr_id, String addr_receiver_name, String addr_phone, String addr_addr,
			String addr_province, String addr_city, String addr_county, String addr_default,String cus_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		if(addr_province.equals("0")){
			addr_province=null;
		}
		if(addr_city.equals("0")){
			addr_city=null;
		}
		if("0".equals(addr_county)){
			addr_county=null;
		}
		if("0".equals(addr_default)){
			addr_default=null;
		}
		if("1".equals(addr_default)){
			map.put("addr_default", 2);
			map.put("cus_unique", cus_unique);
			addrDao.updateAddr(map);
		}
		map.put("addr_id", addr_id);
		map.put("addr_receiver_name", addr_receiver_name);
		map.put("addr_phone", addr_phone);
		map.put("addr_addr", addr_addr);
		map.put("addr_province", addr_province);
		map.put("addr_city", addr_city);
		map.put("addr_county", addr_county);
		map.put("addr_default", addr_default);
		int k=addrDao.updateAddr_detail(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("更新成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("更新失败");
		return ns;
	}
}
