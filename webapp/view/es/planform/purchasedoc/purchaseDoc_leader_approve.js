
var purchaseDocLeaderApproveForm={};

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
				//alert(obj2str(formToJsonObject('purchaseDocLeaderApproveForm')));
				$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=purchaseDocLeaderApprove',formToJsonObject('purchaseDocLeaderApproveForm'),function(json){
					if(json.result)alert(json.result);if(json.failure)return;
		    		jsonObjectToForm('purchaseDocLeaderApproveForm', json.purchaseDoc);
		    	});
		}
		else
		{
			alert('请先提交采购文件！！！');
		}
		
		
	})
		
});
