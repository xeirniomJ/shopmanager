var shop_unique=getCookie("shopUnique");
$(function(){
	//
	$("#manager_pwd").focus();
	//查询店铺详细信息并展示
	findShopDetail();
	//点击提交按钮，更新店铺信息
	$("#submit").click(updateShopDetail);
	//非空测试
	$(":text,:password").blur(testNull);
	//密码相同测试
	$(":password").blur(testSame);
	$("#manager_pwd").blur(testLong);
	//点击取消按钮，返回管理员查询列表
	$("#cancel").click(cancelToShopManager);
});
//查询店铺详细信息
function findShopDetail(){
	$.ajax({
		url:"shops_uniquefind.do",
		type:"post",
		data:{"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var shop=result.data[0];
				$("#shop_name").val(shop.shop_name);
				$("#shop_remark").val(shop.shop_remark);
				if(shop.manager_account!=null){
					$("#manager_account").val(shop.manager_account);
				}
				$("#shop_phone").val(shop.shop_phone);
				$("#shop_address_detail").val(shop.shop_address_detail);
				if(shop.review_status==1){
					$("#review_status").val("尚未提交申请");
				}else if(shop.review_status==2){
					$("#review_status").val("申请已提交，等待审核!");
				}else if(shop.review_status==3){
					$("#review_status").val("审核未通过");
					$("#review_reason").parents("tr").show();
					$("#review_reason").val(shop.review_reason);
				}else if(shop.review_status==4){
					$("#review_status").val("审核已通过!");
					var $inputs=$("input");
					for(var i=0;i<$inputs.length-5;i++){
						if($inputs.eq(i).val()!=null&&$inputs.eq(i).val()!=""){
							$inputs.eq(i).attr("disabled","true");
						}
					}
				}
				if(shop.power_supplier==2){
					$("label").click();
				}
			}
			if(result.status==1){
				console.log("没有满足条件的店铺");
			}
		},
		error:function(){
			alert("店铺详细信息查询失败");
		}
	});
}
//提交修改
function updateShopDetail(){
	var manager_pwd=$("#manager_pwd").val().trim();
	var reManager_pwd=$("#reManager_pwd").val().trim();
	var shop_phone=$("#shop_phone").val().trim();
	var shop_address_detail=$("#shop_address_detail").val().trim();
	var shop_name=$("#shop_name").val().trim();
	var manager_account=$("#manager_account").val().trim();
	var shop_remark=$("#shop_remark").val().trim();
	var flag=testNull();
	var power_supplier=1;
	if($("#power_supplier").is(":checked")){
		power_supplier=2;
	}
	if(!flag){
		return;
	}
	 $("#updateForm").ajaxSubmit({
		url: "shops/updateShopDetail.do",  
	   contentType: "application/x-www-form-urlencoded; charset=utf-8",
       data:$('#img-form-agree').serialize(),
       data:{"manager_pwd":encodeURI(manager_pwd),"shop_phone":encodeURI(shop_phone),"shop_address_detail":encodeURI(shop_address_detail),
    	   "shop_name":encodeURI(shop_name),"shop_unique":shop_unique,"manager_account":manager_account,"shop_remark":encodeURI(shop_remark),"power_supplier":power_supplier},
       dataType:"json",
       success:function(result){
    	   if(result.status==0){
    		   window.parent.document.getElementById("rightFrame").setAttribute("src","shopManager.html");
    		   alert(result.msg);
    	   }
    	   if(result.status==1){
    		   alert(result.msg);
    	   }
       },
       error:function(){
    	   alert("更新失败，请检查网络");
       }
	});
}
//非空检测
function testNull(){
	var $inputs=$(":text");
	for(var i=0;i<$inputs.length;i++){
		if($inputs.eq(i).val().trim()==null||$inputs.eq(i).val().trim()==""){
			$inputs.eq(i).parent().next("td").addClass("warningtd");
			if($("#shop_remark").val()==""||$("#shop_remark").val()==null){
				continue;
			}
			return false;
		}else{
			$inputs.eq(i).parent().next("td").removeClass("warningtd");
		}
	}
	return true;
}
//密码相同测试
function testSame(){
	var inputs=$(":password");
	for(var i=0;i<inputs.length-1;i++){
		if(inputs.eq(i+1).val()!=inputs.eq(i).val()){
			inputs.eq(i+1).parent().next("td").addClass("warning");
			return;
		}else{
			inputs.eq(i+1).parent().next("td").removeClass("warning");
		}
	}
}
//密码长度检测
function testLong(){
	var pwd=$("#manager_pwd").val().trim();
	if(pwd!=""&&pwd.length<8){
		alert("密码长度过短");
	}
}
//点击取消按钮，返回到管理员信息列表
function cancelToShopManager(){
	window.parent.document.getElementById("rightFrame").setAttribute("src","shopManager.html");
}