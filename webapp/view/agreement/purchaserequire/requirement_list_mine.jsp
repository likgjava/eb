<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/purchaserequire/requirement_list_mine.js"></script>
<div>
	<!-- 搜索条件 -->
	<div class="conSearch">
		<h4><span>搜索</span></h4>
		<form id="requirementSearchForm" >
			<ul>
			    <li>
					<label>标题：</label>
					<input type="text" name="requirement.title" value="">
					<input type="hidden" name="requirement.title_op" value="like">
			    </li>
			    <li class="operationBtnDiv">
			        <button id = "requirementSearch" type="button" onclick="requirement_list.getRequirementList();"><span>查询</span></button>
			    </li>
		  </ul>
		 </form>
	</div>
</div>

<div id="epsTabs">
<ul>
	<li>
		<a href="#requirement" id = "tabs_pub" class="refreshData"><span>已报名</span></a>
	</li>
	<li>
		<a href="#requirement" id = "tabs_nopub" class="refreshData"><span>未通过</span></a>
	</li>
</ul>

<div id="requirement">
<!-- 需求列表 -->
	<div id="tabDemo">
		<table class="frontTableList" id="requirementList">
	      <thead>
	        <tr>
	          <th class="left omission">标题</th>
	          <th class="left">品目</th>
	          <th class="left">采购数量</th>
	          <th class="left">单个预算（元）</th>
	          <th class="left">区域</th>
	          <th class="left">结束时间</th>
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
