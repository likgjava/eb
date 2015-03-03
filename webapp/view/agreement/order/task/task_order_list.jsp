<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/task/task_order_list.js"></script>

<input type="hidden" name="protaskItemId" id="protaskItemId" value="<c:out value="${param.protaskItemId}"/>"/>

<!-- 订单详情 -->
<table class="frontGoodsTableList" id="taskOrderList">
<caption>订单商品</caption>
    <thead>
	<tr>
		<th class="center">订单编号</th>
		<th>商品名称</th>
		<th>商品型号</th>
		<th class="money">市场价</th>
		<th class="money">协议价</th>
		<th class="money">单价</th>
		<th class="amount">数量</th>
		<th class="money">金额</th>
	</tr>
    </thead>
    <tbody>  
    </tbody>
</table>
<div class="billingInfo">
<ul>
	<li>数量总计：<strong><span id="countGoods">0</span></strong>件</li>
	<li>金额总计：<strong>￥<span id="totalMoney">0.00</span></strong>元</li>
	<li>节省金额：<strong>￥<span id="saveAmount">0.00</span></strong>元</li>
</ul>
</div>
<div class="formLayout form2Pa">
</div>
<div class="conOperation">	
 		<button  id="taskItemClose" type="button"><span>关闭</span></button>
</div>
