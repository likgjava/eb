<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
function loadBizPage() {
	$('#auditOpinionForm input[type=checkbox][name=saveAuditLanguage]').attr('checked',false);
	var connectChar = "";
	if ($('#resLink').val().indexOf('.do')>0 && $('#resLink').val().indexOf('?')>0) {
		connectChar = "&";
	} else {
		connectChar = "?"
	}
	var url = $('#initPath').val() + '/';
	url += $('#resLink').val();
	url += connectChar;
	url += 'bizId='+$('[name=nodeParam]').find('[name=bizId]').val();
	url += '&bizType='+$('[name=nodeParam]').find('[name=bizType]').val();
	url += $('[name=nodeParam]').find('[name=requestStr]').val();
	url += '&relatingId='+$('#relatingId').val();
	$('#audit_content_body_div').empty().loadPage(url,{},function(){
		$('#audit_content_body_div').find('button.remove').remove();
			if ($('#audit_content_body_div').find('button').length >0) {
			$('#workFlowButton').remove();
			$('#auditOpinionForm').remove();
		}
		if ($('#extendParamUrl').val()) {
			var connectChar = "";
			if ($('#extendParamUrl').val().indexOf('.do')>0 && $('#extendParamUrl').val().indexOf('?')>0) {
				connectChar = "&";
			} else {
				connectChar = "?"
			}
			var url = $('#initPath').val() + '/';
			url += $('#extendParamUrl').val();
			url += connectChar;
			url += 'bizId='+$('[name=nodeParam]').find('[name=bizId]').val();
			url += '&bizType='+$('[name=nodeParam]').find('[name=bizType]').val();
			url += $('[name=nodeParam]').find('[name=requestStr]').val();
			$('#extendParamDiv').empty().loadPage(url);
		}
	});
}
$(document).ready(function(){
	$('#extendParamDiv').hide();
	loadBizPage();
	$('#return_to_my_desktop').click(function(){
		if (undefined != $('#returnToList').val() && null != $('#returnToList').val() && '' != $('#returnToList').val() && 'true' == $('#returnToList').val()) {
			sysTools_showSub();
			$('#conBody').empty().loadPage($("#returnUrl").val());
		} else {
			$('#myDesktop').click();
		}
	})
	$('.conOperation button[id=processDefNodeFormButton]').click(function(){
		$(this).attr('disabled',true);
		if (confirm('确认审核 '+$(this).find('span').html()+' 吗？')) {
			changeWorkFlowInstance($(this));
		}
		$(this).attr('disabled',false);
	})
	$('#opinion').click(function(){
		if (this.value == '审核意见：') {
			this.value = '';
		}
	})
	$('#languageManage').change(function(){
		$('#opinion').val($(this).find('option[selected=true]').text());
	})
	$('#languageManage').prepend('<option>请选择常用审批用语...</option>').find('option:first').attr('selected',true);
	
	function changeWorkFlowInstance($this){
		if(!$('#auditOpinionForm').valid()){return false;}
		var opinion = $('[name=opinion]').val();
		if (opinion == '审核意见：' || '' == opinion){
			opinion = "";
		}

		var onSubmitMethodName = $this.attr('onSubmitMethodName');
		if (onSubmitMethodName){
			if (!eval(onSubmitMethodName)) {
				return false;
			}
		}
		
		var checkboxNum = $('#audit_content_body_div input[type=checkbox].childProcessAudit').length;
		//var checkboxCheckNum = $('#audit_content_body_div input[type=checkbox][checked=true].childProcessAudit').length;
		var checkboxCheckNum = 0;
		$('#audit_content_body_div input[type=checkbox].childProcessAudit').each(function(){
			if ($(this).attr('checked')) {
				checkboxCheckNum ++;
			}
		})
		var isSaveAuditLanguage = $('#auditOpinionForm input[type=checkbox][name=saveAuditLanguage]').attr('checked');
		if (true == isSaveAuditLanguage) {
			if (opinion) {
				$('#languageManage').append('<option>'+opinion+'</option>');
			}
		}
		if (checkboxNum == 0) {
			var obj = formToJsonObject('nodeParam');
			obj.nodeType = $this.attr('formPropertyOperate');
			obj.auditOpinion = opinion;
			obj.nodeFormId = $this.attr('objId');
			obj.nodeId = $this.attr('nodeId');
			obj.disposeResult = $this.attr('disposeResult');
			obj.auditResult = $this.attr('auditResult');
			obj.isTakeBack = $this.attr('isTakeBack');
			obj.extendParam = '';
			var formJson = formToJsonObject('extendParamForm');
			for (var key in formJson) {
				obj.extendParam += '&'+key+'='+formJson[key];
			}
			var formJson = formToJsonObject('auditContentBodyForm');
			for (var key in formJson) {
				obj.extendParam += '&'+key+'='+formJson[key];
			}
			//obj.nextNodeNo = $this.attr('processNodeNo');
			obj.saveAuditLanguage = isSaveAuditLanguage;
			$.getJSON($('#initPath').val()+'/WorkFlowController.do?method=changeWorkFlowInstance',obj, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#return_to_my_desktop').click();
			});
		} else {
			if (checkboxCheckNum == 0) {
				alert('请选择需要审批的条目!');
				return false;
			}
			var isParamEfficiency = true;
			var nodeParams = new Array();
			$('#audit_content_body_div input[type=checkbox].childProcessAudit').each(function(){
				if ($(this).attr('checked')) {
					var nodeParam = formToJsonObject('nodeParam');
					nodeParam.nodeType = $this.attr('formPropertyOperate');
					nodeParam.disposeResult = $this.attr('disposeResult');
					nodeParam.auditResult = $this.attr('auditResult');
					nodeParam.isTakeBack = $this.attr('isTakeBack');
					nodeParam.auditOpinion = opinion;
					nodeParam.nodeFormId = $this.attr('objId');
					nodeParam.extendParam = '';
					var formJson = formToJsonObject('extendParamForm');
					for (var key in formJson) {
						nodeParam.extendParam += '&'+key+'='+formJson[key];
					}
					nodeParam.bizId = $(this).attr('bizId');
					nodeParam.bizType = $(this).attr('bizType');
	
					var extendParamObject = {};
					$(this).parent().parent().find('input,textarea,select').each(function(){
						if ($(this).attr('name')) {
							if(this.type == 'select-multiple'){
								var select_multiple = new Array();
								$(this.options,function(){
									if (this.selected) {
										select_multiple.push(this.value);
									}
								})
								extendParamObject[$(this).attr('name')] = select_multiple.toString();
							}
							if (this.type == 'checkbox') {
								if (this.checked) {
									if (extendParamObject[$(this).attr('name')]) {
										extendParamObject[$(this).attr('name')] += ","+this.value;
									} else {
										extendParamObject[$(this).attr('name')] = this.value;
									}
								}
							}
							if (this.type == 'radio') {
								if (this.checked) {
									extendParamObject[$(this).attr('name')] = this.value;
								}
							}
							if (this.type == 'hidden') {
								extendParamObject[$(this).attr('name')] = this.value;
							}
							if (this.type == 'password') {
								extendParamObject[$(this).attr('name')] = this.value;
							}
							if (this.type == 'select-one') {
								extendParamObject[$(this).attr('name')] = $(this).val();
							}
							if (this.type == 'textarea') {
								extendParamObject[$(this).attr('name')] = this.value;
							}
							if (this.type == 'text') {
								extendParamObject[$(this).attr('name')] = this.value;
							}
						}
					})
					
					if (!nodeParam.bizId) {
						isParamEfficiency = false;
					}
					if (!nodeParam.bizType) {
						isParamEfficiency = false;
					}
					if (!isParamEfficiency) {
						return false;
					}
	
					for (var key in extendParamObject) {
						nodeParam.extendParam += '&'+key+'='+extendParamObject[key];
					}
					nodeParams.push(nodeParam);
				}
			})
			if (!isParamEfficiency) {
				alert('获取流程参数异常,请检查复选框是否含有[bizId,bizType]');
				return false;
			}
			if (nodeParams.length == 0) {
				alert('请选择需要审批的条目!');
				return false;
			}
			$.getJSON($('#initPath').val()+'/WorkFlowController.do?method=changeWorkFlowInstance',{'nodeParamStr':JSON.stringify(nodeParams),'saveAuditLanguage':isSaveAuditLanguage}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				if (checkboxNum == checkboxCheckNum) {
					$('#return_to_my_desktop').click();
				} else {
					loadBizPage();
				}
			});
		}
	}

	if (true == $('#isShowNodeExtendParam').val() || 'true' == $('#isShowNodeExtendParam').val()) {
		$('#extendParamDiv').show();
	} else {
		$('#extendParamDiv').hide();
	}
})
//
</script>
<div>
	<form id="nodeParam" name="nodeParam">
		<input type="hidden" name="bizType"  value="${nodeParam.bizType}"/>
		<input type="hidden" name="bizId"  value="${nodeParam.bizId}"/>
		<input type="hidden" name="requestStr"  value="${nodeParam.requestStr}"/>
		<input type="hidden" id="resLink" value="${node.resLink}" />
		<input type="hidden" id="extendParamUrl" value="${node.extendParamUrl}" />
		<input type="hidden" id="relatingId" value="${nodeParam.relatingId}" />
		<input type="hidden" id="isShowNodeExtendParam" value="${isShowNodeExtendParam}" />
		<input type="hidden" id="returnToList" value="${param.returnToList}" />
	</form>
