<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanFormForSum.js"></script>
<input type="hidden" id="paramType" value="${param.paramType}"></input>
<div class="partContainers">
<div class="formLayout form2Pa">  
	<form:form id="taskPlanFormForSum" method="post" modelAttribute="taskPlan">      
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditDetail"></form:hidden>
		<form:hidden path="confirmStatus"></form:hidden>
		<input  type="hidden" id="department.objId" name="department.objId" value="${taskPlan.department.objId}">
		<input  type="hidden" id="departmentName" name="departmentName" value="${taskPlan.departmentName}">
		<input  type="hidden" id="departmentLinkerTel" name="departmentLinkerTel" value="${taskPlan.departmentLinkerTel}">
		<input  type="hidden" id="departmentLinker" name="departmentLinker" value="${taskPlan.departmentLinker}">
		<input  type="hidden" id="budgetLinkerTel" name="budgetLinkerTel" value="${taskPlan.budgetLinkerTel}">
		<input type="hidden" id="taskTypes" name="taskTypes"  value="${taskPlan.taskType}">
		<h5><span><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>信息</span></h5>
     		<ul>
					<li>
						<label class="short"for="taskCode"><spring:message code="taskPlanForm.taskCode"/>：</label>
						<input type="text" name="taskCode" value="${taskPlan.taskCode}" readonly="readonly" style="border: 0">
					</li>
					
					<li>
						<label class="short"for="taskCode"><spring:message code="taskPlanForm.taskName"/>：</label>
						<form:input path="taskName" cssClass="required"/>
						<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label class="short"for="taskCode"><spring:message code="taskPlanForm.ebuyMethod"/>：</label>
						<html:select styleClass="required" id="ebuyMethod" name="ebuyMethod" code="ebuyMethod" selectedValue="${taskPlan.ebuyMethod}">
						</html:select>
					</li>
					
					<li><label class="short"for="taskType"><spring:message code="taskPlanForm.taskType"/>：</label>
						<html:select styleClass="required" id="taskType" name="taskType" code="taskplantype" selectedValue="${taskPlan.taskType}">
						</html:select>
					</li>
					
					 <li id="dljg" class="fullLines">
						<label class="short"for="taskAgent"><spring:message code="taskPlanForm.taskAgent"/>：</label>
						<form:input path="taskAgentName" cssClass="required"/>
						<form:hidden path="taskAgent.objId" />
						<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label class="short"for="applyDate"><spring:message code="taskPlanForm.applyDate"/>：</label>
						<form:input path="applyDate" cssClass="required" readonly="readonly"/>
						<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label class="short"for="finishDate"><spring:message code="taskPlanForm.finishDate"/>：</label>
						<form:input path="finishDate" cssClass="required" readonly="readonly"/>
						<span class="eleRequired">*</span>
					</li>
				
					<li>
						<label class="short"for="budgetLinker"><spring:message code="taskPlanForm.budgetLinker"/>：</label>
						<form:input path="budgetLinker" cssClass="required"/>
						<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label class="short"for="budgetLinkerTel"><spring:message code="taskPlanForm.budgetLinkerTel"/>：</label>
						<form:input path="budgetLinkerTel" cssClass="required cnPhone"/>
						<span class="eleRequired">*</span>
					</li>
				
				</ul>
			<h6 align="center"><span>业务处室信息</span></h6>
     			<ul>	
					<li>
						<label class="short"><spring:message code="taskPlanForm.governmentName"/>：</label>
						<form:input path="governmentName" cssClass="required"/>
						<form:hidden path="government.objId"/>
						<span class="eleRequired">*</span>
					</li>
					
					
					<li>
						<label class="short"><spring:message code="taskPlanForm.govLinker"/>：</label>
						<input type="hidden" name="govLinker" id="govLinker" value="${taskPlan.govLinker}"></input>
						<span id="govLinkerSpan">${taskPlan.govLinker}</span>
					</li>
					<li class="fullLine">
						<label class="short"for="govLinkerTel"><spring:message code="taskPlanForm.govLinkerTel"/>：</label>
						<form:input path="govLinkerTel" cssClass="required cnPhone"/>
						<span class="eleRequired">*</span>
					</li>
		</ul>
		<div class="conOperation">
			<button id="taskPlanSave" type="button" tabindex="18" style="<c:if test='${taskPlan.objId != null && taskPlan.objId != "" }'>display:none</c:if>"><span>下一步</span></button>
			<button id="taskPlanSubmit" type="button" tabindex="19"><span>提交立项</span></button>
			<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
			<button id="historyId" name="historyName" style="<c:if test='${taskPlan.objId == null || taskPlan.objId == "" }'>display:none</c:if>" type="button" tabindex="20"><span>操作历史</span></button>
			
		</div>
	</form:form>
	<input value="${moneySub }" id="moneySub" type="hidden"/>
    <input value="${moneyDetail }" id="moneyDetail" type="hidden"/>
</div>
</div>
<div id="epsTabs">
	<ul>
		<li>
	      <a href="#taskPlanSubListView" id = "tabs_subs" class="refreshData"><span>本级申报书明细</span></a>
	    </li>
	    <li>
	      <a href="#taskPlanDetailListView" id = "tabs_details" class="refreshData"><span>本级采购资金明细</span></a>
	    </li>
	    <li>
	      <a href="#taskPlanSubListForSumView" id = "tabs_subs_s" class="refreshData"><span>下级申报书明细</span></a>
	    </li>
	    <li>
	      <a href="#taskPlanDetailListForSumView" id = "tabs_details_s" class="refreshData"><span>下级采购资金明细</span></a>
	    </li>
    </ul>
    <div id="taskPlanSubListView"></div>
    <div id="taskPlanDetailListView"></div>
    <div id="taskPlanSubListForSumView"></div>
    <div id="taskPlanDetailListForSumView"></div>
</div>
<c:if test="${sysConfigItemListStringLength!=0}">
	<div id="epsTabs2">
		<h5 align="center"><span>附件信息</span></h5>
		<ul>
			<c:forEach items="${sysConfigItemListString}" var="sysConfigItem">
				<li>
			      <a href="#TPFFSUploadAttachment" onclick="javascript:taskPlanFormForSum.loadAttachment('${sysConfigItem.objId}');" class="refreshData"><span>${sysConfigItem.name}</span></a>
			    </li>
		    </c:forEach>
	    </ul>
	    <div id="TPFFSUploadAttachment"></div>
	</div>
</c:if>
<div id="historyView"></div>