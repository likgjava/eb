<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/bulletinDetail.js"></script>

<div class="formLayout form2Pa">  
	<form id="bulletinDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<h4><span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></span></h4>
		<ul>
			<li>
				<label for="bullTitle"><spring:message code="bulletinForm.bullTitle"/></label>
				<span id="bullTitle"></span>
			</li>
			<li>
				<label for="bulletinNo"><spring:message code="bulletinForm.bulletinNo"/></label>
				<span id="bulletinNo"></span>
			</li>
			
			<li>
				<label for="bullContents"><spring:message code="bulletinForm.bullContents"/></label>
				<span id="bullContents"></span>
			</li>
			<li>
				<label for="bullIssuedate"><spring:message code="bulletinForm.bullIssuedate"/></label>
				<span id="bullIssuedate"></span>
			</li>
			<li>
				<label for="bullValidDdte"><spring:message code="bulletinForm.bullValidDdte"/></label>
				<span id="bullValidDdte"></span>
			</li>
			<li>
				<label for="bullInvaliddate"><spring:message code="bulletinForm.bullInvaliddate"/></label>
				<span id="bullInvaliddate"></span>
			</li>
			<li>
				<label for="bullSignData"><spring:message code="bulletinForm.bullSignData"/></label>
				<span id="bullSignData"></span>
			</li>
			<li>
				<label for="bullType"><spring:message code="bulletinForm.bullType"/></label>
				<span id="bullType"></span>
			</li>
			<li>
				<label for="buyMethod"><spring:message code="bulletinForm.buyMethod"/></label>
				<span id="buyMethod"></span>
			</li>
			<li>
				<label for="useStatus"><spring:message code="bulletinForm.useStatus"/></label>
				<span id="useStatus"></span>
			</li>
			<li>
				<label for="createUser"><spring:message code="bulletinForm.createUser"/></label>
				<span id="createUser"></span>
			</li>
			<li>
				<label for="createTime"><spring:message code="bulletinForm.createTime"/></label>
				<span id="createTime"></span>
			</li>
			<li>
				<label for="updateUser"><spring:message code="bulletinForm.updateUser"/></label>
				<span id="updateUser"></span>
			</li>
			<li>
				<label for="updateTime"><spring:message code="bulletinForm.updateTime"/></label>
				<span id="updateTime"></span>
			</li>
		</ul>
	    <div class="conOperation">
	        <button class="btn primary" id="bulletinReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	    </div>
	</form>
</div>
