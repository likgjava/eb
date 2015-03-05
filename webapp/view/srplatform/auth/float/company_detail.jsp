<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/float/company_detail.js"></script>

  <div class="formLayout detail">
 <form id="companyDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	    <ul>
	      <li>
	        <label><spring:message code="organizationForm.name"/>:</label>
	        <span id="name"></span>
	      </li>
	      <li>
	        <label><spring:message code="organizationForm.shortName"/>:</label>
	        <span id="shortName"></span>
	      </li>
	      <li>
	        <label>上级机构:</label>
	        <span id="parent.shortName"></span>
	      </li>
	      
	      <li>
	        <label><spring:message code="companyForm.code"/>:</label>
	       	<span id="code"></span>
	      </li>
	      <li>
	        <label><spring:message code="companyForm.croporate"/>:</label>
	        <span id="croporate"></span>
	      </li>
	      <li>
	        <label><spring:message code="companyForm.email"/>:</label>
	        <span id="email"></span>
	     </li>
	     <li>
	        <label><spring:message code="organizationForm.contact"/>:</label>
	        <span id="contact"></span>
	     </li>
	      <li>
	        <label>联系电话:</label>
		    <span id="tel"></span>
	     </li>
	      <li>
	        <label><spring:message code="companyForm.fax"/>:</label>
		    <span id="fax"></span>
	     </li>
	   	<li>
	        <label><spring:message code="companyForm.address"/>:</label>
		    <span id="address"></span>
	     </li>
	   	 <li>
	        <label ><spring:message code="companyForm.postCode"/>:</label>
		    <span id="postCode"></span>
	     </li>
	     <li>
			<label ><spring:message code="companyForm.town"/>:</label>
		    <span id="district"></span>
		</li>
	     
	   </ul>
  </form>
  </div>






