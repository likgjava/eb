<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/updateTaskPlanSubForm.js"></script>
<div class="paper">        
	<form id="taskPlanSubForm" method="post">
		<h5 align="center"><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>明细</h5>
		<input type="hidden" name="objId" id="planSubId" value="<c:out value="${param.taskPlanSubId}"/>"/>
		<input type="hidden" name="price" id="price" value="<c:out value="${param.totalPrice}"/>"/>
    	<table class="tableListP">
			<tr>
				<th class="short" style="width:25%"><label class="short"  for="purchaseName"><spring:message code="taskPlanSubForm.purchase"/>
				    <span class="eleRequired">*</span></label></th>
				<td style="width:30%"><input type="text" name="purchase.name" id="purchase.name" class="required sysicon siSearch" readonly="readonly"/>
				    <input type="hidden" name="purchase.objId" id="purchase.objId" /></td>

				<th class="short" style="width:20%"><label class="short"  for="unit"><spring:message code="taskPlanSubForm.unit"/>
				    <span class="eleRequired">*</span></label></th>
				<td style="width:25%"><input type="text" name="unit" id="unit"  maxlength="15"  class="required" /></td>					
			</tr>
			<tr>
				<th class="short" style="width:25%"><label class="short"  for="quantity"><spring:message code="taskPlanSubForm.quantity"/>
				    <span class="eleRequired">*</span></label></th>
				<td colspan="3"><input type="text" name="quantity" id="quantity" value="0"  size="25"
							class="required digits" maxlength="25"/>
					<span class="eleRequired"></span></td>
			</tr>
			<tr class="fullLine">
				<th class="short" style="width:25%"><label class="short"  for="totalPrice"><spring:message code="taskPlanSubForm.totalPrice"/>（元）
				    <span class="eleRequired">*</span></label></th>
				<td colspan="3"><input type="text" name="totalPrice" id="totalPrice" value="0.0" class="required money" maxlength="25"
					 size="25" onkeyup="taskPlanSubForm.showmoney()" onblur="taskPlanSubForm.valid()"/>
					  <span class="eleRequired" id="view"></span><span id="totalPriceToMoney"></span></td>
			</tr>
		</table>
	</form>	
		<h5 align="center" style="padding-top:12px;">需求明细</h5>
		<input type="hidden" name="objId" id="preqEntryId" value="" />
   		<form id="preqEntryForm" method="post">
   		   <table class="tableListP">
			<tr>
				<th class="short" style="width:25%"><label class="short"  for="spec"><spring:message code="preqEntryForm.spec"/>
				   <span class="eleRequired">*</span></label></th>
				<td style="width:30%"><input type="text" name="spec" id="spec" class="required" maxlength="25"/></td>

				<th style="width:20%"><label class="short"  for="warrentyLen"><spring:message code="preqEntryForm.warrentyLen"/></label></th>
				<td style="width:25%"><input type="text"  name="warrentyLen" id="warrentyLen" maxlength="25" /></td>
			</tr>
			<tr class="formTextarea" style="height:50px;">
				<th class="short" style="width:25%"><label class="short"  for="paymentClause"><spring:message code="preqEntryForm.paymentClause"/></label></th>
				<td colspan="3"><textarea name="paymentClause" style="height:40px;" id="paymentClause" maxlength="200" class="maxInput"></textarea>
				<span class="eleRequired"></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;">
				<th class="short" style="width:25%"><label class="short"  for="deliveryRequire"><spring:message code="preqEntryForm.deliveryRequire"/></label></th>
				<td colspan="3"><textarea name="deliveryRequire"  style="height:40px;" id="deliveryRequire" maxlength="200" class="maxInput"></textarea>
				<span class="eleRequired"></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;">
				<th class="short" style="width:25%"><label class="short"  for="servicePromise"><spring:message code="preqEntryForm.servicePromise"/></label></th>
				<td colspan="3"><textarea name="servicePromise" style="height:40px;" id="servicePromise" maxlength="200" class="maxInput"></textarea>
				<span class="eleRequired"></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;">
				<th class="short" style="width:25%"><label class="short"  for="qualityAssurance"><spring:message code="preqEntryForm.qualityAssurance"/></label></th>
				<td colspan="3"><textarea name="qualityAssurance" style="height:40px;" id="qualityAssurance" maxlength="200" class="maxInput"></textarea>
				<span class="eleRequired"></span></td>
			</tr>
			<tr class="formTextarea"  style="height:50px;" >
				<th class="short" style="width:25%"><label class="short"  for="acceptStandard"><spring:message code="preqEntryForm.acceptStandard"/></label></th>
				<td colspan="3"><textarea name="acceptStandard" style="height:40px;" id="acceptStandard" maxlength="200" class="maxInput"></textarea>
				<span class="eleRequired"></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;" >
				<th class="short" style="width:25%"><label class="short"  for="technical"><spring:message code="preqEntryForm.technical"/></label></th>
				<td colspan="3"><textarea name="technical" style="height:40px;"  id="technical" maxlength="200" class="maxInput"></textarea>
				<span class="eleRequired"></span></td>
			</tr>
			 </table>
			</form>
			<table class="tableListP">
			 <tr class="fullLine">
				<th class="short" style="width:25%"><label class="short"  for="preqEntryAtt"><span>若上述需求内容还需补充，可上传文件</span></label></th>
				<td colspan="3"><div id="attachRelaId" class="uploadFile"></div></td>
			 </tr>
			 </table>
			<div class="conOperationBtnDiv">
				<button id="taskPlanSubSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
				<button id="taskPlanSubClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
			</div>
</div>