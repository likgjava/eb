<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/buyresult/buyWinnerForm.js"></script>
<div class="formLayout">        
	<form id="buyWinnerForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="buyWinnerId" id="buyWinnerId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="buyResult.objId" id="buyResult.objId" value="${param.buyResultId}">
		<input type="hidden" name="buyResultId" id="buyResultId" value="${param.buyResultId}">
     		<ul>

					<li>
						<label for="selllerId"><spring:message code="buyWinnerForm.selllerId"/>：</label>
						<input type="text" name="selllerId" id="selllerId" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="sellerName"><spring:message code="buyWinnerForm.sellerName"/>：</label>
						<input type="text" name="sellerName" id="sellerName" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

		</ul>
		<div class="conOperation">
			<button id="buyWinnerSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="buyWinnerClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</div>
	</form>
</div>