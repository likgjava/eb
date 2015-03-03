<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" >
var SuccessCaseAuditForm={};

SuccessCaseAuditForm.audit=function(auditStatus){
	var jsonObj = formToJsonObject('SuccessCaseAuditForm');
	jsonObj.auditStatus = auditStatus;
	$.getJSON($('#initPath').val()+'/SuccessCaseController.do?method=updateSuccessCaseInfo', jsonObj, function(json){
		if(json.failure) {
			alert(json.result);
			return;
		}
		$('#conBody').loadPage($('#initPath').val()+'/view/bizplatform/organization/successcase/successcase_audit_list.jsp');
	});
}
$(document).ready(function(){
	$('#SuccessCaseAuditForm').validate();
	//返回
	$('#returnBtn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/bizplatform/organization/successcase/successcase_audit_list.jsp");
	});
	//通过
	$('#agreeBtn').click(function(){
		if(window.confirm("确定审核通过吗?")){
			SuccessCaseAuditForm.audit("02");
		}
	});
	//不通过
	$('#refuseBtn').click(function(){
		if($('#opinion').val() == ''){alert('请填写审核不通过的意见！谢谢...');return;}
		if(window.confirm("确定审核不通过吗?")){
			SuccessCaseAuditForm.audit("03");
		}
	});
});
</script>

<form:form id="SuccessCaseAuditForm" method="post" modelAttribute="successCase" enctype="multipart/form-data">
	<input type="hidden" name="objId" id="objId" value="${successCase.objId}">
	<div class="formLayout  form2Pa">
     	<h4><span>案例信息</span></h4>
     	<ul>
    	    <li class="fullLine"><label><spring:message code="successCaseForm.projectName"/>：</label> 
    	    	<span id="projectName">${successCase.projectName}</span>
			</li>
			<li class="fullLine"><label><spring:message code="successCaseForm.startTime"/>：</label> 
    	    	<span id="startTime"><fmt:formatDate value="${successCase.startTime}" pattern="yyyy-MM-dd HH:mm"/></span>
			</li>
			<li class="fullLine"><label><spring:message code="successCaseForm.endTime"/>：</label> 
    	    	<span id="endTime"><fmt:formatDate value="${successCase.endTime}" pattern="yyyy-MM-dd HH:mm"/></span>
			</li>
			<li class="fullLine"><label>采购内容：</label> 
    	    	<span id="categoryNames">${successCase.categoryNames}</span>
			</li>
	     	<li class="fullLine">
	     		<label for="successCaseForm.description"><spring:message code="successCaseForm.description"/>：</label>
				<span id="categoryNames">${successCase.description}</span>
    	    </li>
    		<li class="formTextarea">
	     		<label>审核意见：</label>
	     		<textarea name="opinion" id ="opinion">${successCase.opinion}</textarea>
    	    </li>
		</ul>
		   <div class="conOperation">
		   		<button type="button" id="agreeBtn"><span>审核通过</span></button>
				<button type="button" id="refuseBtn"><span>审核不通过</span></button>
				<button type="button" id="returnBtn"><span>返回</span></button>
		   </div>
	   </div>
</form:form>