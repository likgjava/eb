<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/craeteProjForTaskPlanSub.js"></script> 
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
			<label class="short"  for="taskType"><spring:message code="taskPlanForm.taskType"/>：</label>
			<input type="hidden" name="tenderType" id="tenderType" value="${taskType}"/>
			${taskTypeCN}
		</li>
	</ul>
	</form>
</div>
<div id="taskplanListView">
	<flex:flexgrid checkbox="true"	id="consignGrid" url="TaskPlanController.do?method=getTaskPlanSubListByNotblockAndPass" queryColumns=""  
		rp="5" title="申报书明细（金额单位：元）"  onSuccess="craeteProjForTaskPlanSub.success"
		onSubmit="craeteProjForTaskPlanSub.before">
				<flex:flexCol name="budgetName" display="taskPlanSubForm.budgetName" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="purchaseName" display="taskPlanSubForm.purchase"  sortable="true" width="130"align="left"></flex:flexCol>
				<flex:flexCol name="totalPrice" display="taskPlanSubForm.totalPrice" sortable="true" width="150"align="right" format="money"></flex:flexCol>
			<flex:flexBtn name="追加申报书明细" bclass="add" onpress="craeteProjForTaskPlanSub.add"></flex:flexBtn>
			<flex:flexBtn name="删除申报书明细" bclass="delete" onpress="craeteProjForTaskPlanSub.remove"></flex:flexBtn>	
	</flex:flexgrid>
</div>
<div class="formLayout form2Pa">
	<div class="conOperation">
		<button id="projectSaveButton" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	</div>
</div>
