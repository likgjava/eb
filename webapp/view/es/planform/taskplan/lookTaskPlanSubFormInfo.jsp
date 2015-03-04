<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/es/planform/taskplan/lookTaskPlanSubFormInfo.js"></script>
<div class="paper">
<form id="lookTaskPlanForm">
<h5 align="center">需求明细</h5>
<input type="hidden" id="task_sub_objId" value="${param.objId}" />
<table class="tableListP">
	<tr>
		<th><label class="short"  for="purchaseName"><spring:message
			code="taskPlanSubForm.purchase" /></label></th>
		
		<td><span id="purchaseName"></span></td>
	
		<th class="short"><label class="short"  for="unit"><spring:message
			code="taskPlanSubForm.unit" /></label></th>
		
	    <td><span id="unit"></span></td>		
	</tr>
	<tr>
		<th><label class="short"  for="quantity"><spring:message
			code="taskPlanSubForm.quantity" /></label></th>
		
		<td><span id="quantity"></span></td>

		<th class="short"><label class="short"  for="totalPrice"><spring:message code="taskPlanSubForm.totalPrice" />（万元）</label></th>
		
		<td><span id="totalPrice"></span></td>		
	</tr>

	<tr>
		<th><label class="short"  for="spec"><spring:message code="preqEntryForm.spec" /></label></th>
		
		<td><span id="spec"></span></td>

		<th class="short"><label class="short"  for="warrentyLen"><spring:message
			code="preqEntryForm.warrentyLen" /></label></th>
		
		<td><span id="warrentyLen"></span></td>		
	</tr>
	
	
	<tr class="formTextarea" style="height:50px;">
		<th><label class="short"  for="paymentClause"><spring:message
			code="preqEntryForm.paymentClause" /></label></th>
		
		<td colspan="3"><span id="paymentClause"></span></td>
		
	</tr>
	<tr class="formTextarea" style="height:50px;">
		<th><label class="short"  for="deliveryRequire"><spring:message
			code="preqEntryForm.deliveryRequire" /></label></th>
		
		<td colspan="3"><span id="deliveryRequire"></span></td>
		
	</tr>
	<tr class="formTextarea" style="height:50px;">
		<th><label class="short"  for="servicePromise"><spring:message
			code="preqEntryForm.servicePromise" /></label></th>
		
		<td colspan="3"><span id="servicePromise"></span></td>
		
	</tr>
	<tr class="formTextarea" style="height:50px;">
		<th><label class="short"  for="qualityAssurance"><spring:message
			code="preqEntryForm.qualityAssurance" /></label></th>
		
		<td colspan="3"><span id="qualityAssurance"></span></td>
		
	</tr>
	<tr class="formTextarea" style="height:50px;">
		<th><label class="short"  for="acceptStandard"><spring:message
			code="preqEntryForm.acceptStandard" /></label></th>
		
		<td colspan="3"><span id="acceptStandard"></span></td>
		
	</tr>
	<tr class="formTextarea" style="height:50px;">
		<th><label class="short"  for="technical"><spring:message
			code="preqEntryForm.technical" /></label></th>
		
		<td colspan="3"><span id="technical"></span></td>
	</tr>
</table>
</form>
<div class="paper" id="TaskPlanUl"></div>
</div>