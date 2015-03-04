<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/workgMemberForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="workgMemberForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="workGroup.objId" id="workGroup.objId" value="${param.workGroupId}"/>
		<input type="hidden" name="workgMemberId" id="workgMemberId" value="${param.objId}"/>
		<input type="hidden" name="workGroupId" id="workGroupId" value="${param.workGroupId}"/>
		<input type="hidden" id="workGroupType" name="workGroupType"  value="${param.workGroupType}">
		<input type="hidden" id="subProject.objId" name="subProject.objId"  value="${param.subProjectId}">
     		<ul>

					<li >
						<label for="workgmName" class="short"><spring:message code="workgMemberForm.workgmName"/></label>
						<input type="text" name="workgmName" id="workgmName" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li >
						<label for="workgmType" class="short"><spring:message code="workgMemberForm.workgmType"/></label>
						<select name="workgmType" id="workgmType" class="required" style="width: 153px;height: 23px">
						<option value="01">专家</option>
						</select>	 	  
							<span class="eleRequired">*</span>
					</li>
                    
                    <li>
						<label for="workgmIsLeader"  class="short"><spring:message code="workgMemberForm.workgmIsLeader"/></label>
						<select name="workgmIsLeader" id="workgmIsLeader" class="required" style="width: 153px;height: 23px">
						<option value="0">成员</option>
						<option value="1">组长</option>
						</select>	 	  
							<span class="eleRequired">*</span>
					</li>
                    
					<li id="workgmDutyli" >
						<label for="workgmDuty" class="short"><spring:message code="workgMemberForm.workgmDuty"/></label>
							<input type="text" name="workgmDuty" id="workgmDuty" 
									class="required"
							  />  
							<span class="eleRequired">*</span>
					</li >

					<li id="workgmSpecialityli" >
						<label for="workgmSpeciality" class="short"><spring:message code="workgMemberForm.workgmSpeciality"/></label>
						<input type="text" name="workgmSpeciality" id="workgmSpeciality" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>
						<li id="linkerPhone">
						<label for="linkerPhone" class="short">联系电话</label>
						<input type="text" name="linkerPhone" id="linkerPhone" 
									class="required cnMobile"
							  />
							<span class="eleRequired">*</span>
					</li >
		</ul>
		<div class="conOperation">
			<button id="workgMemberSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="workgMemberReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>