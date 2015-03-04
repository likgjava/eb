<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<div class="formLayout form2Pa">  
		<form id="signUprecordDetailForm" method="post">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<h5><span>投标单位报名记录</span></h5>
			<ul>
				<li>
					<label for="signLinker"><spring:message code="signUprecordForm.supplierName"/>：</label>
					<span>${signUprecord.supplierName}</span>
				</li>
				<li>
					<label for="signLinker"><spring:message code="signUprecordForm.signLinker"/>：</label>
					<span id="signLinker">${signUprecord.signLinker}</span>
				</li>
				<li>
					<label for="linkerTel"><spring:message code="signUprecordForm.linkerTel"/>：</label>
					<span id="linkerTel">${signUprecord.linkerTel}</span>
				</li>
				<li>
					<label for="signIdcard"><spring:message code="signUprecordForm.signIdcard"/>：</label>
					<span id="signIdcard">${signUprecord.signIdcard}</span>
				</li>
				<li>
					<label for="signAddress"><spring:message code="signUprecordForm.signAddress"/>：</label>
					<span id="signAddress">${signUprecord.signAddress}</span>
				</li>
				<li>
					<label for="confirmStatus"><spring:message code="signUprecordForm.confirmStatus"/>：</label>
					<span>${signUprecord.confirmStatusCN}</span>
				</li>
				
				
			</ul>
		</form>
	</div>
	<div class="formLayout form2Pa">
		<h5>确认投标单位报名</h5>
   		<form id="signUpRecordConfirmForm" method="post">
	  		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
			<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
			<input type="hidden" name="confirmStatus" id="confirmStatus" value="02"/>
			<ul>
				<li class="formTextarea">
					<label>意见：</label>
					<textarea name="opinion" id="opinion"></textarea>
				</li>
			</ul>
			<div class="conOperation">
	       		<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
				<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
	   	    </div>
		</form>
	</div>
</div>
<script>
var purBulletinAudit = {};
purBulletinAudit.checkOpinion = function(){
	//操作权限
	var doAuth = true;
	if($("#auditStatus").val()=="N"){
		if(($("textarea[name='opinion']").val()==null || $("textarea[name='opinion']").val()=="")){
			alert("请填写不通过原因!");
			doAuth = false;
		}
	}
	return doAuth;
}
purBulletinAudit.auditSignupRecord = function(){
	$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=auditSignupRecord', formToJsonObject('signUpRecordConfirmForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$("#epsDialogCloseReload").click();
	});
}

$(document).ready(function(){
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		$("#confirmStatus").val("02");
		if(purBulletinAudit.checkOpinion()){
			purBulletinAudit.auditSignupRecord();
		}
	});
	//不确认
	$('#noPassButton').click(function(){
		$("#auditStatus").val("N");
		$("#confirmStatus").val("03");
		if(purBulletinAudit.checkOpinion()){
			purBulletinAudit.auditSignupRecord();
		}
	});
})
</script>