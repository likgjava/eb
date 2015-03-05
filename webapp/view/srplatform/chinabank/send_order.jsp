<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/purchaseRequirement.css"/>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>

<!--页面容器 开始-->
<div id="container"> 
  <!--头部容器 开始-->
  <div class="header"> 
  		<input type="hidden" id="isOutCss" value="${param.isOutCss}"/>
  		<c:choose>
	  		<c:when test="${!isOutCss}"><jsp:include page="/view/srplatform/portal/include/backgroundmenu.jsp"></jsp:include></c:when>
  			<c:otherwise><%@ include file="/view/srplatform/portal/include/main_header_index.jsp"%></c:otherwise>
  		</c:choose>
  </div>
  <!--头部容器 结束--> 
	<!--主要内容容器 开始-->
	<div class="page" style="min-height:600px!important;">
	
	  <!-- 接收初始路径参数，团购总金额参数 ，支付返回调用方法参数，业务ID参数-->
	  <input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
	  <input type="hidden" id="v_amount" value="${v_amount}" />
	  <input type="hidden" id="v_back_req_method" value="${v_back_req_method}" />
	  <input type="hidden" id="v_business_id" value="${order.objId }" />
		<div class="gridBox">
			<!-- 三级菜单 -->
			<div class="grid16_3 hidden" id="menuList">
				<div class="menuList"><ul></ul></div>
			</div>
			
			<!-- conBody开始 内容页面加载位置 -->
			<div class="grid16_16" id="conBody">
			
				<div id="bd">
		    	<div class="bd_top"></div>
		    	<!--主体部分start-->
		    	<div class="bd_context">
		    		<!--导航 position-->
		    		<div class="bd_title">
						<div class="bd_t_name"><img  src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_order_pay.jpg" /></div>
						<div class="bd_t_guide">
						
						<div class="bd_t_pos pos_1_on">确定订单去支付</div>
						<div class="bd_t_pos_spc">
						</div><div class="bd_t_pos pos_2">支付返回结果</div>
						
						</div>
					</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_context">	
		    				<div class="bd_post_space2"></div>										
		    				<div class="bd_post_form">
		    				
		    				<div class="bd_post_form_line">
		   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>采购人：</div>
		   						<div class="bd_post_form_context">
		   							<div class="bd_post_form_input_float">${order.buyer.orgName }</div>
		   						</div>												
		   					</div>
		    				
		   					<div class="bd_post_form_line">
		   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>订单编号：</div>
		   						<div class="bd_post_form_context">
		   							<div class="bd_post_form_input_float">${v_oid}</div>
		   						</div>												
		   					</div>
		   					
		   					<div class="bd_post_form_line">
		   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>订单总额：</div>
		   						<div class="bd_post_form_context">
		   							<div class="bd_post_form_input_float"><fmt:formatNumber value="${v_amount}" pattern="#,##0.00#" /></div>
		   						</div>												
		   					</div>
		   					
		   					<div class="bd_post_form_line">
		   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>数量：</div>
		   						<div class="bd_post_form_context">
		   							<div class="bd_post_form_input_float">${order.goodsQty }</div>
		   						</div>												
		   					</div>
		   					
		   					<div class="bd_post_form_line">
		   					
		   						<table class="frontTableList" id="goodsAndOption">
				<thead>
					<tr>
						<th width="380px">商品名称</th>
						<th>市场价（元）</th>
						<c:if test="${!empty orderItemList && orderItemList[0]!=null&&orderItemList[0].goods!=null}">
							<th>供应商报价（元）</th>
							<th class="money">单价（元）</th>
						</c:if>
						<th>商品数量</th>
						<c:if test="${!empty orderItemList &&orderItemList[0]!=null&&orderItemList[0].goods!=null}">
						<th>金额（元）</th>
						</c:if>
					</tr>
				</thead>
				<tbody>	 
					<c:forEach var = "orderItem" items="${orderItemList}">
						<c:set var="cellTotal" value="0"/>
						<tr class="goodsInfo" id="${orderItem.objId}">
							<td>
								<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${orderItem.goods.picture}" />" />
								<a href="javascript:void(0);" onclick="OrderBuyerDetail.showDetail('${orderItem.goods.objId}');return false;" >${orderItem.goods.productName } &nbsp;${orderItem.goods.productCode }</a>
								<!-- 有礼包 -->
								<c:if test="${!empty orderItem.orderGoodsGifts && fn:length(orderItem.orderGoodsGifts) > 0}">
									<a href="javascript:void(0);" title="商品礼包详情" onclick="OrderBuyerDetail.showOrHideGoodsGift(this);return false;"><img src="view/resource/skin/sysicon/16/goods_gift.png" /></a>
								</c:if>
								<!-- 有零配件  -->
								<c:if test="${!empty orderItem.orderGoodsAccessories && fn:length(orderItem.orderGoodsAccessories) > 0}">
									<a href="javascript:void(0);" title="商品零配件详情" onclick="OrderBuyerDetail.showOrHideGoodsAccess(this);return false;"><img src="view/resource/skin/sysicon/16/goods_access.png" /></a>
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
							<td class="money">
								<span id="goodsTotal"><fmt:formatNumber value="${cellTotal*orderItem.goodsQty}" pattern="#,##0.00#"/></span>
								<input type="hidden" name="cellTotal" value="${cellTotal*orderItem.goodsQty}"/>
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
											<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${orderGoodsGift.goodsGift.giftPicture}" />'/>
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
						<tr>
							<td colspan="7">
								<table>
									<tr>
									<c:forEach items="${orderItem.orderGoodsAccessories}" var="orderGoodsAccess">
									<td>
										<img class="goodsImg" src='<c:url value="AttachmentController.do?method=showImg&objId=${orderGoodsAccess.goodsAccess.accessoryGoods.picture}" />'/>
										<div class="fitting" style="display:inline;">
										${orderGoodsAccess.goodsAccess.accessoryGoods.productName}<br/>
										￥<fmt:formatNumber value="${orderGoodsAccess.accessPrice}" pattern="#,##0.00#"/>
										</div>
									</td>
									</c:forEach>
									</tr>
								</table>
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
									<li>节省金额：<strong>￥<span id="saveAmount"><fmt:formatNumber value="${marktTotal-order.goodsTotal}" pattern="#,##0.00#" /></span></strong>元</li>
							</ul>
						</td>
					</tr>
				</tfoot>
			</table>
		   					</div>
		   					
		   					<div class="bd_post_form_line">
		   						<div class="bd_post_form_title">发票抬头：</div>
		   						<div class="bd_post_form_context">
		   							<div class="bd_post_form_input_float"><input type="text" id="invoiceTitle" name="invoiceTitle" /></div>
		   						</div>
		   					</div>
		   					<div class="bd_post_form_line">
		   						<div class="bd_post_form_title">发票名目：</div>
		   						<div class="bd_post_form_context">
		   							<div class="bd_post_form_input_float"><input type="text" id="invoiceItems" name="invoiceItems" /></div>
		   						</div>
		   					</div>
		   					<div class="bd_post_form_line">
		   						<div class="bd_post_form_title">邮寄地址：</div>
		   						<div class="bd_post_form_context">
		   							<div class="bd_post_form_input_float"><input type="text" id="mailingAddress" name="mailingAddress" /></div>
		   						</div>
		   					</div>
		   					
							</div>
							<!--分割线2-->
							<div class="bd_post_space2"></div>
							
							<!-- 加载预支付页面，含支付参数 -->
							<div id="prepayDiv">
								<div class="bd_post_subscribe">	
									<div class="bd_post_submit">
										<a onclick="OrderPay.toPrePayView();" class="bd_post_submit_btn">确定</a>
									</div>
									<div class="bd_post_submit_cls"></div>
								</div>
							</div>
						</div>
						<div class="bd_m_bottom"></div>										
					</div>								
				</div>
		 		<!--主体部分end-->
				<div class="bd_bottom"></div>
				
				</div>
			
			</div>
			<!-- conBody结束 内容页面加载位置-->
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束--> 
</div>
<!--页面容器 结束--> 

<script type="text/javascript">
var OrderPay = {};
OrderPay.toPrePayView = function(){
	$('#invoiceTitle').attr('disabled', true);
	$('#invoiceItems').attr('disabled', true);
	$('#mailingAddress').attr('disabled', true);
	var remark1 = native2ascii('_'+$('#invoiceTitle').val()+'_'+$('#invoiceItems').val()+'_'+$('#mailingAddress').val());
	//加载预支付页面
	$('#prepayDiv').loadPage($('#initPath').val()+'/PayController.pay?method=toPrePayView&v_business_id='+$('#v_business_id').val()+'&v_back_req_method='+$('#v_back_req_method').val()+'&v_amount='+$('#v_amount').val(),{'remark1':remark1});
}
</script>
</body>
</html>
