<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style  type="text/css">
.spanFont{font: 0.9em/0.9em;font-size: 12;margin-left: 2px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/jquery/epsStatusLight/epsStatusLight.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/putOnProjectRecord.js"></script>
<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
<input type="hidden" value="${project.ebuyMethodCN }" id="ebuyMethodCN"/>
<div id="project" class="partContainers">
	<div id="projectNav"></div>
	<div class="conOperation" style="text-align: left">
        <button class="button" type="button" id="poprPrint" tabindex="18"><span>打印</span></button>
    </div>	
	<div class="formLayout form2Pa">
		<h5><span>项目信息</span></h5>
		<ul>
			<li>
				<label class="short" ><spring:message code="projectForm.projCode"/>：</label>
				${project.projCode}
			</li>
		</ul>
		<ul>
			<li>
				<label class="short" ><spring:message code="projectForm.projName"/>：</label>
				${project.projName}
			</li>
		</ul>
		<ul>
			<li>
				<label class="short" >项目状态：</label>
				${empty project.projProcessStatusCN ? "未知" : project.projProcessStatusCN}
			</li>
		</ul>
	</div>		
	<div id="projectInfoTabs" class="partContainers">
		<ul>
		  	<li><a href="#taskPlanListInfo" class="refreshData"><span>采购信息</span></a></li>
		  	<li><a href="#taskPlanListInfo" class="refreshData"><span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">采购公告</dm:out></span></a></li>
		  	<li><a href="#taskPlanListInfo" class="refreshData"><span>采购文件</span></a></li>
		  	<li><a href="#taskPlanListInfo" class="refreshData"><span>供应商报名情况</span></a></li>
		  	<li><a href="#taskPlanListInfo" class="refreshData"><span>供应商上传文件</span></a></li>
		  	<li><a href="#taskPlanListInfo" class="refreshData"><span>结果公告</span></a></li>
		  	<li><a href="#taskPlanListInfo" class="refreshData"><span>通知书</span></a></li>
		  	<li><a href="#taskPlanListInfo" class="refreshData"><span>质疑信息</span></a></li>
	   </ul>
	   <div class="formLayout" id="taskPlanListInfo">
	   </div>
	</div>
</div>