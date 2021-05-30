function customer_level_list() {
	$.ajax({
		url : "customer_level/customer_level_list.do",
		type : "get",
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				var customer_levels=result.data;
				for(var i=0;i<customer_levels.length;i++){
					 var tr="<tr><td></td><td>";
					 tr+=customer_levels[i].cus_level_name+"</td><td>";
					 tr+=customer_levels[i].cus_level_points+"</td><td>";
					 tr+=customer_levels[i].cus_level_discount+"</td></tr>";
					 $("#customer_level_list").append(tr);
				}
				order();
			}
		},
		error : function() {
			alert("系统异常，请稍后重试");
		}
	});
}
//表格排序
function order() {
	var $trs=$("#customer_level_list").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}