<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/branch_assign.js"></script>
<div class="formLayout ">
	<span style="margin-left:5px">当前位置：任务立项 >> 分配执行部门</span>
    <div class="partContainers operationLog"><h5 id="taskbook"><span class="switch  left11">项目任务书</span></h5></div>
    <div id="taskInfoDiv"></div>

	<div class="partContainers operationLog"><h5 id="taskDetail"><span class="switch left11">任务书明细</span></h5></div>
	<div id="taskDetailList"></div>
	
	<div class="partContainers operationLog"><h5 id="branchAssign"><span class="switch left11">分配执行部门</span></h5></div>
	<div id="branchAssignDetail"></div>
</div>
<div class="functionBtnDiv" style="padding-top:5px;padding-bottom:5px">
	<button type="button" id="pre"><span>上一步</span></button><button type="button" id="next"><span>下一步</span></button>
</div>
 <div id="historyDiv"></div>
