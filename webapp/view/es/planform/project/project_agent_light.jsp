<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/jquery/epsStatusLight/epsStatusLight.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project_agent_light.js"></script>
<!-- 隐藏数据 -->
<!-- 项目 全局使用 start-->
<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
<input type="hidden" value="${planId }" id="planId" name="planId"/>
<input type="hidden" value="${project.ebuyMethod }" id="ebuyMethod" name="ebuyMethod"/>
<input type="hidden" value="" id="subProjectId" name="subProjectId" />
<input type="hidden" value="" id="projectTaskId" name="projectTaskId" />
<input type="hidden" value="yes" id="fromProject" name="fromProject" />
<!-- 项目 全局使用 end-->
<div id="projectNav"></div>

<div id="projectTop" class="accountInfo">
	<div class="right">
		<p></p>
		<p></p>
	</div>
	<p><span>[${project.projCode }]${project.projName }</span></p>
	<p>
	<b>采购方式：</b>${project.ebuyMethodCN }  |
	<b>监管：</b>${project.monitor.name }  |
	<b>负责人：</b>${project.manager.name }<c:if test="${project.manager  == null ||project.manager.objId == null }">[<a href="#"    onClick="javascript:checkProjectMenuForDialog('xmjbr_fp',false);">指定经办人</a>]</c:if> |
	<b>项目状态：</b>${project.projProcessStatusCN} |
	<b>项目进度：</b><span id="spaceused" onclick='javascript:projectagent.showPlan("${project.objId}")'>${process}%</span> <font style="color:gray;">${taskName}</font>
	<button class="sysicon siApp_side_tree" type="button" onclick='javascript:projectagent.plan("${project.objId}")' title="调整项目计划"><span>调整项目计划</span></button>
	 |
	<c:if test="${taskPlanSub=='01'}">
	<a href="#" onClick="javaScript:$('#subProject').click();"><font color="red"><b><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>未拆分完全</b></font></a>
	</c:if>
	<c:if test="${taskPlanSub=='02'}">
	<a href="#" onClick="javaScript:$('#subProject').click();"><font color="red"><b><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>未拆分</b></font></a>
	</c:if>
	<c:if test="${taskPlanSub=='03'}">
	<a href="#" onClick="javaScript:$('#subProject').click();"><font color="red"><b><dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out>拆分完全</b></font></a>
	</c:if>
</p>
</div>

<div id="contentDiv">
	
</div>

