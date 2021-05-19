package org.haier.shop.dao;

import java.util.List;
import java.util.Map;

import org.haier.shop.entity.Sale_list_detail;

public interface Sale_list_detailDao {
	public List<Sale_list_detail> findSale_list_details(Map<String,Object> map);
}
