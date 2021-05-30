var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
if(null==manager_unique||null==manager_token){
	window.parent.location.href="../../login.html";
}else{
	$(function(){
		$("#message").focus();
		//页面加载完后，自动加载省份！
		loadProvince();
		//省份修改后，自动填充市
		$("#provinces").change(loadCities);
		//城市修改后，自动填充县区
		$("#cities").change(loadContries);
		//点击查询按钮，查询满足条件的供货商列表
		$("#search").click(findSups);
		//点击页面实现搜索功能
		$("body").keydown(function(event){
			var code=event.keyCode;
			if(code==13){
				$("#search").click();
			}
		});
	});
}

//点击查询按钮，是先查询功能
function findSups(){
	var supMessage=$("#message").val().trim();
	var parea_dict_num=$("#provinces option:selected").val();
	var carea_dict_num=$("#cities option:selected").val();
	var area_dict_num=$("#contries option:selected").val();
	var examinestatus=$("#examinestatus").val();
	$.ajax({
		url:"findSuppliers.do",
		type:"post",
		data:{"manager_unique":manager_unique,"manager_token":manager_token,"supMessage":supMessage,
			"parea_dict_num":parea_dict_num,"carea_dict_num":carea_dict_num,"area_dict_num":area_dict_num,"examinestatus":examinestatus},
		dataType:"json",
		success:function(result){
			var status=result.status;
			var msg=result.msg;
			if(status==2){
				alert(msg);
				window.parent.location.href="../../login.html";
			}
			if(status==1){
				alert(msg);
			}
			if(status==0){
				$("#supList").empty();
				var sups=result.data;
				var pageNum=result.data1;
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
					}
					if(sups[i].supplier_address!=null){
						tr+=sups[i].supplier_address+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					var status=sups[i].examinestatus;
					if(status==1){
						tr+="尚未提交申请，</td><td>";
					}else if(status==2){
						tr+="已提交申请</td><td>";
					}else if(status==3){
						tr+="审核未通过</td><td>";
					}else if(status==4){
						tr+="审核已通过</td><td>";
					}
					if(sups[i].reamrk!=null){
						tr+=sups[i].remark+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					tr+="<input type='button' value='查看详情' onclick='toSupDetail(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("supplier_unique",sups[i].supplier_unique);
					$("#supList").append($tr);
				}//for循环结束
			}//status状态判断结束
		}
	});
}
//单击查看详情，进入查看详情界面
function toSupDetail(btn){
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	addCookie("supplier_unique", supplier_unique, 10);
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/supplier/supplierDetail.html");
}