<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/smallscale/groupbuying/group_buying_manage_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="groupBuyingSearchForm">
		<ul>
		    <li>
				<label>团购名称：</label>
				<input type="text" name="name">
				<input type="hidden" name="name_op" value="like">
		    </li>
		    <li>
				<label>商品名称：</label>
				<input type="text" name="goods.productName" value="">
				<input type="hidden" name="goods.productName_op" value="like">
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="GroupBuyingList.getGroupBuyingList();"><span>查询</span></button>
		    </li>
		</ul>
	 </form>
</div>

<div class="formTips attention" id="meetingRoomAttention">
	<ul>
		<li>
			<em>注意：</em>新增团购请点击<a id="addGroupBuying" class="sysicon siAdd" href="javascript:void(0);"><span><strong>新增团购</strong></span></a>
		</li>
	</ul>
</div>

<!--团购信息列表-->
<div id="epsTabs">
	<ul>
		<li><a href="#groupBuyingListInfo" class="refreshData" id="tabs_noStart"><span>未开始</span></a></li>
		<li><a href="#groupBuyingListInfo" class="refreshData" id="tabs_buying"><span>正在进行</span></a></li>
		<li><a href="#groupBuyingListInfo" class="refreshData" id="tabs_over"><span>已结束</span></a></li>
	</ul>
	<div id="groupBuyingListInfo">
		<table class="frontTableList" id="groupBuyingList">
			<thead>
				<tr>
					<th class="omission" omiLength="15">团购名称</th>
					<th class="omission" omiLength="15">商品名称</th>
					<th class="money">市场价</th>
					<th class="money">团购价</th>
					<th class="amount">最低团购数</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
