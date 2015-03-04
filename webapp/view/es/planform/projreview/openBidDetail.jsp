<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/openBidDetail.js"></script>

<div class="formZone">  
	<form id="openBidDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc"><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out><spring:message code="globe.input.required.prompt"/></div>
		<div class="formFieldset">
			<div class="formRow">
				<div class="formLabel"><spring:message code="openBidForm.projId"/></div>
				<div class="formField">
					<div><span id="projId"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="openBidForm.subProjId"/></div>
				<div class="formField">
					<div><span id="subProjId"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="openBidForm.projName"/></div>
				<div class="formField">
					<div><span id="projName"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="openBidForm.projCode"/></div>
				<div class="formField">
					<div><span id="projCode"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="openBidForm.openBStartTime"/></div>
				<div class="formField">
					<div><span id="openBStartTime"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="openBidForm.openBEndTime"/></div>
				<div class="formField">
					<div><span id="openBEndTime"></span></div>
				</div>
			</div>
		</div>
	    <div class="btnArea">
	        <button class="btn primary" id="openBidReturn" type="button" tabindex="19"><span><span><spring:message code="globe.return"/></span></span></button>
	    </div>
	</form>
</div>
