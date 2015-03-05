<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/orgDetailDetail.js"></script>

<form method="post" name="OrgDetailDetailForm" id="OrgDetailDetailForm">
<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
<div class="inputInfoSimple">
				<h3>详细信息</h3>
				<ul>
								<li>
									<label><spring:message code="OrgDetailForm.agentType"/>:</label>
									<div><span id="agentType"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.authFirstDate"/>:</label>
									<div><span id="authFirstDate"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.contPerson"/>:</label>
									<div><span id="contPerson"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.contTel"/>:</label>
									<div><span id="contTel"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.dunsNum"/>:</label>
									<div><span id="dunsNum"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.fax"/>:</label>
									<div><span id="fax"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.historyId"/>:</label>
									<div><span id="historyId"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.openAccount"/>:</label>
									<div><span id="openAccount"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.openBank"/>:</label>
									<div><span id="openBank"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.orgCode"/>:</label>
									<div><span id="orgCode"></span></div>
								</li>
								<li><label><spring:message code="OrgDetailForm.orgCodeFile"/>:</label>
									<div><span id="orgCodeFile."></span></div>
					            </li>
								<li>
									<label><spring:message code="OrgDetailForm.regAddress"/>:</label>
									<div><span id="regAddress"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.regAuthOrg"/>:</label>
									<div><span id="regAuthOrg"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.regBusScope"/>:</label>
									<div><span id="regBusScope"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.regCode"/>:</label>
									<div><span id="regCode"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.regCoporate"/>:</label>
									<div><span id="regCoporate"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.regDate"/>:</label>
									<div><span id="regDate"></span></div>
								</li>
								<li><label><spring:message code="OrgDetailForm.regFile"/>:</label>
									<div><span id="regFile."></span></div>
					            </li>
								<li>
									<label><spring:message code="OrgDetailForm.regToDate"/>:</label>
									<div><span id="regToDate"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.unitAddress"/>:</label>
									<div><span id="unitAddress"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.unitBrief"/>:</label>
									<div><span id="unitBrief"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.unitLog"/>:</label>
									<div><span id="unitLog"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.unitName"/>:</label>
									<div><span id="unitName"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.unitScape"/>:</label>
									<div><span id="unitScape"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.unitType"/>:</label>
									<div><span id="unitType"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.webUrl"/>:</label>
									<div><span id="webUrl"></span></div>
								</li>
								<li>
									<label><spring:message code="OrgDetailForm.orgnization"/>:</label>
									<div><span id="orgnization"></span></div>
								</li>
				</ul>
</div> 

<Div align="center"><INPUT type="button" value="关闭" id="close"/></div>
</form>
