var manager_unique=getCookie("manager_unique");
var manager_token=getCookie("manager_token");
$(function(){
	$("#message").focus();
	//页面加载完后，自动加载省份！
	loadProvince();
	//省份修改后，自动填充市
	$("#provinces").change(loadCities);
	//城市修改后，自动填充县区
	$("#cities").change(loadContries);
	//点击查询按钮，查询满足条件的店铺信息
	$("#search").click(findShops);
	$("body").keydown(function(event){
		var code=event.keyCode;
		if(code==13){
			$("#search").click();
		}
	});
});
//点击查询按钮，查询满足条件的店铺信息
function findShops(){
	var message=$("#message").val().trim();
	var parea_dict_num=$("#provinces option:selected").val().trim();
	var carea_dict_num=$("#cities option:selected").val().trim();
	var area_dict_num=$("#contries option:selected").val().trim();
	var examinestatus=$("#examinestatus").val().trim();
	addCookie("message",message, 10);
	addCookie("parea_dict_num",parea_dict_num,10);
	addCookie("carea_dict_num",carea_dict_num,10);
	addCookie("area_dict_num",area_dict_num,10);
	addCookie("examinestatus",examinestatus,10);
	$.ajax({
		url:"findShops.do",
		type:"post",
		data:{"manager_unique":manager_unique,"manager_token":manager_token,"message":message,
			"parea_dict_num":parea_dict_num,"carea_dict_num":carea_dict_num,"area_dict_num":area_dict_num,"examinestatus":examinestatus},
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
				$("#shopList").empty();
				var shops=result.data;
				var pageNums=result.data1;
				for(var i=0;i<shops.length;i++){
					var tr="<tr><td>"+(i+1)+"</td><td>";
					if(shops[i].shop_name!=null){
						tr+=shops[i].shop_name+"</td><td>";
					}else{
						tr+="</td><td>";
					}
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
					if(shops[i].shop_phone!=null){
						tr+=shops[i].shop_phone+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(shops[i].shop_address_detail!=null){
						tr+=shops[i].shop_address_detail+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					var exam=shops[i].examinestatus;
					if(exam==2){
						tr+="新提交申请</td><td>";
					}else if(exam==3){
						tr+="审核未通过</td><td>";
					}else if(exam==4){
						tr+="审核已通过</td><td>";
					}else{
						tr+="审核状态未知,请检查系统状态！</td><td>";
					}
					tr+="<input type='button' value='查看详情' onclick='showShopDetails(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("shop_unique",shops[i].shop_unique);
					$("#shopList").append($tr);
				}//循环结束
				$(".tcdPageCode").createPage({
					pageCount:pageNums ,
					current:1,
					backFn:function(p){
						findShops1(p);
					}
				});
			}//if判断结束
		},
		error:function(){
			alert("查询失败，请稍后重试！");
		}
	});
}

//按页面查询商品信息
function findShops1(pageNum){
	var message=getCookie("message");
	var parea_dict_num=getCookie("parea_dict_num");
	var carea_dict_num=getCookie("carea_dict_num");
	var area_dict_num=getCookie("area_dict_num");
	var examinestatus=getCookie("examinestatus");
	$.ajax({
		url:"findShops.do",
		type:"post",
		data:{"manager_unique":manager_unique,"manager_token":manager_token,"message":message,
			"parea_dict_num":parea_dict_num,"carea_dict_num":carea_dict_num,"area_dict_num":area_dict_num,"examinestatus":examinestatus,"pageNum":pageNum},
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
				$("#shopList").empty();
				var shops=result.data;
				var pageNums=result.data1;
				for(var i=0;i<shops.length;i++){
					var tr="<tr><td>"+(i+1)+"</td><td>";
					if(shops[i].shop_name!=null){
						tr+=shops[i].shop_name+"</td><td>";
					}else{
						tr+="</td><td>";
					}
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
					if(shops[i].shop_phone!=null){
						tr+=shops[i].shop_phone+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(shops[i].shop_address_detail!=null){
						tr+=shops[i].shop_address_detail+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					var exam=shops[i].examinestatus;
					if(exam==2){
						tr+="新提交申请</td><td>";
					}else if(exam==3){
						tr+="审核未通过</td><td>";
					}else if(exam==4){
						tr+="审核已通过</td><td>";
					}else{
						tr+="审核状态未知,请检查系统状态！</td><td>";
					}
					tr+="<input type='button' value='查看详情' onclick='showShopDetails(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("shop_unique",shops[i].shop_unique);
					$("#shopList").append($tr);
				}//循环结束
			}//if判断结束
		},
		error:function(){
			alert("查询失败，请稍后重试！");
		}
	});
}

//点击查看详情，查看店铺详情
function showShopDetails(btn){
	var shop_unique=$(btn).parents("tr").data("shop_unique");
	addCookie("shop_unique", shop_unique, 10);
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/shop/shopFunction.html");
}