<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/gadgetForm.js"></script>

<div class="formLayout">
	<form id="gadgetForm" method="post">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<h4><span>小工具维护</span></h4>
			<ul>
			  <li>
			    <label for="name"><spring:message code="gadgetForm.name"/>:</label>
			    <input type="text" name="name" id="name" class="required" />
			    <span class="eleRequired">*</span>
			  </li>
			 
				<li>
				  <label for="resource.objId">关联资源:</label>
				    <input type="hidden" name="resource.objId" id="resource.objId" value="">
					<input type="text" name="resource.name" id="resource.name" value="" readonly="readonly" class="required sysicon siSearch">
					<span class="eleRequired">*</span>
				</li>
				 <li class="formTextarea">
				    <label for="descs"><spring:message code="gadgetForm.descs"/>:</label>
				    <textarea type="text" name="descs" id="descs" class="required" ></textarea>
				    <span class="eleRequired">*</span>
				  </li>
			</ul>
		   <div class="conOperation">
				<button type="button" id="gadgetSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="gadgetReturn"><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>