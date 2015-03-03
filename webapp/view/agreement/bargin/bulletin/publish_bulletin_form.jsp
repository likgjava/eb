<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<div class="formLayout">
<form:form id="bulletinForm" method="post" modelAttribute="bulletin">
	<input type="hidden" id="bullContents" value="${bulletin.bullContents }"/>
	<form:hidden path="objId" />
	<input	type="hidden" name="project.objId" id="projectId"	value="<c:out value="${param.projectId}"/>" />
	<input	type="hidden" name="project.projProcessStatus" id="project.projProcessStatus"	value="${bulletin.project.projProcessStatus}" />
	<input	type="hidden" name="auditStatus" id="auditStatus"	value="00" />
	<input	type="hidden" name="bullType" id="bullType"	value="${bulletin.bullType }" />
	<ul>
		<li><label>采购预告标题：</label>
		<input name="bullTitle" id="bullTitle" class="required" value="${bulletin.bullTitle}" style="width:400px;"
		onkeyup="{if($(this).val()!=null) $('#number').html($(this).val().length+'/50')}" />
		<span class="eleRequired">*</span>
		<span id="number" class="eleRequired">0/50</span>
		</li>
		<li><label>所属项目：</label> ${project.projName}</li>
	</ul>
</form:form>
</div>

<div>
	<h4 style="background-color: #CCE2F8;"><span>采购预告内容：</span></h4>
	<div id="htmlEditor"></div>
</div>

<div class="conOperation">
	<button type="button" name="bulletinSaveBtn" id="save"><span><spring:message code="globe.save" /></span></button>
	<button type="button" name="bulletinSaveBtn" id="publish"><span>发布</span></button>
	<button type="button" name="historyBackBtn"><span><spring:message code="globe.return" /></span></button>
</div>

<script>
var bulletinForm = {};
var htmlEditor;

$(document).ready(function(){
	
	//表单验证
	$('#bulletinForm').validate();
	
	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 350,
		anchor: '100%'
	});
	new Ext.panel.Panel({
	    renderTo: 'htmlEditor',
	    layout: 'anchor',
	    border : false,
	    items: [htmlEditor]
	});
	htmlEditor.setValue($("#bullContents").val()+" ");

	//保存
	$("button[name=bulletinSaveBtn]").click(function(){
		if($(this).attr("id")=="publish"){//发布
			if($('input[name=project.projProcessStatus]').val()=="100"||$('input[name=project.projProcessStatus]').val()=="20"){
				$('input[name=project.projProcessStatus]').val("160");
			}else{
				$('input[name=project.projProcessStatus]').val("230");
			}
			$('input[name=auditStatus]').val("01");
		}
		$("#bullContents").val(htmlEditor.getValue());
		if(!$('#bulletinForm').valid()){alert('请正确填写表单!');return;}
		if($("#bullTitle").val().length>50){alert('预告标题不能超过设定长度!');return;}
		var bulletin = formToJsonObject('bulletinForm');
		if(!$("#bullType").val()){bulletin.bullType = "01";}
		bulletin.bullContents = $('#bullContents').val();

		//屏蔽按钮，以免重复提交
		$("button[name=bulletinSaveBtn]").attr("disabled","disabled");
		$.getJSON($('#initPath').val()+'/BulletinAgreementController.do?method=saveBulletin', bulletin, function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$("button[name=bulletinSaveBtn]").removeAttr("disabled");
			$('button[name=historyBackBtn]').click();
		});
	});
})
</script>