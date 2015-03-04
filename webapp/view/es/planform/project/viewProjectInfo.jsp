<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
.spanFont {
	font: 0.9em/ 0.9em;
	font-size: 12;
	margin-left: 2px;
}
.myFont {
	font: 0.9em/ 0.9em;
	font-size: 12;
	margin-left: 2px;
	text-decoration: underline;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/resource/scripts/jquery/epsStatusLight/epsStatusLight.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/es/planform/project/viewProjectInfo.js"></script>
<input type="hidden" value="${project.objId }" id="projectId"
	name="projectId" />
<input type="hidden" value="${project.objId }" id="proId" name="proId" />
<input type="hidden" value="${planId }" id="planId" name="planId" />
<input type="hidden" value="${project.ebuyMethod }" id="ebuyMethod"
	name="ebuyMethod" />
<input type="hidden" value="${project.ebuyMethodCN }" id="ebuyMethodCN" />
<input type="hidden" value="" id="subProjectId" name="subProjectId" />
<input type="hidden" value="" id="projectTaskId" name="projectTaskId" />
<input type="hidden" value="yes" id="fromProject" name="fromProject" />
<input type="hidden" value="" id="task_id"></input>
<input type="hidden" value="" id="auditTask_Id"></input>
<input type="hidden" value="${project.monitor.objId}" id="monitor_objId"></input>
<input type="hidden" id="taskPlanId_lk" value="">
<input type="hidden" id="projImplStatus" value="${project.projImplStatus}">

<div id="project">
<div class="projectHead">
<div class="projectInfo"><span id="projectTitle">${project.ebuyMethodCN
}[${project.projCode }]${project.projName }</span> <span class="spanFont"
	style="display: none;">负责人：<strong class="spanFont"
	title="${project.agencies.orgName }">${project.manager.name }<c:if
	test="${project.manager  == null ||project.manager.objId == null }">[<a
		href="#"
		onClick="javascript:ProjectInfo.checkProjectMenuForDialog('xmjbr_fp',false);">指定经办人</a>]</c:if>
</strong></span> |<span class="spanFont">项目进度：<strong class="spanFont">
<span class="spaceused" id="spaceused"
	style="position: relative; top: 2px;"
	onclick='javascript:ProjectInfo.showPlan("${project.objId}")'>${process}%</span></strong> </span>
<font id="waitporcPlan" style="color: gray;"> <c:set var="num"
	value="0"></c:set> 
	
<!--  
	<c:forEach items="${waitporcPlanList}"
	var="waitporcPlan">
	<c:if test="${num < 1}">
		<c:if test="${num != 0}">,</c:if>
		<a class="linkButton" id="${waitporcPlan.objId}" href="#"
			onClick="ProjectInfo.selectedProjectPlan('${waitporcPlan.objId}','${waitporcPlan.parent.objId}');">
		<span class="highlight"><em>${waitporcPlan.name}</em></span> </a>
	</c:if>
	<c:set var="num" value="${num+1}"></c:set>
	</c:forEach> 
-->

<c:if test="${fn:length(waitporcPlanList)> 0}">
	<span><a class="myFont" href="#" id="more_worktask"><font color="red" title="点击查看待办任务！"><b>待办任务</b></font></a></span>
</c:if> </font>



 &nbsp;|<a class="sysicon group_edit"
	onclick='javascript:ProjectInfo.answer("${project.objId}")' title="答疑"><span>答疑</span></a>
<a class="sysicon siDownBtn" id="lookPace" title="查看进度"><span
	id="pace" >查看进度</span></a></div>
<div id="projectNav" class="hidden" >
</div>
</div>
<div id="projectContent" class="partContainers">
<div id="projectSub">
<ul>
	<li class="selected"><a href="#">项目主要环节</a>
	<ul id="secondLevelUl">
		<li backUpClass="finish" class="finish"><a href="#"
			id="projectView">立项</a></li>
	</ul>
	</li>
</ul>
</div>

<div id="projectMain">
<div id="projectBtnDiv" class="BtnDiv"></div>
<div id="projectDoDiv"></div>
</div>
<div id="nextplanTaskPage"></div>
</div>
</div>
