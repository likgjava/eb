<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="cols">
	<h2>热销商品</h2>
	<ul class="verticalShowListM">
		<c:forEach var="recommendGoods" items="${recommendGoodsList}" varStatus="status">
		<li>
			<h3>
				<a href="javascript:void(0);" onclick="common.geToGoodsDetail('${recommendGoods.goods.objId}');" title="${recommendGoods.goods.productName}">
		        	<c:choose>
	    				<c:when test="${fn:length(recommendGoods.goods.productName) > 20}">${fn:substring(recommendGoods.goods.productName,0,19)}…</c:when>
	    				<c:otherwise>${recommendGoods.goods.productName }</c:otherwise>
					</c:choose>
	        	</a>
	        </h3>
			<p class="hotImg"><a href="javascript:void(0);" onclick="common.geToGoodsDetail('${recommendGoods.goods.objId}');"><img style="width: 132px; height: 132px;" src="AttachmentController.do?method=showImg&objId=${recommendGoods.goods.picture}&fileNameSuffix=_160*160" /></a></p>
			<p class="hotPrice"><em>￥<fmt:formatNumber value="${recommendGoods.goods.referPrice}" pattern="#,##0.00" /></em></p>
		</li>
		</c:forEach>
	</ul>
	<div class="more"><a href="GoodsShowController.do?method=toGoodsList&rp=20&page=1" target="_blank">更多</a></div>
</div>