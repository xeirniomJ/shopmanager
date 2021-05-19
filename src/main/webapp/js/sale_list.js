  //单击修改订单按钮，进入修改订单界面
function sale_list_modify(btn){
	$("#sale_list_area").css("display","none");
	$("#sale_list_modifypage").show();
	var sale_list_id=$(btn).parent().parent().children().eq(1).html();
	$.ajax({
		url:"html/home/sale_list/sale_list_find.do",
		type:"post",
		data:{"sale_list_id":sale_list_id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#sale_listbasetbody").empty();
				$("#sale_listSend").empty();
				$("#sale_list_detail").empty();
				var sale_list=result.data;
				var basetr="<tr><td>"+sale_list[0].sale_list_id+"</td><td>";
				if(sale_list[0].sale_type==0){
					basetr+="实体店订单</td><td>";
				}else if(sale_list[0].sale_type==1){
					basetr+="网络订单</td><td>";
				}
				if(sale_list[0].cus_name==null){
					basetr+="游客</td><td>";
				}else{
					basetr+=sale_list[0].cus_name+"</td><td>";
				}
				if(sale_list[0]){
					basetr+="游客</td><td>";
				}else{
					basetr+=sale_list[0].cus_phone+"</td><td>";
				}
				basetr+=sale_list[0].sale_list_total.toFixed(2)+"</td><td>";
				var time=new Date(sale_list[0].sale_list_datetime);
				basetr+=time.getFullYear()+"-"+time.getMonth()+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds()+"</td></tr>";
				$("#sale_listbasetbody").append(basetr);
				if(sale_list[0].cus_name!=null){
					var sendtr="<tr><td>"+sale_list[0].cus_name+"</td><td>";
				}else{
					var sendtr="<tr><td>未知</td><td>";
				}
				if(sale_list[0].cus_phone!=null){
					sendtr+=sale_list[0].cus_phone+"</td><td>";
				}else{
					sendtr+="未知</td><td>";
				}
				if(sale_list[0].sale_list_address!=null){
					sendtr+=sale_list[0].sale_list_address+"</td></tr>";
				}else{
					sendtr+="非外送</td><tr>";
				}
				var tr=sale_list[0].sale_list_address;
				$("#sale_listSend").append(sendtr);
				for(var k=0;k<sale_list.length;k++){
					var detailtr="<tr><td></td><td>";
					detailtr+=sale_list[k].goods_barcode+"</td><td>";
					detailtr+=sale_list[k].goods_name+"</td><td>";
					detailtr+=sale_list[k].sale_list_detail_count+"</td><td>";
					detailtr+=sale_list[k].sale_list_detail_price+"</td><td>";
					detailtr+=sale_list[k].sale_list_detail_count*sale_list[k].sale_list_detail_price+"</td><tr>";
					$("#sale_lisat_detail").append(detailtr);
				}
				//商品总金额
				$("#goods_total").html(sale_list[0].sale_list_total-sale_list[0].sale_list_delfee);
				//添加外送费
				$("#sale_list_delfee").val(sale_list[0].sale_list_delfee);
				//添加总价
				$("#sale_list_total").html(sale_list[0].sale_list_total);
				//修改订单处理情况
				//订单备注
				$("#sale_list_remarks").val(sale_list[0].sale_list_remarks);
				order();
				//设置订单状态
				var states=$("#newsale_list_state").children();
				for(var  i=0;i<states.length;i++){
					if(i+1==sale_list[0].sale_list_state){
						states[i].selected="true";
					}
				}
				states=$("#newsale_list_handlestate").children();
				for(var  i=0;i<states.length;i++){
					if(states.eq(i).val()==sale_list[0].sale_list_handlestate){
						states[i].selected="true";
					}
				}
			}
		},
		error:function(){
			alert("加载异常");
		}
	});
}

