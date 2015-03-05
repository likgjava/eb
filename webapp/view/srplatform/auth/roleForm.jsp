<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/roleForm.js"></script>
	<form id="roleForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<div class="formLayout">
			<h4><span><spring:message code="roleForm.info" /></span></h4>
		    <ul>
		    	<li>
			        <label for="input01"><spring:message code="roleForm.chName" />:</label>
			        <input type="text" name="chName" id="chName" class="required" maxlength="50"/>
				    <span class="eleRequired">*</span>
		        </li>
		    	<li>
			        <label for="input01"><spring:message code="roleForm.enName" />:</label>
			        <input type="text" name="enName" id="enName" class="required" maxlength="50"/>
				    <span class="eleRequired">*</span>
		        </li>
		    	<li>
			        <label for="input01"><spring:message code="roleForm.status" />:</label>
			        <select id="status" name="status" tabindex="10" >
	          			<option value="1" selected="selected"><spring:message code="roleForm.status1" /></option>
	            		<option value="0"><spring:message code="roleForm.status0" /></option>
	          		</select>
		        </li>
		    	
		        <li id="orgForm">
			        <label for="input01"><spring:message code="organizationForm.name" />:</label>
			        <input type="hidden" name="org.objId" id="org.objId" value="">
    				<input type="text" name="org.name" id="org.name" value="" style="border: 0" readonly="readonly" class="required">
			        <span class="eleRequired">请选择左侧机构</span>
		        </li>
		    	<li class="formTextarea">
			        <label for="input01"><spring:message code="roleForm.memo" />:</label>
			        <textarea id="memo" name="memo" cols="30" rows="4"  tabindex="14" ></textarea>
		       </li>
		    </ul>
		    <div class="conOperation">
				<button type="button" id="roleSave"><span><spring:message code="globe.save"/></span></button>
				<button type="reset" ><span><spring:message code="globe.reset"/></span></button>
				<button type="button" id="roleReturn"><span><spring:message code="globe.cancel"/></span></button>
			</div>
	    </div>
	</form>