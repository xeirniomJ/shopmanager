var manager_unique=getCookie("manager_unique");
var shop_unique=null;
shops_uniquefind();
//给店铺编号赋值
var height=screen.height;
if(manager_unique!=null){
	$(function(){
		//修改列表的高度
//		$("ul").css("height",screen.height-240);
//		$("#shops").css("height","20px");
		//获取店铺名字
		//修改可显示区域的高度
//		$("#softmanager").css("height",screen.height-240);
//		$("#sale_list_area").css("height",screen.height-240);
		
//		$(".softmanager").show();
		//单击订单管理，显示订单管理功能列表，并显示进货订单查询页面
		$("#orders").click(showorders);
		//单击进货订单查询，显示进货订单管理页面
		$("#purchase_list_find").click(purchase_list_find);
		//单击销售订单查询，显示销售订单管理界面
		$("#sale_list_find").click(sale_list_find);
		//单击退货订单查询，显示退货订单管理界面
		$("#return_list_find").click(return_list_find);
		//单击产品管理，显示产品管理管理列表
		$("#goods").click(showgoods);
		//单击商品分类管理
		$("#goods_kind").click(goods_kind);
		//单击商品促销管理
		$("#goods_promotion").click(goods_promotion);
		//单击商品管理，显示商品管理界面
		$("#goods_manager").click(goods_manager);
		//单击会员管理，显示会员管理列表
		$("#customers").click(customers);
		//单击会员信息管理，显示会员信息管理界面
		$("#customer_manager").click(customer_manager);
		//单击会员等级管理，显示会员等级管理界面
		$("#customer_level").click(customer_level);
		//单击会员送货地址管理，显示会员送货地址管理界面
		$("#addr_manager").click(addr_manager);
		//单击会员评论管理，显示会员评论管理界面
		$("#customer_comment").click(customer_comment);
		//加载页面后，自动给出未处理订单提示
		index_alert();
		//加载页面后，自动给出滞销产品提醒及其当日营业额状态
		conditions();
		//单击库存盘点，显示库存管理列表
		$("#stock").click(stocklist);
		//单击库存查询，显示库存查询页面
		$("#stockmanager").click(stockmanager);
		//单击入库查询，显示入库查询页面
		$("#instockmanager").click(instockmanager);
		//单击出库查询列表，显示出库查询页面
		$("#outstockmanager").click(outstockamanger);
		//单击入库查询，显示入库查询界面
		$("#instockmanager").click(instockmanager);
		//单击经营状况按钮，显示店铺经营状况页面
		$("#conditionsfunction").click(conditonsfunction);
		//点击进销量统计，显示进销量统计页面
		$("#statistical").click(statistical);
		//点击未处理订单，显示未处理订单
		$("#sale_index").click(show_undosale);
		//保存订单
		$("#sale_list_save").click(sale_list_save);
		//取消订单修改
		$("#sale_list_cancel").click(sale_list_cancel);
		//修改外送费后,自动修改订单总金额
		$("#sale_list_delfee").blur(totalchange);
		//点击未处理退货，显示退货查询详情
		$("#ret_index").click(ret_undo);
		//点击订单修改按钮，进入订单修改页面
		$("#return_list_modify").click(return_list_modify1);
		//点击保存修改按钮，保存已修改订单
		$("#return_list_save").click(return_list_save);
		//点击取消修改按钮，取消订单的保存
		$("#return_list_cancel").click(return_list_cancel);
		//加载页面后，自动给出进货提醒
	//	instock();
		//点击滞销产品统计，显示滞销产品
		$("#unsold1").click(unsold);
		//点击进货及滞销产品提醒，显示进货及滞销产品页面
		$("#warning").click(warning1);
		//单击调换货物，显示调换货物功能列表
		$("#adjust").click(adjustfunction);
		//单击我的调货申请，显示调货申请页面
		$("#myadjust").click(myadjust);
		//点击其他商家调货申请，显示其他商家调货申请界面
		$("#otheradjust").click(otheradjust);
		//单击我响应的调货申请，显示我响应的调货申请的界面
		$("#responseadjust").click(responseadjust);
		//单击店铺列表，选中店铺
		$("#shops").on("click","li",changeshop);
		//单击商品采购，显示商品采购列表
		$("#purchase").click(toPurchase);
		//单击修改账号密码，进入修改账号密码界面
		$("#toChangePwd").click(toChangePwd);
		//设置定时器，定时检查是否有新的订单
		setInterval(checkNewOrder,300000);
		//点击提醒，显示订单处理界面
		$("#newOrderWarning").click(function(){
			$("#sale_index").click();
		});
		//点击店铺管理，显示店铺管理页面
		$("#shopsManager").click(toShopManager);
		//点击购物车标识，跳转购物车界面
		$("#newPurchase").parents("p").click(function(){
			$("#purchase").click();
		})
		
	});
}else{
	window.location.href="login.html";
}//单击订单管理页面，显示订单管理功能列表，并显示进货订单管理页面
function  showorders(){
	$(".softmanager").css("display","none");
	$("#orders").parent().children().removeClass("checked");
	$("#orders").addClass("checked");
	//取消所有子元素的checked样式
	$("#checkfunction").children().removeClass("checked");
	$("#purchase_list_find").addClass("checked");
	$(".purchase_list_area").show();
	purchase_list_find();
}

