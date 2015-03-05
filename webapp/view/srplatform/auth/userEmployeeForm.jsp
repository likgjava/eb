<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/userEmployeeForm.js"></script>

  <div class="formLayout form2Pa">
  <form id="employeeForm" method="post">
	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	<input type="hidden" name="userId" id="userId" value="<c:out value="${param.userId}"/>"/>
    <h4><span>员工信息</span></h4>
    
    <ul>
      <li>
        <label for="input01"><spring:message code="employeeForm.number"/>:</label>
        <input type="text" name="number" id="number" class="required"  />
        <em>*</em>
        <span class="eleNote"></span>
      </li>
      
      <li>
        <label for="input02"><spring:message code="userForm.usrPeriodOfValidity"/>:</label>
        <input type="text" name="usrPeriodOfValidity" id="usrPeriodOfValidity" class="required" readonly="readonly"/>
        <em>*</em>
        <span class="eleWarning"></span></li>
      <li>
        <label for="input03">绑定员工:</label>
        <input type="hidden" name="emp.objId" id="emp.objId" value="">
    	<input type="text" name="emp.name" id="emp.name" value="" readonly="readonly" class="sysicon siSearch">
        <span class="eleRight"></span></li>
      <li>
        <label for="input04"><spring:message code="userForm.usrIsAdmin"/>:</label>
        <div class="formField" id="usrIsAdminDiv"></div>
        <em>*</em>
        <span class="eleNote"></span></li>
      <li>
        <label for="input05"><spring:message code="userForm.usrPwdPeriodValidity"/>:</label>
        <input type="text" name="usrPwdPeriodValidity" id="usrPwdPeriodValidity" class="required" readonly="readonly"/>
       	<em>*</em>
        <span class="eleNote"></span></li>
   
     
    </ul>
    <div class="conOperation">
      	<button  id="userSave" type="button" tabindex="17"><span><spring:message code="globe.save"/></span></button>
	 	 <button  id="userReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
    </div>
    </form>
  </div>










<!--<div class="formZone">         
	<form id="employeeForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="userId" id="userId" value="<c:out value="${param.userId}"/>"/>
		<div class="desc">员工      <spring:message code="globe.input.required.prompt"/></div>
            <div class="formFieldset">
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.number"/></div>
        		<div class="formField">
					<input type="text" name="number" id="number" class="required" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.name"/></div>
        		<div class="formField">
					<input type="text" name="name" id="name" class="required" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.department"/></div>
        		<div class="formField">
				<input type="hidden" name="org.objId" id="org.objId" value="">
				<input type="text" name="org.name" id="org.name" value="" readonly="readonly">
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
    	    <div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.email"/></div>
        		<div class="formField">
					<input type="text" name="email" id="email" class="required email" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
    	    <div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.telOffice"/></div>
        		<div class="formField">
					<input type="text" name="telOffice" id="telOffice" class="required cnPhone" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.mobile"/></div>
        		<div class="formField">
					<input type="text" name="mobile" id="mobile" class="cnMobile" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.msn"/></div>
        		<div class="formField">
					<input type="text" name="msn" id="msn" class="email" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.qq"/></div>
        		<div class="formField">
					<input type="text" name="qq" id="qq" class="digits" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.telHome"/></div>
        		<div class="formField">
					<input type="text" name="telHome" id="telHome" class="cnPhone" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="employeeForm.sort"/></div>
        		<div class="formField">
					<input type="text" name="sort" id="sort" class="number" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
		</div>
	    <div class="conOperation">
	        <button  id="employeeSave" type="button" tabindex="19"><span><spring:message code="globe.save"/></span></button>
	        <button  id="employeeReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	        <button  type="reset" id="employeeReset" tabindex="20" ><span><spring:message code="globe.reset"/></span></button>
	    </div>
	</form>
</div>-->