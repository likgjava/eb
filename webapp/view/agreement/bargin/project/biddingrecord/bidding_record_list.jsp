<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/agreement/bargin/project/biddingrecord/bidding_record_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="biddingRecordDetailSearchForm">
		<input type="hidden" name="supplier.objId" value="${supplierId}" />
		<ul>
		    <li>
				<label>商品名称：</label>
				<input type="text" name="goods.productName">
				<input type="hidden" name="goods.productName_op" value="like">
		    </li>
		    <li>
				<label>项目名称：</label>
				<input type="text" name="project.projName" value="">
				<input type="hidden" name="project.projName_op" value="like">
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="BiddingRecordDetailList.getBiddingRecordDetailList();"><span>查询</span></button>
		    </li>
		</ul>
	 </form>
</div>

<!--报价记录列表-->
<div>
	<table class="frontTableList" id="biddingRecordDetailList">
		<thead>
			<tr>
				<th>商品名称</th>
				<th>项目名称</th>
				<th class="money">报价</th>
				<th class="time">报价时间</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
