<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/purBulletin.js"></script>
<div class="formLayout form2Pa">
<h5>项目<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></h5>
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
			<label for="signUpSTime">报名开始时间：</label>
			<span id="signUpSTime"><fmt:formatDate value="${bulletin.signUpSTime }" pattern="yyyy-MM-dd HH:mm"/></span>
		</li>
		<li>
			<label for="signUpETime">报名结束时间：</label>
			<span id="signUpETime"><fmt:formatDate value="${bulletin.signUpETime }" pattern="yyyy-MM-dd HH:mm"/></span>
		</li>
		<li>
			<label for="tenderStartTime"><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>开始时间：</label>
			<span id="tenderStartTime"><fmt:formatDate value="${bulletin.tenderStartTime }" pattern="yyyy-MM-dd HH:mm"/></span>
		</li>
		<li>
			<label for="tenderEndTime">
			<dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>开始时间：</label>
			<span id="tenderEndTime"><fmt:formatDate value="${bulletin.tenderEndTime }" pattern="yyyy-MM-dd HH:mm"/></span>
		</li>
		<li class="formTextarea">
			<label for="bullContents"><spring:message code="bulletinForm.bullContents"/></label>
			<span id="bullContents">${bulletin.bullContents}</span>
		</li>
	</ul>
	<div class="conOperation">
	<button name="historyBackBtn" id="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
