<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/contract/contract_supplier_confirm.js"></script>

<!--操作类型-->
<input type="hidden" id="type" value="<c:out value="${param.type}"/>"/>

<div class="formLayout form2Pa">
  <form id="contractForm" method="post">
  <!--任务书ID-->
  <input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
  
  <h4><span>合同信息</span></h4>
    <ul>
      <li class="fullLine"><label>合同名称：</label>
      		<input name="contractName" id="contractName" maxlength="50" size="50" class="required"/>
      		<span class="eleRequired">*</span>
      </li>
      <li class="fullLine"><label>合同编号：</label>
      		<span id="contractNo"></span>
      		<input type="hidden" name="buyerConfirmStatus" id="buyerConfirmStatus"/>
  			<input type="hidden" name="supplierConfirmStatus" id="supplierConfirmStatus"/>
      </li>
      <li><label>甲方（采购人）：</label>
      		<span id="buyer.orgName"></span>
      		<input id="buyer.objId" name="buyer.objId" type="hidden"/>
      </li>
      <li><label>乙方（供应商）：</label>
      		<span id="supplier.orgName"></span>
      		<input id="supplier.objId" name="supplier.objId" type="hidden"/>
      </li>
      <li><label>生效日期：</label>
      		<input name="contractBeginTime" id="contractBeginTime" tabType="datesimple" class="required"/>
      		<span class="eleRequired">*</span>
      </li>
      <li><label>失效日期：</label>
      		<input name="contractEndTime" id="contractEndTime" tabType="datesimple" class="required"/>
      		<span class="eleRequired">*</span>
      </li>
      <li><label>签订日期：</label>
      		<input name="contractSignedTime" id="contractSignedTime" tabType="datesimple" class="required"/>
      		<span class="eleRequired">*</span>
      </li>
      <li><label>合同总额（元）：</label>
      		<span>￥</span><span id="total" tabType="money"></span>
      </li>
      <li><label>交货时间：</label>
      		<input name="deliveryTime" id="deliveryTime" class="required"/>
      		<span class="eleRequired">*</span>
      </li>
      <li><label>交货地点：</label>
      		<input name="deliveryPlace" id="deliveryPlace" class="required"/>
      		<span class="eleRequired">*</span>
      </li>
      <li class="fullLine"><label>附件：</label>
      		<div id="contractFile" class="uploadFile"></div>
      </li>   
      <li class="fullLine"><label>采购人意见：</label>
      		<span id="buyerOption"></span>
      </li>
      <li class="formTextarea"><label>供应商意见：</label>
      		<textarea name="supplierOption" id="supplierOption" maxlength="100"></textarea>
      </li>
    </ul>
  </form>
<br />   
<!--  订单商品列表-->
  <h4><span>订单信息</span></h4>
</div>
  <div class="functionBtnDiv right">
  		<button id="addOrder" class="add"><span>增加订单</span></button>
   		<button id="delOrder" class="delete"><span>删除订单</span></button>	
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
 <div class="conOperation">	
 		<button  id="save" type="button" class="hidden"><span>保存</span></button>
 		<button  id="submit" type="button" class="hidden"><span>提交</span></button>		
   		<button  id="back" type="button" class="hidden"><span>退回</span></button>
   		<button  id="cancel" type="button"><span>取消合同</span></button>			
   	 	<button  type="button" name="historyBackBtn" ><span>返回</span></button>  
   	 	<button  id="printContract" type="button" ><span>打印合同</span></button> 
</div>

 <div id="operatorList"></div>