	$(document).ready(function(){
	if($("#nodoc").val()!='NopurDoc')
	{
		$("#purchaseDoc").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDoc&projectId='+$("#projectId").val());	
	}
	else
	{
		$("#purchaseDoc").html("<b align='center'>还未上传采购文件</b>");
	}
	if($("#isBuy").val()=='yes')
	{
		$("#purchaseDocDownLoad").html("<b align='center'>下载采购文件</b>")
	}
	else if($("#isBuy").val()=='No')
	{
		
		$("#purchaseDocDownLoad").html("<b align='center'>请先购买采购文件</b>")
	}
});

