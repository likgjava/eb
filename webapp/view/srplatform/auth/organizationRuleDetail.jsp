<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/organizationRuleDetail.js"></script>

<div class="formZone">  
	<form id="orgnizationRuleDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">      <spring:message code="globe.input.required.prompt"/></div>
		<div class="formFieldset">
		</div>
	    <div class="conOperation">
	        <button  id="orgnizationRuleReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	    </div>
	</form>
</div>
