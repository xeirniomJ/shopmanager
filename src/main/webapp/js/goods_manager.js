var shop_unique = getCookie("shop_unique");
	var manager_unique=getCookie("manager_unique");
	var shop_unique=getCookie("shop_unique");
		$(function() {
			//条码输入框自动获取焦点
			$("#goodsmessage").focus();
			//进入页面，自动添加商品大类
			add_goods_kind_group();
			//进入页面，自动添加商品小类
			add_goods_kind_name();
			//查询选择商品大类后，自动修改商品小类
			$("#ingoods_kind_group").change(changegroup);
			$("#goods_kind_group").change(chgroup);
			//点击更新商品按钮，进入商品信息修改页面
			$("#goods_modify").click(goods_modify);
			////点击删除商品按钮，将该商品的记录信息删除，暂不建议删除
			//$("#goods_delete").click(goods_delete);
			//点击保存修改按钮，将修改后的商品信息保存，并返回上一层 
			$("#goods_modify_save").click(goods_modify_save);
			//点击取消修改按钮，直接返回上一层
			$("#goods_modify_cancel").click(goods_modify_cancel);
			//单击添加新商品按钮，跳转到添加新商品页面
			$("#newgoods").click(newgoods);
			//点击保存新加按钮，将新的产品添加到商品表里
			//点击查询按钮，根据输入条件查询相应结果
			$("#find").click(findGoods);
			//添加新商品
			$("#goods_new").click(new_goodss);
			//单击页面实现查询
			$("body").keydown(function(event){
				var code=event.keyCode;
				if(code==13){
					$("#find").click();
				}
			});
			//点击关闭按钮，关闭弹出页面
			$(".pageClose").click(pageClose);
			//默认供应商按钮，跳转至默认供应商界面
			$("#supplier_name").click(supplierChange);
			//页面加载后，自动加载省份
			loadProvince();
			//修改省份，自动加载城市列表
			$("#provinces").change(loadCities);
			//城市修改后，自动加载区县列表
			$("#cities").change(loadContries);
			//区县修改后，自动加载满足条件的供应商信息
			$("#contries").change(findGoodsSuppliers);
			//点击设为默认，将商品供应商信息修改为客户选择供应商,并返回商品修改页面
			$("#setDefault").click(updateGoodsSupplier);
			//点击取消修改按钮，取消默认供应商的信息修改
			$("#cancelSet").click(cancleSet);
			//修改条码后，根据条码内容，查询满足条件的商品内容，并注入网页
			$("#goods_barcode").change(fillGoodsMessage);
		});
