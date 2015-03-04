<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/blockTradeListForSum.js"></script>
<form id="taskPlanSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
				<label>
					<spring:message code="taskPlanForm.taskCode" />：
				</label>
		    	<input type="text" name="taskCode" value="">
				<input type="hidden" name="taskCode_op" value="like">
			</li>
			<li>
				<label>
					<spring:message code="taskPlanForm.applyDate" />：
				</label>
		    	<input id="applyDate" name="applyDate">
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
      <a href="#taskPlanListInfo" id = "tabs_toSubmit" class="refreshData"><span>待提交</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_toSure" class="refreshData"><span>待审核</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_toAdjust" class="refreshData"><span>被退回</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_done" class="refreshData"><span>已通过</span></a>
    </li>
  </ul>
  <div id="taskPlanListInfo">
	  <flex:flexgrid checkbox="true"
		id="taskPlanGrid" url="BlockTradeController.do?method=getBlockSubNotSumSubsByOrg" queryColumns=""  
			searchZone="taskPlanSearchZone" rp="10"  title="采购申报书列表"
			onSubmit="taskPlanListForSum.before" onSuccess="taskPlanListForSum.success">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="60" align="center"></flex:flexCol>
					<flex:flexCol name="departmentName" display="taskPlanForm.department" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="finishDate" format="date" display="taskPlanForm.finishDate" sortable="true" width="80" align="center"></flex:flexCol>
		<flex:flexBtn name="globe.detail" bclass="look" onpress="taskPlanListForSum.showDetail"></flex:flexBtn>	
		<flex:flexBtn name="提交审核" bclass="audit" onpress="taskPlanListForSum.auditAgian"></flex:flexBtn>		
	</flex:flexgrid>
  </div>
 
</div>