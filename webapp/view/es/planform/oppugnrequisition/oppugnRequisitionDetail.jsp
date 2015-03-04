<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/oppugnrequisition/oppugnRequisitionDetail.js"></script>

<div class="formLayout form2Pa">  
	<form:form id="oppugnRequisitionDetailForm" method="post" modelAttribute="oppugnRequisition">
		<form:hidden path="objId"></form:hidden> 
		<input type="hidden" id="type" value="${param.type}" />
		<input type="hidden" id="projectId" value="${projectId}" />
		<h5><span>质疑信息</span></h5>
		<ul>	
			<li>
				<label for="oppuType"><spring:message code="oppugnRequisitionForm.oppuType"/></label>
				<span id="oppuType">${oppugnRequisition.oppuTypeCN}</span>
			</li>
			<li>
				<label for="consBuyerName"><spring:message code="oppugnRequisitionForm.consBuyerName"/></label>
				<span id="consBuyerName">${oppugnRequisition.consBuyerNameCN}</span>
			</li>
			<li>
				<label for="oppuLinker"><spring:message code="oppugnRequisitionForm.oppuLinker"/></label>
				<span id="oppuLinker">${oppugnRequisition.oppuLinker}</span>
			</li>
			<li>
				<label for="oppuLinkerTel"><spring:message code="oppugnRequisitionForm.oppuLinkerTel"/></label>
				<span id="oppuLinkerTel">${oppugnRequisition.oppuLinkerTel}</span>
			</li>
			<li>
				<label for="oppuLinkerFax"><spring:message code="oppugnRequisitionForm.oppuLinkerFax"/></label>
				<span id="oppuLinkerFax">${oppugnRequisition.oppuLinkerFax}</span>
			</li>
			<li>
				<label for="oppuLinkerEmail"><spring:message code="oppugnRequisitionForm.oppuLinkerEmail"/></label>
				<span id="oppuLinkerEmail">${oppugnRequisition.oppuLinkerEmail}</span>
			</li>
			<li>
				<label for="oppuLinkerAddress"><spring:message code="oppugnRequisitionForm.oppuLinkerAddress"/></label>
				<span id="oppuLinkerAddress">${oppugnRequisition.oppuLinkerAddress}</span>
			</li>
			<li>
				<label for="oppuReplyer"><spring:message code="oppugnRequisitionForm.oppuReplyer"/></label>
				<span id="oppuReplyer">${oppugnRequisition.oppuReplyer}</span>
			</li>
			<li class="fullLine" >
				<label for="attachRelaId"><spring:message code="oppugnRequisitionForm.attachRelaId"/></label>
				<span id="attachRelaId">${oppugnRequisition.attachRelaId}</span>
			</li>
			<li class="formTextarea" >
				<label for="oppuContent"><spring:message code="oppugnRequisitionForm.oppuContent"/></label>
				<span id="oppuContent">${oppugnRequisition.oppuContent}</span>
			</li>
			<li class="formTextarea" >
				<label for="oppuRemark"><spring:message code="oppugnRequisitionForm.oppuRemark"/></label>
				<span id="oppuRemark">${oppugnRequisition.oppuRemark}</span>
			</li>
		</ul>
	</form:form>
	
	<div class="conOperation">
			<button id="oppugnRequisitionReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
