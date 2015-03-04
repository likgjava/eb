<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/signuprecord/agentSignUprecordForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="signUprecordForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}"/>
		<h4><span>投标单位报名</span></h4>
     		<ul>

					<li>
						<label for="signLinker"><spring:message code="signUprecordForm.signLinker"/></label>
						<input type="text" name="signLinker" id="signLinker" class="required" value="${emp.name}"
							  />
						<span class="eleRequired">*</span> 	  
					</li>
					
					<li>
						<label for="signIdcard"><spring:message code="signUprecordForm.signIdcard"/></label>
						<input type="text" name="signIdcard" id="signIdcard" class="required" value="${emp.idCard }"
							  />
						<span class="eleRequired">*</span> 	  
					</li>
					
					<li>
						<label for="linkerTel"><spring:message code="signUprecordForm.linkerTel"/></label>
						<input type="text" name="linkerTel" id="linkerTel" class="required"
							  />
						<span class="eleRequired">*</span> 	  
					</li>

					<li>
						<label for="signAddress"><spring:message code="signUprecordForm.signAddress"/></label>
						<input type="text" name="signAddress" id="signAddress" class="required"
							  />
						<span class="eleRequired">*</span>
					</li>

					<li>
						<label for="signType"><spring:message code="signUprecordForm.signType"/></label>
						<select name="signType" id="signType" class="required">
						<option value="00">招标中心录入</option>
						</select>
						<span class="eleRequired">*</span> 	  
					</li>

		</ul>
		<div class="conOperation">
			<button id="signUprecordSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		</div>
	</form>
</div>