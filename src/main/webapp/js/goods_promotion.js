//单击修改商品促销状态按钮，进入修改商品促销页面
function goods_promotion_modify(btn){
	var goods_barcode=$(btn).parents("tr").data("goods_barcode");
	$.ajax({
		url:"goods/findGood.do",
		type:"post",
		data:{"goods_barcode":goods_barcode},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goods=result.data;
				$("#goods_id").html(goods.goods_id);
				$("#goods_name").html(goods.goods_name);
				$("#goods_price").val(goods.goods_sale_price.toFixed(2));
				$("#goods_discount").val(goods.goods_discount.toFixed(2));
				$("#goods_dis_price").val((parseFloat(goods.goods_sale_price)*parseFloat(goods.goods_discount)).toFixed(2));
				$("#goods_in_price").val(goods.goods_in_price.toFixed(2));
				$("#profit").html((parseFloat($("#goods_dis_price").val())-goods.goods_in_price).toFixed(2));
				$("#goods_id").data("goods_barcode",goods_barcode);
			}
		},
		error:function(){
			alert("加载商品属性异常");
		}
	});
	$(".showarea").css("display","none");
	$("#goods_promotion_modifypage").show();
}
//单击保存修改按钮，将修改后的促销信息保存
function goods_discount_modify_save(){
	var goods_barcode=$("#goods_id").data("goods_barcode");
	var goods_sale_price=$("#goods_price").val().trim();
	var goods_discount=$("#goods_discount").val().trim();
	var goods_promotion=$("#newgoods_promotion option:selected").val();
	if(goods_promotion==0){
		goods_promotion="";
	}else{
		console.log(goods_promotion);
	}
	$.ajax({
		url:"goods/updateGoods_promotion.do",
		type:"post",
		data:{"goods_barcode":goods_barcode,"goods_sale_price":goods_sale_price,"goods_discount":goods_discount,"goods_promotion":goods_promotion,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$(".showarea").css("display","none");
				$("#goods_promotion").show();
				findGoods_promotion();
			}
			if(result.status==1){
				alert(result.msg);
				$(".showarea").css("display","none");
				$("#goods_promotion").show();
				findGoods_promotion();
			}
		},
		error:function(){
			alert("修改产品促销状态异常");
		}
	});
}
//单击取消修改按钮，返回之前的页面
function goods_discount_modify_cancel(){
	$(".showarea").css("display","none");
	$("#goods_promotion").show();
}
//加载页面后，自动填充商品大类
function add_goods_kind_group(){
	$.ajax({
		url:"goods_kind/add_goods_kind_group.do",
		type:"post",
		data:{"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var groups=result.data;
				var a="<option value=0>请选择商品大类</option>";
				var b="<option value=0>请选择商品小类</option>";
				$("#ingoods_kind_name").append(b);
				$("#ingoods_kind_group").append(a);
				for(var i=0;i<groups.length;i++){
					var option="<option value="+groups[i].goods_kind_unique+">"+groups[i].goods_kind_name+"</option>";
					$("#ingoods_kind_group").append(option);
				}
			}
		},
		error:function(){
			alert("加载商品大类型失败，请检查网络");
		}
	});
}
//加载页面时，自动添加商品小类
//function add_goods_kind_name(){
//	$.ajax({
//		url:"goods_kind/add_goods_kind_name.do",
//		type:"get",
//		dataType:"json",
//		success:function(result){
//			if(result.status==0){
//				var option="<option value=0>请选择商品小类</option>";
//				$("#ingoods_kind_name").append(option);
//				var groups=result.data;
//				for(var i=0;i<groups.length;i++){
//					var option="<option value="+(i+1)+">"+groups[i]+"</option>";
//					$("#ingoods_kind_name").append(option);
//				}
//			}
//		},
//		error:function(){
//			alert("自动加载商品小类型失败，请检查网络");
//		}
//	});
//}
//修改大类后，自动修改小类填充
function changegroup(){
	var goods_kind_parunique=$("#ingoods_kind_group option:selected").val();
	$("#ingoods_kind_name").empty();
	var option="<option value=0>请选择商品小类</option>";
	$("#ingoods_kind_name").append(option);
	if(goods_kind_parunique=="0"){
		return;
	}
	$.ajax({
		url:"goods_kind/inadd_goods_kind_name.do",
		type:"post",
		data:{"goods_kind_parunique":goods_kind_parunique,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#ingoods_kind_name").empty() ;
				var option="<option value=0>请选择商品小类</option>";
				$("#ingoods_kind_name").append(option);
				var groups=result.data;
				for(var i=0;i<groups.length;i++){
					var option="<option value="+groups[i].goods_kind_unique+">"+groups[i].k+"</option>";
					$("#ingoods_kind_name").append(option);
				}
			}
		},
		error:function(){
			alert("加载商品大类型失败，请检查网络");
		}
	});
}
//点击商品查询按钮，查询满足条件的商品
function findGoods_promotion(){
	var goodsmessage=$("#goodsmessage").val().trim();
	var goods_kind_parunique=$("#ingoods_kind_group option:selected").val();
	var goods_kind_unique=$("#ingoods_kind_name option:selected").val();
	var goods_promotion=$("#ingoods_promotion option:selected").val();
	$.ajax({
		url:"goods/findGoods_promotion.do",
		type:"post",
		data:{"goods_kind_parunique":goods_kind_parunique,"goodsmessage":goodsmessage,"manager_unique":getCookie("manager_unique"),
			"goods_kind_unique":goods_kind_unique,"goods_promotion":goods_promotion,"shop_unique":shop_unique,},
		dataType:"json",
		success:function(result){
			$("#goods_list").empty();
			if(result.status==0){
				var goods=result.data;
				for(var i=0;i<goods.length;i++){
					var tr="<tr><td class='public'></td><td>"+goods[i].goods_id+"</td><td>";
					tr+=goods[i].goods_name+"</td><td>";
					tr+=goods[i].shop_name+"</td><td>";
					if(goods[i].goods_promotion==1){
						tr+="不促销"+"</td><td>";
					}else 	if(goods[i].goods_promotion==2){
						tr+="促销</td><td>";
					}else{
						tr+="异常</td><td>";
					}
					tr+=goods[i].goods_sale_price+"</td><td>";
					tr+=goods[i].goods_discount+"</td><td>";
					tr+=parseFloat(goods[i].goods_sale_price)*parseFloat(goods[i].goods_discount)+"</td><td>";
					tr+="<input type='button' value='修改促销状态' onclick='goods_promotion_modify(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("goods_barcode",goods[i].goods_barcode);
					$("#goods_list").append($tr);
				}
			}
		},error:function(){
			alert("查询促销商品失败，请稍后重试");
		}
	});
}
//计算折扣后总价
function dis_price(){
	var goods_sale_price=$("#goods_price").val().trim();
	var goods_discount=$("#goods_discount").val().trim();
	$("#goods_dis_price").val((parseFloat(goods_sale_price)*parseFloat(goods_discount)).toFixed(2));
	//商品进价
	var goods_in_price=parseFloat($("#goods_in_price").val().trim());
	//商品折扣价
	var goods_dis_price=parseFloat($("#goods_dis_price").val());
	$("#profit").html(goods_dis_price-goods_in_price);
}
//输入折扣价，自动计算折扣
function discount(){
	var goods_sale_price=$("#goods_price").val().trim();
	var goods_dis_price=$("#goods_dis_price").val().trim();
	$("#goods_discount").val((parseFloat(goods_dis_price)/parseFloat(goods_sale_price)).toFixed(4));
}
$("body").keydown(function(event){
	var code=event.keyCode;
	if(code==13){
		$("#findGoods").click();
	}
})