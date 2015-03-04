
var purchaseDoc_config = {};
purchaseDoc_config.auditpurchaseDoc = function(){
	var workFlowTaskId = $('[id=auditTaskId]').val();
	if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
		workFlowTaskId = $('[id=auditTask_Id]').val();
	}
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=saveConfirmPruchaseDocAudit&objId='+$("#purchaseDocId").val()+'&projectId='+$("#projectId").val()+'&auditStatus='+$("#auditStatus").val()+'&workFlowTaskId='+workFlowTaskId,formToJsonObject('purchaseDocConfigForm'), function(json){
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

$(document).ready(function(){
	$("#purchaseDocConfigForm").validate();
	$("#downLoad").click(
			function(){
				window.location.href=''+$("#downAddr").text()+'';
			}
	)
	$("#downLoad2").click(
					function(){
						window.location.href=''+$("#downAddr2").text()+'';
					}
			)
	//确认
	$('#passButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==''){
			$("#opinion").val('同意');
		}
		if(	!$("#purchaseDocConfigForm").valid()){alert("输入意见过长，请重新输入!");return false;}
		$("#auditStatus").val("Y");
		if(purchaseDoc_config.checkOpinion()) {
			$('#passButton').attr("disabled","disabled");
			$('#noPassButton').attr("disabled","disabled");
			purchaseDoc_config.auditpurchaseDoc();
		}
		
	});
	//不确认
	$('#noPassButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==''||$("#opinion").val()=='同意'){
			$("#opinion").val('');
			alert("请输入意见!");
			return false;
		}
		if(	!$("#purchaseDocConfigForm").valid()){alert("输入意见过长，请重新输入!");return false;}
		$("#auditStatus").val("N");
		if(purchaseDoc_config.checkOpinion()) {
			$('#passButton').attr("disabled","disabled");
			$('#noPassButton').attr("disabled","disabled");
			purchaseDoc_config.auditpurchaseDoc();
		}
	});
	$('#congrousFactor').empty().loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("#projectId").val());	
});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#purchaseDocId").val()+'&taskType=03');
});