<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/requirement/preqEntryDetail.js"></script>
<div class="formLayout form2Pa">        
	<form id="preqEntryForm" method="post">
		<input type="hidden" name="objId" id="preqEntryId" value="${param.objId}" />
     		<ul>
					<li>
						<label for="purchaseName"><spring:message code="preqEntryForm.purchase"/>：</label>
						<span id="purchaseName" ></span>
					</li>

					<li>
						<label for="name"><spring:message code="preqEntryForm.name"/>：</label>
						<span id="name" ></span>
					</li>

					<li>
						<label for="spec"><spring:message code="preqEntryForm.spec"/>：</label>
						<span id="spec" ></span>
					</li>

					<li>
						<label for="unit"><spring:message code="preqEntryForm.unit"/>：</label>
						<span id="unit" ></span>
					</li>
					
					<li>
						<label for="unitPrice"><spring:message code="preqEntryForm.unitPrice"/>：</label>
						<span id="unitPrice" ></span>
					</li>

					<li>
						<label for="quantity"><spring:message code="preqEntryForm.quantity"/>：</label>
						<span id="quantity" ></span>
					</li>

					<li>
						<label for="totalPrice"><spring:message code="preqEntryForm.totalPrice"/>：</label>
						<span id="totalPrice" ></span>
					</li>
					
					<li>
						<label for="warrentyLen"><spring:message code="preqEntryForm.warrentyLen"/>：</label>
						<span id="warrentyLen"></span>
					</li>

					<li class="fullLine">
						<label for="note"><spring:message code="preqEntryForm.note"/>：</label>
						<span id="note" ></span>
					</li>

					<li class="fullLine">
						<label for="paymentClause"><spring:message code="preqEntryForm.paymentClause"/>：</label>
						<span id="paymentClause" ></span>
					</li>

					<li class="fullLine">
						<label for="deliveryRequire"><spring:message code="preqEntryForm.deliveryRequire"/>：</label>
						<span id="deliveryRequire" ></span>
					</li>

					<li class="fullLine">
						<label for="servicePromise"><spring:message code="preqEntryForm.servicePromise"/>：</label>
						<span id="servicePromise" ></span>
					</li>

					<li class="fullLine">
						<label for="qualityAssurance"><spring:message code="preqEntryForm.qualityAssurance"/>：</label>
						<span id="qualityAssurance" ></span>
					</li>

					<li class="fullLine">
						<label for="acceptStandard"><spring:message code="preqEntryForm.acceptStandard"/>：</label>
						<span id="acceptStandard" ></span>
					</li>
					
					<li class="fullLine">
						<label for="technical"><spring:message code="preqEntryForm.technical"/>：</label>
						<span id="technical" ></span>
					</li>
					
					<li class="fullLine">
						<label for="remark"><spring:message code="preqEntryForm.remark"/>：</label>
						<span id="remark" ></span>
					</li>

		</ul>
		<div class="conOperation">
			<button id="preqEntryClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</div>
	</form>
</div>
<script>
$(document).ready(function(){
	 if($('#objId').val()!=''){
		$.getJSON($('#initPath').val()+'/PreqEntryController.do?method=createOrUpdate&objId='+$("#preqEntryId").val(),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			json2ObjectSpan('preqEntryForm', json.preqEntry);
    	});
	 }
	
	//关闭
	$('#preqEntryClose').click(function(){
		$('#epsDialogClose').click();
	});
});
</script>