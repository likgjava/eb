<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<c:if test="${param.isOutCss}">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>购物车-阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
</c:if>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/shoppingcart/my_shopping_car_div.js"></script>
<div id="conBody">
<div id="conTitle">
	<div class="navCurrent"><img src="view/resource/skin/pubservice/img/cart.gif" height="35px"/></div>
</div>
<div class="orderManagement">
	<c:set var="totalQty" value="0"/>
	<c:set var="total" value="0"/>
	<c:set var="marktTotal" value="0"/>
	<c:set var="cellTotal" value="0"/>
	<c:set var="supplierIds" value="" />
	
	<table class="frontTableList" id="goodsAndOption">
		<thead>
			<tr>
				<th><input type="checkbox" name="checkAll" onclick="shoppingCartDiv.checkAllOrNot('checkCell',this)">&nbsp;全选</th>
				<th width="380px">商品名称</th>
				<th>市场价</th>
				<th>供应商报价</th>
				<th width="80px">商品数量</th>
				<th>金额</th>
				<th></th>
			</tr>
		</thead>
		<tbody>	 
			<c:forEach var = "shoppingCartItem" items="${ShoppingCartItemList}" varStatus="status">
				<c:choose>
					<c:when test="${!fn:contains(supplierIds , shoppingCartItem.supplier.objId)&& shoppingCartItem.supplier!=null}">
						<tr>
						<td colspan="6" class="splInfo">
							<input name="checkCell${shoppingCartItem.supplier.objId}" type="checkbox" onclick="shoppingCartDiv.checkAllOrNot('${shoppingCartItem.supplier.objId}',this)" >
							&nbsp;供应商：<a href="javascript:void(0);" onclick="shoppingCartDiv.showSupplierDetail('${shoppingCartItem.supplier.objId}');return false;">${shoppingCartItem.supplier.orgName}</a>
							<button type="button" onclick="common.addFavorites('${shoppingCartItem.supplier.objId}','${shoppingCartItem.supplier.orgName}','02')" class="favBtn"><span>收藏</span></button>
						</td>
						</tr>
						<c:set var="supplierIds" value="${supplierIds}${shoppingCartItem.supplier.objId}" />
					</c:when>
					<c:when test="${!fn:contains(supplierIds ,'nosupplier')&& shoppingCartItem.supplier==null}">
						<tr>
						<td colspan="6" class="splInfo">
							<input name="checkCellnosupplier" type="checkbox" onclick="shoppingCartDiv.checkAllOrNot('nosupplier',this)" >
							&nbsp;无供应商
						</td>
						</tr>
						<c:set var="supplierIds" value="${supplierIds}nosupplier" />
					</c:when>
				</c:choose>
				<tr id="${shoppingCartItem.objId }" class="goodsInfo">
					<td>
					<input type="checkbox" name="checkCell<c:choose><c:when test="${shoppingCartItem.supplier!=null}">${shoppingCartItem.supplier.objId }</c:when><c:otherwise>nosupplier</c:otherwise></c:choose>" value="${shoppingCartItem.objId}">
					<input type="hidden" name="cellSupplierId" value="${shoppingCartItem.supplier.objId }" >
					<input type="hidden" name="giftSize" value="${fn:length(shoppingCartItem.cartGoodsGifts)}" >
					</td>
					<td>
						<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${shoppingCartItem.goods.picture}" />" >
						<a href="javascript:void(0);" onclick="shoppingCartDiv.showGoodsDetail('${shoppingCartItem.goods.objId}');return false;" >${shoppingCartItem.goods.productName } &nbsp;${shoppingCartItem.goods.productCode }</a>
						<!-- 有礼包 -->
						<c:if test="${!empty shoppingCartItem.cartGoodsGifts && fn:length(shoppingCartItem.cartGoodsGifts) > 0}">
							<a href="javascript:void(0);" title="商品礼包详情" onclick="shoppingCartDiv.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
						</c:if>
						<!-- 有零配件 -->
						<c:if test="${!empty shoppingCartItem.cartGoodsAccessories && fn:length(shoppingCartItem.cartGoodsAccessories) > 0}">
							<a href="javascript:void(0);" title="商品零配件详情" onclick="shoppingCartDiv.showOrHideGoodsAccess(this);return false;"><img src="view/resource/skin/sysicon/16/goods_access.png" /></a>
						</c:if>
						<div class="fitting"><b>选配：</b>
							<c:forEach var = "ShoppingCartGoodsOption" items="${shoppingCartItem.cartGoodsOptions}">
								${ShoppingCartGoodsOption.option.optionContent } /
								<c:set var="cellTotal" value="${cellTotal + ShoppingCartGoodsOption.optPrice }"/>
							</c:forEach>
						</div>
					</td>
					<td class="money">￥ <span id="mark"><fmt:formatNumber value="${shoppingCartItem.marketPrice }" pattern="#,##0.00#" /></span></td>
					<c:set var="marktTotal" value="${marktTotal+(shoppingCartItem.marketPrice*shoppingCartItem.goodsQty)}"/>
					<td class="money" id="agreePrice_${status.index}"><strong>￥<span class="money"> <fmt:formatNumber value="${shoppingCartItem.agreePrice }" pattern="#,##0.00#" /></span></strong></td>
					<c:set var="cellTotal" value="${cellTotal + shoppingCartItem.agreePrice }"/>
					<td class="operation" id="qty">
						<a href="javascript:void(0);" onclick="{
							$(this).parent().find('input[name=itemqty]').val(Number($(this).parent().find('input[name=itemqty]').val())>1?Number($(this).parent().find('input[name=itemqty]').val())-1:1);
							shoppingCartDiv.updateQty('${shoppingCartItem.objId}','${shoppingCartItem.shoppingCart.objId}',$(this).parent().find('input[name=itemqty]'),'${shoppingCartItem.objId }');return false;
						}">
						<img src="view/resource/skin/pubservice/img/bag_close.gif" /></a>
						<input style="width:25px;" name="itemqty" value="${shoppingCartItem.goodsQty }" oldvalue="${shoppingCartItem.goodsQty}" onchange="shoppingCartDiv.updateQty('${shoppingCartItem.objId}','${shoppingCartItem.shoppingCart.objId}',this,'${shoppingCartItem.objId }')" />
						<a href="javascript:void(0);" onclick="{
							$(this).parent().find('input[name=itemqty]').val(Number($(this).parent().find('input[name=itemqty]').val())+1);
							shoppingCartDiv.updateQty('${shoppingCartItem.objId}','${shoppingCartItem.shoppingCart.objId}',$(this).parent().find('input[name=itemqty]'),'${shoppingCartItem.objId }');return false;
							}">
						<img src="view/resource/skin/pubservice/img/bag_open.gif" /></a>
					</td>
					<c:set var="totalQty" value="${totalQty+shoppingCartItem.goodsQty}"/>
					<td class="money" id="total">
						￥<span class="money"><fmt:formatNumber value="${shoppingCartItem.goodsTotal }" pattern="#,##0.00#"/></span>
						<input type="hidden" name="cellTotal" value="${cellTotal}">
						<c:set var="cellTotal" value="0"/>
					</td>
					<c:set var="total" value="${total+shoppingCartItem.goodsTotal}"/>
					<td class="operation">
						<a href="javascript:void(0);" onclick="shoppingCartDiv.deleteItem('${shoppingCartItem.objId}','${shoppingCartItem.shoppingCart.objId}','${shoppingCartItem.supplier.objId}');return false;">删除</a>
					</td>
				</tr>
				
				<!-- 礼包 -->
				<c:if test="${!empty shoppingCartItem.cartGoodsGifts && fn:length(shoppingCartItem.cartGoodsGifts) > 0}">
				<tr class="hidden">
					<td colspan="7">
						<div>
						<table>
							<tr>
							<c:set var="goodsGiftNum" value="0"/>
							<c:forEach items="${shoppingCartItem.cartGoodsGifts}" var="shoppingCartGoodsGift">
							<c:set var="goodsGiftNum" value="${goodsGiftNum+1}"/>
							<td>
							   <div style="float:left">
									<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${shoppingCartGoodsGift.goodsGift.giftPicture}" />' >
								 	<div class="fitting" style="float:left">
								 	${shoppingCartGoodsGift.goodsGift.giftName}<br/>
								 	￥<fmt:formatNumber value="${shoppingCartGoodsGift.giftPrice}" pattern="#,##0.00#"/><br/>
								 	<input type="hidden" name="goodsGiftPrice" singlePrice="${shoppingCartGoodsGift.giftPrice}" value="${shoppingCartGoodsGift.giftPrice*shoppingCartItem.goodsQty}">
								 	<a href="javascript:void(0);" onclick="shoppingCartDiv.deleteGoodsGiftPrice(this,'${shoppingCartGoodsGift.objId}','agreePrice_${status.index}',${shoppingCartGoodsGift.giftPrice});">删除</a>
								 	</div>
							   </div>
							</td>
							</c:forEach>
							<c:if test="${goodsGiftNum==0}">
							<td>无商品礼包！</td>
							</c:if>
							</tr>
						</table>
						</div>
					</td>
				</tr>
				</c:if>
				
				<!-- 零配件 -->
				<c:if test="${!empty shoppingCartItem.cartGoodsAccessories && fn:length(shoppingCartItem.cartGoodsAccessories) > 0}">
				<tr class="hidden">
					<td colspan="7">
						<div>
						<table>
							<tr>
							<c:set var="goodsAccessNum" value="0"/>
							<c:forEach items="${shoppingCartItem.cartGoodsAccessories}" var="shoppingCartGoodsAccess">
							<c:set var="goodsAccessNum" value="${goodsAccessNum+1}"/>
							<td>
								<div style="float:left">
									<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${shoppingCartGoodsAccess.goodsAccess.accessoryGoods.picture}" />' >
									<div class="fitting" style="float:left">
									${shoppingCartGoodsAccess.goodsAccess.accessoryGoods.productName}<br/>
									￥<fmt:formatNumber value="${shoppingCartGoodsAccess.accessPrice}" pattern="#,##0.00#"/>
									<input type="hidden" name="goodsGiftPrice" singlePrice="${shoppingCartGoodsAccess.accessPrice}" value="${shoppingCartGoodsAccess.accessPrice*shoppingCartItem.goodsQty}">
									<a href="javascript:void(0);" onclick="shoppingCartDiv.deleteGoodsAccessPrice(this,'${shoppingCartGoodsAccess.objId}');">删除</a>
									</div>
								</div>
							</td>
							</c:forEach>
							<c:if test="${goodsAccessNum==0}">
							<td>无商品零配件！</td>
							</c:if>
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
						<li>数量总计：<em><span id="countGoods">${totalQty }</span></em></li>
						<li>节省金额：￥<em><span id="saveAmount"><fmt:formatNumber value="${marktTotal-total }" pattern="#,##0.00#" /></span></em>元</li>
						<li>商品总金额：<strong>￥<span id="totalAmount"><fmt:formatNumber value="${total}" pattern="#,##0.00#" /></span></strong>元</li>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
	
	<div class="functionBtnDiv">
		<img src="view/resource/skin/sysicon/16/cross.png" />&nbsp;<a href="javascript:void(0);" onclick="shoppingCartDiv.clearCart();return false;">清空购物车</a>&nbsp;
		<img src="view/resource/skin/sysicon/16/arrow_undo.png" />&nbsp;<a href="javascript:void(0);" onclick="shoppingCartDiv.goonShopping();return false;">继续购物</a>
	</div>

	<div align="right" class="three-Btn">
		<a name="bargainingBtn" class="toBargain" href="javascript:void(0);" onclick="shoppingCartDiv.bargainGoods();return false;">发起议价</a>
		<a name="vieProjectBtn" class="toBid" href="javascript:void(0);" onclick="shoppingCartDiv.createBargainProject();return false;">发起竞价</a>
		<a name="orderBtn" class="toOrder" href="javascript:void(0);" onclick="shoppingCartDiv.orderGoods();return false;">直接订购</a>
		<!--<a name="vieProjectBtn" class="toReverse"  onclick="shoppingCartDiv.createReverseProject();return false;"><span>发起反拍</span></a>-->
	</div>
</div>
</div>
 
<c:if test="${param.isOutCss}">
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
	<!--在线客服开始-->
    <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
    <!--在线客服结束-->
</div>
</body>
</html>
</c:if>