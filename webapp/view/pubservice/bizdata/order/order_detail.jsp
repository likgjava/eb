<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/bizdata/order/order_detail.js"></script>

<div class="formLayout form2Pa">
<form:form id="OrderSupplierDetail" method="post" modelAttribute="order"> 
		<form:hidden path="objId"></form:hidden>
		<input type="hidden" id="type" value="<c:out value="${type}"/>"/>
     	<h4><span>订单信息</span></h4>
		<ul>
			<li class="fullLine"><label>订单编号：</label>${order.orderNo}</li>
			<li><label>采购人：</label><a href="javascript:void(0);" onclick="linkInfo('${order.buyerId}');return false;">${order.buyerName}</a><form:hidden path="buyerId"></form:hidden></li>
			<li><label>供应商：</label><a href="javascript:void(0);" onclick="linkInfo('${order.supplierId}');return false;">${order.supplierName}</a><form:hidden path="supplierId"></form:hidden></li>
			<li><label for="orderForm.useStatusCN">状态：</label><span id="useStatus">${order.useStatusCN}</span></li>
			<li><label>来源：</label>${order.srcApp}</li>
			<li><label>创建人：</label>${order.createUser.usName}</li>
			<li><label>创建日期：</label><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
			<li><label for="orderForm.supplierConfirmStatusCN">供应商确认状态：</label><span id="supplierConfirmStatus">${order.supplierConfirmStatusCN}</span></li>
			<li><label for="orderForm.buyerConfirmStatusCN">采购人确认状态：</label><span id="buyerConfirmStatus">${order.buyerConfirmStatusCN}</span></li>
			<li><label>采购人确认时间：</label><fmt:formatDate value="${order.buyerConfirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
			<li><label>采购人确认人：</label>${order.buyerConfirmUser.usName}</li>
			<li><label>供应商确认时间：</label><fmt:formatDate value="${order.supplierConfirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
			<li><label>供应商确认人：</label>${order.supplierConfirmUser.usName}</li>
			<li class="fullLine formTextarea"><label>订单备注：</label>${order.memo}</li>
			<c:if test="${order.contract != null}">
				<li id="contract" class="fullLine"><label>相关合同：</label>
					<a href="javascript:void(0);" onclick="OrderSupplierDetail.openContract('${order.contract.objId}');return false;">${order.contract.contractNo}</a>
				</li>
			</c:if>
		</ul>
</form:form>
</div>

<div class="formLayout form2Pa">
<!--  订单商品列表-->
<h4><span>商品信息</span></h4>
	<table class="frontTableList" id="OrderItemList">
		<thead>
			<tr>
				<th>商品名称</th>
				<th>商品型号</th>
				<th class="money">市场价</th>
				<th class="money">协议价</th>
				<th class="money">单价</th>
				<th class="amount">数量</th>
				<th class="money">金额</th>
			</tr>
		</thead>
	</table>
</div>
<div class="billingInfo">
	<ul>
		<li>数量总计：<strong><span id="countGoods">0</span></strong>件</li>	
		<li>金额总计：<strong>￥<span id="totalMoney">0.00</span></strong>元</li>
		<li>节省金额：<strong><span id="saveAmount">0</span></strong>元</li>
	</ul>
</div>
 <div class="conOperation">
 	<button type="button" name="enterBackBtn" type="button" tabindex="20" onclick="common.open('${project.srcUrl}','${project.objId}');"><span>进入</span></button>
	<button type="button" name="historyBackBtn" type="button" tabindex="20""><span><spring:message code="globe.return"/></span></button>
 </div>
