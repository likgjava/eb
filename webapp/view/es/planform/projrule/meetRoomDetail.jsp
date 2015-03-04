<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/meetRoomDetail.js"></script>

<div class="formZone">  
	<form id="meetRoomDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">标评室信息      <spring:message code="globe.input.required.prompt"/></div>
		<div class="formFieldset">
			<div class="formRow">
				<div class="formLabel"><spring:message code="meetRoomForm.meetRoomName"/></div>
				<div class="formField">
					<div><span id="meetRoomName"></span></div>
				</div>
			</div>
			<div class="formRow">
				<div class="formLabel"><spring:message code="meetRoomForm.meetRoomAddress"/></div>
				<div class="formField">
					<div><span id="meetRoomAddress"></span></div>
				</div>
			</div>
		</div>
	    <div class="btnArea">
	        <button class="btn primary" id="meetRoomReturn" type="button" tabindex="19"><span><span><spring:message code="globe.return"/></span></span></button>
	    </div>
	</form>
</div>
