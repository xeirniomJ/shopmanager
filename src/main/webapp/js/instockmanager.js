//单击返回按钮，返回入库查询界面
function ret(){
	$(".showarea").css("display","none");
	$(".main").show();
}
//单击查询按钮，查询满足条件的入库单
function findinstocks(){
	var startDate=$("#startdate").val().trim();
	var endDate=$("#enddate").val().trim();
	if(startDate==null||startDate==""){
		startDate="1000-10-10 00:00:00";
	}else{
		startDate+=" 00:00:01";
	}
	if(endDate==null||endDate==''){
		endDate="2200-10-10 00:00:00";
	}else{
		endDate+=" 00:00:00";
	}
	$.ajax({
		url:"instock/findInstocks.do",
		type:"post",
		data:{"shop_unique":shop_unique,"startDate":startDate,"endDate":endDate,"manager_unique":getCookie("manager_unique")},
		datatype:"json",
		success:function(result){
			if(result.status==0){
				$("#instocks").empty();
				var instocks=result.data;
				for(var i=0;i<instocks.length;i++){
					var tr="<tr><td class='public'>"+instocks[i].shop_unique+"</td><td></td><td>"+instocks[i].shop_name+"</td><td>"+instocks[i].instock_id+"</td><td>";
					var date=new Date(instocks[i].instock_date);
					tr+=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"</td><td>";
					tr+=instocks[i].instock_total+"</td><td>";
					tr+="<input type='button' value='查看详情' onclick='findinstock(this)'></td></tr>";
					$("#instocks").append(tr);
				}
				order();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("查询入库单记录失败");
		}
	});
}
//单击查看详情按钮，跳转到订单详情界面，并查询该入库单详情
function findinstock(btn){
	var instock_id=$(btn).parent().parent().children().eq(3).html();
	var shop_unique=$(btn).parent().parent().children().eq(0).html();
	$.ajax({
		url:"instock/findInstock.do",
		type:"post",
		data:{"shop_unique":shop_unique,"instock_id":instock_id},
		datatype:"json",
		success:function(result){
			if(result.status==0){
				var instock=result.data;
				$("#instock").empty();
				$("#instockdetails").empty();
				var intr="<tr><td>"+instock_id+"</td><td>";
				var date=new Date(instock[0].instock_date);
				intr+=date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+"</td><td>";
				intr+=instock[0].instock_total+"</td></tr>";
				$("#instock").append(intr);
				for(var i=0;i<instock.length;i++){
					var tr="<tr><td></td><td>"+instock[i].name+"</td><td>";
					tr+=(instock[i].subtotal/instock[i].count)+"</td><td>";
					tr+=instock[i].count+"</td><td>";
					tr+=instock[i].subtotal+"</td></tr>";
					$("#instockdetails").append(tr);
				}
				order1();
			}
		},
		error:function(){
			alert("入库详情查询失败");
		}
	});
	$(".showarea").css("display","none");
	$(".details").show();
}
//排序
function order(){
	var $trs=$("#instocks").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(1).html(i+1);
	}
}

//详情排序
function order1(){
	var $trs=$("#instockdetails").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}

//查询购物车里商品数量
function findPurCartGoods(){
	
}