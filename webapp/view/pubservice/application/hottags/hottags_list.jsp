<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/hottags/hottags_list.js"></script>
<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="hottagsSearchForm" >
			<ul>
			    <li>
					<label>标签搜索：</label>
					<input type="text" name="tagsName" value="">
					<input type="hidden" name="tagsName_op" value="like">
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "hottagsSearch" type="button"><span>查询</span></button>
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
			<span id="addTags"><a class="sysicon siAdd" href="javascript:void(0);" onclick="hottagsList.newTags();return false;"><strong>新增标签</strong></a></span>
			在此进行批量
			<a href="javascript:void(0);" onclick="hottagsList.delTags(hottagsList.oTable.dtSelectArray());return false;"><strong>删除标签</strong></a>
		</li>
	</ul>
</div>


<div id="epsTabs">
	<ul>
		<li><a href="#hottags" id="tabs_goods" class="refreshData"><span>商品热门标签</span></a></li>
		<li><a href="#hottags" id="tabs_supplier" class="refreshData"><span>供应商热门标签</span></a></li>
		<!--<li><a href="#hottags" id = "tabs_agency" class="refreshData"><span>代理机构热门标签</span></a></li>-->
		<li><a href="#hottags" id="tabs_price" class="refreshData"><span>行情热门标签</span></a></li>
		<li><a href="#hottags" id="tabs_goodsClass" class="refreshData"><span>商品分类热门标签</span></a></li>
		<li><a href="#hottags" id="tabs_purCategory" class="refreshData"><span>采购品目热门标签</span></a></li>
	</ul>
	<div id="hottags">
		<div id="tabDemo">
			<table class="frontTableList" id="hottagsList">
				<thead>
					<tr>
						<th>标签名</th>
						<th class="center">加入时间</th>
						<th class="operation">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>