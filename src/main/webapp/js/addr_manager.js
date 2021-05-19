/**
 * 单击客户查询，查询满足条件客户
 * 
 */
function customers_addrs_find(){
	var customermessage=$("#customermessage").val();
	$.ajax({
		url:"customer/findCusts.do",
		type:"post",
		data:{"customermessage":customermessage},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#cus_list").empty();
				var cuss=result.data;
				for(var i=0;i<cuss.length;i++){
					var tr="<tr><td></td><td>"+cuss[i].cus_id+"</td><td>";
					tr+=cuss[i].cus_name+"</td><td>";
					tr+=cuss[i].cus_phone+"</td><td>";
					tr+="<input type='button' value='管理送货地址' onclick='addr_manager(this)'></td></tr>";
					$("#cus_list").append(tr);
					order();
				}
				$("#cus_unique").val(cuss[0].cus_unique);
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("客户信息查询异常");
		}
	});
}
/**
 * 点击管理送货地址按钮，查询当前会员送货地址详情
 * @param btn
 */
function addr_manager(btn){
	var cus_id=$(btn).parent().parent().children().eq(1).html();
	$(".showarea").css("display","none");
	$(".addr_detail").show();
	$.ajax({
		url:"addr_manager/findAddr_details.do",
		type:"post",
		data:{"cus_id":cus_id},
		dataType:"json",
		success:function(result){
			$("#cus_message").empty();
			$("#addrs_details").empty();
			if(result.status==0){
				var addrs=result.data;
				$("#cus_unique").val(addrs[0].cus_unique);
				var htr="<tr><td>"+cus_id+"</td><td>";
				htr+=addrs[0].cus_name+"</td><td>";
				htr+=addrs[0].cus_phone+"</td></tr>";
				$("#cus_message").append(htr);
				for(var i=0;i<addrs.length;i++){
					if(addrs[i].addr_receiver_name==null){
					}else{
						var tr="<tr><td></td><td class='public'>"+addrs[i].addr_id+"</td><td>"+addrs[i].addr_receiver_name+"</td><td>";
						tr+=addrs[i].addr_phone+"</td><td>";
						tr+=addrs[i].addr_province+"</td><td>";
						tr+=addrs[i].addr_city+"</td><td>";
						tr+=addrs[i].addr_county+"</td><td>";
						tr+=addrs[i].addr_addr+"</td><td>";
						var defalt=addrs[i].addr_default;
						if(defalt=="1"){
							tr+="默认地址</td><td>";
						}else{
							tr+="非默认地址</td><td>";
						}
						tr+="<input type='button' value='更新信息' onclick='modify_addr_message(this)'></td></tr>"
						
						$("#addrs_details").append(tr);
					}
				}
				order1();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("管理客户送货地址信息异常;");
		}
	});
}

//排序
function order(){
	var $trs=$("#cus_list").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//单击返回上层按钮，会员送货地址，会员查询页面
function returnlist(){
	$(".showarea").css("display","none");
	$(".main").show();
}
//显示更新会员送货地址详情界面
function modify_addr_message(btn){
	var addr_id=$(btn).parent().parent().children().eq(1).html();
	$.ajax({
		url:"addr_manager/findAddr_detail.do",
		type:"post",
		data:{"addr_id":addr_id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var addr=result.data;
				$("#addr_id").html(addr.addr_id);
				$("#addr_receiver_name").val(addr.addr_receiver_name);
				$("#addr_phone").val(addr.addr_phone);
				$("#addr_county").val(addr.addr_county);
				$("#addr_addr").val(addr.addr_addr);
				var addr_default=addr.addr_default;
				var defoptions=$("#addr_default").children();
				for(var i=0;i<defoptions.length;i++){
					if(addr_default==i){
						defoptions[i].selected=true;
					}
				}
			}
		},
		error:function(){
			alert("查询送货地址详情失败，请稍后重试");
		}
	});
	$(".showarea").css("display","none");
	$(".addr_modify").show();
}

//更新送货地址页面，点击更新地址按钮，将信息保存到数据库
function addr_save(){
	var addr_id=$("#addr_id").html();
	var cus_unique=$("#cus_unique").val().trim();
	var addr_receiver_name=$("#addr_receiver_name").val().trim();
	var addr_phone=$("#addr_phone").val().trim();
	var addr_addr=$("#addr_addr").val().trim();
	var addr_province=$("#addr_province option:selected").val();
	var addr_city=$("#addr_city option:selected").val();
	var addr_county=$("#addr_counties option:selected").val();
	var addr_default=$("#addr_default option:selected").val();
	$.ajax({
		url:"addr_manager/updateAddr_detail.do",
		type:"post",
		data:{"addr_id":addr_id,"addr_receiver_name":addr_receiver_name,"addr_phone":addr_phone,"addr_addr":addr_addr,
			"addr_province":addr_province,"addr_city":addr_city,"addr_conuty":addr_county,"addr_default":addr_default,"cus_unique":cus_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$(".showarea").css("display","none");
				$(".main").show();
				customers_addrs_find();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("更新送货地址详情失败，请稍后重试");
		}
	});
}
//点击取消，返回上层
function cancel(){
	$(".showarea").css("display","none");
	$(".addr_detail").show();
}
//送货地址详情排序
function order1(){
	var $trs=$("#addrs_details").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//自动添加省份
function province(){
	$.ajax({
		url:"area_dict/find_provinces.do",
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var provinces=result.data;
				for(var i=0;i<provinces.length;i++){
					var op="<option>"+provinces[i].area_dict_content+"</option>";
					$("#addr_province").append(op);
				}
			}
		},
		error:function(){
			alert("省份加载异常");
		}
	});
}
//修改省份后，自动添加城市列表
function city1(){
	var area_dict_content=$("#addr_province option:selected").text();
	if(area_dict_content=="请选择省份"){
		return;
	}
	console.log(area_dict_content);
	$.ajax({
		url:"area_dict/find_cities.do",
		type:"post",
		data:{"area_dict_content":area_dict_content},
		dataType:"json",
		success:function(result){
			$("#addr_city").empty();
			var op="<option>请选择城市</option>";
			$("#addr_city").append(op);
			$("#addr_counties").empty();
			var op="<option>请选择区县</option>";
			$("#addr_counties").append(op);
			if(result.status==0){
				var cities=result.data;
				for(var i=0;i<cities.length;i++){
					var op="<option>"+cities[i].area_dict_content+"</option>";
					$("#addr_city").append(op);
				}
			}
		},
		error:function(){
			alert("城市加载异常");
		}
	});
}
//修改市后自动添加区县
function contries(){
	var area_dict_content=$("#addr_city option:selected").text();
	console.log(area_dict_content);
	$.ajax({
		url:"area_dict/find_cities.do",
		type:"post",
		data:{"area_dict_content":area_dict_content},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var cities=result.data;
				$("#addr_counties").empty();
				var op="<option>请选择区县</option>";
				$("#addr_counties").append(op);
				for(var i=0;i<cities.length;i++){
					var op="<option>"+cities[i].area_dict_content+"</option>";
					$("#addr_counties").append(op);
				}
			}
		},
		error:function(){
			alert("区县加载异常");
		}
	});
}