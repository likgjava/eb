<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout">
<form:form id="bulletinForm" method="post" modelAttribute="bulletin">
	<span class="hidden" id="bullContents"></span>
	<input	type="hidden" name="objId" id="objId"	value="<c:out value="${param.objId}"/>" />
	<ul>
		<li><label>采购预告标题：</label>
		<input name="bullTitle" id="bullTitle" class="required" value="${bulletin.bullTitle}" style="width:400px;"
		onkeyup="{if($(this).val()!=null) $('#number').html($(this).val().length+'/50')}" />
		<span class="eleRequired">*</span>
		<span id="number" class="eleRequired">0/50</span>
		</li>
	</ul>
	<textarea id="bulletinContent" class="hidden">${bulletin.bullContents}</textarea>
</form:form>
</div>

<div id="htmlEditor"></div>

<div class="conOperation">
	<button type="button" id="bulletinSaveBtn"><span><spring:message code="globe.save" /></span></button>
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return" /></span></button>
</div>


<script>
var bulletinForm = {};
var htmlEditor;

$(document).ready(function(){
	$('#number').html($('#bullTitle').val().length+'/50');
	
	//表单验证
	$('#bulletinForm').validate();

	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 400,
		anchor: '100%'
	});
	new Ext.panel.Panel({
	    renderTo: 'htmlEditor',
	    layout: 'anchor',
	    border : false,
	    items: [htmlEditor]
	});
	htmlEditor.setValue($('#bulletinContent').val()+" ");

	//返回
	$("#reBackBtn").click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	})
	
	//保存
	$("#bulletinSaveBtn").click(function(){
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		if($("#bullTitle").val().length>50){alert('预告标题不能超过设定长度!');return;}
		var bulletin = formToJsonObject('bulletinForm');
		bulletin.bullType = "00";//采购预告
		bulletin.auditStatus = "01";//管理员发布的审核状态直接是已经审核
		bulletin.bullContents = htmlEditor.getValue();
		$.getJSON($('#initPath').val()+'/BulletinAgreementController.do?method=saveBulletin', bulletin, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$('button[name=historyBackBtn]').click();
		});
	});
})
</script>