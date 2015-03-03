<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/order/order_item_detail.js"></script>

<h4><span>订单商品</span></h4>
<table class="frontTableList" id="orderItemList">
    <thead>
        <tr>
            <th>商品图片</th>
               <th>商品名称</th>
            <th class="center">商品型号</th>
            <th class="money">市场价</th>
            <th class="money">单价</th>
            <th class="amount">数量</th>
            <th class="money">金额</th>
        </tr>
    </thead>
    <tbody>  
    </tbody>
</table>