function doUpload() {  
	var ceshi=$("#ceshi").val().trim();
	console.log(encodeURI(ceshi));
     $("#uploadForm").ajaxSubmit({  
          url: 'upload.do',  
          type: 'post',  
          contentType: "application/x-www-form-urlencoded; charset=utf-8", 
          data:{"ceshi":encodeURI(ceshi)},
          dataType:"json",
          success: function (result) {  
        	  console.log(result.msg);
        	  console.log(result.data);
          },  
          error: function () {  
        	  console.log("上传失败");
          }  
     });  
} 
function download(){
	window.location.href="xlsx/purchase.xls";
}

function doup(){
	var ceshi=$("#ceshi").val();
	$.ajax({
		url:"upload1.do",
		data:{"ceshi":ceshi},
		type:"post",
		dataType:"json",
		success:function(){
			
		},
		error:function(){
			
		}
	});
}