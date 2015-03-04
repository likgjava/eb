<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/pubservice/application/template/template_list.js"/>'></script>

<!--搜索条件 -->
<div class="conSearch">
	<h4><span>搜索</span></h4>
	<form id="templateSearchForm">
		<input type="hidden" name="createUser.objId" value="${userId }" />
		<ul>
		    <li>
				<label>范本名称：</label>
				<input type="text" id="templateName" name="templateName" />
				<input type="hidden" id="templateNameOp" name="templateName_op" value="like" />
		    </li>
		    <li>
				<label>区域名称：</label>
				<input type="text" id="districtName" name="districtName" />
				<input type="hidden" id="districtNameOp" name="districtName_op" value="like" />
		    </li>
		    <li>
				<label>品目名称：</label>
				<input type="text" id="categoryName" name="categoryName" />
				<input type="hidden" id="categoryNameOp" name="categoryName_op" value="like" />
		    </li>
		    <li class="hidden" id="projectNameInput">
				<label>项目名称：</label>
				<input type="text" name="projectName" />
				<input type="hidden" name="projectName_op" value="like" />
		    </li>
		    <li class="hidden" id="favoriteNameInput">
				<label>收藏名称：</label>
				<input type="text" name="favoriteName" />
				<input type="hidden" name="favoriteName_op" value="like" />
		    </li>
		    <li>
				<label>范本类型：</label>
				<html:select id="templateType" name="templateType" code="pubservice.application.template.templateType">
					<html:option value="">全部</html:option>
				</html:select>
		    </li>
		    <li class="operationBtnDiv">
		        <button type="button" id="queryTemplateList"><span>查询</span></button>
		    </li>
		</ul>
	 </form>
</div>

<div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>新增范本请点击<a id="addTemplate" class="sysicon siAdd" href="javascript:void(0);"><span><strong>新增范本</strong></span></a>
		</li>
	</ul>
</div>

<!--范本列表-->
<div id="epsTabs">
	<ul>
		<li><a href="#myTemplateInfo" class="refreshData" id="tabs_my"><span>我的范本</span></a></li>
		<li><a href="#favoriteTemplateInfo" class="refreshData" id="tabs_favorite"><span>收藏的范本</span></a></li>
		<li><a href="#downTemplateInfo" class="refreshData" id="tabs_down"><span>下载的范本</span></a></li>
		<li><a href="#usedTemplateInfo" class="refreshData" id="tabs_used"><span>使用过的范本</span></a></li>
	</ul>
	<!-- 我创建的范本 -->
	<div id="myTemplateInfo">
		<table class="frontTableList" id="myTemplateList">
			<thead>
				<tr>
					<th class="omission" omiLength="10">范本名称</th>
					<th class="omission" omiLength="10">区域名称</th>
					<th class="omission" omiLength="10">品目名称</th>
					<th>范本类型</th>
					<th class="center">共享</th>
					<th class="center">收藏|下载</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	
	<!-- 收藏的范本 -->
	<div id="favoriteTemplateInfo">
		<table class="frontTableList" id="favoriteTemplateList">
			<thead>
				<tr>
					<th class="omission" omiLength="8">收藏名称</th>
					<th class="omission" omiLength="8">范本名称</th>
					<th class="omission" omiLength="8">区域名称</th>
					<th class="omission" omiLength="8">品目名称</th>
					<th>范本类型</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	
	<!-- 下载的范本 -->
	<div id="downTemplateInfo">
		<table class="frontTableList" id="downTemplateList">
			<thead>
				<tr>
					<th class="omission" omiLength="10">范本名称</th>
					<th class="omission" omiLength="8">区域名称</th>
					<th class="omission" omiLength="8">品目名称</th>
					<th>范本类型</th>
					<th class="date">下载时间</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	
	<!-- 使用过的范本 -->
	<div id="usedTemplateInfo">
		<table class="frontTableList" id="usedTemplateList">
			<thead>
				<tr>
					<th class="omission" omiLength="10">范本名称</th>
					<th class="omission" omiLength="10">品目名称</th>
					<th class="omission" omiLength="10">项目名称</th>
					<th>范本类型</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>
