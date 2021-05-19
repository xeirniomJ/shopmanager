var shop_unique=getCookie("shop_unique");//店铺编号
var manager_unique=getCookie("manager_unique");//管理员编号
var manager_token=getCookie("manager_token");//管理员登录口令
var k=2;
var flag=true;
$(function(){
	//输入框获取焦点后，自动全选
	$("input").focus(selectAll);
	//点击审核通过按钮，弹出确认框，进一步确认通过审核
	$("#passExamine").click(passExamine);
	//进入界面后，自动加载店铺相关信息
	findShopDetail();
	//点击审核不通过，弹出输入框
	$("#notPassExamine").click(toNotPassReason);
	//点击确定按钮，提示审核不通过成功，并在两秒内返回申请界面
	$("#submitReason").click(submitReason);
	//点击取消按钮，取消不通过审核
	$("#cancelReason").click(cancelReason);
});
//输入框获取输入
function selectAll(){
	this.select();
}

//点击通过审核按钮，弹出确认框，进一步确认通过审核
function passExamine(){
	var flag=confirm("确定该店铺信息通过审核吗");
	var examinestatus=4;
	if(flag){
		$.ajax({
			url:"passExaminestatus.do",
			type:"post",
			data:{"shop_unique":shop_unique,"manager_unique":manager_unique,"manager_token":manager_token,"examinestatus":examinestatus},
			dataType:"json",
			success:function(result){
				if(result.status==1){
					alert(result.msg);
				}
				if(result.status==2){
					alert(result.msg);
					window.parent.location.href="../../login.html";
				}
				if(result.status==0){
					$(".function").css("display","none");
					$(".passExamine").show();
					flag=true;
					toExamShop();
				}
			},
			error:function(){
				alert("提交审核信息失败！");
			}
		})
	}
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
			}
		}
	});
}
//等待3S后，页面返回申请界面
function toExamShop(){
	if(k>=1){
		if(flag){
			$("#toExamineStatus").html("审核通过"+k+"秒后返回申请界面！");
		}else{
			$("#toExamineStatus").html("审核未通过"+k+"秒后返回申请界面！");
		}
		k--;
		setTimeout(toExamShop, 1000);
	}else{
		window.parent.document.getElementById("rightFrame").setAttribute("src","html/shop/examshop.html");
	}
}
//弹出审核不通过对话框，输入不通过原因
function  toNotPassReason(){
	$(".notPassReason").show();
}
//点击确定按钮，提示审核不通过，并在两秒内返回申请界面
function submitReason(){
	$(".notPassReason").css("display","none");
	var examinestatus_reason=$("#examinestatus_reason").val();
	if(examinestatus_reason==null||""==examinestatus_reason||examinestatus_reason.length<10){
		alert("请输入不少于十个字的审核不通过原因！");
		return;
	}
	$.ajax({
		url:"submitReason.do",
		type:"post",
		data:{"manager_token":manager_token,"manager_unique":manager_unique,"shop_unique":shop_unique,"examinestatus_reason":examinestatus_reason},
		dataType:"json",
		success:function(result){
			var status=result.status;
			if(status==2){
				alert(result.msg);
				window.parent.location.href="../../login.html";
			}
			if(status==1){
				alert(result.msg);
			}
			if(status==0){
				$(".function").css("display","none");
				$(".notPassReason").css("display","none");
				$(".passExamine").show();
				flag=false;
				toExamShop();
			}
		},
		error:function(){
			alert("提交失败！请稍后重试！");
		}
	});
}
function cancelReason(){
	$(".notPassReason").css("display","none");
}
