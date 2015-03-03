<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/order/s_order_purchaser_list.js"></script>
 
<input type="hidden" id="currentTab" value="0">
 
<!-- 查询条件 -->
<div class="conSearch">
  <h4><span><spring:message code="globe.query"/></span></h4>
  <form id="purchaserOrderForm">
	  <ul>
	  	<li>
	  		<label>订单编号：</label>
	        <input name="orderNo" type="text" >
	       	<input name="orderNo_op" type="hidden" value="like">	
	  	</li>
	  	<li>
	  		<label>订单时间：</label>
			<input name="startDate" id="startDate">&nbsp;&nbsp;到
	        <input name="endDate" id="endDate">
	  	</li>
	  	<li>
	  		<label>供应商：</label>
	  		<input name="supplier.orgName" type="text" >
			<input name="supplier.orgName_op" type="hidden" value="like">	
	  	</li>
	  	<li class="operationBtnDiv right">
	        <button type="button" id="query"><span><spring:message code="globe.query"/></span></button>
	   	</li>
	  </ul>
   </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <ul>
  	<li>
      <a href="#orderPurchaserListInfo1" id = "tabs_toSubmit" class="refreshData"><span>待提交订单</span></a>
    </li>
    <li>
      <a href="#orderPurchaserListInfo1" id = "tabs_toSupSure" class="refreshData"><span>待对方确认订单</span></a>
    </li>
    <li>
      <a href="#orderPurchaserListInfo1" id = "tabs_toSupAdjust" class="refreshData"><span>待调整订单</span></a>
    </li>
    <li>
      <a href="#orderPurchaserListInfo2" id = "tabs_toSupdone" class="refreshData"><span>已确认订单</span></a>
    </li>
  </ul>
  <div id="orderPurchaserListInfo1">
    <!-- 订单列表 -->
    <table class="frontTableList" id="OrderPurchaserList1">
      <thead>
      	<tr>
          <th class="left">订单编号</th>
          <th class="left omission" omiLength="5">采购品目</th>
          <th class="left omission" omiLength="5">供应商</th>
          <th class="amount">总数量</th>
          <th class="money">总金额（元）</th>
          <th class="date">创建时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
  
  <div id="orderPurchaserListInfo2">
  	<div class="functionBtnDiv">
    	<span class="showContract"><input type="checkbox" id="onlyNoContract" >只显示未起草合同订单</span>
  		<button id="addContract" class="add"><span>起草合同</span></button>
    </div>
    <!-- 订单列表 -->
    <table class="frontTableList" id="OrderPurchaserList2">
      <thead>
      	<tr>
          <th class="left">订单编号</th>
          <th class="left omission"  omiLength="5">采购品目</th>
          <th class="left omission"  omiLength="5">供应商</th>
          <th class="amount">总数量</th>
          <th class="money">总金额（元）</th>
          <th class="date">创建时间</th>
          <th class="left">合同编号</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        
        

 
