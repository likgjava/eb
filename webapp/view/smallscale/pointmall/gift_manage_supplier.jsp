<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/smallscale/pointmall/gift_manage_supplier.js"></script>
<!-- 查询条件 -->
<div class="conSearch">
<h4><span><spring:message code="globe.query"/></span></h4>	
<form id="GiftManageSearchForm">
	<ul >
		<li>
			<label>礼品名称：</label>
			<input name="giftName" id="giftName">
			<input type="hidden" name="giftName_op" id="giftName_op" value="like">
		</li>
		<li>
			<label>礼品编号：</label>
			<input name="giftCode" id="giftCode">
			<input type="hidden" name="giftCode_op" id="giftCode_op" value="like">
		</li>
		<li>
			<label>创建时间：</label>
			<input type="text" name="startDate" id="startDate">&nbsp;到<input type="text" name="endDate" id="endDate">
		</li>
		<li class="operationBtnDiv">
			<button type="button" onclick="GiftManageList.getTableList();"><span><spring:message code="globe.query" /></span></button>
		</li>
	</ul>
</form>
</div>
<div id="epsTabs">
  <div id="giftInfo">
	<table class="frontTableList" id="giftManageList">
		<thead>
			<tr>
				<th class="operation">图片</th>
				<th class="left omission" omiLength="15">礼品名称</th>
				<th class="left omission" omiLength="15">礼品编号</th>
				<th class="left omission" omiLength="15">礼品系列</th>
				<th class="center">礼品类型</th>
				<th class="center">兑换总数</th>
				<th class="center">启用状态</th>
				<th class="date">创建时间</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
  </div>
</div>
