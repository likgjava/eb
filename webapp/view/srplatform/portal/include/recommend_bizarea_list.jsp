<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
//跳转到商圈
loadToshangquan = function(groupId){
	window.open("http://qun.qq.com/air/#"+groupId+"/bbs");
}
</script>

<div class=''>

<ul  class="verticalShowList">
	<c:forEach var="community" items="${communityList}">
     <li style="width:110px;margin-bottom:0px;">
       <h3 style="padding-top:75px;"><a href="javascript:void(0);" title="${community.communityName}" onclick="common.goToCommunity('${community.objId }'); return false;">
            	<c:choose>
			  		<c:when test="${fn:length(community.communityName)> 6 }">${fn:substring(community.communityName,0,6) }…</c:when>
			  		<c:otherwise>${community.communityName}</c:otherwise>
				</c:choose>
       </a></h3>
       <p class="hotImg" style="width:100px;height:75px;"><a  href="javascript:void(0);" onclick="common.goToCommunity('${community.objId }'); return false;"><img style="width: 100px;height: 75px;" src="AttachmentController.do?method=showImg&objId=${community.picture}"></a></p>
     </li>
     </c:forEach>
</ul>
</div>

