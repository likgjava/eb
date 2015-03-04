<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/goods/goodsprice/goods_gift_manage.js"/>'></script>

<input type="hidden" id="goodsId" value="${param.goodsId}"/>
<input type="hidden" id="goodsPriceId" value="${param.goodsPriceId}"/>

<!-- 列表 -->
<div>
	<table class="frontTableList" id="goodsGiftList">
		<thead>
			<tr>
				<th class="center">礼包图片</th>
				<th class="left">礼包名称</th>
				<th class="center">所属商品</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<!-- 挑选的商品礼包列表  -->
<div>
	<table class="frontTableList" id="goodsGiftSelectedTable">
		<caption><span>商品优惠礼包</span></caption>
		<thead>
			<tr>
				<th>礼包名称</th>
				<th>礼包价格(元)</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="goodsGiftSelectedTbody">
			<c:forEach items="${goodsGiftPriceList}" var="goodsGiftPrice">
			<tr goodsGiftPriceId="${goodsGiftPrice.objId}" goodsGiftId="${goodsGiftPrice.goodsGift.objId}">
				<td>${goodsGiftPrice.goodsGift.giftName}</td>
				<td class="center">
					<input type="text" name="giftPrice" size="5" style="width:35px;" value="${goodsGiftPrice.giftPrice}">
				</td>
				<td class="operation"><a href="javascript:void(0);" onclick="GoodsGiftManage.removeSelected(this);return false;">删除</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<!-- 用于克隆的行数据  -->
<table class="hidden" id="goodsGiftPriceTempTable">
	<tr>
		<td></td>
		<td class="center">
			<input type="text" name="giftPrice" style="width:35px;" size="5">
		</td>
		<td class="operation"><a href="javascript:void(0);" onclick="GoodsGiftManage.removeSelected(this);return false;">删除</a></td>
	</tr>
</table>

<!-- 操作按钮 -->
<div class="conOperation ">
	<button type="button" id="saveGoodsGiftPriceBtn"><span>保存</span></button>
	<button type="button" id="closeBtn"><span>关闭</span></button>
</div> 
