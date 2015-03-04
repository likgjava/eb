<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<div class="functionBtnDiv" style="text-align: right;margin-right:10px;">
	<button id="viewProjectId"><span class="add">查看项目</span></button>
	<span style="height:auto;">${bulletin.bullContents}</span>
</div>

 <div class="formLayout" id="signUpCondFactorView"></div>

<div class="formLayout formPa" style="height: 73px;">
  			<h5>审核意见</h5>
  			<input type="hidden" id="signUpResponeNum" value="${signUpResponeNum}">
      		<form id="BulletinForm" method="post">
			<input type="hidden" name="projectId" id="projectId" value="<c:out value="${bulletin.project.objId}"/>"/>
			<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
			<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
			<input type="hidden" name="auditStatus" id="auditStatus" value="<c:out value="${bulletin.auditStatus}"/>"/>
			<input type="hidden" name="bullType" id="bullType" value="<c:out value="${bulletin.bullType}"/>"/>
			<input type="hidden" name="divTarget" id="divTarget" value="<c:out value="${divTarget}"/>"/>
			<input type="hidden" name="divTargetUrl" id="divTargetUrl" value="<c:out value="${divTargetUrl}"/>"/>
			<input type="hidden" name="fromList" id="fromList" value="<c:out value="${fromList}"/>"/>
			<ul>
				<li style="height:50px;">
					<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
					<span class="eleRequired"></span>
				</li>
			</ul>
			</form>
</div>
<div class="conOperation">
    <button id="passButton" type="button" tabindex="18"><span>通过</span></button>
	<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
	<button id="returnBtn" type="button" tabindex="18"><span>返回</span></button>
	<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
</div>
   <div id="historyView"></div>
<script>
$(document).ready(function(){
	$("strong").css("color","black");
	$("#signUpCondFactorView").hide();
	var bullType = $("#bullType").val();
	var signUpResponeNum = $("#signUpResponeNum").val();
	if(signUpResponeNum != 'none'){
		if(bullType=='01' || bullType=='10'){//如果为招标公告或询价公告，才显示报名指标
			$("#signUpCondFactorView").loadPage($('#initPath').val()+'/SignUpCondFactorController.do?method=getSignUpCondFactorListByProjectId&type=detail&projectId='+$("input[name=projectId]").val());
			$("#signUpCondFactorView").show();
			}	
	}
	
	$("#BulletinForm").validate();
	$("#noPassButton").click(function(){
		//评审未通过且未填写意见
		if($("#opinion").val()==''||$("#opinion").val()=='同意'){
			$("#opinion").val('');
			alert("请填写退回意见");
			return false;
		}else if(!$("#BulletinForm").valid()){	
			alert('请正确填写表单！')
			return false;
		}else if(window.confirm("确认提交?")){
			$.getJSON($('#initPath').val()+'/PurBulletinController.do?method=purbulletinAudit&passStatus=02',formToJsonObject('BulletinForm'),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				if($("#divTarget").val()!=''){
					if($("#divTargetUrl").val()!=''){
						$('#'+$("#divTarget").val()).loadPage($('#initPath').val()+'/'+$("#divTargetUrl").val());
					}
				}else {
					$('#returnBtn').click();
					}
	    	});
		}
	});
	$('#passButton').click(function(){
		if($("#opinion").val()==''){
			$("#opinion").val('同意');
		}
		
		if(!$("#BulletinForm").valid()) {	
			alert('请正确填写表单！')
			return false;
		}else {
			if(window.confirm("确认提交?")){
					$.getJSON($('#initPath').val()+'/PurBulletinController.do?method=purbulletinAudit&passStatus=01',formToJsonObject('BulletinForm'),function(json){
						if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
				        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
					   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
					    } else {
								$('#returnBtn').click();
					    }
			    	});		
		    	}
		     }
	});
//返回
	$('#returnBtn').click(function(){
		var auditStatus = $('#auditStatus').val();
		var bullType = $('#bullType').val();
		if($('#fromList').val()==''){
			$("#myDesktop").click();
			}else{
		$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getMoreForBulletinList&auditStatus='+auditStatus+'&bullType='+bullType);
		}
	});
	
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
//操作历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#objId").val()+'&taskType=01');
});
</script>