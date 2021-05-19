//商品分类管理
//页面加载后，调用函数
$(function(){
	//自动添加一级商品分类信息
	addFristLevel();
	//当一级商品分类信息修改后，自动添加二级商品分类信息
	$("#fristLevel").on("change",addSecLevel);
	//当二级商品分类信息修改后，自动添加三级商品分类信息
	$("#secLevel").on("change",addThridLevel);
	
	//进入商品添加页面后，自动添加所有一级商品分类信息,二级商品分类信息，三级商品分类信息
	addFLevels();
	addSLevels();
	addTLevels();
	//商品一级分类填写后，自动修改二级分类datalist列表
	$("#FLevel").on("blur",chSLevels);
	//商品二级分类填写后，自动修改三级分类datalist列表
	$("#SLevel").on("blur",chTLevels);
});//页面加载后调用的函数结束

//商品以及分类修改后，自动修改二级分类datalist列表
function chSLevels(){
	var goodsKindName=$("#FLevel").val().trim();
	$.ajax({
		url:"../../goods_kind/chSLevels.do",
		type:"post",
		dataType:"json",
		data:{"goodsKindName":goodsKindName},
		success:function(result){
			if(result.status==0){
				$("#secLevels").empty();
				var datas=result.data;
				for(var i=0;i<datas.length;i++){
					var option="<option value='"+datas[i].goodsKindName+"'>";
					$("#secLevels").append(option);
				}//for循环结束
			}
		},
		error:function(){
			console.log("查询商品信息失败！");
		}
	});
}//方法结束
//商品二级分类天下完毕后，自动修改三级分类datalist
function chTLevels(){
	var goodsKindName=$("#SLevel").val().trim();
	$.ajax({
		url:"../../goods_kind/chSLevels.do",
		type:"post",
		data:{"goodsKindName":goodsKindName},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#thridLevels").empty();//如果二级分类已存在，则自动加载相关三级分类信息，以避免重复
				var datas=result.data;
				for(var i=0;i<datas.length;i++){
					var option="<option value='"+datas[i].goodsKindName+"'></option>";
					$("#thridLevels").append(option);
				}
			}
		},
		error:function(){
			console.log("查询商品三级分类失败！");
		}
	});
}