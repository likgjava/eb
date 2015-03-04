<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/projectScheduleDetail.js"></script>
<div class="formLayout form2Pa">        
	<form id="projectScheduleForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectId" id="projectId" value="${param.projectId}"/>
	
     		<ul>
					<li>
						<label for="signUpSTime"><spring:message code="projectScheduleForm.signUpSTime"/></label>
						<input type="text" name="signUpSTime" id="signUpSTime" 
								readonly="readyonly"    style="border-width:0px;background-color:transparent;"
									class="required"
							  />
							
					</li>

					<li>
						<label for="signUpETime"><spring:message code="projectScheduleForm.signUpETime"/></label>
						<input type="text" name="signUpETime" id="signUpETime" style="border-width:0px;background-color:transparent;"
								readonly="readyonly"
									class="required"
							  />
							
					</li>

					<li>
						<label for="sellBidDocSTime"><spring:message code="projectScheduleForm.sellBidDocSTime"/></label>
						<input type="text" name="sellBidDocSTime" id="sellBidDocSTime" style="border-width:0px;background-color:transparent;"
								readonly="readyonly"
									class="required"
							  />
							
					</li>

					<li>
						<label for="sellBidDocETime"><spring:message code="projectScheduleForm.sellBidDocETime"/></label>
						<input type="text" name="sellBidDocETime" id="sellBidDocETime" style="border-width:0px;background-color:transparent;"
								readonly="readyonly"
									class="required"
							  />
							
					</li>

					<li>
						<label for="tenderStartTime"><spring:message code="projectScheduleForm.tenderStartTime"/></label>
						<input type="text" name="tenderStartTime" id="tenderStartTime"  style="border-width:0px;background-color:transparent;"
								readonly="readyonly"
									class="required"
							  />
						
					</li>

					<li>
						<label for="tenderEndTime"><spring:message code="projectScheduleForm.tenderEndTime"/></label>
						<input type="text" name="tenderEndTime" id="tenderEndTime"  style="border-width:0px;background-color:transparent;"
								readonly="readyonly"
									class="required"
							  />
							
					</li>

					<li>
						<label for="openBidTime"><spring:message code="projectScheduleForm.openBidTime"/></label>
						<input type="text" name="openBidTime" id="openBidTime"  style="border-width:0px;background-color:transparent;"
								readonly="readyonly"
									class="required"
							  />
							
					</li>

		</ul>
		<div class="conOperation">
			<button id="projectScheduleReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>