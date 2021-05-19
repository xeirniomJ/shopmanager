//加载页面后，自动填充商品大类
function add_goods_kind_group(){
	$.ajax({
		url:"/shopmanager/goods_kind/add_goods_kind_group.do",
		type:"post",
		data:{"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			$("#goods_kind_group").empty();
			$("#ingoods_kind_group").empty();
			if(result.status==0){
				var groups=result.data;
				var a="<option value=0>请选择商品大类</option>";
				$("#ingoods_kind_group").append(a);
				$("#goods_kind_group").append(a);
				for(var i=0;i<groups.length;i++){
					var option="<option value="+groups[i].goods_kind_unique+">"+groups[i].goods_kind_name+"</option>";
					$("#ingoods_kind_group").append(option);
					$("#goods_kind_group").append(option);
				}
			}
		},
		error:function(){
			alert("加载商品大类型失败，请检查网络");
		}
	});
}
//加载页面时，自动添加商品小类
function add_goods_kind_name(){
		$.ajax({
			url:"/shopmanager/goods_kind/addGoodsKindNames.do",
			type:"post",
			data:{"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
			dataType:"json",
			success:function(result){
				$("#ingoods_kind_name").empty();
				var option="<option value=0>请选择商品小类</option>";
				$("#ingoods_kind_name").append(option);
				$("#goods_kind_unique").append(option);
				if(result.status==0){
					var names=result.data;
					for(var i=0;i<names.length;i++){
						var option ="<option value='"+names[i].goods_kind_unique+"'>"+names[i].n+"</option>";
						$("#goods_kind_unique").append(option);
					}
				}
			},
			error:function(){
				alert("加载商品小类失败！");
			}
		});
			
}
//修改大类后，自动修改小类填充
function changegroup(){
	var goods_kind_parunique=$("#ingoods_kind_group option:selected").val();
	$("#ingoods_kind_name").empty();
	var option="<option value=0>请选择商品小类</option>";
	$("#ingoods_kind_name").append(option);
	$("#goods_kind_name").append(option);
	if(goods_kind_group=="请选择商品大类"){
		add_goods_kind_name();
		return;
	}
	$.ajax({
		url:"/shopmanager/goods_kind/inadd_goods_kind_name.do",
		type:"post",
		data:{"goods_kind_parunique":goods_kind_parunique,"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#ingoods_kind_name").empty() ;
				$("#goods_kind_name").empty();
				var option="<option value=0>请选择商品小类</option>";
				$("#ingoods_kind_name").append(option);
				$("#goods_kind_name").append(option);
				var groups=result.data;
				for(var i=0;i<groups.length;i++){
					var option="<option value="+groups[i].goods_kind_unique+">"+groups[i].k+"</option>";
					$("#ingoods_kind_name").append(option);
					$("#goods_kind_name").append(option);
				}
			}
		},
		error:function(){
			alert("加载商品大类型失败，请检查网络");
		}
	});
}
//修改页面自动填充小类
function chgroup(){
	var ops=$(this).children();
	for(var i=0;i<ops.length;i++){
		if(ops[i].selected){
			var goods_kind_parunique=$(ops[i]).val();
		}
	}
	$("#ingoods_kind_name").empty();
	$("#goods_kind_unique").empty();
	var option="<option value=0>请选择商品小类</option>";
	$("#goods_kind_unique").append(option);
	$("#goods_kind_unique").append(option);
	if(goods_kind_group=="请选择商品大类"){
		add_goods_kind_name();
		return;
	}
	$.ajax({
		url:"/shopmanager/goods_kind/inadd_goods_kind_name.do",
		type:"post",
		data:{"goods_kind_parunique":goods_kind_parunique,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			
			if(result.status==0){
				$("#ingoods_kind_name").empty() ;
				$("#goods_kind_name").empty();
				$("#goods_kind_unique").empty();
				var option="<option value=0>请选择商品小类</option>";
				$("#ingoods_kind_name").append(option);
				$("#goods_kind_name").append(option);
				$("#goods_kind_unique").append(option);
				var groups=result.data;
				for(var i=0;i<groups.length;i++){
					var option="<option value="+groups[i].goods_kind_unique+">"+groups[i].k+"</option>";
					$("#goods_kind_unique").append(option);
					$("#ingoods_kind_name").append(option);
				}
			}
		},
		error:function(){
			alert("加载商品小类型失败，请检查网络");
		}
	});
}
//自动添加申请类型状态
function adjust_type_find(){
	$.ajax({
		url:"adjust/adjust_type_find.do",
		type:"get",
		datType:"json",
		success:function(result){
			if(result.status==0){
				var types=result.data;
				for(var i=0;i<types.length;i++){
					var type=types[i].adjust_type;
					if(type==1){
						var option="<option value="+type+">转售</option>";
					}else if(type==2){
						var option="<option value="+type+">采购</option>";
					}
					$("#adjust_type").append(option);
					$("#nadjust_type").append(option);
				}
			}
		},
		error:function(){
			alert("网络异常，请稍后重试");
		}
	});
}
//点击关闭按钮，关闭弹出页面
function pageClose(){
	$(this).parent().parent().css("display","none");
	$(".aindex").css("display","none");
}
//单击减号，修改商品的数量
function reduce(btn){
	var num=$(btn).parent().children().eq(1).val();
	if(num<=1){
		return;
	}else{
		num--;
	}
	$(btn).parent().children().eq(1).val(num);
	if($(btn).parent().parent().children().length==8){
		purchaseTotal();
	}
	total();
}
//单击加号按钮，增加商品数量
//单击加号，增加商品数量
function addCount(btn){
	var num=$(btn).parent().children().eq(1).val();
	$(btn).parent().children().eq(1).val(++num);
	if($(btn).parent().parent().children().length==8){
		purchaseTotal();
	}
	total();
}

