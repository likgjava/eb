<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goodsprice/price_goods_list.js"></script>
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
                    <input type="text" name="brandName" id="brandName">
                </li>
                <li>
                  <label>商品分类：</label>
                  <input type="text" name="className" id="className">
                </li>
                <li class="operationBtnDiv">
       				 <button type="button" onclick="priceGoodsList.getPriceGoodsList()"><span><spring:message code="globe.query" /></span></button>
     			 </li>
              </ul>
		</form>
	</div>
</div>


<div id="epsTabs">
		<!-- 商品列表 -->
		<table class="frontTableList" id="priceGoodsList">
			<thead>
				<tr>
					<th class="operation">图片</th>
					<th >商品名称</th>
					<th >规格型号</th>
					<th >品牌</th>
					<th >分类</th>
					<th class="center">有效行情数</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
</div>