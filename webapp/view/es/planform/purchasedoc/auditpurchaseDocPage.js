
var purchaseDoc_config = {};
purchaseDoc_config.auditpurchaseDoc = function(){
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=purchaseDocAuditing&objId='+$("#purchaseDocId").val()+'&auditStatus='+$("#auditStatus").val(), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		//projectagent.treeLeftClick($("#projectTaskId").val()+"");
		$('#'+$("#divTarget").val()).loadPage($('#initPath').val()+'/'+$("#divTargetUrl").val());
	});
}

$(document).ready(function(){
	$("#purchaseDoc").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocDetail&projectId='+$("#projectId").val());	
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		purchaseDoc_config.auditpurchaseDoc();
	});
	//不确认
	$('#noPassButton').click(function(){
		$("#auditStatus").val("N");
		purchaseDoc_config.auditpurchaseDoc();
	});
		
});
