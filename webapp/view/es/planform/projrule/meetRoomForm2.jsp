<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/meetRoomForm2.js"></script>
<div class="formLayout">        
				
	<form id="meetRoomForm" method="post">
		<input type="hidden" name="objId" id="objId" value="${objId }"/>
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
						<html:select styleClass="required" id="roomType" name="roomType" code="roomtype"  disabled="true">
						</html:select>
					</li>

		</ul>
	</form>
	<div id="facilities2">
		<c:forEach items="${facilitiesList }" var="facilities" varStatus="status">
		<div id="tr${status.count }">
						设施名称:<input type="text" name="facilitiesName" id="facilitiesName${status.count }" class="required"
							  value="${facilities.facilitiesName }" />
						设施类型:<select id="facilitiesType${status.count }" name="facilitiesType" class="required" > 
						<option value="00" <c:if test="${facilities.facilitiesType=='00'}">selected="selected"</c:if>>电脑</option>
						<option value="01" <c:if test="${facilities.facilitiesType=='01'}">selected="selected"</c:if>>摄像头</option>
						</select> 
						<br/>
						设施描述:<input type="text" name="facilitiesMemo" id="facilitiesMemo${status.count }" class="required"
							  value="${facilities.facilitiesMemo }"/>
						使用方式:<select id="facilitiesMethod${status.count }" name="facilitiesMethod" class="required" > 
						<option value="00" <c:if test="${facilities.facilitiesMethod=='00'}">selected="selected"</c:if>>主控</option>
						<option value="01" <c:if test="${facilities.facilitiesMethod=='01'}">selected="selected"</c:if>>辅助</option>
						</select> 
						<br/>
						访问地址:&nbsp;<input type="text" name="facilitiesAddr" id="facilitiesAddr${status.count }" class="required"
							  value="${facilities.facilitiesAddr }"/>
						<a href='#' onclick='deleteAgent(${status.count })'>移除</a>
		</div>
						</c:forEach>
	</div>
	<div class="conOperation">
			<button id="meetRoomSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="meetRoomClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
			<button id="addFacilities2" type="button" tabindex="19"><span>新增设施</span></button>
		</div>
</div>