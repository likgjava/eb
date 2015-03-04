<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<div class="formLayout form2Pa">
	<ul>
		<li>
			<label for="bullTitle"><spring:message code="bulletinForm.bullTitle"/></label>
			<span id="bullTitle">${bulletin.bullTitle }</span>
		</li>
		<li>
			<label for="bulletinNo"><spring:message code="bulletinForm.bulletinNo"/></label>
			<span id="bulletinNo">${bulletin.bulletinNo }</span>
		</li>
		<li>
			<label>&nbsp;</label>
			<span>&nbsp;</span>
		</li>
		<li class="formTextarea">
			<label for="bullContents"><spring:message code="bulletinForm.bullContents"/></label>
			<span id="bullContents">${bulletin.bullContents }</span>
		</li>
	</ul>
</div>
