<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 精品团购  开始 -->
<div class="cols groupBuy">
	<h2>精品团购</h2>
	<ul class="hotGoodsList">
		<c:forEach var="groupBuying" items="${groupBuyingList}">
		<li>
			<h3><a href="javascript:void(0);" title="${groupBuying.name}" onclick="GroupBuyingIndex.toGroupBuyerFormView('${groupBuying.objId}');"><c:choose><c:when test="${fn:length(groupBuying.name) > 16}">${fn:substring(groupBuying.name,0,15)}…</c:when><c:otherwise>${groupBuying.name}</c:otherwise></c:choose></a></h3>
			<p class="hotImg"><img style="width: 100px; height: 100px;" src="AttachmentController.do?method=showImg&objId=${groupBuying.picture}&fileNameSuffix=_180*180" /></p>
			<p class="hotDetails"><c:choose><c:when test="${fn:length(groupBuying.desc) > 18}">${fn:substring(groupBuying.desc,0,17)}…</c:when><c:otherwise>${groupBuying.desc}</c:otherwise></c:choose></p>
			<p class="hotPrice"><em>￥<fmt:formatNumber value="${groupBuying.groupPrice}" pattern="#,##0" /></em> <del>￥<fmt:formatNumber value="${groupBuying.marketPrice}" pattern="#,##0" /></del></p>
			<p class="signUp"><a href="javascript:void(0);" class="bigBtn" onclick="GroupBuyingIndex.toGroupBuyerFormView('${groupBuying.objId}');">我要团购</a></p>
		</li>
		</c:forEach>
	</ul>
	<div class="more"><a title="点击查看更多" href="<%=request.getContextPath()%>/GroupBuyingShowController.do?method=toShowGroupBuyingIndexView" target="_blank">更多</a></div>
</div>
<!-- 精品团购  结束 -->

<script type="text/javascript">
/** 首页精品团购页面 */
var GroupBuyingIndex = {};

//跳转到下订单页面
GroupBuyingIndex.toGroupBuyerFormView = function(groupBuyingId){
	window.open($('#initPath').val()+"/GroupBuyerController.do?method=toCreateOrUpdateView&groupBuyingId="+groupBuyingId);
}
</script>