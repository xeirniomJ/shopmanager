var shop_unique = getCookie("shop_unique");
var manager_unique=getCookie("manager_unique");
	$(function(){
		//
		$("#purchase_list_id").focus();
		//点击显示订单修改界面
		$("#purchase_list_modify").click(purchase_list_modify);
		//点击取消按钮，不保存修改返回并返回上一层
		$("#cancel").click(purchase_list_cancel);
		//点击保存按钮，保存修改并返回上一层
		$("#save").click(purchase_list_save);
		//点击订单查询按钮，将符合条件的订单查询出来
		$("#purchase_lists_find").click(purchase_lists_find);
		//点击excel表生产
		$("#purchase_list_excel").click(excel);
		$("body").keydown(function(event){
			var code=event.keyCode;
			if(code==13){
			$("#purchase_lists_find").click();	
			}
		})
	});
//点击按钮，进入订单修改界面
function purchase_list_modify(btn) {
	$("#purchase_lists_modify").empty();
	$(".purchase_list_area").css("display", "none");
	$("#purchase_list_modifypage").show();
	var purchase_list_date=$(btn).parent().parent().children().eq(4).html();
	var purchase_list_id=$(btn).parent().parent().children().eq(2).html();
	var purchase_list_unique = $(btn).parent().parent().children().eq(1).html();
	var purchase_list_total=$(btn).parent().parent().children().eq(5).html();
	var purchase_list_statue=$(btn).parent().parent().children().eq(6).html();
	$.ajax({
		url : "purchase_list_detail/purchase_list_detail_find.do",
		type : "post",
		data : {
			"purchase_list_unique":purchase_list_unique,"shop_unique":shop_unique
		},
		dataType : "json",
		success : function(result) {
			if(result.status==0){
				var detail=result.data;
				var tr="<tr><td class='public'>"+purchase_list_unique+"</td><td>"+purchase_list_id+"</td><td>";
				tr+=purchase_list_date+"</td><td>";
				tr+=purchase_list_total+"</td><td>";
				tr+=purchase_list_statue+"</td></tr>";
				$("#purchase_lists_modify").append(tr);
				$("#purchase_list_details").empty();
				for(var i=0;i<detail.length;i++){
					var detr="<tr><td></td><td>"+detail[i].goods_name+"</td><td>";
					detr+=detail[i].purchase_list_detail_count+"</td><td>";
					if(detail[i].purchase_list_detail_price!=null){
						detr+=detail[i].purchase_list_detail_price.toFixed(2)+"</td><td>";
					}else{
						detr+="0.00</td><td>";
					}
					detr+=(detail[i].purchase_list_detail_count*detail[i].purchase_list_detail_price).toFixed(2)+"</td></tr>";
					$("#purchase_list_details").append(detr);
				}
				order1();
				var states=$("#newpurchase_list_statue").children();
				for(var j=0;j<states.length;j++){
					if(detail[0].purchase_list_statue==j){
						states[j-1].selected="true";
					}
				}
			}
		},
		error : function() {
			alert("查询订单详情失败");
		}
	});
}
// 保存进货单的修改
function purchase_list_save() {
	var purchase_list_unique=$("#purchase_lists_modify").children().eq(0).children().eq(0).html();
	var purchase_list_statue=$("#newpurchase_list_statue").val().trim();
	$.ajax({
		url : "purchase_list/purchase_list_update.do",
		type : "post",
		data : {
			"purchase_list_unique" : purchase_list_unique,
			"purchase_list_statue":purchase_list_statue
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				alert(result.msg);
				purchase_lists_find() ;
			}
		},
		error : function() {
			alert("系统异常，请稍后重试");
		}
	});
	$(".public").css("display", "none");
	$(".purchase_list_area").show();
}
// 取消进货单的修改
function purchase_list_cancel() {
	$(".public").css("display", "none");
	$(".purchase_list_area").show();
}
// 单击查询订单按钮，查询符合添加到订单
function purchase_lists_find() {
	var purchase_list_id = $("#purchase_list_id").val().trim();
	var startDate = $("#startdate").val().trim();
	var endDate = $("#enddate").val().trim();
	if (startDate == null || startDate == "") {
		startDate = "2000-12-01";
	}
	if (endDate == null || endDate == "") {
		endDate = "2100-12-12";
	}
	$.ajax({
				url : "purchase_list/purchase_list_find.do",
				type : "post",
				data : {
					"purchase_list_id" : purchase_list_id,
					"startDate" : startDate,
					"endDate" : endDate,
					"shop_unique":shop_unique,
					"manager_unique":manager_unique
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 0) {
						$("#purchase_lists").empty();
						var purchase_lists = result.data;
						for (var i = 0; i < purchase_lists.length; i++) {
							var tr = "<tr><td></td><td class='public'>"+purchase_lists[i].purchase_list_unique+"</td><td>";
							tr += purchase_lists[i].purchase_list_id
									+ "</td><td>";
							tr+=purchase_lists[i].shop_name+ "</td><td>";
							var date=new Date(purchase_lists[i].purchase_list_date);
							tr += date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+ "</td><td>";
							tr += purchase_lists[i].purchase_list_total.toFixed(2)+ "</td><td>";
							var flag = purchase_lists[i].purchase_list_statue;
							var statue = "";
							if (flag - 1 == 0) {
								statue = "未付款";
							} else if (flag - 2 == 0) {
								statue = "已付款，未发货";
							} else if (flag - 3 == 0) {
								statue = "已付款，已发货";
							}else if(flag-4==0){
								statue="已付款，已收货";
							}
							tr += statue
									+ "</td><td><input type='button' value='更新订单'  onclick='purchase_list_modify(this)'></td></tr>";
							var $tr = $(tr);
							$("#purchase_lists").append(tr);
							
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
	var $trs=$("#purchase_lists").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//订单详情排序
function order1(){
	var $trs=$("#purchase_list_details").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(0).html(i+1);
	}
}
//点击excel生产，根据输入条件生产excel表格
function excel(){
	var purchase_list_id = $("#purchase_list_id").val().trim();
	if (purchase_list_id == "" || purchase_list_id == null) {
		purchase_list_id = "%";
	}
	var startDate = $("#startdate").val().trim();
	var endDate = $("#enddate").val().trim();
	if (startDate == null || startDate == "") {
		startDate = "2000-12-01";
	}
	if (endDate == null || endDate == "") {
		endDate = "2100-12-12";
	}
	$.ajax({
		url : "purchase_list/excel.do",
		type : "post",
		data : {
			"startDate":startDate,
			"endDate":endDate,
			"purchase_list_id":purchase_list_id,
			"shop_unique":shop_unique,
			"manager_unique":manager_unique
		},
		dataType : "json",
		success : function(result) {
			if(result.status==0){
				window.location.href="xlsx/"+shop_unique+"/purchase.xls";
			}
		},
		error : function() {
			alert("系统异常，请稍后重试");
		}
	});
}
