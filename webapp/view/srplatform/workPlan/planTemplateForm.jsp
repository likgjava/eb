<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/workPlan/planTemplateForm.js"></script>
<style>
.planTempDis{
display: none;
}
</style>
<input type="hidden" id="tempTypeId" value="${planTemplate.tempType}"/>
<div class="formLayout form2Pa">
	<form:form id="planTemplateForm" method="post" modelAttribute="planTemplate">
		<form:hidden path="objId"/>
     	<h5><span>计划模版</span></h5>
     	<ul>
	     	<li >
	     		<label class="short"><spring:message code="planTemplateForm.name"/>：</label>
	     		<form:input path="name" cssClass="required" size="50"/>
    	   		<span class="eleRequired">*</span>
    	    </li>
	     	<li >
	     		<label class="short">编号：</label>
	     		<form:input path="code" cssClass="required" size="50"/>
    	   		<span class="eleRequired">*</span>
    	    </li>
	     	<li>
				<label class="short"><spring:message code="planTemplateForm.type"/>：</label>
				<html:select styleClass="required" id="type" name="type" code="srplatform.workPlan.planTemplate.buyMethod" selectedValue="">
				</html:select>
    	    </li>
	     	<li>
				<label class="short">类型：</label>
				<c:choose>
					<c:when test="${planTemplate.tempType==00}">
						<input type="radio" name="tempType" id="tempType" value="00" onclick="planTemplateForm.changeTempType(00);" checked="checked"/>&nbsp;默认&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="tempType" id="tempType" value="01"  onclick="planTemplateForm.changeTempType(01);"/>&nbsp;指定代理机构
					</c:when>
					<c:otherwise>
						<input type="radio" name="tempType" id="tempType" value="00" onclick="planTemplateForm.changeTempType(00);"/>&nbsp;默认&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="tempType" id="tempType" value="01"  onclick="planTemplateForm.changeTempType(01);" checked="checked"/>&nbsp;指定代理机构
					</c:otherwise>
				</c:choose>
				<span class="eleRequired">*</span>
    	    </li>
	     	<li class="fullLine" id="planTemplateOrg">
	     		<label class="short"><spring:message code="planTemplateForm.org"/>：</label>
	     		<form:input path="org.name" cssClass="required"/>
	     		<form:hidden path="org.objId"/>
	     		<span class="eleRequired">*</span>
    		 </li>
	     	<li class="fullLine">
	     		<label class="short"><spring:message code="planTemplateForm.rule"/>：</label>
	     		<form:input path="rule" size="50"/>
    	    </li>
		    <li class="formTextarea">
		        <label class="short"><spring:message code="planTemplateTaskForm.memo"/>：</label>
		        <form:textarea path="memo"/>
		    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="planTemplateSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="planTemplateTask"><span>计划详情</span></button>
				<button type="button" name="historyBackBtn" type="button" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form:form>
</div>