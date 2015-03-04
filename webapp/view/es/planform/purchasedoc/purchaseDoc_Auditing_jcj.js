
var purchaseDoc_config = {};
purchaseDoc_config.auditpurchaseDoc = function(){
	$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=savePruchaseDocAuditForJCJ&objId='+$("#purchaseDocId").val()+'&auditStatus='+$("#auditStatus").val(),formToJsonObject('purchaseDocConfigForm') ,function(json){
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
$(document).ready(function(){
	$("#purchaseDocConfigForm").validate();
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		if($("#opinion").val()==null||$("#opinion").val()==""){
			$("#opinion").val("同意");
		}
		if(	!$("#purchaseDocConfigForm").valid()){alert("输入意见过长，请重新输入!");return false;}
		if(window.confirm("确认审核通过 吗？")){
			purchaseDoc_config.auditpurchaseDoc();
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
		if(	!$("#purchaseDocConfigForm").valid()){alert("输入意见过长，请重新输入!");return false;}
		if(window.confirm("确认审核不通过 吗？")){
			purchaseDoc_config.auditpurchaseDoc();
		}
		
	});
		
});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#purchaseDocId").val()+'&taskType=03');
});

