<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/clauseDetailDetail.js"></script>

<div class="formLayout form2Pa">
	<form id="clauseDetailDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span>项目</span></h4>
			     	<ul>
						<li>
			 				<label for="workFlowModelForm.bizId"><spring:message code="workFlowModelForm.bizId"/></label>
							<span id="bizId"></span>
						</li>
						<li>
			 				<label for="workFlowModelForm.opinion"><spring:message code="workFlowModelForm.opinion"/></label>
							<span id="opinion"></span>
						</li>
						<li>
			 				<label for="workFlowModelForm.user"><spring:message code="workFlowModelForm.user"/></label>
							<span id="user"></span>
						</li>
						<li>
			 				<label for="workFlowModelForm.createTime"><spring:message code="workFlowModelForm.createTime"/></label>
							<span id="createTime"></span>
						</li>
						<li>
			 				<label for="workFlowModelForm.workflowAuditStatus"><spring:message code="workFlowModelForm.workflowAuditStatus"/></label>
							<span id="workflowAuditStatus"></span>
						</li>
					</ul>
		   <div class="conOperation">
				<button type="button" id="clauseDetailReturn" type="button" tabindex="20""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>

	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>
