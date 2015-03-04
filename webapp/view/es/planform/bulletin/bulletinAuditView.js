$(document).ready(function(){
	$("#signUpCondFactorView").hide();
	var bullType = $("#bullType").val();
	if(bullType=='01' || bullType=='10'){//如果为招标公告或询价公告，才显示报名指标
	$("#signUpCondFactorView").loadPage($('#initPath').val()+"/CongruousFactorController.do?method=toFactorTabShow&projectId="+$("input[name='projectId']").val());
	$("#signUpCondFactorView").show();
	}
	
	$('#bulletinPass').click(function(){
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val("同意");
		}
		if(!$("#bulletinFormView").valid()) {	
			alert('请正确填写表单！')
			return false;
		}else{
			if(window.confirm("确认提交?")) {
				var workFlowTaskId = $('[id=auditTaskId]').val();
				if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
					workFlowTaskId = $('[id=auditTask_Id]').val();
				}
				var jsonObj = formToJsonObject('bulletinFormView');
				jsonObj.workFlowTaskId = workFlowTaskId;
				jsonObj.auditStatus = 'Y';
				jsonObj.projectTaskId = $('#projectTaskId').val();
				$.getJSON($('#initPath').val()+'/PurBulletinController.do?method=purbulletinAudit&passStatus=01&ApprovalFlag='+$('#approvalFlag').val(),jsonObj,function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					workFlowTaskId = $('[id=auditTaskId]').val();
					if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
						planTemplateTask.clickMethod($("#projectTaskId").val()+"");
						planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					  } else {
						$("#myDesktop").click();
					}
		    	});
			}
		}
	
	});
	$('#bulletinNoPass').click(function(){
		if($("#opinion").val()==''||$("#opinion").val()==null||$("#opinion").val()=='同意') { 	//评审未通过且未填写意见
			$("#opinion").val('');	
			alert("请填写审核意见");
			return false;
		}
		if(!$("#bulletinFormView").valid()) {	
			alert('请正确填写表单！')
			return false;
		}else{
			if(window.confirm("确认提交?")) {
				$.getJSON($('#initPath').val()+'/PurBulletinController.do?method=purbulletinAudit&passStatus=02&ApprovalFlag='+$('#approvalFlag').val(),formToJsonObject('bulletinFormView'),function(json){
					if(json.result)alert(json.result);if(json.failure)return;
					if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
						$('#conBody').loadPage("ProjectController.do?method=toProjectInfo&objId="+$("#projectId").val());
//						planTemplateTask.clickMethod($("#projectTaskId").val()+"");
//						planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					  } else {
						$("#myDesktop").click();
					}
		    	});
				}
		}
	});
	
	$('#viewProjectId').click(function(){//查看项目信息
		$.epsDialog({
	        title:"项目信息",
	        url:$("#initPath").val()+"/ProjectViewController.do?method=toViewProjectForSupervise&projectId="+$('#projectId').val(),
	        width: 650,
	        height: 265,
	        isReload: false
		});
	});
	
	
});


//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=01');
});