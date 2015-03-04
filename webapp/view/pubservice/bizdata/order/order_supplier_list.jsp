<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/bizdata/order/order_supplier_list.js"></script>

<!-- 查询条件 -->
<div class="conSearch">
  <h4><span><spring:message code="globe.query"/></span></h4>
  <form id="supplierOrderForm">
    <ul>
	  	<li>
	  		<label>订单编号：</label>
	  		<input name="orderNo" type="text" >
	  	</li>
  		<li>
  			<label>订单时间：</label>
  			<input name="startDate" id="startDate">&nbsp;&nbsp;到
	        <input name="endDate" id="endDate">
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
      <a href="#orderInfo" id = "tabs_toSure" class="refreshData"><span>待确认订单</span></a>
    </li>
    <li>
      <a href="#orderInfo" id = "tabs_toOppositeAdjust" class="refreshData"><span>待对方调整订单</span></a>
    </li>
    <li>
      <a href="#rderInfo" id = "tabs_done" class="refreshData"><span>已确认订单</span></a>
    </li>
  </ul>
  <div id="orderInfo">
    <!-- 订单列表 -->
    <table class="frontTableList" id="OrderSupplierList">
      <thead>
      	<tr>
          <th class="center">订单编号</th>
          <th>采购品目</th>
          <th>采购人</th>
          <th class="amount">总数量</th>
          <th class="money">总金额</th>
          <th class="date">创建时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
  
  <div id="rderInfo">
    
    <!-- 订单列表 -->
    <table class="frontTableList" id="RderSupplierList">
      <thead>
      	<tr>
          <th class="center">订单编号</th>
          <th>采购品目</th>
          <th>采购人</th>
          <th class="amount">总数量</th>
          <th class="money">总金额</th>
          <th class="date">创建时间</th>
          <th class="center">合同编号</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
        
        

 
