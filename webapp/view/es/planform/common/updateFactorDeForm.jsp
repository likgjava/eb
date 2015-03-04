<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/common/updateFactorDeForm.js"></script>
<div class="formLayout form2Pa">
	<form id="factorDeForm" method="post">
		<input type="hidden" name="objId" id="objId" value="${factorDe.objId}"/>
		<input type="hidden" name="factortypeDe.objId" id="factortypeDe.objId"/>
     	<ul>
   			<li class="fullLine">
				<label><spring:message code="factorDeForm.factorName"/>：</label>
				<input type="text" name="factorName" id="factorName" value="${factorDe.factorName}" class="required"></input>
				<span class="eleRequired">*</span>
			</li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="factorDeSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="return_btn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>