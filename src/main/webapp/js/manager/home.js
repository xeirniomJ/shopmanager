var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
$(function(){
	if(manager_unique!=null){
		findApply();
		$("#shopApply").click(showShopApply);
		$("#supApply").click(showSupApply);
	}else{
		window.parent.location.href="../../login.html";
	}
});

//查询新的店铺申请
function findApply(){
	$.ajax({
		url:"findApply.do",
		type:"post",
		data:{"manager_unique":manager_unique,"manager_token":manager_token},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var nums=result.msg.split("；");
				$("#shopApply").text(nums[0]);
				$("#supApply").text(nums[1]);
				addCookie("examinestatus", 2, 1);
				window.parent.addCookie("examinestatus",2,1);
			}
			if(result.status==1){
				alert(result.msg);
				window.parent.location.href="../../login.html";
			}
		},
		error:function(){
			alert("查询管理员列表失败！");
		}
	})
};

//跳转至新申请界面
function showShopApply(){
	addCookie("shopexastatus", 1, 1);
	window.parent.document.getElementById("shopmanager").click();
}
//跳转至供货商申请界面
function showSupApply(){
	addCookie("supexastatus",1, 1);
	window.parent.document.getElementById("supmanager").click();
}