//点击更新商品按钮，进入商品更新页面
function goods_modify(btn){
	var goods_barcode=$(btn).parent().parent().children().eq(2).html();
	$.ajax({
		url:"goods/findGood.do",
		type:"post",
		data:{"goods_barcode":goods_barcode,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goods=result.data;
				$("#goods_id").html(goods.goods_id);
				$("#goods_name").val(goods.goods_name);
				$("#goods_brand").val(goods.goods_brand);
				$("#goods_barcode").val(goods.goods_barcode);
				$("#goods_alias").val(goods.goods_alias);
				$("#goods_sale_price").val(goods.goods_sale_price);
				$("#goods_price").val(goods.goods_price);
				$("#goods_life").val(goods.goods_life);
				$("#goods_points").val(goods.goods_points);
				$("#goods_address").val(goods.goods_address);
				$("#goods_contain").val(goods.goods_contain);
				$("#goods_standard").val(goods.goods_standard);
				$("#goods_count").val(goods.goods_count);
				$("#supplier_name").val(goods.supplier_name);
				$("#supplier_name").data("supplier_unique",goods.supplier_unique);
				var $groups=$("#goods_kind_group").children();
				var name=goods.name;
				for(var i=0;i<$groups.length;i++){
					if($groups.eq(i).text().trim()==goods.goods_kind_name){
						$groups[i].selected=true;
						addNames(name);
					}
				}
			}
			$("#goods_sale_price").focus();
		},
		error:function(){
			alert("进入产品修改页面失败，请稍后重试");
		}
	});
	$(".showarea").css("display","none");
	$(".goods_new").css("display","none");
	$(".goods_modify").show();
}
//点击保存更新按钮，将修改后的信息保存并返回上一层,更新商品信息
function goods_modify_save(){
	var flag=true;
	var goods_id=$("#goods_id").html();
	var goods_name=$("#goods_name").val();
	var goods_brand=$("#goods_brand").val();
	var goods_barcode=$("#goods_barcode").val();
	var goods_alias=$("#goods_alias").val();
	var goods_sale_price=$("#goods_sale_price").val();
	var goods_price=$("#goods_price").val();
	var goods_life=$("#goods_life").val();
	var goods_points=$("#goods_points").val();
	var goods_address=$("#goods_address").val();
	var goods_contain=$("#goods_contain").val();
	var goods_standard=$("#goods_standard").val();
	var goods_kind_parunique=$("#goods_kind_group option:selected").val();
	var goods_kind_unique=$("#goods_kind_unique option:selected").val();
	var goods_count=$("#goods_count").val();
	var supplier_unique=$("#supplier_name").data("supplier_unique");
	if(flag){
		 $("#uploadForm").ajaxSubmit({  
			   url: "goods/updateGoods.do",  
			   contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		       data:{"goods_id":goods_id,"goods_name":encodeURI(goods_name),"goods_brand":encodeURI(goods_brand),"goods_barcode":encodeURI(goods_barcode),"goods_alias":encodeURI(goods_alias),"goods_sale_price":encodeURI(goods_sale_price)
					,"goods_life":encodeURI(goods_life),"goods_points":encodeURI(goods_points),"goods_address":encodeURI(goods_address),"goods_contain":encodeURI(goods_contain),"goods_standard":encodeURI(goods_standard),"goods_kind_parunique":encodeURI(goods_kind_parunique)
					,"goods_kind_unique":encodeURI(goods_kind_unique),"shop_unique":encodeURI(shop_unique),"goods_price":encodeURI(goods_price),"goods_count":goods_count,"supplier_unique":encodeURI(supplier_unique)},
		       type: 'POST',  
		       dataType:"json",
		       success:function(result){
					if(result.status==0){
						alert(result.msg);
						$(".showarea").css("display","none");
						$(".goods_show_area").show();
						findGoods();
					}
					if(result.status==1){
						alert(result.msg);
					}
					$("#goodsmessage").val("");
					$("#goodsmessage").focus();
				},
				error:function(){
					alert("11商品更新失败");
				}
		});
	}
}
//点击取消更新按钮，直接返回上一层
function goods_modify_cancel(){
	$(".showarea").css("display","none");
	$(".goods_show_area").show();
}
//单击添加新品按钮，显示新品添加页面
function newgoods(){
	$(".goods_modify").css("display","none");
	$(".showarea").css("display","none");
	$(".goods_new").show();
	//清空页面内容
	$("#goods_id").html("");
	$("#goods_name").val("");
	$("#goods_brand").val("");
	$("#goods_barcode").val("");
	$("#goods_alias").val("");
	$("#goods_sale_price").val("");
	$("#goods_price").val("");
	$("#goods_life").val("");
	$("#goods_points").val("");
	$("#goods_address").val("");
	$("#goods_contain").val("");
	$("#goods_standard").val("");
	var $groups=$("#goods_kind_group").children();
	for(var i=0;i<$groups.length;i++){
		if($groups.eq(i).text()=="请选择商品大类"){
			$groups[i].selected=true;
		}
	}
	var $names=$("#goods_kind_name").children();
	for(var j=0;j<$names.length;j++){
		if($names.eq(j).text()=="请选择商品小类"){
			$names[j].selected=true;
		}
	}
	$(".goods_modify").css("display","none");
	$(".goods_new").show();
	$("#goods_barcode").focus();
}
//单击保存新加按钮，将新产品添加到数据库中
function goods_new(){
	var goods_id=$("#goods_id").html();
	var goods_name=$("#goods_name").val();
	var goods_brand=$("#goods_brand").val();
	var goods_barcode=$("#goods_barcode").val();
	var goods_alias=$("#goods_alias").val();
	var goods_sale_price=$("#goods_sale_price").val();
	var goods_price=$("#goods_price").val();
	var goods_life=$("#goods_life").val();
	var goods_points=$("#goods_points").val();
	var goods_address=$("#goods_address").val();
	var goods_contain=$("#goods_contain").val();
	var goods_standard=$("#goods_standard").val();
	var goods_kind_parunique=$("#goods_kind_group option:selected").val();
	var goods_kind_unique=$("#goods_kind_unique option:selected").val();
	if(goods_name==""){
		alert("商品名称不能为空！");
		return;
	}
	if(goods_sale_price==""){
		alert("商品售价不能为空");
		return;
	}
	if(goods_kind_parunique=="0"){
		alert("请选择商品大类");
		return;
	}
	if(goods_kind_unique=="0"){
		alert("请选择商品小类");
		return;
	}
	if(goods_list==""){
		alert("商品保质期不能为空");
		return;
	}
	$.ajax({
		url:"goods/newGoods.do",
		type:"post",
		data:{"goods_name":goods_name,"goods_brand":goods_brand,"goods_barcode":goods_barcode,"goods_alias":goods_alias,"goods_sale_price":goods_sale_price
			,"goods_life":goods_life,"goods_points":goods_points,"goods_address":goods_address,"goods_contain":goods_contain,"goods_standard":goods_standard,"goods_kind_parunique":goods_kind_parunique
			,"goods_kind_unique":goods_kind_unique,"shop_unique":shop_unique,"goods_price":goods_price},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
				}
			},
			error:function(){
				alert("添加失败");
			}
		});
	$(".showarea").css("display","none");
	$(".goods_show_area").show();
	$(".modify").css("display","none");
}
//新商品添加
function new_goodss(){
	console.log("添加新商品！");
	var goods_id=$("#goods_id").html();
	var goods_name=$("#goods_name").val();
	var goods_brand=$("#goods_brand").val();
	var goods_barcode=$("#goods_barcode").val();
	var goods_alias=$("#goods_alias").val();
	var goods_sale_price=$("#goods_sale_price").val();
	var goods_price=$("#goods_price").val();
	var goods_life=$("#goods_life").val();
	var goods_points=$("#goods_points").val();
	var goods_address=$("#goods_address").val();
	var goods_contain=$("#goods_contain").val();
	var goods_standard=$("#goods_standard").val();
	var goods_kind_parunique=$("#goods_kind_group option:selected").val();
	var goods_kind_unique=$("#goods_kind_unique option:selected").val();
	var goods_count=$("#goods_count").val();
	if(goods_name==""){
		alert("商品名称不能为空！");
		return;
	}
	if(goods_sale_price==""){
		alert("商品售价不能为空");
		return;
	}
	if(goods_kind_parunique=="0"){
		alert("请选择商品大类");
		return;
	}
	if(goods_kind_unique=="0"){
		alert("请选择商品小类");
		return;
	}
	   $("#uploadForm").ajaxSubmit({  
		   url: "goods/newGoods1.do",  
		   contentType: "application/x-www-form-urlencoded; charset=utf-8",
	       data:$('#img-form-agree').serialize(),
	       data:{"goods_name":encodeURI(goods_name),"goods_brand":encodeURI(goods_brand),"goods_barcode":encodeURI(goods_barcode),"goods_alias":encodeURI(goods_alias),"goods_sale_price":encodeURI(goods_sale_price)
				,"goods_life":encodeURI(goods_life),"goods_points":encodeURI(goods_points),"goods_address":encodeURI(goods_address),"goods_contain":encodeURI(goods_contain),"goods_standard":encodeURI(goods_standard),"goods_kind_parunique":encodeURI(goods_kind_parunique)
				,"goods_kind_unique":encodeURI(goods_kind_unique),"shop_unique":encodeURI(shop_unique),"goods_price":encodeURI(goods_price),"manager_unique":encodeURI(getCookie("manager_unique")),"goods_count":goods_count},
	       type: 'POST',  
	       dataType:"json",
	       success: function (result) {  
        	  alert(result.msg);
        	  $(".showarea").css("display","none");
        	  $(".goods_show_area").show();
        	  $(".modify").css("display","none");
        	  $("#goodsmessage").focus();
          },  
          error: function () {  
        	  alert("上传失败");
          }  
	   });
}
//单击查询按钮，根据输入条件查询相应结果
function findGoods(){
	var goodsmessage=$("#goodsmessage").val().trim();
	var ingoods_kind_unique=$("#ingoods_kind_name option:selected").val();
	var ingoods_kind_parunique=$("#ingoods_kind_group option:selected").val();
	var timea=new Date().getTime();
	var goods_id=null;
	console.log(goodsmessage);
	console.log(ingoods_kind_unique);
	console.log(ingoods_kind_parunique);
	$.ajax({
		url:"goods/findGoods.do",
		type:"post",
		data:{"goodsmessage":goodsmessage,"goods_kind_parunique":ingoods_kind_parunique,
			"goods_kind_unique":ingoods_kind_unique,"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			var timeb=new Date().getTime();
			$("#goods_list").empty();
			if(result.status==0){
				var maps=result.data;
				for(var i=0;i<maps.length;i++){
					var tr="<tr><td></td><td>"+maps[i].shop_name+"</td><td>";
					if(maps[i].goods_barcode==null){
						tr+="</td><td>";
					}else{
						tr+=maps[i].goods_barcode+"</td><td>";
					}
					if(maps[i].goods_name==null){
						tr+="</td><td>";
					}else{
						tr+=maps[i].goods_name+"</td><td>";
					}
					//商品售价
					if(maps[i].goods_sale_price==null){
						tr+="未定价</td><td style='text-align:right'>";
					}else{
						tr+=maps[i].goods_sale_price.toFixed(2)+"</td><td>";
					}
					if(maps[i].goods_count==null){
						tr+="0</td><td>";
					}else{
						tr+=maps[i].goods_count+"</td><td>";
					}
					if(maps[i].goods_standard!=null){
						tr+=maps[i].goods_standard+"</td><td>"
					}else{
						tr+="</td><td>";
					}
					//商品大类
					if(maps[i].l==null){
						tr+="未分类</td><td>";
					}else{
						tr+=maps[i].l+"</td><td>";
					}
					//商品小类
					if(maps[i].k==null){
						tr+="未分类</td><td>";
					}else{
						tr+=maps[i].k+"</td><td>";
					}
					//商品进价
					if(maps[i].goods_price==null){
						tr+="未定价</td><td>";
					}else{
						tr+=maps[i].goods_price+"</td><td>";
					}
					if(maps[i].supplier_name!=null){
						tr+=maps[i].supplier_name+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					tr+="<input type='button' value='采购' onclick='goods_purchase(this)'>" +
							"<input type='button' value='更新' onclick='goods_modify(this)'><input type='button' value='删除' onclick='deleteGoods(this)'>";
					tr+="<input type='button' value='退换' onclick='goods_exchange(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("supplier_unique",maps[i].supplier_unique);
					$("#goods_list").append($tr);
				}
				order();
				//
			}
			if(result.status==1){
				alert(result.msg);
			}
			var timec=new Date().getTime();
		},
		error:function(){
			alert("查询失败,请稍后重试");
		}
	});
}
//删除商品
function deleteGoods(btn){
	var flag=confirm("确定删除商品？");
	if(!flag){
		return;
	};
	var goods_barcode=$(btn).parent().parent().children().eq(2).html();
	$.ajax({
		url:"goods/deleteGoods.do",
		type:"post",
		data:{"goods_barcode":goods_barcode,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
			}
			if(result.status==1){
				alert(result.msg)	
			}
			findGoods();
		},
		error:function(){
			alert("删除失败，请检查网络!");
		}
	});
}
//排序
function order(){
	var $goods=$("#goods_list").children();
	for(var i=0;i<$goods.length;i++){
		$goods.eq(i).children().eq(0).html(i+1);
	}
}
//采购商品
function goods_purchase(btn){
	$("#goodsToExchange").empty();
	var $tds=$(btn).parents("tr").children();
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	var tr="<tr><td>"+$tds.eq(2).text()+"</td><td>"+$tds.eq(3).text()+"</td><td>";
	if($tds.eq(9).text()==null||$tds.eq(9).text()==""||$tds.eq(9).text()=="未定价"){
		alert("请选选择商品供应商！");
		return;
	}else{
	}
	tr+=$tds.eq(9).text()+"</td><td>";
	tr+="<input type='button' value='-' onclick='reduce(this)'><input type='text' value='1' onblur='test(this)'>";
	tr+="<input type='button' value='+' onclick='addCount(this)'></td><td>" ;
	tr+=$tds.eq(9).text()+"</td><td>";
	tr+="<input type='button' value='添加到订单' onclick='addToCart(this)'></td></tr>";
	$tr=$(tr);
	$tr.data("supplier_unique",supplier_unique);
	$("#goodsToCart").empty();
	$("#goodsToCart").append($tr);
	$(".aindex").show();
	$(".goodsLoad").show();
}
//将商品添加到数据库
function addToCart(btn){
	console.log("添加");
	var $trs=$(btn).parents("tr").children();
	var goods_barcode=$trs.eq(0).html();
	var purchase_list_detail_count=$trs.eq(3).children().eq(1).val();
	var purchase_list_detail_total=$trs.eq(4).html();
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	var purchase_list_total=0;
	$.ajax({
		url:"/shopmanagert/html/exchange/purchase/addToCart.do",
		type:"post",
		data:{"goods_barcode":goods_barcode,"purchase_list_detail_count":purchase_list_detail_count,"purchase_list_detail_total":purchase_list_total,"supplier_unique":supplier_unique,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			$(".aindex").css("display","none");
			$(".goodsLoad").css("display","none");
			window.parent.findPurCartGoods();
		},
		error:function(){
			alert("添加商品到购物车失败!");
		}
	});
}
//修改商品数量后，修改商品合计金额
function total(){
	var $tds=$("#goodsToCart").children().eq(0).children();
	$tds.eq(4).html($tds.eq(2).html()*$tds.eq(3).children().eq(1).val());
}

