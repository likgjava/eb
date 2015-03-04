<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<form:form id="qualificationsAppDetailForm" method="post" modelAttribute="qualificationsApp">
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditStatus"></form:hidden>
		<form:hidden path="relStatus"></form:hidden>
		
     	<h5><span>预审申请</span></h5>
     	<ul>
	     	<li>
	     		<label for="qualificationsAppForm.supplyer"><spring:message code="qualificationsAppForm.supplyer"/>：</label>
    	   			<span id="supplyer">${qualificationsApp.supplyer.orgName}</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.linkMan"><spring:message code="qualificationsAppForm.linkMan"/>：</label>
					<span id="linkMan">${qualificationsApp.linkMan}</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.linkPhone"><spring:message code="qualificationsAppForm.linkPhone"/>：</label>
					<span id="linkPhone">${qualificationsApp.linkPhone}</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.email"><spring:message code="qualificationsAppForm.email"/>：</label>
					<span id="email">${qualificationsApp.email}</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.address"><spring:message code="qualificationsAppForm.address"/>：</label>
					<span id="address">${qualificationsApp.address}</span>
    	    </li>
	     	<li>
	     		<label for="qualificationsAppForm.zipCode"><spring:message code="qualificationsAppForm.zipCode"/>：</label>
					<span id="zipCode">${qualificationsApp.zipCode}</span>	    	      	      
    	    </li>
		</ul>
	</form:form>
</div>
