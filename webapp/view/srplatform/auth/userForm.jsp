<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/userForm.js"></script>

<div class="formLayout form2Pa">
		<form id="userForm" method="post">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<h4><span>账号</span></h4>
			<ul>
			  <li>
			    <label for="usName"><spring:message code="userForm.usName"/>:</label>
			    <input type="text" name="usName" id="usName" class="required userName" />
			    <span class="eleRequired">*</span> 
			  </li>
			   <li>
			    <label for="usrIsAdminDiv"><spring:message code="userForm.usrIsAdmin"/>:</label>
			    <select id="usrIsAdmin" name="usrIsAdmin"></select>
			    <span class="eleRequired">*</span> 
			  </li>
			  <li>
			    <label for="usrPeriodOfValidity"><spring:message code="userForm.usrPeriodOfValidity"/>:</label>
			    <input type="text" name="usrPeriodOfValidity" id="usrPeriodOfValidity"  class="required sysicon siDate" readonly="readonly"/>
			    <span class="eleRequired">*</span> 
			  </li>
			 
			 
			  <li>
			    <label for="usrPwdPeriodValidity"><spring:message code="userForm.usrPwdPeriodValidity"/>:</label>
			    <input type="text" name="usrPwdPeriodValidity" id="usrPwdPeriodValidity"  class="required sysicon siDate" readonly="readonly"/>
			   	<span class="eleRequired">*</span> 
			  </li>
			   <li>
			    <label for="emp.objId">绑定员工:</label>
			    <input type="hidden" name="emp.objId" id="emp.objId" value="">
				<input type="text" name="emp.name" id="emp.name" value="" readonly="readonly" class="sysicon siSearch">
			  </li>
			</ul>
		   <div class="conOperation">
				<button type="button" id="userSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="historyBackBtn"><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
  </div>
	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
   </div>
