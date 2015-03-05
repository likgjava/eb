<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/smallscale/groupbuying/group_buying_class_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="groupBuyingSearchForm">
		<ul>
		    <li>
				<label>分类名称：</label>
				<input type="text" name="name">
				<input type="hidden" name="name_op" value="like">
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" onclick="GroupBuyingClassList.getGroupBuyingClassList();"><span>查询</span></button>
		    </li>
		</ul>
	 </form>
</div>

<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>新增团购分类请点击<a id="addGroupBuyingClass" class="sysicon siAdd" href="javascript:void(0);"><span><strong>新增团购分类</strong></span></a>
		</li>
	</ul>
</div>

<!--团购分类列表-->
<div>
	<table class="frontTableList" id="groupBuyingClassList">
		<thead>
			<tr>
				<th class="omission" omiLength="20">名称</th>
				<th class="omission" omiLength="20">商品分类名称</th>
				<th class="operation">在首页显示</th>
				<th class="operation">排序</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
