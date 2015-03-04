<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bidPackageForm.js"></script>
<div class="partContainers">
<div class="formLayout form2Pa">        
	<form id="bidPackageForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="bidId" id="bidId" value="${param.bidId}"/>
		<h5><span><spring:message code="globe.input.required.prompt"/></span></h5>
     		<ul>

					<li>
						<label for="bidPQuoteSum"><spring:message code="bidPackageForm.bidPQuoteSum"/>：</label>
						<input type="text" name="bidPQuoteSum" id="bidPQuoteSum" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="subProjId"><spring:message code="bidPackageForm.subProjId"/>：</label>
						<input type="text" name="subProjId" id="subProjId" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

		</ul>
		<div class="conOperation">
			<button id="bidPackageSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="bidPackageReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>
</div>