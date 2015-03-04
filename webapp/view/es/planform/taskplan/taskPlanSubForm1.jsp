<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanSubForm1.js"></script>
<div class="partContainers">
	<div class="formLayout form2Pa">        
	<form id="preqEntryForm" method="post">
	<h5 align="left">需求明细</h5>
		<input type="hidden" name="objId" id="preqEntryId" value="${param.objId}" />
   		<ul>
			<li>
				<label for="spec"><spring:message code="preqEntryForm.spec"/>：</label>
				<input type="text" name="spec" id="spec" 
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
			<li>
				<label for="warrentyLen"><spring:message code="preqEntryForm.warrentyLen"/></label>
				<input type="text"  name="warrentyLen" id="warrentyLen" 
					  />
			</li>
			<li class="formTextarea" style="height:50px;">
				<label for="paymentClause"><spring:message code="preqEntryForm.paymentClause"/>：</label>
				<textarea name="paymentClause" style="height:40px;" id="paymentClause"></textarea>
			</li>
			<li class="formTextarea" style="height:50px;">
				<label for="deliveryRequire"><spring:message code="preqEntryForm.deliveryRequire"/>：</label>
				<textarea name="deliveryRequire"  style="height:40px;" id="deliveryRequire"></textarea>
			</li>
			<li class="formTextarea" style="height:50px;">
				<label for="servicePromise"><spring:message code="preqEntryForm.servicePromise"/>：</label>
				<textarea name="servicePromise" style="height:40px;" id="servicePromise"></textarea>
			</li>
			<li class="formTextarea" style="height:50px;">
				<label for="qualityAssurance"><spring:message code="preqEntryForm.qualityAssurance"/>：</label>
				<textarea name="qualityAssurance" style="height:40px;" id="qualityAssurance"></textarea>
			</li>
			<li class="formTextarea"  style="height:50px;" >
				<label for="acceptStandard"><spring:message code="preqEntryForm.acceptStandard"/>：</label>
				<textarea name="acceptStandard" style="height:40px;" id="acceptStandard"></textarea>
			</li>
			<li class="formTextarea" style="height:50px;" >
				<label for="technical"><spring:message code="preqEntryForm.technical"/>：</label>
				<textarea name="technical" style="height:40px;"  id="technical"></textarea>
			</li>
			<div class="conOperation">
				<button id="savePreqEntry" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
				<button id="taskPlanSubClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
			</div>
		</ul>
	</form>
	</div>
</div>