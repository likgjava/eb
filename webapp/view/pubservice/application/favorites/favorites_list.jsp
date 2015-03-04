<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/pubservice/application/favorites/favorites_list.js"></script>
<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="favofitesSearchForm" >
			<ul>
			    <li>
					<label>标签搜索：</label>
					<input type="text" name="favoriteKey" value="">
					<input type="hidden" name="favoriteKey_op" value="like">
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "favofitesSearch" type="button"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>

<div id="epsTabs">
	<ul>
		<li><a href="#favofites" id="tabs_goods" class="refreshData"><span>收藏的商品</span></a></li>
		<li><a href="#favofites" id="tabs_supplier" class="refreshData"><span>收藏的供应商</span></a></li>
		<li><a href="#favofites" id="tabs_agency" class="refreshData"><span>收藏的采购人</span></a></li>
		<li><a href="#favofites" id="tabs_expert" class="refreshData"><span>收藏的专家</span></a></li>
		<li><a href="#favofites" id="tabs_bulletin" class="refreshData"><span>收藏的项目</span></a></li>
		<!-- 暂屏蔽
		<li><a href="#favofites" id="tabs_agency" class="refreshData"><span>收藏的代理机构</span></a></li>
		<li><a href="#favofites" id="tabs_hotel" class="refreshData"><span>收藏的酒店</span></a></li>
		-->
	</ul>
	<div id="favofites">
		<div id="tabDemo">
			<table class="frontTableList" id="favofitesList">
				<thead>
					<tr>
						<th>名称</th>
						<th>标签</th>
						<th class="center">收藏时间</th>
						<th class="operation">操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>