<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/view/es/planform/negotationrecord/lookPreqEntryInfo.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa detail">
<form id="lookTaskPlanForm">
<h5><span>需求明细</span></h5>
<input type="hidden" id="task_sub_objId" value="${param.objId}" />
<ul id="TaskPlanUl">
	<li>
		<label class="short"  for="purchaseName"><spring:message
			code="taskPlanSubForm.purchase" />：</label>
		
		<span id="purchaseName"></span>
	</li>
	<li>	
		<label class="short"  for="unit"><spring:message
			code="taskPlanSubForm.unit" />：</label>
		
		<span id="unit"></span>
		
	</li>
	<li>
		<label class="short"  for="quantity"><spring:message
			code="taskPlanSubForm.quantity" />：</label>
		
		<span id="quantity"></span>
	</li>
	<li>
		<label class="short"  for="totalPrice"><spring:message	code="taskPlanSubForm.totalPrice" />：</label>
		
		<span id="totalPrice"></span>
		
	</li>

	<li>
		<label class="short"  for="spec"><spring:message
			code="preqEntryForm.spec" />：</label>
		
		<span id="spec"></span>
	</li>
	<li>
		<label class="short"  for="warrentyLen"><spring:message
			code="preqEntryForm.warrentyLen" />：</label>
		
		<span id="warrentyLen"></span>
		
	</li>
	
	
	<li class="formTextarea">
		<label class="short"  for="paymentClause"><spring:message
			code="preqEntryForm.paymentClause" />：</label>
		
		<span id="paymentClause"></span>
		
	</li>
	<li class="formTextarea">
		<label class="short"  for="deliveryRequire"><spring:message
			code="preqEntryForm.deliveryRequire" />：</label>
		
		<span id="deliveryRequire"></span>
		
	</li>
	<li class="formTextarea">
		<label class="short"  for="servicePromise"><spring:message
			code="preqEntryForm.servicePromise" />：</label>
		
		<span id="servicePromise"></span>
		
	</li>
	<li class="formTextarea">
		<label class="short"  for="qualityAssurance"><spring:message
			code="preqEntryForm.qualityAssurance" />：</label>
		
		<span id="qualityAssurance"></span>
		
	</li>
	<li class="formTextarea">
		<label class="short"  for="acceptStandard"><spring:message
			code="preqEntryForm.acceptStandard" />：</label>
		
		<span id="acceptStandard"></span>
		
	</li>
	<li class="formTextarea">
		<label class="short"  for="technical"><spring:message
			code="preqEntryForm.technical" />：</label>
		
		<span id="technical"></span>
	</li>
</ul>
</form>
</div>
    <div class="conOperation">
		<button name="closeBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>	
	</div>
</div>