//单击进货订单查询，显示进货订单管理界面
function purchase_list_find(){
	$(this).parent().children().removeClass("checked");
	$("#purchase_list_find").addClass("checked");
	$(".public").css("display","none");
	$(".purchase_list_area").show();
	//重新加载页面
	$("#purchase_html").attr("src","purchase_list.html");
}
//单击销售订单查询，显示销售订单管理界面
function sale_list_find(){
	$("#sale_list_find").parent().children().removeClass("checked");
	$("#sale_list_find").addClass("checked");
	$(".public").css("display","none");
	$(".sale_list_area").show();
	$("#sale_list_html").attr("src","sale_list.html");
}
//单击产品管理，显示产品管理列表
function showgoods(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$("#goods_manager").click();
	$("#goodsfunction").show();
	$("#sale_list_html").attr("src",$("#sale_list_html").attr("src"));
}
//单击商品管理，显示商品管理界面
function goods_manager(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".goods_manager").show();
	$("#goods_manager_html").attr("src","goods_manager.html");
}
//单击商品分类管理
function goods_kind(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".goods_kind").show();
	$("#goods_kind_html").attr("src","goods_kind.html");
}
//单击商品促销管理
function goods_promotion(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".goods_promotion").show();
	$("#goods_promotion_html").attr("src","goods_promotion.html");
}
//单击会员管理，显示会员管理列表
function customers(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	//
	$("#customer_manager").click();
	$(".public").css("display","none");
	$(".customer_manager").show();
}
//单击会员信息管理，显示会员信息管理界面
function customer_manager(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".customer_manager").show();
	$("#customer_manager_html").attr("src","customer_manager.html");
}
//单击会员等级管理，显示会员等级管理页面
function customer_level(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".customer_level").show();
	$("#customer_level_html").attr("src","customer_level.html");
}
//单击送货地址管理，显示会员送货地址管理页面
function addr_manager(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".addr_manager").show();
	$("#addr_manager_html").attr("src","addr_manager.html");
}
//单击会员评论管理，显示会员评论管理界面
function customer_comment(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".customer_comment").show();
	$("#customer_comment_html").attr("src","customer_comment.html");
}
//单击显示退货订单页面
function return_list_find(){
	$("#return_list_find").parent().children().removeClass("checked");
	$("#return_list_find").addClass("checked");
	$(".public").css("display","none");
	$(".return_list_area").show();
	$("#return_list_html").attr("src","return_list.html");
}
//加载页面后，自动给出未处理订单提示
function index_alert(){
	$("#manager_name").html(getCookie("manager_name"));
	manager_unique=getCookie("manager_unique");
	$.ajax({
		url:"index_alert.do",
		type:"post",
		data:{"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
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
	manager_unique=getCookie("manager_unique");
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
//点击库存盘点，显示库存盘点列表
function stocklist(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	//
	$("#stockmanager").click();
	$(".public").css("display","none");
	$(".stockmanager").show();
}

//单击库存查询，显示库存管理页面
function stockmanager(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".stockmanager").show();
	$("#stockmanager_html").attr("src","stockmanager.html");
}
//单击入库查询，显示入库查询列表
function instockmanager(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".instockmanager").show();
	$("#instockmanager_html").attr("src","instockmanager.html");
}

//单击出库查询，显示出库查询列表
function outstockamanger(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".outstockmanager").show();
	$("#outstockmanager_html").attr("src","outstockmanager.html");
}
//单击经营状况，显示经营状况管理界面
function  conditonsfunction(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".statistical").show();
	$("#statistical").click();
}
//单击滞销产品统计，显示滞销产品统计界面
function unsold(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".unsold").show();
	$("#unsold2").attr("src","unsold.html");
	
}
//单击进销量统计，显示进销量统计页面
function statistical(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	window.frames["statistical"];
	$("#statistical1").attr("src","statistical.html");
	$("#public").css("display","none");
	$(".statistical").show();
}
//点击未处理订单提醒，显示未处理订单
function show_undosale(){
	$.ajax({
		url:"sale_list/undo_sale.do",
		type:"post",
		data:{"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			$(".public").css("display","none");
			$(".undo_sale").show();
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
					tr+="<input type='button' value='修改订单' onclick='sale_list_modify1(this)'>";
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
//查询订单详情
function sale_list_modify1(btn){
	$(".public").css("display","none");
	$("#sale_list_modifypage").show();
	var sale_list_id=$(btn).parent().parent().children().eq(0).html();
	$.ajax({
		url:"sale_list/sale_list_find.do",
		type:"post",
		data:{"sale_list_id":sale_list_id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#sale_listbasetbody").empty();
				$("#sale_listSend").empty();
				$("#sale_list_detail").empty();
				var sale_list=result.data;
				var basetr="<tr><td>"+sale_list[0].sale_list_id+"</td><td>";
				if(sale_list[0].sale_type==0){
					basetr+="实体店订单</td><td>";
				}else if(sale_list[0].sale_type==1){
					basetr+="网络订单</td><td>";
				}
				basetr+=sale_list[0].cus_name+"</td><td>";
				basetr+=sale_list[0].cus_phone+"</td><td>";
				basetr+=sale_list[0].sale_list_total.toFixed(2)+"</td><td>";
				var time=new Date(sale_list[0].sale_list_datetime);
				basetr+=time.getFullYear()+"-"+time.getMonth()+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds()+"</td></tr>";
				$("#sale_listbasetbody").append(basetr);
				
				var sendtr="<tr><td>"+sale_list[0].cus_name+"</td><td>";
				sendtr+=sale_list[0].cus_phone+"</td><td>";
				sendtr+=sale_list[0].sale_list_address+"</td></tr>"
				$("#sale_listSend").append(sendtr);
				for(var k=0;k<sale_list.length;k++){
					var detailtr="<tr><td></td><td>";
					detailtr+=sale_list[k].goods_barcode+"</td><td>";
					detailtr+=sale_list[k].goods_name+"</td><td>";
					detailtr+=sale_list[k].sale_list_detail_count+"</td><td>";
					detailtr+=sale_list[k].sale_list_detail_price+"</td><td>";
					detailtr+=sale_list[k].sale_list_detail_count*sale_list[k].sale_list_detail_price+"</td><tr>";
					$("#sale_lisat_detail").append(detailtr);
				}
				//商品总金额
				$("#goods_total").html(sale_list[0].sale_list_total-sale_list[0].sale_list_delfee);
				//添加外送费
				$("#sale_list_delfee").val(sale_list[0].sale_list_delfee);
				//添加总价
				$("#sale_list_total").html(sale_list[0].sale_list_total);
				//修改订单处理情况
				//订单备注
				$("#sale_list_remarks").val(sale_list[0].sale_list_remarks);
				order();
				//设置订单状态
				var states=$("#newsale_list_state").children();
				for(var  i=0;i<states.length;i++){
					if(i+1==sale_list[0].sale_list_state){
						states[i].selected="true";
					}
				}
				states=$("#newsale_list_handlestate").children();
				for(var  i=0;i<states.length;i++){
					if(i+1==sale_list[0].sale_list_state){
						states[i].selected="true";
					}
				}
			}
		},
		error:function(){
			alert("加载异常");
		}
	});
}
//商品排序
function order(){
	var $trs=$("#sale_lisat_detail").children();
	for(var k=0;k<$trs.length;k++){
		$trs.eq(k).children().eq(0).html((k+2)/2);
	}
}

//保存订单修改
function sale_list_save(){
	totalchange();
	var sale_list_id=$("#sale_listbasetbody").children().eq(0).children().eq(0).html();
	var sale_list_delfee=$("#sale_list_delfee").val().trim();
	var sale_list_remarks=$("#sale_list_remarks").val().trim();
	var sale_list_state=$("#newsale_list_state option:selected").val();
	var sale_list_handlestate=$("#newsale_list_handlestate option:selected").val();
	var sale_list_total=parseFloat($("#sale_list_total").html().trim());
	$.ajax({
		url:"sale_list/sale_list_save.do",
		type:"post",
		data:{"sale_list_id":sale_list_id,"sale_list_delfee":sale_list_delfee,"sale_list_remarks":sale_list_remarks,"sale_list_handlestate":sale_list_handlestate,"sale_list_state":sale_list_state,"sale_list_total":sale_list_total},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$("#sale_list_modifypage").css("display","none");
				$("#sale_list_area").show();
				showorders();
				sale_list_find();
			}
		},
		error:function(){
			alert("订单保存异常");
		}
	});
	
}
//单击删除订单按钮，删除当前订单
function sale_list_delete(btn){
}
//取消订单修改
function sale_list_cancel(){
	$("#sale_list_modifypage").css("display","none");
	$("#sale_list_area").show();
}
//修改订单外送费金额后,自动修改订单总金额
function totalchange(){
	var sale_list_delfee=parseFloat( $ ("#sale_list_delfee").val());
	var goods_total=parseFloat($("#goods_total").html());
	
	$("#sale_list_total").html(sale_list_delfee+goods_total);
}
//点击显示未处理订单
function ret_undo(){
	$.ajax({
		url:"return_list/undo_ret.do",
		type:"post",
		data:{"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			$(".public").css("display","none");
			$(".undo_ret").show();
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
				tr+="<input type='button' value='更新订单' onclick='return_list_modify1(this)'></td></tr> ";
				$("#return_lists").append(tr);
				}
			}
		},
		error:function(){
		}
	});
}
//
//点击修改订单按钮，进入订单修改页面
function return_list_modify1(btn){
	var ret_list_unique=$(btn).parent().parent().children().eq(1).html();
	var cus_phone=$(btn).parent().parent().children().eq(4).html();
	$.ajax({
		url:"return_list/return_list_find.do",
		type:"post",
		data:{"ret_list_unique":ret_list_unique},
		dataType:"json",
		success:function(result){ 
			if(result.status==0){
				$("#return_listbasetbody").empty();
				$("#return_list_detail").empty();
				var ret_list=result.data;
				var cus=result.data1;
				var basetr="<tr><td class='public'>"+ret_list[0].ret_list_unique+"</td><td>"+ret_list[0].ret_list_id+"</td><td>";
				basetr+=cus[0].cus_name+"</td><td>";
				basetr+=cus[0].cus_phone+"</td><td>";
				basetr+=ret_list[0].ret_list_total+"</td><td>";
				var time=new Date(ret_list[0].ret_list_datetime);
				basetr+=time.getFullYear()+"-"+time.getMonth()+"-"+time.getDate()+" "+time.getHours()+":"+time.getMinutes()+":"+time.getSeconds() +"</td></tr>";
				$("#return_listbasetbody").append(basetr);
				$("#ret_total").html(ret_list[0].ret_list_total);
				for(var i=0;i<ret_list.length;i++){
					var detailtr="<tr><td></td><td>";
					if(detailtr!=null){
						detailtr+=ret_list[i].goods_name+"</td><td>";
					}else{
						detailtr+="</td><td>";
					}
					detailtr+=ret_list[i].ret_list_detail_count+"</td><td>";
					detailtr+=ret_list[i].ret_list_detail_price+"</td><td>";
					detailtr+=ret_list[i].ret_list_detail_count*ret_list[i].ret_list_detail_price+"</td></tr>";
					$("#return_list_detail").append(detailtr);
				}
			}
		},
		error:function(){
			alert("加载修改订单页面失败");
		}
	});
	$(".public").css("display","none");
	$("#return_list_modifypage").show();
}

