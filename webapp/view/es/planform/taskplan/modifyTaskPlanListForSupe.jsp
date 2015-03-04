<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/modifyTaskPlanListForSupe.js"></script>
<form id="addTaskPlanSubSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
				<label for="purchaseName"><spring:message code="taskPlanSubForm.purchase"/>：
				</label>
				<input type="text" name="purchaseName" id="purchaseName"  />
				<input type="hidden" name="purchaseName_op" value="like"/>
			</li>
			<li>
				<label>
					<spring:message code="taskPlanSubForm.budgetName" />：
				</label>
		    	<input type="text" name="budgetName" value="">
				<input type="hidden" name="budgetName_op" value="like">
			</li>
			<li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>
<div id="consignListView">
	<flex:flexgrid checkbox="true"	id="addTaskPlanSubGrid" url="TaskPlanController.do?method=getmodifyTaskPlanSubListByConfirmConsign&projectId=${projectId}&taskType=${taskType}&ebuyMethod=${ebuyMethod}&taskPlanSubIds_not=${taskPlanSubIds_not}" queryColumns=""  
		searchZone="addTaskPlanSubSearchZone" rp="${rp}" title="申报书明细"  
		onSubmit="addTaskPlanListForSupe.before">
				<flex:flexCol name="budgetName" display="taskPlanSubForm.budgetName" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="purchaseName" display="taskPlanSubForm.purchase" sortable="true" width="130"align="left"></flex:flexCol>
				<flex:flexCol name="totalPrice" display="taskPlanSubForm.totalPrice" sortable="true" width="150"align="right"></flex:flexCol>
			<flex:flexBtn name="确认" bclass="add" onpress="addTaskPlanListForSupe.add"></flex:flexBtn>	
	</flex:flexgrid>
</div>
