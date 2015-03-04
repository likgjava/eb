$(document).ready(function(){
	$("#bulletinFormView").validate();
	$('#bulletinPass').click(function(){
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val("同意");
		}
		if(	!$("#bulletinFormView").valid()){alert("输入意见过长，请重新输入!");return false;}
		if(window.confirm("确认提交?"))
		{
		$.getJSON($('#initPath').val()+'/ResultBulletinController.do?method=auditResultBulletin&passStatus=01',formToJsonObject('bulletinFormView'),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			  } else {
				$("#myDesktop").click();
			}
    	});
		}
	});
	$('#bulletinNoPass').click(function(){
		//评审未通过且未填写意见
		if($("#opinion").val()==''||$("#opinion").val()==null||$("#opinion").val()=='同意')
		{
			$("#opinion").val('');	
			alert("请填写审核意见");
			return false;
		}
		if(	!$("#bulletinFormView").valid()){alert("输入意见过长，请重新输入!");return false;}
		if(window.confirm("确认提交?"))
		{
		$.getJSON($('#initPath').val()+'/ResultBulletinController.do?method=auditResultBulletin&passStatus=02',formToJsonObject('bulletinFormView'),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
//				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				$('#conBody').loadPage("ProjectController.do?method=toProjectInfo&objId="+$("#projectId").val());
			  } else {
				$("#myDesktop").click();
			}
    	});
		}
	});
	
//返回
	$('#returnBtn').click(function(){
		var auditStatus = $('#auditStatus').val();
		var bullType = $('#bullType').val();
		$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getMoreForBulletinList&auditStatus='+auditStatus+'&bullType='+bullType);
		
	});
	
});


//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=01');
});