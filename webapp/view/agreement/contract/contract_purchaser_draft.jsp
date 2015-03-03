<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<%@page import="java.net.URLDecoder"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/contract/contract_purchaser_draft.js"></script>

<!--任务书ID-->
<input type="hidden" id="orderIds" value="<c:out value="${param.orderIds}"/>"/>

<div class="formLayout form2Pa">
  <form id="contractForm" method="post">
  <h4><span>合同信息</span></h4>
    <ul>
      <li class="fullLine"><label>合同名称：</label>
      		<input name="contractName" id="contractName" class="required" maxlength="50" size="50"/>
      		<span class="eleRequired">*</span>
      </li>
      <li><label>甲方（采购人）：</label>
      		<span id="buyerName">${orgInfo.orgName }</span>
      		<input id="buyerId" name="buyer.objId" type="hidden" value="${orgInfo.objId }"/>
      </li>
      <li><label>乙方（供应商）：</label>
      		<span id="supplierName"><%=(-1!= request.getHeader("USER-AGENT").indexOf("MSIE"))?new String(request.getParameter("supplierName").getBytes("ISO-8859-1"),"gbk"):new String(request.getParameter("supplierName").getBytes("ISO-8859-1"),"UTF-8")%></span>
      		<input id="supplierId" name="supplier.objId" type="hidden" value="<c:out value="${param.supplierId}"/>"/>
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
      <li class="formTextarea"><label>采购人意见：</label>
      		<textarea name="buyerOption" id="buyerOption" maxlength="100"></textarea>
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
 		<button  id="save" type="button"><span>保存</span></button>
   		<button  id="submit" type="button"><span>提交</span></button>			
   	 	<button  id="return" type="button" name="historyBackBtn" ><span>返回</span></button>  
</div>