//保存订单修改
function sale_list_save(){
	totalchange();
	var sale_list_id=$("#sale_listbasetbody").children().eq(0).children().eq(0).html();
	var sale_list_delfee=$("#sale_list_delfee").val().trim();
	var sale_list_remarks=$("#sale_list_remarks").val().trim();
	var sale_list_state=$("#newsale_list_state option:selected").val();
	var sale_list_handlestate=$("#newsale_list_handlestate option:selected").val();
	var sale_list_total=parseFloat($("#sale_list_total").html().trim());
	$.ajax({
		url:"html/home/sale_list/sale_list_save.do",
		type:"post",
		data:{"sale_list_id":sale_list_id,"sale_list_delfee":sale_list_delfee,"sale_list_remarks":sale_list_remarks,"sale_list_handlestate":sale_list_handlestate,"sale_list_state":sale_list_state,"sale_list_total":sale_list_total},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$("#sale_list_modifypage").css("display","none");
				$("#sale_list_area").show();
				sale_list_find1();
			}
		},
		error:function(){
			alert("订单保存异常");
		}
	});
	
}
//单击删除订单按钮，删除当前订单
function sale_list_delete(btn){
}
//取消订单修改
function sale_list_cancel(){
	$("#sale_list_modifypage").css("display","none");
	$("#sale_list_area").show();
}
//单击查询按钮，查询符合条件订单
function sale_list_find1(){
	var sale_list_id=$("#sale_list_id").val().trim();
	var startDate=$("#startdate").val().trim();
	if(startDate==null||startDate==""){
		startDate ="1000-01-01 01:01:01";
	}else{
		startDate+=" 00:00:01";
	}
	var endDate=$("#enddate").val().trim();
	if(endDate==null||endDate==""){
		endDate="2016-12-12 01:01:01";
	}else{
		endDate+=" 00:00:01";
	}
	var sale_list_state=$("#sale_list_state option:selected").val();
	var sale_list_handlestate=$("#sale_list_handlestate option:selected").val();
	$.ajax({
		url:"sale_list/sale_lists_find.do",
		type:"post",
		data:{"shop_unique":shop_unique,"sale_list_id":sale_list_id,"startDate":startDate,"endDate":endDate,"sale_list_state":sale_list_state,"sale_list_handlestate":sale_list_handlestate,"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#sale_lists").html("");
				var sales=result.data;
				if(sales.length==0){
					alert("没有满足条件的订单");
					return;
				}
				for(var i=0;i<sales.length;i++){
					var date=new Date(sales[i].sale_list_datetime);
					var tr="<tr><td class='public'></td><td>";
					tr+=sales[i].sale_list_id+"</td><td>";
					tr+=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"</td><td>";
					tr+=sales[i].shop_name+"</td><td>";
					if(sales[i].cus_name==null){
						tr+="游客</td><td>";
					}else{
						tr+=sales[i].cus_name+"</td><td>";
					}
					if(sales[i].cus_phone==null){
						tr+="</td><td>";
					}else{
						tr+=sales[i].cus_phone+"</td><td>";
					}
//					tr+=result.data1[i].cus_name+":"+result.data1[i].cus_phone+"</td><td>";
					tr+=sales[i].sale_list_total+"</td><td>";
					var state=sales[i].sale_list_state;
					if(state==1){
						tr+="货到付款，未付款</td><td>"
					}else if(state==2){
						tr+="未付款</td><td>";
					}else if(state==3){
						tr+="已付款</td><td>";
					}else{
						tr+="错误提示</td><td>"
					}
					var handlestate=sales[i].sale_list_handlestate;
					if(handlestate==1){
						tr+="无效订单</td><td>"
					}else if(handlestate==2){
						tr+="未发货</td><td>";
					}else if(handlestate==3){
						tr+="已发货</td><td>";
					}else if(handlestate==4){
						tr+="已收货</td><td>"
					}else{
						tr+="错误提示</td><td>"
					}
					tr+="<input type='button' value='修改订单' onclick='sale_list_modify(this)'>";
					tr+="</td></tr>";
					$("#sale_lists").append(tr);
				}
			}
		},
		error:function(){
			alert("系统异常，请稍后重试");
		}
	});
}
//商品排序
function order(){
	var $trs=$("#sale_lisat_detail").children();
	for(var k=0;k<$trs.length;k++){
		$trs.eq(k).children().eq(0).html((k+2)/2);
	}
}
//修改订单外送费金额后,自动修改订单总金额
function totalchange(){
	var sale_list_delfee=parseFloat( $ ("#sale_list_delfee").val());
	var goods_total=parseFloat($("#goods_total").html());
	
	$("#sale_list_total").html(sale_list_delfee+goods_total);
}

//单击excel生产销售订单excel
function excel(){
	var sale_list_id=$("#sale_list_id").val().trim();
	var startDate=$("#startdate").val().trim();
	if(startDate==null||startDate==""){
		startDate ="1000-01-01 01:01:01";
	}else{
		startDate+=" 00:00:01";
	}
	var endDate=$("#enddate").val().trim();
	if(endDate==null||endDate==""){
		endDate="2016-12-12 01:01:01";
	}else{
		endDate+=" 00:00:01";
	}
	var sale_list_state=$("#sale_list_state option:selected").val();
	var sale_list_handlestate=$("#sale_list_handlestate option:selected").val();
	$.ajax({
		url:"sale_list/excel.do",
		type:"post",
		data:{"shop_unique":shop_unique,"sale_list_id":sale_list_id,"startDate":startDate,"endDate":endDate,"sale_list_state":sale_list_state,"sale_list_handlestate":sale_list_handlestate,"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
			}
		},
		error:function(){
			alert("生成excel表异常");
		}
	});
	setTimeout(function(){
		window.location.href="xlsx/"+shop_unique+"/sale.xls";
	}, 2000);
}
