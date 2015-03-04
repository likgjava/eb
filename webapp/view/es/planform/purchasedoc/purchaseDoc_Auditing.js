
var purchaseDoc_config = {};
purchaseDoc_config.auditpurchaseDoc = function(){
	var workFlowTaskId = $('[id=auditTaskId]').val();
	if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
		workFlowTaskId = $('[id=auditTask_Id]').val();
	}
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=savePruchaseDocAuditForJCJ&objId='+$("#purchaseDocId").val()+'&auditStatus='+$("#auditStatus").val()+'&workFlowTaskId='+workFlowTaskId,formToJsonObject('purchaseDocConfigForm') ,function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
	   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	    } else {
	    	$("#myDesktop").click();
	    }
	});
}

purchaseDoc_config.checkOpinion = function(){
	//操作权限
	var doAuth = true;
	if($("#auditStatus").val()=="N"){
		if(($("textarea[name='opinion']").val()==null || $("textarea[name='opinion']").val()=="")){
			alert("请填写审核不通过原因!");
			doAuth = false;
		}
	}
	return doAuth;
}


purchaseDoc_config.downloadFile = function(attachRelaId){	
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=dosIsDownLoad&projectId='+$("#projectId").val(),function(json){
		if(json.result)alert(json.result);if(json.failure)return;
	    if(json.isPay){
	    	$('#downLoadView').empty().loadPage($('#initPath').val()+"/view/es/inquiryprice/inqpdoc/docDownLoad.jsp?attachRelaId="+attachRelaId);
	    }
	});
}

purchaseDoc_config.getFileName = function(attachRelaId){
	 $.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=getAttachmentByobjId&attachmentId='+attachRelaId,function(json){
		 if(json.result)alert(json.result);if(json.failure)return;
		 $("#fileName").text(json.attachment.viewName);
		});
}



$(document).ready(function(){
	  var AttachmentId = $("#attachmentId").val();
	  purchaseDoc_config.getFileName(AttachmentId);
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		if(purchaseDoc_config.checkOpinion()){
		   purchaseDoc_config.auditpurchaseDoc();
		}
		
	});
	//不确认
	$('#noPassButton').click(function(){
		$("#auditStatus").val("N");
		if(purchaseDoc_config.checkOpinion()){
			purchaseDoc_config.auditpurchaseDoc();
		}
		
	});
		
});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=03');
});

