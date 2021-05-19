package org.haier.shop.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haier.shop.dao.ManagerDao;
import org.haier.shop.dao.ShopDao;
import org.haier.shop.dao.Sup_goodsDao;
import org.haier.shop.util.ChineseCharToEn;
import org.haier.shop.util.Load;
import org.haier.shop.util.NoteResult;
import org.haier.shop.util.PicSaveUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("shopService")
public class ShopServiceImpl implements ShopService{
	@Resource
	private ShopDao shopDao;
	@Resource
	private Sup_goodsDao supGoodsDao;
	@Resource
	private ManagerDao manDao;
	/**
	 * 根据店铺查询店铺周边可为其供货的供货商产品列表
	 * @param shop_unique
	 * @return
	 */
	public NoteResult findPurchaseGoods(String shop_unique,String goods_kind_unique,String goods_kind_parunique,String goods_message,Integer pageNum){
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shop_unique", shop_unique.trim());
		if(!"0".equals(goods_kind_parunique)&&goods_kind_parunique!=null){
			map.put("goods_kind_parunique", goods_kind_parunique.trim());
		}
		if(!"0".equals(goods_kind_unique)&&goods_kind_unique!=null){
			map.put("goods_kind_unique", goods_kind_unique.trim());
		}
		if(!"".equals(goods_message)&&goods_message!=null){
			map.put("goods_message", "%"+goods_message.trim()+"%");
		}else{
			map.put("goods_message", "%%%");
		}
		List<Map<String,Object>> goodsList=supGoodsDao.findSupGoods(map);
		int k=goodsList.size();
		Map<String,Object> ma=new HashMap<String,Object>();
		if(k/Load.apage_size==0){
			ma.put("pageSize", k/Load.apage_size);
		}else{
			ma.put("pageSize", k/Load.apage_size+1);
		}
		if(pageNum==null){
			pageNum=0;
		}
		map.put("startNum", pageNum*Load.apage_size);
		map.put("pageSize", Load.apage_size);
		goodsList=supGoodsDao.findSupGoods(map);
		goodsList.add(ma);
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(goodsList);
		return ns;
	}
	/**
	 * 注册新店铺
	 */
	public NoteResult newShop(String manager_unique, String account, String pwd, String shop_name,
			String shop_address_detail, String shop_phone) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("shop_name", shop_name);
		List<Map<String,Object>> mlist=shopDao.findShop(map);
		map.clear();
		if(mlist.size()>0){
			map.put("shop_unique", mlist.get(0).get("shop_unique"));
		}else{
			map.put("shop_unique", new Date().getTime());
		}
		map.put("manager_account", account);
		int k=shopDao.findShop(map).size();
		map.put("manager_unique", manager_unique);
		map.put("shop_name", shop_name);
		if(k>=1){
			ns.setStatus(1);
			ns.setMsg("前端账号已存在");
			return ns;
		}
		map.put("manager_pwd", pwd);
		map.put("shop_phone", shop_phone);
		map.put("shop_address_detail", shop_address_detail);
		map.put("shop_alias", ChineseCharToEn.getAllFirstLetter("shop_name"));
		k=shopDao.newShop(map);
		if(k==0){
			ns.setStatus(1);
			ns.setMsg("添加新商铺失败");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("创建成功");
		return ns;
	}
	/**
	 * 更新店铺信息
	 * 
	 */
	public NoteResult updateShopDetail(String shop_unique, String shop_name, String shop_phone,
			String shop_address_detail, String manager_pwd, HttpServletRequest request,String shop_remark,String power_supplier) {
		MultipartFile file=null;
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			map.put("shop_unique", URLDecoder.decode(shop_unique,"utf-8"));
			if(shop_address_detail!=null&&!"".equals(shop_address_detail)){
				map.put("shop_address_detail", URLDecoder.decode(shop_address_detail,"utf-8"));
			}
			if(shop_name!=null){
				map.put("shop_name", URLDecoder.decode(shop_name,"utf-8"));
			}
			if(shop_phone!=null){
				map.put("shop_phone", URLDecoder.decode(shop_phone,"utf-8"));
			}
			if(manager_pwd!=null&&!"".equals(manager_pwd)){
				map.put("manager_pwd", URLDecoder.decode(manager_pwd,"utf-8"));
			}
			if(shop_remark!=null){
				map.put("shop_remark", URLDecoder.decode(shop_remark,"utf-8"));
			}
			if(power_supplier!=null){
				map.put("power_supplier", power_supplier);
			}
			if(request instanceof MultipartHttpServletRequest){
				MultipartHttpServletRequest re=(MultipartHttpServletRequest)request;
				Map<String, MultipartFile> mp=re.getFileMap();
				file=mp.get("file");
			}
			if(file!=null){
				//检查保存路径是否存在
				String goods_picturepath = File.separator + "home" + File.separator + "apache-tomcat-7.0.42"
						+ File.separator + "webapps" + File.separator + "image" + File.separator + shop_unique
						+ File.separator;
				File fi = new File(goods_picturepath);
				if (!fi.exists()) {
					fi.mkdirs();
				}
				String shop_image_name=new Date().getTime()+"";
				PicSaveUtil.handleFileUpId(file, request, goods_picturepath, shop_image_name);
				goods_picturepath= "image" + File.separator + shop_unique
						+ File.separator+shop_image_name+".jpg";
				map.put("shop_image_path", goods_picturepath);
				
			}
		} catch (UnsupportedEncodingException e) {
			ns.setStatus(1);
			ns.setMsg("提交信息有误,请检查后重新提交");
			e.printStackTrace();
			return ns;
		}
		
