<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers formLayout form2Pa">
	<form method="post" name="ProcessDefNodeFormForm" id="ProcessDefNodeFormForm"  action="#">
		<input type="hidden" name="objId" id="objId" value="${processDefNodeForm.objId}"/>
		<input type="hidden" name="processDefNode.objId" id="processDefNode.objId" value="${processDefNodeForm.processDefNode.objId}"/>
		<ul>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefNodeFormForm.formPropertyNo"/>：</label>
				<input type="text" name="formPropertyNo" value="${processDefNodeForm.formPropertyNo}" class="required long digits" maxlength="8"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefNodeFormForm.formPropertyName"/>：</label>
				<input type="text" name="formPropertyName" value="${processDefNodeForm.formPropertyName}" class="required long" maxlength="50"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefNodeFormForm.formPropertyType"/>：</label>
				<html:select id="formPropertyType" name="formPropertyType" selectedValue="${processDefNodeForm.formPropertyType}" code="processDefNodeForm.formPropertyType" style="width:306px;"></html:select>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefNodeFormForm.formPropertyOperate"/>：</label>
				<html:select id="formPropertyOperate" name="formPropertyOperate" selectedValue="${processDefNodeForm.formPropertyOperate}" code="processDefNodeForm.formPropertyOperate" style="width:306px;"></html:select>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefNodeFormForm.auditResult"/>：</label>
				<html:select id="auditResult" name="auditResult" selectedValue="${processDefNodeForm.auditResult}" code="AUDIT_RESULT_ENUM" style="width:306px;"></html:select>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefNodeFormForm.disposeResult"/>：</label>
				<html:select id="disposeResult" name="disposeResult" selectedValue="${processDefNodeForm.disposeResult}" code="${processDefNodeForm.processDefNode.processDefine.disposeResultEnum}" style="width:306px;"></html:select>
				<span>${processDefNodeForm.processDefNode.processDefine.disposeResultEnum}</span>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefNodeFormForm.isTakeBack"/>：</label>
				<input type="radio" name="isTakeBack" <c:if test="${processDefNodeForm.isTakeBack == true}">checked="checked"</c:if> value="true" />是
				<input type="radio" name="isTakeBack" <c:if test="${processDefNodeForm.isTakeBack == false}">checked="checked"</c:if> value="false" />否
			</li>
			<li class="fullLine" id="processNodeNoLi">
				<label class="short"><spring:message code="processDefNodeFormForm.processNodeNo"/>：</label>
				<input type="text" name="processNodeNo" value="${processDefNodeForm.processNodeNo}" class="long digits" maxlength="8"></input>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefNodeFormForm.formPropertyRule"/>：</label>
				<textarea style="width: 650px;height: 240px;" name="formPropertyRule" maxlength="1000">${processDefNodeForm.formPropertyRule}</textarea>
			</li>
		</ul>
	</form>
    <div class="conOperation">
    	<button id="save_node_form" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button id="return_to_node_form_list" type="button" tabindex="19"><span>关闭</span></button>
	</div>
</div>
<script>
$('#return_to_node_form_list').click(function(){
	$('#process_node_form_form .epsDialogCloseReload').click();
})
$('#save_node_form').click(function(){
	if(!$('#ProcessDefNodeFormForm').valid()){return false;}
	var json = formToJsonObject('ProcessDefNodeFormForm');
	$.getJSON($('#initPath').val()+'/ProcessDefNodeFormController.do?method=saveProcessDefNodeForm',json, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('#processDefNodeFormGrid').reload();
		$('#process_node_form_form .epsDialogCloseReload').click();
	});
})
</script>