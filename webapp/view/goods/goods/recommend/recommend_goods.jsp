<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goods/recommend/recommend_goods.js"></script>

<input type="hidden" id="goodsIds" />
<input type="hidden" id="recommendSuccess" value="0" />
<!-- 查询条件 -->
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<!-- 未推荐商品搜索表单 -->
	<form id="noRecommendGoodsSearchForm">
		<ul >
			<li>
				<label>商品分类：</label>
				<input type="text" id="goodsClassName" class="sysicon siSearch" />
				<input type="hidden" id="goodsClassId" name="goodsClassCode" />
			</li>
			<li>
				<label>商品品牌：</label>
				<input type="text" id="goodsBrandName" name="goodsBrandName" />
			</li>
			<li>
				<label for="productName"> 商品名称：</label>
				<input type="text" id="productName" name="productName" />
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query1"><span>查询</span></button>
			</li>
		</ul>
	</form>
	<!-- 已推荐商品搜索表单 -->
	<form id="recommendGoodsSearchForm" class="hidden">
		<ul >
			<li>
				<label for="goods.goodsClass.goodsclassName">商品品牌：</label>
				<input type="text" name="goods.goodsBrand.brandName" id="brandName">
				<input type="hidden" name="goods.goodsBrand.brandName_op" value="like">
			</li>
			<li>
				<label for="goods.productName"> 商品名称：</label>
				<input type="text" name="goods.productName" id="productName">
				<input type="hidden" name="goods.productName_op" value="like">
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
		<li id="recommendGoodsBatchLi">
			批量推荐商品请点击
			<span class="sysicon siAdd">
				<a id="recommendGoodsBatch" style="hide-focus: true" href="javascript:void(0);"><strong>批量推荐商品</strong></a>
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

<!-- 商品列表 -->
<div id="epsTabs">
  	<ul>
	    <li>
	      <a href="#goodsInfos1" id = "tabs_toNoRecommend" class="refreshData"><span>未推荐</span></a>
	    </li>
	    <li>
	      <a href="#goodsInfos2" id = "tabs_toRecommend" class="refreshData"><span>已推荐</span></a>
	    </li>
	</ul>
	<!-- 未推荐商品列表 -->
	<div id="goodsInfos1">
		<table class="frontTableList" id="goodsList1">
			<thead>
				<tr>
					<th class="omission">商品名称</th>
					<th class="omission">规格型号</th>
					<th class="omission">商品品牌</th>
					<th class="omission">商品分类</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- 已推荐商品列表 -->
	<div id="goodsInfos2">
		<table class="frontTableList" id="goodsList2">
			<thead>
				<tr>
					<th class="omission">商品名称</th>
					<th class="omission">规格型号</th>
					<th class="omission">商品品牌</th>
					<th class="omission">推荐理由</th>
					<th class="operation">排序</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
