function adjust_rfind(){
	var goods_message=$("#goods_message").val().trim();
	var adjust_type=$("#adjust_type option:selected").val();
	$.ajax({
		url:"adjust/adjust_rfind.do",
		type:"post",
		data:{"goods_message":goods_message,"shop_unique":shop_unique,"adjust_type":adjust_type},
		datType:"json",
		success:function(result){
			$("#adjusts").empty();
			if(result.status==0){
				var adjusts=result.data;
				for(var i=0;i<adjusts.length;i++){
					var tr="<tr><td></td><td>"+adjusts[i].adjust_id+"</td><td>";
					tr+=adjusts[i].shop_name+"</td><td>";
					tr+=adjusts[i].shop_phone+"</td><td>";
					if(adjusts[i].adjust_remark==null){
						tr+="</td><td>";
					}else{
						tr+=adjusts[i].adjust_remark+"</td><td>";
					}
					var date=new Date(adjusts[i].adjust_datetime);
					tr+=date.getFullYear()+"-"+(date.getMonth()+1)+"_"+date.getDate()+"</td><td>";
					var hstatue=adjusts[i].adjust_handlestatue;
					if(hstatue==1){
						tr+="未发货</td><td>";
					}else if(hstatue==2){
						tr+="已发货</td><td>";
					}else{
						tr+="错误提示</td><td>";
					}
					tr+=adjusts[i].adjust_total+"</td><td>";
					tr+=adjusts[i].goods_name+"</td><td>";
					tr+=adjusts[i].adjust_count+"</td><td>";
					tr+=adjusts[i].adjust_price.toFixed(2)+"</td></tr>";
					$("#adjusts").append(tr);
				}
				order();
			}
			if(result.status==1){
				$("#warning").html(result.msg);
				$("#warnings").fadeIn(1000).fadeOut(1000);
			}
		},
		error:function(){
			alert("查询失败，请稍后重试");
		}
	});
}

//排序
function order(){
	var $trs=$("#adjusts").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//商品信息栏，输入回车实现查询
function query(event){
	var code=event.keyCode;
	if(code==13){
		$("#adjust_rfind").click();
	}
}