//自动加载省份
function loadProvince(){
	$.ajax({
		url:"/shopmanager/area_dict/find_provinces.do",
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var provinces=result.data;
				$("#provinces").empty();
				var option="<option value=0>--请选择省份--</option>";
				$("#provinces").append(option);
				for(var i=0;i<provinces.length;i++){
					var option="<option value='"+provinces[i].area_dict_num+"'>"+provinces[i].area_dict_content+"</option>";
					$("#provinces").append(option);
				}
			}
		},
		error:function(){
			alert("加载省份失败，请检查网络！");
		}
	});
}
//省份修改后，自动修改城市列表
function loadCities(){
	var area_dict_num=$("#provinces option:selected").val().trim();
	$.ajax({
		url:"/shopmanager/area_dict/find_cities.do",
		type:"post",
		data:{"area_dict_num":area_dict_num},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#cities").empty();
				var cities=result.data;
				var option="<option value='0'>--请选择城市--</option>";
				$("#cities").append(option);
				for(var i=0;i<cities.length;i++){
					var option="<option value='"+cities[i].area_dict_num+"'>"+cities[i].area_dict_content+"</option>";
					$("#cities").append(option);
				}
			}
		},
		error:function(){
			alert("加载城市列表失败，请检查网络!");
		}
	});
}
//城市修改后，自动修改区县列表
function loadContries(){
	var area_dict_num=$("#cities option:selected").val().trim();
	$.ajax({
		url:"/shopmanager/area_dict/find_contries.do",
		type:"post",
		data:{"area_dict_num":area_dict_num},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var contries=result.data;
				$("#contries").empty();
				var option="<option value='0'>--请选择区县--</option>";
				$("#contries").append(option);
				for(var i=0;i<contries.length;i++){
					var option="<option value='"+contries[i].area_dict_num+"'>"+contries[i].area_dict_content+"</option>";
					$("#contries").append(option);
				}
			}
			
		},
		error:function(){
			alert("加载城市列表失败，请检查网络!");
		}
	});
}

