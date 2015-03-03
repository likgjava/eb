<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/order/order_detail_div.js"></script>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>

<input type="hidden" id="isNogoodsPrice" name="isNogoodsPrice" value="${isNogoodsPrice}"/>

<!-- 订单详情 -->
<div class="formLayout form2Pa">
  <form id="orderForm" method="post">
  <input type="hidden" id="orderId" value="<c:out value="${param.objId}"/>"/>
  <h4><span>订单信息</span></h4>
    <ul>
      <li class="fullLine"><label>订单编号：</label><span id="">${order.orderNo }</span></li>
      <li><label>采购人：</label><span id="buyer.orgName">${order.buyer.orgName }</span></li>
      <li><label>供应商：</label><span id="supplier.orgName">${order.supplier.orgName }</span></li>
      <li><label>创建人：</label><span id="createUser.emp.name">${order.createUser.emp.name }</span></li>
      <li><label>创建日期：</label><span id="createTime" tabType="datesimple"><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
    </ul>
  </form>
  
</div>

<!--  订单商品列表-->
<div class="orderManagement">
<c:set var="countGoods" value="0"/>
<c:set var="totalMoney" value="0"/>
<c:set var="saveAmount" value="0"/>
<c:set var="marktTotal" value="0"/>
<c:set var="cellTotal" value="0"/>

	<table class="frontTableList" id="goodsAndOption">
		<thead>
			<tr>
				<th width="380px">商品名称</th>
				<th>市场价</th>
				<c:if test="${isNogoodsPrice=='false'}">
				<th>供应商报价</th>
				<th class="money">单价</th>
				</c:if>
				<th>商品数量</th>
				<c:if test="${isNogoodsPrice=='false'}">
				<th>金额</th>
				</c:if>
			</tr>
		</thead>
		<tbody>	 
			<c:forEach var = "orderItem" items="${orderItemList}">
				<c:set var="cellTotal" value="0"/>
				<tr class="goodsInfo" id="${orderItem.objId}">
					<td>
						<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${orderItem.goods.picture}" />" >
						<a href="javascript:void(0);" onclick="orderDetail.showGoodsDetail('${orderItem.goods.objId}');return false;" >${orderItem.goods.productName } &nbsp;${orderItem.goods.productCode }</a>
						<!-- 有礼包 -->
						<c:if test="${!empty orderItem.orderGoodsGifts && fn:length(orderItem.orderGoodsGifts) > 0}">
							<a href="javascript:void(0);" title="商品礼包详情" onclick="orderDetail.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
						</c:if>
						<!-- 有零配件  -->
						<c:if test="${!empty orderItem.orderGoodsAccessories && fn:length(orderItem.orderGoodsAccessories) > 0}">
							<a href="javascript:void(0);" title="商品零配件详情" onclick="orderDetail.showOrHideGoodsAccess(this);return false;"><img src="view/resource/skin/sysicon/16/goods_access.png" /></a>
						</c:if>
						<div class="fitting"><em>选配：</em>
								<c:forEach var = "orderGoodsOption" items="${orderItem.orderGoodsOptions}">
									${orderGoodsOption.optionalFitting.optionContent } /
								</c:forEach>
						</div>
					</td>
					<td class="money">
						<span id="mark"><fmt:formatNumber value="${orderItem.marketPrice }" pattern="#,##0.00#" /></span>
						<c:set var="marktTotal" value="${marktTotal+(orderItem.marketPrice*orderItem.goodsQty)}"/>
						<!-- 假若供应商都没有保单个价就把市场价往上加 -->
						<c:if test="${!(orderItem.agreePrice>0&&orderItem.goodsPrice>0)}">
							<c:set var="cellTotal" value="${cellTotal + orderItem.marketPrice }"/>
						</c:if>
					</td>
					<c:if test="${orderItem.agreePrice>0&&orderItem.goodsPrice>0}">
					<td class="money">
						<fmt:formatNumber value="${orderItem.agreePrice }" pattern="#,##0.00#" />
						<c:set var="cellTotal" value="${cellTotal + orderItem.agreePrice }"/>
					</td>
					<td class="money">
						<span id="itmeprice"><fmt:formatNumber value="${orderItem.goodsPrice }" pattern="#,##0.00#" /></span>
					</td>
					</c:if>
					<td class="money">
						<span id="itemqty">${orderItem.goodsQty }</span>
						<c:set var="countGoods" value="${countGoods+orderItem.goodsQty}"/>
					</td>
					<c:if test="${orderItem.agreePrice>0&&orderItem.goodsPrice>0}">
					<td>
						<span id="goodsTotal"><fmt:formatNumber value="${cellTotal*orderItem.goodsQty}" pattern="#,##0.00#"/></span>
						<input type="hidden" name="cellTotal" value="${cellTotal*orderItem.goodsQty}">
						<c:set var="totalMoney" value="${totalMoney + orderItem.goodsTotal}"/>
					</td>
					</c:if>
				</tr>
				
				<!-- 礼包 -->
				<c:if test="${!empty orderItem.orderGoodsGifts && fn:length(orderItem.orderGoodsGifts) > 0}">
				<tr class="hidden">
					<td colspan="7">
						<div>
						<table>
							<tr>
							<c:forEach items="${orderItem.orderGoodsGifts}" var="orderGoodsGift">
							<td>
							   <div style="float:left">
									<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${orderGoodsGift.goodsGift.giftPicture}" />' >
								 	<div class="fitting" style="float:left">
								 	${orderGoodsGift.goodsGift.giftName}<br/>
								 	￥<fmt:formatNumber value="${orderGoodsGift.giftPrice}" pattern="#,##0.00#"/><br/>
								 	</div>
							   </div>
							</td>
							</c:forEach>
							</tr>
						</table>
						</div>
					</td>
				</tr>
				</c:if>
				
				<!-- 零配件 -->
				<c:if test="${!empty orderItem.orderGoodsAccessories && fn:length(orderItem.orderGoodsAccessories) > 0}">
				<tr class="hidden">
					<td colspan="7">
						<div>
						<table>
							<tr>
							<c:forEach items="${orderItem.orderGoodsAccessories}" var="orderGoodsAccess">
							<td>
								<div style="float:left">
									<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${orderGoodsAccess.goodsAccess.accessoryGoods.picture}" />' >
									<div class="fitting" style="float:left">
									${orderGoodsAccess.goodsAccess.accessoryGoods.productName}<br/>
									￥<fmt:formatNumber value="${orderGoodsAccess.accessPrice}" pattern="#,##0.00#"/>
									</div>
								</div>
							</td>
							</c:forEach>
							</tr>
						</table>
						</div>
					</td>
				</tr>
				</c:if>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<ul>
							<li>数量总计：<strong><span id="countGoods">${countGoods}</span></strong>件</li>
							<li>金额总计：<strong>￥<span id="totalMoney"><fmt:formatNumber value="${order.goodsTotal}" pattern="#,##0.00#" /></span></strong>元</li>
							<li>节省金额：<strong>￥<span id="saveAmount"><fmt:formatNumber value="${marktTotal-order.goodsTotal }" pattern="#,##0.00#" /></span></strong>元</li>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<!-- 订单列表结束 -->



 
 <div class="formLayout form2Pa">
</div>  

<div class="conOperation">	
 		<button  id="orderDivclose" type="button"><span>关闭</span></button>
</div>