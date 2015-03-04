$(document).ready(function(){
	
	$("#sure").click(function(){
		if($("#purchaseFileTitle").val()=='')
		{
			alert("采购文件标题为必填项");
		}
		else
		{
			alert('采购文件上传成功!');
		}	
		
	})
})