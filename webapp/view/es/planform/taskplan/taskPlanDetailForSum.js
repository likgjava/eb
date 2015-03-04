/*
 * 执行平台，采购申报书详情
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanDetail={};

$(document).ready(function(){
	$("#taskPlanDetailForSum_epsTabs").loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=div&objId='+$("#taskPlanId").val());
	$("#taskPlanFormOpinion").validate();
    //查询条件过滤
    taskPlanDetail.before=function(){
    	var option={"taskPlan.objId":$("#objId").val()}
    	$('#taskPlanDetailGrid').flexOptions({params:option});
    	$('#taskPlanSubGrid').flexOptions({params:option});
    	return true;
    }
    $("#returnButton").click(
    		function(){
    			$("#allTask").show();
    			$("#taskPlanDetailForSum").hide();
    		}
    )
	//提交审核
	$('#submitButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==""){
			$("#opinion").val("同意");
		}
		if(!$("#taskPlanFormOpinion").valid()){
			alert("意见输入内容过长，请重新输入！");
			return;
		} else if(window.confirm('确定要提交审核吗？')){
			$("#submitButton").attr("disabled","disabled");
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitTwainTaskPlan', {objIds:$("#objId").val(),auditDetail:"01",workFlowTaskId:$("#auditTaskId").val(),opinion:$("#opinion").val()}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=historyBackBtn]').click();
			});
		}
	});
	
	//退回
	$('#backButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==""||$("#opinion").val()=="同意"){
			$("#opinion").val("");
			alert("请输入退回意见！");
		}else if(!$("#taskPlanFormOpinion").valid()){
			alert("意见输入内容过长，请重新输入！");
		} else if(window.confirm('确定要退回该申报书吗？')){
			$("#backButton").attr("disabled","disabled");
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=rejectTwainTaskPlan', {objIds:$("#objId").val(),useStatus:"00",opinion:$("#opinion").val()}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=historyBackBtn]').click();
			});
		}
	});
	
	
});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#taskPlanId").val()+'&taskType=00');
});
