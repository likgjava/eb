<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projectplan/lookMoreWaitprocWorkTaskProjectPlanList.js"></script>
<div class="partContainers">
	<div class="partContainers">
		<div class="formLayout form2Pa">
			<table class="tableList">
				<tr>
					<th><spring:message code="projectPlanForm.code" /></th>
					<th><spring:message code="projectPlanForm.name" /></th>
					<th><spring:message code="projectPlanForm.planStartDate" /></th>
					<th><spring:message code="projectPlanForm.planEndDate" /></th>
					<th><spring:message code="projectPlanForm.prePlan" /></th>
					<th>操作</th>
				</tr>
				<c:forEach items="${projectPlanList}" var="plan">
					<tr>
						<td style="text-align: center;">${plan.code}</td>
						<td>${plan.name}</td>
						<td style="text-align: center;"><fmt:formatDate value="${plan.planStartDate}" pattern="yyyy-MM-dd"/></td>
						<td style="text-align: center;"><fmt:formatDate value="${plan.planEndDate}" pattern="yyyy-MM-dd"/></td>
						<td>${plan.prePlan.name}</td>
						<td><button name="execute_proj_plan" title="点击进入  ${plan.name}" objId="${plan.objId}" parentTaskId="${plan.parent.objId}" class="sysicon siControl_fastforward_right"><span></span></button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<div class="conOperation">
	<button id="return_plan_info" tabindex="20"><span><spring:message code="globe.close"/></span></button>
</div>