<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="mainsub">
	<h1>
		<span>主题：${forumTopic.title}</span>
		<span>
			<c:if test="${forumTopic.isElite == 'true'}">
				<img src="view/resource/skin/smallscale/img/jing_icon.png"/>
			</c:if>
		</span>
		<span><input type="hidden" id="topicReId" value="${forumTopic.objId}"/></span>
	</h1>
</div>
<div class="maincont">
	<div class="main_l">     
		<div class="mainbox">
			<table>
				<tbody>
					<tr>
						<td class="td_line1">
							<div class="mybbs">
								<div class="myPic">
									<span>
										<a target="_blank" onclick="forumTopicReplyForm.showOrgDetail('${forumTopic.orginfo.objId}','${forumTopic.orginfo.buyerId}','${forumTopic.orginfo.supplierId}')"><img height="60" width="60" style="cursor: pointer;" border="0" alt="${forumTopic.orginfo.orgName }" src="AttachmentController.do?method=showImg&objId=${forumTopic.orginfo.logo }"></a>
									</span>
								</div>
								<div class="myInfo">
									<div class="myInfo_up">
										<a target="_blank" onclick="forumTopicReplyForm.showOrgDetail('${forumTopic.orginfo.objId }','${forumTopic.orginfo.buyerId}','${forumTopic.orginfo.supplierId}')">${forumTopic.orginfo.orgName }</a>&nbsp;&nbsp; &nbsp; &nbsp;
										<font style="color: gray;">发表于：<fmt:formatDate value="${forumTopic.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></font>
									</div>
									<div class="myInfo_dw">
										发帖： ${orgReplyNum} &nbsp; &nbsp;精华：${orgTopNum}&nbsp; &nbsp;注册日期：<fmt:formatDate value="${forumTopic.orginfo.createTime}" pattern="yyyy-MM-dd"/>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="overflow: hidden;">
							<div class="mybbs_cont"> 
								<span style="float: right;" id="topicReplyRowNum">回复&nbsp;<font color="#ff0000">${replyTopicCount}</font>&nbsp;次 </span><br>
								<div class="cont">${forumTopic.content}</div>
								<br>
								<c:if test="${fn:length(attachmentList) > 0}">
									<c:forEach var="attachment" items="${attachmentList}" varStatus="stauts">
									附件${stauts.count }：<a href="javascript:void(0)" onclick="forumTopicReplyForm.downFile('${attachment.objId}')">${attachment.viewName }(<fmt:formatDate value="${attachment.uploadTime }" pattern="yyyy-MM-dd HH:mm:ss"/>)</a><br>
						  	    	</c:forEach>
								</c:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="showTopicReplyDiv">
		</div>
	</div>
</div>
<div id="topicReplyFormDiv">
	<%@ include file="/view/smallscale/business/forum_topic_reply_form.jsp" %>
</div>

<script type="text/javascript">
var forumTopicReplyForm = {}
var show_list = {}

//分页跳转
show_list.makeSearchData = function(){
	forumTopicReplyForm.replyReload();
}
//回帖列表
forumTopicReplyForm.replyReload = function(){
	forumTopicReplyForm.search = "";
	//分页信息
	if($('#rp') && $('#rp').val()==null){
		forumTopicReplyForm.search += "&rp=25";
		forumTopicReplyForm.search += "&page=1";
	}else{
		forumTopicReplyForm.search += "&rp="+$('#rp').val();
		forumTopicReplyForm.search += "&page="+$('#page').val();
	}
	
	//当前主题Id
	forumTopicReplyForm.search += "&topicId="+$('#topicReId').val();

	//加载
	$("#showTopicReplyDiv").empty().loadPage($("#initPath").val()+"/ForumTopicShowController.do?method=toTopicReplyList"+forumTopicReplyForm.search);
}

//下载附件
forumTopicReplyForm.downFile = function(attachmentId){
	window.open($('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+attachmentId);
}

//显示采购人或供应商详情
forumTopicReplyForm.showOrgDetail = function(orgInfoId, buyerId, supplierId) {
	if(orgInfoId==null || orgInfoId==""){return ;}
	if(buyerId!=null && buyerId!=""){
		common.geToBuyerDetail(buyerId);
	}else{
		common.goToOrgShop(orgInfoId);
	}
}

$(document).ready(function(){
	forumTopicReplyForm.replyReload();
});

</script>