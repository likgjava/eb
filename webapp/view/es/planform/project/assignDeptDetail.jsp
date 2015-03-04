<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/purBulletin.js"></script>

<div class="formLayout form2Pa">
	<ul>
		<li>
			<label for="customer"><spring:message code="projectComWorkForm.customer"/></label>
			<span id="customer">${projectDepart.customer }</span>
		</li>
		<li>
			<label for="department"><spring:message code="projectComWorkForm.department"/></label>
			<span id="department">${projectDepart.department }</span>
		</li>
		<li>
			<label for="assignOpition"><spring:message code="projectComWorkForm.assignOpition"/></label>
			<span id="assignOpition">${projectDepart.assignOpition }</span>
		</li>
		<li>
			<label>&nbsp;</label>
			<span>&nbsp;</span>
		</li>
	</ul>
</div>
