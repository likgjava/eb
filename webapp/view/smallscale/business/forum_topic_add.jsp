<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout">
	<form id="forumTopicForm" method="post">
		<input type="hidden" name="objId" id="objId" value="${forumTopic.objId}"/>
		<input type="hidden" name="community.objId" id="community.objId" value="${forumTopic.community.objId}"/>
		<input type="hidden" name="isShow" id="isShow" value="${forumTopic.isShow}"/>
		<input type="hidden" name="isTop" id="isTop" value="${forumTopic.isTop}"/>
		<input type="hidden" name="isElite" id="isElite" value="${forumTopic.isElite}"/>
		<textarea name="content" id="content" class="hidden required" maxlength="4000">${forumTopic.content}</textarea>
		<label>主题：</label>
		<input id="title" name="title" value="${forumTopic.title}" size="45"  class="required"><span class="eleRequired">*</span> 
	</form>
</div>

<label>内容：</label>
<div id="htmlEditor"></div>
<div id="attFileDiv" class="uploadFile">${forumTopic.attachment}</div>

<div class="conOperation">
	<button id="btSave" type="button"><span>发表</span></button>
</div>

<script>
var forumTopicForm={};
var htmlEditor;

$(document).ready(function(){
	//加载附件
	$("#attFileDiv").loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attachment&attachRelaId='+$("#attFileDiv").text());

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
	htmlEditor.setValue($('#content').val()+" ");
});

//提交
$('#btSave').click(function(){	
	$('#content').val(htmlEditor.getValue());
	if($('#isTop').val() == ''){
		$('#isTop').val("false");
		}
	if($('#isShow').val() == ''){
		$('#isShow').val("true");
		}
	if($('#isElite').val() == ''){
		$('#isElite').val("false");
		}
	
	if(!$('#forumTopicForm').valid()){alert('请正确填写表单!');return;}
	
	$.getJSON($('#initPath').val()+'/ForumTopicController.do?method=saveTopic', formToJsonObject('forumTopicForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		alert("发表成功！");
		$('#returnList').click();
	});
});
</script>

