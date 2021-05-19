var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
$(function(){
	loadManager();
});

function toManagerDetail(btn){
	var submanager_unique=$(btn).parents("tr").data("submanager_unique");
	addCookie("submanager_unique",submanager_unique, 1);
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/manager/managerDetail.html");
}
//加载所有管理员信息
function loadManager(){
	$.ajax({
		url:"loadManager.do",
		data:{"manager_unique":manager_unique,"manager_token":manager_token},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var managers=result.data;
				if(managers==null){
					console.log("没有相关数据！");
					return;
				}
				for(var i=0;i<managers.length;i++){
					if(manager_unique==managers[i].manager_unique){
						continue;
					}
					var tr="<tr><td></td><td>"+managers[i].manager_name+"</td><td>";
					tr+=managers[i].manager_account+"</td><td>";
					if(managers[i].manager_level==2){
						tr+="超级管理员</td><td>";
					}else if(managers[i].manager_level==3){
						tr+="普通管理员</td><td>";
					}
					tr+=managers[i].manager_phone+"</td><td>";
					tr+="<input type='button' value='设置详情' onclick='toManagerDetail(this)' class='cbutton'></td></tr>";
					$tr=$(tr);//将数据转化为Jquery模式
					$tr.data("submanager_unique",managers[i].manager_unique);//绑定数据
					$("#managers").append($tr);
				}//for循环结束
				managersOrder();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("查询管理员信息失败！请检查网络");
		}
	});
}

//查询排序
function managersOrder(){
	var $trs=$("#managers").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}