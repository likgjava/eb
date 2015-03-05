<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/UpdateUserPwd.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/sha1.js"></script>
<div class="formLayout">
	<form id="pswForm" method="post">
	   <ul>
	   	  	<li>
		        <label>旧密码:</label>
		        <input type="password" name="oldPsw" id="oldPsw" class="required" maxlength="50" class="required"/>
			    <span class="eleRequired">*</span>			      
	       </li>
	   	  	<li>
		        <label>新密码:</label>
		        <input type="password" name="newPsw" id="newPsw" class="required" maxlength="50" class="required"/>
			    <span class="eleRequired">*</span>				      
	       </li>
	   	  	<li>
		        <label>新密码确认:</label>
		        <input type="password" name="newPsw2" id="newPsw2" class="required" maxlength="50" class="required"/>
			   <span class="eleRequired">*</span>		      
	       </li>
	   </ul>
	   <div class="conOperation">
			<button type="reset" id="submit"><span><spring:message code="globe.save"/></span></button>
			<button type="reset" id="reset"><span><spring:message code="globe.reset"/></span></button>
	   </div>
	 </form>
	</div>
  <div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
  </div>