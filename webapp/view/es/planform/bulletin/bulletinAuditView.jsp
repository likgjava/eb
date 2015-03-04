<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/bulletinAuditView.js"></script>
<div class="functionBtnDiv" >
	<button id="viewProjectId"><span class="add">查看项目</span></button>
	<span style="height:auto;">${bulletin.bullContents}</span>
</div>
 <form id="bulletinFormView" method="post">
<input type="hidden" name="projectId" id="projectId" value="${projectId}">
<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
<input type="hidden" name="auditStatus" id="auditStatus" value="<c:out value="${bulletin.auditStatus}"/>"/>
<input type="hidden" name="bullType" id="bullType" value="<c:out value="${bulletin.bullType}"/>"/>
<input type="hidden" name="approvalFlag" id="approvalFlag" value="<c:out value="${approvalFlag}"/>"/>
<c:if test="${(bulletin.project.ebuyMethod == '10'&& bulletin.project.tenderType == '02') || (bulletin.project.ebuyMethod == '09'&& bulletin.project.tenderType == '02')  || (bulletin.project.ebuyMethod == '08'&& bulletin.project.tenderType == '02') || (bulletin.project.ebuyMethod == '06'&& bulletin.project.tenderType == '03') || (bulletin.project.ebuyMethod == '00'&& bulletin.project.tenderType == '01')}">
<div class="formLayout formPa" id="signUpCondFactorView"></div>  
</c:if>
 <div class="formLayout formPa" style="height:73px;">
 <h5><span>审核意见</span></h5>
<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
<input type="hidden" name="auditStatus" id="auditStatus" value="<c:out value="${bulletin.auditStatus}"/>"/>
<input type="hidden" name="divTarget" id="divTarget" value="<c:out value="${divTarget}"/>"/>
<input type="hidden" name="divTargetUrl" id="divTargetUrl" value="<c:out value="${divTargetUrl}"/>"/>
<ul>
	<li style="height:50px;">
		<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
		<span class="eleRequired"></span>
	</li>
</ul>
</div>
</form>
<div class="conOperation">
	<button class="btn primary" id="bulletinPass" type="button" tabindex="19"><span>通过</span></button>
 	<button class="btn primary" id="bulletinNoPass" type="button" tabindex="19"><span>不通过</span></button>
 	<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
</div>
   <div id="historyView"></div>