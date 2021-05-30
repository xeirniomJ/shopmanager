$(function(){
	//点击提交按钮，将店铺选择的功能进行修改
	$("#submit").click(submitChange);
});

//单击保存修改按钮，将店铺选择的功能进行修改
function submitChange(){
	var power_supplier=1;
	if($("#power_supplier").is(":checked")){
		power_supplier=2;
	}
	$.ajax({
		url:"shop/updateShop.do",
		type:"post",
		data:{"power_supplier":power_supplier},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				
			}
			if(result.status==1){
				
			}
		},
		erro:function(){
			alert("更新失败，请检查网络！");
		}
	});
}