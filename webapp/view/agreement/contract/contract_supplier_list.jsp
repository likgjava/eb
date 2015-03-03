<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/contract/contract_supplier_list.js"></script>
 
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="contractForm">
	    <ul>
	      <li>
	        <label>合同编号：</label>
			<input name="contractNo" type="text" >		
	      </li>
	      <li>
	        <label for="contractName">合同名称：</label>
		  	<input name="contractName" type="text" >
		  	<input name="contractName_op" type="hidden" value="like">			
	      </li>
	      <li>
		  		<label for="buyer.orgName">采购人：</label>
		  		<input name="buyer.orgName" type="text" >
		  		<input name="buyer.orgName_op" type="hidden" value="like">
		  </li>
		  <li>
		  		<label for="contractSignedTime">签订时间：</label>
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
      <a href="#contractInfo" id = "tabs_submit" class="refreshData"><span>待提交合同</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_todo" class="refreshData"><span>待确认合同</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_notpass" class="refreshData"><span>被退回合同</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_done" class="refreshData"><span>已签订合同</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_tocancel" class="refreshData"><span>待确认作废合同</span></a>
    </li>
    <li>
      <a href="#contractInfo" id = "tabs_cancel" class="refreshData"><span>作废合同</span></a>
    </li>
  </ul>
  <div id="contractInfo">
    <!-- 合同列表 -->
    <table class="frontTableList" id="ContractSupplierList">
      <thead>
      	<tr>
          <th class="left">合同编号</th>
          <th>合同名称</th>
          <th>采购人</th>
          <th class="amount">合同总额（元）</th>
          <th class="date">签订时间</th>
          <th class="center">合同文件</th>
          <th class="operation">操作</th>
      	</tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>  
        

 
 