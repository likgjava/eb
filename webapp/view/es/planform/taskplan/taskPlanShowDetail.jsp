<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanShowDetail.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">        
	<form:form id="taskPlanDetail_Form" method="post" modelAttribute="taskPlan">
	<input type="hidden" id="show_detail_id" value="${taskPlan.objId}"></input>
		<table>
			<tr>
				<td colspan="4"><strong>申报书信息</strong></td>
			</tr>
				<tr>
					<th><label><spring:message code="taskPlanForm.taskCode"/>：</label></th>
					<td>
						<span id="taskCodeSpan">${taskPlan.taskCode}</span>
						<form:hidden path="taskCode"></form:hidden>
					</td>
					<th><label><spring:message code="taskPlanForm.taskName"/>：</label></th>
					<td>
						<span>${taskPlan.taskName}</span>
					</td>
				</tr>
				<tr>
					<th><label><spring:message code="taskPlanForm.ebuyMethod"/>：</label></th>
					<td>
						<span>${taskPlan.ebuyMethodCN}</span>
					</td>
					<th><label><spring:message code="taskPlanForm.taskType"/>：</label></th>
					<td>
						<c:if test="${taskPlan.taskType=='00' }">
							<span>非大宗交易</span>
						</c:if>
						<c:if test="${taskPlan.taskType=='01' }">
							<span>大宗交易</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<th><label><spring:message code="taskPlanForm.applyDate"/>：</label></th>
					<td>
						<span>${taskPlan.applyDate}</span>
					</td>
					<th><label><spring:message code="taskPlanForm.finishDate"/>：</label></th>
					<td>
						<span>${taskPlan.finishDate}</span>
					</td>
				</tr>
				<tr>
					<th><label><spring:message code="taskPlanForm.taskAgent"/>：</label></th>
					<td>
						<span>${taskPlan.taskAgentName }</span>
					</td>
					<th><label><spring:message code="taskPlanForm.budgetLinker"/>：</label></th>
					<td>
						<span>${taskPlan.budgetLinker}</span>
					</td>
				</tr>
				<tr>
					<th><label><spring:message code="taskPlanForm.budgetLinkerTel"/>：</label></th>
					<td colspan="3">
						<span>${taskPlan.budgetLinkerTel}</span>
					</td>
					
				</tr>
				<tr>
				<th>
					<label for="government"><spring:message code="taskPlanForm.government"/>：</label>
				</th>
				<td>
					<span>${taskPlan.governmentName}</span>
				</td>
				
				<th>
					<label for="govLinker"><spring:message code="taskPlanForm.govLinker"/>：</label>
				</th>
				<td>
					<span>${taskPlan.govLinker}</span>
				</td>
			</tr>
			<tr>
				<th>
					<label for="govLinkerTel"><spring:message code="taskPlanForm.govLinkerTel"/>：</label>
				</th>
				<td colspan="3">
					<span>${taskPlan.govLinkerTel }</span>
				</td>
			</tr>
			<tr>
			<th>
				<label for="departmentName"><spring:message code="taskPlanForm.department"/>：</label>
			</th>
			<td>
				<span>${taskPlan.departmentName}</span>
			</td>
			<th>
				<label for="departmentLinker"><spring:message code="taskPlanForm.departmentLinker"/>：</label>
			</th>
			<td>
				<span>${taskPlan.departmentLinker }</span>
			</td>
			</tr>
			<tr>
				<th>
					<label for="departmentLinkerTel"><spring:message code="taskPlanForm.departmentLinkerTel"/>：</label>
				</th>
				<td colspan="3">
					 <span>${taskPlan.departmentLinkerTel }</span>
				</td>
			</tr>
		</table>
	</form:form>
	<table>
		<tbody id="taskPlanItemList"></tbody>
		<tbody id="taskPlanDetailList"></tbody>
	</table>
		<div class="conOperation">
			<button type="button" id="taskPlanPrintView"><span>打印预览</span></button> 
			<button name="historyBackBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
</div>
</div>