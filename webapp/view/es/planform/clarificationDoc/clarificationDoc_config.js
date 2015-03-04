
var clarificationDoc_config = {};
clarificationDoc_config.auditpurchaseDoc = function(){
	var workFlowTaskId = $('[id=auditTaskId]').val();
	if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
		workFlowTaskId = $('[id=auditTask_Id]').val();
	}
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=saveConfirmClarificationDocAudit&objId='+$("#purchaseDocId").val()+'&projectId='+$("#projectId").val()+'&auditStatus='+$("#auditStatus").val()+'&workFlowTaskId='+workFlowTaskId,formToJsonObject('purchaseDocConfigForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('#epsDialogCloseNoReload').click();
		$('#purchaseDocGrid').reload();//刷新
		if($("#fromType").val()=='fromList'){
			workFlowTaskId = $('[id=auditTaskId]').val();
			if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
				$("#tabform2").click();
			}else{
				$("#myDesktop").click();
			}
		}
		if($("#fromType").val()=='fromDesk'){
			$("#myDesktop").click();
		}
	});
}

clarificationDoc_config.checkOpinion = function(){
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

clarificationDoc_config.downloadFile = function(attachRelaId){	
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=dosIsDownLoad&projectId='+$("#projectId").val(),function(json){
		if(json.result)alert(json.result);if(json.failure)return;
	    if(json.isPay){
	    	$('#downLoadView').empty().loadPage($('#initPath').val()+"/view/es/inquiryprice/inqpdoc/docDownLoad.jsp?attachRelaId="+attachRelaId);
	    }
	});
}

clarificationDoc_config.getFileName = function(attachRelaId){
	 $.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=getAttachmentByobjId&attachmentId='+attachRelaId,function(json){
		 if(json.result)alert(json.result);if(json.failure)return;
		 $("#fileName").text(json.attachment.viewName);
		});
}


$(document).ready(function(){
	  var AttachmentId = $("#attachmentId").val();
	  clarificationDoc_config.getFileName(AttachmentId);
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		if($("#opinion").val()==null||$("#opinion").val()==""){
			$("#opinion").val("同意");
		}
		if(window.confirm("确认通过?")) {
			clarificationDoc_config.auditpurchaseDoc();
		}
	});
	//不确认
	$('#noPassButton').click(function(){
		$("#auditStatus").val("N");
		if($("#opinion").val()==null||$("#opinion").val()==""||$("#opinion").val()=="同意"){
			$("#opinion").val("");
			alert("请输入退回意见！");
			return false;
		}
		if(window.confirm("确认退回该澄清文件吗?")) {
			clarificationDoc_config.auditpurchaseDoc();
		}
	});
	//关闭
	$('#close').click(function(){
		if($("#fromType").val()=='fromDesk'){
			$("#myDesktop").click();
		}else{
		    $('#epsDialogCloseNoReload').click();
		}
		return;
	});
});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#purchaseDocId").val()+'&taskType=03');
});