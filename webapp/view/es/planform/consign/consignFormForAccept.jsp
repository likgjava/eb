<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
/*
 * 执行平台，接收委托协议
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var consignFormAccept={};

//验证审核信息
consignFormAccept.checkOpinion = function(){
	//操作权限
	var doAuth = true;
	if($("#confirmStatus").val()=="04"){
		if(($("#opinion").val()==null || $("#opinion").val()=="")){
			alert("请填写不确认原因!");
			doAuth = false;
		}
	}
	return doAuth;
}
$(document).ready(function(){

	$('#consignFormAccept').validate();
	//加载详细信息页面
	$("#consignDetail").loadPage($('#initPath').val()+'/ConsignController.do?method=showDetail&returntype=notDetail&objId='+$("#consignId").val());
	
	//确认
	$('#passButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==""){
			$("#opinion").val("同意");
		}
		if(!$('#consignFormAccept').valid()){
			alert("请正确填写表单！");return;
		}else if(window.confirm("确认接受委托协议吗？")){
			$("#passButton").attr("disabled","disabled");
			var jsonObj = {};
			jsonObj.objId = $("#objId").val();
			jsonObj.opinion = $('#opinion').val();
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=affirmConsignPass', jsonObj, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				var fromMyDesktop = $('#fromMyDesktop').val();
				//来源于桌面或者是来源于待办任务，则点转到桌面
				if(fromMyDesktop=='yes'||($("#auditTaskId") && $("#auditTaskId").val()!=null && $("#auditTaskId").val()!="")){
					$("#myDesktop").click();
				}else{	
				   $("button[name='returnButton']").click();
				}
			});
		}
	});
	
	//不确认
	$('#noPassButton').click(function(){
		if($("#opinion").val()==null||$("#opinion").val()==""||$("#opinion").val()=="同意"){
			$("#opinion").val("");
			alert("请输入退回意见！");
			return false;
		}
		if(!$('#consignFormAccept').valid()){
			alert("请正确填写表单！");return;
		}else if(window.confirm("确认不接受委托协议吗？")){
			$("#noPassButton").attr("disabled","disabled");
			var jsonObj = {};
			jsonObj.objId = $("#objId").val();
			jsonObj.opinion = $('#opinion').val();
			$.getJSON($('#initPath').val()+'/ConsignController.do?method=affirmConsignReject', jsonObj, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				var fromMyDesktop = $('#fromMyDesktop').val();
				//来源于桌面或者是来源于待办任务，则点转到桌面
				if(fromMyDesktop=='yes'||($("#auditTaskId") && $("#auditTaskId").val()!=null && $("#auditTaskId").val()!="")){
					$("#myDesktop").click();
				}else{	
				   $("button[name='returnButton']").click();
				}
			});
		}
	});

	//返回操作
	$("button[name='returnButton']").click(function(){
		var type = $("#type").val();
		if(type=='confirm') {
			$('#conBody').empty().loadPage($('#initPath').val()+"/view/es/planform/consign/consignListForConfirm.jsp");
		}else if(type=='accept') {
			$('#conBody').empty().loadPage($('#initPath').val()+"/view/es/planform/consign/consignListForAccept.jsp");
		}else if(type=='fromDesk'){
			$("#myDesktop").click();
		}
	});
	$('#show_audit_view').click(function(){
		$("#consign_audit_list").empty().loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$('#consignId').val()+'&taskType=05');
	});
});
</script>
<input type="hidden" name="consignId" id="consignId" value="<c:out value="${param.objId}"/>"/>
<input type="hidden" name="type" id="type" value="<c:out value="${param.type}"/>"/>
<div id="consignDetail"></div>
<div class="formLayout" style="height: 73px;">
	<h5><span>确认意见</span></h5>
		<form id="consignFormAccept" method="post">
			<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
			<span class="eleRequired"></span>
		</form>
</div>
<div class="conOperation">
	<button id="passButton" type="button" tabindex="18"><span>确认</span></button>
	<button id="noPassButton" type="button" tabindex="19"><span>退回</span></button>
	<button name="returnButton"  type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
</div>
<div id="consign_audit_list"></div>