<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractOperInfoForm.js"></script>
<div class="formLayout form2Pa">
	<form id="contractOperInfoForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span></span></h4>
     	<ul>
	     	<li>
	     		<label for="contractOperInfoForm.opinion"><spring:message code="contractOperInfoForm.opinion"/></label>
					<input type="text" name="opinion" id="opinion" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="workFlowModelForm.bizId"><spring:message code="workFlowModelForm.bizId"/></label>
					<input type="text" name="bizId" id="bizId" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="workFlowModelForm.user"><spring:message code="workFlowModelForm.user"/></label>
					<input type="text" name="user" id="user" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="workFlowModelForm.createTime"><spring:message code="workFlowModelForm.createTime"/></label>
					<input type="text" name="createTime" id="createTime" class="required" 
									readonly="readonly"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="workFlowModelForm.workflowAuditStatus"><spring:message code="workFlowModelForm.workflowAuditStatus"/></label>
					<input type="text" name="workflowAuditStatus" id="workflowAuditStatus" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="contractOperInfoSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="contractOperInfoReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form>
</div>

	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>