//数量输入框修改数据后，光标离开测试非空、非数字、非负数,计算合计
function test(text){
	var count=$(text).val();
	if(count==""||count==null||isNaN(count)||count<=0){
		$(text).addClass("warningtd");
	}else{
		$(text).removeClass("warningtd");
	}
	total();
}

//默认供应商按钮，跳转至默认供应商界面
function supplierChange(){
	$("#goodsMessage").empty();
	$(".public").css("display","none");
	$(".default_supplier").show();
	var goods_barcode=$("#goods_barcode").val().trim();
	var goods_name=$("#goods_name").val().trim();
	var supplier_name=$("#supplier_name").val().trim();
	var supplier_unique=$("#supplier_name").data("supplier_unique");
	if(supplier_name!=null){
		var tr="<tr><th>"+goods_barcode+"</th><th>"+goods_name+"</th><th>"+supplier_name+"</th></tr>";
	}else{
		var tr="<tr><th>"+goods_barcode+"</th><th>"+goods_name+"</th><th></th></tr>";
	}
	$tr=$(tr);
	$tr.data("supplier_unique",supplier_unique);
	$("#goodsMessage").append($tr);
	//此时，有商品条码信息，可以查询店铺area_dict_num,通过此来确认商品供应商信息可否
	//测试
	var area_dict_num=getCookie("area_dict_num");
	//通过店铺所在县区,查询其所在省市
	findPC(area_dict_num);
}

