<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/signuprecord/signUprecordDetail.js"></script>

<div class="formLayout form2Pa">  
	<form id="signUprecordDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<h4><span>投标单位报名记录</span></h4>
		<ul>
			<li>
				<label for="signLinker"><spring:message code="signUprecordForm.signLinker"/></label>
				<span id="signLinker"></span>
			</li>
			
			<li>
				<label for="signIdcard"><spring:message code="signUprecordForm.signIdcard"/></label>
				<span id="signIdcard"></span>
			</li>
			
			<li>
				<label for="linkerTel"><spring:message code="signUprecordForm.linkerTel"/></label>
				<span id="linkerTel"></span>
			</li>
			
			<li>
				<label for="signAddress"><spring:message code="signUprecordForm.signAddress"/></label>
				<span id="signAddress"></span>
			</li>
			<li>
				<label for="signType"><spring:message code="signUprecordForm.signType"/></label>
				<span id="signType"></span>
			</li>
			<li>
				<label for="signStatus"><spring:message code="signUprecordForm.signStatus"/></label>
				<span id="signStatus"></span>
			</li>
		</ul>
<!--	    <div class="conOperation">-->
<!--	        <button class="btn primary" id="signUprecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>-->
<!--	    </div>-->
	</form>
</div>
