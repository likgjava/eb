
var clarificationDoc_config = {};
clarificationDoc_config.auditpurchaseDoc = function(){
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=saveClarificationDocAuditForJCJ&objId='+$("#purchaseDocId").val()+'&auditStatus='+$("#auditStatus").val(),formToJsonObject('purchaseDocConfigForm') ,function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if($("#fromType").val()=='fromDesk'){
			$("#myDesktop").click();
		}else{
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			  } else {
				$("#myDesktop").click();
			}
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

$(document).ready(function(){
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		if($("#opinion").val()==""||$("#opinion").val()==null){
			$("#opinion").val("同意");
		}
		if(window.confirm("确认通过吗?")){
			clarificationDoc_config.auditpurchaseDoc();
		}	
	});
	//不确认
	$('#noPassButton').click(function(){
		$("#auditStatus").val("N");
		if($("#opinion").val()==""||$("#opinion").val()==null||$("#opinion").val()=="同意"){
			alert($("#opinion").val());
			alert("请输入审核意见!");
			$("#opinion").val("");
			return false;
		}
	if(window.confirm("确认不通过吗?")){
		clarificationDoc_config.auditpurchaseDoc();
	}
		
	});
	
	//关闭
	$('#close').click(function(){
		if($("#fromType").val()=='fromDesk'){
			$("#myDesktop").click();
		}else{
			$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/purchasedoc/purchaseDocTypeforAudit.jsp");
		}
		return;
	});
		
});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=03');
});

