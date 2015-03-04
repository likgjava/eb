<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpbulletin/inqpBulletinAuditView.js"></script>
<div class="functionBtnDiv" style="text-align: right;margin-right:10px;">
 <button id="viewProjectId"><span class="add">查看项目</span></button>
<span style="height:auto;">${bulletin.bullContents}</span>
</div>
<!-- 
 <div class="formLayout" id="signUpCondFactorView"></div>
 -->
 <div class="formLayout formPa" style="height: 73px;">
	<h5>审核意见</h5>
 <form id="bulletinFormView" method="post">
	<input type="hidden" name="projectId" id="projectId" value="${projectId}">
	<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
	<input type="hidden" name="auditStatus" id="auditStatus" value="<c:out value="${bulletin.auditStatus}"/>"/>
	<input type="hidden" name="approvalFlag" id="approvalFlag" value="<c:out value="${approvalFlag}"/>"/>
	<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
	<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
	<input type="hidden" name="auditStatus" id="auditStatus" value="<c:out value="${bulletin.auditStatus}"/>"/>
	<input type="hidden" name="divTarget" id="divTarget" value="<c:out value="${divTarget}"/>"/>
	<input type="hidden" name="divTargetUrl" id="divTargetUrl" value="<c:out value="${divTargetUrl}"/>"/>
	<input type="hidden" name="fromList" id="fromList" value="<c:out value="${fromList}"/>"/>
	<input type="hidden" name="fromDesk" id="fromDesk" value="<c:out value="${fromDesk}"/>"/>
	<ul>
		<li style="height:50px;">
			<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
		</li>
	</ul>
	</form>
	</div>

	<div class="conOperation">
	  	<button class="btn primary" id="bulletinPass" type="button" tabindex="19"><span>通过</span></button>
 		<button class="btn primary" id="bulletinNoPass" type="button" tabindex="19"><span>不通过</span></button>
 		<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
  	</div>
   <div id="historyView"></div>