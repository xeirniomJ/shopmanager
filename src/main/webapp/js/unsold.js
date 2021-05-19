//点击查询，查询时间段内产品销量
function finds(){
	var startDate=$("#startdate").val().trim();
	var endDate=$("#enddate").val().trim();
	if(startDate==null||startDate==""){
		startDate="2000-10-01 20:00:00"
	}else{
		startDate+=" 00:00:00";
	}
	if(endDate==null||endDate==""){
		endDate="2200-01-01 00:00:00";
	}else{
		endDate+=" 00:00:00";
	}
	$.ajax({
		//Finds_conditionsController
		url : "sale_list/findtotals.do",
		type : "post",
		data:{
			"shop_unique":shop_unique,
			"startDate":startDate,
			"endDate":endDate,
			"manager_unique":getCookie("manager_unique")
		},
		dataType : "json",
		success : function(result) {
			$("#saletotal").empty();
			if (result.status == 0) {
				var datas=result.data;
				var total=0;
				for(var i=0;i<datas.length;i++){
					total+=datas[i].total;
				}
				for(var i=0;i<datas.length;i++){
					if(datas[i].goods_name!=null){
						var tr="<tr><td></td><td>"+datas[i].goods_name+"</td><td>";
					}else{
						var tr="<tr><td></td><td></td><td>";
					}
					tr+=datas[i].sum+"</td><td>";
					tr+=datas[i].total+"</td><td>";
					tr+=((datas[i].total/total)*100).toFixed(2)+"%</td></tr>";
					$("#saletotal").append(tr);
				}
			}
			order();
		},
		error : function() {
			alert("系统异常，请稍后重试");
		}
	});
}

//排序
function order(){
	var $trs=$("#saletotal").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}