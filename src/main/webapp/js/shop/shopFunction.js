var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
var shop_unique=getCookie("shop_unique");
$(function(){
	//点击功能列表，显示功能分区
	$(".check").click(getClass);
	//点击获取当前元素的类名
	$("#baseMessage").click(getClass);
	//加载页面后，加载店铺基本信息
	loadBaseMessage();
});
//加载页面后，选择查看店铺基本信息
function loadBaseMessage(){
	$("#baseMessage").click();
}
//进入界面后，自动加载商铺信息
function findShopDetail(){
	if(manager_token==null||manager_unique==null){
		window.parent.location.href="../../login.html";
	}
	$.ajax({
		url:"findShopDetail.do",
		type:"post",
		data:{"shop_unique":shop_unique,"manager_unique":manager_unique,"manager_token":manager_token},
		dataType:"json",
		success:function(result){
			if(result.status==2){
				alert(result.msg);
				window.parent.location.href="../../login.html";
			}
			if(result.status==1){
				alert(result.msg);
			}
			if(result.status==0){
				var shop=result.data;
				$("#manager_name").html(shop.manager_name);
				$("#manager_phone").html(shop.manager_phone);
				$("#shop_name").html(shop.shop_name);
				$("#shop_phone").html(shop.shop_phone);
				$("#shop_address_detail").html(shop.shop_address_detail);
				$("#shop_remark").html(shop.shop_remark);
				$("#shop_latitude").val(shop.shop_latitude);
				$("#shop_longitude").val(shop.shop_longitude);
				if(shop.examinestatus==1){
					$("#examinestatus").html("尚未提交申请！");
				}else if(shop.examinestatus==2){
					$("#examinestatus").html("已提交申请，尚未审核");
				}else if(shop.examinestatus==3){
					$("#examinestatus").html("审核未通过！");
					$(".examinestatus_reason").show();
					$("#examinestatus_reason").val(shop.examinestatus_reason);
				}else if(shop.examinestatus==4){
					$("#examinestatus").html("审核已通过！");
				}
			}
		}
	});
}
//点击获取当前元素的类
function getClass(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	var cla=$(this).attr("class").substring(0,$(this).attr("class").indexOf(" "));
	$("."+cla+"").show();
	if(cla=="baseMessage"){
		findShopDetail();
	}
	if(cla=="goodsMessage"){
		showShopGoods();
	}
}
//加载店铺商品详情
function showShopGoods(){
	$.ajax({
		url:"queryGoodsMessage.do",
		post:"post",
		data:{"manager_unique":manager_unique,"manager_token":manager_token,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==2){
				alert(result.msg);
				window.parent.location.href="../../login.html";
			}
			if(result.status==1){
				alert(result.msg);
			}
			if(result.status==0){
				var goodss=result.data;
				for(var i=0;i<goodss.length;i++){
					var tr="<tr><td>"+(i+1)+"</td><td>";
					tr+=goodss[i].goods_name+"</td><td>";
					tr+=goodss[i].goods_barcode+"</td><td>";
					tr+=goodss[i].goods_count+"</td><td>";
					tr+=goodss[i].goods_sale_price+"</td><td>";
					tr+=goodss[i].goods_sold+"</td><td>";
					tr+=goodss[i].l+"</td><td>";
					tr+=goodss[i].k+"</td><td>";
					tr+="<input type='button' value='查看详情' class='cbutton' onclick='toGoodsDetail(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("goods_barcode",goodss[i].goods_barcode);
					$("#goodsList").append($tr);
				}
			}
		},
		error:function(){
			alert("查询商品信息失败！");
		}
	});
}
//单击查看详情，跳转至商品详情界面
function toGoodsDetail(btn){
	var goods_barcode=$(btn).parents("tr").data("goods_barcode");
	window.parent.document.getElementById("rightFrame").setAttribute("src","goodsDetail.html");
}