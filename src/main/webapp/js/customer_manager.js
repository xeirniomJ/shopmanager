//单击修改信息按钮，进入信息修改页面；
function customer_modify(btn){
	var cus_id=$(btn).parent().parent().children().eq(2).html();
	var endDate=null;
	$.ajax({
		url:"customer/findCus.do",
		type:"post",
		data:{"shop_unique":shop_unique,"cus_id":cus_id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var cus=result.data;
				$("#cus_id").html(cus_id);
				$("#cus_account").html(cus.cus_account);
				$("#cus_pwd").val(cus.cus_pwd);
				$("#cus_name").val(cus.cus_name);
				$("#cus_sex").val(cus.cus_sex);
				$("#cus_alias").val(cus.cus_alias);
				$("#cus_phone").val(cus.cus_phone);
				$("#cus_points").html(cus.cus_points);
				$("#cus_shop_points").html(cus.cus_shop_points);
				$("#cus_birthday").val(cus.cus_birthday);
				$("#cus_email").val(cus.cus_email);
			}
		},
		error:function(){
			alert("查询客户信息失败");
		}
	});
	$(".showarea").css("display","none");
	$(".customer_new").css("display","none");
	$(".customer_modifypage").show();
	customer_find();
}
//单击添加新会员按钮，显示添加会员页面
function customer_new(){
	$("#cus_id").html("");
	$("#cus_pwd").val("");
	$("#cus_name").val("");
	$("#cus_sex").val("");
	$("#cus_alias").val("");
	$("#cus_phone").val("");
	$("#cus_birthday").val("");
	$("#cus_email").val("");
	$("#cus_points").html("0");
	$("#cus_shop_points").html("0");
	$(".update").css("dispaly","none");
	$(".showarea").css("display","none");
	$(".customer_modifypage").css("display","none");
	$(".customer_new").show();
	
}
//单击删除会员按钮，删除会员信息
function customer_delete(){
	var flag=confirm("是否删除该会员记录，该操作可能会导致数据库错误");
	if(flag){
		alert("该会员的相关信息已删除");
	}
}
//单击保存修改按钮，将修改后的信息更新到数据库
function customer_modify_save(){
	var cus_id=$("#cus_id").html();
	var cus_pwd=$("#cus_pwd").val();
	var cus_name=$("#cus_name").val();
	var cus_sex=$("#cus_sex").val();
	var cus_alias=$("#cus_alias").val();
	var cus_phone=$("#cus_phone").val();
	var cus_birthday=$("#cus_birthday").val();
	var cus_email=$("#cus_email").val().trim();
	if(cus_birthday!=null&&cus_birthday!=""){
		cus_birthday+=" 01:01:01";
	}
	$.ajax({
		url:"customer/updateCus.do",
		type:"post",
		data:{"cus_id":cus_id,"cus_name":cus_name,"cus_sex":cus_sex,"cus_alias":cus_alias,"cus_phone":cus_phone,"cus_birthday":cus_birthday,"cus_email":cus_email},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$("#customer_find").click();
			}
		},
		error:function(){
			alert("更新失败");
		}
	});
	$(".showarea").css("display","none");
	$(".customer_manager").show();
	customer_find();
}
//单击添加会员按钮，将新的会员信息添加到数据库中
function customer_new_save(){
	var cus_account=$("#cus_account_new").val().trim();
	var cus_name=$("#cus_name").val().trim();
	var cus_sex=$("#cus_sex").val().trim();
	var cus_alias=$("#cus_alias").val().trim();
	var cus_phone=$("#cus_phone").val().trim();
	var cus_birthday=$("#cus_birthday").val();
	var cus_email=$("#cus_email").val().trim();
	if(cus_birthday!=null&&cus_birthday!=""){
		cus_birthday+=" 01:01:01";
	}
	$.ajax({
		url:"customer/newCus.do",
		type:"post",
		data:{"cus_account":cus_account,"cus_name":cus_name,"cus_sex":cus_sex,"cus_alias":cus_alias,"cus_phone":cus_phone,"cus_birthday":cus_birthday,"cus_email":cus_email},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
			}
		},
		error:function(){
			alert("添加失败");
		}
	});
	$(".showarea").css("display","none");
	$(".customer_manager").show();
}
//单击取消按钮，取消会员的更新和新建
function customer_modify_cancel(){
	$(".showarea").css("display","none");
	$(".customer_manager").show();
}

//单击会员查询，查询满足条件的会员
function customer_find(){
	var customermessage =$("#customermessage").val().trim();
	$.ajax({
		url:"customer/findCuss.do",
		type:"post",
		data:{"shop_unique":shop_unique,"customermessage":customermessage,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var cus=result.data;
				$("#cus_list").empty();
				for(var i=0;i<cus.length;i++){
					var tr="<tr><td class='public'><input type='checkbox' name='all' id='allcheckbox' class='public'></td><td></td><td>"+cus[i].cus_id+"</td><td>";
					tr+=cus[i].shop_name+"</td><td>";
					if(cus[i].cus_name!=null){
						tr+=cus[i].cus_name+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					if(cus[i].cus_phone!=null){
						tr+=cus[i].cus_phone+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					tr+=cus[i].cus_points+"</td><td>";
					if(cus[i].cus_shop_points!=null){
						tr+=cus[i].cus_shop_points+"</td><td>";
					}else{
						tr+="0</td><td>";
					}
					if(cus[i].cus_birthday!=null){
						tr+=cus[i].cus_birthday+"</td><td>";
					}else{
						tr+="</td><td>";
					}
					tr+="<input type='button' value='更新会员' id='updatecus' onclick='customer_modify(this)'></td></tr>";
					$("#cus_list").append(tr);
				}
				order();
			}
		},
		error:function(){
			alert("查询异常");
		}
	});
}

//排序
function order(){
	var $trs=$("#cus_list").children();
	for(var i=0;i<$trs.length;i++){
		$trs.eq(i).children().eq(1).html(i+1);
	}
}