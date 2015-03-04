<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/requirement/purRequirementList.js"></script>
<form id="planTaskZone" >
	<div class="conSearch" id="taskPlanDiv">
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
					<spring:message code="taskPlanForm.taskName" />：
				</label>
		    	<input type="text" name="taskName" value="">
				<input type="hidden" name="taskName_op" value="like">
			</li>
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>
<form id="purRequirementSearchZone" >
	<div class="conSearch" id="purRequirementDiv">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
				<label>
					<spring:message code="purRequirementForm.name" />：
				</label>
		    	<input type="text" name="name" value="">
				<input type="hidden" name="name_op" value="like">
			</li>
			
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>
<div id="epsTabs">
  <ul>
  	<li>
      <a href="#taskPlanView" id = "tabs_toNone" class="refreshData"><span>未编辑需求</span></a>
    </li>
    <li>
      <a href="#purRequirementView" id = "tabs_toDone" class="refreshData"><span>已编辑需求</span></a>
    </li>
  </ul>
  <div id="taskPlanView">
	   <flex:flexgrid checkbox="true"
		id="taskPlanGrid" url="TaskPlanController.do?method=getTaskPlanByRequire" queryColumns=""  
			searchZone="planTaskZone" rp="10"  title="采购申报书列表"
			onSubmit="purRequirementList.beforeT">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="60" align="center"></flex:flexCol>
					<flex:flexCol name="departmentName" display="taskPlanForm.department" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="finishDate" format="date" display="taskPlanForm.finishDate" sortable="true" width="80" align="center"></flex:flexCol>
			<flex:flexBtn name="globe.detail" bclass="look" onpress="purRequirementList.showTaskDetail"></flex:flexBtn>	
			<flex:flexBtn name="补充需求" bclass="add" onpress="purRequirementList.add"></flex:flexBtn>	
		</flex:flexgrid>
  </div>
  <div id="purRequirementView">
	   <flex:flexgrid checkbox="true"
		id="purRequirementGrid" url="PurRequirementController.do?method=list" queryColumns=""  
			searchZone="purRequirementSearchZone" rp="10"  title="采购需求列表"
			onSubmit="purRequirementList.beforeP">
					<flex:flexCol name="name" display="purRequirementForm.name" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="consign.consName" display="委托协议名称" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="consign.consAgentName" display="consignForm.consAgent" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="consign.consTime" format="date" display="consignForm.consTime" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="submitTime" format="date" display="purRequirementForm.submitTime" sortable="true" width="80" align="center"></flex:flexCol>
			<flex:flexBtn name="globe.detail" bclass="look" onpress="purRequirementList.showRequDetail"></flex:flexBtn>	
			<flex:flexBtn name="修改需求" bclass="add" onpress="purRequirementList.update"></flex:flexBtn>	
		</flex:flexgrid>
  </div>
</div>
	
