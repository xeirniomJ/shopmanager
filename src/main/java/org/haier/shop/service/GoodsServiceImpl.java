package org.haier.shop.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.dao.GoodsDao;
import org.haier.shop.dao.Goods_kindDao;
import org.haier.shop.dao.ManagerDao;
import org.haier.shop.dao.ShopDao;
import org.haier.shop.dao.SupplierDao;
import org.haier.shop.util.ChineseCharToEn;
import org.haier.shop.util.Load;
//import org.haier.shop.entity.Goods;
//import org.haier.shop.entity.Goods_kind;
import org.haier.shop.util.NoteResult;
import org.haier.shop.util.PicSaveUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private Goods_kindDao kindDao;
	@Resource
	private ShopDao shopDao;
	@Resource
	private ManagerDao managerDao;
	@Resource
	private SupplierDao supplierDao;

	/**
	 * 查询满足条件的商品
	 */
	public NoteResult findGoods(String goodsmessage, String goods_kind_parunique, String goods_kind_unique,
			String shop_unique, String manager_unique) {
		NoteResult ns = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		// 若未选择商品大类，则自动修改为空
		if ("".equals(goods_kind_parunique) || "0".equals(goods_kind_parunique)) {
			goods_kind_parunique = null;
		}
		// 若未选择商品小类，自动修改为空
		if ("".equals(goods_kind_unique) || "0".equals(goods_kind_unique)) {
			goods_kind_unique = null;
		}
		// 如果商品信息未输入，设置为空
		if ("".equals(goodsmessage)) {
			goodsmessage = null;
		}
		if (goodsmessage != null) {
			goodsmessage = "%" + goodsmessage + "%";
		}
		// 使用联合查询结果集
		List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
		map.put("goodsmessage", goodsmessage);
		map.put("goods_kind_parunique", goods_kind_parunique);
		map.put("goods_kind_unique", goods_kind_unique);
		if (!"undefined".equals(shop_unique) && shop_unique != null && !"null".equals(shop_unique)) {
			map.put("shop_unique", shop_unique);
		} else {
			map.put("manager_unique", manager_unique);
		}
		reslist.addAll(goodsDao.findGoods1(map));
		if (reslist.size() == 0) {
			ns.setStatus(1);
			ns.setMsg("没有符合条件的产品");
			return ns;
		}
		
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(reslist);
		return ns;
	}

	/**
	 * 查询单个商品信息，以便修改
	 */
	public NoteResult findGood(String goods_barcode, String shop_unique) {
		NoteResult ns = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_barcode", goods_barcode);
		map.put("shop_unique", shop_unique);
		List<Map<String, Object>> list = goodsDao.findGood(map);
		if (list.size() > 0) {
			ns.setData(list.get(0));
		} else {
			ns.setStatus(1);
			ns.setMsg("查询失败");
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		return ns;
	}
	
	/**
	 * 促销管理界面，促销状态商品查询
	 */
	public NoteResult findGoods_promotion(String goodsmessage, String goods_kind_parunique, String goods_kind_unique,
			String goods_promotion, String shop_unique, String manager_unique) {
		NoteResult ns = new NoteResult();
		if ("".equals(goodsmessage)) {
			goodsmessage = null;
		}
		if ("0".equals(goods_kind_parunique)) {
			goods_kind_parunique = null;
		}
		if ("0".equals(goods_kind_unique)) {
			goods_kind_unique = null;
		}
		if ("请选择促销状态".equals(goods_promotion) || "0".equals(goods_promotion)) {
			goods_promotion = null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsmessage", goodsmessage);
		map.put("goods_kind_parunique", goods_kind_parunique);
		map.put("goods_kind_unique", goods_kind_unique);
		map.put("goods_promotion", goods_promotion);
		if (!"undefined".equals(shop_unique) && !"null".equals(shop_unique) && shop_unique != null) {
			map.put("shop_unique", shop_unique);
		} else {
			map.put("manager_unique", manager_unique);
		}
		// 使用联合查询结果集
		List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
		reslist.addAll(goodsDao.findGoods1(map));
		if (reslist.size() == 0) {
			ns.setStatus(1);
			ns.setMsg("没有符合条件的产品");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(reslist);
		return ns;
	}

	/**
	 * 修改商品促销信息
	 */
	public NoteResult updateGoods_promotion(String goods_barcode, String goods_promotion, Double goods_discount,
			Double goods_sale_price, String shop_unique) {
		NoteResult ns = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_id", goods_barcode);
		if (goods_promotion == "" || goods_promotion == "0") {
			goods_promotion = null;
		}
		map.put("goods_promotion", goods_promotion);
		map.put("goods_discount", goods_discount);
		map.put("goods_sale_price", goods_sale_price);
		int k = goodsDao.updateGoods_promotion(map);
		if (k == 1) {
			ns.setStatus(0);
			ns.setMsg("更新商品促销状态成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("更新商品促销状态不成功");
		return ns;
	}

	/**
	 * 更新商品信息
	 */
	public NoteResult updateGoods(HttpServletRequest request, String goods_id, String goods_name, String goods_brand,
			String goods_barcode, String goods_alias, String goods_sale_price, String goods_in_price, String goods_life,
			String goods_points, String goods_address, String goods_contain, String goods_standard,
			String goods_kind_parunique, String goods_kind_unique, String shop_unique, Integer goods_count,
			String supplier_unique) {
		NoteResult ns = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (!"".equals(goods_name) && goods_name != null) {
				map.put("goods_name", URLDecoder.decode(goods_name, "utf-8"));
			}
			if (!"".equals(goods_brand) && goods_brand != null) {
				map.put("goods_brand", URLDecoder.decode(goods_brand, "utf-8"));
			}
			if (!"".equals(goods_barcode) && goods_barcode != null) {
				map.put("goods_barcode", URLDecoder.decode(goods_barcode.trim(), "utf-8").trim());
			}
			if (!"".equals(goods_alias) && goods_alias != null) {
				map.put("goods_alias", URLDecoder.decode(goods_alias, "utf-8"));
			} else {
				if (!"".equals(goods_name) && goods_name != null) {
					map.put("goods_alias", ChineseCharToEn.getAllFirstLetter(URLDecoder.decode(goods_name, "utf-8")));
				}
			}
			if (!"".equals(goods_address) && goods_address != null) {
				map.put("goods_address", URLDecoder.decode(goods_address, "utf-8"));
			}
			if (!"".equals(goods_standard) && goods_standard != null) {
				map.put("goods_standard", URLDecoder.decode(goods_standard, "utf-8"));
			}
			if (goods_id != null) {
				map.put("goods_id", URLDecoder.decode(goods_id, "utf-8"));
			}
			if (!URLDecoder.decode(goods_kind_unique, "utf-8").equals("0")) {
				map.put("goods_kind_unique", URLDecoder.decode(goods_kind_unique, "utf-8"));
			} else {
				map.put("goods_kind_unique", null);

			}
			if (goods_sale_price != null && !goods_sale_price.equals("")) {
				map.put("goods_sale_price", URLDecoder.decode(goods_sale_price, "utf-8"));
			}
			if (goods_in_price != null && !"".equals(goods_in_price)) {
				map.put("goods_in_price", URLDecoder.decode(goods_in_price, "utf-8"));
			}
			if (shop_unique != null && !"".equals(shop_unique)) {
				map.put("shop_unique", URLDecoder.decode(shop_unique, "utf-8"));
				shop_unique = URLDecoder.decode(shop_unique, "utf-8");
			}
			if (!"".equals(goods_life) && goods_life != null) {
				map.put("goods_life", URLDecoder.decode(goods_life, "utf-8"));
			}
			if (goods_points != null && !"".equals(goods_points)) {
				map.put("goods_points", URLDecoder.decode(goods_points, "utf-8"));
			}
			if (goods_contain != null && !"".equals(goods_contain)) {
				map.put("goods_contain", URLDecoder.decode(goods_contain, "utf-8"));
			}
			map.put("goods_count", goods_count);
			if (supplier_unique != null && !"".equals(supplier_unique)) {
				map.put("supplier_unique", URLDecoder.decode(supplier_unique, "utf-8"));
			}

			MultipartFile file = null;
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; // 获得文件：
				Map<String, MultipartFile> mp = multipartRequest.getFileMap();
				file = mp.get("file");
			}
			if (file != null) {
				String orName = file.getOriginalFilename();
				int index = orName.lastIndexOf(".");
				String lastName = orName.substring(index);
				String goods_picturepath = File.separator + "home" + File.separator + "apache-tomcat-7.0.42"
						+ File.separator + "webapps" + File.separator + "image" + File.separator + shop_unique
						+ File.separator;
				File fi = new File(goods_picturepath);
				if (!fi.exists()) {
					fi.mkdirs();
				}
				String picName = null;
				picName = URLDecoder.decode(goods_barcode, "utf-8");
				PicSaveUtil.handleFileUpId(file, request, goods_picturepath, picName);
				goods_picturepath = "121.42.189.191:80" + File.separator + "image" + File.separator + shop_unique
						+ File.separator;
				goods_picturepath += picName + "." + lastName;
				map.put("goods_picturepath", goods_picturepath);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int k = goodsDao.updateGoods(map);
		if (k == 0) {
			ns.setStatus(1);
			ns.setMsg("更新失败");
			return ns;
		}
		ns.setStatus(0);
		ns.setData(k);
		ns.setMsg("更新成功");
		return ns;
	}

	/**
	 * 添加新商品
	 */
	public NoteResult newGoods(String goods_name, String goods_brand, String goods_barcode, String goods_alias,
			Double goods_sale_price, Double goods_in_price, Integer goods_life, Integer goods_points,
			String goods_address, Integer goods_contain, String goods_standard, String goods_kind_parunique,
			String goods_kind_unique, String shop_unique, String manager_unique) {
		NoteResult ns = new NoteResult();
		if ("".equals(goods_name)) {
			goods_name = null;
		}
		if ("".equals(goods_brand)) {
			goods_brand = null;
		}
		if ("".equals(goods_barcode)) {
			goods_barcode = null;
		}
		if ("".equals(goods_alias)) {
			goods_alias = null;
		}
		if ("".equals(goods_address)) {
			goods_address = null;
		}
		if ("".equals(goods_standard)) {
			goods_standard = null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_kind_group", goods_kind_parunique);
		map.put("goods_kind_name", goods_kind_unique);
		map.put("goods_kind_unique", goods_kind_unique);
		map.put("goods_name", goods_name);
		map.put("goods_brand", goods_brand);
		map.put("goods_barcode", goods_barcode);
		map.put("goods_alias", goods_alias);
		map.put("goods_sale_price", goods_sale_price);
		map.put("goods_in_price", goods_in_price);
		map.put("goods_life", goods_life);
		map.put("goods_points", goods_points);
		map.put("goods_address", goods_address);
		map.put("goods_contain", goods_contain);
		map.put("goods_standard", goods_standard);
		map.put("goods_unique", new Date().getTime() + "");
		map.put("goods_discount", 1);
		System.out.println("添加的商品信息为：1：" + map);
		if (shop_unique != null && !"null".equals(shop_unique) && !"undefined".equals(shop_unique)) {
			map.put("shop_unique", shop_unique);
			goodsDao.newGoods(map);
		} else {
			map.put("manager_unique", manager_unique);
		}
		ns.setStatus(0);
		ns.setMsg("添加成功");
		return ns;
	}

	/**
	 * 新商品添加测试
	 */
	public NoteResult newGoods1(HttpServletRequest request, String goods_name, String goods_brand, String goods_barcode,
			String goods_alias, String goods_sale_price, String goods_in_price, String goods_life, String goods_points,
			String goods_address, String goods_contain, String goods_standard, String goods_kind_unique,
			String shop_unique, String manager_unique, Integer goods_count) {
		NoteResult ns = new NoteResult();
		MultipartFile file = null;
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; // 获得文件：
			Map<String, MultipartFile> mp = multipartRequest.getFileMap();
			file = mp.get("file");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("goods_unique", new Date().getTime());
			if (!"".equals(goods_name) && goods_name != null) {
				map.put("goods_name", URLDecoder.decode(goods_name, "utf-8"));
			}
			if (goods_count != null) {
				map.put("goods_count", goods_count);
			} else {
				map.put("goods_count", 0);
			}
			if (!"".equals(goods_brand) && goods_brand != null) {
				map.put("goods_brand", URLDecoder.decode(goods_brand, "utf-8"));
			}
			if (!"".equals(goods_barcode) && goods_barcode != null) {
				map.put("goods_barcode", URLDecoder.decode(goods_barcode, "utf-8"));
			}
			if (!"".equals(goods_alias) && goods_alias != null) {
				map.put("goods_alias", URLDecoder.decode(goods_alias, "utf-8"));
			} else {
				map.put("goods_alias", ChineseCharToEn.getAllFirstLetter(URLDecoder.decode(goods_name, "utf-8")));
			}
			if (!"".equals(goods_address) && goods_address != null) {
				map.put("goods_address", URLDecoder.decode(goods_address, "utf-8"));
			}
			if (!"".equals(goods_standard) && goods_standard != null) {
				map.put("goods_standard", URLDecoder.decode(goods_standard, "utf-8"));
			}
			if (!"".equals(goods_kind_unique) && goods_kind_unique != null) {
				map.put("goods_kind_unique", URLDecoder.decode(goods_kind_unique, "utf-8"));
			}
			if (goods_sale_price != null && !goods_sale_price.equals("")) {
				map.put("goods_sale_price", URLDecoder.decode(goods_sale_price, "utf-8"));
			}
			if (goods_in_price != null && !"".equals(goods_in_price)) {
				map.put("goods_in_price", URLDecoder.decode(goods_in_price, "utf-8"));
			}
			if (!"".equals(goods_life) && goods_life != null) {
				map.put("goods_life", URLDecoder.decode(goods_life, "utf-8"));
			}
			if (goods_points != null && !"".equals(goods_points)) {
				map.put("goods_points", URLDecoder.decode(goods_points, "utf-8"));
			}
			if (goods_contain != null && !"".equals(goods_contain)) {
				map.put("goods_contain", URLDecoder.decode(goods_contain, "utf-8"));
			}
			if (!"".equals(shop_unique) && !"null".equals(shop_unique) && shop_unique != null) {
				map.put("shop_unique", URLDecoder.decode(shop_unique, "utf-8"));
			}
			int k = goodsDao.findGood(map).size();
			System.out.println("信息查询！" + map);
			if (k >= 1) {
				ns.setStatus(1);
				ns.setMsg("该类已存在");
				return ns;
			}
			if (file != null) {
				String goods_picturepath = File.separator + "home" + File.separator + "apache-tomcat-7.0.42"
						+ File.separator + "webapps" + File.separator + "image" + File.separator + shop_unique
						+ File.separator;
				File fi = new File(goods_picturepath);
				if (!fi.exists()) {
					fi.mkdirs();
				}
				String picName = null;
				String lastName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
				picName = URLDecoder.decode(goods_barcode, "utf-8");
				PicSaveUtil.handleFileUpId(file, request, goods_picturepath, picName);
				goods_picturepath = "121.42.189.191:8080" + File.separator + "image" + File.separator + shop_unique
						+ File.separator;
				goods_picturepath += picName + "." + lastName;
				map.put("goods_picturepath", goods_picturepath);
			}
			if (!"".equals(shop_unique) && !"null".equals(shop_unique) && shop_unique != null) {
				map.put("shop_unique", URLDecoder.decode(shop_unique, "utf-8"));
				goodsDao.newGoods(map);
			} else {
				map.put("manager_unique", URLDecoder.decode(manager_unique, "utf-8"));
				List<Map<String, Object>> slist = managerDao.shops_uniquefind(map);
				for (int i = 0; i < slist.size(); i++) {
					map.put("shop_unique", slist.get(i).get("shop_unique"));
					goodsDao.newGoods(map);
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ns.setStatus(0);
		ns.setMsg("保存成功");
		return ns;
	}

	/**
	 * 调货页面，查询商品基本信息
	 * 
	 * @param shop_unique，店铺基本信息
	 * @param goodsmessage,商品基本信息
	 */
	public NoteResult findGoods2(String shop_unique, String goods_message) {
		NoteResult ns = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		if (goods_message == null || goods_message.equals("")) {
			goods_message = "%%%";
		} else {
			goods_message = "%" + goods_message + "%";
		}
		map.put("shop_unique", shop_unique);
		map.put("goods_message", goods_message);
		int k = goodsDao.findGoods2(map).size();
		map.put("page_start", 0);
		map.put("page_size", Load.page_size);
		List<Map<String, Object>> list = goodsDao.findGoods2(map);
		map.clear();
		if (k % Load.page_size == 0) {
			map.put("page_num", k / Load.page_size);
		} else {
			map.put("page_num", k / Load.page_size + 1);
		}
		list.add(map);
		if (list.size() == 1) {
			ns.setStatus(1);
			ns.setMsg("没有满足条件的商品");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}

	/**
	 * 根据输入的页数，查询商品基本信息
	 * 
	 * @param shop_unique
	 *            ,商品编号
	 * @param goods_message,商品信息
	 * @param page_num页码
	 */
	public NoteResult findGood_page(String shop_unique, String goods_message, Integer page_num) {
		NoteResult ns = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_unique", shop_unique);
		if (goods_message == null || goods_message.equals("")) {
			goods_message = "%%%";
		} else {
			goods_message = "%" + goods_message + "%";
		}
		map.put("goods_message", goods_message);
		map.put("page_start", (page_num - 1) * Load.page_size);
		map.put("page_size", Load.page_size);

		List<Map<String, Object>> list = goodsDao.findGoods2(map);
		if (list.size() == 1) {
			ns.setStatus(1);
			ns.setMsg("没有满足条件的商品");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}

	/**
	 * 删除店铺中不需要的商品
	 */
	public NoteResult deleteGoods(String goods_barcode, String shop_unique) {
		NoteResult ns = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_unique", shop_unique);
		map.put("goods_barcode", goods_barcode);
		int k = goodsDao.deleteGoods(map);
		if (k >= 1) {
			ns.setStatus(0);
			ns.setMsg("商品删除成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("商品删除失败");
		return ns;
	}

	/**
	 * 根据店铺所选区县，查询区域内供应商信息
	 */
	public NoteResult findGoodsSuppliers(String goods_barcode, String area_dict_num) {
		NoteResult ns = new NoteResult();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_barcode", goods_barcode);
		map.put("area_dict_num", area_dict_num);
		List<Map<String, Object>> list = supplierDao.findGoodsSuppliers(map);
		if (list.size() == 0) {
			ns.setStatus(1);
			ns.setMsg("该区域没有供应商提供该商品");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(list);
		return ns;
	}
}