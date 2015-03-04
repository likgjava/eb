<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/groupMemberForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="groupMemberForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectId" id="projectId" value="${param.projectId}"/>
		<h4><span><spring:message code="globe.input.required.prompt"/></span></h4>
     		<ul>
					<li>
						<label for="name"><spring:message code="groupMemberForm.name"/></label>
						<input type="text" name="name" id="name" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="groupType"><spring:message code="groupMemberForm.groupType"/></label>
						<select name="groupType" id="groupType" class="required" style="width: 153px;height: 23px">
						<option value="领导小组">领导小组</option>
						<option value="工作小组">工作小组</option>
					</select>
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="duty"><spring:message code="groupMemberForm.duty"/></label>
						<input type="text" name="duty" id="duty" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="orgName"><spring:message code="groupMemberForm.orgName"/></label>
						<input type="text" name="orgName" id="orgName" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="dept"><spring:message code="groupMemberForm.dept"/></label>
						<input type="text" name="dept" id="dept" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="memberTitle"><spring:message code="groupMemberForm.memberTitle"/></label>
						<select name="memberTitle" id="memberTitle" class="required" style="width: 153px;height: 23px">
						<option value="组长">组长</option>
						<option value="成员">成员</option>
					</select>	  
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="tel"><spring:message code="groupMemberForm.tel"/></label>
						<input type="text" name="tel" id="tel" 
									class="required"
							  />
							<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="remark"><spring:message code="groupMemberForm.remark"/></label>
						<input type="text" name="remark" id="remark" 
							  />
					</li>

		</ul>
		<div class="conOperation">
			<button id="groupMemberSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="groupMemberReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>