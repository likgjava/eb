<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="goodsGiftGroup clearfix">
	<c:if test="${!empty goodsAccessPriceList && fn:length(goodsAccessPriceList) > 0}" var="hasGoodsAccess">
	<div class="goodsGiftList">
        <ul>
			<li class="pic_choice none">
				<a class="pic" title="${goods.productName}" href="#" target="_blank">
					<img src="<c:url value="AttachmentController.do?method=showImg&objId=${goods.picture}" />" width="80px" height="80px">
				</a>
			</li>
			<c:forEach items="${goodsAccessPriceList}" var="goodsAccessPrice">
				<li>
					<a class="pic" title="${goodsAccessPrice.goodsAccessories.accessoryGoods.productName}(价格:￥${goodsAccessPrice.accessoryPrice})" href="javascript:GoodsPriceList.viewGoodsAccess('${goodsAccessPrice.goodsAccessories.objId}');">
					<img src="<c:url value="AttachmentController.do?method=showImg&objId=${goodsAccessPrice.goodsAccessories.accessoryGoods.picture}" />" width="80px" height="80px">
					</a>
					<div>
						<input type="checkbox" goodsGiftPrice="${goodsAccessPrice.accessoryPrice}" goodsGiftDiv="${goodsAccessDiv}" goodsGiftId="${goodsAccessPrice.goodsAccessories.objId}" onclick="GoodsPriceList.selectOrCancleGoodsAccess(this);">
						<label><a title="${goodsAccessPrice.goodsAccessories.accessoryGoods.productName}" href="javascript:GoodsPriceList.viewGoodsAccess('${goodsAccessPrice.goodsAccessories.objId}');">${goodsAccessPrice.goodsAccessories.accessoryGoods.productName}</a></label>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="group_result">
		<p class="tips"><span name="packCount_${goodsAccessDiv}">1</span>件商品组合购买</p>
		<p class="price_d">总价：￥<span name="totalPrice_${goodsAccessDiv}">${goodsPrice.prtcPrice}</span></p>
		<button type="button" onclick="GoodsPriceList.addAA2SC('${goodsAccessDiv}');" class="addCart"></button>
	</div>
	</c:if>
	<c:if test="${!hasGoodsAccess}">
		<h2>暂无零配件！</h2>
	</c:if>
</div>
	