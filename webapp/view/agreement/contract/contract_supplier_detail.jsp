<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/contract/contract_supplier_detail.js"></script>

<!--任务书ID-->
<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>

<div class="formLayout form2Pa">
  <form id="contractForm" method="post">
  <h4><span>合同信息</span></h4>
    <ul>
      <li><label>合同编号：</label>
      		<span id="contractNo">${contract.contractNo }</span>
      </li>
      <li><label>合同名称：</label>
      		<span id="contractName">${contract.contractName }</span>
      </li>
      <li><label>甲方（采购人）：</label>
      		<span id="buyer.orgName">${contract.buyer.orgName }</span>
      </li>
      <li><label>乙方（供应商）：</label>
      		<span id="supplier.orgName">${contract.supplier.orgName}</span>
      </li>
      <li><label>生效日期：</label>
      		<span id="contractBeginTime" tabType="datesimple">${contract.contractBeginTime}</span>
      </li>
      <li><label>失效日期：</label>
      		<span id="contractEndTime" tabType="datesimple">${contract.contractEndTime}</span>
      </li>
      <li><label>签订日期：</label>
      		<span id="contractSignedTime" tabType="datesimple">${contract.contractSignedTime}</span>
      </li>
      <li><label>合同总额（元）：</label>
      		<span>￥</span><span id="total" tabType="money">${contract.total}</span>
      </li>
      <li><label>交货时间：</label>
      		<span id="deliveryTime">${contract.deliveryTime}</span>
      </li>
      <li><label>交货地点：</label>
      		<span id="deliveryPlace">${contract.deliveryPlace}</span>
      </li>
	  <c:if test="${contract.supplierOption!=null}">
      <li class="fullLine"><label>供应商意见：</label>
      		<span id="supplierOption">${contract.supplierOption}</span>
      </li>
	  </c:if>
	  <c:if test="${contract.buyerOption!=null}">
      <li class="fullLine"><label>采购人意见：</label>
      		<span id="buyerOption">${contract.buyerOption}</span>
      </li>
      </c:if>
      <li class="fullLine"><label>附件：</label>
      		<input type="hidden" value="${contract.contractFile }" name="contractFile" >
      		<div id="contractFile" class="uploadFile"></div>
      </li>
    </ul>
  </form>
	<br />   
<!--  订单商品列表-->
  <h4><span>订单信息</span></h4>
  <table class="frontTableList" id="orderList">
      <thead>
      	<tr>
          <th class="center">订单编号</th>
          <th >订购品目</th>
          <th class="amount">总数量</th>
          <th class="money">总金额</th>
          <th >创建人</th>
          <th class="date">创建时间</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
  </table>
</div>
<br>
<div class="conOperation">				
   	 	<button name="historyBackBtn" type="button" ><span>返回</span></button>  
   	 	<button id="printContract" type="button" ><span>打印合同</span></button>
</div>

