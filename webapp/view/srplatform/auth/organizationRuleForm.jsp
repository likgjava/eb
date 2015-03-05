<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/organizationRuleForm.js"></script>
<form id="orgnizationRuleForm" method="post">
    <input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
  	<div class="formLayout">
	    <h4><span>机构规则</span></h4>
	    <ul>
	      <li>
	        <label><spring:message code="organizationRuleForm.source"/>:</label>
	        <select name="source" id="source">
    				<option value="1">公司</option>
    				<option value="2">部门</option>
    				<option value="3">岗位</option>
	        </select>
	        <span class="eleRequired">*</span> 
	      </li>
	      <li>
	        <label><spring:message code="organizationRuleForm.target"/>:</label>
	        <select name="target" id="target">
       				<option value="1">公司</option>
       				<option value="2">部门</option>
       				<option value="3">岗位</option>
	        </select>
	        <span class="eleRequired">*</span> 
	     </li>
	     <li>
	        <label><spring:message code="organizationRuleForm.rule"/>:</label>
	        <input type="text" name="rule" id="rule" class="required" 
										class="required"
										maxLength="50"
							      />
		    <span class="eleRequired">*</span> 
	      </li>
	    </ul>
	   <div class="conOperation">
	    	 <button  id="orgnizationRuleSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		     <button  id="orgnizationRuleReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	   </div>
  </div>
 </form> 
 <div class="formTips">
 	 <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
     </ul>
 </div>