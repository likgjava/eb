<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/workFlow/ProcessDefineDetail.js"></script>

<form method="post" name="ProcessDefineDetailForm" id="ProcessDefineDetailForm">
<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
<div class="inputInfoSimple">
				<h3>详细信息</h3>
				<ul>
								<li>
									<label><fmt:message key="ProcessDefineForm.processDefName"/></label>
									<div><span id="processDefName"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefineForm.bizType"/></label>
									<div><span id="bizType"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefineForm.status"/></label>
									<div><span id="status"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefineForm.createTime"/></label>
									<div><span id="createTime"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefineForm.createUser"/></label>
									<div><span id="createUser"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefineForm.modifyTime"/></label>
									<div><span id="modifyTime"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefineForm.modifyUser"/></label>
									<div><span id="modifyUser"></span></div>
								</li>
				</ul>
</div> 

<Div align="center"><INPUT type="button" value="关闭" id="close"/></div>
</form>
