<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanListForConsign.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<input id="curOrgId_temp" type="hidden" value="${orgInfo.objId }" />
<form id="taskPlanSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
					<spring:message code="taskPlanForm.taskCode" />：
		    	<input class="short" type="text" name="taskCode" value="">
				<input type="hidden" name="taskCode_op" value="like">
					<spring:message code="taskPlanForm.taskName" />：
		    	<input class="short" type="text" name="taskName" value="">
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
  <div id="taskPlanListInfo">
	  <flex:flexgrid checkbox="false"
		id="taskPlanGrid" url="TaskPlanController.do?method=list" queryColumns=""  
			searchZone="taskPlanSearchZone" rp="10"  title="采购申报书"
			onSubmit="taskPlanList.before" onSuccess="taskPlanList.success">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="55" align="center"></flex:flexCol>
					<flex:flexCol name="departmentName" display="taskPlanForm.department" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="53" align="center"></flex:flexCol>
					<flex:flexCol name="finishDate" format="date" display="taskPlanForm.finishDate" sortable="true" width="53" align="center"></flex:flexCol>
	</flex:flexgrid>
	
  </div>
  <!--直接提交审核需要判断对应的申报书明细和资金明细是否已经填写。暂无对应方法，故屏蔽 <f lex:flexBtn name="提交审核" bclass="audit" onpress="taskPlanList.auditAgian"></fle x:flexBtn>	--> 	
</div>