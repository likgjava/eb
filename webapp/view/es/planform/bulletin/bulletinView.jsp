<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/bulletinView.js"></script>
<input type="hidden" name="projectId" id="projectId" value="${projectId}">
	<input type="hidden" name="auditStatus" id="auditStatus" value="${bulletin.auditStatus}"/>
	<input type="hidden" name="bullType" id="bullType" value="${bulletin.bullType}"/>
	<span style="height:auto;">${bulletin.bullContents}</span>
<c:if test="${bulletin.bullType==01||bulletin.bullType==10}">
<c:if test="${(bulletin.project.ebuyMethod == '10'&& bulletin.project.tenderType == '02') || (bulletin.project.ebuyMethod == '09'&& bulletin.project.tenderType == '02')  || (bulletin.project.ebuyMethod == '08'&& bulletin.project.tenderType == '02') || (bulletin.project.ebuyMethod == '06'&& bulletin.project.tenderType == '03') || (bulletin.project.ebuyMethod == '00'&& bulletin.project.tenderType == '01')}">
 <div class="formLayout" id="signUpCondFactorView"></div>
 </c:if>
</c:if>

