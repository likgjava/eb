<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/userDetail.js"></script>
<div class="formLayout form2Pa detail">
  <form id="userDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<h4><span>账号信息</span></h4>
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
									<span id="usrIsAdmin"></span>
                      </li>
                      <li><label>状态:</label>
								<span id="usrIsLocked"></span>
                     </li>
       		</ul>
   </form>
  </div>
 		
	 