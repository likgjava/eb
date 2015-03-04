<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanFormForAgent.js"></script>
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
						<td><form:input path="totalScale" id="totalScale"/></td>
						
						<th><label class="short"><spring:message code="taskPlanForm.constructScale"/></label></th>
						<td><form:input path="constructScale" id="constructScale"/></td>
					</tr>
					
					<tr>
						<th><label class="short"><spring:message code="taskPlanForm.moneySrc"/></label></th>
						<td><html:select id="moneySrc" name="moneySrc" code="moneyResource" selectedValue="${taskPlan.moneySrc}">
						</html:select></td>
						
						<th><label class="short"><spring:message code="taskPlanForm.totalAmount"/></label></th>
						<td><form:input path="totalAmount" id="totalAmount"/></td>
					</tr>
					
					<tr>
						<th><label class="short"><spring:message code="taskPlanForm.prjAddress"/></label></th>
						<td><form:input path="prjAddress" id="prjAddress"/></td>
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
						<th><label class="short" for="taskAgent"><spring:message code="taskPlanForm.taskAgent"/>
					        &nbsp;<span class="eleRequired">*</span></label></th>
						<td>
						    <input name="taskAgentName" id="taskAgentName" value="${taskPlan.taskAgent.orgName}"/>
							<input name="taskAgent.objId" id="taskAgent.objId" type="hidden" value="${taskPlan.taskAgent.objId}"/>
						</td>
						<th class="short">招标中心项目负责人：</th>
						<td>
							<select name="agentLeader" id="agentLeader" class="required">
						   		<option value="">--请选择--</option>
						        <c:forEach items="${empList}" var="empe" >	
						        	<option value="${empe.name}">${empe.name}</option>
					        	</c:forEach>
					    	</select>
					    	<span class="eleRequired">*</span>
				    	</td>
					</tr>
					
					<tr>
						<th><label class="short" for="departmentLinkerTel"><spring:message code="taskPlanForm.departmentLinkerTel"/>
						&nbsp;<span class="eleRequired">*</span></label></th>
						<td><input type="text" id="departmentLinkerTel" name="departmentLinkerTel"/></td>
					</tr>
					
					<tr>
						<th>
							<label class="short" for="budget"><spring:message code="taskPlanForm.budget"/>
							&nbsp;<span class="eleRequired">*</span></label>
						</th>
						<td>
							<input type="text" id="yzdw_name" name="budgetName" readonly="readonly" class="required"/>
			            	<input type="hidden" id="yzdw_id" name="budget.objId"/>
						</td>
					</tr>
					
					<tr>
						<th><label class="short" for="budgetLinker"><spring:message code="taskPlanForm.budgetLinker"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td><input type="text" id="leaderName" name="leaderName" class="required"/>
			            <input type="hidden" id="leaderId" name="leader.objId"/></td>

						<th><label class="short" for="budgetLinkerTel"><spring:message code="taskPlanForm.budgetLinkerTel"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td><input type="text" id="mobile" name="leader.mobile" class="required cnPhone"/></td>
			        </tr>
			        
			        <tr>
				        <td colspan="4"><h4 align="center"><span>主管单位信息</span></h4></td>
			        </tr>
					<tr>
						<th><label class="short" for="departmentName"><spring:message code="taskPlanForm.department"/></label></th>
						<td>
						  	<form:input path="departmentName" id="yzdw_name_parentname" disabled="disabled" />
							<input type="hidden" id="yzdw_id_parentid" name="department.objId" />
					    </td>
					</tr>
					
			        <tr>
			           <td colspan="4"><h4 align="center"><span>业务处室信息</span></h4></td>
			        </tr>
			        <tr>
						 <th><label class="short" for="government"><spring:message code="taskPlanForm.government"/>
						     &nbsp;<span class="eleRequired">*</span></label></th>
						 <td><form:input path="governmentName"  cssClass="required" readonly="true"/>
							<form:hidden path="government.objId"/>
                         </td>

						 <th><label class="short" for="govLinker"><spring:message code="taskPlanForm.govLinker"/></label></th>
						 <td><form:hidden path="govLinker"/>
							<span id="govLinkerSpan">${taskPlan.govLinker}</span>
						 </td>
	                </tr>
					<tr>
						<th><label class="short" for="govLinkerTel"><spring:message code="taskPlanForm.govLinkerTel"/>
						    &nbsp;<span class="eleRequired">*</span></label></th>
						<td colspan="3"><form:input path="govLinkerTel" cssClass="required cnPhone"/></td>
					</tr>
			</table>
		<div class="conOperationBtnDiv">
			<button id="taskPlanSave" class="subBtn" style="<c:if test='${taskPlan.objId != null && taskPlan.objId != "" }'>display:none</c:if>" type="button" tabindex="18"><span>保存</span></button>
			<button id="taskPlanSubmit" class="subBtn" type="button" tabindex="19"><span>提交立项</span></button>
			<c:if test="${taskPlan.useStatus == '01' && (taskPlan.auditDetail == '04' || taskPlan.confirmStatus == '04')}">
				<button id="historyId" class="subBtn" type="button" tabindex="20"><span>操作历史</span></button>
			</c:if>
			<button name="return_to_list" class="subBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form:form>   
	<input value="${moneySub }" id="moneySub" type="hidden"/>
    <input value="${moneyDetail }" id="moneyDetail" type="hidden"/>

	<!-- 任务书附件 -->
 	<div class="uploadFile" id="attachId"></div>
</div>
<div id="epsTabs1">
	<ul>
		<li>
	      <a href="#taskPlanSubListView" id = "tabs_subs" class="refreshData"><span><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>明细<span class="eleRequired">*</span></span></a>
	    </li>
	    <li>
	      <a href="#taskPlanDetailListView" id = "tabs_details" class="refreshData"><span><dm:out value="local__taskPlanManager__taskPlanDetail_zh">采购资金</dm:out>明细<span class="eleRequired">*</span></span></a>
	    </li>
    </ul>
    <div id="taskPlanSubListView"></div>
    <div id="taskPlanDetailListView"></div>
</div>
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
<div id="historyView"></div>
