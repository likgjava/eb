<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/craeteProjForResProject2.js"></script> 
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="formLayout form2Pa">
	<form id="craeteProjForTaskPlanSubForm" name="projectForm" method="post" >
	<input type="hidden" id="resProjectId" name="resProjectId" value="${resProjectId }"/>
	<h5 align="center"><span>录入招标项目信息</span></h5>
	<ul>
		<li>
			<label class="short"  for="projCode">招标编号：</label>
			${projCode}
			<input type="hidden" name="projCode" id="projCode" value="${projCode}"/>
		</li>
		<li>
			<label class="short"  for="projName">招标名称：</label>
			<input type="text" name="projName" id="projName" class="required" value="" />
			<span class="eleRequired">*</span>
		</li>
		<li>
			<label class="short"  for="projName">项目负责人：</label>
			<select id="managerId" name="manager.objId">
				<c:forEach items="${empList}" var="emp_">
					<option value="${emp_.objId}">${emp_.name }</option>
				</c:forEach>
			</select>
			<span class="eleRequired">*</span>
		</li>
		<li>
			<label class="short"  for="projName">招标方式：</label>
			<select name="ebuyMethod" id="ebuyMethod">
				<option value="20">随机选取</option>
				<option value="21">综合评价</option>
			</select>
		</li>
	 
	</ul>
	</form>
</div>
<div id="taskplanListView">
	<flex:flexgrid checkbox="true"	id="consignGrid" url="ResProjectController.do?method=list&auditStatus=01" queryColumns="objId"  
		rp="5" title="项目名称（金额单位：元）"  onSuccess="craeteProjForTaskPlanSub.success"
		onSubmit="craeteProjForTaskPlanSub.before">
				<flex:flexCol name="projectName" display="项目名称" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="projectType" display="项目性质" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="budgetName" display="建设单位名称" sortable="true" width="130"align="right"></flex:flexCol>
				<flex:flexCol name="ebuyMethod" display="招标方式" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="bidInviStyle"  display="招标组织形式" sortable="true" width="100"align="center"></flex:flexCol>
	</flex:flexgrid>
</div>
<div class="formLayout form2Pa">
	<div class="conOperation">
		<button id="projectSaveButton" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	</div>
</div>
