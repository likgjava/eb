<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/bulletinOrPublicityAudit.js"></script>
<form id="bulletinFormView" method="post">
	<input type="hidden" name="auditStatus" id="auditStatus" value="<c:out value="${bulletin.auditStatus}"/>"/>
	<input type="hidden" name="bullType" id="bullType" value="<c:out value="${bulletin.bullType}"/>"/>
	<input type="hidden" name="fromList" id="fromList" value="<c:out value="${fromList}"/>"/>
	<input type="hidden" name="fromDesk" id="fromDesk" value="<c:out value="${fromDesk}"/>"/>
	<input type="hidden" name="objId" id="objId" value="<c:out value="${bulletin.objId}"/>"/>
	<div>
		<span style="height:auto;">${bulletin.bullContents}</span>
	</div>
	<div class="formLayout formPa">
	<h5>审核意见</h5>
        <ul>
			<li class="fullLine">
				<textarea name="opinion" id="opinion" style="width: 99%;height: 45px;" class="maxInput" maxlength="200">同意</textarea>
			</li>
		</ul>
		<div class="conOperation">
	        <button class="btn primary" id="bulletinPass" type="button" tabindex="19"><span>通过</span></button>
	        <button class="btn primary" id="bulletinNoPass" type="button" tabindex="19"><span>不通过</span></button>
	        <button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
	        <c:if test="${fromList=='yes'}">
				<button id="returnBtn" type="button" tabindex="18"><span>返回</span></button>
			</c:if>
	    </div>
	</div>
</form>

<div id="historyView"></div>