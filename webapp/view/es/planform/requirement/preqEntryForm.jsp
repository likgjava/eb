<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/requirement/preqEntryForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="preqEntryForm" method="post">
		<input type="hidden" name="objId" id="preqEntryId" value="${param.objId}" />
		<input type="hidden" name="buyer.objId" id="buyer.objId" value="" />
     		<ul>
					<li>
						<label for="name"><spring:message code="preqEntryForm.name"/>：</label>
						<input type="text" name="name" id="name" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>
					
					<li>
						<label for="purchaseName"><spring:message code="preqEntryForm.purchase"/>：</label>
						<span id="purchaseName"></span>
					</li>


					<li>
						<label for="spec"><spring:message code="preqEntryForm.spec"/>：</label>
						<input type="text" name="spec" id="spec" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="unit"><spring:message code="preqEntryForm.unit"/>：</label>
						<span id="unit"></span>
					</li>
					
					<li>
						<label for="unitPrice"><spring:message code="preqEntryForm.unitPrice"/>：</label>
						<span id="unitPrice"></span>
					</li>

					<li>
						<label for="quantity"><spring:message code="preqEntryForm.quantity"/>：</label>
						<span id="quantity"></span>
					</li>

					<li>
						<label for="totalPrice"><spring:message code="preqEntryForm.totalPrice"/>：</label>
						<span id="totalPrice"></span>
					</li>
					
					<li>
						<label for="warrentyLen"><spring:message code="preqEntryForm.warrentyLen"/></label>
						<input type="text" name="warrentyLen" id="warrentyLen" 
							  />
					</li>

					<li class="formTextarea">
						<label for="note"><spring:message code="preqEntryForm.note"/>：</label>
						<textarea name="note" id="note"></textarea>
					</li>

					<li class="formTextarea">
						<label for="paymentClause"><spring:message code="preqEntryForm.paymentClause"/>：</label>
						<textarea name="paymentClause" id="paymentClause"></textarea>
					</li>

					<li class="formTextarea">
						<label for="deliveryRequire"><spring:message code="preqEntryForm.deliveryRequire"/>：</label>
						<textarea name="deliveryRequire" id="deliveryRequire"></textarea>
					</li>

					<li class="formTextarea">
						<label for="servicePromise"><spring:message code="preqEntryForm.servicePromise"/>：</label>
						<textarea name="servicePromise" id="servicePromise"></textarea>
					</li>

					<li class="formTextarea">
						<label for="qualityAssurance"><spring:message code="preqEntryForm.qualityAssurance"/>：</label>
						<textarea name="qualityAssurance" id="qualityAssurance"></textarea>
					</li>

					<li class="formTextarea">
						<label for="acceptStandard"><spring:message code="preqEntryForm.acceptStandard"/>：</label>
						<textarea name="acceptStandard" id="acceptStandard"></textarea>
					</li>
					
					<li class="formTextarea">
						<label for="technical"><spring:message code="preqEntryForm.technical"/>：</label>
						<textarea name="technical" id="technical"></textarea>
					</li>
					
					<li class="formTextarea">
						<label for="remark"><spring:message code="preqEntryForm.remark"/>：</label>
						<textarea name="remark" id="remark" ></textarea>
					</li>

		</ul>
		<div class="conOperation">
			<button id="preqEntrySave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="preqEntryClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</div>
	</form>
</div>