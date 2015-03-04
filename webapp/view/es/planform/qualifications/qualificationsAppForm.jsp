<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<form:form id="qualificationsAppForm" method="post" modelAttribute="qualificationsApp">
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditStatus"></form:hidden>
		<form:hidden path="relStatus"></form:hidden>
		
     	<h5><span>预审申请</span></h5>
     	<ul>
	     	<li class="eleDisable">
	     		<label for="qualificationsAppForm.supplyer"><spring:message code="qualificationsAppForm.supplyer"/>：</label>
					<form:input path="supplyer.orgName" cssClass="required"/>
					<form:hidden path="supplyer.objId"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.linkMan"><spring:message code="qualificationsAppForm.linkMan"/>：</label>
					<form:input path="linkMan" cssClass="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.linkPhone"><spring:message code="qualificationsAppForm.linkPhone"/>：</label>
					<form:input path="linkPhone" cssClass="required"/>	      
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.email"><spring:message code="qualificationsAppForm.email"/>：</label>
					<form:input path="email" cssClass="required"/>	    	      
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.address"><spring:message code="qualificationsAppForm.address"/>：</label>
					<form:input path="address" cssClass="required"/>	    	    	      
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.zipCode"><spring:message code="qualificationsAppForm.zipCode"/>：</label>
					<form:input path="zipCode"/>	    	    	      	      
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="qualificationsAppSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="qualificationsAppReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form:form>
</div>

<script>
$(document).ready(function(){
	$('#qualificationsAppForm').validate();
	//保存
	$('#qualificationsAppSave').click(function(){
		if(!$('#qualificationsAppForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/QualificationsAppController.do?method=submitQualificationsAppPage&project.objId='+$("#projectId").val(), formToJsonObject('qualificationsAppForm'), function(json){
			if(json.failure){alert(json.result);return;}
			alert("保存成功!");
		});
	});
});
</script>
    