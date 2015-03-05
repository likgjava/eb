<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<table>
	<tr>
		<th>精选话题</th>
		<th>发布人</th>
		<th>所属社区</th>
		<th>回复次数</th>
		<th>发布时间</th>
	</tr>
	<c:forEach var="eliteTopic" items="${newEliteTopicList.data}">
		<tr>
			<td><a href="javascript:void(0);" onclick="common.goToCommunity('${eliteTopic.community.objId}','${eliteTopic.objId}');return false;"> 
			<c:choose>
				<c:when test="${fn:length(eliteTopic.title) > 11}">${fn:substring(eliteTopic.title,0,11)}… </c:when>
				<c:otherwise>${eliteTopic.title}</c:otherwise>
			</c:choose>
			</a></td>
			<td><a href="javascript:void(0);" onclick="common.goToOrgShop('${eliteTopic.orginfo.objId}');return false;"> 
			<c:choose>
				<c:when test="${fn:length(eliteTopic.orginfo.orgName) > 11}">${fn:substring(eliteTopic.orginfo.orgName,0,11)}… </c:when>
				<c:otherwise>${eliteTopic.orginfo.orgName}</c:otherwise>
			</c:choose>
			</a></td>
			<td><a href="javascript:void(0);" onclick="common.goToCommunity('${eliteTopic.community.objId}');return false;">
			<c:choose>
				<c:when test="${fn:length(eliteTopic.community.communityName) > 6}">${fn:substring(eliteTopic.community.communityName,0,6)}… </c:when>
				<c:otherwise>${eliteTopic.community.communityName}</c:otherwise>
			</c:choose>
			</a></td>
			<td id="${eliteTopic.objId}" class="center">0</td>
			<script>
				var eliteTopicId = '${eliteTopic.objId}' ;
				//添加回帖数
				$.getJSON($("#initPath").val()+"/ForumTopicShowController.do?method=countReplyTopicNum",{"objId":eliteTopicId},function(json){
					if(json.replyTopicCount){
						$('#${eliteTopic.objId}').empty().append(json.replyTopicCount);
					}else{
						$('#${eliteTopic.objId}').empty().append("0");
					}
				});
			</script>
			<td class="center"><fmt:formatDate value="${eliteTopic.updateTime}" pattern="MM-dd HH:mm"/></td>
		</tr>
	</c:forEach>
</table>
<script>
	function loadToTopicReplyList(topicId){
		$('#shangquanCommunityInfo').loadPage($('#initPath').val()+"/ForumTopicShowController.do?method=toTopicShow&objId="+topicId+"&skipType=unfurlTopic");
	}
</script>