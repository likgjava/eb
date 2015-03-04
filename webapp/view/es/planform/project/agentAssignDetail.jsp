<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<div class="formLayout form2Pa">
	<ul>
		<li>
			<label for="linkGovMan"><spring:message code="projectLinkGovManForm.linkGovMan"/></label>
			<span id="linkGovMan">${projectLinkGovMan.linkGovMan}</span>
		</li>
		<li>
			<label for="name"><spring:message code="projectLinkGovManForm.name"/></label>
			<span id="name">${projectLinkGovMan.name }</span>
		</li>
		<li>
			<label for="telephone"><spring:message code="projectLinkGovManForm.telephone"/></label>
			<span id="telephone">${projectLinkGovMan.telephone }</span>
		</li>
		<li>
			<label>&nbsp;</label>
			<span>&nbsp;</span>
		</li>
		<li>
			<label for="fax"><spring:message code="projectLinkGovManForm.fax"/></label>
			<span id="fax">${projectLinkGovMan.fax }</span>
		</li>
		<li>
			<label for="remark"><spring:message code="projectLinkGovManForm.remark"/></label>
			<span id="remark">${projectLinkGovMan.remark }</span>
		</li>
	</ul>
</div>
