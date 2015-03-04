<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/groupMemberDetail.js"></script>
<div class="formLayout form2Pa">        
	<form id="groupMemberForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectId" id="projectId" value="${param.projectId}"/>
     		<ul>
					<li>
						<label for="name"><spring:message code="groupMemberForm.name"/></label>
						<input type="text" name="name" id="name" 
									class="required" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

					<li>
						<label for="groupType"><spring:message code="groupMemberForm.groupType"/></label>
						<input type="text" name="groupType" id="groupType" 
									class="required" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

					<li>
						<label for="duty"><spring:message code="groupMemberForm.duty"/></label>
						<input type="text" name="duty" id="duty" 
									class="required" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

					<li>
						<label for="orgName"><spring:message code="groupMemberForm.orgName"/></label>
						<input type="text" name="orgName" id="orgName" 
									class="required" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

					<li>
						<label for="dept"><spring:message code="groupMemberForm.dept"/></label>
						<input type="text" name="orgName" id="orgName" 
									class="required" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

					<li>
						<label for="memberTitle"><spring:message code="groupMemberForm.memberTitle"/></label>
						<input type="text" name="memberTitle" id="memberTitle" 
									class="required" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

					<li>
						<label for="tel"><spring:message code="groupMemberForm.tel"/></label>
						<input type="text" name="tel" id="tel" 
									class="required" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

					<li>
						<label for="remark"><spring:message code="groupMemberForm.remark"/></label>
						<input type="text" name="remark" id="remark" 
									class="required" style="border-width:0px;background-color:transparent;"
									readonly="readonly"
							  />
					</li>

		</ul>
		<div class="conOperation">
			<button id="groupMemberReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>
