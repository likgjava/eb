<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projectplan/projectPlanForm.js"></script>
<div class="formLayout form2Pa">
	<form:form id="projectPlanForm" method="post" modelAttribute="projectPlan">
		<form:hidden path="objId"/>
		<form:hidden path="project.objId"/>
		<input type="hidden" id="resourceId" value="${projectPlan.resource.objId }"></input>
		<input type="hidden" id="taskCode" name="taskCode" value="${projectPlan.taskCode}"></input>
     	<ul>
			<li>
   				<label for="projectPlanForm.project"><spring:message code="projectPlanForm.project"/>：</label>
   					${projectPlan.project.projName}
			</li>
	     	<li>
	     		<label for="projectPlanForm.planTemplate"><spring:message code="projectPlanForm.planTemplate"/>：</label>
					${projectPlan.planTemplate.name}
    	    </li>
	     	<li>
	     		<label for="projectPlanForm.code"><spring:message code="projectPlanForm.code"/>：</label>
	     			${projectPlan.code}
    	    </li>
	     	<li>
	     		<label for="projectPlanForm.name"><spring:message code="projectPlanForm.name"/>：</label>
					<form:input path="name" cssClass="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
   				<label for="projectPlanForm.parent"><spring:message code="projectPlanForm.parent"/>：</label>
            		${projectPlan.parent.name }
			</li>
	     	<li>
   				<label for="projectPlanForm.prePlan"><spring:message code="projectPlanForm.prePlan"/>：</label>
            		<form:input path="prePlan.name" readonly="readonly" cssClass="sysicon siSearch_view_tree"/>
            		<form:hidden path="prePlan.objId"/>
			</li>
	     	<li>
   				<label for="projectPlanForm.backPlan"><spring:message code="projectPlanForm.backPlan"/>：</label>
            		<form:input path="backPlan.name" readonly="readonly" cssClass="sysicon siSearch_view_tree"/>
            		<form:hidden path="backPlan.objId"/>
			</li>
	     	<li class="fullLine">
	     		<label for="projectPlanForm.resource"><spring:message code="projectPlanForm.resource"/>：</label>
	     			${projectPlan.resource.name }
    	    </li>
	     	<li>
	     		<label for="projectPlanForm.planStartDate"><spring:message code="projectPlanForm.planStartDate"/>：</label>
					<form:input path="planStartDate" readonly="readonly" cssClass="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projectPlanForm.planEndDate"><spring:message code="projectPlanForm.planEndDate"/>：</label>
					<form:input path="planEndDate" readonly="readonly" cssClass="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projectPlanForm.role"><spring:message code="projectPlanForm.role"/>：</label>
					<form:input path="role.chName" readonly="readonly" cssClass="sysicon siSearch_view_tree"/>
	     			<form:hidden path="role.objId"/>
    	    </li>
	     	<li>
	     		<label for="projectPlanForm.employee"><spring:message code="projectPlanForm.employee"/>：</label>
					<form:input path="employee.name" readonly="readonly" cssClass="sysicon siSearch_view_tree"/>
	     			<form:hidden path="employee.objId"/>
    	    </li>
	     	<li class="fullLine" id="ruleLi">
	     		<input type="hidden" name="rule" id="rule" value="${projectPlan.rule}"></input>
	     		<label>${sysConfigItemName}：</label>
				是<input type="radio" name="useStatus" id="useStatus" value="01" <c:if test='${projectPlan.useStatus == 01}'>checked="checked"</c:if> ></input>
				否<input type="radio" name="useStatus" id="useStatus" value="00" <c:if test='${projectPlan.useStatus == 00}'>checked="checked"</c:if> ></input>
				<span class="eleRequired"></span>
    	    </li>
    	   	<li class="fullLine">
    	   		<label>待办任务：</label>
				<span>${projectPlan.taskCode}</span>
    	    </li>
	     	<li class="formTextarea">
	     		<label for="projectPlanForm.memo"><spring:message code="projectPlanForm.memo"/>：</label>
					<textarea name="memo" id="memo" class="maxInput" maxlength="50" >${projectPlan.memo}</textarea>
					<span class="eleRequired"></span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="projectPlanSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="projectPlanClose"><span><spring:message code="globe.close"/></span></button>
		   </div>
	</form:form>
</div>