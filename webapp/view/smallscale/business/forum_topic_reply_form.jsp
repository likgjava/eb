<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
		
<div style="width:100%;margin-top:10px;">
	<form id="forumReplyFrom" name="forumReplyFrom" method="post" enctype="multipart/form-data">
		<input type="hidden" name="topic.objId" id="topicIdForm" value = "${forumTopic.objId }"/>
		<input type="hidden" name="isShow" id="isShow" value="true"/>
		<div>
			<label>回复内容：</label>
			<div id="htmlEditor"></div>
			<textarea name="content" id="content" class="hidden"></textarea>
		</div>
		<label>附件：</label>
		<input name="attachmentFile" type="file" id="attachmentFile" size="40"/>
		<div class="conOperation">
			<button id="btReply" type="button" style=""><span>发表</span></button>
		</div>
	</form>		
</div>


<script>
var htmlEditor;

//提交回复
$("#btReply").click(function(){
	//判断是否登录
	if(common.isLogin(true)){
		
		//判断当前机构是否拥有'商圈会员'角色
		var jsonObj=$.ajax({url:$("#initPath").val()+"/ForumTopicShowController.do?method=getCurrentUserIsBizMember",data:{}, async: false }).responseText
		jsonObj = eval("("+jsonObj+")");
		if(jsonObj.isBizMember !="true"){
			alert("你还没有订阅'商圈会员'服务,请先订阅商圈会员服务!");
			return false;
		}
		
		$('#content').val(htmlEditor.getValue());
		var content = $('#content').val();
		var url = $('#initPath').val()+'/ForumReplyController.do?method=saveTopicReply';
		if(!$('#forumReplyFrom').valid()){alert('请正确填写表单!');return;}
		if(content==null||""==content){
			alert("请做出评论再提交！");
			return false;
		}else{
			$('#forumReplyFrom').ajaxSubmit({
				url:url,
				dataType:'json',
				success:function(json){
					alert("提交成功");
					forumTopicReplyForm.replyReload();
					$("#topicReplyFormDiv").loadPage($("#initPath").val()+"/view/smallscale/business/forum_topic_reply_form.jsp");
				},
				error:function(msg){
					alert(JSON.stringify(msg));
				}
			});
		}
	}
})

$(document).ready(function(){
	var topicId = $('#topicReId').val();
	$('#topicIdForm').val(topicId);
	
	//加载ExtJs的HTML编辑器
	htmlEditor = new Ext.form.HtmlEditor({
		height: 220,
		anchor: '100%'
	});
	new Ext.panel.Panel({
	    renderTo: 'htmlEditor',
	    layout: 'anchor',
	    border : false,
	    items: [htmlEditor]
	});
	htmlEditor.setValue(" ");
});
</script>
