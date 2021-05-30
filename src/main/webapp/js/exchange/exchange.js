var shop_unique=getCookie("shop_unique").trim();
$(function(){
	//加载页面后，商品信息输入框自动获取焦点
	$("#goods_message").focus();
	//单击商品查询按钮，实现商品查询
	$("#goodsQuery").click(goodsQuery);
	//自动添加商品大类
	addGroup();
	//修改大类后，自动修改对应的小类
	$("#goods_kind_parunique").change(addNames);
	//点击关闭按钮，将商品列表关闭并返回定单页面
//	$(".pageClose").click(pageClose);
	$("body").keydown(function(event){
		var code=event.keyCode;
		if(code==13){
			$("#goodsQuery").click();
		}
		if(code==27){
			$(".pageClose").click();
		}
	});
	$(".pageClose").click(pageClose);
	//提交订单
	$("#submitExCart").click(submitExCart);
	//进入页面后，自动加载购物车商品
	findExchangeGoods();
})
//单击商品查询，弹出商品查询结果界面并显示结果集
function goodsQuery(){
	var goods_message=$("#goods_message").val().trim();
	var sup_goods_kind_unique=$("#goods_kind_unique option:selected").val();
	var sup_goods_kind_parunique=$("#goods_kind_parunique option:selected").val();
	addCookie("sup_goods_kind_unique", sup_goods_kind_unique, 2);
	addCookie("sup_goods_kind_parunique",sup_goods_kind_parunique,2);
	addCookie("goods_message",goods_message,2);
	var pageNum=0;
	$.ajax({
		url:"goods/goodsQuery.do",
		type:"post",
		data:{"goods_message":goods_message,"sup_goods_kind_unique":sup_goods_kind_unique,"sup_goods_kind_parunique":sup_goods_kind_parunique,"shop_unique":shop_unique,"pageNum":pageNum},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$(".goodsLoad").show();
				$(".aindex").show();
				$("#goodsList").empty();
				var sup_goods=result.data;
				for(var i=0;i<sup_goods.length-1;i++){
					var tr="<tr><td>"+(i+1)+"</td><td>";
					tr+=sup_goods[i].goods_barcode+"</td><td>";
					tr+=sup_goods[i].goods_name+"</td><td>";
					tr+="<input type='button' value='-' onclick='reduceCart' class='stopPropagation sbutton'><input type='text' maxlength='10' placeholder='订购数量' value='1' " +
							"onblur='testNum(this)' class='stopPropagation stext'> <input type='button' value='+' onclick='addCount(this)' class='stopPropagation sbutton'></td><td>";
					tr+="<input type='button' value='添加到订单' onclick='addToExCart(this)'></td><td class='public'>";
					tr+=sup_goods[i].supplier_unique+"</td></tr>";
					$tr=$(tr);
					$tr.data("supplier_unique",sup_goods[i].supplier_unique);
					$("#goodsList").append($tr);
				}
				$(".tcdPageCode").createPage({
					pageCount:sup_goods[sup_goods.length-1].pageSize,
					current:1,
					backFn:function(pageNum){
						goodsQuery1(pageNum-1);
					}
				});
			}
			if(result.status==1){
				alert("没有满足条件的商品");
			}
			$("#goods_message").focus();
		},
		error:function(){
			alert("查询失败，请检查网络！");
		}
	});
}
//配合public使用，无实际意义
function total(){}
//将商品添加到换货订单
function addToExCart(btn){
	var $tds=$(btn).parent().parent().children();
	var goods_barcode=$tds.eq(1).html();
	var goods_name=$tds.eq(2).html();
	var exchange_list_detail_count=$tds.eq(3).children().eq(1).val();
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	if(supplier_unique==null||supplier_unique==""||supplier_unique=="undefined"){
		alert("请先选择商品供应商！");
		return;
	}
	$.ajax({
		url:"exchange/addToExCart.do",
		type:"post",
		data:{
			"goods_barcode":goods_barcode,"exchange_list_detail_count":exchange_list_detail_count,"supplier_unique":supplier_unique,"shop_unique":shop_unique
		},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goods=result.data;
				var $trs=$("#exchangeGoods").children();
				for(var i=0;i<$trs.length;i++){
					var barcode=$trs.eq(i).children().eq(1).html();
					if(barcode==goods_barcode){
						$trs.eq(i).children().eq(4).children().eq(1).val(goods.cartCount);
						$(".pageClose").click();
						order();
						return;
					}
				}
				var tr="<tr><td></td><td>"+goods_barcode+"</td><td>"+goods_name+"</td><td>"+goods.goods_count+"</td><td><input type='button' value='-' onclick='reduceExCartCount(this)'><input type='text' value='" ;
				tr+=goods.cartCount+"' ><input type='button' value='+' onclick='addExCartCount(this)'></td><td><input type='button' value='移出退货' onclick='rmCartGoods(this)'></td></tr>";
				$tr=$(tr);
				$tr.data("supplier_unique",supplier_unique);
				$("#exchangeGoods").append($tr);
				$(".pageClose").click();
				order();//购物车排序,计算购物车总数量
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("添加失败，请检查网络！");
		}
	});
}
//商品排序,并计算总数量
function order(){
	var $trs=$("#exchangeGoods").children();
	var count=0;
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
		count+=$trs.eq(i).children().eq(4).children().eq(1).val()*1;
	}
	$("#exchange_list_total").html(count);
	$("#newExchange",window.parent.document).html(count);
}
//进入购物车后，自动查询退货购物车
function findExchangeGoods(){
	$.ajax({
		url:"exchange/findExCartGoods.do",
		type:"post",
		data:{"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goods=result.data;
				for(var i=0;i<goods.length;i++){
					var tr="<tr><td></td><td>"+goods[i].goods_barcode+"</td><td>"+goods[i].goods_name+"</td><td>"+goods[i].goods_count+"</td><td>";
					tr+="<input type='button' value='-' onclick='reduceExCartCount(this)'><input type='text' value='"
					tr+=goods[i].exchange_list_detail_count+"' onblur='testNum(this)'><input type='button' value='+' onclick='addExCartCount(this)'></td><td><input type='button' value='移出购物车' onclick='rmCartGoods(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("supplier_unique",goods[i].supplier_unique);
					$("#exchangeGoods").append($tr);
				}
				order();
			}
		},
		error:function(){
			alert("查询退货订单失败，请检查网络！");
		}
	})
}

