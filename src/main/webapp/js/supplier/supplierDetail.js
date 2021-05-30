var manager_token=getCookie("manager_token");
var manager_unique=getCookie("manager_unique");
var supplier_unique=getCookie("supplier_unique");
var k=2;
var flag=true;
$(function(){
	//进入页面后，自动加载供应商信息
	loadSupplierMessage();
	//点击审核通过按钮，通过供应商审核并返回申请界面
	$("#passExamine").click(passExamine);
	//点击审核不通过，弹出审核不通过原因界面，用以输入审核不通过原因，并确认审核不通过
	$("#notPassExamine").click(toNotPassReason);
	//点击确认按钮，提交审核未通过及其原因
	$("#submitReason").click(submitReason);
});

//页面加载后，自动加载供应商相关信息
function loadSupplierMessage(){
	var supplier_unique=getCookie("supplier_unique");
	$.ajax({
		url:"loadSupplierMessage.do",
		type:"post",
		data:{"manager_unique":manager_unique,"manager_token":manager_token,"supplier_unique":supplier_unique.trim()},
		dataType:"json",
		success:function(result){
			if(result.status==2){
				alert(result.msg);
				window.parent.location.href="../../login.html";
			}
			if(result.status==1){
				alert(result.msg);
			}
			if(result.status==0){
				var sup=result.data;
				$("#company_leagl").html(sup.company_leagl);
				$("#company_name").html(sup.company_name);
				$("#supplier_name").html(sup.supplier_name);
				$("#supplier_address").html(sup.supplier_address);
				$("#supplier_phone").html(sup.supplier_phone);
				$("#supplier_remark").html(sup.supplier_remark);
				$("#supplier_longitude").val(sup.supplier_longitude);
				$("#supplier_latitude").val(sup.supplier_latitude);
				if(sup.licence_path!=null){
					$("#licence").attr("src",sup.licence_path);
				}
				if(sup.identitypositive_path!=null){
					$("#identitypositive").attr("src",sup.identitypositive_path);
				}
				if(sup.identityopposite_path!=null){
					$("#identityopposite").attr("src",sup.identityopposite_path);
				}
			}
		}
	});
}
//点击审核通过，通过供应商审核并返回申请界面
function passExamine(){
	$.ajax({
		url:"passExamine.do",
		type:"post",
		data:{"manager_token":manager_token,"manager_unique":manager_unique,"supplier_unique":supplier_unique},
		dataType:"json",
		success:function(result){
			if(result.status==2){
				alert(result.msg);
				window.parent.location.href="../../login.html";
			}
			if(result.status==1){
				alert(result.msg);
			}
			if(result.status==0){
				$(".function").css("display","none");
				$(".passExamine").show();
				toExamSup();
			}
		},
		error:function(){
			alert("审核失败，请稍后重试！");
		}
	});
}
//等待2S后，页面返回申请界面
function toExamSup(){
	if(k>=1){
		if(flag){
			$("#toExamineStatus").html("审核通过"+k+"秒后返回申请界面！"+"<a onclick='returnNow()'>立即返回</a>");
		}else{
			$("#toExamineStatus").html("审核未通过"+k+"秒后返回申请界面！");
		}
		k--;
		setTimeout(toExamSup, 1000);
	}else{
		if(flag){
			window.parent.document.getElementById("rightFrame").setAttribute("src","html/supplier/examsup.html");
		}else{
			window.parent.document.getElementById("rightFrame").setAttribute("src","html/supplier/supCheck.html")
		}
	}
}
//立即返回
function returnNow(){
	window.parent.document.getElementById("rightFrame").setAttribute("src","html/supplier/examsup.html")
}
//点击审核不通过按钮，弹出审核不通过原因输入框
function toNotPassReason(){
	$(".notPassReason").show();
}

//点击确认，将审核不通过原因提交并修改审核状态
function submitReason(){
	var examinestatus_reason=$("#examinestatus_reason").val();
	if(examinestatus_reason==null||examinestatus_reason.length<10){
		alert("请输入不少于10字的原因！");
		return ;
	}
	$.ajax({
		url:"supSubmitReason.do",
		type:"post",
		data:{"manager_unique":manager_unique,"manager_token":manager_token,"supplier_unique":supplier_unique},
		dataType:"json",
		success:function(result){
			if(result.status==2){
				alert(result.msg);
				window.parent.location.href="../../login.html";
			}
			if(result.status==1){
				alert(result.msg);
			}
			if(result.status==0){
				$(".notPassReason").css("display","none");
				$(".function").css("display","none");
				$(".passExamine").show();
				flag=false;
				toExamSup();
			}
		}
	});
}

