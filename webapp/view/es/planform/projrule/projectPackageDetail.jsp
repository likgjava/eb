<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/projectPackageDetail.js"></script>

<div class="formZone">  
	<form id="projectPackageDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">项目拆分      <spring:message code="globe.input.required.prompt"/></div>
		<div class="formFieldset">
			<div class="formRow">
				<div class="formLabel"><spring:message code="projectPackageForm.project"/></div>
				<div class="formField">
					<div><span id="project"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="projectPackageForm.packCode"/></div>
				<div class="formField">
					<div><span id="packCode"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="projectPackageForm.packName"/></div>
				<div class="formField">
					<div><span id="packName"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="projectPackageForm.packContent"/></div>
				<div class="formField">
					<div><span id="packContent"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="projectPackageForm.remark"/></div>
				<div class="formField">
					<div><span id="remark"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="projectPackageForm.creator"/></div>
				<div class="formField">
					<div><span id="creator"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="projectPackageForm.createTime"/></div>
				<div class="formField">
					<div><span id="createTime"></span></div>
				</div>
			</div>
		</div>
	    <div class="btnArea">
	        <button class="btn primary" id="projectPackageReturn" type="button" tabindex="19"><span><span><spring:message code="globe.return"/></span></span></button>
	    </div>
	</form>
</div>
