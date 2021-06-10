var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
var shop_unique=getCookie("shop_unique");
$(function(){
	//点击获取当前元素的类名
	$(".check").click(getClass);
});
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
	
}

//