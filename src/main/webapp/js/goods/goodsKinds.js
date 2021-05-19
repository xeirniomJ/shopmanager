var shop_unique=getCookie("shop_unique");
$(function(){
	//加载商品分类信息
	loadKinds();
	//点击提交按钮，将选择的商品分类加载到数据库中
	$("#submitChange").click(submitKinds);
});

/**
 * 
 * 页面加载后，自动加载所有商品分类
 * 加载商品分类同时，加载店铺商品分类，并将其设置为选中状态
*/
function loadKinds(){
	$.ajax({
		url:"queryKinds.do",
		type:"post",
		data:{"shop_unique":shop_unique},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var kinds=result.data;
				var numArr=new Array();
				var nameArr=new Array();
				var shopKinds=result.data1;
				for(var i=0;i<kinds.length;i++){
					var flag=true;
					for(var j=0;j<numArr.length;j++){
						if(kinds[i].goods_kind_parunique!=0){
							flag=false;
							break;
						}
						if(kinds[i].goods_kind_parunique==0&&kinds[i].goods_kind_parunique==numArr[j]){
							console.log(kinds[i]);
							flag=false;
							break;
						}
					}//内层for循环结束
					if(flag){
						   numArr[numArr.length]=kinds[i].goods_kind_unique;
						   nameArr[nameArr.length]=kinds[i].goods_kind_name;
					}
				}//外层for循环结束
				for(var k=0;k<numArr.length;k++){
					var groupFlag=false;
					var gtr="";
					for(var m=0;m<shopKinds.length;m++){
						if(shopKinds[m].goods_kind_unique==numArr[k]){
							groupFlag=true;
						}
					}
					if(groupFlag){
						gtr="<tr class='groups'><td>一级分类</td><td>"+nameArr[k]+"</td><td><label><input type='checkbox' name='group' class='"+nameArr[k]+"' onclick='checkGroup(this)' checked='checked'></label></td></tr>";
					}else{
						gtr="<tr class='groups'><td>一级分类</td><td>"+nameArr[k]+"</td><td><label><input type='checkbox' name='group' class='"+nameArr[k]+"' onclick='checkGroup(this)'></label></td></tr>";
					}
					$gtr=$(gtr);
					$gtr.data("goods_kind_unique",numArr[k]);
					$gtr.data("goods_kind_name",nameArr[k]);
					$gtr.data("goods_kind_parunique",0);
					$("#kindsList").append($gtr);
					for(var j=0;j<kinds.length;j++){
						var kindFlag=false;
						var ktr="<tr><td></td><td>";
						for(var m=0;m<shopKinds.length;m++){
							if(shopKinds[m].goods_kind_unique==kinds[j].goods_kind_unique){
								kindFlag=true;
							}
						}
						if(kinds[j].goods_kind_parunique==numArr[k]){
							if(kindFlag){
								ktr+=kinds[j].goods_kind_name+"</td><td><label><input type='checkbox' name='"+nameArr[k]+"' onclick='checkKind(this)' class='"+nameArr[k]+"' checked='checked'></label></td></tr>";
							}else{
								ktr+=kinds[j].goods_kind_name+"</td><td><label><input type='checkbox' name='"+nameArr[k]+"' onclick='checkKind(this)' class='"+nameArr[k]+"'></label></td></tr>";
							}
							$ktr=$(ktr);
							$ktr.data("goods_kind_unique",kinds[j].goods_kind_unique);
							$ktr.data("goods_kind_name",kinds[j].goods_kind_name);
							$ktr.data("goods_kind_parname",nameArr[k]);
							$ktr.data("goods_kind_parunique",numArr[k]);
							$("#kindsList").append($ktr);
						}//if()判定结束
					}//for()循环结束
				}
			}//if()判定结束
		},
		error:function(){
			console.log("加载失败！");
		}
	});
}
/**
 * 点击商品小类，若选中，则大类选中
 * 若未选中，则查看其它同一大类下小类，若均为选中，则大类未选中，否则大类选中
 * @param btn
 */
function checkKind(btn){
	var parunique=$(btn).parents("tr").data("goods_kind_parname");
	var flag=false;
	var trClass=$(btn).attr("class");
	var $trs=$(btn).parents("tbody").find("."+trClass+"");
	for(var i=1;i<$trs.length;i++){
		if($trs.eq(i).is(":checked")){
			flag=true;
			break;
		}
	}
	var $par=$("[class='"+parunique+"']");
	if(flag){
		$par[0].checked=true;
	}else{
		$par[0].checked=false;
	}
}
/**
 * 选中大类，则选中全部小类，
 * 取消大类，取消全部小类选中
 * @param btn
 */
function checkGroup(btn){
	var trClass=$(btn).attr("class");
	var $trs=$(btn).parents("tbody").find("."+trClass);
	var flag=false;
	if($(btn).is(":checked")){
		for(var i=1;i<$trs.length;i++){
			$trs[i].checked=true;
		}
	}else{
		for(var i=1;i<$trs.length;i++){
			$trs[i].checked=false;
		}
	}
}
/**
 * 提交新的店铺分类
 */
function submitKinds(){
	var $trs=$("#kindsList").children();
	var message="";
	for(var i=0;i<$trs.length;i++){
		if($trs.eq(i).find("input").is(":checked")){
			message+=shop_unique+":";
			message+=$trs.eq(i).data("goods_kind_unique")+":";
			message+=$trs.eq(i).data("goods_kind_parunique")+":";
			message+=$trs.eq(i).data("goods_kind_name")+";";
		}
	}
	message.substring(0,message.length-1);
	if(message.length==0){
		alert("没有选择的商品分类！");
		return;
	}
	$.ajax({
		url:"submitShopKinds.do",
		type:"post",
		data:{"shop_unique":shop_unique,"message":message},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
			}
			if(result.status==1){
				alert(result.msg);
			}
		},
		error:function(){
			alert("保存失败，请稍后重试！");
		}
	});
}