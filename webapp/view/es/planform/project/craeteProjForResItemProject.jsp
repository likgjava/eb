<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/craeteProjForResItemProject.js"></script> 
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="formLayout form2Pa">
	<form id="craeteProjForTaskPlanSubForm" name="projectForm" method="post" >
	<input type="hidden" id="tenderType"  name="tenderType" class="required"/>
	<input type="hidden" id="resProjecItemId" name="resProjecItemId" value="${resProjectItem.objId }"/>
	<input type="hidden" id="resProjectId" name="resProjectId" value="${resProjectItem.resProject.objId }"/>
	<h5 align="center"><span>录入分标段信息</span></h5>
	<ul>
		<li>
			<label class="short"  for="projectNo">编号：</label>
			<input type="text" id="projCode"  name="projCode" class="required" value="${projCode }"/>
			<span>*</span>
		</li>
		<li>
			<label class="short"  for="projName">标段名称：</label>
			<input type="text" id="projName"  name="projName" class="required"/>
			<span>*</span>
		</li>
		<li>
			<label class="short"  for="projName">招标方式：</label>
			公开招标
			<input type="hidden" id="ebuyMethod"  name="ebuyMethod" class="required" value="00"/>
			<span>*</span>
		</li>
		<li>
			<label class="short"  for="content">监管经办人：</label>
				<input type="text" name="" id="">
				<input type="hidden" id="monitor.objId"  name="monitor.objId" class="required"/>
			<span>*</span>
		</li>
		<li class="formTextarea" >
			<label  for="content">备注：</label>
			<textarea name="content"  id="content" maxlength="200" class="maxInput"></textarea>
			
			<span>*</span>
		</li>
	</ul>
	</form>
</div>
		<div class="conOperationBtnDiv">
			<button id="projectSaveButton" class="subBtn" type="button" tabindex="19"><span>保存</span></button>
		</div>
		
		<table class="tableList" >
		<tfoot>
		<tr>
		<th>项目编号</th>
		<th>项目名称</th>
		<th>采购方式</th>
		<th>项目类型</th>
		</tr>
		</tfoot>
		<tbody>
		<c:forEach items="${projectItemTendList}" var="projectItemTend">
		<tr>
		<td>${projectItemTend.project.projCode }</td>
		<td>${projectItemTend.project.projName }</td>
		<td>${projectItemTend.project.ebuyMethodCN }</td>
		<td>${projectItemTend.project.tenderTypeCN }</td>
		</tr>
		</c:forEach>
		</tbody>
		<tfoot></tfoot>
		</table>
