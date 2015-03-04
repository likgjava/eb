<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanDetailForSelectAgent.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="partContainers">
<input type="hidden" id="type" value="<c:out value="${type}"/>" />
<input type="hidden" id="taskPlanIds" name="taskPlanIds"  value="${taskPlan.objId}">
<input type="hidden" id="isBlockTrade" name="isBlockTrade"  value="${isBlockTrade}">
<input type="hidden" id="consignId" name="consignId"  value="${consign.objId}">
<input type="hidden" id="fromto" name="fromto"  value="${fromto}">
<div class="formLayout form2Pa">        
	<form:form id="taskPlanForm" method="post" modelAttribute="taskPlan">
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditDetail"></form:hidden>
		<form:hidden path="confirmStatus"></form:hidden>
		<h5><span><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>信息</span></h5>
     		<ul>
					<li>
						<label class="short"  for="taskCode"><spring:message code="taskPlanForm.taskCode"/>：</label>
						<span id="taskCode">${taskPlan.taskCode}</span>
					</li>
					<li>
						<label class="short"  for="taskName"><spring:message code="taskPlanForm.taskName"/>：</label>
						<span id="taskName">${taskPlan.taskName}</span>
					</li>
					<li>
						<label class="short"  for="applyDate"><spring:message code="taskPlanForm.applyDate"/>：</label>
						<span id="applyDate"><fmt:formatDate value="${taskPlan.applyDate}" pattern="yyyy-MM-dd"/></span>	
					</li>
					
					<li>
						<label class="short"  for="finishDate"><spring:message code="taskPlanForm.finishDate"/>：</label>
						<span id="finishDate"><fmt:formatDate value="${taskPlan.finishDate}" pattern="yyyy-MM-dd"/></span>	
					</li>
					<li>
						<label class="short"  for="ebuyMethod"><spring:message code="taskPlanForm.ebuyMethodCN"/>：</label>
						<span id="ebuyMethodCN">${taskPlan.ebuyMethodCN}</span>
					</li>
					<li><label class="short"  for="taskType"><spring:message code="taskPlanForm.taskType"/>：</label>
						<span id="taskTypeCN">${taskPlan.taskTypeCN}</span>
					</li>
					<li><label class="short"  for="taskAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
						<span id="taskAgentName">${taskPlan.taskAgentName}</span>	
					</li>
					<li>
						<label class="short"  for="budgetName"><spring:message code="taskPlanForm.budget"/>：</label>
						<span id="budgetName">${taskPlan.budgetName}</span>			 		  
					</li>
					<li>
						<label class="short"  for="budgetLinker"><spring:message code="taskPlanForm.budgetLinker"/>：</label>
						<span id="budgetLinker">${taskPlan.budgetLinker}</span>	
					</li>
					<li>
						<label class="short"  for="budgetLinkerTel"><spring:message code="taskPlanForm.budgetLinkerTel"/>：</label>
						<span id="budgetLinkerTel">${taskPlan.budgetLinkerTel}</span>	
					</li>
					<c:if test="${!empty ctList}">
						<li class="fullLine" id="consignLi">
							<label class="short"  for="govLinkerTel">委托协议：</label>
							<span id="consigns">
									<c:forEach items="${ctList}" var="consign" >
										<a href="javascript:void(0)" style="color: blue;" onclick="taskPlanDetail.showConsign2('${consign.objId }')">${consign.consName }</a>
									</c:forEach>
							</span>	
						</li>
					</c:if>
				</ul>
			<h6 align="center"><span>业务处室信息</span></h6>
				<ul>
					<li>
						<label class="short"  for="governmentName"><spring:message code="taskPlanForm.government"/>：</label>
					    <span id="governmentName">${taskPlan.governmentName}</span>	
					</li>
					<li>
						<label class="short"  for="govLinker"><spring:message code="taskPlanForm.govLinker"/>：</label>
						<span id="govLinker">${taskPlan.govLinker}</span>			 	  
					</li>
					<li class="fullLine">
						<label class="short"  for="govLinkerTel"><spring:message code="taskPlanForm.govLinkerTel"/>：</label>
						<span id="govLinkerTel">${taskPlan.govLinkerTel}</span>			 		  
					</li>
		</ul>
			<h6 align="center"><span>主管单位信息</span></h6>
				<ul>
					<li>
						<label class="short"  for="departmentName"><spring:message code="taskPlanForm.department"/>：</label>
					    <span id="departmentName">${taskPlan.departmentName}</span>	
					</li>
					<li>
						<label class="short"  for="departmentLinker"><spring:message code="taskPlanForm.departmentLinker"/>：</label>
						<span id="departmentLinker">${taskPlan.departmentLinker}</span>		
					</li>
					<li class="fullLine">
						<label class="short"  for="departmentLinkerTel"><spring:message code="taskPlanForm.departmentLinkerTel"/>：</label>
						<span id="departmentLinkerTel">${taskPlan.departmentLinkerTel}</span>			 
					</li>
				</ul>
	</form:form>
</div>
	<div id="taskPlanSubListView" class="partContainers">
    	<flex:flexgrid checkbox="false"
			id="taskPlanSubGrid" url="TaskPlanMSubController.do?method=list&taskPlan.objId=${taskPlan.objId}" queryColumns="" rp="10" title="申报书明细" 
				onSubmit="taskPlanDetail.before" usepager="false" onSuccess="taskPlanDetail.success">
						<flex:flexCol name="taskPlanSub.purchaseName" display="taskPlanSubForm.purchaseName" sortable="true" width="180"align="left"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.unit" display="taskPlanSubForm.unit" sortable="true" width="100"align="center"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.unitPrice" format="money" display="taskPlanSubForm.unitPrice" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.quantity" format="amount" display="taskPlanSubForm.quantity" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.totalPrice" format="money" display="taskPlanSubForm.totalPrice" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.objId" display="操作" sortable="true" width="60" align="center"></flex:flexCol>
		</flex:flexgrid>
    </div>
	<div id="taskPlanDetailListView"  class="partContainers">
    	<flex:flexgrid checkbox="false"
			id="taskPlanDetailGrid" url="TaskPlanMDetailController.do?method=list&taskPlan.objId=${taskPlan.objId}" queryColumns=""  rp="10" title="采购资金明细"  
				onSubmit="taskPlanDetail.before" usepager="false">
						<flex:flexCol name="taskPlanDetail.approvalNumber" display="taskPlanDetailForm.approvalNumber" sortable="true" width="160"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.superiorApp"format="money" display="taskPlanDetailForm.superiorApp" sortable="true" width="90"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.localApp" format="money" display="taskPlanDetailForm.localApp" sortable="true" width="90"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.ownerApp" format="money" display="taskPlanDetailForm.ownerApp" sortable="true" width="90"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.otherApp" format="money" display="taskPlanDetailForm.otherApp" sortable="true" width="90"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.quantity" format="money" display="taskPlanDetailForm.quantity" sortable="true" width="90"align="right"></flex:flexCol>
		</flex:flexgrid>
    </div>
</div>
<div id="historyView"></div>
<div class="conOperation">
	<authz:authorize ifAnyGranted="CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId">
		<button id="historyGrantedBtn" type="button" tabindex="19"><span>查看历史</span></button>
	</authz:authorize>
	<button name="return_to_list" tabindex="19"><span><spring:message code="globe.return"/></span></button>
</div>