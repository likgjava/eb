<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/mamagement/purchase_agreement_list.js"></script>
 
<!-- 查询条件 -->
<div class="conSearch">
	<form id="agreementSearchForm">
    <h4><span>搜索</span></h4>
    <ul>
      <li>
        <label  for="input01">协议名称：</label>
        <input type="text" id="name" name="name" value=""/>
        <input type="hidden" id="name_op" name="name_op" value="like"/>
      </li>
      <li>
      	<label for="input02">经销商：</label>
      	<input type="text" id="supplier.orgName" name="supplier.orgName" />
      	<input type="hidden" id="supplier.orgName_op" name="supplier.orgName_op" value="like"/>
      </li>
      <li >
        <label>协议期间：</label>
        <input type="text" id="period.periodName" name="period.periodName" />
        <input type="hidden" id="period.periodName_op" name="period.periodName_op" value="like"/>
      </li>
      <li class="operationBtnDiv">
        	<button id="agreementSearch" type="button"><span>查询</span></button>
      </li>
    </ul>
    </form>
  </div>

<div class="functionBtnDiv right">
			<button onclick="PurchaseAgreementList.add()"><span>新增</span></button>
        	<button onclick="PurchaseAgreementList.updateAgreementGoods()"><span>维护协议商品</span></button>
        	<button id="updateVenderQualify" class="hidden" onclick="PurchaseAgreementList.updateVenderQualify()"><span>维护供货商资格</span></button>
        	<button onclick="PurchaseAgreementList.del()"><span>删除</span></button>
</div>

<div id="epsTabs" class="">
  <ul>
    <li>
      <a href="#contractInfo" id = "tabs_submit" ><span>有效协议</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_todo" ><span>无效协议</span></a>
    </li>
  </ul>
  <div id="contractInfo">
    <!-- 协议列表 -->
    <table class="frontTableList" id="PurchaseAgreementList">
      <thead>
        <tr>
          <th>&nbsp;&nbsp;协议名称&nbsp;&nbsp;</th>
          <th>协议经销商</th>
          <th>协议期间</th>
          <th>协议区间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