		int k=shopDao.updateShopDetail(map);
		if(k<1){
			ns.setStatus(1);
			ns.setMsg("提交失败,请稍后重试");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("提交成功");
		return ns;
	}
	
	/**
	 * 删除商铺管理员信息
	 */
	public NoteResult deleteManager(String manager_account) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_account", manager_account);
		int k=shopDao.deleteManager(map);
		if(k==1){
			ns.setStatus(0);
			ns.setMsg("删除成功");
			return ns;
		}
		ns.setStatus(1);
		ns.setMsg("删除失败");
		return ns;
	}
	/**
	 * 查询所有新店铺申请
	 */
	public NoteResult findShopApplys(String manager_unique, String manager_token) {
		NoteResult ns=new NoteResult();
		if(manager_unique==null||manager_token==null){
			ns.setStatus(2);
			ns.setMsg("您的登录已超时，请重新登录");
			return ns;
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		List<Map<String,Object>> manlist=manDao.findManagers(map);
		if(manlist.size()<1){
			ns.setStatus(2);
			ns.setMsg("您的登录已超时，请重新登录!");
			return ns;
		}
		
		if(Integer.parseInt(manlist.get(0).get("power_examShop").toString())!=1){
			ns.setStatus(1);
			ns.setMsg("您的权限不够，不能审核店铺");
			return ns;
		}
		map.put("examinestatus", 2);
		List<Map<String,Object>> shoplist=shopDao.findShopApplys(map);
		if(shoplist.size()<1){
			ns.setStatus(1);
			ns.setMsg("没有新的店铺申请");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(shoplist);
		return ns;
	}
	/**
	 * 根据店铺编号，查询店铺及管理员信息
	 */
	public NoteResult findShopDetail(String manager_unique, String manager_token, String shop_unique) {
		NoteResult ns=new NoteResult();
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("manager_unique", manager_unique.trim());
		map.put("manager_token", manager_token.trim());
		List<Map<String,Object>> manlist=manDao.findManagers(map);
		if(manlist.size()<1){
			ns.setStatus(2);
			ns.setMsg("您的登录已超时，请重新登录！");
			return ns;
		}
		if(Integer.parseInt(manlist.get(0).get("power_examShop").toString())!=1){
			ns.setStatus(1);
			ns.setMsg("您的权限不够，不能审核店铺");
			return ns;
		}
		map.clear();
		map.put("shop_unique", shop_unique);
		List<Map<String,Object>> shopList=shopDao.findShopDetails(map);
		if(shopList.size()<1){
			ns.setStatus(1);
			ns.setMsg("没有相关店铺信息！");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(shopList.get(0));
		return ns;
	}
	/**
	 * 更新店铺的审核状态
	 */
	public NoteResult updateExamineStatus(String manager_unique, String manager_token, String shop_unique,
			Integer examinestatus, String examinestatus_reason) {
		NoteResult ns=new NoteResult();
		if(manager_unique==null||manager_token==null){
			ns.setStatus(2);
			ns.setMsg("登录已超时，请重新登录!");
			return ns;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		List<Map<String,Object>> manlist=manDao.findManagers(map);
		if(manlist.size()<1){
			ns.setStatus(2);
			ns.setMsg("您的登录已超时，请重新登录！");
			return ns;
		}
		if(Integer.parseInt(manlist.get(0).get("power_examShop").toString())!=1){
			ns.setStatus(1);
			ns.setMsg("您的权限不够，不能审核店铺");
			return ns;
		}
		map.clear();
		map.put("shop_unique", shop_unique);
		map.put("examinestatus", examinestatus);
		map.put("examinestatus_reason", examinestatus_reason);
		int k=shopDao.updateShopDetail(map);
		if(k!=1){
			ns.setStatus(1);
			ns.setMsg("更新失败");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("更新成功!");
		return ns;
	}
	/**
	 * 查询满足条件的店铺列表
	 */
	public NoteResult findShopDetails(String manager_unique, String manager_token, Integer examinestatus, String message,Integer pageNum,
			String parea_dict_num,String carea_dict_num,String area_dict_num) {
		NoteResult ns =new NoteResult();
		if(manager_unique==null||manager_token==null){
			ns.setStatus(2);
			ns.setMsg("登录已超时，请重新登录!");
			return ns;
		}
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		List<Map<String,Object>> manlist=manDao.findManagers(map);
		if(manlist.size()<1){
			ns.setStatus(2);
			ns.setMsg("您的登录已超时，请重新登录！");
			return ns;
		}
		if(Integer.parseInt(manlist.get(0).get("power_examShop").toString())!=1){
			ns.setStatus(1);
			ns.setMsg("您的权限不够，不能审核店铺");
			return ns;
		}
		map.clear();
		if(!"".equals(message)&&message!=null){
			map.put("message", "%"+message+"%");
		}
		if(!"0".equals(area_dict_num)){
			map.put("area_dict_num", area_dict_num);
		}else{
			if(!"0".equals(carea_dict_num)){
				map.put("carea_dict_num",carea_dict_num);
			}else{
				if(!"0".equals(parea_dict_num)){
					map.put("parea_dict_num", parea_dict_num);
				}
			}
		}
		if(examinestatus!=0){
			map.put("examinestatus", examinestatus);
		}
		List<Map<String,Object>> shopList=null;
		Integer pageSize=Load.apage_size;
		map.put("pageSize", pageSize);
		if(pageNum==null){
			shopList=shopDao.findShopDetails(map);
			map.put("startNum",0);
			int k=shopList.size()%pageSize;
			int num=shopList.size()/pageSize;
			if(k==0){
				ns.setData1(num);
			}else{
				ns.setData1(num+1);
			}
			shopList.clear();
			shopList=shopDao.findShopDetails(map);
		}else{
			map.put("startNum", (pageNum-1)*pageSize);
			shopList=shopDao.findShopDetails(map);
		}
		ns.setStatus(0);
		ns.setMsg("查询成功");
		ns.setData(shopList);
		return ns;
	}
	/**
	 * 将店铺设置为审核不通过状态
	 */
	public NoteResult notPassExamine(String manager_unique, String manager_token, 
			String shop_unique, String examinestatus_reason) {
		NoteResult ns =new NoteResult();
		if(manager_unique==null||manager_token==null){
			ns.setStatus(2);
			ns.setMsg("登录已超时，请重新登录!");
			return ns;
		}
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("manager_unique", manager_unique);
		map.put("manager_token", manager_token);
		List<Map<String,Object>> manlist=manDao.findManagers(map);
		if(manlist.size()<1){
			ns.setStatus(2);
			ns.setMsg("您的登录已超时，请重新登录！");
			return ns;
		}
		if(Integer.parseInt(manlist.get(0).get("power_examShop").toString())!=1){
			ns.setStatus(1);
			ns.setMsg("您的权限不够，不能审核店铺");
			return ns;
		}
		map.clear();
		map.put("shop_unique", shop_unique);
		map.put("examinestatus_reason", examinestatus_reason);
		map.put("examinestatus", 3);
		int k=shopDao.updateShopDetail(map);
		if(k!=1){
			ns.setStatus(1);
			ns.setMsg("更新失败");
			return ns;
		}
		ns.setStatus(0);
		ns.setMsg("更新成功");
		return ns;
	}
}
