<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/agentassignList1.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<form id="taskPlanSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
				<spring:message code="taskPlanForm.taskCode" />：
		    	<input type="text" name="taskCode" value="">
				<input type="hidden" name="taskCode_op" value="like">
				<spring:message code="taskPlanForm.taskName" />：
		    	<input type="text" name="taskName" value="">
				<input type="hidden" name="taskName_op" value="like">
				<spring:message code="taskPlanForm.applyDate" />：
		    	<input id="applyDate" name="applyDate" style="width: 70px;">
			</li>
			<li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>
<div id="epsTabs">
	<ul>
  	<li>
      <a href="#taskPlanListInfo" id = "tabs_toTemp" class="refreshData"><span>待指定</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_toFormal" class="refreshData"><span>已指定</span></a>
   </li>
   </ul>
  <div id="taskPlanListInfo">
	  <flex:flexgrid checkbox="false"
		id="taskPlanGrid" url="TaskPlanController.do?method=list" queryColumns=""  
			searchZone="taskPlanSearchZone" rp="10"  title="采购申报书列表"
			onSubmit="taskPlanList.before" onSuccess="taskPlanList.success">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="120" align="left"></flex:flexCol>
					<flex:flexCol name="taskType" alias="taskTypeCN" display="taskPlanForm.taskType" sortable="true" width="50" align="center"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="50" align="center"></flex:flexCol>
					<flex:flexCol name="departmentName" display="taskPlanForm.department" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="50" align="left"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="53" align="center"></flex:flexCol>
				</flex:flexgrid>
  </div>
</div>