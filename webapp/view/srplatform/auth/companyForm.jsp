<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/select/jQuery.CascadingSelect.js">//级联</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/plug-in/jquery/select/jQuery.FillOptions.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/companyForm.js"></script>
<div class="formLayout">
  <form id="companyForm" method="post">
	<input type="hidden" name="model" id="model" value="<c:out value="${param.model}"/>"/>
	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	
	<input type="hidden" name="type" id="type" value="<c:out value="${param.type}"/>"/>
    <h4><span>机构信息</span></h4>
    <ul>
	      <li >
	        <label><spring:message code="organizationForm.name"/>:</label>
	        <input type="text" name="name" id="name"  maxlength="50" class="required long"/>
	        <span class="eleRequired">*</span> 
	      </li>
	      <li>
	        <label><spring:message code="organizationForm.shortName"/>:</label>
	        <input type="text" name="shortName" id="shortName" />
		  </li>
	      <li>
	        <label><spring:message code="companyForm.code"/>:</label>
	        <input type="text" name="code" id="code" />
	      </li>
	      <li>
	        <label><spring:message code="companyForm.croporate"/>:</label>
	        <input type="text" name="croporate" id="croporate" class=""/>
	      </li>
	      <li>
	        <label><spring:message code="companyForm.email"/>:</label>
	        <input type="text" name="email" id="email" class=" email" />
	     </li>
	      <li class="eleDisable">
	        <label>上级机构简称:</label>
	        <input type="hidden" name="parent.objId" id="parent.objId" value="<c:out value="${param.parentId}"/>"/>
	        <input type="text" name="parent.shortName" id="parent.shortName"  value="<c:out value="${param.parentShortName}"/>"/>
	     </li>
	      <li>
	        <label><spring:message code="organizationForm.contact"/>:</label>
	        <input type="text" name="contact" id="contact" class=""/>
	     </li>
	      <li>
	        <label><spring:message code="organizationForm.tel"/>:</label>
		     <input type="text" name="tel" id="tel" class=" cnPhone"/>
	     </li>
	      <li>
	        <label><spring:message code="companyForm.fax"/>:</label>
		    <input type="text" name="fax" id="fax" class=" cnPhone" />
	     </li>
	    <li>
	        <label><spring:message code="companyForm.address"/>:</label>
		    <input type="text" name="address" id="address" class="" />
	    </li>
	    <li>
	        <label><spring:message code="companyForm.postCode"/>:</label>
		    <input type="text" name="postCode" id="postCode" class="cnZipCode" maxlength="6"/>
	    </li>
	     <li >
			<label>所在地区11:</label>
			<select name="province.objId" id="province.objId" class="short"></select><span>省</span>
			<select name="city.objId" id="city.objId" class="short"></select><span>市</span>
			<select name="town.objId" id="town.objId" class="short required"></select><span>县/区</span>
			<span class="eleRequired">*</span>
		</li>
   </ul>
    <div class="conOperation">
      	 <button type="button" id="companySave"><span><spring:message code="globe.save"/></span></button>
	     <button type="reset" id="companyReturn"><span><spring:message code="globe.return"/></span></button>
    </div>
   </form>
</div>
  <div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
  </div>
