var manager_unique=getCookie("manager_unique");
$(function(){
	//查询管理员所管理的店铺列表，并展示其基本信息
	findAllShops();
});

//查询管理员所管理的店铺的基本信息
function findAllShops(){
	$.ajax({
		url:"manager/findShopManagers.do",
		type:"post",
		data:{"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var shops=result.data;
				for(var i=0;i<shops.length;i++){
					var tr="<tr><td>"+(i+1)+"</td><td>";
					if(shops[i].shop_name!=null){
						tr+=shops[i].shop_name+"</td><td>";
					}else{
						tr+="</ted><td>"
					}
					if(shops[i].manager_account!=null){
						tr+=shops[i].manager_account+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(shops[i].shop_phone!=null){
						tr+=shops[i].shop_phone+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(shops[i].shop_address_detail==null){
						tr+="</td><td>";
					}else{
						tr+=shops[i].shop_address_detail+"</td><td>";
					}
					if(shops[i].shop_remark!=null){
						tr+=shops[i].shop_remark+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					tr+="<input type='button' value='更新商铺信息' onclick='toUpdateShop(this)'><input type='button' value='删除前端管理' onclick='deleteManager(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("shop_unique",shops[i].shop_unique);
					$("#shopList").append($tr);
				}
			}
			if(result.status==1){
			}
		},
		error:function(){
			alert("查询店铺异常，请检查网络");
		}
	});
}
//点击更新跳转到更新界面
function toUpdateShop(btn){
	var shop_unique=$(btn).parent().parent().data("shop_unique");//var shop_unique =$(btn).parents("tr").data("shop_unique");
	addCookie("shopUnique",shop_unique, 2);
	window.parent.document.getElementById("rightFrame").setAttribute("src","shopDetail.html");
}
//删除前端管理员
function deleteManager(btn){
	var flag=confirm("确实要删除该管理员吗？该操作不可逆,请慎重操作");
	if(!flag){
		return;
	}
	var manager_account=$(btn).parents("tr").children().eq(2).html();
	$.ajax({
		url:"shops/deleteManager.do",
		type:"post",
		data:{"manager_account":manager_account},
		dataType:"json",
		success:function(result){
			console.log(result.status);
			if(result.status==0){
				window.parent.document.getElementById("toPurchase").setAttribute("src","shopManager.html");
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除管理员失败，请检查网络");
		}
	});
}