<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<h2>推荐商品</h2>
<ul class="goodsLibraryList" id="recommendGoodsList">
	<c:forEach var="recommendGoods" items="${recommendGoodsList}" varStatus="status">
	<li name="page${status.index / 8}" class="<c:if test="${status.index > 7}">hidden</c:if> <c:if test="${status.index % 4 == 3}">omega</c:if>">
		<h3>
			<a title="${recommendGoods.goods.productName}" href="javascript:void(0);" onclick="common.geToGoodsDetail('${recommendGoods.goods.objId}');">
				<c:choose><c:when test="${fn:length(recommendGoods.goods.productName) > 18}">${fn:substring(recommendGoods.goods.productName,0,17)}…</c:when><c:otherwise>${recommendGoods.goods.productName }</c:otherwise></c:choose>
			</a>
		</h3>
		<p class="hotImg"><a href="javascript:void(0);" onclick="common.geToGoodsDetail('${recommendGoods.goods.objId}');"><img style="width: 124px; height: 124px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${recommendGoods.goods.picture}&fileNameSuffix=_160*160" />"/></a></p>
		<p><em>￥<fmt:formatNumber value="${recommendGoods.goods.referPrice}" pattern="#,##0.00#" /></em></p>
	</li>
	</c:forEach>
</ul>
<div class="pageBox">
	<a href="javascript:void(0);" onclick="RecommendGoodsList.showPage(this,0);" class="selected">1</a>
	<a href="javascript:void(0);" onclick="RecommendGoodsList.showPage(this,1);">2</a>
	<a href="javascript:void(0);" onclick="RecommendGoodsList.showPage(this,2);">3</a>
	<a href="javascript:void(0);" onclick="RecommendGoodsList.showPage(this,3);">4</a>
</div>
<div class="more"><a href="GoodsShowController.do?method=toGoodsList&rp=20&page=1" target="_blank">更多</a></div>

<script>
RecommendGoodsList = {};
//换页
RecommendGoodsList.showPage = function(domE, num){
	$('#recommendGoodsList').find('li').hide();
	$('#recommendGoodsList').find('li[name^=page'+num+']').show();
	$(domE).siblings().removeClass('selected');
	$(domE).addClass('selected');
}
</script>