//根据店铺所选区县及商品条码，查询该区区县内该商品的供货商
function findGoodsSuppliers(data){
	var goods_barcode=$("#goodsMessage").children().eq(0).children().eq(0).html().trim();
	if(data==null){
		var area_dict_num=$("#contries option:selected").val();
	}else{
		var area_dict_num=data[0].area_dict_num;
		console.log(area_dict_num);
	}
	
	$.ajax({
		url:"goods/findGoodsSups.do",
		type:"post",
		data:{"goods_barcode":goods_barcode,"area_dict_num":area_dict_num},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#suppliers").empty();
				var suppliers=result.data;
				var option="<option value='0'>--请选择店铺--</option>";
				$("#suppliers").append(option);
				for(var i=0;i<suppliers.length;i++){
					var option="<option value='"+suppliers[i].supplier_unique+"'>"+suppliers[i].supplier_name+"</option>";
					$("#suppliers").append(option);
				}
			}
			if(result.status==1){
				$("#suppliers").empty();
				var option="<option value='0'>--请选择店铺--</option>";
				$("#suppliers").append(option);
			}
		},
		error:function(){
			alert("查询供货商信息失败！请检查网络！");
		}
	});
}
//点击设置按钮，将修改后的默认供应商修改
function updateGoodsSupplier(){
	var goods_barcode=$("#goodsMessage").children().eq(0).children().eq(0).html().trim();
	var supplier_unique=$("#suppliers option:selected").val().trim();
	if(supplier_unique==0){
		$(".public").css("display","none");
		$(".goods_modify").show();
		alert("未修改！");
		return;
	}
	$(".public").css("display","none");
	$(".goods_modify").show();
	$("#supplier_name").val($("#suppliers option:selected").html().trim());
	$("#supplier_name").data("supplier_unique",supplier_unique);
}

