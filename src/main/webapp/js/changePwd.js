var shop_unique=getCookie("shop_unique");
var manager_unique=getCookie("manager_unique");
$(function(){
	//密码栏获取焦点
	$("#newPwd").focus();
	//回车键实现提交功能
	$("body").keydown(function(event){
		var code=event.keyCode;
		if(code==13){
			$("#submit").click();
		}
	});
	//单击提交按钮，实现修改提交功能
	$("#submit").click(submitChange);
	//非空测试
	$("#oldPwd").blur(testNull);
	$("#newPwd").blur(testNull);
	$("#newPwdRe").blur(testNull);
	//取消修改密码
	$("#cancel").click(index);
});

//单击提交按钮，实现提交功能
function submitChange(){
	var oldPwd=$("#oldPwd").val();
	var newPwd=$("#newPwd").val();
	var newPwdRe=$("#newPwdRe").val();
	if(oldPwd==null||oldPwd==""){
		$("#warningOldPwd").html("密码不能为空");
		$("#warningOldPwd").parent().addClass("warningtd");
		return;
	}
	if(newPwd==null||newPwd==""){
		console.log("新密码为空");
		$("#warningPwd").html("新密码不能为空！");
		$("#warningPwd").parent().addClass("warningtd");
		return ;
	}
	if(newPwdRe==null||newPwdRe==""){
		$("#warningPwdRe").html("重复密码不能为空");
		$("#warningPwdRe").addClass("warningtd");
		return;
	}
	if(newPwd!=newPwdRe){
		$("#warningPwdRe").html("两次密码输入不相同");
		$("#warningPwdRe").parent().addClass("warningtd");
		return;
	}
	$.ajax({
		url:"manager/updatePwd.do",
		type:"post",
		data:{"oldPwd":oldPwd,"manager_unique":manager_unique,"manager_pwd":newPwdRe},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
			}
			if(result.status==1){
				alert(result.msg);
			}
			window.parent.document.getElementById("logo").click();
		},
		error:function(){
			alert("更新密码失败");
		}
	});
}
//测试登录内容是否为空
function testNull(){
	if($(this).val()!=null&&$(this).val()!=""){
		$(this).parent().next("td").removeClass("warningtd");
		$(this).parent().next("td").children().html("必填");
	};
}
//单击取消按钮，返回登录状态
function index(){
	window.parent.document.getElementById("logo").click();
}