//点击保存订单按钮，保存定单修改
function return_list_save(){
	//保证订单号唯一
	var ret_list_unique=$("#return_listbasetbody").children().eq(0).children().eq(0).html();
	var ret_list_state=$("#ret_list_state option:selected").val();
	var ret_list_handlestate=$("#ret_list_handlestate option:selected").val();
	$.ajax({
		url:"return_list/return_list_save.do",
		type:"post",
		data:{"ret_list_unique":ret_list_unique,"ret_list_state":ret_list_state,"ret_list_handlestate":ret_list_handlestate},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$("#return_list_area").show();
				$("#return_list_modifypage").css("display","none");
				showorders();
				return_list_find()
				
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("保存订单失败");
		}
	});
}

//点击取消修改按钮，取消订单修改
function return_list_cancel(){
	$("#return_list_modifypage").css("display","none");
	$("#return_list_area").show();
}
//点击进货及滞销提醒，显示进货及滞销提醒界面
function warning1(){
	$(".public").css("display","none");
	$(".warning").show();
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$("#warning1").attr("src","warning.html");
}
////加载页面后，自动给出进货提醒
//function instock(){
//	$.ajax({
//		url:"return_list/return_list_save.do",
//		type:"post",
//		data:{"ret_list_unique":ret_list_unique,"ret_list_state":ret_list_state,"ret_list_handlestate":ret_list_handlestate},
//		dataType:"json",
//		success:function(result){
//			if(result.status==0){
//				alert(result.msg);
//				$("#return_list_area").show();
//				$("#return_list_modifypage").css("display","none");
//				showorders();
//				return_list_find()
//				
//			}else{
//				alert(result.msg);
//			}
//		},
//		error:function(){
//			alert("失败");
//		}
//	});
//}

