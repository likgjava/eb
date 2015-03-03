<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var bulletinForm = {};

//审核
bulletinForm.auditBulletin = function(status){
	var bulletin = formToJsonObject('bulletinForm');
	bulletin.auditStatus = status;
	$.getJSON($('#initPath').val()+'/BulletinAgreementController.do?method=saveBulletin', bulletin, function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		BulletinList.getPreBulletinList();
		bulletinForm.closeDiv();
	});
}

//关闭
bulletinForm.closeDiv  = function(){
	$("#auditDivView").find('.epsDialogClose').trigger('click');
}
</script>

<div class="formLayout">
<form:form id="bulletinForm" method="post" modelAttribute="bulletin">
	<span class="hidden" id="bullContents" ></span>
	<input	type="hidden" name="objId" id="objId"	value="<c:out value="${param.objId}"/>" />
	<ul>
		<li><label>采购预告标题：</label>
		<span>${bulletin.bullTitle}</span>
		</li>
	</ul>
	<div>
	${bulletin.bullContents}
	</div>
	<div class="conOperation">
		<button type="button" id="pass" onclick="bulletinForm.auditBulletin('01');"><span>通过</span></button>
		<button type="button" id="nopass" onclick="bulletinForm.auditBulletin('02');"><span>不通过</span></button>
		<button type="button" id="closeDiv" onclick="bulletinForm.closeDiv();"><span>关闭</span></button>
	</div>
</form:form>
</div>

