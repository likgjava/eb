
var purchaseDoc_config = {};
purchaseDoc_config.auditpurchaseDoc = function(){
	var jsonObj = {};
	jsonObj.objId = $("#purchaseDocId").val();
	jsonObj.auditStatus = $("#auditStatus").val();
	jsonObj.opinion = $('#opinion').val();
	$.getJSON($('#initPath').val()+'/InqpDocController.do?method=purchaseDocAuditing',jsonObj, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
	   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	    } else {
			if($("#fromType").val()=='fromList'){
				$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/purchasedoc/purchaseDocListForAudit.jsp");
			}else{
				$("#myDesktop").click();
			}
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
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val('同意');
		}
		if(purchaseDoc_config.checkOpinion()){
		   purchaseDoc_config.auditpurchaseDoc();
		}
		
	});
	//不确认
	$('#noPassButton').click(function(){
		if($("#opinion").val()==''||$("#opinion").val()==null||$("#opinion").val()=='同意'){
			$("#opinion").val('');
			alert('请输入审核意见!');
			return false
		}
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

