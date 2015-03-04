<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanForm.js"></script>
<input type="hidden" id="paramType" value="${param.paramType}"></input>
<div class="paper"> 
	<!--    	
  	<div class="operationBtnDiv">
      <button id="printBtn" class="iconBtn" type="button"><span>打印</span></button>
   </div>
    -->
    <h1><span>采购<dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>信息</span></h1>
 	<p class="headInfo">&nbsp;</p>  
    <input type="hidden" id="taskTypes" name="taskTypes"  value="${taskPlan.taskType}">  
	<form:form id="taskPlanForm" method="post" modelAttribute="taskPlan">
		<input type="hidden" name="workFlowTaskId" id="workFlowTaskId" value="${param.workFlowTaskId}"/>
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditDetail"></form:hidden>
		<form:hidden path="confirmStatus"></form:hidden>
		<form:hidden path="budget.objId"></form:hidden>
     		<table class="tableListP">
					<tr>
						<th><label class="short" for="taskCode"><spring:message code="taskPlanForm.taskCode"/></label></th>
						<td>${taskPlan.taskCode}
						 <input type="hidden" name="taskCode" value="${taskPlan.taskCode}" />
						</td>

						<th><label class="short" for="taskName"><spring:message code="taskPlanForm.taskName"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td><form:input path="taskName" cssClass="required" /></td>			    
					</tr>
					<tr>
						<th><label class="short" for="ebuyMethod"><spring:message code="taskPlanForm.ebuyMethod"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td><html:select styleClass="required" id="ebuyMethod" name="ebuyMethod" code="ebuyMethod" selectedValue="${taskPlan.ebuyMethod}">
						   </html:select></td>
					
					    <th><label class="short" for="taskType"><spring:message code="taskPlanForm.taskType"/>
					        &nbsp;<span class="eleRequired">*</span></label></th>
						<td><html:select styleClass="required" id="taskType" name="taskType" code="taskplantype" selectedValue="${taskPlan.taskType}">
						   </html:select></td>
					</tr>
					
					<tr>
						<th><label class="short"><spring:message code="taskPlanForm.totalScale"/></label></th>
						<td><form:input path="totalScale" id="totalScale"  maxlength="100"/></td>
						
						<th><label class="short"><spring:message code="taskPlanForm.constructScale"/></label></th>
						<td><form:input path="constructScale" id="constructScale" maxlength="100"/></td>
					</tr>
					
					<tr>
						<th><label class="short"><spring:message code="taskPlanForm.moneySrc"/></label></th>
						<td><html:select id="moneySrc" name="moneySrc" code="moneyResource" selectedValue="${taskPlan.moneySrc}">
						</html:select></td>
						
						<th><label class="short"><spring:message code="taskPlanForm.totalAmount"/>(元)</label></th>
						<td><form:input path="totalAmount" id="totalAmount" cssClass="money" maxlength="15"/></td>
					</tr>
					
					<tr>
						<th><label class="short"><spring:message code="taskPlanForm.prjAddress"/></label></th>
						<td><form:input path="prjAddress" id="prjAddress" maxlength="100"/></td>
					</tr>
					
					<tr>
						<th><label class="short" for="applyDate"><spring:message code="taskPlanForm.applyDate"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td><form:input path="applyDate" cssClass="required" readonly="readonly" /></td>

						<th><label class="short" for="finishDate"><spring:message code="taskPlanForm.finishDate"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td><form:input path="finishDate" cssClass="required" readonly="readonly" /></td>
				    </tr>
				  
					<tr>
						<th><label class="short" for="taskAgent">招标中心
					        &nbsp;<span class="eleRequired">*</span></label></th>
						<td><form:input path="taskAgentName" cssClass="required" />
						    <form:hidden path="taskAgent.objId"/>
						</td>
					</tr>
				
					<tr>
						<th><label class="short" for="budgetLinker"><spring:message code="taskPlanForm.budgetLinker"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td><form:input path="budgetLinker" cssClass="required"/></td>

						<th><label class="short" for="budgetLinkerTel"><spring:message code="taskPlanForm.budgetLinkerTel"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td><form:input path="budgetLinkerTel" cssClass="required cnPhone"/></td>
			        </tr>
			</table>
		<div class="conOperationBtnDiv">
			<button id="taskPlanSave" class="subBtn" style="<c:if test='${taskPlan.objId != null && taskPlan.objId != "" }'>display:none</c:if>" type="button" tabindex="18"><span>下一步</span></button>
			<button id="taskPlanSubmit" class="subBtn" type="button" tabindex="19"><span>提交立项</span></button>
			<c:if test="${taskPlan.useStatus == '01' && (taskPlan.auditDetail == '04' || taskPlan.confirmStatus == '04')}">
				<button id="historyId" class="subBtn" type="button" tabindex="20"><span>操作历史</span></button>
			</c:if>
			<button name="return_to_list" class="subBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>   
	<input value="${moneySub }" id="moneySub" type="hidden"/>
    <input value="${moneyDetail }" id="moneyDetail" type="hidden"/>

</div>
<div id="epsTabs1">
	<ul>
		<li>
	      <a href="#taskPlanSubListView" id = "tabs_subs" class="refreshData"><span><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>明细<span class="eleRequired">*</span></span></a>
	    </li>
    </ul>
    <div id="taskPlanSubListView"></div>
</div>
<!-- 
<c:if test="${fn:length(sysConfigItemListString)!=0}">
	<div id="epsTabs2">
		<h5 align="center"><span>附件信息</span></h5>
		<ul>
			<c:forEach items="${sysConfigItemListString}" var="sysConfigItem">
				<li>
			      <a href="#TPFFSUploadAttachment" onclick="javascript:taskPlanForm.loadAttachment('${sysConfigItem.objId}');" class="refreshData"><span>${sysConfigItem.name}</span></a>
			    </li>
		    </c:forEach>
	    </ul>
	    <div id="TPFFSUploadAttachment"></div>
	</div>
</c:if>
 -->

<div id="historyView"></div>
