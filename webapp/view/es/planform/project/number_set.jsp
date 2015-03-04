<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/number_set.js"></script>
<div class="formLayout">
	<span style="margin-left:5px">当前位置：任务立项 >> 设置招标编号</span>
	<div class="partContainers operationLog"><h5 id="taskbook"><span class="switch left11">项目任务书</span></h5></div>
	<div id="taskInfoDiv"></div>
	<div class="partContainers operationLog"><h5 id="numberset"><span class="switch left11">设置招标编号</span></h5></div>
	<div id="taskDetailList"></div>
</div>
<div id="historyDiv"></div>