<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers formLayout form2Pa">
	<form method="post" name="ProcessDefineForm" id="ProcessDefineForm"  action="#">
		<input type="hidden" name="objId" id="objId" value="${processDefine.objId}"/>
		<ul>
			<li class="fullLine">
				<label><spring:message code="processDefineForm.processDefName"/>：</label>
				<input type="text" name="processDefName" value="${processDefine.processDefName}" maxlength="50" class="required" style="width: 300px;"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label><spring:message code="processDefineForm.bizType"/>：</label>
				<input type="text" name="bizType" value="${processDefine.bizType}" class="required" maxlength="100" style="width: 300px;"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label><spring:message code="processDefineForm.disposeResultEnum"/>：</label>
				<input type="text" name="disposeResultEnum" value="${processDefine.disposeResultEnum}" maxlength="100" class="required" style="width: 300px;"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label><spring:message code="processDefineForm.processType"/>：</label>
                <html:select name="processType" id="processType" code="WORK_FLOW_WAITP_TASK_TYPE" selectedValue="${processDefine.processType}" style="width: 300px;"></html:select>
			</li>
			<li class="fullLine">
				<label><spring:message code="processDefineForm.waitpTaskAdapter"/>：</label>
                <html:select name="waitpTaskAdapter" id="waitpTaskAdapter" code="WAITP_TASK_ADAPTER_ENUM" selectedValue="${processDefine.waitpTaskAdapter}" style="width: 300px;"></html:select>
			</li>
			<li class="fullLine">
				<label><spring:message code="processDefineForm.status"/>：</label>
				启用<input class="checkboxInput"  type="radio" <c:if test="${processDefine.status == '0'}">checked="checked"</c:if> <c:if test="${processDefine.objId == null}">checked="checked"</c:if> name="status" id="status_0" value="0"/>
		                     停用<input class="checkboxInput"  type="radio" <c:if test="${processDefine.status == '1'}">checked="checked"</c:if> name="status" id="status_1" value="1"/>
			</li>
		</ul>
	</form>
	<div class="conOperation">
		<button name="submit" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
		<button name="historyBackBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script>
//提交
$("*[name=submit]").click(function(){
	if(!$('#ProcessDefineForm').valid()){return;}
	$.getJSON($('#initPath').val()+'/ProcessDefineTemplateController.do?method=save', formToJsonObject('ProcessDefineForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('[name=historyBackBtn]').click();
	});
});
</script>