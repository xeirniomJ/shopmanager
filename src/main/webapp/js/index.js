var manager_unique=getCookie("manager_unique");
var shop_unique=null;
shops_uniquefind();
if(manager_unique!=null){
	$(function(){
		//显示主页
		loadHome();
		//查询管理员管理的店铺列表
		//点击列表，显示子列表
		$(".div2").click(div2_click);
		//设置定时器，定时检查是否有新的订单
		setInterval(checkNewOrder,300000);
		//点击提醒，显示订单处理界面
		$("#newPurchase").parents("p").click(function(){
			$("#rightFrame").attr("src","html/exchange/purchase.html");
		});
		//单击首页，显示home页面
		$("#indexHtml").click(loadHome);
	});
}else{
	window.location.href="login.html";
}
//点击首页，加载主界面
function loadHome(){
	$("#indexHtml").addClass("checked");
	$("#rightFrame").attr("src","html/home/home.html");
}
//点击左侧菜单列表，显示隐藏子列表
function div2_click(){
	$(this).parent().children().find("img").css("display","none");
	$(".div2").removeClass("checked");
	$(this).next("div").find("li").removeClass("checked");
	$(this).next("div").find("li").eq(0).click();
	$(this).next("div").slideToggle("slow").siblings(".div3:visible").slideUp("slow");
	$(this).addClass("checked");
	$(this).children().show();
}
//点击li列表，显示该li对应页面
function openurl(url,btn) {
	$(btn).parents("div").find("li").removeClass("checked");
	$("#rightFrame").attr("src",url);
	$(btn).addClass("checked");
}
//点击首页，显示首页内容
function left_top(){
	$("#rightFrame").attr("src","home.html");
}
//查询店铺列表
function shops_uniquefind(){
	
	$.ajax({
		url:"shops_uniquefind.do",
		type:"post",
		data:{"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#shops").empty();
				var shops=result.data;
				if(shops.length==0){
					window.location.href="newShop.html";
				}else if(shops.length==1){
					var li="<li class='checked'>"+shops[0].shop_name+"</li>";
					var $li=$(li);
					$li.data("shop_unique",shops[0].shop_unique);
					$li.data("manager_unique",getCookie("manager_unique"));
					$("#shops").append($li);
					addCookie("shop_unique", shops[0].shop_unique,10);
					addCookie("area_dict_num",shops[0].area_dict_num,10);
					shop_unique=shops[0].shop_unique;
				}else{
					for(var i=0;i<shops.length;i++){
						if(i==0){
							var li="<li class='checked'>"+shops[i].shop_name+"</li>";
						}else{
							var li="<li>"+shops[i].shop_name+"</li>";
						}
						var $li=$(li);
						$li.data("shop_unique",shops[i].shop_unique);
						$li.data("manager_unique",getCookie("manager_unique"));
						$("#shops").append($li);
						
					}
					var li="<li class='checked'>"+"全部"+"</li>";
					$li=$(li);
					$li.data("manager_unique",getCookie("manager_unique"));
					$("#shops").append($li);
					addCookie("area_dict_num",shops[0].area_dict_num,10);//添加店铺所在地理位置信息
				}
				li="<li><input type='button' value='添加新店' onclick='addNewShop()' class='change'></li>";
				$("#shops").append(li);
			}
			findPurCartGoods();//查询购物车里的商品数量
			findExCartGoods();//查询退货清单的商品数量
			loadHome();//加载主页面
		},
		error:function(){
			alert("查询失败");
		}
	});
	
}

//定期检查是否有新存在的订单
function checkNewOrder(){
	var time=new Date();
	var start=new Date(time.getTime()-300*1000);
	var startTime=start.getFullYear()+"-"+(start.getMonth()+1)+"-"+start.getDate()+" "+start.getHours()+":"+start.getMinutes()+":"+start.getSeconds();
	$.ajax({
		url:"sale_list/checkNewOrder.do",
		type:"post",
		data:{"shop_unique":shop_unique,"startTime":startTime,"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var data=result.data;
				var k=0;
				for(var i=0;i<data.length;i++){
					if(data[i].sale_list_datetime-start>0){
						k++
					}
				}
				$("#newOrderWarning").html(k);
			}else{
				$("#newOrderWarning").html(0);
			}
		},
		error:function(){
			console.log("系统异常！");
//			alert("查询新订单异常！请检查网络！");
		}
	})
}

//点击添加新的商品，跳转到商铺添加页面
function addNewShop(){
	window.location.href="newShop.html";
}

//查询购物车里商品数量
function findPurCartGoods(){
	$.ajax({
		url:"html/exchange/purchase/findPurCartGoods.do",
		type:"post",
		data:{"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goodss=result.data;
				var k=0;
				for(var i=0;i<goodss.length;i++){
					if(goodss[i].purchase_list_detail_count!=null){
						k+=goodss[i].purchase_list_detail_count;
					}
				}
				$("#newPurchase").html(k);
			}
			if(result.status==1){
				$("#newPurchase").html(0);
			}
		},
		error:function(){
			alert("查询购物车产品数量失败！");
		}
	})
}
//查询退货清单里的商品数量
function findExCartGoods(){
	$.ajax({
		url:"html/exchange/exchange/findExCartGoods.do",
		type:"post",
		data:{"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goods=result.data;
				var count=0;
				for(var i=0;i<goods.length;i++){
					count+=goods[i].exchange_list_detail_count*1;
				}
				$("#newExchange").html(count);
			}
		},
		error:function(){
			alert("查询换货清单商品数量失败！");
		}
	})
}
