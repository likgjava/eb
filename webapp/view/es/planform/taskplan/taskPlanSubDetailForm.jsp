<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanSubDetailForm.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/util/otherUtils.js"></script>
	<div class="paper">        
	<form id="taskPlanSubForm" method="post">
		<h5 align="center"><dm:out value="local__taskPlanManager__taskplan_zh">申报书</dm:out>明细</h5>
		<input type="hidden" name="objId" id="planSubId" value="<c:out value="${param.taskPlanSubId}"/>"/>
    	<table class="tableListP">
			<tr style="width:500px;">
				<th style="width:25%;"><label><spring:message code="taskPlanSubForm.purchase"/></label></th>
				<td style="width:25%;"><span id="purchaseName" ></span></td>

				<th style="width:25%;"><label><spring:message code="taskPlanSubForm.unit"/></label></th>
				<td style="width:25%;"><span id="unit" ></span></td>
			</tr>
			<tr>
				<th style="width:25%;"><label><spring:message code="taskPlanSubForm.quantity"/></label></th>
				<td style="width:25%;"><span id="quantity"></span></td>

				<th style="width:25%;"><label><spring:message code="taskPlanSubForm.totalPrice"/>（万元）</label></th>
				<td style="width:25%;"><span id="totalPrice" ></span></td>
			</tr>
		</table>
	</form>
	
	<h5 align="center" style="padding-top:12px;">需求明细</h5>
	<form id="preqEntryForm" method="post">
   		<table class="tableListP">
			<tr>
				<th style="width:25%;"><label><spring:message code="preqEntryForm.spec"/></label></th>
				<td style="width:25%;"><span id="spec" ></span></td>

				<th style="width:25%;"><label><spring:message code="preqEntryForm.warrentyLen"/></label></th>
				<td style="width:25%;"><span id="warrentyLen" ></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;">
				<th style="width:25%;"><label><spring:message code="preqEntryForm.paymentClause"/></label></th>
				<td colspan="3"><span id="paymentClause" ></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;">
				<th style="width:25%;"><label><spring:message code="preqEntryForm.deliveryRequire"/></label></th>
				<td colspan="3"><span id="deliveryRequire" ></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;">
				<th style="width:25%;"><label><spring:message code="preqEntryForm.servicePromise"/></label></th>
				<td colspan="3"><span id="servicePromise" ></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;">
				<th style="width:25%;"><label><spring:message code="preqEntryForm.qualityAssurance"/></label></th>
				<td colspan="3"><span id="qualityAssurance" ></span></td>
			</tr>
			<tr class="formTextarea"  style="height:50px;" >
				<th style="width:25%;"><label><spring:message code="preqEntryForm.acceptStandard"/></label></th>
				<td colspan="3"><span id="acceptStandard" ></span></td>
			</tr>
			<tr class="formTextarea" style="height:50px;" >
				<th style="width:25%;"><label><spring:message code="preqEntryForm.technical"/></label></th>
				<td colspan="3"><span id="technical" ></span></td>
			</tr>
			<tr class="fullLine">
				<th style="width:25%;"><label><p>补充的需求内容</p></label></th>
				<td colspan="3"><div id="attachRelaId" class="uploadFile"></div></td>
			</tr>
			
		</table>
	</form>
	
	<div class="conOperationBtnDiv">
		<button id="taskPlanSubClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
	</div>
</div>