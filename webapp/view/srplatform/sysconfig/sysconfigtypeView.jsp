<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/sysconfig/sysconfigtypeView.js"></script>
		<div class="formLayout">
		<form id="sysconfigtypeViewForm" method="post">		
		<ul>
			<li>
				<!-- 不展现出来的数据  start-->
				<input type="hidden" name="objId" id="objId" value="${objId}"/>
				<!-- 不展现出来的数据  end-->
				<label><spring:message code="sysConfigTypeForm.name"/>:</label>
				<span id="name"></span>
			</li>
			<li>
				<label><spring:message code="sysConfigTypeForm.code"/>:</label>
				<span id="code"></span>
			</li>
			<li>
				<label><spring:message code="sysConfigTypeForm.typeKind"/>:</label>
				<span id="typeKind"></span>
			</li>
			<li>
				<label><spring:message code="sysConfigTypeForm.notes"/>:</label>
				<span id="notes"></span>
			</li>
		</ul>
		</form>
		</div>
