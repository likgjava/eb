<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/jquery/epsStatusLight/epsStatusLight.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project_agent_new.js"></script>
<!-- 隐藏数据 -->
<!-- 项目 全局使用 start-->
<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
<input type="hidden" value="${planId }" id="planId" name="planId"/>
<input type="hidden" value="${project.ebuyMethod }" id="ebuyMethod" name="ebuyMethod"/>
<input type="hidden" value="" id="subProjectId" name="subProjectId" />
<input type="hidden" value="" id="projectTaskId" name="projectTaskId" />
<input type="hidden" value="yes" id="fromProject" name="fromProject" />
<input type="hidden" value="" id="task_id"></input>
<!-- 项目 全局使用 end-->

<div id="project">
	<div id="projectTitle">当前项目：[${project.projCode }]${project.projName }</div>
	<div class="projectInfo">
	  <span>采购方式：<strong>${project.ebuyMethodCN }</strong></span>
	  <span>监管：<strong>${project.monitor.name }</strong><a href="#"><span class="sysicon siEdit "></span></a></span>
	  <span>负责人：<strong>${project.manager.name }<c:if test="${project.manager  == null ||project.manager.objId == null }">[<a href="#"    onClick="javascript:checkProjectMenuForDialog('xmjbr_fp',false);">指定经办人</a>]</c:if> </strong></span>
	  <span>项目进度：<strong><a id="spaceused" onclick='javascript:projectagent.showPlan("${project.objId}")'>${process}%</a> <font style="color:gray;">${taskName}</font></strong></span>
	  <button class="sysicon siApp_side_tree" type="button" onclick='javascript:projectagent.plan("${project.objId}")' title="调整项目计划"><span>调整项目计划</span></button>
	</div>
	<div id="projectContent">
		  <div id="projectSub">
		    <ul>
		      <li class="selected"><a href="#">项目工作计划</a>
		        <ul id="secondLevelUl">
		          
		        </ul>
		      </li>
		    </ul>
		  </div>
		  
		  <div id="projectMain">
		      <div class="BtnDiv">
		       </div>
		  </div>
	</div>
</div>