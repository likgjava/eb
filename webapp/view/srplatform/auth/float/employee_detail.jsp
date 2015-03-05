<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/float/employee_detail.js"></script>

<!--原型页面开始。关于单列与双列的选择：如果显示的数据有可能会比较长时使用，则用如下默认的样式，如果能肯定显示的数据不会太长，在formLayout的后面加上form2Pa；，注意前后需空格隔开-->
<form id="employeeDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	<div class="formLayout  detail" >
		<ul>
			<li><label ><spring:message code="employeeForm.number"/>:</label><span id="number"></span></li>
			<li><label ><spring:message code="employeeForm.company"/>:</label><span id="company.name"></span></li>
			<li><label ><spring:message code="employeeForm.department"/>:</label><span id="department.name"></span></li>
			<li><label ><spring:message code="employeeForm.name"/>:</label><span id="name"></span></li>
			<li><label ><spring:message code="employeeForm.sex"/>:</label><span id="sex_value"></span></li>
			<li><label ><spring:message code="employeeForm.idCard"/>:</label><span id="idCard"></span></li>
			<li><label ><spring:message code="employeeForm.mobile"/>:</label><span id="mobile"></span></li>
			<li><label ><spring:message code="employeeForm.email"/>:</label><span id="email"></span></li>
			<li><label ><spring:message code="employeeForm.msn"/>:</label><span id="msn"></span></li>
			<li><label ><spring:message code="employeeForm.qq"/>:</label><span id="qq"></span></li>
			<li><label ><spring:message code="employeeForm.telOffice"/>:</label><span id="telOffice"></span></li>
			<li><label ><spring:message code="employeeForm.telHome"/>:</label><span id="telHome"></span></li>
			<li><label ><spring:message code="employeeForm.address"/>:</label><span id="address"></span></li>
		
		</ul>
	</div>
</form>
