<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/serve/hotel/recommend/recommend_hotel_manage_list.js"></script>

<input type="hidden"" name="recommendReason" id="recommendReason" value=""/>
<!-- 查询条件 -->
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<!-- 未推荐酒店搜索表单 -->
	<form id="noRecommendHotelSearchForm">
		<ul >
			<li>
				<label for="hotelName">酒店名称：</label>
				<input type="text" name="hotelName" id="hotelName">
		      	<input type="hidden" name="hotelName_op" value="like">
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query1"><span>查询</span></button>
			</li>
		</ul>
	</form>
	<!-- 已推荐酒店搜索表单 -->
	<form id="recommendHotelSearchForm" class="hidden">
		<ul >
			<li>
				<label for="hotelName">酒店名称：</label>
				<input type="text" name="hotel.hotelName" id="hotelName">
		      	<input type="hidden" name="hotel.hotelName_op" value="like">
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query2"><span>查询</span></button>
			</li>
		</ul>
	</form>
</div>

<!-- 操作 -->
<div class="formTips attention">
	<ul>
		<li id="recommendHotelBatchLi">
			批量推荐酒店请点击
			<span class="sysicon siAdd">
				<a id="recommendHotelBatch" style="hide-focus: true" href="javascript:void(0);"><strong>批量推荐酒店</strong></a>
			</span>
		</li>
		<li id="cancleRecommendBatchsLi" class="hidden">
			批量取消推荐请点击
			<span class="sysicon siAdd">
				<a id="cancleRecommendBatch" style="hide-focus: true" href="javascript:void(0);"><strong>批量取消推荐</strong></a>
			</span>
		</li>
	</ul>
</div>

<!-- 酒店列表 -->
<div id="epsTabs">
  	<ul>
	    <li>
	      <a href="#hotelInfos1" id = "tabs_toNoRecommend" class="refreshData"><span>未推荐</span></a>
	    </li>
	    <li>
	      <a href="#hotelInfos2" id = "tabs_toRecommend" class="refreshData"><span>已推荐</span></a>
	    </li>
	</ul>
	<!-- 未推荐酒店列表 -->
	<div id="hotelInfos1">
		<table class="frontTableList" id="hotelList1">
			<thead>
				<tr>
					<th class="operation">图片</th>
					<th class="omission">酒店名称</th>
					<th class="omission">地址</th>
					<th class="center">星级</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- 已推荐酒店列表 -->
	<div id="hotelInfos2">
		<table class="frontTableList" id="hotelList2">
			<thead>
				<tr>
					<th class="operation">图片</th>
					<th class="omission">酒店名称</th>
					<th class="omission">推荐理由</th>
					<th class="center">排序</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
