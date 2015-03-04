<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div id="openbidGeneralList" class="formLayout formPa">
<input type="hidden" id="projectId" value="${projectId}">
	<h5><span>待审核的邀请函</span></h5>  
	<table class="tableList">
		<tr>
	    	<th >投标单位名称</th>
	    	<th >操作</th>
   		</tr>
		<c:forEach items="${inrqDetailList}" var="inrqDetail">
			<tr>
				<td style="text-align: left">${inrqDetail.supplierName}</td>
				<td style="text-align: center"><a class="abtn" onclick="InviterollrequAuditList.look('${inrqDetail.objId}');"><span>查看</span></a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<div class="formLayout" style="height: 73px;">
	<h5><span>审核意见</span></h5>  
<form id="InviterollrequAuditForm" method="post">
		<input type="hidden" name="inviterollrequId" id="inviterollrequId" value="${inviterollrequ.objId}"/>
			<textarea name="opinion" id="opinion" style="width: 99%;height: 45px;">同意</textarea>
		</form>
		<div class="conOperation">
       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
			<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
			<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
   	    </div>
</div>
<div id="historyView"></div>
<script>
var  InviterollrequAuditList ={}
InviterollrequAuditList.look = function(objId){
$.epsDialog({
    title:'邀请函信息',
    url:$('#initPath').val()+'/InrqDetailController.do?method=getInrqDetailView&objId='+objId,
    width: '600',
    height: '200',
	isReload:true,
    onOpen: function(){ },
    afterLoad: function(){ },
    onClose: function(){ }
		});

}

//审核通过
$("#passButton").click(function(){
	if($("#opinion").val()==''||$("#opinion").val()==null){
		$("#opinion").val('同意');
	}
	if(!$('#InviterollrequAuditForm').valid()){
		alert("请正确填写表单！");return;
	}else if(window.confirm("确认提交?")) {
		$.getJSON($('#initPath').val()+'/InviterollrequController.do?method=inviterollrequAudit&status=02', formToJsonObject('InviterollrequAuditForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			  } else {
				$("#myDesktop").click();
			}
		});
	}
});


//审核不通过
$("#noPassButton").click(function(){
	//评审未通过且未填写意见
	if($("#opinion").val()==''||$("#opinion").val()==null||$("#opinion").val()=='同意') {
		$("#opinion").val('');
		alert("请填写审核意见");
		return false;
	} else if(!$('#InviterollrequAuditForm').valid()){
		alert("请正确填写表单！");return;
	}else if(window.confirm("确认提交?")) {
		$.getJSON($('#initPath').val()+'/InviterollrequController.do?method=inviterollrequAudit&status=04', formToJsonObject('InviterollrequAuditForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != "" &&$("#projectTaskId").val() !=null && $("#projectTaskId").val()!= undefined){
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			  } else {
				$("#myDesktop").click();
			}
		});
	}
});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByParentBizIdAndType&parentBizId="+$("#projectId").val()+'&taskType=07');
});

</script>