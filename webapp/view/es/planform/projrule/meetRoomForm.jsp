<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/meetRoomForm.js"></script>
<div class="formLayout">        
	<form id="meetRoomForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     		<ul>
					<li>
						<label for="meetRoomName"><spring:message code="meetRoomForm.meetRoomName"/>：</label>
						<input type="text" name="meetRoomName" id="meetRoomName" class="required"
							  />
					</li>

					<li>
						<label for="meetRoomAddress"><spring:message code="meetRoomForm.meetRoomAddress"/>：</label>
						<input type="text" name="meetRoomAddress" id="meetRoomAddress" class="required"
							  />
					</li>

					<li>
						<label for="roomType"><spring:message code="meetRoomForm.roomType"/>：</label>
						<html:select styleClass="required" id="roomType" name="roomType" code="roomtype">
						</html:select>
					</li>

		</ul>
		<div class="conOperation">
			<button id="meetRoomSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="meetRoomClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</div>
	</form>
</div>