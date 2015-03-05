<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
	<form:form id="planTemplateForm" method="post" modelAttribute="planTemplate">
     	<h5><span>计划模版</span></h5>
     	<ul>
	     	<li class="fullLine">
	     		<label class="short"><spring:message code="planTemplateForm.name"/>：</label>
	     		${planTemplate.name }
    	    </li>
	     	<li>
	     		<label class="short"><spring:message code="planTemplateForm.type"/>：</label>
				${planTemplate.typeCN }
    	    </li>
    	    <li>
    	    	<label class="short">编号：</label>
				${planTemplate.code}
    	    </li>
    	    <c:if test="${planTemplate.tempType=='01'}">
		     	<li>
		     		<label class="short"><spring:message code="planTemplateForm.org"/>：</label>
		     		${planTemplate.org.name }
	    		 </li>
    	    </c:if>
	     	<li class="fullLine">
	     		<label class="short"><spring:message code="planTemplateForm.rule"/>：</label>
	     		${planTemplate.rule }
    	    </li>
		    <li class="fullLine">
		        <label class="short"><spring:message code="planTemplateTaskForm.memo"/>：</label>
		        ${planTemplate.memo }
		    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" name="historyBackBtn" type="button" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form:form>
</div>
