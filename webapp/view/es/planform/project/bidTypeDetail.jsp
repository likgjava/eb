<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<div class="formLayout form2Pa">
	<ul>
		<li>
			<label for="comWorkName"><spring:message code="projectComWorkForm.comWorkName"/></label>
			<span id="comWorkName">${projectComWork.comWorkName}</span>
		</li>
		<li>
			<label for="comId"><spring:message code="projectComWorkForm.comId"/></label>
			<span id="comId">${projectComWork.comId }</span>
		</li>
		<li>
			<label for="remark"><spring:message code="projectComWorkForm.remark"/></label>
			<span id="remark">${projectComWork.remark }</span>
		</li>
	</ul>
</div>