//进入商品更新界面，加载完商品大类后，自动加载商品小类，并完成对应关系
function addNames(name){
	var parunique=$("#goods_kind_group option:selected").val();
	$.ajax({
		url:"goods_kind/inadd_goods_kind_name.do",
		type:"post",
		data:{"goods_kind_parunique":parunique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#goods_kind_unique").empty();
				var names=result.data;
				for(var i=0;i<names.length;i++){
					if(names[i].k==name.trim()){
						var option="<option value='"+names[i].goods_kind_unique+"' selected='selected'>"+names[i].k+"</option>";
						$("#goods_kind_unique").append(option);
					}else{
						var option="<option value='"+names[i].goods_kind_unique+"'>"+names[i].k+"</option>";
						$("#goods_kind_unique").append(option);
					}
				}
			}
			if(result.status==1){
				
			}
		},
		error:function(){
		}
	});
}
//货物的退换申请
function goods_exchange(btn){
	$("#goodsToExchange").empty();
	var $tds=$(btn).parents("tr").children();
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	var tr="<tr><td>"+$tds.eq(2).text()+"</td><td>"+$tds.eq(3).text()+"</td><td>";
	if(supplier_unique=null){
		alert("商品没有默认供应商！");
		return;
	}
	tr+="<input type='button' value='-' onclick='reduce(this)'><input type='text' value='1' onblur='test(this)'>";
	tr+="<input type='button' value='+' onclick='addCount(this)'></td><td>" ;
	tr+="<input type='button' value='添加到换货' onclick='addToExchange(this)'></td></tr>";
	$tr=$(tr);
	$tr.data("supplier_unique",supplier_unique);
	$("#goodsToExchange").append($tr);
	$(".aindex").show();
	$(".goodsExchange").show();
}
//将产品添加到退换货订单
function addToExchange(btn){
	var $trs=$(btn).parents("tr").children();
	var goods_barcode=$trs.eq(0).html();
	var exchange_list_detail_count=$trs.eq(2).children().eq(1).val();
	var supplier_unique=$(btn).parents("tr").data("supplier_unique");
	var purchase_list_total=0;
	$.ajax({
		url:"html/exchange/purchase/addToExchange.do",
		type:"post",
		data:{"goods_barcode":goods_barcode,"exchange_list_detail_count":exchange_list_detail_count,"supplier_unique":supplier_unique,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			$(".aindex").css("display","none");
			$(".goodsExchange").css("display","none");
		},
		error:function(){
			alert("添加商品到购物车失败!");
		}
	});
}

