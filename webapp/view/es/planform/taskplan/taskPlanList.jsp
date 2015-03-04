<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanList.js"></script>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<div id="task_plan_list">
<div id="partContainers" class="partContainers" style="width: 100%">
<form id="taskPlanSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li class="fullLine">
					<spring:message code="taskPlanForm.taskCode" />：
		    	<input type="text" name="taskCode" class="short" value="">
				<input type="hidden" name="taskCode_op" value="like">
					<spring:message code="taskPlanForm.taskName" />：
		    	<input type="text" name="taskName" value="">
				<input type="hidden" name="taskName_op" value="like">
					<spring:message code="taskPlanForm.applyDate" />：
		    	<input id="_applyDate" name="applyDate" style="width: 70px;">
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
      <a href="#taskPlanListInfo" id = "tabs_toTemp" class="refreshData"><span>草稿</span></a>
    </li>
    <li>
      <a href="#taskPlanListInfo" id = "tabs_toFormal" class="refreshData"><span>已提交</span></a>
    </li>
   <li>
    <a href="#taskPlanListInfo" id = "tabs_aleradyCreateHistory" class="refreshData"><span>查看历史</span></a>
    </li>
   </ul>
  <input type="hidden" id="taskPlanZh" value="<dm:out value="local__taskPlanManager__taskplan_cn">申报书</dm:out>列表">
  <div id="taskPlanListInfo">
	  <flex:flexgrid checkbox="true" id="taskPlanGrid" url="TaskPlanController.do?method=list" queryColumns="useStatus,auditDetail,confirmStatus,leader.objId,taskAgent.objId"   
			searchZone="taskPlanSearchZone" rp="10"  title="#taskPlanZh" onSuccess="taskPlanList.success"
			onSubmit="taskPlanList.before" minGridHeight="140" height="165">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="190" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="70" align="center"></flex:flexCol>
					<flex:flexCol name="taskAgentName" display="招标中心" sortable="true" width="120" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="70" align="center"></flex:flexCol>
					<flex:flexCol name="confirmStatus" alias="confirmStatusCN" display="taskPlanForm.confirmStatus" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="70" align="center"></flex:flexCol>
		<flex:flexBtn name="导入执行计划" bclass="add" onpress="taskPlanList.importTaskPlan"></flex:flexBtn>	
		<flex:flexBtn name="globe.new" bclass="add" onpress="taskPlanList.add"></flex:flexBtn>	
		<flex:flexBtn name="批量删除" bclass="delete" onpress="taskPlanList.remove"></flex:flexBtn>
	</flex:flexgrid>
	<div><span class="info" style="background:url('<%=request.getContextPath()%>/view/resource/skin/skin05/img/icon/info.png') no-repeat scroll left center transparent;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>提示信息：“草稿”－临时保存的申报书，可以修改操作；
		</div>
  </div>
  <!--直接提交审核需要判断对应的申报书明细和资金明细是否已经填写。暂无对应方法，故屏蔽 <f lex:flexBtn name="提交审核" bclass="audit" onpress="taskPlanList.auditAgian"></fle x:flexBtn>	--> 	
</div>
</div>
</div>
<div id="task_plan_form" class="hidden"></div>