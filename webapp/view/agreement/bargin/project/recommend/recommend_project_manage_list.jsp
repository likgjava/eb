<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/bargin/project/recommend/recommend_project_manage_list.js"></script>

<input type="hidden" id="recommendSuccess" value="0" />

<!-- 查询条件 -->
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<!-- 未推荐项目搜索表单 -->
	<form id="noRecommendProjectSearchForm">
		<ul>
			<li>
				<label for="projCode">项目编号：</label>
				<input type="text" name="projCode" id="projCode" >
			</li>
			<li>
				<label for="projName"> 项目名称：</label>
				<input type="text" name="projName" id="projName">
			</li>
			<li>
				<label for="ebuyMethod">采购方式：</label>
				<select name="ebuyMethod" id="ebuyMethod">
					<option value="">全部</option>
					<option value="06">竞价</option>
					<option value="00">公开招标</option>
				</select>
			</li>
			<li>
				<label for="amountRange">预算金额：</label>
				<select name="amountRange" id="amountRange">
					<option value="">全部</option>
					<option value="0,100000">10万以下</option>
					<option value="100000,200000">10万--20万</option>
					<option value="200000,300000">20万--30万</option>
					<option value="300000,400000">30万--40万</option>
					<option value="400000,500000">40万--50万</option>
					<option value="500000,99999999999999">50万以上</option>
				</select>
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query1"><span>查询</span></button>
			</li>
		</ul>
	</form>
	<!-- 已推荐项目搜索表单 -->
	<form id="recommendProjectSearchForm" class="hidden">
		<ul >
			<li>
				<label for="project.projCode">项目编号：</label>
				<input type="text" name="project.projCode" id="project.projCode" >
				<input type="hidden" name="project.projCode_op" value="like">
			</li>
			<li>
				<label for="project.projName"> 项目名称：</label>
				<input type="text" name="project.projName" id="project.projName">
				<input type="hidden" name="project.projName_op" value="like">
			</li>
			<li>
				<label for="project.ebuyMethod">采购方式：</label>
				<select name="project.ebuyMethod" id="project.ebuyMethod">
					<option value="">全部</option>
					<option value="06">竞价</option>
					<option value="00">公开招标</option>
				</select>
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query2"><span>查询</span></button>
			</li>
		</ul>
	</form>
</div>

<!-- 操作 -->
<div class="formTips attention">
	<ul>
		<li id="recommendProjectBatchLi">
			批量推荐项目请点击
			<span class="sysicon siAdd">
				<a id="recommendProjectBatch" style="hide-focus: true" href="javascript:void(0);"><strong>批量推荐项目</strong></a>
			</span>
		</li>
		<li id="cancleRecommendBatchsLi" class="hidden">
			批量取消推荐请点击
			<span class="sysicon siAdd">
				<a id="cancleRecommendBatch" style="hide-focus: true" href="javascript:void(0);"><strong>批量取消推荐</strong></a>
			</span>
		</li>
	</ul>
</div>

<!-- 项目列表 -->
<div id="epsTabs">
  	<ul>
	    <li><a href="#projectInfos1" id = "tabs_toNoRecommend" class="refreshData"><span>未推荐</span></a></li>
	    <li><a href="#projectInfos2" id = "tabs_toRecommend" class="refreshData"><span>已推荐</span></a></li>
	</ul>
	<!-- 未推荐项目列表 -->
	<div id="projectInfos1">
		<table class="frontTableList" id="projectList1">
			<thead>
				<tr>
					<th>项目编号</th>
					<th class="omission">项目名称</th>
					<th class="money">采购预算(元)</th>
					<th class="date">评标开始时间</th>
					<th class="date">评标结束时间</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- 已推荐项目列表 -->
	<div id="projectInfos2">
		<table class="frontTableList" id="projectList2">
			<thead>
				<tr>
					<th>图片</th>
					<th>项目编号</th>
					<th class="omission">项目名称</th>
					<th class="date">评标开始时间</th>
					<th class="date">评标结束时间</th>
					<th class="center">排序</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
