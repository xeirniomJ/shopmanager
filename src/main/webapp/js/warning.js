/**
 * 进货及滞销提醒
 */
function warning(){
	$.ajax({
		url:"warning.do",
		type:"post",
		data:{"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var res=result.data;
				for(var i=0;i<res.length;i++){
					var tr="<tr><td></td><td>"+res[i].goods_name+"</td><td>";
					tr+=res[i].sum.toFixed(2)+"</td><td>";
					tr+=res[i].average.toFixed(2)+"</td><td>";
					tr+=res[i].hold.toFixed(2)+"天</td></tr>";
					if(res[i].hold>30){
						$("#unsalable").append(tr);
					}else if(res[i].hold<7){
						$("#purchase").append(tr);
					}
					order1();
					order2();
				}
			}
		},
		error:function(){
			alert("加载失败，请稍后重试");
		}
	});
}

//滞销排序
function order1(){
	var $trs=$("#unsalable").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//进货排序
function order2(){
	var $trs=$("#purchase").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}