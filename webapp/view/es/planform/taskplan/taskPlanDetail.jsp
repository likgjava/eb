<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanDetail.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="paper">
    <p class="headInfo">&nbsp;</p>      	
    <h1><span>采购<dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>信息</span></h1>
 	<p class="headInfo">&nbsp;</p> 
<input type="hidden" value="${from }" id="from"/>
<input type="hidden" id="type" value="<c:out value="${type}"/>" />
<input type="hidden" id="taskPlanIds" name="taskPlanIds"  value="${taskPlan.objId}">
<input type="hidden" id="isBlockTrade" name="isBlockTrade"  value="${isBlockTrade}">
<input type="hidden" id="consignId" name="consignId"  value="${consign.objId}">
<input type="hidden" id="fromto" name="fromto"  value="${fromto}">
<input type="hidden" id="canConsign" value="${param.canConsign}">
<input type="hidden" id="noConsign" value="${noConsign}">      
	<form:form id="taskPlanForm" method="post" modelAttribute="taskPlan">
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditDetail"></form:hidden>
		<form:hidden path="confirmStatus"></form:hidden>
		
     		<table class="tableListP">
					<tr>
						<th><label class="short" for="taskCode"><spring:message code="taskPlanForm.taskCode"/></label></th>
						<td><span id="taskCode">${taskPlan.taskCode}</span></td>
					
						<th><label class="short"  for="taskName"><spring:message code="taskPlanForm.taskName"/></label></th>
						<td><span id="taskName">${taskPlan.taskName}</span></td>
					</tr>
					<tr>
						<th><label class="short"  for="applyDate"><spring:message code="taskPlanForm.applyDate"/></label></th>
						<td><span id="applyDate"><fmt:formatDate value="${taskPlan.applyDate}" pattern="yyyy-MM-dd"/></span></td>	

						<th><label class="short"  for="finishDate"><spring:message code="taskPlanForm.finishDate"/></label></th>
						<td><span id="finishDate"><fmt:formatDate value="${taskPlan.finishDate}" pattern="yyyy-MM-dd"/></span></td>	
					</tr>
					<tr>
						<th><label class="short"  for="ebuyMethod"><spring:message code="taskPlanForm.ebuyMethodCN"/></label></th>
						<td><span id="ebuyMethodCN">${taskPlan.ebuyMethodCN}</span></td>
					
					    <th><label class="short"  for="taskType"><spring:message code="taskPlanForm.taskType"/></label></th>
						<td><span id="taskTypeCN">${taskPlan.taskTypeCN}</span></td>
					</tr>
					
					
					<tr>
						<th><label class="short"><spring:message code="taskPlanForm.totalScale"/></label></th>
						<td><span id="totalScale">${taskPlan.totalScale}</span></td>
						
						<th><label class="short"><spring:message code="taskPlanForm.constructScale"/></label></th>
						<td><span id="totalScale">${taskPlan.constructScale}</span></td>
					</tr>
					
					<tr>
						<th><label class="short"><spring:message code="taskPlanForm.moneySrc"/></label></th>
						<td><span id="totalScale">${taskPlan.moneySrcCN}</span></td>
						
						<th><label class="short"><spring:message code="taskPlanForm.totalAmount"/></label></th>
						<td><span id="totalScale">${taskPlan.totalAmount}</span></td>
					</tr>
					
					<tr>
						<th><label class="short"><spring:message code="taskPlanForm.prjAddress"/></label></th>
						<td><span id="totalScale">${taskPlan.prjAddress}</span></td>
					</tr>
					
					
					<tr>
					    <th><label class="short"  for="taskAgent">招标中心</label></th>
						<td><span id="taskAgentName">${taskPlan.taskAgentName}</span></td>	

						<th><label class="short"  for="budgetName"><spring:message code="taskPlanForm.budget"/></label></th>
						<td><span id="budgetName">${taskPlan.budgetName}</span></td>			 		  
					</tr>
					<tr>
						<th><label class="short"  for="budgetLinker"><spring:message code="taskPlanForm.budgetLinker"/></label></th>
						<td><span id="budgetLinker">${taskPlan.budgetLinker}</span></td>	

                        <th><label class="short"  for="budgetLinkerTel"><spring:message code="taskPlanForm.budgetLinkerTel"/></label></th>
						<td><span id="budgetLinkerTel">${taskPlan.budgetLinkerTel}</span></td>	
					</tr>
				</table>
	</form:form>
</div>
<input type="hidden" id="taskPlanZh" value="<dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>明细（金额单位：元）">
	<div id="taskPlanSubListView" class="partContainers">
    	<flex:flexgrid checkbox="false"
			id="taskPlanSubGrid" url="TaskPlanMSubController.do?method=list&taskPlan.objId=${taskPlan.objId}" queryColumns="" rp="10" title="申报书列表（金额单位：元）" 
				onSubmit="taskPlanDetail.before" usepager="false" onSuccess="taskPlanDetail.success">
						<flex:flexCol name="taskPlanSub.purchaseName" display="taskPlanSubForm.purchaseName" sortable="true" width="180"align="left"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.unit" display="taskPlanSubForm.unit" sortable="true" width="100"align="center"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.unitPrice" format="money" display="taskPlanSubForm.unitPrice" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.quantity" format="amount" display="taskPlanSubForm.quantity" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.totalPrice" format="money" display="taskPlanSubForm.totalPrice" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.objId" display="操作" sortable="true" width="60" align="center"></flex:flexCol>
		</flex:flexgrid>
    </div>
<div id="historyView"></div>
<div class="conOperationBtnDiv">
	<button id="toConsign" type="button" tabindex="19"><span>发起委托</span></button>
	<button id="toPrint" type="button" tabindex="19"><span>打印预览</span></button>
	<c:set var="grantFlag" value="false"/>
<c:if test="${grantFlag!=true}">
	<authz:authorize ifAnyGranted="CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId">
		<c:set var="grantFlag" value="true"/>
		<button id="historyGrantedBtn" type="button" tabindex="19"><span>查看历史</span></button>
	</authz:authorize>
</c:if>
<c:if test="${grantFlag!=true}">
	<c:if test="${taskPlan.budget.objId==budgetId}">
		<c:set var="grantFlag" value="true"/>
		<button id="currentAuthBtn" type="button" tabindex="19"><span>查看历史</span></button>
	</c:if>
</c:if>
	<button name="return_to_list" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>