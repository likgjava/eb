<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/init.jsp"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/bizplatform/suppliers/recommend/recommend_supplier.js"></script>

<input type="hidden"" name="recommendReason" id="recommendReason" value=""/>
<!-- 查询条件 -->
<div class="conSearch">
	<h4><span><spring:message code="globe.query" /></span></h4>
	<!-- 未推荐供应商搜索表单 -->
	<form id="noRecommendSupplierSearchForm">
		<ul >
			<li>
				<label for="orgName">机构名称：</label>
				<input type="text" name="orgName">
			</li>
			<li>
				<label>企业性质：</label>
				<html:select id="entPrpt" name="entPrpt" code="biz.buyer.unitType">
					<html:option value="">全部</html:option>
				</html:select>
			</li>
			<li>
				<label for="unitScape">企业规模：</label>
				<html:select id="unitScape" name="unitScape" code="biz.orgInfo.unitScape">
					<html:option value="">全部</html:option>
				</html:select>
			</li>
			<li class="operationBtnDiv">
				<button type="button" id="query1"><span>查询</span></button>
			</li>
		</ul>
	</form>
	<!-- 已推荐供应商搜索表单 -->
	<form id="recommendSupplierSearchForm" class="hidden">
		<ul >
			<li>
				<label>机构名称：</label>
				<input type="text" name="orgInfo.orgName" id="orgInfo.orgName" >
				<input type="hidden" name="orgInfo.orgName_op" value="like">
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
		<li id="recommendSupplierBatchLi">
			批量推荐供应商请点击
			<span class="sysicon siAdd">
				<a id="recommendSupplierBatch" style="hide-focus: true" href="javascript:void(0);"><strong>批量推荐供应商</strong></a>
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

<!-- 供应商列表 -->
<div id="epsTabs">
  	<ul>
	    <li>
	      <a href="#supplierInfos1" id = "tabs_toNoRecommend" class="refreshData"><span>未推荐</span></a>
	    </li>
	    <li>
	      <a href="#supplierInfos2" id = "tabs_toRecommend" class="refreshData"><span>已推荐</span></a>
	    </li>
	</ul>
	<!-- 未推荐供应商列表 -->
	<div id="supplierInfos1">
		<table class="frontTableList" id="supplierList1">
			<thead>
				<tr>
					<th>机构名称</th>
					<th class="center">企业性质</th>
					<th class="center">企业规模</th>
					<!--<th class="money">注册资金(万元)</th>-->
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	<!-- 已推荐项目列表 -->
	<div id="supplierInfos2">
		<table class="frontTableList" id="supplierList2">
			<thead>
				<tr>
					<th class="omission">机构名称</th>
					<th class="omission">推荐理由</th>
					<th class="center">排序</th>
					<th class="operation">操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
