<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<c:forEach var="biddingRecord" items="${biddingRecordList}">
<div class="package_details_intro layoutfix">
	<div class="b_bd layoutfix">
		<div class="pripackage_date_main">
			<h5><a><fmt:formatDate value="${biddingRecord.barginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></a></h5>
			<table class="pripackage_date_list" style="width: 100%;">
			<thead>
				<tr>
			  		<th>商品名称</th>
					<th>协议价（元）</th>
					<th>报价（元）</th>
					<th>数量</th>
					<th>金额（元）</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="totalPrice" value="0" />
				<c:forEach var="biddingRecordItem" items="${biddingRecord.biddingRecordItemSet}">
				<tr>
					<td>${biddingRecordItem.goods.productName }</td>
					<td><fmt:formatNumber value="${biddingRecordItem.requireItem.agreePrice }" pattern="#,##0.00#" /></td>
					<td><fmt:formatNumber value="${biddingRecordItem.goodsPrice }" pattern="#,##0.00#" /></td>
					<td>${biddingRecordItem.requireItem.goodsQty }</td>
					<td><fmt:formatNumber value="${biddingRecordItem.goodsTotal }" pattern="#,##0.00#" /></td>
					<c:set var="totalPrice" value="${totalPrice + biddingRecordItem.goodsTotal}" />
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5" style="text-align: right; padding-right: 20px;">总计：<fmt:formatNumber value="${totalPrice}" pattern="#,##0.00#" />元</td>
				</tr>
			</tbody>
			</table>
		</div>	
	</div>
</div>
</c:forEach>
<c:if test="${empty biddingRecordList || fn:length(biddingRecordList) <= 0}">
	<div>暂无报价信息！</div>
</c:if>

<div class="conOperation">
	<button type="button" id="biddingRecordCloseBut"><span>关闭</span></button>
</div>

<script>
$(document).ready(function(){
	//关闭
	$("#biddingRecordCloseBut").click(function(){
		$('.epsDialogClose').trigger('click');
	});
})
</script>