//加载订单付款状况
function loadSaleListState(){
	$.ajax({
		url:"saleList/loadSaleListState.do",
		dataType:"json",
		type:"post",
		success:function(result){
			if(result.status==0){
				var statues=result.data;
				for(var i=0;i<statues.length;i++){
					var option=null;
					if(statues[i].sale_list_state==1){
						option="<option value='"+statues[i].sale_list_state+"'>货到付款未付款</option>";
					}else if(statues[i].sale_list_state==2){
						option="<option value='"+statues[i].sale_list_state+"'>网上订单未付款</option>";
					}else if(statues[i].sale_list_state==3){
						option="<option value='"+statues[i].sale_list_state+"'>已付款</option>";
					}else{
						option="<option value='0'>异常订单，请联系开发人员</option>";
					}
					$("#sale_list_state").append(option);
				}
			}
		},
		error:function(){
			alert("加载付款状态失败！");
		}
	})
}
//加载订单处理状态
function loadSaleListHandleState(){
	$.ajax({
		url:"saleList/loadSaleListHandleState.do",
		dataType:"json",
		type:"post",
		success:function(result){
			if(result.status==0){
				var hstatues=result.data;
				for(var i=0;i<hstatues.length;i++){
					var option=null;
					if(hstatues[i].sale_list_handlestate==1){
						option="<option value='"+hstatues[i].sale_list_handlestate+"'>无效订单</option>";
					}else if(hstatues[i].sale_list_handlestate==2){
						option="<option value='"+hstatues[i].sale_list_handlestate+"'>未发货</option>";
					}else if(hstatues[i].sale_list_handlestate==3){
						option="<option value='"+hstatues[i].sale_list_handlestate+"'>已发货</option>";
					}else if(hstatues[i].sale_list_handlestate==4){
						option="<option value='"+hstatues[i].sale_list_handlestate+"'>已收货</option>";
					}else if(hstatues[i].sale_list_handlestate==5){
						option="<option value='"+hstatues[i].sale_list_handlestate+"'>已收货，未付款</option>";
					}else{
						option="<option value='0'>异常订单，请联系开发人员</option>";
					}
					$("#sale_list_handlestate").append(option);
				}
				findSaleListDetail();
			}
		},
		error:function(){
			alert("查询发货情况失败，请检查网络！");
		}
	})
}

//加载页面时，自动加载商品大类
function addGroup(){
	$.ajax({
		url:"/shopmanager/goods_kind/addGoodsGroups.do",
		type:"post",
		data:{"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$(".goods_kind_parunique").empty();
				var option="<option value='0'>--请选择商品大类--</option>";
				$(".goods_kind_parunique").append(option);
				var groups=result.data;
				for(var i=0;i<groups.length;i++){
					var option="<option value='"+groups[i].goods_kind_unique+"'>"+groups[i].goods_kind_name+"</option>";
					$(".goods_kind_parunique").append(option);
				}
			}
		},
		error:function(){
			alert("查询商品大类失败，请检查网络")
		}
	});	
}

//商品大类更新后，加载商品小类
function addNames(){
	var goods_kind_parunique=$("select option:checked").val();
	if(goods_kind_parunique==0){
		$(".goods_kind_unique").empty();
		var option="<option value='0'>--请选择商品小类--</option>";
		$(".goods_kind_unique").append(option);
		return;
	}
	$.ajax({
			url:"/shopmanager/goods_kind/addGoodsNames.do",
			type:"post",
			data:{"goods_kind_parunique":goods_kind_parunique},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var names=result.data;
					$(".goods_kind_unique").empty();
					var option="<option value='0'>--请选择商品小类--</option>";
					$(".goods_kind_unique").append(option);
					for(var i=0;i<names.length;i++){
						var  option="<option value='"+names[i].goods_kind_unique+"'>"+names[i].k+"</option>";
						$(".goods_kind_unique").append(option);
					}
				}
			},
			error:function(){
				alert("查询商品小类失败！请检查网络！");
			}
	});
}

//检测图片大小，防止上传商品图片过大
function checkSize(file){
	var maxSize=$("#maxSize").val();
	if(file.files[0].size>maxSize){
		alert("上传的图片尺寸过大！");
	}
	console.log(file.files[0].length);
	console.log(file.files[0].size);
	console.log("最大上传尺寸为："+maxSize);
}

//自动从公共库里加载所有商品大类
function addFristLevel(){
	$.ajax({
		url:"/shopmanager/goods_kind/addFristLevel.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var datas=result.data;
				$("#fristLevel").empty();
				var option="<option value='0'>--请选择一级分类--</option>";
				$("#fristLevel").append(option);
				for(var i=0;i<datas.length;i++){
					var option="<option value='"+datas[i].goodsKindId+"'>"+datas[i].goodsKindName+"</option>";
					$("#fristLevel").append(option);
				}
			}
		},
		error:function(){
			alert("查询商品分类失败！请稍后重试！");
		}
	});
}

