
$(document).ready(function(){
	if($("#nodoc").val()!='NopurDoc')
	{
		$("#purchaseDoc").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDoc&projectId='+$("#projectId").val());	
	}
	else
	{
		$("#purchaseDoc").html("<b align='center'>还未上传采购文件</b>");
	}
	
	//点击确定
	$("#submitBtn").click(function(){
		if($("#nodoc").val()!='NopurDoc')
		{
			$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=purchaseDocRelease',{objId:$('#purchaseDocId').val(), isconfig:$('#isconfig').val()},function(json){
	    		if(json.result)alert(json.result);if(json.failure)return;
	    		
	    	});
		}
		else
		{
			alert('请先提交采购文件！！！！');
		}
		
		
	})
		
});
