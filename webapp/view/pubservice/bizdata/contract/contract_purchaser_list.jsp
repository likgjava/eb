<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/bizdata/contract/contract_purchaser_list.js"></script>
<input type="hidden" name="org" id="org" value=""/>
<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query" /></span></h4>	
<form id="ContractManageSearchForm">
	<ul >
         <li>
           <label for="contractNo">合同编号：</label>
             <input type="text" name="contractNo" id="contractNo" >
             <input type="hidden" name="contractNo_op" value="like">
         </li>
        
         <li>
           <label for="contractName"> 合同名称：</label>
             <input type="text" name="contractName" id="contractName">
             <input type="hidden" name="contractName_op" value="like">
         </li>
         <li>
           <label for="contractType">合同类型：</label>
             <select name="contractType" id="contractType">
                 <option value="">请选择</option>
                 <option value="01">项目采购</option>
                 <option value="02">协议采购</option>
             </select>
             <input type="hidden" name="contractType_op" value="like">
         </li>
         
         <li class="operationBtnDiv">&nbsp;&nbsp;&nbsp;&nbsp;
				 <button type="button" id="query"><span>查询</span></button>
	 	</li>
    </ul>
</form>
</div>
<div id="epsTabs">
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
	<table class="frontTableList" id="contractManageList">
		<thead>
			<tr>
				<th class="left">合同编号</th>
				<th class="center">合同名称</th>
				<th class="center">供应商机构名称</th>
				<th class="center">合同签订时间</th>
				<th class="center">合同文件</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
  </div>
</div>
