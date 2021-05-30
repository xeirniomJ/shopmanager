var shop_unique = getCookie("shop_unique");
		$(function(){
			$("#query").focus();
			//单击修改商品类型，进入商品类型修改界面
			$("#goods_kind_modify").click(goods_kind_modify);
			//单击删除类型按钮，删除该类型
			$("#goods_kind_delete").click(goods_kind_delete);
			//点击保存修改按钮，将修改过的商品类型保存
			$("#goods_kind_save").click(goods_kind_save);
			//点击取消保存按钮，取消商品类型修改
			$(".goods_kind_cancel").click(goods_kind_cancel);
			//点击添加新类按钮，弹出添加新类页面
			$("#newkind").click(newkind);
			//点击保存新类型按钮，将新类型添加到数据库
			$("#goods_kind_new").click(goods_kind_new);
			//加载页面时，自动添加大类
			add_goods_kind_group();
			//加载页面时，自动添加商品小类
			add_goods_kind_name();
			//修改大类后，自动修改小类填充
			$("#ingoods_kind_group").change(changegroup);
			//单击查询按钮，查询商品类型
			$("#query").click(queryGoods_kind);
			//输入框输入商品大类时，自动加载
		});
//单击修改商品类型，进入商品类型修改界面
function goods_kind_modify(btn){
	var goods_kind_id=$(btn).parent().parent().children().eq(1).html();
	$.ajax({
		url:"goods_kind/findGoods_kind.do",
		type:"post",
		data:{"goods_kind_id":goods_kind_id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var kind=result.data;
				for(var i=0;i<kind.length;i++){
					$("#goods_kind_id").html(kind[i].goods_kind_id);
					$("#goods_kind_group").val(kind[i].n);
					$("#goods_kind_name").val(kind[i].goods_kind_name);
				}
				$(".showarea").css("display","none");
				$("#goods_kind_modifypage").show();
			}
		},
		error:function(){
			alert("进入产品修改页面失败，请稍后重试");
		}
	});
}
//单击删除类型按钮，删除该类型
function goods_kind_delete(){
	var flag=confirm("是否删除该类型，可能会导致查询错误");
	if(flag){
		$(".showarea").css("display","none");
		$("#goods_kind").show();
	}
}
//点击保存修改按钮，将修改过的商品类型保存
function goods_kind_save(){
	var goods_kind_id=$("#goods_kind_id").html();
	var goods_kind_parname=$("#goods_kind_group").val().trim();
	var goods_kind_name=$("#goods_kind_name").val().trim();
	if(goods_kind_group==null||goods_kind_group==""){
		alert("商品大类不能为空");
		return;
	}
	if(goods_kind_group==null|goods_kind_name==""){
		alert("商品小类不能为空");
		return;
	}
	$.ajax({
		url:"goods_kind/modifyGoods_kind.do",
		type:"post",
		data:{"goods_kind_id":goods_kind_id,"goods_kind_parname":goods_kind_parname,"goods_kind_name":goods_kind_name,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$(".showarea").css("display","none");
				$("#goods_kind").show();
				queryGoods_kind();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("进入产品修改页面失败，请稍后重试");
		}
	});
}
//点击取消保存按钮，取消商品类型修改
function goods_kind_cancel(){
	$(".showarea").css("display","none");
	$("#goods_kind").show();
}
//点击添加新类按钮，弹出添加新类页面
function newkind(){
	$(".showarea").css("display","none");
	$("#newkindpage").show();
}

//点击保存新类型按钮，将新类型添加到数据库
function goods_kind_new(){
	console.log(getCookie("manager_unique"));
	var goods_kind_parname=$("#newgoods_kind_group").val().trim();
	if(goods_kind_parname==null||goods_kind_parname==""){
		alert("大类不能为空");
		return;
	}
	var goods_kind_name=$("#newgoods_kind_name").val().trim();
	if(goods_kind_name==null||goods_kind_name==""){
		alert("小类不能为空");
		return;
	}
	
	$.ajax({
		url:"goods_kind/newGoods_kind.do",
		type:"post",
		data:{"goods_kind_parname":goods_kind_parname,"goods_kind_name":goods_kind_name,"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				queryGoods_kind();
				$(".showarea").css("display","none");
				$("#goods_kind_modifypage").show();
				$(".showarea").css("display","none");
				$("#goods_kind").show();
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("进入产品修改页面失败，请稍后重试");
		}
	});
}

//加载页面后，自动填充商品大类
function add_goods_kind_group(){
	
	$.ajax({
		url:"goods_kind/add_goods_kind_group.do",
		type:"post",
		data:{"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			
			if(result.status==0){
				var groups=result.data;
				var a="<option value=0>请选择商品大类</option>";
				$("#ingoods_kind_group").append(a);
				for(var i=0;i<groups.length;i++){
					var option="<option value="+groups[i].goods_kind_unique+">"+groups[i].goods_kind_name+"</option>";
					$("#ingoods_kind_group").append(option);
				}
			}
		},
		error:function(){
			alert("加载商品大类型失败，请检查网络");
		}
	});
}
//加载页面时，自动添加商品小类
function add_goods_kind_name1(){
	var option="<option value=0>请选择商品小类</option>";
	$("#ingoods_kind_name").append(option);
}
//修改大类后，自动修改小类填充
function changegroup(){
	var goods_kind_parunique=$("#ingoods_kind_group option:selected").val();
	$("#ingoods_kind_name").empty();
	var option="<option value=0>请选择商品小类</option>";
	$("#ingoods_kind_name").append(option);
	if(goods_kind_group=="请选择商品大类"){
		add_goods_kind_name();
		return;
	}
	$.ajax({
		url:"goods_kind/inadd_goods_kind_name.do",
		type:"post",
		data:{"goods_kind_parunique":goods_kind_parunique,"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#ingoods_kind_name").empty() ;
				var option="<option value=0>请选择商品小类</option>";
				$("#ingoods_kind_name").append(option);
				var groups=result.data;
				for(var i=0;i<groups.length;i++){
					var option="<option value="+groups[i].goods_kind_unique+">"+groups[i].k+"</option>";
					$("#ingoods_kind_name").append(option);
				}
			}
		},
		error:function(){
			alert("加载商品大类型失败，请检查网络");
		}
	});
}
//单击查询按钮，查询商品种类
function queryGoods_kind(){
	var goods_kind_parunique=$("#ingoods_kind_group option:selected").val();
	var goods_kind_unique=$("#ingoods_kind_name option:selected").val();
	$.ajax({
		url:"goods_kind/queryGoods_kind.do",
		type:"post",
		data:{"goods_kind_parunique":goods_kind_parunique,"goods_kind_unique":goods_kind_unique,"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$("#goods_kind_list").empty();
				var kinds=result.data;
				for(var i=0;i<kinds.length;i++){
					var tr="<tr><td>"+(i+1)+"</td><td>"+kinds[i].goods_kind_id+"</td><td>";
					tr+=kinds[i].g+"</td><td>";
					tr+=kinds[i].k+"</td><td>";
					tr+='<input type="button" value="更新类型" onclick="goods_kind_modify(this)"></td></tr>';
					$("#goods_kind_list").append(tr);
				}
				if(kinds.length==0){
					alert("没有满足条件的商品");
				}
			}
		},
		error:function(){
			alert("查询商品类型失败");
		}
	});
}
