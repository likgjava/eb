<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="list-content">
	<ul class="list-view-enlarge">
		<c:forEach var="gift" items="${PAGERESULT.data}">
			<li class="list-item hlisting sell">
				<h3 class="summary"><a href="javascript:void(0);" onclick="gift_list.showDetail('${gift.objId}');return false;">${gift.giftName}</a></h3>
				<div class="photo"><a href="javascript:void(0);" onclick="gift_list.showDetail('${gift.objId}');return false;"><img src="<c:url value="AttachmentController.do?method=showImg&objId=${gift.picture}" />"></a></div>
				<ul class="attribute">
					<li><img src="view/resource/skin/sysicon/16/star.png">&nbsp;兑换人数:${gift.virtualRecordQty+gift.realRecordQty}[${gift.totalEvalQty}人评论]<a href="javascript:void(0);" onclick="gift_list.showDetail('${gift.objId}','evaluateListTab');return false;"></a></li>
				</ul>
			</li>
		</c:forEach>
	</ul>
</div>
<%@ include file="/view/pubservice/common/pageDirection.jsp" %>