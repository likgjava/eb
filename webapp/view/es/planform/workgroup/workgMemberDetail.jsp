<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/workgMemberDetail.js"></script>
<div class="formLayout form2Pa">        
	<form id="workgMemberForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="workGroup.objId" id="workGroup.objId" value="${param.workGroupId}"/>
		<input type="hidden" name="workgMemberId" id="workgMemberId" value="${param.objId}"/>
		<input type="hidden" name="workGroupId" id="workGroupId" value="${param.workGroupId}"/>
		<input type="hidden" id="workGroupType" name="workGroupType"  value="${param.workGroupType}">
     		<ul>

					<li>
						<label for="workgmName"><spring:message code="workgMemberForm.workgmName"/></label>
						<span id="workgmName"></span>
					</li>

					<li>
						<label for="workgmType"><spring:message code="workgMemberForm.workgmType"/></label>  
						<span id="workgmType"></span>
					</li>
                          <li>
						<label for="workgmIsLeader"><spring:message code="workgMemberForm.workgmIsLeader"/></label>
						<span id="workgmIsLeader"></span>	   	  
					</li>
					<li>
						<label for="workgmDuty"><spring:message code="workgMemberForm.workgmDuty"/></label>
						<span id="workgmDuty"></span>	   	  
					</li>

					<li>
						<label for="workgmSpeciality"><spring:message code="workgMemberForm.workgmSpeciality"/></label>
						<span id="workgmSpeciality"></span>	
					</li>

					<li>
						<label for="workgmAccount"><spring:message code="workgMemberForm.workgmAccount"/></label>
						<span id="workgmAccount"></span>		
					</li>

					<li>
						<label for="workgmPassWord"><spring:message code="workgMemberForm.workgmPassWord"/></label>
						<span id="workgmPassWord"></span>
					</li>

		</ul>
		<div class="conOperation">	
			<button id="workgMemberReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>