//package org.haier.shop.controller.test;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.haier.shop.service.GoodsService;
//import org.haier.shop.util.NoteResult;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//@RequestMapping("/goods")
//public class New_goodssController extends PicController{
//	@Resource
//	private GoodsService goodsService;
//	
//	@RequestMapping("/newGoods1.do")
//	@ResponseBody
//	public NoteResult execute(HttpServletRequest request,MultipartFile file,String goods_name,String goods_brand,String goods_barcode,String goods_alias,String goods_sale_price,String goods_in_price
//			,String goods_life,String goods_points,String goods_address,String goods_contain,String goods_spec,String goods_kind_parunique,String goods_kind_unique,String shop_unique,String manager_unique,Integer goods_count){
//		return goodsService.newGoods1(request, file, goods_name, goods_brand, goods_barcode, goods_alias, 
//				goods_sale_price, goods_in_price, goods_life, goods_points, goods_address, goods_contain, goods_spec, 
//				goods_kind_parunique, goods_kind_unique, shop_unique,manager_unique,goods_count);
//	}
//}