//当一级分类发生变化时，自动修改二级分类信息
function addSecLevel(){
	var goodsKindPid=$("#fristLevel option:selected").val().trim();
	$("#secLevel").empty();
	var option ="<option value='0'>--请选择二级分类--</option>";
	$("#secLevel").append(option);//一级分类修改后，二级分类初始化
	$("#thridLevel").empty();
	var option="<option value='0'>--请选择三级分类--</option>";
	$("#thridLevel").append(option);//一级分类修改后，三级分类初始化
	if(goodsKindPid==0){
		return;
	}
	$.ajax({
		url:"/shopmanager/goods_kind/addSecLevel.do",
		type:"post",
		data:{"goodsKindPid":goodsKindPid},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var secLevels=result.data;
				for(var i=0;i<secLevels.length;i++){
					var option="<option value='"+secLevels[i].goodsKindId+"'>"+secLevels[i].goodsKindName+"</option>";
					$("#secLevel").append(option);
				};//添加商品二级分类结束
			}else if(result.status==1){
				
			}
		},
		error:function(){
			alert("查询商品二级分类异常，请稍后重试！");
		}
	});
}//方法结束

//根据二级分类信息，自动添加三类商品分级信息
function addThridLevel(){
	var goodsKindPid=$("#secLevel option:selected").val().trim();
	$("#thridLevel").empty();
	var option="<option value='0'>--请选择三级分类--</option>";
	$("#thridLevel").append(option);//二级商品分类修改后，三级商品分类初始化
	if(goodsKindPid==0){
		return;
	}
	$.ajax({
		url:"/shopmanager/goods_kind/addThridLevel.do",
		type:"post",
		data:{"goodsKindPid":goodsKindPid},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				alert("没有满足条件的三级商品分类信息！");
			}
			if(result.status==0){
				var thridLevels=result.data;
				for(var i=0;i<thridLevels.length;i++){
					var option="<option value='"+thridLevels[i].goodsKindId+"'>"+thridLevels[i].goodsKindName+"</option>";
					$("#thridLevel").append(option);
				}
			}
		},
		error:function(){
			alert("查询信息失败！");
		}
	});
}//二级商品分类修改后，修改三级商品分类信息方法结束

//页面加载后，自动为页面添加所有一级商品分类信息
function addFLevels(){
	$.ajax({
		url:"/shopmanager/goods_kind/addFristLevel.do",
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var datas=result.data;
				$("#fristLevels").empty();
				for(var i=0;i<datas.length;i++){
					var option="<option value='"+datas[i].goodsKindName+"'>"+datas[i].goodsKindName+"</option>";
					$("#fristLevels").append(option);
				}
			}
		},
		error:function(){
			alert("查询商品分类失败！请稍后重试！");
		}
	});
}
//页面加载后，自动为页面添加所有二级分类信息
function addSLevels(){
	$.ajax({
		url:"/shopmanager/goods_kind/addSLevels.do",
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status===0){
				var datas=result.data;
				for(var i=0;i<datas.length;i++){
					var option="<option value='"+datas[i].goodsKindName+"'>"+datas[i].goodsKindName+"</option>";
					$("#secLevels").append(option);
				}
			}
		},
		error:function(){
			console.log("查询二级商品分类信息错误！");
		}
	});
}

//页面加载后，自动为页面添加所有三级分类信息
function addTLevels(){
	$.ajax({
		url:"/shopmanager/goods_kind/addTLevels.do",
		type:"get",
		dataType:"json",
		success:function(result){
			var datas=result.data;
			for(var i=0;i<datas.length;i++){
				var option="<option value='"+datas[i].goodsKindName+"'>"+datas[i].goodsKindName+"</option>";
				$("#thridLevels").append(option);
			}
		},
		error:function(){
			console.log("查询二级商品分类信息错误！");
		}
	});
}

//根据县级编号，查询其对应的省、市编号
function queryPCAreaDictNum(area_dict_num){
	var area_dict_num=getCookie("area_dict_num");
	
}
//分页查询商品
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