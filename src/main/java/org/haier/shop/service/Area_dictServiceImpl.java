package org.haier.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.haier.shop.dao.Area_dictDao;
import org.haier.shop.util.NoteResult;
import org.springframework.stereotype.Service;

@Service("area_dictService")
public class Area_dictServiceImpl implements Area_dictService{
	@Resource
	private Area_dictDao areaDao;
	/**
	 * 自动加载省份
	 */
	public NoteResult find_provinces() {
		NoteResult ns=new NoteResult();
		List<Map<String,Object>> list=areaDao.find_provinces();
		ns.setStatus(0);
		ns.setMsg("查询省份成功");
		ns.setData(list);
		return ns;
	}
	/**
	 * 
	 * 修改省份后，自动加载市
	 */
	public NoteResult find_cities(String area_dict_num) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("area_dict_num", area_dict_num.trim());
		List<Map<String,Object>> list=areaDao.find_cities(map);
		ns.setData(list);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		return ns;
	}
	/**
	 * 修改市后，自动加载县区
	 */
	public NoteResult find_contries(String area_dict_content) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("area_dict_content", area_dict_content.trim());
		List<Map<String,Object>> list=areaDao.find_cities(map);
		list.remove(0);
		ns.setData(list);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		return ns;
	}
	/**
	 * 根据区县编号，查询省市名称及编号
	 */
	public NoteResult findPC(String area_dict_num) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("area_dict_num", area_dict_num);
		List<Map<String,Object>> list=areaDao.findPC(map);
		if(list.size()<1){
			ns.setStatus(1);
			ns.setMsg("输入错误");
			return ns;
		}
		ns.setMsg("查询成功");
		ns.setData(list);
		ns.setStatus(0);
		return ns;
	}
}
