//根据输入条件，查询满足条件的商品
function findgoods(){
	var goodsmessage=$("#goodsmessage").val().trim();
	$.ajax({
		url:"stock/findGoodss.do",
		type:"post",
		data:{"shop_unique":shop_unique,"goodsmessage":goodsmessage,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#goods").empty();
				var goods=result.data;
				
				for(var i=0;i<goods.length;i++){
					var tr="<tr><td></td><td>"+goods[i].goods_barcode+"</td><td>";
					tr+=goods[i].shop_name+"</td><td>";
					tr+=goods[i].goods_name+"</td><td>";
					if(goods[i].goods_in_price!=null){
						tr+=goods[i].goods_in_price+"</td><td>";
					}else{
						tr+="0.00</td><td>";
					}
					tr+=goods[i].goods_sale_price.toFixed(2)+"</td><td>";
					tr+=goods[i].goods_count+"</td></tr>";
					$("#goods").append(tr);
				}
				//排序
				order();
			}
		},
		error:function(){
			alert("查询商品失败");
		}
	});
}

//自动排序
function order(){
	var $trs=$("#goods").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//查看物品详情
function details(btn){
	var goods_barcode=$(btn).parent().parent().children().eq(1).html();
	console.log(goods_barcode);
}