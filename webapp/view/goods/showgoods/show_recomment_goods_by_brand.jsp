<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<c:if test="${!empty recommendGoodsList && fn:length(recommendGoodsList) > 0}">
<div class="hot-products">
	<h3><span>推荐商品</span></h3>
	<ul>
		<c:forEach var ="recommentGoods" items="${recommendGoodsList}">
		<li> 
			<a onclick="show_list.showDetail('${recommentGoods.goods.objId}',null,'${recommentGoods.goods.goodsClass.goodsClassCode}');return false;">
				<img alt="${recommentGoods.goods.productName}" src="<c:url value="AttachmentController.do?method=showImg&objId=${recommentGoods.goods.picture}&fileNameSuffix=_160*160" />">
			</a>
			<a title="${recommentGoods.goods.productName}" onclick="show_list.showDetail('${recommentGoods.goods.objId}',null,'${recommentGoods.goods.goodsClass.goodsClassCode}');return false;">
				<span class="desc">
					<c:choose>
						<c:when test="${fn:length(recommentGoods.goods.productName) > 10 }">${fn:substring(recommentGoods.goods.productName,0,9)}…</c:when>
						<c:otherwise>${recommentGoods.goods.productName}</c:otherwise>
					</c:choose>
				</span>
			</a> 
			<em class="em">￥<fmt:formatNumber value="${recommentGoods.goods.referPrice}" pattern="#,##0.00" /></em>
		</li>
		</c:forEach>
	</ul>
</div>
</c:if>