//根据店铺所在的区县编号查询其所在的省市编号及名称
function findPC(area_dict_num){
	$.ajax({
		url:"/shopmanager/area_dict/findPC.do",
		type:"post",
		data:{"area_dict_num":area_dict_num},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				alert(result.msg);
			}
			if(result.status==0){
				var data=result.data;
				changeProvince(data);
				changeContry(data);
			}
		},
		error:function(){
			alert("查询店铺所在省市异常，请稍后重试！");
		}
	});
}

//根据查询到的省市信息，修改相应的省市区县信息，并查询满足条件的店铺
function changeProvince(data){
	var area_dict_num=data[0].pnum;
	var $provinces=$("#provinces").children();
	for(var i=0;i<$provinces.length;i++){
		if($provinces.eq(i).val()==area_dict_num){//根据传入的数据省份，调整省份
			$provinces.eq(i).attr("selected",true);
			changeCity(data);
		}
	}
}
//根据查询到的省市信息，修改相应的市级信息
function changeCity(data){
	var parea_dict_num=data[0].pnum;
	$.ajax({
		url:"/shopmanager/area_dict/find_cities.do",
		type:"post",
		data:{"area_dict_num":parea_dict_num},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var cities=result.data;
				for(i=0;i<cities.length;i++){
					if(cities[i].area_dict_num==data[0].cnum){
						var option="<option value='"+cities[i].area_dict_num+"' selected='selected'>"+cities[i].area_dict_content+"</option>";
						$("#cities").append(option);
					}else{
						var option="<option value='"+cities[i].area_dict_num+"'>"+cities[i].area_dict_content+"</option>;"
						$("#cities").append(option);
					}
				}
			}
		},
		error:function(){
			alert("查询失败！");
		}
	});
}

