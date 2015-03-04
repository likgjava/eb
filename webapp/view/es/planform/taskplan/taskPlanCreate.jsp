<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanCreate.js"></script>
<input type="hidden" id="paramType" value="${param.paramType}"></input>
<div class="partContainers">
<div class="formLayout formPa">   
    <input type="hidden" id="taskTypes" name="taskTypes"  value="${taskPlan.taskType}">  
	<form:form id="taskPlanForm" method="post" modelAttribute="taskPlan">
		<input type="hidden" name="workFlowTaskId" id="workFlowTaskId" value="${param.workFlowTaskId}"/>
		<form:hidden path="objId"></form:hidden> 
		<form:hidden path="useStatus"></form:hidden>
		<form:hidden path="auditDetail"></form:hidden>
		<form:hidden path="confirmStatus"></form:hidden>
		<input type="hidden" name="taskType" value="01" />
		<h5><span>项目信息</span></h5>
     		<ul>
					<li>
						<label class="short">项目编号：</label>
						${taskPlan.taskCode}
						<input type="hidden" name="taskCode" value="${taskPlan.taskCode}" class="long"/>
					</li>
					
					<li>
						<label class="short">项目名称：</label>
						<input name="taskName" id="taskName" class="required" />
						<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label class="short">项目投资规模：</label>
						<input name="totalScale" id="totalScale" />
					</li>
					
					<li>
						<label class="short">建设规模：</label>
						<input name="constructScale" id="constructScale" />
					</li>
					
					<li>
						<label class="short">资金来源：</label>
						<html:select id="moneySrc" name="moneySrc" code="moneyResource" selectedValue="${taskPlan.moneySrc}">
						</html:select>
					</li>
					
					<li>
						<label class="short">工程地点：</label>
						<input name="prjAddress" id="prjAddress" />
					</li>
					
					<li>
						<label class="short">项目总投资额：</label>
						<input name="totalAmount" id="totalAmount" class="money" />
					</li>
					
					<li>
						<label class="short">招标方式：</label>
						<html:select styleClass="required" id="ebuyMethod" name="ebuyMethod" code="ebuyMethod" selectedValue="${taskPlan.ebuyMethod}">
						</html:select>
					</li>
					
					<li>
						<label class="short">招标中心：</label>
						<input name="taskAgentName" id="taskAgentName" value="${taskPlan.taskAgent.orgName}"/>
						<input name="taskAgent.objId" id="taskAgent.objId" type="hidden" value="${taskPlan.taskAgent.objId}"/>
					</li>
					
					<li>
						<label class="short">招标中心项目负责人：</label>
						<select name="departmentLinker" id="departmentLinker" class="required">
					   		<option value="">--请选择--</option>
					        <c:forEach items="${empList}" var="empe" >	
					        	<option value="${empe.name}">${empe.name}</option>
				        	</c:forEach>
				    	</select>
				    	<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label class="short">联系方式：</label>
						<input type="text" id="departmentLinkerTel" name="departmentLinkerTel"/>
					</li>
					
					<li>
						<label class="short">业主单位：</label>
						<input type="text" id="yzdw_name" name="budgetName" readonly="readonly" class="required"/>
			            <input type="hidden" id="yzdw_id" name="budget.objId"/>
			            <span class="eleRequired">*</span>
					</li>
					<li>
						<label class="short">管理公司：</label>
						<input name="departmentName" value="${taskPlan.departmentName}" id="yzdw_name_parentname" disabled="disabled" />
						<input type="hidden" id="yzdw_id_parentid" name="department.objId" value="${taskPlan.department.objId}"/>
					</li>
					<li>
						<label class="short">业主单位项目负责人：</label>
						<input type="text" id="leaderName" name="leaderName" class="required"/>
			            <input type="hidden" id="leaderId" name="leader.objId"/>
			            <span class="eleRequired">*</span>
					</li>
					
					<li>
						<label class="short">联系方式：</label>
						<input type="text" id="mobile" name="leader.mobile" class="required"/>
			            <span class="eleRequired">*</span>
					</li>
			</ul>
		<div class="conOperation">
			<button id="taskPlanSave" style="<c:if test='${taskPlan.objId != null && taskPlan.objId != "" }'>display:none</c:if>" type="button" tabindex="18"><span>保存</span></button>
			<button id="taskPlanSubmit" type="button" class="hidden" tabindex="19"><span>提交</span></button>
			<button name="return_to_list" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>   
	<input value="${moneySub }" id="moneySub" type="hidden"/>
    <input value="${moneyDetail }" id="moneyDetail" type="hidden"/>
    
    <div class="uploadFile" id="attachId"></div>
</div>
</div>
