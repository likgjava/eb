<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/logs/LoginLogsDetail.js"></script>

<div class="formZone">  
	<form id="loginLogsDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">登录日志      <spring:message code="globe.input.required.prompt"/></div>
		<div class="formFieldset">
			<div class="formRow">
				<div class="formLabel"><spring:message code="loginLogsForm.user"/></div>
				<div class="formField">
					<div><span id="user"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="loginLogsForm.lgnInTime"/></div>
				<div class="formField">
					<div><span id="lgnInTime"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="loginLogsForm.lgnOutTime"/></div>
				<div class="formField">
					<div><span id="lgnOutTime"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="loginLogsForm.lgnFromIp"/></div>
				<div class="formField">
					<div><span id="lgnFromIp"></span></div>
				</div>
			</div>
		</div>
	    <div class="conOperation">
	        <button  id="loginLogsReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	    </div>
	</form>
</div>
