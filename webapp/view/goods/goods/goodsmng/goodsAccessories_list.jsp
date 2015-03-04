<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goods/goodsmng/goodsAccessories_list.js"></script>

<form id="GoodsAccessoriesListForm" >
	<input type="hidden" id="goodsId" value="${param.goodsId}"/>
	
	<div class="formLayout form2Pa">
		<h4 class="title"><span>商品信息</span></h4>
		<div class="k1">
			<img width="200px" height="175px" src="" id="img">
		</div>
		<ul>
			<li>
		 		<label>商品名称：</label>
		 		<span id="productName"></span>
		 	</li>
		 	<li>
		 		<label>采购品目：</label>
		 		<span id="purCategory.categoryName"></span>
		 	</li>
		 	<li>
		 		<label>商品分类：</label>
		 		<span id="goodsClass.goodsClassName"></span>
		 	</li>
			<li>
		 		<label>品牌名称：</label>
		 		<span id="goodsBrand.brandName"></span>
		 	</li>
		 	<li>
		 		<label>计量单位：</label>
		 		<span id="measureUnit"></span>
		 	</li>
		 	<li>
		 		<label>规格型号：</label>
		 		<span id="productCode"></span>
		 	</li>
		 	<li>
		 		<label>参考价(元)：</label>
		 		<span id="referPrice"></span>
		 	</li>
		 	<li>
		 		<label>制造商：</label>
		 		<span id="factory"></span>
		 	</li>
		 	<li class="fullLine">
		 		<label>产地：</label>
		 		<span id="madeIn"></span>
		 	</li>
		 	<li class="fullLine">
		 		<label>外部链接：</label>
		 		<span id="externalInforLink"></span>
		 	</li>
		 	
		 	<c:if test="${goods.paramInputType==02}">
		 		<li id="spec_1" class="fullLine">
	 				<label>详细配置</label>
	 				<span id="spec"></span>
	 			</li>
		 	</c:if>
	 		
	 		<c:if test="${goods.functionIntro!=null && goods.functionIntro != ''}">
		 		<li class="fullLine">
		 				<label>商品描述：</label>
		 				<span id="functionIntro"></span>
		 		</li>
		 	</c:if>
		</ul>
	</div>
	
	<div class="formTips attention">
		<span id="addPlanItem">
		<em>提示：</em>新增零配件请点击
		<a class="sysicon siAdd" id="addAccessory" href="javascript:void(0);"><strong>新增零配件</strong></a>
		</span>
	</div>
	
	<table class="frontTableList" id="GoodsAccessoriesList">
		<thead>
			<tr>
				<th class="left">配件名称</th>
				<th class="center">数量</th>
				<th class="center">状态</th>
				<th class="center">创建人</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</form>


<div class="conOperation">
	<button type="button" name="historyBackBtn" ><span>返回</span></button>
</div>
