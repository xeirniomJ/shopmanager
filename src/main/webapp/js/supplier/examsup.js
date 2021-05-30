var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
$(function(){
	//加载页面后，自动查询新提交的发供货商申请
	findSupplierApply();
});

//进入界面后，自动查询新提交的供货商申请
function findSupplierApply(){
	$.ajax({
		url:"findSupplierApplys.do",
		type:"post",
		data:{"manager_unique":manager_unique,"manager_token":manager_token},
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
				var sups=result.data;
				$("#supplierApplys").empty();
				for(var i=0;i<sups.length;i++){
					var tr="<tr><td>"+(i+1)+"</td><td>";
					if(sups[i].supplier_name!=null){
						tr+=sups[i].supplier_name+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(sups[i].supplier_phone!=null){
						tr+=sups[i].supplier_phone+"</td><td>";
					}else{
						tr+="</td><td>";
					}if(sups[i].supplier_address!=null){
						tr+=sups[i].supplier_address+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(sups[i].examinestatus==1){
						tr+="未提交申请</td><td>";
					}else if(sups[i].examinestatus==2){
						tr+="已提交申请</td><td>";
					}else if(sups[i].examinestatus==3){
						tr+="审核未通过！</td><td>";
					}else if(sups[i].examinestatus==4){
						tr+="审核已通过！</td><td>";
					}//if结束
					tr+="<input type='button' value='查看详情' onclick='showSupDetail(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("supplier_unique",sups[i].supplier_unique);
					$("#supplierApplys").append($tr);
				}
			}
		},
		error:function(){
			alert("查询失败！请稍后重试！");
		}
	});
}

//点击查看详情界面，跳转至供应商详情界面
function showSupDetail(btn){
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	addCookie("supplier_unique",supplier_unique,10);
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/supplier/supplierDetail.html");
}