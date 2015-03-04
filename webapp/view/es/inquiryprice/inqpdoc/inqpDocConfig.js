
var InqpDocConfig = {};
InqpDocConfig.auditpurchaseDoc = function(){
	var jsonObj = {};
	jsonObj.objId = $("#purchaseDocId").val();
	jsonObj.auditStatus = $("#auditStatus").val();
	jsonObj.projectId = $("#projectId").val();
	jsonObj.opinion = $('#opinion').val();
	$.getJSON($('#initPath').val()+'/InqpDocController.do?method=purchaseDocConfig',jsonObj, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if($("#fromType").val()=='fromList'){ //从项目列表进入
			$("#tabform2").click();
		}else if($("#fromType").val()=='fromDesk'){//从我的桌面进入
			$("#myDesktop").click();
		}
		
	});
}

InqpDocConfig.checkOpinion = function(){
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

//返回
$("#returnButton").click(function(){
	var fromtype =  $("#fromType").val();
	  if(fromtype=='fromList'){
		  if($("#projectTaskId") && $("#projectTaskId").val() != ""){
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
        }
	  } else{
		  $("#myDesktop").click();
	  }
})

$(document).ready(function(){	
	$("#downLoad2").click(
			function(){
				window.location.href=''+$("#downAddr2").text()+'';
			}
	)
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val('同意');
		}
		if(InqpDocConfig.checkOpinion()) {
			InqpDocConfig.auditpurchaseDoc();
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
		if(InqpDocConfig.checkOpinion()) {
			InqpDocConfig.auditpurchaseDoc();
		}
	});
		
});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#purchaseDocId").val()+'&taskType=03');
});