var manager_unique=getCookie("manager_unique");
$(function(){
	//
	$("#shop_name").focus();
	//点击提交按钮，实现新店铺注册功能
	$("#newShop").click(newShop);
	//输入框失去焦点触发非空检测
	$("input").blur(testNull);
	//检查两次输入的密码是否相同
	$("#rePwd").blur(testSame);
	//点击取消按钮，返回主界面
	$("#cancel").click(toIndex);
});

//点击提交按钮，实现先店铺注册
function newShop(){
	var shop_name=$("#shop_name").val();
	var account=$("#account").val();
	var pwd=$("#pwd").val();
	var rePwd=$("#rePwd").val();
	var shop_phone=$("#shop_phone").val();
	var shop_address_detail=$("#shop_address_detail").val();
	for(var i=0;i<$("input").length-1;i++){
		var th=$("input").eq(i);
		if(th.val()==null||th.val()==""){
			th.parent().next("td").addClass("warning");
			return;
		}
	}
	if(pwd!=rePwd){
		$("input").eq(4).parent().next("td").find("span").html("两次输入的密码不相同");
		$("input").eq(4).parent().next("td").addClass("warning");
	}
	$.ajax({
		url:"shop/newShop.do",
		type:"post",
		data:{"manager_unique":manager_unique,"shop_name":shop_name,"account":account,"pwd":pwd,"shop_phone":shop_phone,"shop_address_detail":shop_address_detail},
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				window.location.href="index.html";
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("添加新商铺失败，请稍后重试!");
		}
	});
}

//当光标离开输入框时，检查是否非空
function testNull(){
	var $inputs=$("input");
	for(var i=0;i<$inputs.length;i++){
		var th=$("input").eq(i);
		if(th.val()==null||th.val()==""){
			th.parent().next("td").addClass("warning");
		}else{
			th.parent().next("td").removeClass("warning");
		}
	}
	var pwd=$("#pwd").val();
	var rePwd=$("#rePwd").val();
	if(rePwd!=null&&rePwd!=""){
		if(rePwd!=pwd){
			$("#rePwd").parent().next("td").addClass("warning");
			$("#rePwd").parent().next("td").find("span").html("两次输入的密码不同");
		}else{
			$("#rePwd").parent().next("td").removeClass("warning");
			$("#rePwd").parent().next("td").find("span").html("必填");
		}
	}
}

//检查两次输入的密码是否相同
function testSame(){
	var pwd=$("#pwd").val();
	var rePwd=$("#rePwd").val();
	if(rePwd!=pwd){
		$("#rePwd").parent().next("td").addClass("warning");
		$("#rePwd").parent().next("td").find("span").html("两次输入的密码不同");
	}else{
		$("#rePwd").parent().next("td").removeClass("warning");
		$("#rePwd").parent().next("td").find("span").html("必填");
	}
}
//点击取消按钮，返回到主界面
function toIndex(){
	window.location.href="index.html";
}