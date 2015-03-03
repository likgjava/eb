<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="SuccessCaseDetailForm" method="post" modelAttribute="successCase">
	<input type="hidden" name="objId" id="objId" value="${successCase.objId}">
	<div class="formLayout  form2Pa">
     	<h4><span>案例信息</span></h4>
     	<ul>
    	    <li class="fullLine"><label><spring:message code="successCaseForm.projectName"/>：</label> 
    	    	<span id="projectName">${successCase.projectName}</span>
			</li>
			<li class="fullLine"><label><spring:message code="successCaseForm.startTime"/>：</label> 
    	    	<span id="startTime"><fmt:formatDate value="${successCase.startTime}" pattern="yyyy-MM-dd"/></span>
			</li>
			<li class="fullLine"><label><spring:message code="successCaseForm.endTime"/>：</label> 
    	    	<span id="endTime"><fmt:formatDate value="${successCase.endTime}" pattern="yyyy-MM-dd"/></span>
			</li>
			<li class="fullLine"><label>采购内容：</label> 
    	    	<span id="categoryNames">${successCase.categoryNames}</span>
			</li>
	     	<li class="fullLine">
	     		<label for="successCaseForm.description"><spring:message code="successCaseForm.description"/>：</label>
				<span id="categoryNames">${successCase.description}</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="returnBtn" name="historyBackBtn"><span>返回</span></button>
		   </div>
	   </div>
</form:form>
