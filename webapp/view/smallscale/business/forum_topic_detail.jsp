<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<h4>主题信息：</h4>
	<ul>
		<li class="fullLine">
			<label>主题标题：</label>
			${forumTopic.title }
		</li>
		<li>
			<label>所属社区：</label>
			${forumTopic.community.communityName }
		</li>
		<li>
			<label>发表机构：</label>
			${forumTopic.orginfo.orgName }
		</li>
		<li>
			<label>发表人：</label>
			${forumTopic.createUser.emp.name }(${forumTopic.createUser.usName })
		</li>
		<li>
			<label>发表时间：</label>
			<fmt:formatDate value="${forumTopic.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
		</li>
		<li>
			<label>显示状态：</label>
			<c:if test="${forumTopic.isShow == 'true'}">显示</c:if>
			<c:if test="${forumTopic.isShow == 'false'}">禁止显示</c:if>
		</li>
		<li>
			<label>是否置顶：</label>
			<c:if test="${forumTopic.isTop == 'true'}">是</c:if>
			<c:if test="${forumTopic.isTop == 'false'}">否</c:if>
		</li>
		<li class="fullLine">
     		<label>附件：</label>
     		<div id="topicInfoFile" class="uploadFile"></div>
   	    </li>
	</ul>
	<h4>内容描述：</h4>
	<div>${forumTopic.content}</div>
</div>

<div class="conOperation">
	<button id="closeDesk" type="button"><span>关闭</span></button>
</div>

<script>
	//关闭
	$("#closeDesk").click(function(){
		$('.epsDialogClose').trigger('click');
	})
	
	$(document).ready(function(){
		if(${forumTopic.attachment != null && forumTopic.attachment != ''}){
			$('#topicInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&isSelect=false&attachRelaId=${forumTopic.attachment}');
		}
	});
</script>

