<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/moreForTaskPlanListForSub.js"></script>
 <style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="contractSearchZone">
	    <ul>
	      <li>
	        <label>编号：</label>
			<input name="taskCode" type="text" >	
			<input type="hidden" name="taskCode_op" value="like">
	      </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      	<input type="hidden" name="useStatus" id="useStatus" value="${useStatus}" />
	      	<input type="hidden" name="confirmStatus" id="confirmStatus" value="${confirmStatus}" />
	      	<input type="hidden" name="auditDetail" id="auditDetail" value="${auditDetail}" />
	      	<input type="hidden" name="leader" id="leader" value="${leader}" />
	      	<input type="hidden" name="governmentId" id="governmentId" value="${governmentId}" />
	      	<input type="hidden" name="isSubmit" id="isSubmit" value="${param.isSubmit}" />
	      </li>
	    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <div id="contractInfo">
    <flex:flexgrid checkbox="false"
		id="TaskPlanGrid" url="TaskPlanController.do?method=getSubNotSumSubsByOrg" queryColumns=""
			searchZone="contractSearchZone" rp="10"  title="采购申报书列表" onSuccess="TaskPlanList.success" 
			onSubmit="TaskPlanList.before">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="60" align="center"></flex:flexCol>
					<flex:flexCol name="departmentName" display="taskPlanForm.department" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="taskAgentName" display="taskPlanForm.taskAgent" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="finishDate" format="date" display="taskPlanForm.finishDate" sortable="true" width="80" align="center"></flex:flexCol>
	</flex:flexgrid>
  </div>
</div>