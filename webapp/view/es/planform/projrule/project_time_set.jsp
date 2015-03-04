<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/project_time_set.js"></script>
<form id="ProjectScheduleForm" name="ProjectScheduleForm" method="post">
<div class="formLayout">
<span>当前位置：编制文件 >> 项目规则 >> 项目时间设置</span>

<div class="partContainers operationLog"><h5 id="timeSet"><span class="switch  left11">项目时间设置</span></h5></div>
	<div id="projectTimeContent"></div>
</div>
</form>
 <div class="functionBtnDiv" style="text-align:center;">
	    <button type="submit" id="lastStep" style="width:70px"><span>上一步</span></button>
	    <button type="submit" id="nextStep" style="width:70px"><span>下一步</span></button>
 </div>
<div id="historyDiv"></div>