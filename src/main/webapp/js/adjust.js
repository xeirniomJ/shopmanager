//查询满足条件的调货单
function adjust_find(){
	var adjust_type=$("#adjust_type option:selected").val();
	var adjust_statue=$("#adjust_statue option:selected").val();
	var adjust_handlestatue=$("#adjust_handlestatue option:selected").val();
	addCookie("adjust_type", adjust_type, 10);
	addCookie("adjust_statue",adjust_statue, 10);
	addCookie("adjust_handlestatue", adjust_handlestatue, 10);
	$.ajax({
		url:"adjust/adjust_find.do",
		type:"post",
		data:{"adjust_statue":adjust_statue,"shop_unique":shop_unique,"adjust_handlestatue":adjust_handlestatue,"adjust_type":adjust_type,"manager_unique":getCookie("manager_unique")},
		datType:"json",
		success:function(result){
			if(result.status==0){
				$("#adjusts").empty();
				var adjusts=result.data;
				for(var i=0;i<adjusts.length-1;i++){
					var time=new Date(adjusts[i].adjust_datetime);
					var tr="<tr><td class='public' id='adjust_id'>"+adjusts[i].adjust_id+"</td><td></td><td>"+time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate()+"</td><td>";
					var validity=new Date(adjusts[i].adjust_validity);
					tr+=validity.getFullYear()+"-"+(validity.getMonth()+1)+"-"+validity.getDate()+"</td><td>";
					var type=adjusts[i].adjust_type;
					if(type==1){
						tr+="转售</td><td>";
					}else{
						tr+="采购</td><td>";
					}
					tr+=adjusts[i].adjust_total+"</td><td>";
					tr+=adjusts[i].goods_name+"</td><td>";
					tr+=adjusts[i].adjust_count+"</td><td>";
					tr+=adjusts[i].adjust_price+"</td><td>";
					var statue=adjusts[i].adjust_statue;
					if(statue==1){
						tr+="新申请</td><td>";
					}else if(statue==2){
						tr+="已响应</td><td>";
					}
					var shop_name=adjusts[i].shop_name;
					
					if(shop_name!=null){
						tr+=shop_name+"</td><td>";
						tr+=adjusts[i].shop_phone+"</td><td>";
					}else{
						tr+="</td><td>";
						tr+="</td><td>";
					}
					var handlestatue=adjusts[i].adjust_handlestatue;
					if(handlestatue==1){
						tr+="未发货</td><td>";
					}else if(handlestatue==2){
						tr+="已收货</td><td>";
					}
						tr+="<input type='button' value='取消申请' onclick='deleteadjust(this)'></td></tr>";
					$("#adjusts").append(tr);
				}
				$(".tcdPageCode").createPage({
					pageCount:adjusts[adjusts.length-1].page_num ,
					current:1,
					backFn:function(p){
						adjust_find_page(p-1)
					}
				});
				order();
			}
		},
		error:function(){
			alert("查询失败");
		}
	});
}
//根据选择的页数，查询相应的调货单
function adjust_find_page(page_num){
	var adjust_statue=getCookie("adjust_statue");
	var adjust_handlestatue=getCookie("adjust_handlestatue");
	var adjust_type=getCookie("adjust_type");
	$.ajax({
		url:"adjust/adjust_find.do",
		type:"post",
		data:{"adjust_statue":adjust_statue,"shop_unique":shop_unique,"adjust_handlestatue":adjust_handlestatue,"adjust_type":adjust_type,"page_num":page_num},
		datType:"json",
		success:function(result){
			if(result.status==0){
				$("#adjusts").empty();
				var adjusts=result.data;
				for(var i=0;i<adjusts.length-1;i++){
					var time=new Date(adjusts[i].adjust_datetime);
					var tr="<tr><td class='public' id='adjust_id'>"+adjusts[i].adjust_id+"</td><td></td><td>"+time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate()+"</td><td>";
					var validity=new Date(adjusts[i].adjust_validity);
					tr+=validity.getFullYear()+"-"+(validity.getMonth()+1)+"-"+validity.getDate()+"</td><td>";
					var type=adjusts[i].adjust_type;
					if(type==1){
						tr+="转售</td><td>";
					}else{
						tr+="采购</td><td>";
					}
					tr+=adjusts[i].adjust_total+"</td><td>";
					tr+=adjusts[i].goods_name+"</td><td>";
					tr+=adjusts[i].adjust_count+"</td><td>";
					tr+=adjusts[i].adjust_price+"</td><td>";
					var statue=adjusts[i].adjust_statue;
					if(statue==1){
						tr+="新申请</td><td>";
					}else if(statue==2){
						tr+="已响应</td><td>";
					}
					var shop_name=adjusts[i].shop_name;
					
					if(shop_name!=null){
						tr+=shop_name+"</td><td>";
						tr+=adjusts[i].shop_phone+"</td><td>";
					}else{
						tr+="</td><td>";
						tr+="</td><td>";
					}
					var handlestatue=adjusts[i].adjust_handlestatue;
					if(handlestatue==1){
						tr+="未发货</td><td>";
					}else if(handlestatue==2){
						tr+="已收货</td><td>";
					}
						tr+="<input type='button' value='取消申请' onclick='deleteadjust(this)'></td></tr>";
					$("#adjusts").append(tr);
				}
				order();
			}
		},
		error:function(){
			alert("查询失败");
		}
	});
	
}
//排序
function order(){
	var $trs=$("#adjusts").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(1).html(i+1);
	}
}

