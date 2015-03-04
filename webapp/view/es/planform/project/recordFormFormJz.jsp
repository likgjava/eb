<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/recordFormFormJz.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="formLayout form2Pa">
<form id="craeteProjForTaskPlanSubForm" name="projectForm" method="post" >
	<input type="hidden" id="taskPlanSubIds" name="taskPlanSubIds" value="${taskPlanSubIds }"/>
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
			<label class="short"  for="projName">采购方式：</label>
			<input type="hidden" name="ebuyMethod" id="ebuyMethod" value="${ebuyMethod}"/>
			${ebuyMethodCN}
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
		<li class="fullLines">
			<label class="short"  for="taskType"><spring:message code="recordFormForm.recFormBuyMethod"/>：</label>
			<input type="hidden" name="tenderType" id="tenderType" value="${taskType}"/>
			${taskTypeCN}
		</li>
	</ul>
	<input type="hidden" name="objId" id="objId" />
</form>
</div>
<div>
<div id="taskplanListView">
	<flex:flexgrid checkbox="false"
		id="recordFormGrid" url="RecordFormController.do?method=list" queryColumns=""  
			searchZone="recordFormSearchZone" rp="10" title="备案书" height="280" 
			onSubmit="recordFormForm.before" onSuccess="recordFormForm.success">
					<flex:flexCol name="recFormBuyMethod" alias="recFormBuyMethodCn"  display="recordFormForm.recFormBuyMethod" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCn" display="recordFormForm.ebuyMethod" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="engineeringName" display="recordFormForm.engineeringName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormScale" display="recordFormForm.recFormScale" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormAddr" display="recordFormForm.recFormAddr" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormStructureType" display="recordFormForm.recFormStructureType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormContent" display="recordFormForm.recFormContent" sortable="true" width="100"align="left"></flex:flexCol>
	</flex:flexgrid> 
</div>
</div>
<div class="formLayout form2Pa">
		   <div class="conOperation">
				<button type="button" id="recordFormSave"><span><spring:message code="globe.save"/></span></button>	
				<button type="button" id="recordFormReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		   </div>
</div>
<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
	  </ul>
</div>