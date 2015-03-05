<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/wflow/ProcessDefNodeDetail.js"></script>

<form method="post" name="ProcessDefNodeDetailForm" id="ProcessDefNodeDetailForm">
<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
<div class="inputInfoSimple">
				<h3>详细信息</h3>
				<ul>
								<li><label><fmt:message key="ProcessDefNodeForm.processDefine"/></label>
									<div><span id="processDefine."></span></div>
					            </li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.nodeSort"/></label>
									<div><span id="nodeSort"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.nodeName"/></label>
									<div><span id="nodeName"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.nodeDes"/></label>
									<div><span id="nodeDes"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.rolId"/></label>
									<div><span id="rolId"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.nodeSign"/></label>
									<div><span id="nodeSign"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.resMode"/></label>
									<div><span id="resMode"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.resLink"/></label>
									<div><span id="resLink"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.isAutoExecute"/></label>
									<div><span id="isAutoExecute"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.isAppointUser"/></label>
									<div><span id="isAppointUser"></span></div>
								</li>
								<li>
									<label><fmt:message key="ProcessDefNodeForm.isCommonAudit"/></label>
									<div><span id="isCommonAudit"></span></div>
								</li>
				</ul>
</div> 

<Div align="center"><INPUT type="button" value="关闭" id="close"/></div>
</form>
