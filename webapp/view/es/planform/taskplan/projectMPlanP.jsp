<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page import="com.gpcsoft.epp.common.domain.EbuyMethodEnum"%>
<%@ page import="com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/projectMPlanP.js"></script>
<style>
<!--
a.abtn {text-decoration:underline;font-style:inherit;}
-->
</style>
<div class="partContainers" style="width: 100%">
<form id="taskPlanSubForCreateProjZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li class="fullLine">
					采购品目：
		    	<input type="text" name="purchaseName" id="purchaseName" value="" />
				<input type="hidden" name="purCategoryId" id="purCategoryId" value=""/>
			</li>
			<li class="fullLine">
				<label><spring:message code="projectForm.ebuyMethod"/>：</label>
				<html:select styleClass="required" id="ebuyMethod" name="ebuyMethod" code="ebuyMethod" selectedValue="${ebuyMethod}" >
				<html:option value="">—显示全部—</html:option>
				</html:select>
			</li>
			<li class="operationBtnDiv">
				<button type="submit" id="search"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>
<div id="consignListView">
	<flex:flexgrid checkbox="false"	id="taskPlanSubForCreateProjGrid" url="ProjectMTaskPlanController.do?method=getAllTaskPlanSubListForCreatedProj&isComplete=true" queryColumns="objId,taskplanId,taskPlanSubId,projectId"
	minGridHeight="295" height="295"	searchZone="taskPlanSubForCreateProjZone" rp="10" title="查看采购申报书"  onSuccess="projectMPlanP.success"
		 sortname="applyDate" sortorder="asc" onSubmit="projectMPlanP.before">
				<flex:flexCol name="taskcode" display="申报书编号" sortable="true" width="170"align="center" ></flex:flexCol>
				<flex:flexCol name="taskName" display="申报书名称" sortable="true" width="120"align="left" ></flex:flexCol>
				<flex:flexCol name="purchaseName" display="申报书明细品目" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="budgetMoney" display="预算总金额（元）" format="money" sortable="true" width="130"align="right"></flex:flexCol>
				<flex:flexCol name="tproject.projName"   display="招标项目" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="applyDate"  display="申请日期" sortable="true" width="100"align="center" format="date"></flex:flexCol>
	</flex:flexgrid>
</div>
</div>