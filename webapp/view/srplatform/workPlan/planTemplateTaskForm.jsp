<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/workPlan/planTemplateTaskForm.js"></script>
<div class="formLayout form2Pa">
	<form:form id="planTemplateTaskForm" method="post" modelAttribute="planTemplateTask">
		<form:hidden path="objId"/>
		<form:hidden path="planTemplate.objId"/>
		<form:hidden path="path"/>
		<form:hidden path="level"/>
		<form:hidden path="isLeaf"/>
     	<ul>
	     	<li>
	     		<label for="planTemplateTaskForm.code" class="short"><spring:message code="planTemplateTaskForm.code"/>：</label>
					<form:input path="code" cssClass="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="planTemplateTaskForm.name" class="short"><spring:message code="planTemplateTaskForm.name"/>：</label>
					<form:input path="name" cssClass="required"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="planTemplateTaskForm.parent" class="short"><spring:message code="planTemplateTaskForm.parent"/>：</label>
		            ${planTemplateTask.parent.name }
		            <form:hidden path="parent.objId"/>
			</li>
	     	<li>
	     		<label for="planTemplateTaskForm.prePlan" class="short"><spring:message code="planTemplateTaskForm.prePlan"/>：</label>
		            <form:input path="prePlan.name" readonly="readonly" cssClass="sysicon siSearch_view_tree"/>
            		<form:hidden path="prePlan.objId"/>
			</li>
	     	<li>
	     		<label for="planTemplateTaskForm.backPlan" class="short"><spring:message code="planTemplateTaskForm.backPlan"/>：</label>
		            <form:input path="backPlan.name" readonly="readonly" cssClass="sysicon siSearch_view_tree"/>
            		<form:hidden path="backPlan.objId"/>
			</li>
	     	<li>
   				<label for="planTemplateTaskForm.resource" class="short"><spring:message code="planTemplateTaskForm.resource"/>：</label>
            		<form:input path="resource.name" readonly="readonly" cssClass="required sysicon siSearch_view_tree"/>
            		<form:hidden path="resource.objId"/>
            		<span class="eleRequired">*</span>
            		<form:hidden path="resource.path"/>
            		<form:hidden path="resource.url"/>
            		<a href="#" id="pttfResourceDetail">资源详情</a>
			</li>
	     	<li>
	     		<label for="planTemplateTaskForm.role" class="short"><spring:message code="planTemplateTaskForm.role"/>：</label>
	     			<form:input path="role.chName" readonly="readonly" cssClass="required sysicon siSearch_view_tree"/>
	     			<form:hidden path="role.objId"/>
	     			<span class="eleRequired">*</span>
			</li>
			<li>
	     		<label for="planTemplateTaskForm.duration" class="short"><spring:message code="planTemplateTaskForm.duration"/>（日）：</label>
					<form:input path="duration" cssClass="floats required"/>
					<span class="eleRequired">*</span>
    	    </li>
    	    <li>
   				<label class="short">查看组件资源：</label>
            		<form:input path="resourceView.name" readonly="readonly" cssClass="sysicon siSearch_view_tree"/>
            		<form:hidden path="resourceView.objId"/>
            		<form:hidden path="resourceView.path"/>
            		<form:hidden path="resourceView.url"/>
            		<a href="#" id="pttfResourceViewDetail">资源详情</a>
			</li>
			<li>
	     		<label for="planTemplateTaskForm.isAutoEnd" class="short"><spring:message code="planTemplateTaskForm.isAutoEnd"/>：</label>
					<form:radiobutton path="isAutoEnd" value="0"/>否
					<form:radiobutton path="isAutoEnd" value="1" />是
    	    </li>
    	    <li >
	     		<label for="planTemplateTaskForm.serviceClassName" class="short"><spring:message code="planTemplateTaskForm.serviceClassName"/>：</label>
				<input type="text" class="required" name="serviceClassName" id="serviceClassName" value="${planTemplateTask.serviceClassName}"></input>
				<span class="eleRequired">*</span>
    	    </li>
    	    <li >
	     		<label for="planTemplateTaskForm.serviceMethodName" class="short"><spring:message code="planTemplateTaskForm.serviceMethodName"/>：</label>
				<input type="text"  class="required" name="serviceMethodName" id="serviceMethodName" value="${planTemplateTask.serviceMethodName}"></input>
				<span class="eleRequired">*</span>
    	    </li>
    	     <li class="fullLine">
	     		<label for="" class="short">添加子流程：</label>
	     		<input type="hidden" name="subProcesseTemplateId" id="subProcesseTemplateId" value="${planTemplateTask.subProcesseTemplateId }"/>
				<input type="text"  class="long" name="subProcesseTemplate" id="subProcesseTemplate" value="${pdt.processDefName }" readonly/>
				<a href="#" id="selectSubProcesseTemplate">选择流程模板</a>&nbsp;|&nbsp;<a href="#" id="deleteSubProcesseTemplate">删除</a>
    	    </li>
    	    <li class="fullLine">
	     		<label for="planTemplateTaskForm.startRuleMethod" class="short"><spring:message code="planTemplateTaskForm.startRuleMethod"/>：</label>
				<input type="text"  class="long" name="startRuleMethod" id="startRuleMethod" value="${planTemplateTask.startRuleMethod}"></input>
    	    </li>
    	    <li class="fullLine">
	     		<label for="planTemplateTaskForm.endRuleMethod" class="short"><spring:message code="planTemplateTaskForm.endRuleMethod"/>：</label>
				<input type="text"  class="long" name="endRuleMethod" id="endRuleMethod" value="${planTemplateTask.endRuleMethod}"></input>
    	    </li>
    	    <li class="fullLine">
    	    	<label class="short">业务规则：</label>
				<input type="text" id="bizRuleNameId" name="bizRuleName" value="${planTemplateTask.bizRuleName}"/>
				<input type="hidden" id="bizRuleId" name="bizRuleId" value="${planTemplateTask.bizRuleId}"/>
    	    </li>
	     	<li class="formTextarea" >
	     		<label for="planTemplateTaskForm.rule" class="short"><spring:message code="planTemplateTaskForm.rule"/>：</label>
				<form:textarea path="rule"/>
    	    </li>
	     	<li class="formTextarea">
	     		<label for="planTemplateTaskForm.memo" class="short"><spring:message code="planTemplateTaskForm.memo"/>：</label>
					<form:textarea path="memo"/>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="planTemplateTaskSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="planTemplateTaskClose"><span><spring:message code="globe.close"/></span></button>
		   </div>
	</form:form>
</div>