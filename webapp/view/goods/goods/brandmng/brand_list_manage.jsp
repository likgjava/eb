<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/goods/brandmng/brand_list_manage.js"></script>
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
				<li >
					<label for="goodsClassNames">商品分类：</label>
					<input type="text" name="goodsClassNames">
					<input type="hidden" name="goodsClassNames_op" value="like">
				</li>
				<li>
					<label for="auditStatus">审核状态：</label>
					<select name="auditStatus">
						<option value=''>所有</option>
                        <option value='00'>新建待审核</option>
                        <option value='01'>新建审核中</option>
                        <option value='02'>通过</option>
                        <option value='03'>不通过</option>    
			       	</select>
				</li>
			    <li class="operationBtnDiv">
			        <button id = "brandSearch" type="button" onclick="brandList.getBrandList()"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>

<!-- 操作 -->
<div class="formTips attention">
	<ul>
		<li>
			新增品牌请点击
			<span id="addBrand"><a class="sysicon siAdd" href="<%=request.getContextPath()%>/GoodsBrandController.do?method=toCreateOrUpdateView" target="_blank"><strong>新增品牌</strong></a></span>
			在此进行批量
			<a id="delBrand" href="javascript:void(0);" onclick="brandList.delBrands();return false;"><strong>删除品牌</strong></a>
			<a id="stopBrand" href="javascript:void(0);" onclick="brandList.stopBrand();return false;" class="hidden"><strong>禁卖品牌</strong></a>
			<a id="destroyBrand" href="javascript:void(0);" onclick="brandList.destroyBrand();return false;" class="hidden"><strong>报废品牌</strong></a>
			<a id="startBrand" href="javascript:void(0);" onclick="brandList.startBrand();return false;" class="hidden"><strong>启卖品牌</strong></a>
		</li>
	</ul>
</div>

<div id="epsTabs">
		<ul>
			<li>
				<a href="#newBrand" id = "tabs_toSubmit" class="refreshData"><span>新建品牌</span></a>
			</li>
			<li>
				<a href="#newBrand" id = "tabs_hadPass" class="refreshData"><span>有效品牌</span></a>
			</li>
			<li>
				<a href="#newBrand" id = "tabs_notPass" class="refreshData"><span>报废/禁卖品牌</span></a>
			</li>
		</ul>
  		<div id="newBrand">
			<!-- 品牌列表 -->
			<div id="tabDemo">
				<table class="frontTableList" id="QualityList">
			      <thead>
			        <tr>
			          <th class="left">编号</th>
			          <th class="operation">LOGO</th>
			          <th class="left">名称</th>
			          <th class="omission">商品分类</th>
			          <th class="center">售卖状态</th>
			          <th class="center">审核状态</th>
			          <th class="operation">操作</th>
			        </tr>
			      </thead>
		      	  <tbody>
		      	  </tbody>
				</table>
			</div>
		</div>
</div>