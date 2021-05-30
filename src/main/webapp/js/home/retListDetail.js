var shop_unique=getCookie("shop_unique");
var manager_unique=getCookie("manager_unique");
var ret_list_unique=getCookie("ret_list_unique");
$(function(){
	//查询订单
	return_list_find();
	//保存订单
	$("#return_list_save").click(return_list_save);
	//点击取消按钮，返回上一层
	$("#return_list_cancel").click(function(){
		window.history.back();
	});
});
//查询退货订单详情
function return_list_find(){
	$.ajax({
		url:"return_list/return_list_find.do",
		type:"post",
		data:{"ret_list_unique":ret_list_unique},
		dataType:"json",
		success:function(result){ 
			if(result.status==0){
				$("#return_listbasetbody").empty();
				$("#return_list_detail").empty();
				var ret_list=result.data;
				var basetr="<tr><td class='public'>"+ret_list[0].ret_list_unique+"</td><td>"+ret_list[0].ret_list_id+"</td><td>";
				basetr+=ret_list[0].cus_name+"</td><td>";
				basetr+=ret_list[0].cus_phone+"</td><td>";
				basetr+=ret_list[0].ret_list_total+"</td><td>";
				var time=new Date(ret_list[0].ret_list_datetime);
				basetr+=time.getFullYear()+"-"+time.getMonth()+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds() +"</td></tr>";
				$("#return_listbasetbody").append(basetr);
				$("#ret_total").html(ret_list[0].ret_list_total);
				for(var i=0;i<ret_list.length;i++){
					var detailtr="<tr><td></td><td>";
					if(detailtr!=null){
						detailtr+=ret_list[i].goods_name+"</td><td>";
					}else{
						detailtr+="</td><td>";
					}
					detailtr+=ret_list[i].ret_list_detail_count+"</td><td>";
					detailtr+=ret_list[i].ret_list_detail_price+"</td><td>";
					detailtr+=ret_list[i].ret_list_detail_count*ret_list[i].ret_list_detail_price+"</td></tr>";
					$("#return_list_detail").append(detailtr);
				}
				order();
			}
		},
		error:function(){
			alert("加载修改订单页面失败");
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
//保存订单
//点击保存订单按钮，保存定单修改
function return_list_save(){
	//保证订单号唯一
	var ret_list_unique=$("#return_listbasetbody").children().eq(0).children().eq(0).html();
	var ret_list_state=$("#ret_list_state option:selected").val();
	var ret_list_handlestate=$("#ret_list_handlestate option:selected").val();
	$.ajax({
		url:"return_list/return_list_save.do",
		type:"post",
		data:{"ret_list_unique":ret_list_unique,"ret_list_state":ret_list_state,"ret_list_handlestate":ret_list_handlestate},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$("#return_list_area").show();
				$("#return_list_modifypage").css("display","none");
				window.history.back();
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("保存订单失败");
		}
	});
}

