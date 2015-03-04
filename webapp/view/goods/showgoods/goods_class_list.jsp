<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="categories">
	<h2><a href="javascript:void(0);" onclick="ShowGoodsIndex.changeCategory();return false;">所有商品分类</a></h2>
	<ul>
		<c:forEach var="gc" items="${goodsClassList}">
		<li>
			<h3><a href="javascript:void(0);" onclick="ShowGoodsIndex.searchByGoodsClass('${gc.goodsClassCode}');return false;">${gc.goodsClassName}</a></h3>
			<c:if test="${gc.children != null && fn:length(gc.children) > 0}">
			<dl>
				<c:forEach var="gc2" items="${gc.children}">
				<dt><a href="javascript:void(0);" onclick="ShowGoodsIndex.searchByGoodsClass('${gc2.goodsClassCode}');return false;" title="${gc2.goodsClassName}">${gc2.goodsClassName}</a></dt>
				<c:if test="${gc2.children != null && fn:length(gc2.children) > 0}">
					<dd>
						<c:forEach var="gc3" items="${gc2.children}">
							<a href="javascript:void(0);" onclick="ShowGoodsIndex.searchByGoodsClass('${gc3.goodsClassCode}');return false;" title="${gc3.goodsClassName}">${gc3.goodsClassName}</a>
						</c:forEach>
					</dd>
                </c:if>
                </c:forEach>
			</dl>
			</c:if>
		</li>
		</c:forEach>
	</ul>
</div>