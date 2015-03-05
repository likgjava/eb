<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers formLayout form2Pa">
	<form method="post" name="ProcessDefineForm" id="ProcessDefineForm">
		<input type="hidden" name="objId" value="${processDefine.objId}"/>
		<ul>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefineForm.processDefName"/>：</label>
				<input type="text" name="processDefName" value="${processDefine.processDefName}" maxlength="50" class="required" style="width: 300px;"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefineForm.bizType"/>：</label>
				<input type="text" name="bizType" value="${processDefine.bizType}" class="required" maxlength="100" style="width: 300px;"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefineForm.disposeResultEnum"/>：</label>
				<input type="text" name="disposeResultEnum" value="${processDefine.disposeResultEnum}" maxlength="100" class="required" style="width: 300px;"></input>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefineForm.processType"/>：</label>
                <html:select name="processType" id="processType" code="WORK_FLOW_WAITP_TASK_TYPE" selectedValue="${processDefine.processType}" style="width: 300px;"></html:select>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefineForm.waitpTaskAdapter"/>：</label>
                <html:select name="waitpTaskAdapter" id="waitpTaskAdapter" code="WAITP_TASK_ADAPTER_ENUM" selectedValue="${processDefine.waitpTaskAdapter}" style="width: 300px;"></html:select>
			</li>
			<li class="fullLine">
				<label class="short"><spring:message code="processDefineForm.status"/>：</label>
				启用<input class="checkboxInput"  type="radio" <c:if test="${processDefine.status == '0'}">checked="checked"</c:if> <c:if test="${processDefine.objId == null}">checked="checked"</c:if> name="status" id="status_0" value="0"/>
		                     停用<input class="checkboxInput"  type="radio" <c:if test="${processDefine.status == '1'}">checked="checked"</c:if> name="status" id="status_1" value="1"/>
			</li>
			<li class="fullLine">
				<label class="short">节点名称：</label>
				<input type="text" name="resNodeName" value="" class="required" style="width: 240px;"></input>
				更新为：
				<input type="text" name="tarNodeName" value="" class="required" style="width: 240px;"></input>
				替换
				<input type="radio" name="nodeNameReplaceType" value="1" checked="true"/>
				覆盖
				<input type="radio" name="nodeNameReplaceType" value="2" />
			</li>
			<li class="fullLine">
				<label class="short">处理URL：</label>
				<input type="text" name="resNodeUrl" value="" class="required" style="width: 240px;"></input>
				更新为：
				<input type="text" name="tarNodeUrl" value="" class="required" style="width: 240px;"></input>
				替换
				<input type="radio" name="nodeUrlReplaceType" value="1" checked="true"/>
				覆盖
				<input type="radio" name="nodeUrlReplaceType" value="2" />
			</li>
		</ul>
	</form>
	<div class="conOperation">
		<button name="copy_process_define" type="button" tabindex="18"><span>复制流程</span></button>
		<button name="close_copy_process_define_form" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script>
$('[name=copy_process_define]').click(function(){
	var json = formToJsonObject('ProcessDefineForm');
	$.getJSON($('#initPath').val()+'/ProcessDefineTemplateController.do?method=copyProcessDefineTemp',json, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$("#processDefineGrid").reload();
		$('[name=close_copy_process_define_form]').click();
	});
})
$('[name=close_copy_process_define_form]').click(function(){
	$('#copy_process_define .epsDialogCloseReload').click();
})


</script>