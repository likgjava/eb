<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/consign/consignForm.js"></script> 
<input type="hidden" id="isShowOpinion" value="${param.isShowOpinion}"></input>
<input type="hidden" id="taskPlanId" value="${taskPlan.objId}">
<div class="partContainers">
<div class="formLayout form2Pa">        
	<form>
		<h5 align="center"><span>申报书信息</span></h5>
   		<ul>
			<li>
				<label class="short" for="taskCode"><spring:message code="taskPlanForm.taskCode"/>：</label>
				<span id="taskCode">${taskPlan.taskCode}</span>
			</li>
			<li>
				<label class="short" for="taskName"><spring:message code="taskPlanForm.taskName"/>：</label>
				<span id="taskName">${taskPlan.taskName}</span>
			</li>
		</ul>
	</form>
	</div>
</div>
<div class="partContainers">
		<div class="formLayout form2Pa">
		<h5 align="center"><span>委托协议</span></h5>
     	<ul>
        <form:form id="consignForm" method="post" modelAttribute="consign">
	    <input type="hidden" name="workFlowTaskId" id="workFlowTaskId" value="${param.workFlowTaskId}"/>
		<input type="hidden" name="taskPlanIds" id="taskPlanIds" value="<c:out value="${taskPlanId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${consign.project.objId}"/>
		<input type="hidden" name="isSave" id="isSave" value="${isSave}"/>
		<form:hidden path="objId" id="consignId"></form:hidden> 
		<form:hidden path="confirmStatus"></form:hidden>
		<form:hidden path="useStatus"></form:hidden>
			<li>
				<label class="short" for="consCode"><spring:message code="consignForm.consCode"/>：</label>
				<input type="text" name="consCode" value="${consign.consCode}" readonly="readonly" style="border: 0">
			</li>

			<li>
				<label class="short" for="consName"><spring:message code="consignForm.consName"/>：</label>
				<form:input path="consName" cssClass="required"/>
				<span class="eleRequired">*</span> 
			</li>
			<li class="fullLine">
				<label class="short" for="consBuyerName"><spring:message code="consignForm.consBuyerName"/>：</label>
				<form:input path="consBuyerName" disabled="true" />
				<form:hidden path="consBuyer.objId"/>
				<span class="eleRequired">*</span> 
			</li>
			
			<li>
				<label class="short" for="consBuyerLinker"><spring:message code="consignForm.consBuyerLinker"/>：</label>
				<form:input path="consBuyerLinker" cssClass="required"/>
				<span class="eleRequired">*</span> 
			</li>
			
			<li>
				<label class="short" for="consBuyerTel"><spring:message code="consignForm.consBuyerTel"/>：</label>
				<form:input path="consBuyerTel" cssClass="required"/>
				<span class="eleRequired">*</span> 
			</li>

			<li class="fullLine">
				<label class="short" for="consAgentName"><spring:message code="consignForm.consAgent"/>：</label>
				<form:input path="consAgentName" disabled="true" />
				<form:hidden path="consAgent.objId"/>
				<span class="eleRequired">*</span> 
			</li>

			<li>
				<label class="short" for="consAgentLinker"><spring:message code="consignForm.consAgentLinker"/>：</label>
				<form:input path="consAgentLinker" cssClass="required"/>
				<span class="eleRequired">*</span> 
			</li>

			<li>
				<label class="short" for="consAgentTel"><spring:message code="consignForm.consAgentTel"/>：</label>
				<form:input path="consAgentTel" cssClass="required"/>
				<span class="eleRequired">*</span> 
			</li>

			
			<li>
				<label class="short" for="consTime"><spring:message code="consignForm.consTime"/>：</label>
				<form:input path="consTime" cssClass="required" readonly="readonly" id="consTime"/>
				<span class="eleRequired">*</span> 
			</li>
			
			<li>
				<label class="short" for="consFinishTime"><spring:message code="consignForm.consFinishTime"/>：</label>
				<form:input path="consFinishTime" cssClass="required" readonly="readonly" id="consFinishTime"/>
				<span class="eleRequired">*</span> 
			</li>
			</form:form>
			
			<li class="fullLine">
				<label class="short" for="attachRelaId"><spring:message code="consignForm.consContentsAtt"/><br><span class="eleRequired">(必填项*)</span>：</label>
				<div id="attachRelaId" class="uploadFile">${consign.consContentsAtt}</div>
			</li>

		</ul>
	</div>
    <div class="formLayout" style="height: 73px;">
	<h5><span>确认意见</span></h5>
			<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
			<span class="eleRequired"></span>
	</div>
	
	<div class="conOperation">
			<button id="consignSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="consignSubmit" type="button" tabindex="19"><span>提交确认</span></button>
			<button id="noPassButton" type="button" tabindex="19"><span>退回</span></button>
			<button id="show_audit_view" ><span>操作历史</span></button>
			<button name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		</div>
</div>	
<div id="consign_audit_list" ></div>