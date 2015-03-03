<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>


<input id="recordId" name="recordId" type="hidden" value="${param.recordId }">

<div class="orderManagement" id="goodsEvaluateListDiv">
	<table class="condiscusstable" id="chatTable" width="100%">
		<c:forEach var="biddingChat" items="${biddingChatList}">
			<tr><th>
			&nbsp;<fmt:formatDate value="${biddingChat.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<em class="tittlediscussem">${biddingChat.sayOrgInfo.orgName}</em>：  ${biddingChat.content}
			</th></tr>
		</c:forEach>
		
		
		<c:if test="${fn:length(biddingChatList) ==0 }">
			<tr align="center"><th >无聊天记录</th></tr>
		</c:if>
	</table>
</div>

<div class="conOperation">
	<button onclick="chatList.closeDiv()"><span>关闭</span></button>
</div>

<script type="text/javascript">
var chatList = {};
//关闭
chatList.closeDiv = function(){
	$("#chatRecordDiv").find(".epsDialogClose").click();
}
</script>