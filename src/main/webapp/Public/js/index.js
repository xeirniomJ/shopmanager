//单击订单管理，显示订单管理选型
function showlist(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".softmanager").css("display","none");
	$(".sale_list_area").show();
	$("#sale_list_area").show();
}
//单击销售订单查询，弹出订单查询界面
function sale_listshow(){
	//将所有公共区域隐藏
	$(".public").css("display","none");
	//将订单查询界面显示
	$("#sale_list_area").show();
}


//单击进货订单查询，显示进货订单管理界面
function purchase_list_find(){
//	$(".public").css("display","none");
//	$(this).parent().children().removeClass("checked");
//	$(this).addClass("checked");
//	$(".purchase_list_area").show();
	console.log(1);
}

