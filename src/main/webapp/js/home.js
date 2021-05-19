var shop_unique=getCookie("shop_unique");
var manager_unique=getCookie("manager_unique");
$(function(){
	//加载页面后，自动给出未处理订单提示
	index_alert();
	//营业额提醒
	conditions();
	//点击未处理订单，显示未处理订单
	$("#sale_index").click(show_undoSale);
	//点击未处理订单，显示未处理退货详情
	$("#ret_index").click(show_undoRet)
});

//加载页面后，自动给出未处理订单提示
function index_alert(){
	var manager_name=getCookie("manager_name");
	if(manager_name!=null){
		window.parent.document.getElementById("manager_name").innerHTML=manager_name;
	}
	if(manager_unique==null){
		return;
	}
	$.ajax({
		url:"sale_list/index_alert.do",
		type:"post",
		data:{"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique").trim()},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#sale_index").html("您有"+result.data[0]+"条订单未处理");
				$("#ret_index").html("您有"+result.data[1]+"条退货订单未处理");
			}
		},
		error:function(){
		}
	});
}

//营业额提醒
function conditions(){
	var date=new Date();
	var startDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" 00:00:01";
	var endDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()*1+1)+" 00:00:01";
	$.ajax({
		url:"sale_list/conditions_alert.do",
		type:"post",
		data:{"shop_unique":shop_unique,"startDate":startDate,"endDate":endDate,"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#conditions").html("今日的销售营业总额为："+result.data+"元");
			}
		},
		error:function(){
			alert("查询当日销售营业额异常;")
		}
	});
}

//点击未处理订单提醒，显示未处理订单
function show_undoSale(){
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/home/undo_sale.html");
}
//点击未处理，显示未处理退货订单界面
function show_undoRet(){
	window.parent.document.getElementById("rightFrame").setAttribute("src",  "html/home/unDoRet.html")
}