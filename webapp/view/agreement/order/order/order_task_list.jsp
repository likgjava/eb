<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/order/order_task_list.js"></script>

<!-- 任务书详情 -->
<div class="formLayout form2Pa codeTab">
  <form id="OrderTaskForm" method="post">
  <input type="hidden" name="purCategoryId" id="purCategoryId" value="<c:out value="${param.purCategoryId}"/>"/>
  <input type="hidden" name="orderItemId" id="orderItemId" value="<c:out value="${param.orderItemId}"/>"/>
  <input type="hidden" name="orderGoodsQty" id="orderGoodsQty" value="<c:out value="${param.orderGoodsQty}"/>"/>
  <input type="hidden" name="orderGoodsPrice" id="orderGoodsPrice" value="<c:out value="${param.orderGoodsPrice}"/>"/>
	<!--  订单商品列表-->
	  <h4><span>任务书条目</span></h4>
	  <table class="frontTableList" id="OrderTaskItemList">
		  <thead>
		    <tr>
		      <th class="money">预算资金（元）</th>
		      <th class="amount">采购数量</th>
		      <th class="amount">剩余数量</th>
		      <th class="money">剩余金额（元）</th>
		      <th class="money">单价（元）</th>
		      <th class="amount">抵消数量</th>
		      <th class="money">抵消金额（元）</th>
		    </tr>
		  </thead>
		  <tbody>
		  </tbody>
	  </table>
  </form>
</div>

<div class="conOperation">	
		<button  id="delOrderTask" type="button"><span>重新选择任务书</span></button>
 		<button  id="close" type="button"><span>关闭</span></button>
</div>