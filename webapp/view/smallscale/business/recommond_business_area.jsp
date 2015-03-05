<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<h2>推荐企业商圈</h2>
<ul>
	<c:set var="communityNum" value="0" />
	<c:forEach var="community" items="${recommendCommunityList}">
		<c:if test="${communityNum < 18}">
			<li>
				<h4><a href="javascript:void(0);" onclick="common.goToCommunity('${community.objId}');return false;">${community.communityName}</a></h4>
				<p class="goodsPic"><a href="javascriipt:void(0);" onclick="common.goToCommunity('${community.objId}');return false;"><img src="<c:url value="AttachmentController.do?method=showImg&objId=${community.picture}" />"></a></p>
			</li>
		</c:if>
		<c:set var="communityNum" value="${communityNum+1}" />
	</c:forEach>
</ul>