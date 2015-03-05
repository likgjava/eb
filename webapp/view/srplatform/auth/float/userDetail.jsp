<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/float/userDetail.js"></script>
<div class="formLayout  detail">
  <form id="userDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.usName}"/>"/>
			<h5><span>账号信息</span></h5>
			 <ul>
			 		 <li><label><spring:message code="userForm.usName"/>:</label>
									<span id="usName"></span>
                      	</li>
                      	<li>
                      	<label><spring:message code="userForm.usrCreatedDate"/>:</label>
								<span id="usrCreatedDate"></span>
                      </li>
                      
                      <li>
                      	<label><spring:message code="userForm.usrPeriodOfValidity"/>:</label>
								<span id="usrPeriodOfValidity"></span>
                      </li>
                      
                      <li><label><spring:message code="userForm.usrPwdPeriodValidity"/>:</label>
									<span id="usrPwdPeriodValidity"></span>
                      </li>
                      <li><label><spring:message code="userForm.usrIsAdmin"/>:</label>
									<span id="usrIsAdmin_value"></span>
                      </li>
                      <li><label>状态:</label>
								<span id="usrIsLocked_value"></span>
                     </li>
       		</ul>
       		<h5><span>员工详细信息</span></h5>
				<ul>
					<li><label ><spring:message code="employeeForm.name"/>:</label><span id="emp.name"></span></li>
					<li><label ><spring:message code="employeeForm.mobile"/>:</label><span id="emp.mobile"></span></li>
					<li><label ><spring:message code="employeeForm.email"/>:</label><span id="emp.email"></span></li>
					<li><label ><spring:message code="employeeForm.msn"/>:</label><span id="emp.msn"></span></li>
					<li><label ><spring:message code="employeeForm.qq"/>:</label><span id="emp.qq"></span></li>
					<li><label ><spring:message code="employeeForm.telOffice"/>:</label><span id="emp.telOffice"></span></li>
					<li><label ><spring:message code="employeeForm.telHome"/>:</label><span id="emp.telHome"></span></li>
					<li><label ><spring:message code="employeeForm.number"/>:</label><span id="emp.number"></span></li>
					<li><label ><spring:message code="employeeForm.company"/>:</label><span id="emp.company.name"></span></li>
					<li><label ><spring:message code="employeeForm.department"/>:</label><span id="emp.department.name"></span></li>
				
				</ul>
   </form>
  </div>