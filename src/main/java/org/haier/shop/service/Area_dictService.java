package org.haier.shop.service;

import org.haier.shop.util.NoteResult;

public interface Area_dictService {
	/**
	 * 自动加载省份
	 * @return
	 */
	public NoteResult find_provinces();
	/**
	 * 自动加载市
	 * 
	 */
	public NoteResult find_cities(String area_dict_num);
	/**
	 * 自动加载县区
	 */
	public NoteResult find_contries(String area_dict_num);
	/**
	 * 根据区县编号，查询省市名称及编号
	 * @param area_dict_num
	 * @return
	 */
	public NoteResult findPC(String area_dict_num);
}
