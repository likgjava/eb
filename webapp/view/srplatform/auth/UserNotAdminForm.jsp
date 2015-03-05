<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/UserNotAdminForm.js"></script>

<!--页面按钮开始-->
<div id="pageMenu" class="pageMenu ui-state-default">
    <a href="javascript:void(0);" id="refresh" class="addicon refresh">刷新</a>
    <a href="javascript:void(0);" id="print" class="addicon print">打印</a>
    <a href="javascript:void(0);" id="help" class="addicon help">帮助</a>
</div>
<!--页面按钮结束-->
<div class="form-container">
<form method="post" name="UserForm" id="UserForm"  action="UserController.do?method=save" onsubmit="return validateUserForm(this);">
	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	<input type="hidden" name="usrPasswordInit" id="usrPasswordInit" value="<c:out value="${objId}"/>"/>
  	<fieldset>
	    <legend>账号基本信息</legend>
		<div>
	      <label><spring:message code="UserForm.usName"/>:</label>
          <div id="usName"></div>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrCnName"/><em>*</em>:</label>
          <input type="text" name="usrCnName"  class="required" size="40">
          <span class="requisite"> </span>
        </div>
        <div>
	      <label><spring:message code="UserForm.org"/><em>*</em>:</label>
           <input type="hidden" name="org.objId" id="org.objId" value="">
    		<input type="text" name="org.name" id="org.name" value="" readonly="readonly" class="required sysicon siSearch">
    		<span class="requisite"> </span>
        </div>
    </fieldset>
    <fieldset>
	    <legend>账号详细信息</legend>
        <div>
	      <label><spring:message code="UserForm.usrPeriodOfValidity"/>:</label>
          <div id="periodOfValidityShort"></div>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrStatus"/>:</label>
          <div id="statusCN"></div>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrIsLocked"/>:</label>
          <div id="isLockedCN"></div>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrIsManager"/>:</label>
          <div id="isManagerCN"></div>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrIsCredential"/>:</label>
          <div id="isCredentialCN"></div>
        </div>
    </fieldset>
    <fieldset>
	    <legend>用户联系信息</legend>
        <div>
	      <label><spring:message code="UserForm.usrEmail"/><em>*</em>:</label>
          <input type="text" name="usrEmail"  class="required email" size="40">
          <span class="requisite"> </span>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrMobile"/><em>*</em>:</label>
          <input type="text" name="usrMobile"  class="required digits" size="40">
          <span class="requisite"> </span>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrTelOffice"/>:</label>
          <input type="text" name="usrTelOffice"  class="telephone" size="40">
          <span class="requisite"> </span>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrTelHome"/>:</label>
          <input type="text" name="usrTelHome"  class="telephone" size="40">
          <span class="requisite"> </span>
        </div>
        <div>
	      <label><spring:message code="UserForm.usrFax"/>:</label>
          <input type="text" name="usrFax"  size="40">
        </div>
        <div>
	      <label><spring:message code="UserForm.usrAddress"/>:</label>
          <input type="text" name="usrAddress" size="40">
        </div>
        <div>
	      <label><spring:message code="UserForm.usrPostCode"/>:</label>
          <input type="text" name="usrPostCode" size="40">
        </div>
        
	</fieldset> 
  
    <div class="buttonClass">
      
  <button  type="button" name="submit"><span><spring:message code="globe.save"/></span></button>
  <button  type="reset"><span><spring:message code="globe.reset"/></span></button>
  
    </div>  
</form>
</div>
