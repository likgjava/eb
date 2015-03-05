<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/organizationForm.js"></script>

  <div class="formLayout form2Pa">
  <form id="orgnizationForm" method="post">
	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
    <h4><span>员工     <spring:message code="globe.input.required.prompt"/></span></h4>
    
    <ul>
      <li>
        <label for="input01"><spring:message code="employeeForm.name"/>:</label>
        <input type="text" name="name" id="name" class="required"/>
        <span class="eleNote"></span>
      </li>
      
      <li>
        <label for="input02"><spring:message code="employeeForm.mobile"/>:</label>
        <input type="text" name="mobile" id="mobile"/>
        <span class="eleWarning"></span></li>
      <li>
        <label for="input03"><spring:message code="employeeForm.email"/>:</label>
        <input type="text" name="email" id="email"/>
        <span class="eleRight"></span></li>
      <li>
        <label for="input04"><spring:message code="employeeForm.msn"/>:</label>
        <input type="text" name="msn" id="msn"/>
        <span class="eleNote"></span></li>
      <li>
        <label for="input05"><spring:message code="employeeForm.telOffice"/>:</label>
        <input type="text" name="telOffice" id="telOffice" />
        <span class="eleNote"></span>
     </li>
     
     
      <li>
        <label for="input05">直属机构:</label>
        <input type="hidden" name="orgId" id="orgId" class=""/>
        <input type="text" name="orgName" id="orgName" class="required sysicon siSearch"  readonly="READONLY"/>
       	<em>*</em>
        <span class="eleNote"></span>
     </li>
      <li>
        <label for="input05"><spring:message code="employeeForm.telHome"/>:</label>
        <input type="text" name="telHome" id="telHome"/>
       <span class="eleNote"></span>
     </li>
      <li>
        <label for="input05"><spring:message code="employeeForm.number"/>:</label>
        <input type="text" name="number" id="number" />
       	<span class="eleNote"></span>
     </li>
   
   
    <li>
        <label for="input05"><spring:message code="employeeForm.qq"/>:</label>
        <input type="text" name="qq" id="qq"/>
       	<span class="eleNote"></span>
     </li>
   
    <li>
        <label for="input05">账号:</label>
       <input type="text" name="empUsName" id="empUsName"/>
       	<span class="eleNote"></span>
     </li>
   
     
    </ul>
    <div class="conOperation">
      	 <button  id="orgnizationSave" type="button" tabindex="19"><span><span><spring:message code="globe.save"/></span></span></button>
	     <button  id="orgnizationReturn" type="button" tabindex="19"><span><span><spring:message code="globe.return"/></span></span></button>
	     <button  type="reset" id="orgnizationReset" tabindex="20" ><span><span><spring:message code="globe.reset"/></span></span></button>
    </div>
    </form>
  </div>
      
      
      











<div class="formZone">         
	<form id="orgnizationForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="desc">      <spring:message code="globe.input.required.prompt"/></div>
     	<div class="formFieldset">
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="orgnizationForm.name"/><em>*</em></div>
        		<div class="formField">
					<input type="text" name="name" id="name" class="required" 
									class="required"
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="orgnizationForm.shortName"/><em>*</em></div>
        		<div class="formField">
					<input type="text" name="shortName" id="shortName" class="required" 
									class="required"
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
	     	<div class="formRow">
        		<div class="formLabel"><spring:message code="orgnizationForm.isLeaf"/></div>
        		<div class="formField">
					<input type="text" name="isLeaf" id="isLeaf" class="required" 
						      />
				</div>
				<div class="formTips"></div><p class="error"></p>	
    	    </div>
		</div>
		<div class="conOperation">
	    	<button type="button" id="orgnizationSave"><span><spring:message code="globe.save"/></span></button>
	    	<button type="button" id="orgnizationReturn"><span><spring:message code="globe.return"/></span></button>
	    	<button type="reset" id="orgnizationReset"><span><spring:message code="globe.reset"/></span></button>
	    </div>
	</form>
</div>