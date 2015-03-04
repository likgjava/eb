<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bailRecordDetail.js"></script>

<div class="formLayout form2Pa">
	<form id="bailRecordDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span><dm:out  value="local__BAILRECORD" tenderType="${project.tenderType}">保证金</dm:out>记录表</span></h4>
			     	<ul>
						<li>
			 				<label for="bailRecordForm.ballMoney"><spring:message code="bailRecordForm.ballMoney"/></label>
							<span id="ballMoney"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.renderTime"><spring:message code="bailRecordForm.renderTime"/></label>
							<span id="renderTime"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.renderAtt"><spring:message code="bailRecordForm.renderAtt"/></label>
							<span id="renderAtt"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.returnedTime"><spring:message code="bailRecordForm.returnedTime"/></label>
							<span id="returnedTime"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.returnedAtt"><spring:message code="bailRecordForm.returnedAtt"/></label>
							<span id="returnedAtt"></span>
						</li>
						<li>
			 				<label for="bailRecordForm.bailStatus"><spring:message code="bailRecordForm.bailStatus"/></label>
							<span id="bailStatus"></span>
						</li>
					</ul>
		   <div class="conOperation">
				<button type="button" id="bailRecordReturn" type="button" tabindex="20""><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>

	<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
      </ul>
    </div>
