<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline;color: blue;}
--> 
</style>
<script>
var taskPlanFormAudit={};
taskPlanFormAudit.showDetail=function(objId){
	$.epsDialog({
        title:'查看申报书信息',
        url:$('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+objId+'&type=selectAgent',
        width: '800',
        height: '600',
 	    isReload:true
			});
}



$(document).ready(function(){	
	$('button[name=BackBtn]').click(function(){
		if($("#fromType").val()=='fromDesk'){
		$("#myDesktop").click();
		}else{
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanListForSelectAgent.jsp');
		}
	})
	$("#taskPlanFormAudit").validate();
	//通过
	$('#passButton').click(function(){
		if(!$('#taskPlanFormAudit').valid()){
			alert("意见输入过长,请控制!");
			return false;
		}
		if($("#opinion").val()==''||$("#opinion").val()==null){
			$("#opinion").val('同意');
		}
		if(window.confirm("确认审核通过？")) {
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=auditTaskPlanForSelectAgent', {"ids":$("#_taskPlanId").val(),"confirmStatus":"06","opinion":$("#opinion").val()}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=BackBtn]').click();
			});
		}
	});
	
	//不通过
	$('#noPassButton').click(function(){
		if(!$('#taskPlanFormAudit').valid()){
			alert("意见输入过长,请控制!");
			return false;
		}
		if($("#opinion").val()==''||$("#opinion").val()==null||$("#opinion").val()=='同意'){
				$("#opinion").val('');
				alert("请添加不通过原因！");
				return;
			}
		if(window.confirm("确认审核不通过?")) {
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=auditTaskPlanForSelectAgent', {"ids":$("#_taskPlanId").val(),"confirmStatus":"05","opinion":$("#opinion").val()}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=BackBtn]').click();
			});
		}
	});
});

//操作历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#taskPlanId").val()+'&taskType=00');
});

</script>
<input type="hidden" id="fromType" value="${param.fromType}">
<input type="hidden" id="_taskPlanId" value="${taskPlan.objId}">
<div class="partContainers">
<div class="formLayout form2Pa"> 
<h5><span><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>信息</span></h5> 
	<ul>
		<li>
			<label class="short"  for="taskCode">申报书<spring:message code="taskPlanForm.taskCode"/>：</label>
			<a href="#" class="abtn" onclick="taskPlanFormAudit.showDetail('${taskPlan.objId}')"><span id="taskCode">${taskPlan.taskCode}</span></a>
		</li>
		<li>
			<label class="short"  for="taskName">申报书<spring:message code="taskPlanForm.taskName"/>：</label>
			<a href="#" class="abtn" onclick="taskPlanFormAudit.showDetail('${taskPlan.objId}')"><span id="taskName">${taskPlan.taskName}</span></a>
		</li>
		<li class="fullLine">
			<label class="short">抽取的招标中心：</label>
			<span id="AgentName">${taskPlan.taskAgentName}</span>
		</li>
	</ul>
</div>

<div>
<form id="taskPlanFormAudit" method="post">
	<div class="partContainers formLayoutAudit">
		<h5><span>审核意见</span></h5>
			<ul>
				<li>
				<textarea name="opinion" id="opinion" class="texFull" maxlength="200">同意</textarea>
				</li>
			</ul>
	</div>
</form>
<div class="conOperation">
	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
	<button id="noPassButton" type="button" tabindex="19"><span>不通过</span></button>
	<button name="BackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>


</div>

</div>
