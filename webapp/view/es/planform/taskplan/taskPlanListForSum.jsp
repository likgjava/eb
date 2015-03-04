<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanListForSum.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div id="allTask">
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
		    	<input name="applyDate" style="width: 70px;">
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
      <a href="#taskPlanListInfo" id = "tabs_toSubmit" class="refreshData"><span>本级待提交</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfoSub" id = "tabs_toSubmit_sub" class="refreshData"><span>下级待提交</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_toSure" class="refreshData"><span>本级待采购办审核</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_toAdjust" class="refreshData"><span>本级被退回</span></a>
    </li>
     <li>
      <a href="#taskPlanListInfo" id = "tabs_forSelectAgent" class="refreshData"><span>抽取招标中心</span></a>
    </li>
     <li>
      <a href="#taskPlanListInfo" id = "tabs_Audit" class="refreshData"><span>待审核抽取招标中心</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_SelectAgentAgain" class="refreshData"><span>重新抽取招标中心</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_aleradySelectAgent" class="refreshData"><span>已审核通过抽取招标中心</span></a>
    </li>
     <li>
    <a href="#taskPlanListInfoSub2" id = "tabs_aleradyCreateSubHistory" class="refreshData"><span>下级历史</span></a>
    </li>
     <li>
    <a href="#taskPlanListInfo" id = "tabs_aleradyCreateHistory" class="refreshData"><span>本级历史</span></a>
    </li>
   <!-- 
   <li>
      <a href="#taskPlanListInfo" id = "tabs_done" class="refreshData"><span>本级已通过</span></a>
    </li>
    -->
   
  </ul>
  <input type="hidden" id="local__taskPlanManager__taskplan_zh" value="<dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>列表">
  <div id="taskPlanListInfo">
	  <flex:flexgrid checkbox="true"
		id="taskPlanGrid" url="TaskPlanController.do?method=list" queryColumns="useStatus,auditDetail,confirmStatus,leader.objId,taskAgent.objId"  
			searchZone="taskPlanSearchZone" rp="10"  title="#local__taskPlanManager__taskplan_zh"
			onSubmit="taskPlanListForSum.before" onSuccess="taskPlanListForSum.success" minGridHeight="255" height="255">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="90" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="50" align="center"></flex:flexCol>
					<flex:flexCol name="taskAgentName" display="taskPlanForm.taskAgent" sortable="true" width="120" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="45"  align="center"></flex:flexCol>
					<flex:flexCol name="confirmStatus" alias="confirmStatusCN" display="taskPlanForm.confirmStatus" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="60" align="center"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="taskPlanListForSum.add"></flex:flexBtn>	
		<flex:flexBtn name="批量删除" bclass="delete" onpress="taskPlanListForSum.remove"></flex:flexBtn>
	</flex:flexgrid>
  <div><span class="info" style="background:url('<%=request.getContextPath()%>/view/resource/skin/skin05/img/icon/info.png') no-repeat scroll left center transparent;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span id="spanHelp">提示信息：“本级待提交”－本级单位临时保存的申报书，可以修改操作；&nbsp;&nbsp;&nbsp;“下级待提交”－下级机构提交的申报书；&nbsp;&nbsp;&nbsp;“本级待采购办审核”－本级单位待采购办审核的申报书；&nbsp;&nbsp;&nbsp;“本级被退回”－本级单位被采购办”，业务处室打回的申报书；&nbsp;&nbsp;&nbsp;“抽取招标中心”－待抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“待审核抽取招标中心”－待审核抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“重新抽取招标中心”－需要重新抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“已抽取招标中心”－已经抽取招标中心的申报书；</span></div>
</div>
  <div id="taskPlanListInfoSub">
	  <flex:flexgrid 
		id="taskPlanSubGrid" checkbox="true" url="TaskPlanController.do?method=getSubNotSumSubsByOrg" queryColumns="useStatus,auditDetail,confirmStatus,leader"  
			searchZone="taskPlanSearchZone" rp="10"  title="下级采购申报书列表"
			onSubmit="taskPlanListForSum.beforeSub" onSuccess="taskPlanListForSum.success1">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="100" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="160" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="70" align="center"></flex:flexCol>
					<flex:flexCol name="budgetName" display="taskPlanForm.budget" sortable="true" width="160" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="70"  align="center"></flex:flexCol>
					<flex:flexCol name="confirmStatus" alias="confirmStatusCN" display="taskPlanForm.confirmStatus" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="70" align="center"></flex:flexCol>
					<flex:flexBtn name="批量审核" bclass="audit" onpress="taskPlanListForSum.batchAudit"></flex:flexBtn>
	  </flex:flexgrid>
  <div><span class="info" style="background:url('<%=request.getContextPath()%>/view/resource/skin/skin05/img/icon/info.png') no-repeat scroll left center transparent;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>提示信息：“本级待提交”－本级单位临时保存的申报书，可以修改操作；&nbsp;&nbsp;&nbsp;“下级待提交”－下级机构提交的申报书；&nbsp;&nbsp;&nbsp;“本级待采购办审核”－本级单位待采购办审核的申报书；&nbsp;&nbsp;&nbsp;“本级被退回”－本级单位被采购办，业务处室打回的申报书；&nbsp;&nbsp;&nbsp;“抽取招标中心”－待抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“待审核抽取招标中心”－待审核抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“重新抽取招标中心”－需要重新抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“已抽取招标中心”－已经抽取招标中心的申报书；
</div>
</div>
<div id="taskPlanListInfoSub2">
<flex:flexgrid 
		id="taskPlanSubGrid2" checkbox="true" url="TaskPlanController.do?method=getSubAllNotSumSubsByOrg" queryColumns="useStatus,auditDetail,confirmStatus,leader"  
			searchZone="taskPlanSearchZone" rp="10"  title="下级采购申报书列表"
			onSubmit="taskPlanListForSum.beforeSub2" onSuccess="taskPlanListForSum.success2">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="100" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="160" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="70" align="center"></flex:flexCol>
					<flex:flexCol name="budgetName" display="taskPlanForm.budget" sortable="true" width="160" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="70"  align="center"></flex:flexCol>
					<flex:flexCol name="confirmStatus" alias="confirmStatusCN" display="taskPlanForm.confirmStatus" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="70" align="center"></flex:flexCol>
	  </flex:flexgrid>
  <div><span class="info" style="background:url('<%=request.getContextPath()%>/view/resource/skin/skin05/img/icon/info.png') no-repeat scroll left center transparent;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>提示信息：“本级待提交”－本级单位临时保存的申报书，可以修改操作；&nbsp;&nbsp;&nbsp;“下级待提交”－下级机构提交的申报书；&nbsp;&nbsp;&nbsp;“本级待采购办审核”－本级单位待采购办审核的申报书；&nbsp;&nbsp;&nbsp;“本级被退回”－本级单位被采购办，业务处室打回的申报书；&nbsp;&nbsp;&nbsp;“抽取招标中心”－待抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“待审核抽取招标中心”－待审核抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“重新抽取招标中心”－需要重新抽取招标中心的申报书；&nbsp;&nbsp;&nbsp;“已抽取招标中心”－已经抽取招标中心的申报书；
</div>
</div>
</div>
</div>
<div id="taskPlanDetailForSum">
</div>