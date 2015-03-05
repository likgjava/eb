<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/employeeForm.js"></script>
  <div class="formLayout ">
  <form id="employeeForm" method="post">
  		<input type="hidden" name="model" id="model" value="<c:out value="${param.model}"/>"/>
		<input type="hidden" name="isForm" id="isForm" value="<c:out value="${param.isForm}"/>"/>
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="isSelf" id="isSelf" value="<c:out value="${param.isSelf}"/>"/>
    <h4><span>员工信息</span></h4>
    <ul>
    	<li>
	        <label class="short"><spring:message code="employeeForm.number"/>:</label>
	        <input type="text" name="number" id="number" />
	     </li>
      <li>
        <label class="short"><spring:message code="employeeForm.name"/>:</label>
        <input type="text" name="name" id="name" class="required"/>
        <span class="eleRequired">*</span> 
      </li>
      <li>
        <label class="short"><spring:message code="employeeForm.sex"/>:</label>
        	<input class="checkboxInput"  type="radio"  name="sex" id="sex_1" value="false"/>女&nbsp;
           	<input class="checkboxInput"  type="radio" name="sex" id="sex_0" value="true"/>男
      </li>
      <li>
        <label class="short"><spring:message code="employeeForm.idCard"/>:</label>
        <input type="text" name="idCard" id="idCard" class="required cnIdCard"/>
        <span class="eleRequired">*</span> 
      </li>
     
      <li>
        <label class="short">直属机构:</label>
        <input type="hidden" name="orgId" id="orgId"/>
        <input type="text" name="orgName" id="orgName" class="required"/>
       	<span class="eleRequired">*</span> 
      </li>
       <li>
        <label class="short"><spring:message code="employeeForm.mobile"/>:</label>
        <input type="text" name="mobile" id="mobile"/>
       </li>
       
       <li>
        <label class="short"><spring:message code="employeeForm.telOffice"/>:</label>
        <input type="text" name="telOffice" id="telOffice" />
      </li>
      <li>
        <label class="short"><spring:message code="employeeForm.telHome"/>:</label>
        <input type="text" name="telHome" id="telHome"/>
      </li>
      <li>
        <label class="short"><spring:message code="employeeForm.address"/>:</label>
        <input type="text" name="address" id="address" class="long"/>
      </li>
      
     <li>
        <label class="short"><spring:message code="employeeForm.msn"/>:</label>
        <input type="text" name="msn" id="msn"/>
       </li>
     <li>
        <label class="short"><spring:message code="employeeForm.qq"/>:</label>
        <input type="text" name="qq" id="qq"/>
     </li>
     <li id="emailLi" >
     	<label class="short"><spring:message code="employeeForm.email"/>:</label>
	    <input type="text" name="email" id="email" class="required email" />
	    <span class="eleRequired">*</span> 
     </li>
     <li id="accountLi" class="eleHide">
        <label class="short">账号:</label>
        <input type="text" name="empUsName" id="empUsName" class="accout"/>
        <span class="eleRequired">*</span> 
     </li>
   </ul>
    <div class="conOperation">
    	<c:if test="${param.isSelf==null&&param.objId==null}">
      		<button  id="openAccount" type="button" ><span>开通账号</span></button>
      	</c:if>
      	<button  id="employeeSave" type="button" ><span><spring:message code="globe.save"/></span></button>
      	<c:if test="${param.isSelf==null}">
      		<button  id="employeeReturn" type="button" ><span>返回</span></button>
      	</c:if>
    </div>
    </form>
  </div>
  <div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
  </div>

      
      
      
      
      
      
      
      
      
	