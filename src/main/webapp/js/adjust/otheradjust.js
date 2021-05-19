//单击查询按钮，查询满足添加到调货单
function oadjust_find(){
	var goods_message=$("#goods_message").val();
	if(getCookie("shop_unique")=="undefined"){
		$("#warning").html("请选择店铺！");
		$("#warnings").fadeIn(1000).fadeOut(1000);
		return;
	}
	var adjust_type=$("#adjust_type option:selected").val();
	if(shop_unique==null||shop_unique==""){
		alert("shop_unique is null");
		return;
	}
	$.ajax({
		url:"adjust/oadjust_find.do",
		type:"post",
		data:{"goods_message":goods_message,"adjust_type":adjust_type,"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		datType:"json",
		success:function(result){
			$("#adjustss").empty();
			if(result.status==0){
				var adjusts=result.data;
				for(var i=0;i<adjusts.length-1;i++){
					var time=new Date(adjusts[i].adjust_datetime);
					var tr="<tr><td>"+adjusts[i].adjust_id+"</td><td>"+time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate()+"</td><td>";
					var validity=new Date(adjusts[i].adjust_validity);
					tr+=validity.getFullYear()+"-"+(validity.getMonth()+1)+"-"+validity.getDate()+"</td><td>";
					var type=adjusts[i].adjust_type;
					if(type==1){
						tr+="转售</td><td>";
					}else if(type==2){
						tr+="采购</td><td>";
					}else{
						tr+="错误提示</td><td>";
					}
					tr+=adjusts[i].adjust_total+"</td><td>";
					tr+=adjusts[i].goods_name+"</td><td>";
					tr+=adjusts[i].adjust_count+"</td><td>";
					tr+=adjusts[i].adjust_price+"</td><td>";
					tr+=adjusts[i].shop_name+"</td><td>";
					var l=6378137;
					var d1=adjusts[i].shop_latitude*Math.PI/180;
					var d2=adjusts[i].shop_longitude*Math.PI/180;
					var d3=adjusts[i].lt*Math.PI/180;
					var d4=adjusts[i].lg*Math.PI/180;
					var distance=(Math.asin(2*Math.sqrt(Math.pow(Math.sin((d1-d3)/2),2)+Math.cos(d1)*Math.cos(d3)*Math.pow(Math.sin((d2-d4)/2), 2)))*l).toFixed(2);
					tr+=distance+"</td><td>";
					tr+=adjusts[i].shop_phone+"</td><td>";
					tr+="<input type='button' value='响应' onclick='resp(this)'></td></tr>";
					$("#adjustss").append(tr);
				}
			}
			if(result.status==1){
				$("#warnings").html(result.msg);
				$("#warnings").fadeIn(1000).fadeOut(1000);
			}
		},
		error:function(){
			alert("网络异常，请稍后重试");
		}
	});
}
//单击响应，响应订货单
function resp(btn){
	var adjust_id=$(btn).parent().parent().children().eq(0).html();
	$.ajax({
		url:"adjust/adjust_update.do",
		type:"post",
		data:{"adjust_id":adjust_id,"shop_unique":shop_unique},
		datType:"json",
		success:function(result){
			if(result.status==0){
				$("#warning").html(result.msg);
				$("#warnings").fadeIn(1000).fadeOut(1500);
				$("#adjust_find").click();
			}
		},
		error:function(){
			$("#warning").html("网络异常，请稍后重试");
			$("#warnings").fadeIn(500).fadeOut(500);
		}
	});
}