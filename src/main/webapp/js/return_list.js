//点击修改订单按钮，进入订单修改页面
function return_list_modify(btn){
	var ret_list_unique=$(btn).parent().parent().children().eq(1).html();
	var cus_phone=$(btn).parent().parent().children().eq(4).html();
	$.ajax({
		url:"html/home/return_list/return_list_find.do",
		type:"post",
		data:{"ret_list_unique":ret_list_unique},
		dataType:"json",
		success:function(result){ 
			if(result.status==0){
				$("#return_listbasetbody").empty();
				$("#return_list_detail").empty();
				var ret_list=result.data;
				var cus=result.data1;
				var basetr="<tr><td class='public'>"+ret_list[0].ret_list_unique+"</td><td>"+ret_list[0].ret_list_id+"</td><td>";
				basetr+=ret_list[0].cus_name+"</td><td>";
				basetr+=ret_list[0].cus_phone+"</td><td>";
				basetr+=ret_list[0].ret_list_total+"</td><td>";
				var time=new Date(ret_list[0].ret_list_datetime);
				basetr+=time.getFullYear()+"-"+time.getMonth()+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+";"+time.getSeconds()+"</td></tr>";
				$("#return_listbasetbody").append(basetr);
				$("#ret_total").html(ret_list[0].ret_list_total);
				for(var i=0;i<ret_list.length;i++){
					var detailtr="<tr><td></td><td>";
					if(ret_list[i].goods_name!=null){
						detailtr+=ret_list[i].goods_name+"</td><td>";
					}else{
						detailtr+="</td><td>";
					}
					detailtr+=ret_list[i].ret_list_detail_count+"</td><td>";
					detailtr+=ret_list[i].ret_list_detail_price+"</td><td>";
					detailtr+=ret_list[i].ret_list_detail_count*ret_list[i].ret_list_detail_price+"</td></tr>";
					$("#return_list_detail").append(detailtr);
				}
				//自动匹配订单型号
				var states=$("#ret_list_state").children();
				for(var i=0;i<states.length;i++){
					if(states.eq(i).val()==ret_list[0].ret_list_state){
						states[i].selected=true;
					}
				}
				var hstates=$("#ret_list_handlestate").children();
				for(var j=0;j<hstates.length;j++){
					if(hstates.eq(j).val()==ret_list[0].ret_list_handlestate){
						hstates[j].selected=true;
						console.log
					}
				}
				order();
			}
		},
		error:function(){
			alert("加载修改订单页面失败");
		}
	});
	$("#return_list_area").css("display","none");
	$("#return_list_modifypage").show();
}

//点击保存订单按钮，保存定单修改
function return_list_save1(){
	//保证订单号唯一
	var ret_list_unique=$("#return_listbasetbody").children().eq(0).children().eq(0).html();
	console.log(ret_list_unique);
	var ret_list_state=$("#ret_list_state option:selected").val();
	var ret_list_handlestate=$("#ret_list_handlestate option:selected").val();
	$.ajax({
		url:"html/home/return_list/return_list_save.do",
		type:"post",
		data:{"ret_list_unique":ret_list_unique,"ret_list_state":ret_list_state,"ret_list_handlestate":ret_list_handlestate},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$("#return_list_area").show();
				$("#return_list_modifypage").css("display","none");
				return_lists_find();
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("保存订单失败");
		}
	});
}