//点击调换货物，显示点换货物申请页面
function adjustfunction(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	
	$("#myadjust").click();
}
//单击我的申请，显示我的申请界面
function myadjust(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".myadjust").show();
	$("#myadjust1").attr("src","myadjust.html");
}
//单击其他申请，显示其他申请界面
function otheradjust(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".otheradjust").show();
	$("#otheradjust1").attr("src","otheradjust.html");
}
//单击我的响应，显示我的响应界面
function responseadjust(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$(".responseadjust").show();
	$("#responseadjust1").attr("src","responseadjust.html");
}

//加载页面后，自动加载该Boss对应的店铺
function shops_uniquefind(){
	$.ajax({
		url:"shops_uniquefind.do",
		type:"post",
		data:{"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#shops").empty();
				var shops=result.data;
				if(shops.length==0){
					window.location.href="newShop.html";
				}else if(shops.length==1){
					var li="<li class='checked'>"+shops[0].shop_name+"</li>";
					var $li=$(li);
					$li.data("shop_unique",shops[0].shop_unique);
					$li.data("manager_unique",getCookie("manager_unique"));
					
					$("#shops").append($li);
					if($("#shops").children().length==0){
						window.location.reload();
					}
					addCookie("shop_unique", shops[0].shop_unique,10);
					shop_unique=shops[0].shop_unique;
				}else{
					var li="<li class='checked'>"+"全部"+"</li>";
					$li=$(li);
					$li.data("manager_unique",getCookie("manager_unique"));
					$("#shops").append($li);
					for(var i=0;i<shops.length;i++){
						if(i==0){
							var li="<li>"+shops[i].shop_name+"</li>";
						}else{
							var li="<li>"+shops[i].shop_name+"</li>";
						}
						var $li=$(li);
						$li.data("shop_unique",shops[i].shop_unique);
						$li.data("manager_unique",getCookie("manager_unique"));
						$("#shops").append($li);
					}
				}
				li="<li><input type='button' value='添加新店' onclick='addNewShop()' class='change'></li>";
				$("#shops").append(li);
			}
			findPurCartGoods();
		},
		error:function(){
			alert("查询失败");
		}
	});
}
//单击店铺列表，更改店铺编号
function changeshop(){
	shop_unique=$(this).data("shop_unique");
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	addCookie("shop_unique", shop_unique, 10);
	var $iframe=$("iframe");
	for(var i=0;i<$iframe.length;i++){
		$iframe.eq(i).attr("src",$iframe.eq(i).attr("src"));
	}
}

