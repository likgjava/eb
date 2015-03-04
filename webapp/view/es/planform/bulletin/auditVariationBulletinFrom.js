$(document).ready(function(){
	$("#signUpCondFactorView").loadPage($('#initPath').val()+'/SignUpCondFactorController.do?method=getSignUpCondFactorListByProjectId&type=detail&projectId='+$("input[name=projectId]").val());
	
	$('#bulletinPass').click(function(){//审核通过
		if(window.confirm("确认提交?"))
		{
			var workFlowTaskId = $('[id=auditTaskId]').val();
			if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
				workFlowTaskId = $('[id=auditTask_Id]').val();
			}
			var jsonObj = formToJsonObject('bulletinFormView');
			jsonObj.workFlowTaskId = workFlowTaskId;
			jsonObj.auditStatus = 'Y';
			jsonObj.projectTaskId = $('#projectTaskId').val();
			$.getJSON($('#initPath').val()+'/VariationBulletinController.do?method=auditVariationBulletin&passStatus=01',jsonObj,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				workFlowTaskId = $('[id=auditTaskId]').val();
				planTemplateTask.refresh($('#projectTaskId').val());
				$('#returnId').click();
	    	});
		}
	});
	$('#bulletinNoPass').click(function(){
		//评审未通过且未填写意见
		if($("#opinion").val()=='')
		{
			alert("请填写审核意见");
			return false;
		}
		if(window.confirm("确认提交?"))
		{
		$.getJSON($('#initPath').val()+'/VariationBulletinController.do?method=auditVariationBulletin&passStatus=02',formToJsonObject('bulletinFormView'),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			planTemplateTask.refresh($('#projectTaskId').val())
			$('#returnId').click();
    	});
		}
	});
	
	$('#returnId').click(function(){
		$('#epsDialogCloseReload').click();
	});
});


//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=01');
});