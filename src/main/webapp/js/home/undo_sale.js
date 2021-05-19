var shop_unique=getCookie("shop_unique");
var manager_unique=getCookie("manager_unique");
$(function(){
	//查询未处理订单详情
	show_undosale();
})
//查询未处理订单详情
function show_undosale(){
	$.ajax({
		url:"sale_list/undo_sale.do",
		type:"post",
		data:{"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#sale_lists").empty();
				var sales=result.data;
				for(var i=0;i<sales.length;i++){
					var date=new Date(sales[i].sale_list_datetime);
					var tr="<tr><td>";
					tr+=sales[i].sale_list_id+"</td><td>";
					tr+=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"</td><td>";
					if(sales[i].cus_name==null){
						tr+="</td><td>";
					}else{
						tr+=sales[i].cus_name+"</td><td>";
					}
					if(sales[i].cus_phone==null){
						tr+="</td><td>";
					}else{
						tr+=sales[i].cus_phone+"</td><td>";
					}
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
					tr+="<input type='button' value='修改订单' onclick='toSaleListDetail(this)'>";
					tr+="</td></tr>";
					$("#sale_lists").append(tr);
				}
			}
		},
		error:function(){
			alert("查询未处理订单失败");
		}
	});
}

//跳转到未处理订单详情
function toSaleListDetail(btn){
	var saleListId=$(btn).parents("tr").children().eq(0).html();
	addCookie("saleListId",saleListId,2);
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/home/saleListDetail.html");
}