//单击商品采购，显示商品采购界面
function toPurchase(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$("#toPurchase").show();
	$("#toPurchase").attr("src","purchase.html");
}

//单击修改账号密码，进入修改账号密码界面
function toChangePwd(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$("#toPurchase").show();
	$("#toPurchase").attr("src","changePwd.html")
	
}
//定期检查是否有新存在的订单
function checkNewOrder(){
	console.log("检查新订单");
	var time=new Date();
	var start=new Date(time.getTime()-300*1000);
	var startTime=start.getFullYear()+"-"+start.getMonth()+"-"+start.getDate()+" "+start.getHours()+":"+start.getMinutes()+":"+start.getSeconds();
	console.log(startTime);
	$.ajax({
		url:"sale_list/checkNewOrder.do",
		type:"post",
		data:{"shop_unique":shop_unique,"startTime":startTime,"manager_unique":manager_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var data=result.data;
				var k=0;
				for(var i=0;i<data.length;i++){
					if(data[i].sale_list_datetime-start>0){
						k++
					}
				}
				$("#newOrderWarning").html(k);
			}else{
				$("#newOrderWarning").html(0);
			}
		},
		error:function(){
			alert("查询新订单异常！请检查网络！")
		}
	})
}
//点击添加新的商品，跳转到商铺添加页面
function addNewShop(){
	window.location.href="newShop.html";
}
//点击商铺管理，显示商铺管理页面
function toShopManager(){
	$(this).parent().children().removeClass("checked");
	$(this).addClass("checked");
	$(".public").css("display","none");
	$("#toPurchase").show();
	$("#toPurchase").attr("src","shopManager.html");
}

//查询购物车里商品数量
function findPurCartGoods(){
	$.ajax({
		url:"purchase/findPurCartGoods.do",
		type:"post",
		data:{"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goodss=result.data;
				var k=0;
				for(var i=0;i<goodss.length;i++){
					if(goodss[i].purchase_list_detail_count!=null){
						k+=goodss[i].purchase_list_detail_count;
					}
				}
				$("#newPurchase").html(k);
			}
			if(result.status==1){
				$("#newPurchase").html(0);
			}
		},
		error:function(){
			alert("查询购物车产品数量失败！");
		}
	})
}