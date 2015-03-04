<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goods/recommend/recommend_goods_brand.js"></script>

<input type="hidden" id="goodsBrandIds" />
<input type="hidden" id="recommendSuccess" value="0"/>
<!-- 查询条件 -->
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<form id="goodsBrandSearchForm">
		<ul >
			<li>
				<label>品牌编号：</label>
				<input type="text" name="goodsBrand.brandCode" id="goodsBrand.brandCode">
				<input type="hidden" name="goodsBrand.brandCode_op" value="like">
			</li>
			<li>
				<label>品牌名称：</label>
				<input type="text" name="goodsBrand.brandName" id="goodsBrand.brandName">
				<input type="hidden" name="goodsBrand.brandName_op" value="like">
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
			</li>
		</ul>
	</form>
</div>

<!-- 操作 -->
<div class="formTips attention">
	<ul>
		<li id="recommendGoodsBrandBatchLi">
			批量推荐商品品牌请点击
			<span class="sysicon siAdd">
				<a style="hide-focus: true" href="javascript:void(0);" onclick="RecommendGoodsBrand.recommendGoodsBrand();"><strong>批量推荐商品品牌</strong></a>
			</span>
		</li>
		<li id="cancleRecommendBatchsLi" class="hidden">
			批量取消推荐请点击
			<span class="sysicon siAdd">
				<a style="hide-focus: true" href="javascript:void(0);" onclick="RecommendGoodsBrand.cancleRrecommend();"><strong>批量取消推荐</strong></a>
			</span>
		</li>
	</ul>
</div>

<!-- 商品品牌列表 -->
<div id="epsTabs">
  	<ul>
	    <li>
	      <a href="#goodsBrandInfos1" id = "tabs_toNoRecommend" class="refreshData"><span>未推荐</span></a>
	    </li>
	    <li>
	      <a href="#goodsBrandInfos2" id = "tabs_toRecommend" class="refreshData"><span>已推荐</span></a>
	    </li>
	</ul>
	<!-- 未推荐商品品牌列表 -->
	<div id="goodsBrandInfos1">
		<table class="frontTableList" id="goodsBrandList1">
			<thead>
				<tr>
					<th>品牌编号</th>
					<th>品牌名称</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- 已推荐商品品牌列表 -->
	<div id="goodsBrandInfos2">
		<table class="frontTableList" id="goodsBrandList2">
			<thead>
				<tr>
					<th class="operation">品牌编号</th>
					<th>品牌名称</th>
					<th class="operation">排序</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
