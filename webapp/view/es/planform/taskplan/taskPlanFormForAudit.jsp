<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
/*
 * 执行平台，审核采购申报书
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanFormAudit={};

//验证审核信息
taskPlanFormAudit.checkOpinion = function(){
	//操作权限
	var doAuth = true;
	if($("#confirmStatus").val()=="04"){
		if(($("#opinion").val()==null || $("#opinion").val()=="")){
			alert("请填写审核不通过原因！");
			doAuth = false;
		}
	}
	else{
		var taskType = $(":radio[name='taskType']:checked").val();
		if(taskType=='00'){
			if($("#taskAgentName").val()==''){
				alert('请选择招标中心！');
				doAuth = false;
			}
		}
	}
	return doAuth;
}

$(document).ready(function(){
	
	$('#taskPlanFormAudit').validate();
	//加载详细信息页面
	$("#taskPlanDetail").loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&isChanged=YES&type=div&objId='+$("#taskPlanId").val());
	
	//隐藏任务书条目
	$("#li_details").hide();
	
	//通过
	$('#passButton').click(function(){
		var agentId = $("input[name='taskAgent.objId']").val();
		var agentName = $("#taskAgentName").val();
		if (!$('#taskPlanFormAudit').valid()) {
			alert("请正确填写表单！");
			return false;
		}
		if($("#opinion").val()==null||$("#opinion").val()==""){
			$("#opinion").val("同意");
		}
		if(window.confirm("确认审核通过？")) {
			$("#passButton").attr("disabled","disabled");
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=auditStockTaskPlanPass', {"objId":$("#objId").val(),"confirmStatus":$("#confirmStatus").val(),"ids":$("#taskId").val(),"opinion":$("#opinion").val(),"taskAgentName":agentName,"taskAgent.objId":agentId}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=historyBackBtn]').click();
			});
		}
	});
	
	//不通过
	$('#noPassButton').click(function(){
		var agentId = $("input[name='taskAgent.objId']").val();
		var agentName = $("#taskAgentName").val();
		if (!$('#taskPlanFormAudit').valid()) {
			alert("请正确填写表单！");
			return false;
		}
		if($("#opinion").val()==null||$("#opinion").val()==""||$("#opinion").val()=="同意"){
			$("#opinion").val("");
			alert("请输入退回意见！");
			return false;
		}
		if(window.confirm("确认审核不通过?")) {
			$("#noPassButton").attr("disabled","disabled");
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=auditStockTaskPlanReject', {"objId":$("#objId").val(),"confirmStatus":$("#confirmStatus").val(),"ids":$("#taskId").val(),"opinion":$("#opinion").val(),"taskAgentName":agentName,"taskAgent.objId":agentId}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=historyBackBtn]').click();
			});
		}
	});
});

//操作历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#taskPlanId").val()+'&taskType=00');
});

</script>
<input type="hidden" name="taskPlanId" id="taskPlanId" value="${taskPlan.objId}"/>
<div id="taskPlanDetail">
</div>
<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
<form id="taskPlanFormAudit" method="post">
	<div class="partContainers formLayoutAudit" >
		<h5><span>审核意见</span></h5>
		<ul>
			<li>
				<textarea name="opinion" id="opinion" class="texFull" >同意</textarea>
			</li>
		</ul>
	</div>
</form>
<div class="partContainers conOperation">
	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
	<button id="noPassButton" type="button" tabindex="19"><span>不通过</span></button>
	<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
	<button id="historyId" name="historyName" style="<c:if test='${taskPlan.objId == null || taskPlan.objId == "" }'>display:none</c:if>" type="button" tabindex="20"><span>操作历史</span></button>
</div>
<div id="historyView"></div>
