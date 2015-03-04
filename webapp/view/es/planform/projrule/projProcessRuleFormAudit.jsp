<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script>
var purBulletinAudit = {};
purBulletinAudit.checkOpinion = function(){
	//操作权限
	var doAuth = true;
	if($("#auditStatus").val()=="N"){
		if(($("textarea[name='opinion']").val()==null || $("textarea[name='opinion']").val()=="")){
			alert("请填写审核不通过原因!");
			doAuth = false;
		}
	}
	return doAuth;
}
purBulletinAudit.auditBulletin = function(){
	$.getJSON($('#initPath').val()+'/ProjProcessRuleController.do?method=auditProjProcessRule', {objId:$("#objId").val(),auditStatus:$("#auditStatus").val(),taskId:$("#taskId").val(),opinion:$("#opinion").val()}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('#conBody').loadPage($('#initPath').val()+'/view/srplatform/wflow/myTaskListView.jsp');
	});
}

$(document).ready(function(){
	//确认
	$('#passButton').click(function(){
		$("#auditStatus").val("Y");
		if(purBulletinAudit.checkOpinion()){
			purBulletinAudit.auditBulletin();
		}
	});
	//不确认
	$('#noPassButton').click(function(){
		$("#auditStatus").val("N");
		if(purBulletinAudit.checkOpinion()){
			purBulletinAudit.auditBulletin();
		}
	});
})
</script>
		<div class="formLayout form2Pa">
			<h5>审核项目规则</h5>
			<ul>

				<li>
					<label for="docDemonStrate"><input type="checkbox" id="docDemonStrate" name="docDemonStrate" value='1' disabled="disabled"
					<c:if test="${projProcessRule.docDemonStrate== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.docDemonStrate"/></span>
				</li>

				<li>
					<label for="localEsurvey"><input type="checkbox" id="localEsurvey" name="localEsurvey" value='1' disabled="disabled"
					<c:if test="${projProcessRule.localEsurvey== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.localEsurvey"/></span>
				</li>
				<li>
					<label for="qualification"><input type="checkbox" id="qualification" name="qualification" value='1' disabled="disabled"
					<c:if test="${projProcessRule.qualification== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.qualification"/></span>
				</li>

				<li>
					<label for="multiphaseBid"><input type="checkbox" id="multiphaseBid" name="multiphaseBid" value='1' disabled="disabled"
					<c:if test="${projProcessRule.multiphaseBid== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.multiphaseBid"/></span>
				</li>

				<li>
					<label for="openBudget"><input type="checkbox" id="openBudget" name="openBudget" value='1' disabled="disabled"
					<c:if test="${projProcessRule.openBudget== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.openBudget"/></span>
				</li>

				<li>
					<label for="bidSinglePack"><input type="checkbox" id="bidSinglePack" name="bidSinglePack" value='1' disabled="disabled"
					<c:if test="${projProcessRule.bidSinglePack== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.bidSinglePack"/></span>
				</li>

				<li>
					<label for="needEvaluateGroup"><input type="checkbox" id="needEvaluateGroup" name="needEvaluateGroup" value='1' disabled="disabled"
					<c:if test="${projProcessRule.needEvaluateGroup== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.needEvaluateGroup"/></span>
				</li>

				<li>
					<label for="needBuyErackPurchaseDoc"><input type="checkbox" id="needBuyErackPurchaseDoc" name="needBuyErackPurchaseDoc" value='1' disabled="disabled"
					<c:if test="${projProcessRule.needBuyErackPurchaseDoc== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.needBuyErackPurchaseDoc"/></span>
					
				</li>

				<li>
					<label for="evaluateOnline"><input type="checkbox" id="evaluateOnline" name="evaluateOnline" value='1' disabled="disabled"
					<c:if test="${projProcessRule.evaluateOnline== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.evaluateOnline"/></span>
				</li>

				<li>
					<label for="publishPurchaseBulletin"><input type="checkbox" id="publishPurchaseBulletin" name="publishPurchaseBulletin" value='1' disabled="disabled"
					<c:if test="${projProcessRule.publishPurchaseBulletin== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.publishPurchaseBulletin"/></span>
				</li>
				<li>
					<label for="publishResultReview"><input type="checkbox" id="publishResultReview" name="publishResultReview" value='1' disabled="disabled"
					<c:if test="${projProcessRule.publishResultReview== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.publishResultReview"/></span>
				</li>
				<li>
					<label for="publishResultBulletin"><input type="checkbox" id="publishResultBulletin" name="publishResultBulletin" value='1' disabled="disabled"
					<c:if test="${projProcessRule.publishResultBulletin== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.publishResultBulletin"/></span>
				</li>

				<li>
					<label for="upLoadAfterSignUp"><input type="checkbox" id="upLoadAfterSignUp" name="upLoadAfterSignUp" value='1' disabled="disabled"
					<c:if test="${projProcessRule.upLoadAfterSignUp== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.upLoadAfterSignUp"/></span>
				</li>

				<li>
					<label for="evalFillScoreType"><input type="checkbox" id="evalFillScoreType" name="evalFillScoreType" value='1' disabled="disabled"
					<c:if test="${projProcessRule.evalFillScoreType== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.evalFillScoreType"/></span>
				</li>

				<li>
					<label for="openPackType"><input type="checkbox" id="openPackType" name="openPackType" value='1' disabled="disabled"
					<c:if test="${projProcessRule.openPackType== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.openPackType"/></span>
				</li>

				<li>
					<label for="openProcessType"><input type="checkbox" id="openProcessType" name="openProcessType" value='1' disabled="disabled"
					<c:if test="${projProcessRule.openProcessType== '1' }">checked="checked"
					</c:if>
					/>&nbsp;</label>
					<span><spring:message code="projProcessRuleForm.openProcessType"/></span>
				</li>

			</ul>
		</div>
    	<div class="formLayout">
  			<h5>操作意见</h5>
      		<form id="auditProjRuleForm" method="post">
      		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
			<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
			<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
			<ul style="padding-top:0px">
				<li class="formTextarea">
					<label>意见</label>
					<textarea name="opinion" id="opinion"></textarea>
				</li>
				<li>
					 <div class="conOperation" style="text-align:center">
			       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
						<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
			   	    </div>
				</li>
			</ul>
			</form>
    	</div>