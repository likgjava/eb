<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="mainbox">
	<input type="hidden" id="pageTotalRowNum" value="${PAGERESULT.totalRowNum}"/>
	<c:forEach var="topicInfo" items="${PAGERESULT.data}" varStatus="status">
		<table width="100%" border="0">
			<tbody>
				<tr>
					<td class="td_line1">
						<div class="mybbs">
							<div class="myPic">
								<span>
									<a target="_blank" onclick="forumTopicReplyForm.showOrgDetail('${topicInfo[0]}','${topicInfo[10]}','${topicInfo[11]}')"><img height="60" width="60" border="0" style="cursor:pointer; " alt="${topicInfo[2] }" src="AttachmentController.do?method=showImg&objId=${topicInfo[1] }"></a>
								</span>
							</div>
							<div class="myInfo">
								<div class="myInfo_up">
									<a target="_blank" onclick="forumTopicReplyForm.showOrgDetail('${topicInfo[0]}','${topicInfo[10]}','${topicInfo[11]}')">${topicInfo[2] }</a>&nbsp;&nbsp; &nbsp; &nbsp;
									<font style="color: gray;">发表于：<fmt:formatDate value="${topicInfo[5] }" pattern="yyyy-MM-dd HH:mm:ss"/></font>&nbsp;&nbsp;
								</div>
								<div class="myInfo_dw"> 
									发帖： ${topicInfo[7]} &nbsp; &nbsp;精华：${topicInfo[8]}&nbsp; &nbsp;注册日期：<fmt:formatDate value="${topicInfo[3] }" pattern="yyyy-MM-dd"/>
								</div>
							</div>
							<div class="myStair">
								<strong onclick="">${(page-1)*PAGERESULT.pageSize + status.count}楼</strong>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="overflow: hidden;">
						<div class="mybbs_cont mybbs_cont1"> 
							<div class="cont">${topicInfo[6]}</div>
							<br>
							<c:if test="${!empty topicInfo[9]}">
								附件：<a href="javascript:void(0)" onclick="	forumTopicReplyDiv.downFile('${topicInfo[9]}')">${topicInfo[12]}</a>
							</c:if>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</c:forEach>
</div>
<div>
	<%@ include file="/view/pubservice/common/pageDirection.jsp" %>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var pageTotalRowNum = $('#pageTotalRowNum').val();
		$('#topicReplyRowNum').html("回复&nbsp;<font color='#ff0000'>"+pageTotalRowNum+"</font>&nbsp;次");

		//若没有数据则提示"还没有回复数据"
		if(pageTotalRowNum == '0'){
			$('span[class=l]').html("还没有回复数据!");
		}
	});

	var forumTopicReplyDiv = {};
	//下载附件
	forumTopicReplyDiv.downFile = function(attachmentId){
		window.open($('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+attachmentId);
	}
</script>