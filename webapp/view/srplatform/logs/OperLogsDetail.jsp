<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/logs/OperLogsDetail.js"></script>

<div class="formZone">  
	<form id="operLogsDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">操作日志      <spring:message code="globe.input.required.prompt"/></div>
		<div class="formFieldset">
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.user"/></div>
				<div class="formField">
					<div><span id="user"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.orgName"/></div>
				<div class="formField">
					<div><span id="orgName"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.logUrl"/></div>
				<div class="formField">
					<div><span id="logUrl"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.logResName"/></div>
				<div class="formField">
					<div><span id="logResName"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.loginLogs"/></div>
				<div class="formField">
					<div><span id="loginLogs"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.logUsrIP"/></div>
				<div class="formField">
					<div><span id="logUsrIP"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.logVisitDate"/></div>
				<div class="formField">
					<div><span id="logVisitDate"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.classesName"/></div>
				<div class="formField">
					<div><span id="classesName"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="operLogsForm.methodName"/></div>
				<div class="formField">
					<div><span id="methodName"></span></div>
				</div>
			</div>
		</div>
	    <div class="conOperation">
	        <button  id="operLogsReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	    </div>
	</form>
</div>