//单击加号，添加购物车里的商品数量
function addExCartCount(btn){
	var $tr=$(btn).parents("tr");
	var goods_barcode=$tr.children().eq(1).html();
	var exchange_list_detail_count=$tr.children().eq(4).children().eq(1).val()*1+1;
	var goods_count=$tr.children().eq(3).html();
	if(isNaN(exchange_list_detail_count)){
		alert("输入的数量不是一个数字！");
		return;
	}
	if(exchange_list_detail_count-goods_count>0){
		alert("换货数量已经大于库存量，请确认！");
		return;
	}
	$.ajax({
		url:"exchange/changeExCartCount.do",
		type:"post",
		data:{"shop_unique":shop_unique,"goods_barcode":goods_barcode,"goods_count":exchange_list_detail_count},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$tr.children().eq(4).children().eq(1).val(exchange_list_detail_count);
			}
			order();
		},
		error:function(){
			alert("添加数量失败，请检查网络！");
		}
	});
}
//单击减号，减少购物车里的商品
function reduceExCartCount(btn){
	var $tr=$(btn).parents("tr");
	var goods_barcode=$tr.children().eq(1).html();
	var exchange_list_detail_count=$tr.children().eq(4).children().eq(1).val()*1-1;
	var goods_count=$tr.children().eq(3).html();
	if(isNaN(exchange_list_detail_count)){
		alert("输入的数量不是一个数字！");
		return;
	}
	
	if(exchange_list_detail_count<1){
		return;
	}
	$.ajax({
		url:"exchange/changeExCartCount.do",
		type:"post",
		data:{"shop_unique":shop_unique,"goods_barcode":goods_barcode,"goods_count":exchange_list_detail_count},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$tr.children().eq(4).children().eq(1).val(exchange_list_detail_count);
			}
			order();
		},
		error:function(){
			alert("添加数量失败，请检查网络！");
		}
	});
}
//退换数量栏手动输入后，光标离开时，测试数量是否在可用范围
function testNum(btn){
	var $tr=$(btn).parents("tr");
	var goods_barcode=$tr.children().eq(1).html();
	var goods_count=$tr.children().eq(3).html();
	var exchange_list_detail_count=$(btn).val();
	if(isNaN(exchange_list_detail_count)){
		alert("输入的不是一个正确的数值！");
		$(btn).addClass("warningtd");
		return;
	}else{
		$(btn).removeClass("warningtd");
	}
	if(exchange_list_detail_count-goods_count>0){
		alert("输入的商品数量大于库存，请确认");
		exchange_list_detail_count=goods_count;
		$(btn).val(goods_count);
	}else{
		$(btn).removeClass("warningtd");
	}
	var re=/^[1-9]\d*$/;
	console.log(exchange_list_detail_count);
	console.log(re.test(exchange_list_detail_count));
	if(!re.test(exchange_list_detail_count)){
		$(btn).val(1);
		exchange_list_detail_count=1;
	}else{
	}
	$.ajax({
		url:"exchange/changeExCartCount.do",
		type:"post",
		data:{"shop_unique":shop_unique,"goods_barcode":goods_barcode,"goods_count":exchange_list_detail_count},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				
			}
			order();
		},
		error:function(){
			alert("添加数量失败，请检查网络！");
		}
	});
}

//将不需要换货的商品移出换货清单
function rmCartGoods(btn){
	var $trs=$(btn).parents("tr");
	var goods_barcode=$trs.children().eq(1).html().trim();
	$.ajax({
		url:"exchange/rmExCartGoods.do",
		type:"post",
		data:{"shop_unique":shop_unique,"goods_barcode":goods_barcode},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$trs.remove();
				order();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("删除换货单商品数量失败，请检查网络！");
		}
	});
	$trs.remove();
}

//提交换货车清单
function submitExCart(){
	var unique="";
	var arr=new Array();
	var $trs=$("#exchangeGoods").children();
	for(var i=0;i<$trs.length;i++){
		var supplier_unique=$trs.eq(i).data("supplier_unique");
		var flag=true;
		$.each(arr,function(n,value){
			if(supplier_unique==value&&value!=null){
				flag=false;
				return false;
			}
		});
		if(flag){
			arr.push(supplier_unique);
		}
	}
	$.each(arr,function(n,value){
		if(n==0){
			unique+=value;
		}else{
			unique+=";"+value;
		}
	});
	$.ajax({
		url:"exchange/submitExCart.do",
		type:"post",
		data:{"shop_unique":shop_unique,"unique":unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$("#exchangeGoods").empty();
				order();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("提交退货清单失败！请检查网络！");
		}
	});
}
