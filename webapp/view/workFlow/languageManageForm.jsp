<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<div class="formLayout form2Pa">
		<form id="languageManageForm" method="post">
			<input type="hidden" name="objId" id="objId" value="<c:out value="${languageManage.objId}"/>"/>
	     	<h4 style="cursor: hand" class="shrink"><span>常用批语</span></h4>
	     	<li class="textarea fullLine">
				<label class="short">审批用语：</label>
				<textarea style="width:  70%;height: 70px;" class="required maxInput" name="content" maxlength="200">${languageManage.content}</textarea>
				<span class="eleRequired">*</span>
			</li>
		</form>
	</div>
	<div class="conOperation">
		<button type="button" id="languageManageSave"><span><spring:message code="globe.save"/></span></button>
		<c:if test="${isShowButon eq null}">
			<button type="button" id="languageManageNext"><span>添加下一条</span></button>
		</c:if>
		<button name="historyBackBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
<script>
var languageManageForm={};
$(document).ready(function(){
	$('#languageManageSave').click(function(){
		languageManageForm.saveLanguageManager('save');
	});
	$("#languageManageNext").click(function(){
		languageManageForm.saveLanguageManager('next');
	});
	languageManageForm.saveLanguageManager = function (op) {
		if(!$('#languageManageForm').valid()){return;}
		$.getJSON($('#initPath').val()+'/LanguageManageController.do?method=saveLanguageManage', formToJsonObject('languageManageForm'), function(json){
			if(json.failure){alert(json.result);return;}
			if (op == 'next') {
				$("[name=content]").val('');
				$("[name=objId]").val('');
				alert('审批用语添加成功，请继续添加审批用语。');
				return false;
			}
			$('[name=historyBackBtn]').click();
		});
	}
});
</script>