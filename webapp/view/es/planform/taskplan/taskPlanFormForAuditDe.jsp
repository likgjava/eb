<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
/*
 * 执行平台，审核采购申报书
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanFormAudit={};


//打印预览
$('#toPrint').click(function(){
	var taskPlanId = $("#taskPlanId").val();
	$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=toAgentTaskPlanPrintPage&taskPlanId='+taskPlanId,function(json){
	    if(json.result)alert(json.result);if(json.failure)return;
	    window.open($('#initPath').val()+'/view/es/common/RequestContentPrint.jsp');
	});	
});


$(document).ready(function(){

   	
	$('#taskPlanFormAudit').validate();
	//加载详细信息页面
	$("#taskPlanDetail").loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=div&objId='+$("#taskPlanId").val());

	//隐藏任务书条目
	$("#li_subs").hide();
	
	//通过
	$('#passButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==""){
			$("#opinion").val("同意");
		}
		if(!$('#taskPlanFormAudit').valid()){
			alert("请正确填写表单！");return;
		}
		var workFlowTaskId = $("#auditTaskId").val();
		if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
			workFlowTaskId = getWaitprocWorkTaskId('audit_taskplan_detail',$("#objId").val(),$("#objId").val());
		}
		if(window.confirm("确认审核通过？")){
			$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=auditStockBankrollPass', {'objId':$("#objId").val(),'confirmStatus':$("#confirmStatus").val(),'auditDetail':$("#auditDetail").val(),'taskId':$("#taskId").val(),'opinion':$("#opinion").val(),'workFlowTaskId':workFlowTaskId}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('button[name=historyBackBtn]').click();
			});
		}		
	});
	
	//不通过
	$('#noPassButton').click(function(){
			if($("#opinion").val()==null||$("#opinion").val()==""||$("#opinion").val()=="同意"){
				$("#opinion").val("");
				alert("请输入退回意见！");
				return false;
			}
			if(!$('#taskPlanFormAudit').valid()){
				alert("请正确填写表单！");return;
			}
			var workFlowTaskId = $("#auditTaskId").val();
			if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
				workFlowTaskId = getWaitprocWorkTaskId('audit_taskplan_detail',$("#objId").val(),$("#objId").val());
			}
			if(window.confirm("确认审核不通过？")){
				$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=auditStockBankrollReject', {'objId':$("#objId").val(),'confirmStatus':$("#confirmStatus").val(),'auditDetail':$("#auditDetail").val(),'taskId':$("#taskId").val(),'opinion':$("#opinion").val(),'workFlowTaskId':workFlowTaskId}, function(json){
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

<input type="hidden" id="taskPlanId" name="taskPlanId" value="<c:out value="${param.objId}"/>"/>
<div id="taskPlanDetail"  class="partContainers">
</div>
<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
<div   class="partContainers">
	<h5><span>审核意见</span></h5>
	<ul>
		<li>
			<form id="taskPlanFormAudit" method="post">
				<textarea name="opinion" id="opinion" class="texFull"  >同意</textarea>
				<span class="eleRequired"></span>
			</form>
		</li>
	</ul>
</div>
<div class="conOperation">
	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
	<button id="noPassButton" type="button" tabindex="19"><span>不通过</span></button>
	<button id="toPrint" type="button" tabindex="19"><span>打印预览</span></button>
	<button id="historyId" name="historyName" style="<c:if test='${param.objId == null || param.objId == "" }'>display:none</c:if>" type="button" tabindex="20"><span>操作历史</span></button>
	<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
<div id="historyView"></div>