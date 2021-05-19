var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
var submanager_unique=getCookie("submanager_unique");
$(function(){
	$("#submit").click(updateManagerDetail);
	queryManagerDetail();
	//输入框获取光标后，输入框内容全选
	$("input").focus(function(){
		this.select();
	});
	$("#goback").click(goback);
});

function updateManagerDetail(){
	$("#managerDetail").ajaxSubmit(option);
}
var option={
		url:"updateManagerDetail.do",
		type:"post",
		data:$("#managerDetail").serialize(),
		async:false,
		data:{"submanager_unique":submanager_unique.trim(),"manager_token":manager_token,"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				window.parent.document.getElementById("rightFrame").setAttribute("src","html/manager/managerpower.html");
			}
		},
		error:function(){
			alert("提交失败！");
		}
}
//根据管理员编号查询其详细信息
function queryManagerDetail(){
	$.ajax({
		url:"queryManagerDetail.do",
		data:{"manager_unique":manager_unique,"manager_token":manager_token,"submanager_unique":submanager_unique},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var manager=result.data;
				$("#manager_account").val(manager.manager_account);
				$("#manager_name").val(manager.manager_name);
				$("#manager_phone").val(manager.manager_phone);
				var $options=$("#manager_level").children();
				var level=manager.manager_level;
				for(var i=0;i<$options.length;i++){
					if(level==$options.eq(i).val()){
						$options[i].selected=true;
					}
				}
				var $power_createManagers=$("input[name='power_createManager");
				for(var i=0;i<$power_createManagers.length;i++){
					if(manager.power_createManager==$power_createManagers.eq(i).val()){
						$power_createManagers[i].checked="checked";
					}
				}
				var $power_modifyFunction=$("input[name='power_modifyFunction']");
				for(var i=0;i<$power_modifyFunction.length;i++){
					if(manager.power_modifyFunction==$power_modifyFunction.eq(i).val()){
						$power_modifyFunction[i].checked="checked";
					}
				}
				var $power_examShop=$("input[name='power_examShop']");
				for(var i=0;i<$power_examShop.length;i++){
					if($power_examShop.eq(i).val()==manager.power_examShop){
						$power_examShop[i].checked=true;
					}
				}
				var $power_examSup=$("input[name='power_examSup']");
				for(var i=0;i<$power_examSup.length;i++){
					if($power_examSup.eq(i).val()==manager.power_examSup){
						$power_examSup[i].checked=true;
					}
				}
				var $power_forbidCus=$("input[name='power_forbidCus']");
				for(var i=0;i<$power_forbidCus.length;i++){
					if($power_forbidCus.eq(i).val()==manager.power_forbidCus){
						$power_forbidCus[i].checked=true;
					}
				}
			}else if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("查询管理员详情失败！");
		}
	});
}

//点击返回按，返回上一层
function goback(){
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/manager/managerpower.html");
}