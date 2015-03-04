<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goodsprice/goodsaccessories_price_list.js"></script>

<form id="goodsAccessoriesPriceSearchZone" >

<input type="hidden" name="goodsPriceId" id="goodsPriceId" value="${param.goodsPriceId}"/>
<input type="hidden" name="goodsId" id="goodsId" value="${param.goodsId}"/>
<input type="hidden" name="goodsPriceSupplierId" id="goodsPriceSupplierId" value="${param.goodsPriceSupplierId}"/>

	<table class="frontTableList" id="GoodsAccessoriesPriceList">
		<thead>
			<tr>
				<th class="left">配件名称</th>
				<th class="center">数量</th>
				<th class="center">状态</th>
				<th class="center">零配件价格</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	
	<div class="conOperation">
		<button type="button" id="saveAccPrice"><span>保存</span></button>
		<button type="button" id="closeAccPrice"><span>关闭</span></button>
	</div>
</form>
