$(function(){
		//输入用户名框自动获取焦点
		$("#manager_account").focus();
		//刷新到登录页面时，清空cookie信息;
		SetCookie("shop_unique",null);
		//实现登录
		$("#login").click(login);
		//当页面输入内容时，取消输入为空的提醒
		$("#manager_account").blur(checkaccount);
		var shop_unique=getCookie("shop_unique");
		//点击注册按钮，显示注册界面
		$("#toregister").click(toregister);
		$("#ret").click(ret);
		$("#register").click(register)
		//页面回车
		$("body").keydown(function(event){
			var code=event.keyCode;
			if(code==13){
				$("#login").click();
			}
		});
		//失去光标，非空测试
		$("#manager_account").blur(testNull);
		$("#manager_pwd").blur(testNull);
		//点击返回按钮，返回登录界面
		$("#return").click(returnBack);
	});
function login(){
	var manager_account=$("#manager_account").val().trim();
	var manager_pwd=$("#manager_pwd").val().trim();
	var flag=true;
	if(manager_account==null||manager_account==""){
		flag=false;
		return;
	}
	if(manager_pwd==null||manager_pwd==""){
		flag=false;
		return;
	}
	if(flag){
		$.ajax({
			url:"login.do",
			type:"post",
			data:{"manager_account":manager_account,"manager_pwd":manager_pwd},
			dataType:"json",
			success:function(result){
				if(result.status==0){//登录成功
					var manager=result.data;
					addCookie("manager_unique",result.data.manager_unique,10);
					addCookie("manager_name", result.data.manager_name, 10);
					addCookie("manager_token",result.data1,10);
					if(manager.manager_level==1){
						window.location.href="index.html";
					}else{
						addCookie("manager_token",result.data1,10);
						var a=Math.random();
						var href="manager.html?level="+manager.manager_level+",a="+encodeURI(a.toFixed(2));
						window.location.href=href;
					}
				}
				if(result.status==1){
					$("#warning").val(result.msg);
					document.getElementById("warning").style.visibility="visible";
				}
			},
			error:function(){
				alert("系统异常，请稍后重试");
			}
		});
	}
}
//检查登录
function checkaccount(){
	var manager_account=$("#manager_account").val().trim();
	var manager_pwd=$("#manager_pwd").val().trim();
	if(manager_account!=null&&manager_account!=""&&manager_pwd!=null&&manager_pwd!=""){
		$("#warning").html("");
	}
}
//点击注册按钮，显示注册界面
function toregister(){
	$(".login").css("display","none");
	$(".register").show();
	$("#account").focus();
}
//注册界面，点击取消，返回登录界面
function ret(){
	$(".app-location").show();
	$(".public").css("display","none");
	$("#account").val("");
	$("#pwd").val("");
	$("#spwd").val("");
}
//点击注册按钮，实现新用户注册
function register(){
	var manager_account=$("#account").val().trim();
	var manager_pwd=$("#pwd").val().trim();
	var spwd=$("#spwd").val().trim();
	var manager_name=$("#manager_name").val().trim();
	var manager_phone=$("#manager_phone").val().trim();
	var flag=true;
	if(manager_pwd!=spwd){
		$("#warning1").val("两次输入密码不同");
		document.getElementById("warning1").style.visibility="visible";
		return;
	}else	if(manager_account==""){
		$("#warning1").val("账号不能为空");
		document.getElementById("warning1").style.visibility="visible";
		return;
	}else if(manager_pwd==""){
		$("#warning1").val("密码不能为空");
		document.getElementById("warning1").style.visibility="visible";
		return;
	}
	$.ajax({	
		url:"register.do",
		type:"post",
		data:{"manager_account":manager_account,"manager_pwd":manager_pwd,"manager_name":manager_name,"manager_phone":manager_phone},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$(".login").show();
				$(".register").css("display","none");
				document.getElementById("warning").style.visibility="hidden";
				$("#manager_account").focus();
			}
			if(result.status==1){
				$("#warning1").val(result.msg);
				$("#account").focus();
			}
		},
		error:function(){
			alert("注册失败，请稍后重试");
		}
	});
}

//非空测试
function testNull(){
	var account=$("#manager_account").val();
	var pwd=$("#manager_pwd").val();
	if(account==null||account==""){
		$("#warning").val("账号不能为空！");
		document.getElementById("warning").style.visibility="visible";
	}else if(pwd==null||pwd==""){
		$("#warning").val("密码不能为空！");
		document.getElementById("warning").style.visibility="visible";
	}else{
		document.getElementById("warning").style.visibility="hidden";
	}
}

function returnBack(){
	$(".login").show();
	$(".register").css("display","none");
	document.getElementById("warning").style.visibility="hidden";
	$("#manager_account").focus();
}