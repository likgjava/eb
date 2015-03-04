<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/projectScheduleForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="projectScheduleForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectId" id="projectId" value="${param.projectId}"/>
     		<ul>
					<li>
						<label for="signUpSTime"><spring:message code="projectScheduleForm.signUpSTime"/></label>
						<input type="text" name="signUpSTime" id="signUpSTime" class="required sysicon siDate"
								readonly="readyonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="signUpETime"><spring:message code="projectScheduleForm.signUpETime"/></label>
						<input type="text" name="signUpETime" id="signUpETime" class="required sysicon siDate"
								readonly="readyonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="sellBidDocSTime"><spring:message code="projectScheduleForm.sellBidDocSTime"/></label>
						<input type="text" name="sellBidDocSTime" id="sellBidDocSTime" class="required sysicon siDate"
								readonly="readyonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="sellBidDocETime"><spring:message code="projectScheduleForm.sellBidDocETime"/></label>
						<input type="text" name="sellBidDocETime" id="sellBidDocETime" class="required sysicon siDate"
								readonly="readyonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="tenderStartTime"><spring:message code="projectScheduleForm.tenderStartTime"/></label>
						<input type="text" name="tenderStartTime" id="tenderStartTime" class="required sysicon siDate"
								readonly="readyonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="tenderEndTime"><spring:message code="projectScheduleForm.tenderEndTime"/></label>
						<input type="text" name="tenderEndTime" id="tenderEndTime" class="required sysicon siDate"
								readonly="readyonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="openBidTime"><spring:message code="projectScheduleForm.openBidTime"/></label>
						<input type="text" name="openBidTime" id="openBidTime" class="required sysicon siDate"
								readonly="readyonly"
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

		</ul>
		<div class="conOperation">
			<button id="projectScheduleSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="projectScheduleReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>