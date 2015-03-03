<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!-- 订单详情 -->
<div class="formLayout form2Pa">
  <form id="orderForm" method="post">
  <input type="hidden" id="orderId" value="<c:out value="${param.objId}"/>"/>
  <h4><span>订单信息</span></h4>
    <ul>
      <li><label>订单编号：</label><span id="">${order.orderNo }</span></li>
      <li><label>订单状态：</label><span id="useStatus"><c:if test="${order.useStatus=='00' }">临时</c:if><c:if test="${order.useStatus=='01' }">正式</c:if><c:if test="${order.useStatus=='02' }">作废</c:if></span></li>
      <li><label>采购人：</label><span id="buyer.orgName">${order.buyer.orgName }</span></li>
      <li><label>供应商：</label><span id="supplier.orgName">${order.supplier.orgName }</span></li>
      <li class="fullLine"><label>创建时间：</label><span id="createTime" tabType="datesimple">${order.createTime }</span></li>      
      <li><label>创建人：</label><span id="createUser.emp.name">${order.createUser.emp.name }</span></li>
      <li><label>供应商联系人：</label><span id="supplier.company.contact">${order.supplier.company.contact }</span></li>      
      <li><label>采购人确认时间：</label><c:if test="${order.buyerConfirmDate == null }">未确认</c:if><c:if test="${order.buyerConfirmDate != null}"><span id="buyerConfirmDate">${order.buyerConfirmDate }</span></c:if></li>
      <li><label>供应商确认时间：</label><c:if test="${order.supplierConfirmDate == null }">未确认</c:if><c:if test="${order.supplierConfirmDate != null }"><span id="supplierConfirmDate">${order.supplierConfirmDate }</span></c:if></li>
      <li><label>采购人确认人：</label><c:if test="${order.buyerConfirmUser == null }">未确认</c:if><c:if test="${order.buyerConfirmUser != null }"><span id="buyerConfirmUser.emp.name">${order.buyerConfirmUser.emp.name }</span></c:if></li>
      <li><label>供应商确认人：</label><c:if test="${order.supplierConfirmUser == null }">未确认</c:if><c:if test="${order.supplierConfirmUser != null }"><span id="supplierConfirmUser.emp.name">${order.supplierConfirmUser.emp.name }</span></c:if></li>
      <li><label>采购人确认状态：</label>
      	<span id="buyerConfirmStatus">
      		<c:if test="${order.buyerConfirmStatus=='01'}">已确认</c:if>
      		<c:if test="${order.buyerConfirmStatus=='00' && order.supplierConfirmStatus=='00'}">待提交</c:if>
      		<c:if test="${order.buyerConfirmStatus=='00' && order.supplierConfirmStatus=='02'}">待调整</c:if>
      	</span>
      </li>
      <li><label>供应商确认状态：</label>
      	<span id="supplierConfirmStatus">
      		<c:if test="${order.supplierConfirmStatus=='01'}">已确认</c:if>
      		<c:if test="${order.buyerConfirmStatus=='00' && order.supplierConfirmStatus=='00'}">采购人还未提交</c:if>
      		<c:if test="${order.buyerConfirmStatus=='01' && order.supplierConfirmStatus=='00'}">待确认</c:if>
      		<c:if test="${order.buyerConfirmStatus=='00' && order.supplierConfirmStatus=='02'}">退回</c:if>
      	</span>
      </li>
      <li class="fullLine"><label>备注：</label><span id="memo">${order.memo}</span></li>  
      <li class="fullLine"><label>合同编号：</label><c:if test="${order.contract.objId != null }"><span id="contract.contractNo"><a href="javascript:void(0);" id="viewContract">${order.contract.contractNo }</a></span></c:if><c:if test="${order.contract.objId == null }">还未生成合同</c:if></li>     
      <li class="fullLine"><label>品目名称：</label><span id="categoryNames">${order.categoryNames}</span></li>    
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
				<th>供应商报价</th>
				<th class="money">单价</th>
				<th>商品数量</th>
				<th>金额</th>
			</tr>
		</thead>
		<tbody>	 
			<c:forEach var = "orderItem" items="${orderItemList}">
				<tr class="goodsInfo" id="${orderItem.objId}">
					<td>
						<img class="goodsImg" src="<c:url value="AttachmentController.do?method=showImg&objId=${orderItem.goods.picture}" />" >
						<a href="javascript:void(0);" onclick="OrderAdminDetail.showDetail('${orderItem.goods.objId}');return false;" >${orderItem.goods.productName } &nbsp;${orderItem.goods.productCode }</a>
						<div class="fitting"><em>选配：</em>
								<c:forEach var = "orderGoodsOption" items="${orderItem.orderGoodsOptions}">
									${orderGoodsOption.optionalFitting.optionContent } /
									<c:set var="cellTotal" value="${cellTotal + orderGoodsOption.optPrice }"/>
								</c:forEach>
						</div>
					</td>
					<td class="money">
						<span id="mark"><fmt:formatNumber value="${orderItem.marketPrice }" pattern="#,##0.00#" /></span>
						<c:set var="marktTotal" value="${marktTotal+(orderItem.marketPrice*orderItem.goodsQty)}"/>
					</td>
					<td class="money">
						<fmt:formatNumber value="${orderItem.agreePrice }" pattern="#,##0.00#" />
						<c:set var="cellTotal" value="${cellTotal + orderItem.agreePrice }"/>
					</td>
					<td class="money">
						<span id="itmeprice"><fmt:formatNumber value="${orderItem.goodsPrice }" pattern="#,##0.00#" /></span>
					</td>
					<td class="money">
						<span id="itemqty">${orderItem.goodsQty }</span>
						<c:set var="countGoods" value="${countGoods+orderItem.goodsQty}"/>
					</td>
					<td>
						<span id="goodsTotal"><fmt:formatNumber value="${orderItem.goodsTotal }" pattern="#,##0.00#"/></span>
						<input type="hidden" name="cellTotal" value="${cellTotal}">
						<c:set var="totalMoney" value="${totalMoney + orderItem.goodsTotal}"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="7">
					<ul>
							<li>数量总计：<strong><span id="countGoods">${countGoods}</span></strong>件</li>
							<li>金额总计：<strong>￥<span id="totalMoney"><fmt:formatNumber value="${totalMoney}" pattern="#,##0.00#" /></span></strong>元</li>
							<li>节省金额：<strong>￥<span id="saveAmount"><fmt:formatNumber value="${marktTotal-totalMoney }" pattern="#,##0.00#" /></span></strong>元</li>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
<!-- 订单列表结束 -->

 <div class="formLayout form2Pa">
</div>  

<div class="conOperation">	  <input type="hidden" id="currentTabNum" value="${param.currentTabNum}"/> 		
 		<button name="historyBackBtn" id="historyBackBtn" type="button" ><span>返回</span></button>  
</div>

<script>
//返回事件
$('#historyBackBtn').click(function(){
	$("#returnUrl").val($("#initPath").val()+"/view/agreement/order/order/order_monitor_list.jsp?currentTabNum=" + $('#currentTabNum').val());
});

//查看合同
$('#viewContract').click(function(){
	$("#returnUrl").val($("#initPath").val()+'/OrderController.do?method=toOrderForm&navigate=monitorDetail&type=draft_contract&objId='+$('#orderId').val()+'&currentTabNum='+$('#currentTabNum').val());
	$('#conBody').loadPage($('#initPath').val()+'/AgContractController.do?method=toContractDetail&forType=admin&objId=${order.contract.objId }');
});

//显示商品详情
var OrderAdminDetail  = {};
OrderAdminDetail.showDetail = function(goodsId) {
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&dailogId=showGoodsDetail&isShowPic=true&objId="+goodsId
	})
}
</script>