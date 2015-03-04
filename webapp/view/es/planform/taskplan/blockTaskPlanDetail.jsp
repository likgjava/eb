<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/blockTaskPlanDetail.js"></script>

<div class="partContainers">
<input type="hidden" id="type" value="<c:out value="${type}"/>" />
<input type="hidden" id="taskPlanIds" name="taskPlanIds" value="${taskPlan.objId}">
<div class="formLayout form2Pa">        
	<form:form id="taskPlanForm" method="post" modelAttribute="taskPlan">
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditDetail"></form:hidden>
		<form:hidden path="confirmStatus"></form:hidden>
		<h5><span>采购申报书</span></h5>
     		<ul>
					<li>
						<label for="taskCode"><spring:message code="taskPlanForm.taskCode"/>：</label>
						<span id="taskCode">${taskPlan.taskCode}</span>
					</li>
					<li>
						<label for="taskName"><spring:message code="taskPlanForm.taskName"/>：</label>
						<span id="taskName">${taskPlan.taskName}</span>
					</li>
					<li>
						<label for="applyDate"><spring:message code="taskPlanForm.applyDate"/>：</label>
						<span id="applyDate"><fmt:formatDate value="${taskPlan.applyDate}" pattern="yyyy-MM-dd"/></span>	
					</li>
					
					<li>
						<label for="finishDate"><spring:message code="taskPlanForm.finishDate"/>：</label>
						<span id="finishDate"><fmt:formatDate value="${taskPlan.finishDate}" pattern="yyyy-MM-dd"/></span>	
					</li>
					<li>
						<label for="ebuyMethod"><spring:message code="taskPlanForm.ebuyMethodCN"/>：</label>
						<span id="ebuyMethodCN">${taskPlan.ebuyMethodCN}</span>
					</li>
					<li><label for="taskType"><spring:message code="taskPlanForm.taskType"/>：</label>
						<span id="taskTypeCN">${taskPlan.taskTypeCN}</span>
					</li>
					<li><label for="taskAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
						<span id="taskAgentName">${taskPlan.taskAgentName}</span>	
					</li>
					<li>
						<label for="budgetName"><spring:message code="taskPlanForm.budget"/>：</label>
						<span id="budgetName">${taskPlan.budgetName}</span>			 		  
					</li>
					<li>
						<label for="budgetLinker"><spring:message code="taskPlanForm.budgetLinker"/>：</label>
						<span id="budgetLinker">${taskPlan.budgetLinker}</span>	
					</li>
					<li>
						<label for="budgetLinkerTel"><spring:message code="taskPlanForm.budgetLinkerTel"/>：</label>
						<span id="budgetLinkerTel">${taskPlan.budgetLinkerTel}</span>	
					</li>
					<li>
						<label for="departmentName"><spring:message code="taskPlanForm.department"/>：</label>
					    <span id="departmentName">${taskPlan.departmentName}</span>	
					</li>
					<li>
						<label for="departmentLinker"><spring:message code="taskPlanForm.departmentLinker"/>：</label>
						<span id="departmentLinker">${taskPlan.departmentLinker}</span>		
					</li>
					<li>
						<label for="departmentLinkerTel"><spring:message code="taskPlanForm.departmentLinkerTel"/>：</label>
						<span id="departmentLinkerTel">${taskPlan.departmentLinkerTel}</span>			 
					</li>
					<li>
						<label for="governmentName"><spring:message code="taskPlanForm.government"/>：</label>
					    <span id="governmentName">${taskPlan.governmentName}</span>	
					</li>
					<li>
						<label for="govLinker"><spring:message code="taskPlanForm.govLinker"/>：</label>
						<span id="govLinker">${taskPlan.govLinker}</span>			 	  
					</li>
					<li>
						<label for="govLinkerTel"><spring:message code="taskPlanForm.govLinkerTel"/>：</label>
						<span id="govLinkerTel">${taskPlan.govLinkerTel}</span>			 		  
					</li>
					<li class="fullLine" id="consignLi">
						<label for="govLinkerTel">委托协议：</label>
						<span id="consigns">
							<c:forEach items="${ctList}" var="consign" >
								<a href="javascript:void(0)" onclick="taskPlanDetail.showConsign('${consign.objId }')">${consign.consName }</a>
							</c:forEach>
						</span>			 		  
					</li>
		</ul>
		<div class="conOperation">
			<button id="taskPlanReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
			<button id="toPrint" type="button" tabindex="19"><span>打印预览</span></button>
			<!--<button id="toConsignprotocol" type="button" tabindex="19"><span>发起委托</span></button>
		--></div>
	</form:form>
</div>
</div>
<div id="epsTabs">
	<ul>
		<li id="li_subs">
	      <a href="#taskPlanSubListView" id = "tabs_subs" class="refreshData"><span>申报书明细</span></a>
	    </li>
	    <li id="li_details">
	      <a href="#taskPlanDetailListView" id = "tabs_details" class="refreshData"><span>采购资金明细</span></a>
	    </li>
    </ul>
    <div id="taskPlanSubListView">
    	<flex:flexgrid checkbox="false"
			id="taskPlanSubGrid" url="TaskPlanMSubController.do?method=list&taskPlan.objId=${taskPlan.objId}" queryColumns="" rp="10" title="申报书明细"  
				onSubmit="taskPlanDetail.before" usepager="false">
						<flex:flexCol name="taskPlanSub.purchaseName" display="taskPlanSubForm.purchaseName" sortable="true" width="100"align="left"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.unit" display="taskPlanSubForm.unit" sortable="true" width="50"align="center"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.unitPrice" format="money" display="taskPlanSubForm.unitPrice" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.quantity" format="amount" display="taskPlanSubForm.quantity" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.totalPrice" format="money" display="taskPlanSubForm.totalPrice" sortable="true" width="100"align="right"></flex:flexCol>
						<flex:flexCol name="taskPlanSub.remark" display="taskPlanSubForm.remark" sortable="true" width="200" align="left"></flex:flexCol>
		</flex:flexgrid>
    </div>
    <div id="taskPlanDetailListView">
    	<flex:flexgrid checkbox="false"
			id="taskPlanDetailGrid" url="TaskPlanMDetailController.do?method=list&taskPlan.objId=${taskPlan.objId}" queryColumns=""  rp="10" title="采购资金明细"  
				onSubmit="taskPlanDetail.before" usepager="false">
						<flex:flexCol name="taskPlanDetail.approvalNumber" display="taskPlanDetailForm.approvalNumber" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.superiorApp"format="money" display="taskPlanDetailForm.superiorApp" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.localApp" format="money" display="taskPlanDetailForm.localApp" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.ownerApp" format="money" display="taskPlanDetailForm.ownerApp" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.otherApp" format="money" display="taskPlanDetailForm.otherApp" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.quantity" format="money" display="taskPlanDetailForm.quantity" sortable="true" width="100"align="center"></flex:flexCol>
		</flex:flexgrid>
    </div>
</div>