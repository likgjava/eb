<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src='<c:url value="/view/bizplatform/organization/manager/org_quality_supplier.js"/>'></script>




<div id="epsTabs">
		<ul>
			<li>
				<a href="#tabDemo" id = "tabs_new" class="refreshData"><span>新建</span></a>
			</li>
			<li>
				<a href="#tabDemo" id = "tabs_await" class="refreshData"><span>待审核</span></a>
			</li>
			<li>
				<a href="#tabDemo" id = "tabs_pass" class="refreshData"><span>审核通过</span></a>
			</li>
		</ul>
		<div id="tabDemo">
			<div class="operationBtnDiv r" id="addQualityDiv">
				<button id="addQuality"><span>新增资质</span></button>
			</div>
			<table class="frontTableList" id="QualityList">
			      <thead>
			        <tr>
			          <th class="left">分类</th>
			          <th class="left">定义</th>
			          <th class="center">审核状态</th>
			          <th class="center">创建时间</th>
			          <th class="operation">操作</th>
			        </tr>
			      </thead>
			      <tbody>
			      </tbody>
			</table>
		</div>
</div>
