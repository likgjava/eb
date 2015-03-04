<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style  type="text/css">
.spanFont{font: 0.9em/0.9em;font-size: 9;margin-left: 2px;}
/*其它项目*/
.otherTabs{position:relative; margin-top:10px;overflow:hidden;}
.otherTabs .otherPoject{position:absolute; top:0; right:0; }
.otherTabs .otherPoject li{ float:left; margin:0 10px;}
.otherTabs .ui-tabs-nav li{ margin-top:3px;}
.otherTabs .ui-tabs-nav li{ height:22px; line-height:22px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/jquery/epsStatusLight/epsStatusLight.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/projectInfoForTabDetail.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/toolTip.js"></script>
<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
<input type="hidden" value="${planId }" id="planId" name="planId"/>
<input type="hidden" value="${project.ebuyMethod }" id="ebuyMethod" name="ebuyMethod"/>
<input type="hidden" value="${project.ebuyMethodCN }" id="ebuyMethodCN"/>
<input type="hidden" value="" id="subProjectId" name="subProjectId" />
<input type="hidden" value="" id="projectTaskId" name="projectTaskId" />
<input type="hidden" value="yes" id="fromProject" name="fromProject" />
<input type="hidden" value="" id="task_id"></input>
<input type="hidden" value="" id="auditTask_Id"></input>
<input type="hidden" value="${project.monitor.objId}" id="monitor_objId"></input>
<input type="hidden" id="taskPlanId_lk" value="">
<div id="project" class="partContainers">
	<div class="projectHead">
	<div class="partContainers"><span id="projectTitle">当前项目：${project.ebuyMethodCN }[${project.projCode }]${project.projName }</span>
		<span class="spanFont" id="modify_monitor_span1">监管：<strong class="spanFont" id="monitor_name">${project.monitor.name }</strong>	<authz:authorize ifAnyGranted="vindicateProjectMonitor"><a href="#" id="modify_monitor" class="sysicon siEdit " title="修改项目监管人"></a></authz:authorize></span>
		<span id="modify_monitor_span2" class="spanFont">监管：<select id="monitor.objId" name="monitor.objId" style="width: auto;position: relative;top: 6px;"><option value="">选择项目监管人</option></select>[<a href="#" onClick="javascript:saveMonitorId();">保存</a>]</span>
		|<span class="spanFont">负责人：<strong class="spanFont" title="${project.agencies.orgName }">${project.manager.name }<c:if test="${project.manager  == null ||project.manager.objId == null }">[<a href="#" onClick="javascript:ProjectInfo.checkProjectMenuForDialog('xmjbr_fp',false);">指定经办人</a>]</c:if> </strong></span>
		|<span class="spanFont">项目进度：<strong class="spanFont">
		<span class="spaceused" id="spaceused" style="position: relative;top: 2px;" onclick='javascript:ProjectInfo.showPlan("${project.objId}")'>${process}%</span>
		<font id="waitporcPlan" style="color:gray;">
		<c:set var="num" value="0"></c:set>
		<c:forEach items="${waitporcPlanList}" var="waitporcPlan">
		  	<c:if test="${num < 1}">
			  	<c:if test="${num != 0}">,</c:if>
			  	<a class="em highlight" id="${waitporcPlan.objId}" href="#" onClick="ProjectInfo.selectedProjectPlan('${waitporcPlan.objId}','${waitporcPlan.parent.objId}');">
			  		<span class="spanFont">${waitporcPlan.name}</span>
			  	</a>
		  	</c:if>
			<c:set var="num" value="${num+1}"></c:set>
		</c:forEach>
		  	<c:if test="${fn:length(waitporcPlanList)> 1}">
				<span><a class="spanFont" href="#" id="more_worktask">更多...</a></span>
			</c:if>
		  	</font></strong>
		  </span>
		  &nbsp;|<a class="sysicon group_edit" onclick='javascript:ProjectInfo.answer("${project.objId}")' title="答疑"><span>答疑</span></a>
		  <a class="sysicon siDownBtn"   id="lookPace" title="查看进度"><span id="pace">查看进度</span></a>
	</div>
	</div>
	<div id="projectNav"  class="hidden">
	</div>
	<div id="projectContent"  class="partContainers">
		  <div id="projectSub">
		    <ul>
		      <li class="selected"><a href="#">项目工作计划</a>
		        <ul id="secondLevelUl">
		        	<li>
		        		<a href="#" id="projectView">概览</a>
		        	</li>
		        
		        </ul>
		      </li>
		    </ul>
		  </div>
		  
		  <div id="projectMain">
		      <div id="projectBtnDiv" class="BtnDiv">
		       </div>
		       <div id="projectDoDiv">
		       </div>
		  </div>
		  <div id="nextplanTaskPage"></div>
	</div>

</div>