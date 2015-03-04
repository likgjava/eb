<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="goodsGiftGroup clearfix">
	<c:if test="${!empty goodsGiftPriceList && fn:length(goodsGiftPriceList) > 0}" var="hasGoodsGift">
	<div class="goodsGiftList">
        <ul>
			<li class="pic_choice_g none">
				<a class="pic" title="${goods.productName}" href="#" target="_blank">
					<img src="<c:url value="AttachmentController.do?method=showImg&objId=${goods.picture}" />" width="80px" height="80px">
				</a>
			</li>
			<c:forEach items="${goodsGiftPriceList}" var="goodsGiftPrice">
				<li>
					<a class="pic" title="${goodsGiftPrice.goodsGift.giftName}(优惠价:￥${goodsGiftPrice.giftPrice})" href="javascript:GoodsPriceList.viewGoodsGift('${goodsGiftPrice.goodsGift.objId}');">
					<img src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsGiftPrice.goodsGift.giftPicture}" />" width="80px" height="80px">
					</a>
					<div>
						<input type="checkbox" goodsGiftPrice="${goodsGiftPrice.giftPrice}" goodsGiftDiv="${goodsGiftDiv}" goodsGiftId="${goodsGiftPrice.goodsGift.objId}" onclick="GoodsPriceList.selectOrCancleGoodsGift(this);">
						<label><a title="${goodsGiftPrice.goodsGift.giftName}" href="javascript:GoodsPriceList.viewGoodsGift('${goodsGiftPrice.goodsGift.objId}');">${goodsGiftPrice.goodsGift.giftName}</a></label>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="group_result">
		<p class="tips"><span name="packCount_${goodsGiftDiv}">1</span>件商品组合购买</p>
		<p class="price_d">总价：￥<span name="totalPrice_${goodsGiftDiv}"><fmt:formatNumber value="${goodsPrice.prtcPrice}" pattern="#,##0.00#"/></span></p>
		<button type="button" onclick="GoodsPriceList.addGG2SC('${goodsGiftDiv}');" class="addCart"></button>
	</div>
	</c:if>
	<c:if test="${!hasGoodsGift}">
		<h2>暂无商品优惠礼包！</h2>
	</c:if>
</div>
	