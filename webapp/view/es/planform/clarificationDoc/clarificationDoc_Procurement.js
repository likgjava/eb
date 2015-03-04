
var clarificationDoc_config = {};

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

clarificationDoc_config.auditpurchaseDoc = function(){
	if(!$('#purchaseDocConfigForm').valid()){
		alert("请正确填写表单！");return;
	}else if(window.confirm("确认提交?"))
	{
		var jsonObj = formToJsonObject('purchaseDocConfigForm');
		var workFlowTaskId = $('[id=auditTaskId]').val();
		if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
			workFlowTaskId = $('[id=auditTask_Id]').val();
		}
		jsonObj.workFlowTaskId = workFlowTaskId;
		$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=saveClarificationDocAudit&objId='+$("#purchaseDocId").val()+'&auditStatus='+$("#auditStatus").val(),jsonObj, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	}
}

$(document).ready(function(){
	$('#purchaseDocConfigForm').validate();
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		if($("#opinion").val()==null||$("#opinion").val()==""){
			$("#opinion").val("同意");
		}
		if(clarificationDoc_config.checkOpinion()){
			clarificationDoc_config.auditpurchaseDoc();
			if($("#fromType").val()=='fromDesk'){
				$("#myDesktop").click();
			}else{
				$('#purchaseDocGrid').reload();//刷新 
				$('#epsDialogCloseNoReload').click();
			}
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
		if(clarificationDoc_config.checkOpinion()){
			clarificationDoc_config.auditpurchaseDoc();	
			if($("#fromType").val()=='fromDesk'){
				$("#myDesktop").click();
			}else{
				$('#purchaseDocGrid').reload();//刷新 
				$('#epsDialogCloseNoReload').click();
			}
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