//自动添加响应状态
function adjust_statue_find(){
	$.ajax({
		url:"adjust/adjust_statue_find.do",
		type:"get",
		datType:"json",
		success:function(result){
			if(result.status==0){
				var statues=result.data;
				for(var i=0;i<statues.length;i++){
					var statue=statues[i].adjust_statue;
					if(statue==1){
						var option="<option value="+statue+">未响应</option>";
					}else if(statue==2){
						var option="<option value="+statue+">已响应</option>";
					}
					$("#adjust_statue").append(option);
					$("#nadjust_statue").append(option);
				}
			}
		},
		error:function(){
			
		}
	});
}
//取消申请单
function deleteadjust(btn){
	var adjust_statue=$(btn).parent().parent().children().eq(9).html();
	if(adjust_statue=="已响应"){
		alert("已被响应，无法删除");
		return;
	}
	var adjust_id=$(btn).parent().parent().children().eq(0).html();
	console.log(adjust_id);
	$.ajax({
		url:"adjust/adjust_cancel.do",
		type:"post",
		data:{"adjust_id":adjust_id},
		datType:"json",
		success:function(result){
			if(result.status==0){
				$("#warning").html(result.msg);
				$("#warnings").fadeIn(1000).fadeOut(1000);
				$("#adjust_find").click();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("该订单已被修改，请重新确认");
			$("#adjust_find").click();
		}
	});
}



//自动添加发货（处理）状态
function adjust_handlestatue_find(){
	$.ajax({
		url:"adjust/adjust_handlestatue_find.do",
		type:"get",
		datType:"json",
		success:function(result){
			if(result.status==0){
				var hstatues=result.data;
				for(var i=0;i<hstatues.length;i++){
					var hstatue=hstatues[i].adjust_handlestatue;
					if(hstatue==1){
						var option="<option value="+hstatue+">未发货</option>";
					}else if(hstatue==2){
						var option="<option value="+hstatue+">已收货</option>";
					}
					$("#adjust_handlestatue").append(option);
				}
			}
		},
		error:function(){
			alert("网络异常");
		}
	});
}

//点击添加新申请，显示添加页面
function adjust_new(){
	$(".main").css("display","none");
	$(".adjust_new").show();
}
//单击减号，减少商品数量
function reduce(){
	var nadjust_count=$("#nadjust_count").val().trim();
	if(nadjust_count<=0){
		return;
	}
	nadjust_count=nadjust_count-1;
	if(nadjust_count<0){
		nadjust_count=0;
	}
	$("#nadjust_count").val(nadjust_count.toFixed(2));
}
//单击加号，增加商品数量
function addcount(){
	var nadjust_count=$("#nadjust_count").val().trim();
	$("#nadjust_count").val((nadjust_count*1+1).toFixed(2));
}

//提交新申请
function adjust_new_save(){
	var goods_barcode=$("#ngoods_barcode").val().trim();
	var adjust_type=$("#nadjust_type option:selected").val();
	var adjust_count=$("#nadjust_count").val().trim();
	var adjust_price=$("#nadjust_price").val();
	var adjust_validity=$("#nadjust_validity").val().trim();
	var goods_name=$("#ngoods_name").val();
	var adjust_remark=$("#nadjust_remark").val();
	if(adjust_count<=0||adjust_count==0){
		$("#warning").html("商品数量必须大于0");
		$("#warnings").fadeIn(500).fadeOut(500);
		return;
	}
	if(adjust_price<=0||adjust_price==0){
		$("#warning").html("价格不能为零或负数");
		$("#warnings").fadeIn(500).fadeOut(500);
		return;
	}
	if(goods_barcode==null||goods_barcode==""){
		$("#warning").html("请至少选择一种商品！");
		$("#warnings").fadeIn(500).fadeOut(500);
		return;
	}
	if(adjust_validity==null||adjust_validity==""){
		var date=new Date();
		adjust_validity=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+3);
	}
	if(new Date(adjust_validity)<new Date()){
		$("#warning").html("有效期不能早于当前时间");
		$("#warnings").fadeIn(500).fadeOut(500);
		return;
	}
	$.ajax({
		url:"adjust/adjust_new_save.do",
		type:"post",
		data:{"adjust_type":adjust_type,"goods_barcode":goods_barcode,"adjust_count":adjust_count,"adjust_remark":adjust_remark,
			"adjust_price":adjust_price,"adjust_validity":adjust_validity,"goods_name":goods_name,"shop_unique":shop_unique},
		datType:"json",
		success:function(result){
			if(result.status==0){
				$("#warning").html(result.msg);
				$("#warnings").fadeIn(500).fadeOut(500);
				$("#ngoods_barcode").val("");
				$("#nadjust_count").val(0);
				$("#nadjust_price").val("");
				$("#ngoods_name").val("");
				$(".public").css("display","none");
				$(".main").show();
			}
			if(result.status==1){
				$("#warning").html(result.msg);
				$("#warnings").fadeIn(500).fadeOut(500);
			}
		},
		error:function(){
			alert("添加失败");
		}
	});
}
//单击取消
function canceladjust(){
	$("#ngoods_barcode").val("");
	$("#nadjust_count").val("0");
	$("#nadjust_price").val("");
	$("#ngoods_name").val("");
	$(".public").css("display","none");
	$(".main").show();
}
//单击弹出产品选择页面
function choic(){
	$("#good_barcode_choic").load("alert/goods_barcode_choic.html");
	$("#abc").show();
	$("#good_barcode_choic").show();
}

//单击关闭按钮，关闭弹出页面
function closeoff(){
	$("#good_barcode_choic").css("display","none");
	$("#abc").css("display","none");
}

