<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/openBidForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="openBidForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projId" id="projId" value="${project.objId}"/>
		<input type="hidden" name="subProjId" id="subProjId" value="${project.objId}"/>
		<input type="hidden" name="openBid" id="openBid" value="${openBid.objId}"/>
     		<ul>
					<li>
						<label for="openBStartTime"><spring:message code="openBidForm.openBStartTime"/></label>
						<input type="text" name="openBStartTime" id="openBStartTime" class="required sysicon siDate"
								readonly="readonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="openBEndTime"><spring:message code="openBidForm.openBEndTime"/></label>
						<input type="text" name="openBEndTime" id="openBEndTime" class="required sysicon siDate"
								readonly="readonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

		</ul>
		<div class="conOperation">
			<button id="openBidSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		</div>
	</form>
</div>