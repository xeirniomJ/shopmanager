//查询周期内店铺营业额状况
function findturnover(){
	$("#sale_statiscal").empty();
	$.ajax({
		url:"sale_list/findturnover.do",
		type:"post",
		data:{"shop_unique":shop_unique,"manager_unique":getCookie("manager_unique")},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var lists=result.data;
				var size=lists[lists.length-1].size;
				for(var i=0;i<size;i++){
					if(i%2==0){
						var tr="<tr><td>"+lists[i].shop_name+"：销售额</td>";
						for(var j=0;j<(lists.length-1)/size;j++){
							if(lists[size*j+i].sum!=null){
								tr+="<td>"+lists[size*j+i].sum+"</td>"
							}else{
								tr+="<td>0.00</td>";
							}
						}
						tr+="</tr>";
						$("#sale_statiscal").append(tr);
					}else if(i%2==1){
						var tr="<tr><td>"+lists[i].shop_name+"：进货额</td>";
						for(var j=0;j<(lists.length-1)/size;j++){
							if(lists[size*j+i].sum!=null){
								tr+="<td>"+lists[size*j+i].sum+"</td>"
							}else{
								tr+="<td>0.00</td>";
							}
						}
						tr+="</tr>";
						$("#sale_statiscal").append(tr);
						var tr="<tr><td>"+lists[i].shop_name+"：利润</td>";
						for(var j=0;j<(lists.length-1)/size;j++){
							if(lists[size*j+i].sum!=null&&lists[size*j].sum!=null){
								tr+="<td>"+(-lists[size*j+i].sum+lists[size*j].sum)+"</td>";
							}else if(lists[size*j+i].sum==null&&lists[size*j].sum!=null){
								tr+="<td>"+(lists[size*j].sum)+"</td>";
							}else{
								tr+="<td>0.00</td>";
							}
						}
						tr+="</tr>";
						$("#sale_statiscal").append(tr);
					}
				}
			}
		},
		error:function(){
			alert("查询店铺进货情况异常；");
		}
	});
}
//查询周期内店铺进货情况
function findPurchases(){
	
}