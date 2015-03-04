<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<c:forEach var="goodsList" items="${goodsListList}" varStatus="status">
<div class="gridBox">
	<div class="cols">
		<h2>${hotTagList[status.index].tagsName}</h2>
		<div class='brandBox'>
			<c:if test="${status.count == 1}">
				<%@ include file="/view/staticpags/adverhtml/adver_goods_left1.jsp" %>
			</c:if>
			<c:if test="${status.count == 2}">
				<%@ include file="/view/staticpags/adverhtml/adver_goods_left2.jsp" %>
			</c:if>
		</div>
		<div class='goodsShowList'>
			<ul class="verticalShowList">
				<c:forEach var="recommendGoods" items="${goodsList}" varStatus="status2">
				<li <c:if test="${status2.index % 5 == 4}">class="omega"</c:if>>
					<h3><a title="${recommendGoods.goods.productName}" href="javascript:void(0);" onclick="ShowGoodsIndex.showGoodsDetail('${recommendGoods.goods.objId}','${recommendGoods.goods.goodsClass.goodsClassCode}');return false;">
						<c:choose>
							<c:when test="${fn:length(recommendGoods.goods.productName) > 22 }">${fn:substring(recommendGoods.goods.productName,0,21)}…</c:when>
							<c:otherwise>${recommendGoods.goods.productName}</c:otherwise>
						</c:choose>
					</a></h3>
					<p class="hotImg"><a href="javascript:void(0);" onclick="ShowGoodsIndex.showGoodsDetail('${recommendGoods.goods.objId}','${recommendGoods.goods.goodsClass.goodsClassCode}');return false;"><img style="width: 120px; height: 120px;" src="<c:url value="AttachmentController.do?method=showImg&objId=${recommendGoods.goods.picture}&fileNameSuffix=_160*160" />"/></a></p>
					<p class="hotPrice"><em class="em">￥<fmt:formatNumber value="${recommendGoods.goods.referPrice}" pattern="#,##0.00#" /></em></p>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="more"><a href="javascript:void(0);" onclick="ShowGoodsIndex.searchByGoodsClass('${hotTagList[status.index].tagsId}');return false;">更多</a></div>
	</div>
</div>
</c:forEach>
