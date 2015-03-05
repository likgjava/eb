<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout form2Pa">
	<h4>主题信息：</h4>
	<ul>
		<li>
			<label>主题标题：</label>
			${forumReply.topic.title }
		</li>
		<li>
			<label>所属社区：</label>
			${forumReply.topic.community.communityName }
		</li>
		<li>
			<label>主题发表机构：</label>
			${forumReply.topic.orginfo.orgName }
		</li>
		<li>
			<label>发表时间：</label>
			<fmt:formatDate value="${forumReply.topic.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
		</li>
	</ul>
	<h4>帖子信息：</h4>
	<div class="k1">
		<c:choose>
			<c:when test="${forumReply.attachment != null && forumReply.attachment != ''}">
				<div class="img_250_1" id="newPreview">
					<img id="view"  src="AttachmentController.do?method=showImg&objId=${forumReply.attachment}" width="200px" ></img>
				</div>
			</c:when>
			<c:otherwise>
				<img width="200" height="175" src="<c:url value="/view/resource/skin/bizplatform/img/orginfo_add.gif"/>"></img>
			</c:otherwise>
		</c:choose>
	</div>
	<ul>
		<li class="fullLine left">
			<label>帖子发表机构：</label>
			${forumReply.orginfo.orgName }
		</li>
		<li class="fullLine left">
			<label>帖子发表人：</label>
			${forumReply.rateName}(${forumReply.createUser.usName})
		</li>
		<li class="fullLine left">
			<label>帖子显示状态：</label>
			<c:if test="${forumReply.isShow == 'true'}">显示</c:if>
			<c:if test="${forumReply.isShow == 'false'}">禁止显示</c:if>
		</li>
		<li class="fullLine left">
			<label>帖子发表时间：</label>
			<fmt:formatDate value="${forumReply.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
		</li>
	</ul>
	<h4>帖子内容描述：</h4>
	<div>${forumReply.content }</div>
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
		if(${forumReply.attachment != null && forumReply.attachment != ''}){
			$('#topicReplyInfoFile').loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=file&isSelect=false&attachRelaId=${forumReply.attachment}');
		}
	});
</script>

