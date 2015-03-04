<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bailRecordForm.js"></script>
<div class="formLayout form2Pa">
	<form id="bailRecordForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>记录表</span></h4>
     	<ul>
	     	<li>
	     		<label for="bailRecordForm.ballMoney"><spring:message code="bailRecordForm.ballMoney"/></label>
					<input type="text" name="ballMoney" id="ballMoney" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="bailRecordForm.renderTime"><spring:message code="bailRecordForm.renderTime"/></label>
					<input type="text" name="renderTime" id="renderTime" class="required" 
									readonly="readonly"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="bailRecordForm.renderAtt"><spring:message code="bailRecordForm.renderAtt"/></label>
					<input type="text" name="renderAtt" id="renderAtt" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="bailRecordForm.returnedTime"><spring:message code="bailRecordForm.returnedTime"/></label>
					<input type="text" name="returnedTime" id="returnedTime" class="required" 
									readonly="readonly"
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="bailRecordForm.returnedAtt"><spring:message code="bailRecordForm.returnedAtt"/></label>
					<input type="text" name="returnedAtt" id="returnedAtt" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="bailRecordForm.bailStatus"><spring:message code="bailRecordForm.bailStatus"/></label>
					<input type="text" name="bailStatus" id="bailStatus" class="required" 
						      />
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
		   <div class="conOperation">
				<button type="button" id="bailRecordSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" name="historyBackBtn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="bailRecordReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
	</form>
</div>

	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>