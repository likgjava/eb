<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanDetailForSum.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div id="taskPlanDetailForSum_epsTabs">
</div>

<div class="formLayout form2Pa" style="height: 73px;">
<form id="taskPlanFormOpinion" method="post">
	<h5><span>审核意见</span></h5>
	<ul>
		<li style="height:50px;">
			<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 200%;height: 45px;">同意</textarea>
			<span class="eleRequired"></span>
		</li>
	</ul>
</form>
</div>
<div class="conOperation">
	<input type="hidden" id="taskPlanId" name="taskPlanId" value="${taskPlan.objId }"/>
	<button id="submitButton" type="button" tabindex="19"><span>提交审核</span></button>
	<button id="backButton" type="button" tabindex="18"><span>退回</span></button>
	<button id="returnButton" type="button" tabindex="18"><span>返回</span></button>
	<button name="historyBackBtn" type="button" tabindex="17" class="hidden"><span><spring:message code="globe.return"/></span></button>
	<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
</div>
<div id="historyView"></div>