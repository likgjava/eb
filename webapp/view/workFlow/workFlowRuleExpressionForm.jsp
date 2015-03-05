<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers formLayout form2Pa">
	<form method="post" name="workFlowRuleExpressionForm" id="workFlowRuleExpressionForm">
		<ul>
			<textarea name="expressionContent" style="width: 770;height: 440;">${expressionContent}</textarea>
		</ul>
	</form>
	<div class="conOperation">
		<button name="save_edit_work_flow_rule_expression" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button name="close_edit_work_flow_rule_expression" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script>
$('[name=close_edit_work_flow_rule_expression]').click(function(){
	$('#work_flow_rule_expression_form .epsDialogCloseReload').click();
})
$('[name=save_edit_work_flow_rule_expression]').click(function(){
	$.getJSON($('#initPath').val()+'/ProcessDefNodeFormTemplateController.do?method=saveWorkFlowRuleExpression',formToJsonObject('workFlowRuleExpressionForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('[name=close_edit_work_flow_rule_expression]').click();
	});
})
</script>
