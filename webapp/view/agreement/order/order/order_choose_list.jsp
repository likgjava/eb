<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/order/order/order_choose_list.js"></script>
<div class="formLayout">
<input type="hidden" id="_PARAMS" value="<c:out value="${param.params}"/>"/>
<h4><span>订单信息</span></h4>
  <table class="frontTableList" id="orderChooseList">
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
<!-- 按钮开始 -->
<div class="conOperation">	
	<button  id="OK" type="button"><span>确定</span></button>
 	<button  id="CLOSE" type="button"><span>关闭</span></button>
</div>
