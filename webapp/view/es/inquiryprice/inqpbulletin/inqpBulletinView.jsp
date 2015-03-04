<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpbulletin/inqpBulletinView.js"></script>
<input type="hidden" id="projectId" name="projectId"  value="${project.objId}">
<div class="formLayout formPa">
	<h5><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>内容
		<span><c:if test="${returnUrl=='yes'}">
			<a href="#" onclick="bullentinReturn();">返回</a>
		</c:if></span>
	</h5>
	<input type="hidden" name="auditStatus" id="auditStatus" value="${bulletin.auditStatus}"/>
	<input type="hidden" name="bullType" id="bullType" value="${bulletin.bullType}"/>
	<span style="height:auto;">${bulletin.bullContents}</span>
</div>
    <!--  
	<div class="formLayout" id="signUpCondFactorView"></div>
	-->