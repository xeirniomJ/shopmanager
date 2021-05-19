var shop_unique=getCookie("shop_unique");
var manager_unique=getCookie("manager_unique");
$(function(){
	//查询未处理的退货订单
	ret_undo();
});
//查询所有未处理的退货订单

function ret_undo(){
	$.ajax({
		url:"return_list/undo_ret.do",
		type:"post",
		data:{"manager_unique":manager_unique,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#return_lists").empty();
				var lists=result.data;
				for(var i=0;i<lists.length;i++){
					var tr="<tr><td class='public'><input type='checkbox' class='public'></td><td class='public'>"+lists[i].ret_list_unique+"</td><td>";
					tr+=lists[i].ret_list_id+"</td><td>";
					if(lists[i].cus_name==null){
						tr+="</td><td>";
					}else{
						tr+=lists[i].cus_name+"</td><td>";
					}
					if(lists[i].cus_phone==null){
						tr+="</td><td>";
					}else{
						tr+=lists[i].cus_phone+"</td><td>";
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
				tr+="<input type='button' value='更新订单' onclick='toRetListDetail(this)'></td></tr> ";
				$("#return_lists").append(tr);
				}
			}
		},
		error:function(){
			alert("查询未处理退货订单失败，请检查网络！");
		}
	});
}

function toRetListDetail(btn){
	var retListUnique=$(btn).parents("tr").children().eq(1).html();
	addCookie("ret_list_unique", retListUnique, 2);
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/home/retListDetail.html")
	
}