<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/contract/contract_purchaser_view.js"></script>

<!--操作类型-->
<input type="hidden" id="type" value="<c:out value="${param.type}"/>"/>
<!--任务书ID-->
<div class="formLayout form2Pa">
  <form id="contractForm" method="post">
  <input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
  <h4><span>合同信息</span></h4>
    <ul>
      <li><label>合同编号：</label>
      		<span id="contractNo"></span>
      </li>
      <li><label>合同名称：</label>
      		<span id="contractName"></span>
      </li>
      <li><label>甲方（采购人）：</label>
      		<span id="buyer.orgName"></span>
      </li>
      <li><label>乙方（供应商）：</label>
      		<span id="supplier.orgName"></span>
      </li>
      <li><label>生效日期：</label>
      		<span id="contractBeginTime" tabType="datesimple"></span>
      </li>
      <li><label>失效日期：</label>
      		<span id="contractEndTime" tabType="datesimple"></span>
      </li>
      <li><label>签订日期：</label>
      		<span id="contractSignedTime" tabType="datesimple"></span>
      </li>
      <li><label>合同总额（元）：</label>
      		<span>￥</span><span id="total" tabType="money"></span>
      </li>
      <li><label>交货时间：</label>
      		<span id="deliveryTime"></span>
      </li>
      <li><label>交货地点：</label>
      		<span id="deliveryPlace"></span>
      </li>
      <li class="fullLine"><label>供应商意见：</label>
      		<span id="supplierOption"></span>
      </li>
      <li class="fullLine"><label>附件：</label>
      		<div id="contractFile" class="uploadFile"></div>
      </li>
    </ul>
  </form>

 <br /> 
<!--  订单商品列表-->
  <h4><span>订单信息</span></h4>
 </div>
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
<div class="formLayout form2Pa">
<form id="contractForm2">
   <h4><span>采购人意见</span></h4>
   <ul>
      <li class="formTextarea"><label>采购人意见：</label><textarea name="buyerOption" maxlength="100"></textarea></li>
  </ul>
  </form>
</div>
 <div class="conOperation">	
 		<button  id="sure" type="button" class="hidden"><span>确认</span></button>
   		<button  id="back" type="button" class="hidden"><span>退回</span></button>	
   		<button  id="invalid" type="button" class="hidden"><span>申请作废</span></button>
   		<button  id="agree" type="button" class="hidden"><span>确认作废</span></button>
   		<button  id="disagree" type="button" class="hidden"><span>取消作废</span></button>			
   	 	<button  id="return" type="button" name="historyBackBtn" ><span>返回</span></button>  
   	 	<button  id="printContract" type="button" ><span>打印合同</span></button> 
</div>

 <div id="operatorList"></div>