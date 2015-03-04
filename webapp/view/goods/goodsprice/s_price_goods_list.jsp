<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goodsprice/s_price_goods_list.js"></script>
<input type="hidden" id="currentOrgId" value="${currentOrgId}"/>
<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="priceSearchForm">
			<ul >
                <li>
                  <label for="productName">商品名称：</label>
                    <input type="text" name="productName" id="productName">
                </li>
				<li>
                  <label for="goodsClass.goodsclassName">商品品牌：</label>
                    <input type="text" name="goodsBrand.brandName" id="brandName">
                </li>
                <li>
                  <label>商品分类：</label>
                  <input type="text" name="goodsClass.className" id="className">
                </li>
                <li class="operationBtnDiv">
       				 <button type="button" onclick="priceGoodsList.getPriceGoodsList()"><span><spring:message code="globe.query" /></span></button>
     			 </li>
              </ul>
		</form>
	</div>
</div>


<div id="epsTabs">
	<ul>
		<li><a href="#myPriceList" id="myPriceListA" class="refreshData"><span>我的报价</span></a></li>
		<li><a href="#myPriceList" id="allGoodsListA" class="refreshData"><span>我未报价</span></a></li>
	</ul>
	
	<div id="myPriceList">
		<!-- 我提供行情 -->
		<table class="frontTableList" id="priceGoodsList">
			<thead>
				<tr>
					<th class="operation">图片</th>
					<th class="left">商品名称</th>
					<th class="left">规格型号</th>
					<th class="left">品牌</th>
					<th class="left">分类</th>
					<th class="center">有效行情数</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>