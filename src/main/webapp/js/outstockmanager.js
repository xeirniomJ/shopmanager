//单击查询按钮，查询满足条件的出库单
function findOutstocks(){
	var startDate=$("#startdate").val().trim();
	var endDate=$("#enddate").val().trim();
	var goodsmessage=$("#goodsmessage").val().trim();
	if(startDate==null||startDate==""){
		startDate='1100-11-11 00:00:01';
	}else{
		startDate+=' 00:00:00';
	}
	if(endDate==null||endDate==""){
		endDate='2220-11-11 00:00:01';
	}else{
		endDate+=' 00:00:00';
	}
	$.ajax({
		url:"outstock/findOutstocks.do",
		type:"post",
		data:{"startDate":startDate,"endDate":endDate,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			$("#outstocks").empty();
			if(result.status==0){
				var outstocks=result.data;
				for(var i=0;i<outstocks.length;i++){
					var date=new Date(outstocks[i].outstock_date);
					var tr="<tr><td></td><td>"+outstocks[i].outstock_id+"</td><td>";
					tr+=date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+"</td><td>";
					tr+=outstocks[i].outstock_total+"</td><td>";
					tr+=outstocks[i].sale_list_id+"</td><td><input type='button' value='查看详情' onclick='findOutstock(this)'></td></tr>";
					$tr=$(tr);
					$tr.data("outstock_unique",outstocks[i].outstock_unique);
					$("#outstocks").append($tr);
				}
				order();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			
		}
	});
}

//单击返回上层按钮，返回查询界面
function back(){
	$(".showarea").css("display","none");
	$(".main").show();
}

//单击查询详情按钮，显示出库单详情
function findOutstock(btn){
	var outstock_unique=$(btn).parents("tr").data("outstock_unique");
	$.ajax({
		url:"outstock/findOutstock.do",
		type:"post",
		data:{"outstock_unique":outstock_unique},
		dataType:"json",
		success:function(result){
			$("#outstock").empty();
			$("#outstockdetails").empty();
			if(result.status==0){
				var stocks=result.data;
				var tr="</tr><td>"+stocks[0].outstock_id+"</td><td>";
				var date=new Date(stocks[0].outstock_date);
				tr+=date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+"</td><td>";
				tr+=stocks[0].outstock_total+"</td><td>";
				tr+=stocks[0].sale_list_id+"</td></tr>";
				$("#outstock").append(tr);
				for(var i=0;i<stocks.length;i++){
					var trs="<tr><td></td><td>"+stocks[i].goods_name+"</td><td>";
					trs+=stocks[i].outstock_detail_count+"</td><td>";
					trs+=stocks[i].outstock_detail_price+"</td><td>";
					trs+=stocks[i].outstock_detail_count*stocks[i].outstock_detail_price+"</td></tr>";
					$("#outstockdetails").append(trs);
				}
				order1();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
		}
	});
	$(".showarea").css("display","none");
	$(".details").show();
}
//出库单排序
function order(){
	var $trs=$("#outstocks").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//出库单详情排序
function order1(){
	var $trs=$("#outstockdetails").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}