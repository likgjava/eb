<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/goods/goodsprice/goods_price_aud_list.js"/>'></script>

<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="priceSearchForm" >
			<ul>
			    <li>
					<label for="brandCode">商品名称：</label>
					<input type="text" name="goodsPriceSupplier.goods.productName" value="">
					<input type="hidden" name="goodsPriceSupplier.goods.productName_op" value="like">
			    </li>
			    <li>
					<label for="brandName">供应商名称：</label>
					<input type="text" name="goodsPriceSupplier.supplier.orgName" value="">
					<input type="hidden" name="goodsPriceSupplier.supplier.orgName_op" value="like">
			    </li>
			    <li>
					<label for="brandName">区域名称：</label>
					<input type="text" name="district.name" value="">
					<input type="hidden" name="district.name_op" value="like">
			    </li>
			    
			    <li class="operationBtnDiv">
			        <button id = "priceSearch" type="button" onclick="goodsPriceAuditList.getPriceList()"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>


<!-- 供应商行情Id -->
<div id="epsTabs">
	<ul>
		<li>
			<a href="#price" class="refreshData"><span>待审核</span></a>
		</li>
		<li>
			<a href="#price" class="refreshData"><span>审核通过</span></a>
		</li>
		
	</ul>
	<!-- 行情列表 -->
	<div id="price">
	<table class="frontTableList" id="priceList">
		<thead>
			<tr>
				<th class="omission" omiLength="5">商品名称</th>
				<th class="omission" omiLength="5">供应商名称</th>
				<th class="money">市场价</th>
				<th class="money">折扣率</th>
				<th class="money">协议价</th>
				<th class="center">生效日期</th>
				<th class="center">失效日期</th>
				<th class="left">所属区域</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	</div>
</div>
