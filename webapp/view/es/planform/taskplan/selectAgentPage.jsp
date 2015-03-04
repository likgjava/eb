<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/selectAgentPage.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline;color: blue;}
--> 
</style>
<input type="hidden" id="fromType" value="${param.fromType}">
<input type="hidden" id="_taskPlanId" value="${taskPlan.objId}">
<input type="hidden" id="_agentId" value="${taskPlan.taskAgent.objId}">
<input type="hidden" id="_agentName" value="${taskPlan.taskAgentName}">
<input type="hidden" id="_drawType" value="${taskPlan.drawType}">
<input type="hidden" id="buyerLevel" value="${buyerLevel}">
<input type="hidden" id="selectNum" value="${selectNum}">
<div class="partContainers">
<div class="formLayout form2Pa"> 
<h5><span><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>信息</span></h5> 
	<ul>
		<li>
			<label class="short"  for="taskCode">申报书<spring:message code="taskPlanForm.taskCode"/>：</label>
			<a href="#" class="abtn" onclick="selectAgentPage.showDetail('${taskPlan.objId}')"><span id="taskCode">${taskPlan.taskCode}</span></a>
		</li>
		<li>
			<label class="short"  for="taskName">申报书<spring:message code="taskPlanForm.taskName"/>：</label>
			<a href="#" class="abtn" onclick="selectAgentPage.showDetail('${taskPlan.objId}')"><span id="taskName">${taskPlan.taskName}</span></a>
		</li>
		<li class="fullLine">
			<label class="short"  for="taskName">抽取方式：</label>
			<input type = "radio" name="selectType" value="00" checked="checked">随机抽取&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type = "radio" name="selectType" value="01" >单项选择
		</li>
	</ul>
</div>

<div id="selectAgentPage">



</div>

</div>