//修改相应的区县信息
function changeContry(data){
	var parea_dict_num=data[0].cnum;
	$.ajax({
		url:"/shopmanager/area_dict/find_contries.do",
		type:"post",
		data:{"area_dict_num":parea_dict_num},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var contries=result.data;
				for(var i=0;i<contries.length;i++){
					if(contries[i].area_dict_num==data[0].area_dict_num){
						var option="<option value='"+contries[i].area_dict_num+"' selected='selected'>"+contries[i].area_dict_content+"</option>";
						$("#contries").append(option);
					}else{
						var option="<option value='"+contries[i].area_dict_num+"'>"+contries[i].area_dict_content+"</option>";
						$("#contries").append(option);
					}//结束If判定
				}//结束for循环
				findGoodsSuppliers();
			}//结束if(result.status==0)判定
		},
		error:function(){
			alert("查询区县信息失败！请稍后重试！");
		}
	});
}
//点击取消修改按钮，取消默认供应商的修改
function cancleSet(){
	$(".public").css("display","none");
	$(".goods_modify").show();
}
//修改商品条码后，自动填充相应商品信息
function fillGoodsMessage(){
	console.log("修改商品信息！");
	var goods_barcode=$("#goods_barcode").val().trim();
	console.log(goods_barcode);
	$.ajax({
		url:"goods/findGood.do",
		type:"post",
		data:{"goods_barcode":goods_barcode},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goods=result.data;
				console.log(goods);
				$("#goods_id").html(goods.goods_id);
				$("#goods_name").val(goods.goods_name);
				$("#goods_brand").val(goods.goods_brand);
				$("#goods_barcode").val(goods.goods_barcode);
				$("#goods_alias").val(goods.goods_alias);
				$("#goods_sale_price").val(goods.goods_sale_price);
				$("#goods_price").val(goods.goods_price);
				$("#goods_life").val(goods.goods_life);
				$("#goods_points").val(goods.goods_points);
				$("#goods_address").val(goods.goods_address);
				$("#goods_contain").val(goods.goods_contain);
				$("#goods_standard").val(goods.goods_standard);
				$("#goods_count").val(goods.goods_count);
				$("#supplier_name").val(goods.supplier_name);
				$("#supplier_name").data("supplier_unique",goods.supplier_unique);
				var $groups=$("#goods_kind_group").children();
				var name=goods.name;
				for(var i=0;i<$groups.length;i++){
					if($groups.eq(i).text().trim()==goods.goods_kind_name){
						$groups[i].selected=true;
						addNames(name);
					}
				}
			}
			$("#goods_sale_price").focus();
		},
		error:function(){
			alert("进入产品修改页面失败，请稍后重试");
		}
	});
	$(".showarea").css("display","none");
	$(".goods_modify").css("display","none");
	$(".goods_new").show();
}
