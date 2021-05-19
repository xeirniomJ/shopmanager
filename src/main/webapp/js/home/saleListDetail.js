var manager_unique=getCookie("manager_unique");
var shop_unique=getCookie("shop_unique");
$(function(){
	//自动订单支付状态
	loadSaleListState();
	//自动加载订单处理状态
	loadSaleListHandleState();
	//提交订单修改
	$("#sale_list_save").click(sale_list_save);
	//取消未处理订单请求
	$("#sale_list_cancel").click(function(){
		window.history.back();
	})
})
//根据订单编号查询订单详情并填充页面数据
function findSaleListDetail(){
	$(".public").css("display","none");
	$("#sale_list_modifypage").show();
	var saleListId=getCookie("saleListId");
	$.ajax({
		url:"sale_list/sale_list_find.do",
		type:"post",
		data:{"sale_list_id":saleListId},
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
				basetr+=sale_list[0].cus_name+"</td><td>";
				basetr+=sale_list[0].cus_phone+"</td><td>";
				basetr+=sale_list[0].sale_list_total.toFixed(2)+"</td><td>";
				var time=new Date(sale_list[0].sale_list_datetime);
				basetr+=time.getFullYear()+"-"+time.getMonth()+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds()+"</td></tr>";
				$("#sale_listbasetbody").append(basetr);
				
				var sendtr="<tr><td>"+sale_list[0].cus_name+"</td><td>";
				sendtr+=sale_list[0].cus_phone+"</td><td>";
				sendtr+=sale_list[0].sale_list_address+"</td></tr>"
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
				order();
				//商品总金额
				$("#goods_total").html(sale_list[0].sale_list_total-sale_list[0].sale_list_delfee);
				//添加外送费
				$("#sale_list_delfee").val(sale_list[0].sale_list_delfee);
				//添加总价
				$("#sale_list_total").html(sale_list[0].sale_list_total);
				//修改订单处理情况
				//订单备注
				$("#sale_list_remarks").val(sale_list[0].sale_list_remarks);
				//设置订单状态
				var $states=$("#sale_list_state").children();
				for(var  i=0;i<$states.length;i++){
					if($states.eq(i).val()==sale_list[0].sale_list_state){
						$states[i].selected="true";
					}
				}
				var $states=$("#sale_list_handlestate").children();
				for(var  i=0;i<$states.length;i++){
					if($states.eq(i).val()==sale_list[0].sale_list_handlestate){
						$states[i].selected="true";
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
	var sale_list_id=getCookie("saleListId");
	var sale_list_delfee=$("#sale_list_delfee").val().trim();
	var sale_list_remarks=$("#sale_list_remarks").val().trim();
	var sale_list_state=$("#sale_list_state option:selected").val();
	var sale_list_handlestate=$("#sale_list_handlestate option:selected").val();
	var sale_list_total=parseFloat($("#sale_list_total").html().trim());
	$.ajax({
		url:"sale_list/sale_list_save.do",
		type:"post",
		data:{"sale_list_id":sale_list_id,"sale_list_delfee":sale_list_delfee,"sale_list_remarks":sale_list_remarks,"sale_list_handlestate":sale_list_handlestate,"sale_list_state":sale_list_state,"sale_list_total":sale_list_total},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				window.history.back();
			}
		},
		error:function(){
			alert("订单保存异常");
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