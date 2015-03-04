<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanDetailForm.js"></script>
<div class="paper">
	<form id="taskPlanDetailForm" method="post">
		<input type="hidden" name="objId" id="planDetailId" value="<c:out value="${param.taskPlanDetailId}"/>"/>
		<table class="tableListP">
	     	<tr>
	     		<th><label class="short" for="taskPlanDetailForm.approvalNumber"><spring:message code="taskPlanDetailForm.approvalNumber"/>
	     		    <span class="eleRequired">*</span></label></th>
				<td><input type="text" name="approvalNumber" id="approvalNumber" class="required long" size="35" maxlength="35"/></td>
    	   </tr>			
           <tr>
	     		<th><label class="short" for="taskPlanDetailForm.superiorApp"><spring:message code="taskPlanDetailForm.superiorApp"/>（元）</label></th>
			    <td><input type="text" name="superiorApp" id="superiorApp" class="number" onblur="taskPlanDetailForm.changeQuantity();"  maxlength="12"
					onkeyup="taskPlanDetailForm.showmoney('superiorApp')" /><span></span><span id="superiorApp2"></span></td>   	   			
    	    </tr>
	     	<tr>
	     		<th><label class="short" for="taskPlanDetailForm.localApp"><spring:message code="taskPlanDetailForm.localApp"/>（元）</label></th>
				<td><input type="text" name="localApp" id="localApp" class="number" onblur="taskPlanDetailForm.changeQuantity();" maxlength="12"
						onkeyup="taskPlanDetailForm.showmoney('localApp')" /><span></span><span id="localApp2"></span></td>
    	   	</tr>		
            <tr>
	     		<th><label class="short" for="taskPlanDetailForm.ownerApp"><spring:message code="taskPlanDetailForm.ownerApp"/>（元）</label></th>
				<td><input type="text" name="ownerApp" id="ownerApp" class="number"  onblur="taskPlanDetailForm.changeQuantity();" maxlength="12"
						onkeyup="taskPlanDetailForm.showmoney('ownerApp')" /><span></span><span id="ownerApp2"></span></td>   	   			
    	    </tr>
	     	<tr>
	     		<th><label class="short" for="taskPlanDetailForm.otherApp"><spring:message code="taskPlanDetailForm.otherApp"/>（元）</label></th>
				<td><input type="text" name="otherApp" id="otherApp" class="number"  onblur="taskPlanDetailForm.changeQuantity();" maxlength="12"
						 onkeyup="taskPlanDetailForm.showmoney('otherApp')"/><span></span><span id="otherApp2"></span></td>   	   			
    	    </tr>
    	    <tr>
	     		<th><label class="short" for="taskPlanDetailForm.quantity"><spring:message code="taskPlanDetailForm.quantity"/>（元）</label></th>
				<td><input type="hidden" name="quantity" id="quantity" class="money required" disabled="disabled" />
					<span class="eleRequired" id="quantity_span"></span></td>
    	    </tr>
		</table>
		   <div class="conOperationBtnDiv">
				<button type="button" id="taskPlanDetailSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="taskPlanDetailReturn" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		   </div>
	</form>
</div>
