<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanManageList.js"></script>
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
					项目编号：
		    	<input type="text" name="taskCode" class="short" value="">
				<input type="hidden" name="taskCode_op" value="like">
					项目名称：
		    	<input type="text" name="taskName" value="">
				<input type="hidden" name="taskName_op" value="like">
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
   </ul>
  <input type="hidden" id="taskPlanZh" value="<dm:out value="local__taskPlanManager__taskplan_cn" >项目</dm:out>列表">
  <div id="taskPlanListInfo">
	  <flex:flexgrid checkbox="true"
		id="taskPlanGrid" url="TaskPlanController.do?method=list" queryColumns="useStatus,auditDetail,confirmStatus,leader.objId,taskAgent.objId"   
			searchZone="taskPlanSearchZone" rp="10"  title="#taskPlanZh" onSuccess="taskPlanList.success"
			onSubmit="taskPlanList.before" minGridHeight="140" height="140">
					<flex:flexCol name="taskCode" display="项目编号" sortable="true" width="120" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="项目名称" sortable="true" width="190" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN" display="招标方式" sortable="true" width="70" align="center"></flex:flexCol>
					<flex:flexCol name="taskAgentName" display="招标中心" sortable="true" width="120" align="left"></flex:flexCol>
					<flex:flexCol name="confirmStatus" alias="confirmStatusCN" display="taskPlanForm.confirmStatus" sortable="true" width="120" align="center"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="taskPlanList.add"></flex:flexBtn>	
		<flex:flexBtn name="批量删除" bclass="delete" onpress="taskPlanList.remove"></flex:flexBtn>
	</flex:flexgrid>
	<div>
		<span class="info" style="background:url('<%=request.getContextPath()%>/view/resource/skin/skin05/img/icon/info.png') no-repeat scroll left center transparent;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>提示信息：“草稿”－临时保存的申报书，可以修改操作；
	</div>
  </div>
</div>
</div>
</div>
<div id="task_plan_form" class="hidden"></div>