<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/smallscale/groupbuying/group_buyer_manage_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="groupBuyerSearchForm">
		<input type="hidden" name="groupBuying.objId" value="${groupBuyingId }">
		<ul>
		    <li>
				<label>收货人：</label>
				<input type="text" name="receiveName">
				<input type="hidden" name="receiveName_op" value="like">
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="GroupBuyerManageList.getGroupBuyerList();"><span>查询</span></button>
		    </li>
	  </ul>
	 </form>
</div>

<!--团购采购人信息列表-->
<div id="groupBuyerManageListInfo">
	<table class="frontTableList" id="groupBuyerManageList">
		<thead>
			<tr>
				<th>收货人</th>
				<th class="center">手机号码</th>
				<th class="amount">购买数量</th>
				<th class="center">支付状态</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
