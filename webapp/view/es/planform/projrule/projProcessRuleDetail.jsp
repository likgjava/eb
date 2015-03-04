<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/projProcessRuleDetail.js"></script>
<div class="formLayout form2Pa">  
	<h5>项目规则</h5>      
	<form id="projProcessRuleForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
     		<ul>

					<li>
						<label for="docDemonStrate"><spring:message code="projProcessRuleForm.docDemonStrate"/>：</label>
						<span id="docDemonStrate">${projProcessRule.docDemonStrate}</span>
					</li>

					<li>
						<label for="localEsurvey"><spring:message code="projProcessRuleForm.localEsurvey"/>：</label>
						<span id="localEsurvey">${projProcessRule.localEsurvey}</span>
					</li>

					<li>
						<label for="qualification"><spring:message code="projProcessRuleForm.qualification"/>：</label>
							<span id="qualification">${projProcessRule.qualification}</span>
					</li>

					<li>
						<label for="multiphaseBid"><spring:message code="projProcessRuleForm.multiphaseBid"/>：</label>
							<span id="multiphaseBid">${projProcessRule.multiphaseBid}</span>
					</li>

					<li>
						<label for="openBudget"><spring:message code="projProcessRuleForm.openBudget"/>：</label>
							<span id="openBudget">${projProcessRule.openBudget}</span>
					</li>

					<li>
						<label for="bidSinglePack"><spring:message code="projProcessRuleForm.bidSinglePack"/>：</label>
						<span id="bidSinglePack">${projProcessRule.bidSinglePack}</span>
							
					</li>

					<li>
						<label for="needEvaluateGroup"><spring:message code="projProcessRuleForm.needEvaluateGroup"/>：</label>
						<span id="needEvaluateGroup">${projProcessRule.needEvaluateGroup}</span>
							
					</li>

					<li>
						<label for="needBuyErackPurchaseDoc"><spring:message code="projProcessRuleForm.needBuyErackPurchaseDoc"/>：</label>
							<span id="needBuyErackPurchaseDoc">${projProcessRule.needBuyErackPurchaseDoc}</span>
					</li>

					<li>
						<label for="evaluateOnline"><spring:message code="projProcessRuleForm.evaluateOnline"/>：</label>
							<span id="evaluateOnline">${projProcessRule.evaluateOnline}</span>
					</li>

					<li>
						<label for="publishPurchaseBulletin"><spring:message code="projProcessRuleForm.publishPurchaseBulletin"/>：</label>
						<span id="publishPurchaseBulletin">${projProcessRule.publishPurchaseBulletin}</span>
							
					</li>

					<li>
						<label for="publishResultReview"><spring:message code="projProcessRuleForm.publishResultReview"/>：</label>
						<span id="publishResultReview">${projProcessRule.publishResultReview}</span>
							
					</li>

					<li>
						<label for="publishResultBulletin"><spring:message code="projProcessRuleForm.publishResultBulletin"/>：</label>
							<span id="publishResultBulletin">${projProcessRule.publishResultBulletin}</span>
					</li>

					<li>
						<label for="upLoadAfterSignUp"><spring:message code="projProcessRuleForm.upLoadAfterSignUp"/>：</label>
							<span id="upLoadAfterSignUp">${projProcessRule.upLoadAfterSignUp}</span>
					</li>

					<li>
						<label for="evalFillScoreType"><spring:message code="projProcessRuleForm.evalFillScoreType"/>：</label>
							<span id="evalFillScoreType">${projProcessRule.evalFillScoreType}</span>
					</li>

					<li>
						<label for="openPackType"><spring:message code="projProcessRuleForm.openPackType"/>：</label>
							<span id="openPackType">${projProcessRule.openPackType}</span>
					</li>

					<li>
						<label for="openProcessType"><spring:message code="projProcessRuleForm.openProcessType"/>：</label>
							<span id="openProcessType">${projProcessRule.openProcessType}</span>
					</li>

		</ul>
		<div class="conOperation">
		
		</div>
	</form>
</div>