</div>
<form id="auditContentBodyForm" name="auditContentBodyForm">
	<div id="audit_content_body_div"></div>
</form>
<form id="extendParamForm" name="extendParamForm">
	<div id="extendParamDiv" class="hidden"></div>
</form>
<div class="partContainers">
	<form id="auditOpinionForm">
		<div class="formLayout form2Pa">
		<ul>
			<li class="fullLine">
				<label class="short">常用审批用语：</label>
				<select id="languageManage" style="min-width: 400px;width:50%;">
					<c:forEach items="${languageManages}" var="languageManage">
						<option>${languageManage.content}</option>
					</c:forEach>
				</select>
				<input type="checkbox" name="saveAuditLanguage" value="true"/><span>保存审批语</span>
			</li>
			<li class="fullLine">
				<textarea name="opinion" id="opinion" style="width: 80%;height: 45px;" maxlength="200" class="maxInput">审核意见：</textarea><span class="eleRequired"></span>
			</li>
		</ul>
		</div>
	</form>
	<div class="conOperation" id="workFlowButton">
		<c:forEach items="${processDefNodeForms}" var="processDefNodeForm">
			<c:if test="${true == processDefNodeForm.isShowAuditButton}">
				<c:if test="${node.auditType == '0'}">
					<c:if test="${processDefNodeForm.formPropertyOperate == 'viewNode'}">
						<button id="processDefNodeFormButton" type="button" tabindex="19" processNodeNo="${processDefNodeForm.processNodeNo}" formPropertyOperate="${processDefNodeForm.formPropertyOperate}" objId="${processDefNodeForm.objId}" onSubmitMethodName="${processDefNodeForm.onSubmit}" disposeResult="${processDefNodeForm.disposeResult}" auditResult="${processDefNodeForm.auditResult}" isTakeBack="${processDefNodeForm.isTakeBack}" nodeId="${nodeParam.nodeId}"><span>${processDefNodeForm.formPropertyName}</span></button>
					</c:if>
				</c:if>
				<c:if test="${node.auditType != '0'}">
					<c:if test="${processDefNodeForm.formPropertyOperate != 'viewNode'}">
						<button id="processDefNodeFormButton" type="button" tabindex="19" processNodeNo="${processDefNodeForm.processNodeNo}" formPropertyOperate="${processDefNodeForm.formPropertyOperate}" objId="${processDefNodeForm.objId}" onSubmitMethodName="${processDefNodeForm.onSubmit}" disposeResult="${processDefNodeForm.disposeResult}" auditResult="${processDefNodeForm.auditResult}" isTakeBack="${processDefNodeForm.isTakeBack}" nodeId="${nodeParam.nodeId}"><span>${processDefNodeForm.formPropertyName}</span></button>
					</c:if>
				</c:if>
			</c:if>
		</c:forEach>
		<button id="return_to_my_desktop" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
