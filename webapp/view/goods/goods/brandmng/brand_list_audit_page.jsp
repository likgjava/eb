<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goods/brandmng/brand_list_audit_page.js"></script>
<div>

	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="brandSearchForm" >
			<ul>
			    <li>
					<label for="brandCode">品牌编号：</label>
					<input type="text" name="brandCode" value="">
					<input type="hidden" name="brandCode_op" value="like">
			    </li>
			    <li>
					<label for="brandName">品牌名称：</label>
					<input type="text" name="brandName" value="">
					<input type="hidden" name="brandName_op" value="like">
			    </li>
			    <li>
					<label for="englishName">品牌英文名称：</label>
					<input type="text" name="englishName" value="">
					<input type="hidden" name="englishName_op" value="like">
			    </li>
				<li>
					<label for="goodsClassNames">商品分类：</label>
					<input type="text" name="goodsClassNames">
					<input type="hidden" name="goodsClassNames_op" value="like">
				</li>
			    <li class="operationBtnDiv">
			        <button id = "brandSearch" type="button"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
	
	
	<div id="epsTabs">
		<ul>
			<li>
				<a href="#newBrand" id = "tabs_toSubmit" class="refreshData"><span>新建品牌</span></a>
			</li>
			<li>
				<a href="#newBrand" id = "tabs_toAudit" class="refreshData"><span>变更品牌</span></a>
			</li>
		</ul>
	<!-- 品牌列表 -->
	<div id="newBrand">
		<table class="frontTableList" id="QualityList">
	      <thead>
	        <tr>
	          <th class="left">编号</th>
	          <th class="left">名称</th>
	          <th class="left">英文名称</th>
	          <th class="omission">商品分类</th>
	          <th class="operation">操作</th>
	        </tr>
	      </thead>
      	  <tbody>
      	  </tbody>
		</table>
	</div>
	</div>
</div>