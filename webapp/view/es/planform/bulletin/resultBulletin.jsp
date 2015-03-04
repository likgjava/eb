<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<div class="formLayout form2Pa">
<c:choose>
	<c:when test="${resultBulletin=='noResult'}">
		<div align="center" style="height:75px;margin-top:70px">暂无<dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out>!</div>
	</c:when>
	<c:otherwise>
	<ul>
		<li>
			<label for="bullTitle"><spring:message code="bulletinForm.bullTitle"/></label>
			<span id="bullTitle">${resultBulletin.bullTitle }</span>
		</li>
		<li>
			<label for="bulletinNo"><spring:message code="bulletinForm.bulletinNo"/></label>
			<span id="bulletinNo">${resultBulletin.bulletinNo }</span>
		</li>
		<li>
			<label>&nbsp;</label>
			<span>&nbsp;</span>
		</li>
		<li class="formTextarea">
			<label for="bullContents"><spring:message code="bulletinForm.bullContents"/></label>
			<span id="bullContents">${resultBulletin.bullContents }</span>
		</li>
	</ul>
	</c:otherwise>
</c:choose>
</div>
