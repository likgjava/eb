
var purchaseDocDeptAudit={};

purchaseDocDeptAudit.checkOpinion = function(){
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
purchaseDocDeptAudit.auditBulletin = function(){
	$.getJSON($('#initPath').val()+'/InqpDocController.do?method=purchaseDocConfig&auditStatus='+$("#auditStatus").val(), formToJsonObject('purchaseDocDeptAuditForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		alert("审核完毕!");
		$('#returnButton').click();
	});
}

$(document).ready(function(){
	//确认
	$('#passButton').click(function(){	
	$("#auditStatus").val("Y");
		if(purchaseDocDeptAudit.checkOpinion()){
		   purchaseDocDeptAudit.auditBulletin();	
		}
	});
	
	//不确认
	$('#noPassButton').click(function(){
			$("#auditStatus").val("N");
			if(purchaseDocDeptAudit.checkOpinion()){
			   purchaseDocDeptAudit.auditBulletin();
			}
			
	});
	
	//返回
	$('#returnButton').click(function(){
		 $("#myDesktop").click();
	});
	
		
});



//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=03');
});
