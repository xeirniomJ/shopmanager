var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
//页面加载后执行的程序
$(function(){
	//自动加载管理员信息
	findManagerMessage();
	//修改完信息后，点击提交修改按钮，提交已修改的信息
	$("#updateManagerMessage").click(updateManagerMessage);
})
//加载页面时，根据传入的管理员信息，自动加载相关信息
function findManagerMessage(){
	if(manager_unique==null){
		return;
	}//if判断结束
	$.ajax({
		url:"findManagerMessage.do",
		data:{"manager_unique":manager_unique,"manager_token":manager_token},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var manager=result.data[0];
				$("#manager_account").val(manager.manager_account);
				$("#manager_name").val(manager.manager_name);
				$("#manager_phone").val(manager.manager_phone);
			}
		},
		error:function(){
			alert("加载用户信息失败，请稍后重试！");
		}
	});
};

//点击提交按钮后，将更新后的信息保存到数据库
function updateManagerMessage(){
	var manager_name=$("#manager_name").val().trim();
	var manager_phone=$("#manager_phone").val().trim();
	if(manager_name==""&&manager_phone==""){
		alert("未更改信息！");
		return;
	}
	$.ajax({
		url:"updateManagerMessage.do",
		type:'post',
		data:{"manager_unique":manager_unique,"manager_name":manager_name,"manager_phone":manager_phone},
		dataType:"json",
		success:function(result){
			if(result.status){
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("保存失败，请稍后重试！");
		}
	});
}