//点击取消修改按钮，取消订单修改
function return_list_cancel(){
	$("#return_list_modifypage").css("display","none");
	$("#return_list_area").show();
}
//单击订单查询按钮，查询满足条件的订单
function return_lists_find(){
	var ret_list_id=$("#ret_list_id").val().trim();
	var startDate=$("#startdate").val().trim();
	var endDate=$("#enddate").val().trim();
	var ret_list_state=$("#return_list_state option:selected").val();
	var ret_list_handlestate=$("#return_list_handlestate option:selected").val();
	if(startDate==null||startDate==""){
		startDate="1000-01-01 01:01:01";
	}else{
		startDate+=" 00:00:00";
	}
	if(endDate==null||endDate==""){
		endDate="2200-12-31 01:01:01";
	}else{
		endDate+=" 01:01:01";
	}
	$.ajax({
		url:"return_list/return_lists_find.do",
		type:"post",
		data:{"ret_list_id":ret_list_id,"startDate":startDate,"endDate":endDate,"ret_list_state":ret_list_state,"ret_list_handlestate":ret_list_handlestate,"shop_unique":shop_unique,"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#return_lists").empty();
				var lists=result.data;
				if(lists.length==0){
					alert("没有满足条件的订单");
					return;
				}
				for(var i=0;i<lists.length;i++){
						var tr="<tr><td class='public'><input type='checkbox' class='public'></td><td class='public'>"+lists[i].ret_list_unique+"</td><td>";
						tr+=lists[i].ret_list_id+"</td><td>";
						tr+=lists[i].shop_name+"</td><td>";
						if(lists[i].cus_name!=null){
							tr+=lists[i].cus_name+"</td><td>";
						}else{
							tr+="</td><td>";
						}
						if(lists[i].cus_phone!=null){
							tr+=lists[i].cus_phone+"</td><td>";
						}else{
							tr+="</td><td>";
						}
						tr+=lists[i].ret_list_total.toFixed(2)+"</td><td>";
						var date=new Date(lists[i].ret_list_datetime);
						tr+=date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"</td><td>";
						var state=lists[i].ret_list_state;
						if(state==1){
							tr+="未退款</td><td>";
						}else if(state==2){
							tr+="已退款</td><td>";
						}else{
							tr+="错误示例</td><td>";
						}
						var hstate=lists[i].ret_list_handlestate;
						if(hstate==1){
							tr+="未处理</td><td>";
						}else if(hstate==2){
							tr+="已处理</td><td>";
						}else if(hstate==3){
							tr+="处理完毕</td><td>";
						}else{
							tr+="错误示例</td><td>";
						}
//					var tr="<tr><td><input type='checkbox'></td><td class='public'>"+ret_lists[i].ret_list_unique+"</td><td>";
//					console.log(ret_lists[i].ret_list_unique);
//					tr+=ret_lists[i].ret_list_id+"</td><td>";
//					tr+=cuss[i].cus_name+"</td><td>";
//					tr+=cuss[i].cus_phone+"</td><td>";
//					tr+=ret_lists[i].ret_list_total.toFixed(2)+"</td><td>";
//					var date=new Date(ret_lists[i].ret_list_datetime);
//					tr+=date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"</td><td>";
//					tr+=ret_lists[i].ret_list_state+"</td><td>";
//					tr+=ret_lists[i].ret_list_handlestate+"</td><td>";
					tr+="<input type='button' value='更新订单' onclick='return_list_modify(this)'></td></tr> ";
					$("#return_lists").append(tr);
				}
			}
		},
		error:function(){
			alert("退货订单查询异常");
		}
		});
}

//点击excel生产excel表格
function excel(){
	var ret_list_id=$("#ret_list_id").val().trim();
	var startDate=$("#startdate").val().trim();
	var endDate=$("#enddate").val().trim();
	var ret_list_state=$("#return_list_state option:selected").val();
	var ret_list_handlestate=$("#return_list_handlestate option:selected").val();
	if(startDate==null||startDate==""){
		startDate="1000-01-01 01:01:01";
	}else{
		startDate+=" 00:00:00";
	}
	if(endDate==null||endDate==""){
		endDate="2200-12-31 01:01:01";
	}else{
		endDate+=" 01:01:01";
	}
	$.ajax({
		url:"return_list/excel.do",
		type:"post",
		data:{"ret_list_id":ret_list_id,"startDate":startDate,"endDate":endDate,"ret_list_state":ret_list_state,"ret_list_handlestate":ret_list_handlestate,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				window.location.href="xlsx/"+shop_unique+"/return.xls";
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("生产excel表格异常");
		}
	});
}
//排序
function order(){
	var $trs=$("#return_list_detail").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}