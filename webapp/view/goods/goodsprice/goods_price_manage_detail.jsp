<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/goods/goodsprice/goods_price_manage_detail.js"/>'></script>

<!-- 供应商行情Id -->
<input type="hidden" id="supplierId" name="supplierId" value="${supplierId }"/>

<form:form id="GoodsDetailForm" method="post" modelAttribute="goods">
		<form:hidden path="objId"/>
		<input type="hidden" name="currentId" id="currentId" value="${goods.currentId}"/>
		<input type="hidden" name="useStatus" id="useStatus" value="${goods.useStatus}"/>
		<input type="hidden" name="additionPicture" value="${goods.additionPicture}" />
		
	<div class="formLayout form2Pa">
		<h4 class="title"><span>商品信息</span></h4>
		<div class="k1">
			<img width="200px" height="175px" src="<c:url value="AttachmentController.do?method=showImg&objId=${goods.picture}" />">
		</div>
		<ul>
		 	<li>
		 		<label for="purCategory.categoryName">采购品目：</label>
		 		${goods.purCategory.categoryName}
		 	</li>
		 	<li>
		 		<label for="goodsClass.goodsClassName">商品分类：</label>
		 		${goods.goodsClass.goodsClassName}
		 	</li>
			<li>
		 		<label for="goodsBrand.brandName">品牌名称：</label>
		 		${goods.goodsBrand.brandName}
		 	</li>
		 	<li>
		 		<label for="measureUnit">计量单位：</label>
		 		${goods.measureUnit}
		 	</li>
		 	<li>
		 		<label for="productName">商品名称：</label>
		 		${goods.productName}
		 	</li>
		 	<li>
		 		<label for="productCode">规格型号：</label>
		 		${goods.productCode}
		 	</li>
		 	<li>
		 		<label for="productDateIssued">发布日期：</label>
		 		<fmt:formatDate value="${goods.productDateIssued}" pattern="yyyy-MM-dd"/>
		 	</li>
		 	<li>
		 		<label for="referPrice">参考价(元)：</label>
		 		<span><fmt:formatNumber value="${goods.referPrice}" pattern="#,#0.00#" /></span>
		 	</li>
		 	<li>
		 		<label for="factory">制造商：</label>
		 		${goods.factory}
		 	</li>
		 	<li>
		 		<label for="factory">产地：</label>
		 		${goods.madeIn}
		 	</li>
		 	<li class="fullLine">
		 		<label for="externalInforLink">外部链接：</label>
		 		${goods.externalInforLink}
		 	</li>
		 	
		 	<c:if test="${goods.paramInputType==02}">
		 		<li id="spec_1" class="fullLine">
	 				<label for="spec">详细配置</label>
	 				${goods.spec}
	 			</li>
		 	</c:if>
	 		
	 		<c:if test="${goods.functionIntro!=null && goods.functionIntro != ''}">
		 		<li class="fullLine">
		 				<label for="functionIntro">商品描述：</label>
		 				${goods.functionIntro}
		 		</li>
		 	</c:if>
		</ul>
	</div>
</form:form>

<div id="epsTabs2">
	<ul>
		<li>
			<a href="#price2" class="refreshData"><span>有效</span></a>
		</li>
		<li>
			<a href="#price2" class="refreshData"><span>无效</span></a>
		</li>
	</ul>
	<!-- 商品列表 -->
	<div id="price2">
	<table class="frontTableList" id="priceDetailList">
		<thead>
			<tr>
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

<div>
	<div class="conOperation"> 
  		<button class="largeBtn" id="return" type="button" onclick="goodsPriceForm.close()"><span>关闭</span></button>
	</div>
</div>





