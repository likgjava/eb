<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/modifyProjForTaskPlanSub.js"></script> 
<div class="formLayout form2Pa">
	<form id="craeteProjForTaskPlanSubForm" name="projectForm" method="post" >
	<input type="hidden" id="taskPlanSubIds" name="taskPlanSubIds" value="${taskPlanSubIds}"/>
	<input type="hidden" id="ebuyMethod" name="ebuyMethod" value="${project.ebuyMethod}"/>
	<input type="hidden" id="tenderType" name="tenderType" value="${project.tenderType}"/>
	<input type="hidden" id="projectId" name="projectId" value="${project.objId}"/>
	<h5 align="center"><span>录入招标项目信息</span></h5>
	<ul>
		<li>
			<label for="projCode">招标编号：</label>
			<span>${project.projCode}</span>
		</li>
		<li>
			<label for="projName">招标名称：</label>
			<input type="text" name="projName" value="${project.projName}" id="projNameId"/>
		</li>
		<li>
			<label for="projName">采购方式：</label>
			<span>${project.ebuyMethodCN}</span>
		</li>
		<li>
			<label for="projName">项目负责人：</label>
			<input type="hidden" name="manager.objId" id="manager.objId" /> 
			<input type="text" name="manager.name" id="manager.name" value="${project.manager.name}" />
			<span></span>
		</li>
	</ul>
	</form>
</div>
<div class="formLayout form2Pa">
	<div class="conOperation">
		<button id="projectSaveButton" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	</div>
</div>
