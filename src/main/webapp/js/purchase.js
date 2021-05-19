var shop_unique=getCookie("shop_unique");
$(function(){
	//加载页面后，商品信息输入框自动获取焦点
	$("#goods_message").focus();
	//单击商品查询按钮，实现商品查询
	$("#goodsQuery").click(goodsQuery);
	//页面单击，实现数据查询
	$("body").keydown(function(event){
		var code=event.keyCode;
		if(code==13){
			$("#goodsQuery").click();
		}
		if(code==27){
			$(".pageClose").click();
		}
	});
	//自动添加商品大类
	add_goods_kind_group();
	//修改大类后，自动修改对应的小类
	$("#goods_kind_group").change(chgroup);
	//点击关闭按钮，将商品列表关闭并返回定单页面
	$(".pageClose").click(pageClose);
	//提交订单
	$("#submitPurchase").click(submitPurchase);
	//进入页面后，自动加载购物车商品
	findPurCartGoods();
})

//单击商品查询，弹出商品查询结果界面并显示结果集
function goodsQuery(){
	var goods_message=$("#goods_message").val().trim();
	var sup_goods_kind_unique=$("#goods_kind_unique option:selected").val();
	var sup_goods_kind_parunique=$("#goods_kind_group option:selected").val();
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
					tr+="<input type='button' value='-' onclick='reduce(this)' class='stopPropagation sbutton'><input type='text' maxlength='10' placeholder='订购数量' value='1' " +
							"onblur='testNum(this)' class='stopPropagation stext'> <input type='button' value='+' onclick='addCount(this)' class='stopPropagation sbutton'></td><td>";
					if(sup_goods[i].goods_price!=null){
						tr+=sup_goods[i].goods_price+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					tr+="<input type='button' value='添加到订单' onclick='addToCart(this)'></td><td class='public'>";
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
//单击页码，查询对应的商品信息
function goodsQuery1(pageNum){
	var goods_message=getCookie("goods_message");
	var sup_goods_kind_unique=getCookie("sup_goods_kind_unique");
	var sup_goods_kind_parunique=getCookie("sup_goods_kind_parunique");
	$.ajax({
		url:"goods/goodsQuery.do",
		type:"post",
		data:{"goods_message":goods_message,"sup_goods_kind_unique":sup_goods_kind_unique,"sup_goods_kind_parunique":sup_goods_kind_parunique,"shop_unique":shop_unique,"pageNum":pageNum},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#goodsList").empty();
				var sup_goods=result.data;
				for(var i=0;i<sup_goods.length-1;i++){
					var tr="<tr><td>"+(i+1)+"</td><td>";
					tr+=sup_goods[i].goods_barcode+"</td><td>";
					tr+=sup_goods[i].goods_name+"</td><td>";
					tr+="<input type='button' value='-' onclick='reduce(this)' class='stopPropagation sbutton'><input type='text' maxlength='10' placeholder='订购数量'" +
							" value='1' onblur='testNum(this)' class='stopPropagation stext'> <input type='button' value='+' onclick='addCount(this)' class='stopPropagation sbutton'></td><td>";
					tr+=sup_goods[i].goods_price+"</td><td>";
					tr+="<input type='button' value='添加到订单' onclick='addToCart(this)'></td></td class='public'>";
					tr+=sup_goods[i].supplier_unique+"</td></tr>";
					$tr=$(tr);
					$tr.data("supplier_unique",sup_goods[i].supplier_unique);
					$("#goodsList").append($tr);
				}
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
//自动添加商品大类
//自动添加商品大类
function addGroup(){
	$.ajax({
		url:"goods/findParNames.do",
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var parnames=result.data;
				$("#goods_kind_parunique").empty();
				var option="<option value='0'>请选择商品大类</option>";
				$("#goods_kind_parunique").append(option);
				for(var i=0;i<parnames.length;i++){
					var option="<option value='"+parnames[i].sup_goods_kind_unique+"'>"+parnames[i].sup_goods_kind_name+"</option>";
					$("#goods_kind_parunique").append(option);
				}
			}
			if(result.status==1){
			}
		},
		error:function(){
			alert("加载大类失败，请检查网络");
		}
	});
}
//修改商品大类后，自动填充商品小类
//大类修改后，自动添加商品小类
function addNames(){
	var sup_goods_kind_parunique=$("#goods_kind_parunique option:selected").val();
	$.ajax({
		url:"goods/findNames.do",
		type:"post",
		data:{"sup_goods_kind_parunique":sup_goods_kind_parunique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#goods_kind_unique").empty();
				var names=result.data;
				var option="<option value='0'>请选择商品小类</option>";
				$("#goods_kind_unique").append(option);
				for(var i=0;i<names.length;i++){
					var option="<option value='"+names[i].sup_goods_kind_unique+"'>"+names[i].sup_goods_kind_name+"</option>";
					$("#goods_kind_unique").append(option);
				}
			}
		},
		error:function(){
			alert("查询失败！");
		}
	});
}
//单击减号按钮，减少商品数量
//单击减号，修改商品的数量
function reduce(btn){
	var $tds=$(btn).parents("tr").children();
	var goods_barcode=$tds.eq(1).text();
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	var num=$(btn).parent().children().eq(1).val();
	if(num<=1){
		return;
	}else{
		$(btn).parent().children().eq(1).val(--num);
		if($(btn).parent().parent().children().length==8){
			$.ajax({
				url:"purchase/addToCart.do",
				type:"post",
				data:{"goods_barcode":goods_barcode,"purchase_list_detail_count":-1,"supplier_unique":supplier_unique,"shop_unique":shop_unique},
				dataType:"json",
				success:function(result){
					if(result.status==0){
						num--;
							purchaseTotal();
					}
					if(result.status==1){
					}
					window.parent.findPurCartGoods();
				},
				error:function(){
					alert("减少失败，请检查网络！");
				}
			});
		}
	}
}
//单击加号按钮，增加商品数量
//单击加号，增加商品数量
function addCount(btn){
	var $tds=$(btn).parents("tr").children();
	var goods_barcode=$tds.eq(1).html();
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	var num=$(btn).parent().children().eq(1).val();
	$(btn).parent().children().eq(1).val(++num);
	purchaseTotal();
	if($(btn).parent().parent().children().length==8){
		$.ajax({
			url:"purchase/addToCart.do",
			type:"post",
			data:{"goods_barcode":goods_barcode,"purchase_list_detail_count":+1,"supplier_unique":supplier_unique,"shop_unique":shop_unique},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					
				}
				window.parent.findPurCartGoods();
			},
			error:function(){
				alert("减少失败，请检查网络！");
			}
		});
	}
}
//单击关闭按钮，关闭弹出商品查询界面
//点击关闭按钮，关闭弹出页面
function pageClose(){
	$(this).parent().parent().css("display","none");
	$(".aindex").css("display","none");
}
//单击添加到订单按钮，将商品添加到购物车
function addToCart(btn){
	var $tds=$(btn).parent().parent().children();
	var goods_barcode=$tds.eq(1).html();
	var goods_name=$tds.eq(2).html();
	var purchase_list_detail_count=$tds.eq(3).children().eq(1).val();
	var goods_price=$tds.eq(4).html();
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	if(supplier_unique==null||supplier_unique==""||supplier_unique=="undefined"){
		alert("请先选择商品供应商！");
		return;
	}
	$.ajax({
		url:"purchase/addToCart.do",
		type:"post",
		data:{
			"goods_barcode":goods_barcode,"purchase_list_detail_count":purchase_list_detail_count,"supplier_unique":supplier_unique,"shop_unique":shop_unique
		},
		dataType:"json",
		success:function(result){
			var $trs=$("#purchaseGoods").children();
			for(var i=0;i<$trs.length;i++){
				if(goods_barcode==$trs.eq(i).children().eq(1).html()){
					$trs.eq(i).children().eq(3).children().eq(1).val($trs.eq(i).children().eq(3).children().eq(1).val()*1+purchase_list_detail_count*1);
					$(".goodsLoad").css("display","none");
					$(".aindex").css("display","none");
					purchaseTotal();
					order();
					$("#goods_message").focus();
					window.parent.findPurCartGoods();
					return;
				}
			}
			var tr="<tr><td></td><td>"+goods_barcode+"</td><td>";
			tr+=goods_name+"</td><td>";
			tr+="<input type='button' value='-' onclick='reduce(this)'><input type='text' maxlength='5' placeholder='订购数量' value='"+purchase_list_detail_count+"' onblur='testNum(this)'> <input type='button' value='+' onclick='addCount(this)'></td><td>";
			tr+=goods_price+"</td><td>";
			tr+=purchase_list_detail_count*goods_price+"</td><td>";
			tr+="<input type='button' value='移出订单' onclick='rmFromCart(this)'></td><td class='public'>";
			tr+=$tds.eq(6).html()+"</td></tr>";
			$tr=$(tr);
			$tr.data("supplier_unique",supplier_unique);
			$tr.data("purchase_list_unique",result.data);
			$("#purchaseGoods").append($tr);
			$(".goodsLoad").css("display","none");
			purchaseTotal();
			$(".aindex").css("display","none");
			order();
			window.parent.findPurCartGoods();
			$("#goods_message").focus();
		},
		error:function(){
			alert("添加商品到购物车失败，请检查网络！");
		}
	});
}
//数量栏失去光标，查询其值是否为一数字,若不是一个数字或者是负数，则修改为1
function testNum(btn){
	var num=$(btn).val();
	var re=/^[1-9]\d*$/;
	if(!re.test(num)){//如果输入的内容不符合格式，则将其值修改为1
		$(btn).val(1);
		num=1;
	}else{
		console.log(num);
	}
	if($(btn).parent().parent().children().length==8){
		var purchase_list_detail_count=$(btn).val();
		var goods_barcode=$(btn).parents("tr").children().eq(1).text();
		var supplier_unique=$(btn).parents("tr").data("supplier_unique");
		var purchase_list_unique=$(btn).parents("tr").data("purchase_list_unique");
		$.ajax({
			url:"purchase/updateCart.do",
			type:"post",
			data:{"goods_barcode":goods_barcode,"purchase_list_detail_count":purchase_list_detail_count,"supplier_unique":supplier_unique,"shop_unique":shop_unique,"purchase_list_unique":purchase_list_unique},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					window.parent.findPurCartGoods();
				}
			},
			error:function(){
				alert("增加商品数量失败，请检查网络！");
			}
		})
		purchaseTotal();
	}
}
//调整商品数量后，购物车总价格
function purchaseTotal(){
	var $trs=$("#purchaseGoods").children();
	var purchaseTotal=0;
	for(var i=0;i<$trs.length;i++){
		var goods_count=$trs.eq(i).children().eq(3).children().eq(1).val();
		var goods_price=$trs.eq(i).children().eq(4).html();
		$trs.eq(i).children().eq(5).html(goods_count*goods_price);
		purchaseTotal+=goods_count*goods_price;
	}
	$("#purchase_list_total").html(purchaseTotal.toFixed(2));
}
//点击移出订单，将选中的商品移除商品清单
function rmFromCart(btn){
	var goods_barcode=$(btn).parents('tr').children().eq(1).html().trim();
	$.ajax({
		url:"purchase/rmCartGoods.do",
		type:"post",
		data:{"shop_unique":shop_unique,"goods_barcode":goods_barcode},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$(btn).parent().parent().addClass("checked");
				$("#purchaseGoods").find(".checked").remove();
				purchaseTotal();
				order();
				window.parent.findPurCartGoods();
			}
			if(result.status==1){
				console.log(result.msg);
			}
		},
		error:function(){
			alert("移除失败！");
		}
	});
}
//购物车排序
function order(){
	var $trs=$("#purchaseGoods").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//提交订单
function submitPurchase(){
	$trs=$("#purchaseGoods").children();
	var details="";
	for(var i=0;i<$trs.length;i++){
		if($trs.eq(i).children().eq(3).children().eq(1).val()<0){
			alert("采购数量不能为负数");
			return;
		}
		if(i==0){
			details+=$trs.eq(i).children().eq(1).html().trim();
		}else{
			details+=";"+$trs.eq(i).children().eq(1).html().trim();
		}
		details+=":"+$trs.eq(i).children().eq(3).children().eq(1).val();
		details+=":"+$trs.eq(i).data("supplier_unique");
	}
	var purchase_list_unique=$trs.eq(0).data("purchase_list_unique");
	if(purchase_list_unique==null){
		return;
	}
	if(purchase_list_total==0||purchase_list_total==null){
		alert("不能添加空订单!");
		return;
	}
	if(purchase_list_total<0){
		alert("不能添加订单，采购金额为负！");
	}
	$.ajax({
		url:"purchase/newPurchase.do",
		type:"post",
		data:{"purchase_list_unique":purchase_list_unique,"details":details},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg); 
				window.parent.findPurCartGoods();
				findPurCartGoods();
			}
		},
		error:function(){
			alert("提交订单失败，请检查网络！");
		}
	});
}
//查询购物车中的商品
function findPurCartGoods(){
	$.ajax({
		url:"purchase/findPurCartGoods.do",
		type:"post",
		dataType:"json",
		data:{"shop_unique":shop_unique},
		success:function(result){
			$("#purchaseGoods").empty();
			if(result.status==0){
				var goodss=result.data;
				for(var i=0;i<goodss.length;i++){
					if(goodss[i].goods_barcode!=null){
						var tr="<tr><td></td><td>"+goodss[i].goods_barcode+"</td><td>";
						tr+=goodss[i].goods_name+"</td><td>";
						tr+="<input type='button' value='-' onclick='reduce(this)'><input type='text' maxlength='5' placeholder='订购数量' value='"+goodss[i].purchase_list_detail_count+"' onblur='testNum(this)'> <input type='button' value='+' onclick='addCount(this)'></td><td>";
						tr+=goodss[i].goods_price+"</td><td></td><td>";
						tr+="<input type='button' value='移出订单' onclick='rmFromCart(this)'></td><td class='public'>";
						$tr=$(tr);
						$tr.data("supplier_unique",goodss[i].supplier_unique);
						$tr.data("purchase_list_unique",goodss[i].purchase_list_unique);
						$("#purchaseGoods").append($tr);
						purchaseTotal();
						order();
					}
					$("#goods_message").focus();
				}
			}
		},
		error:function(){
			alert("查询店铺购物车商品失败，请检查网络！");
		}
	})
}
