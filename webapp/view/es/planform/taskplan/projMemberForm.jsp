<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/epp/taskplan/Record/projMemberForm.js"></script>
<div class="formLayout form2Pa">
	<form id="projMemberForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span>建设工程施工招标组织机构人员登记表</span></h4>
     	<ul>
	     	<li>
	     		<label for="projMemberForm.projMemberName"><spring:message code="projMemberForm.projMemberName"/><span class="eleRequired">*</span> </label>
					<input type="text" name="projMemberName" id="projMemberName" class="required" 
									class="required"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projMemberForm.projMemberDudy"><spring:message code="projMemberForm.projMemberDudy"/><span class="eleRequired">*</span> </label>
					<input type="text" name="projMemberDudy" id="projMemberDudy" class="required" 
									class="required"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projMemberForm.projMemberPosition"><spring:message code="projMemberForm.projMemberPosition"/><span class="eleRequired">*</span> </label>
					<input type="text" name="projMemberPosition" id="projMemberPosition" class="required" 
									class="required"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projMemberForm.projMemberEducation"><spring:message code="projMemberForm.projMemberEducation"/><span class="eleRequired">*</span> </label>
					<input type="text" name="projMemberEducation" id="projMemberEducation" class="required" 
									class="required"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projMemberForm.projMemberPro"><spring:message code="projMemberForm.projMemberPro"/><span class="eleRequired">*</span> </label>
					<input type="text" name="projMemberPro" id="projMemberPro" class="required" 
									class="required"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projMemberForm.projMemberLife"><spring:message code="projMemberForm.projMemberLife"/><span class="eleRequired">*</span> </label>
					<input type="text" name="projMemberLife" id="projMemberLife" class="required" 
									class="required"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projMemberForm.projMemberResults"><spring:message code="projMemberForm.projMemberResults"/><span class="eleRequired">*</span> </label>
					<input type="text" name="projMemberResults" id="projMemberResults" class="required" 
									class="required"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="projMemberForm.projMemberRemark"><spring:message code="projMemberForm.projMemberRemark"/><span class="eleRequired">*</span> </label>
					<input type="text" name="projMemberRemark" id="projMemberRemark" class="required" 
									class="required"
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
	     		<label for="workFlowModelForm.opinion"><spring:message code="workFlowModelForm.opinion"/></label>
					<input type="text" name="opinion" id="opinion" class="required" 
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
				<button type="button" id="projMemberSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="projMemberReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form>
</div>

	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>