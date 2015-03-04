$(document).ready(function(){
	$("#signUpCondFactorView").loadPage($('#initPath').val()+'/SignUpCondFactorController.do?method=getSignUpCondFactorListByProjectId&type=detail&projectId='+$("input[name=projectId]").val());
	$('#viewProjectId').click(function(){//查看项目信息
		$.epsDialog({
	        title:"项目信息",
	        url:$("#initPath").val()+"/ProjectViewController.do?method=toViewProjectForSupervise&projectId="+$('#projectId').val(),
	        width: 650,
	        height: 265,
	        isReload: false
		});
	});
	$('#bulletinPass').click(function(){
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val('同意');
		}
		if(window.confirm("确认提交?")){
		$.getJSON($('#initPath').val()+'/InqpbulletinController.do?method=purbulletinAudit&passStatus=01&ApprovalFlag='+$('#approvalFlag').val(),formToJsonObject('bulletinFormView'),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#fromDesk").val()=='yes'){//表示从我的桌面跳转
				$("#myDesktop").click();
			}
			if($("#fromList").val()=='yes'){//表示从我的项目列表跳转
				if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
					planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				  } else {
					$("#myDesktop").click();
				}
			}
    	});
		}
	});
	$('#bulletinNoPass').click(function(){
		//评审未通过且未填写意见
		if($("#opinion").val()==''||$("#opinion").val()==null||$("#opinion").val()=='同意'){
			$("#opinion").val('');
			alert("请填写审核意见");
			return false;
		}
		if(window.confirm("确认提交?")){
		$.getJSON($('#initPath').val()+'/InqpbulletinController.do?method=purbulletinAudit&passStatus=02&ApprovalFlag='+$('#approvalFlag').val(),formToJsonObject('bulletinFormView'),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#fromDesk").val()=='yes'){//表示从我的桌面跳转
				$("#myDesktop").click();
			}
			if($("#fromList").val()=='yes'){//表示从我的项目列表跳转
				if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
					$('#conBody').loadPage("ProjectController.do?method=toProjectInfo&objId="+$("#projectId").val());
					//planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					//planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				  } else {
					$("#myDesktop").click();
				}
			}
    	});
		}
	});
});


//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=01');
});