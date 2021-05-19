//点击查询商品按钮，显示商品查询明细
function goods_find(){
	var goods_message=$("#goods_message").val();
	$.ajax({
		url:"goods/find_goods.do",
		type:"post",
		data:{"shop_unique":shop_unique,"goods_message":goods_message},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goodss=result.data;
				$("#goodss").empty();
				for(var i=0;i<goodss.length-1;i++){
					var tr="<tr><td>"+goodss[i].goods_barcode+"</td><td>"+goodss[i].goods_name+"</td><td class='public'>"+goodss[i].goods_sale_price+"</td><td class='public'>";
					tr+=goodss[i].goods_in_price+"<td><input type='button' value='添加' onclick='addAdjust(this)'></td></tr>";
					$("#goodss").append(tr);
				}
				addCookie("goods_message",goods_message,100);
				$(".tcdPageCode1").createPage({
					pageCount:goodss[goodss.length-1].page_num,
					current:1,
					backFn:function(p){
						goods1_find(p);
					}
				});
			}
			if(result.status==1){
				$("#goodss").empty();
				$("#warning1").html(result.msg);
				$("#warning1s").fadeIn(1000).fadeOut(500);
				$(".tcdPageCode1").createPage({
					pageCount:0,
					current:1,
					backFn:function(p){
						goods1_find(p);
					}
				});
			}
		},
		error:function(){
			alert("网络异常，请稍后重试！");
		}
	});
}
//获取输入的页数
function goods1_find(page_num){
	var goods_message=getCookie("goods_message");
	$.ajax({
		url:"goods/find_goods_page.do",
		type:"post",
		data:{"shop_unique":shop_unique,"goods_message":goods_message,"page_num":page_num},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var goodss=result.data;
				$("#goodss").empty();
				for(var i=0;i<goodss.length;i++){
					var tr="<tr><td>"+goodss[i].goods_barcode+"</td><td>"+goodss[i].goods_name+"</td><td class='public'>"+goodss[i].goods_sale_price+"</td><td class='public'>";
					tr+=goodss[i].goods_in_price+"<td><input type='button' value='添加' onclick='addAdjust(this)'></td></tr>";
					$("#goodss").append(tr);
				}
			}
		},
		error:function(){
			alert("网络异常，请稍后重试！");
		}
	});
}
//单击添加，将所选产品添加到调货单
function addAdjust(btn){
	
	var goods_barcode=$(btn).parent().parent().children().eq(0).html();
	var goods_name=$(btn).parent().parent().children().eq(1).html();
	var goods_in_price=$(btn).parent().parent().children().eq(3).html();
	$("#close").click();
	$("#ngoods_barcode").val(goods_barcode);
	$("#ngoods_name").val(goods_name);
	var goods_type=$("#ngoods_type").val();
	$("#nadjust_price").val(goods_in_price);
}
