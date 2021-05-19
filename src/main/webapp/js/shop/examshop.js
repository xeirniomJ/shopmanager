var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
var examinestatus=getCookie("examinestatus");
$(function(){
	findSups();
});

//通过获取存储的状态，查看是否需要直接查询

function findSups(){
	if(manager_unique==null||manager_token==null){
		return ;
	}
	$.ajax({
		url:"findShopApplys.do",
		data:{"manager_unique":manager_unique,"manager_token":manager_token},
		type:"post",
		dataType:"json",
		success:function(result){
			
			if(result.status==0){
				var shops=result.data;
				for(var i=0;i<shops.length;i++){
					var tr="<tr><td></td><td>";
					if(shops[i].manager_name!=null){
						 tr+=shops[i].manager_name+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(shops[i].manager_phone!=null){
						tr+=shops[i].manager_phone+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(shops[i].shop_name!=null){
						tr+=shops[i].shop_name+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(shops[i].shop_address_detail!=null){
						tr+=shops[i].shop_address_detail+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(shops[i].shop_phone!=null){
						tr+=shops[i].shop_phone+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					tr+="<input type='button' value='查看详情' class='change' onclick='toShopDetail(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("shop_unique",shops[i].shop_unique);
					$("#shopApplys").append($tr);
				}
				orderApplys();
			}
			if(result.status==1){
				alert(result.msg);
			}
			if(result.status==2){
				window.parent.location.href="../../login.html";
			}
		},
		error:function(){
			alert("查询申请店铺失败，请稍后重试！");
		}
	});
}

//排序
function orderApplys(){
	var $tr=$("#shopApplys").children();
	for(var i=0;i<$tr.length;i++){
		$tr.eq(i).children().eq(0).html(i+1);
	}
}

//跳转至审核详情界面
function toShopDetail(btn){
	var shop_unique=$(btn).parents("tr").data("shop_unique");
	addCookie("shop_unique",shop_unique, 1);
	if(shop_unique==null){
		return;
	}
	window.parent.document.getElementById("rightFrame").setAttribute("src", "html/shop/shopDetail.html");
}