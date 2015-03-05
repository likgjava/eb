<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/smallscale/groupbuying/group_buyer_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="groupBuyerSearchForm">
		<input type="hidden" name="orgInfo.objId" value="${orgInfoId}">
		<input type="hidden" name="createUser.objId" value="${userId}">
		<ul>
		    <li>
				<label>团购名称：</label>
				<input type="text" name="groupBuying.name">
				<input type="hidden" name="groupBuying.name_op" value="like">
		    </li>
		    <li>
				<label>商品名称：</label>
				<input type="text" name="groupBuying.goods.productName" value="">
				<input type="hidden" name="groupBuying.goods.productName_op" value="like">
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="GroupBuyerList.getGroupBuyerList();"><span>查询</span></button>
		    </li>
	  </ul>
	 </form>
</div>

<!--团购采购人信息列表-->
<div id="groupBuyerListInfo">
	<table class="frontTableList" id="groupBuyerList">
		<thead>
			<tr>
				<th class="omission" omiLength="20">团购名称</th>
				<th class="omission" omiLength="20">商品名称</th>
				<th class="money">团购价</th>
				<th class="amount">团购数量</th>
				<th class="center">支付状态</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
