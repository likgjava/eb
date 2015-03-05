<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/companyDetail.js"></script>

  <div class="formLayout form2Pa detail">
 <form id="companyDetailForm" method="post">
 		<input type="hidden" name="model" id="model" value="<c:out value="${param.model}"/>"/>
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
	    <div class="treeEditNav">
				<ul id="createBtnsArea">
					<li id="companyEdit" class="edit"><a href="#"><span><spring:message code="globe.modify"/></span></a></li>
					<li id="companyDel" class="del"><a href="#"><span><spring:message code="globe.delete"/></span></a></li>
				</ul>
		</div>
	    <h5><span><spring:message code="companyForm.info"/></span></h5>
	    <ul>
	      <li>
	        <label><spring:message code="companyForm.name"/>:</label>
	        <span id="name"></span>
	      </li>
	      <li>
	        <label><spring:message code="companyForm.shortName"/>:</label>
	        <span id="shortName"></span>
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
	        <label><spring:message code="organizationForm.tel"/>:</label>
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






