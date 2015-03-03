<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/order/order_purchaser_conform.js"></script>

<input type="hidden" id="appType" name="appType" value="${param.appType }"/>
<input type="hidden" id="isNogoodsPrice" name="isNogoodsPrice" value="${isNogoodsPrice}"/>

<div class="formLayout form2Pa">
<form:form id="OrderPurchaserForm" method="post" modelAttribute="order">	 
	<form:hidden path="objId"></form:hidden> 
	<input type="hidden" id="type" value="<c:out value="${type}" />" />
<h4><span>订单信息</span></h4>
<ul>
	<li><label>订单编号：</label>${order.orderNo}</li>
	<li><label>供应商：</label><span id="supplier.orgName">${order.supplier.orgName}</span></li>
	<li class="fullLine"><label>支付状态：</label><font style="font-weight: bold;" color="<c:choose><c:when test="${order.payStatus=='01'}">green</c:when><c:otherwise>red</c:otherwise></c:choose>">【${order.payStatusCN }】</font></li>
	<li><label>创建人：</label>${order.createUser.emp.name}</li>
	<li><label>创建日期：</label><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
	
</ul>
</form:form>
</div>
<!--  订单商品列表-->
<div class="orderManagement">

<c:set var="countGoods" value="0"/>
<c:set var="totalMoney" value="0"/>
<c:set var="saveAmount" value="0"/>
<c:set var="marktTotal" value="0"/>
	<table class="frontTableList" id="goodsAndOption">
		<thead>
			<tr>
				<th width="380px">商品名称</th>
				<th>市场价</th>
				<c:if test="${isNogoodsPrice=='false'}">
					<th>供应商报价</th>
					<th class="money">单价</th>
				</c:if>
				<th>数量</th>
				<c:if test="${isNogoodsPrice=='false'}">
				<th>金额</th>
				<th >删除</th>
				</c:if>
			</tr>
		</thead>
		<tbody>	 
			<c:forEach var = "orderItem" items="${orderItemList}" varStatus="status">
				<c:set var="cellTotal" value="0"/>
				<tr class="goodsInfo" id="${orderItem.objId}">
					<td>
						<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${orderItem.goods.picture}" />" >
						<c:choose>
							<c:when test="${orderItem.goods!=null }">
								<a href="javascript:void(0);" onclick="OrderPurchaserForm.showDetail('${orderItem.goods.objId}');return false;" >
									${orderItem.goods.productName } &nbsp;${orderItem.goods.productCode }
								</a>
							</c:when>
							<c:otherwise>
								${orderItem.desr}
							</c:otherwise>
						</c:choose>
						<!-- 有礼包 -->
						<c:if test="${!empty orderItem.orderGoodsGifts && fn:length(orderItem.orderGoodsGifts) > 0}">
							<a href="javascript:void(0);" title="商品礼包详情" onclick="OrderPurchaserForm.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
						</c:if>
						<!-- 有零配件  -->
						<c:if test="${!empty orderItem.orderGoodsAccessories && fn:length(orderItem.orderGoodsAccessories) > 0}">
							<a href="javascript:void(0);" title="商品零配件详情" onclick="OrderPurchaserForm.showOrHideGoodsAccess(this);return false;"><img src="view/resource/skin/sysicon/16/goods_access.png" /></a>
						</c:if>
						<div class="fitting"><em>选配：</em>
								<c:forEach var = "orderGoodsOption" items="${orderItem.orderGoodsOptions}">
									${orderGoodsOption.optionalFitting.optionContent }(${orderGoodsOption.optPrice})/
									<c:set var="cellTotal" value="${cellTotal + orderGoodsOption.optPrice}"/>
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
					<td class="money" id="agreePrice_${status.index}">
						<fmt:formatNumber value="${orderItem.agreePrice }" pattern="#,##0.00#"/>
						<c:set var="cellTotal" value="${cellTotal + orderItem.agreePrice }"/>
					</td>
					<td class="money">
						<c:if test="${orderItem.goodsPrice>0}"><c:set var="cellTotal" value="${orderItem.goodsPrice }"/></c:if>
						<c:if test="${param.appType=='XEJY'||orderItem.orderProtaskCount>0}">
							<span><fmt:formatNumber value="${orderItem.goodsPrice }" pattern="#,##0.00#" /></span>
							<input type="hidden" name="itmeprice" onchange="OrderPurchaserForm.goodsPriceChange(this)" value="<fmt:formatNumber value="${cellTotal}" pattern="#,##0.00#" />"/>
						</c:if>
						<c:if test="${param.appType=='XYGH'&&!(orderItem.orderProtaskCount>0)}">
							<input type="text" name="itmeprice" onchange="OrderPurchaserForm.goodsPriceChange(this)" value="<fmt:formatNumber value="${orderItem.goodsPrice }" pattern="#,##0.00#" />"/>
						</c:if>
					</td>
					</c:if>
					<td class="money">
						<c:if test="${orderItem.agreePrice>0&&orderItem.goodsPrice>0&&!(orderItem.orderProtaskCount>0)}">
						<a href="javascript:void(0);" onclick="OrderPurchaserForm.goodsQtyAddOrReduce(this,-1);return false;"><img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
						</c:if>
						<input style="width:25px;" <c:if test="${!(orderItem.agreePrice>0&&orderItem.goodsPrice>0)||orderItem.orderProtaskCount>0}">disabled="disabled"</c:if> 
						name="itemqty" value="${orderItem.goodsQty }" oldvalue="${orderItem.goodsQty}" onchange="OrderPurchaserForm.goodsQtyChange(this)" />
						<c:if test="${orderItem.agreePrice>0&&orderItem.goodsPrice>0&&!(orderItem.orderProtaskCount>0)}">
						<a href="javascript:void(0);" onclick="OrderPurchaserForm.goodsQtyAddOrReduce(this,1);return false;"><img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
						</c:if>
						<c:set var="countGoods" value="${countGoods+orderItem.goodsQty}"/>
					</td>
					<c:if test="${isNogoodsPrice=='false'}">
					<td>
						<span id="goodsTotal"><fmt:formatNumber value="${orderItem.goodsTotal}" pattern="#,##0.00#"/></span>
						<input type="hidden" name="cellTotal" value="${cellTotal*orderItem.goodsQty}">
						<c:set var="totalMoney" value="${totalMoney + orderItem.goodsTotal}"/>
					</td>
					<td class="operation">
						<a href="javascript:void(0);" onclick="OrderPurchaserForm.remove('${orderItem.objId}');return false;" ><span>删除</span></a>
						<c:if test="${param.appType!='XEJY'}">
							<c:choose>
								<c:when test="${orderItem.orderProtaskCount>0 }">
									<a href="javascript:void(0);" onclick="OrderPurchaserForm.vieTask('${orderItem.objId}','${orderItem.goods.purCategory.objId}',this<c:if test="${orderItem.orderProtaskCount!=null}">,${orderItem.orderProtaskCount}</c:if>);return false;"><span id="chooseTask">查看任务书</span></a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0);" onclick="OrderPurchaserForm.vieTask('${orderItem.objId}','${orderItem.goods.purCategory.objId}',this<c:if test="${orderItem.orderProtaskCount!=null}">,${orderItem.orderProtaskCount}</c:if>);return false;"><span id="chooseTask">挑选任务书</span></a>
								</c:otherwise>
							</c:choose>
						</c:if>
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
							<li>金额总计：<strong>￥<span id="totalMoney">
							<c:if test="${isNogoodsPrice!='false'}"><fmt:formatNumber value="${order.goodsTotal}" pattern="#,##0.00#" /></c:if>
							<c:if test="${isNogoodsPrice=='false'}"><fmt:formatNumber value="${totalMoney}" pattern="#,##0.00#" /></c:if>
							</span></strong>元</li>
							<li>节省金额：<strong>￥<span id="saveAmount">
							<c:if test="${isNogoodsPrice!='false'}"><fmt:formatNumber value="${marktTotal-order.goodsTotal}" pattern="#,##0.00#" /></c:if>
							<c:if test="${isNogoodsPrice=='false'}"><fmt:formatNumber value="${marktTotal-totalMoney}" pattern="#,##0.00#" /></c:if>
							</span></strong>元</li>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
</div>

<!-- 订单列表结束 -->



<br />
<div class="formLayout form2Pa">

<c:if test="${order.supplierOption!=null}">
<h4><span>供应商意见</span></h4>
<ul>
	<li class="fullLine">
		<label>供应商意见</label>
		<span>${order.supplierOption }</span>
	</li>
</ul>
</c:if>

<h4><span>采购人意见</span></h4>
<ul>
	<li class="formTextarea"><label for="paOpinion">采购人意见:</label>
	<textarea name="buyerOption" id="buyerOption">${order.buyerOption}</textarea></li>
</ul>
</div>


<div class="conOperation">
<button class="hidden" id="save"><span>保存</span></button>
<c:if test="${order.useStatus=='00'}">
	<button id="submit"><span>提交</span></button>
	<button id="cancel"><span>取消订单</span></button>
</c:if>
<button name="historyBackBtn" type="button"><span>返回</span></button>
</div>


